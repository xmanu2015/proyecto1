package pe.com.tdp.ventafija.microservices.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pe.com.tdp.ventafija.microservices.common.util.exception.ApplicationException;
import pe.com.tdp.ventafija.microservices.common.util.exception.Exception;
import pe.com.tdp.ventafija.microservices.domain.Response;
import pe.com.tdp.ventafija.microservices.domain.TdpCatalogAditionalData;
import pe.com.tdp.ventafija.microservices.domain.TdpCatalogData;
import pe.com.tdp.ventafija.microservices.domain.dto.InventResult;
import pe.com.tdp.ventafija.microservices.domain.dto.InventSyntaxError;
import pe.com.tdp.ventafija.microservices.repository.TdpCatalogAditionalDao;
import pe.com.tdp.ventafija.microservices.repository.TdpCatalogDao;
import pe.com.tdp.ventafija.microservices.repository.TdpCatalogRepository;
import pe.com.tdp.ventafija.microservices.util.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;

@Service
public class CartillaProductoServiceImpl {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private TdpCatalogRepository tdpCatalogRepository;
    @Autowired
    private TdpCatalogDao tdpCatalogDao;
    @Autowired
    private TdpCatalogAditionalDao tdpCatalogAditionalDao;
    @Autowired
    private AzureService azureService;

    @Value("${ftp.api.connection.dirout}")
    private String remoteDirectoryOut;
    @Value("${azure.api.connection.replace.sftp}")
    private String azureConnectionReplaceSftp;
    @Value("${visor.api.sync.enabled}")
    private Boolean syncEnabled;

    //@Scheduled(cron = "${carga.cartilla.productosMT.api.sync.cron}")
    @Scheduled(cron = "*/30 * * * * *")
    public void loadFileOut() {
        if (!syncEnabled) {
            logger.info("Carga del archivo para cartilla de productos Movistar Total no habilitada");
            return;
        }

        Date ahora = new Date();
        long ahoramilisecs = ahora.getTime();
        logger.info("iniciando carga de cartilla de productos Movistar Total " + ahora + " .....");
        //tdpCatalogRepository.deleteAll();
        List<String> filesToProcess = azureService.listFiles(azureConnectionReplaceSftp);
        logger.info(String.format("listado de archivos del container storage en %s minutos", ((double) ((new Date()).getTime() - ahoramilisecs)) / 60000));
        ahoramilisecs = new Date().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        sdf.setTimeZone(TimeZone.getTimeZone("America/Lima"));
        String actualMonth = "CARTILLA_PRINCIPAL_MT_" + sdf.format(new Date());

        for (String nameFile : filesToProcess) {
            if (nameFile != null && nameFile.startsWith(actualMonth)) {
                logger.info("archivo: " + nameFile);
                if (nameFile.toLowerCase().contains("cartilla")) {
                    processFile(nameFile, ahoramilisecs);
                } else {
                    logger.info("Este archivo no esta siendo considerado.");
                }
            }
        }

    }

    public void processFile(String nameFile, Long ahoramilisecs) {
        logger.info("inicio procesar archivo: " + nameFile);
        String newFileName = String.format("COF_%s_%s", new Date().getTime(), nameFile);
        logger.info(String.format("Archivo cambiado de nombre en %s minutos", ((double) ((new Date()).getTime() - ahoramilisecs)) / 60000));
        ahoramilisecs = new Date().getTime();

        String filePath = "." + remoteDirectoryOut + nameFile;

        try {
            syncCartillaProductFileOutlets(filePath, nameFile);
            azureService.moveFile(nameFile, newFileName, azureConnectionReplaceSftp, "BASESOUT", "Backup");
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            int position = nameFile.lastIndexOf(".txt");
            String errorFileName = nameFile;
            if (position != -1) {
                errorFileName = nameFile.substring(0, position) + "0.txt";
            }
            //azureService.moveFile(nameFile, errorFileName, azureConnectionReplaceSftp, "BASESOUT", "Error");
        }
        logger.info(String.format("Archivo procesado en %s minutos", ((double) ((new Date()).getTime() - ahoramilisecs)) / 60000));
    }

    public Response<String> syncCartillaProductFileOutlets(String remoteFilePath, String fileNameTxt) throws Exception {
        logger.info("starting syncEquipmentOutlets of file: " + remoteFilePath);
        Response<String> response = new Response<>();
        File file = null;
        try {
            file = azureService.retrieveFile(fileNameTxt, azureConnectionReplaceSftp);
            if (file != null) {
                if (fileNameTxt.toLowerCase().contains("cartilla")) {
                    processCartillaProductFile(file, fileNameTxt);
                }
            } else {
                throw new ApplicationException("file.not.found");
            }

            response.setResponseCode("0");
            response.setResponseMessage("Cartilla de productos Movistar Total se sincronizo correctamente.");
            logger.info("Cartilla de productos Movistar Total se sincronizo correctamente.");
        } catch (IOException | ApplicationException e) {
            logger.info("Error al buscar el archivo");
            response.setResponseCode("1");
            response.setResponseMessage("Error al sincronizar cartilla de productos Movistar Total.");
        } finally {
            if (file != null) {
                file.delete();
            }
        }
        logger.info("sync1 finished");
        return response;
    }

    public void processCartillaProductFile(File file, String fileNameTxt) throws IOException, Exception {
        logger.info("PuntosVenta.processEquipmentFile .........");
        List<TdpCatalogData> outsCatalog = new ArrayList<>();
        List<InventSyntaxError> syntaxErrors = new ArrayList<>();

        FileInputStream fstream = new FileInputStream(file);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.ISO_8859_1));

        String line;
        int count = 0;

        while ((line = br.readLine()) != null) {

            count++;
            line = line.replace("||", "| |").replace("||", "| |");
            line = line + "|";
            String[] out = line.split(Pattern.quote("|"));

            if (out.length < 8) {
                logger.info("error al procesar linea " + line);
                syntaxErrors.add(new InventSyntaxError(InventSyntaxError.CAUSE_INCOMPLETE_LINE, line));
                continue;
            }

            TdpCatalogData model = new TdpCatalogData();
            model.setCodigoproducto(StringUtils.trim(out[0]));
            model.setCommercialoperation(StringUtils.trim(out[1]));
            model.setSegmento(StringUtils.trim(out[2]));
            model.setCanal(StringUtils.trim(out[3]));
            model.setEntidad(StringUtils.trim(out[4]));
            model.setProvincia(StringUtils.trim(out[5]));
            model.setDistrito(StringUtils.trim(out[6]));
            model.setCampaign(StringUtils.trim(out[7]));
            model.setCampaingcode(StringUtils.trim(out[8]));
            model.setPriority(StringUtils.trim(out[9]));
            model.setProdtypecode(StringUtils.trim(out[10]));
            model.setProdcategorycode(StringUtils.trim(out[11]));
            model.setProducttype(StringUtils.trim(out[12]));
            model.setProductcategory(StringUtils.trim(out[13]));
            model.setProductcode(StringUtils.trim(out[14]));
            model.setProductname(StringUtils.trim(out[15]));
            model.setBloquetv(StringUtils.trim(out[16]));
            model.setSvainternet(StringUtils.trim(out[17]));
            model.setSvalinea(StringUtils.trim(out[18]));
            model.setTiporegistro(StringUtils.trim(out[19]));
            model.setDiscount(StringUtils.trim(out[20]));
            model.setPrice(StringUtils.trim(out[21]));
            model.setPromprice(StringUtils.trim(out[22]));
            model.setMonthperiod(StringUtils.trim(out[23]));
            model.setInstallcost(StringUtils.trim(out[24]));
            model.setLinetype(StringUtils.trim(out[25]));
            model.setPaymentmethod(StringUtils.trim(out[26]));
            model.setCashprice(StringUtils.trim(out[27]));
            model.setFinancingcost(StringUtils.trim(out[28]));
            model.setFinancingmonth(StringUtils.trim(out[29]));
            model.setEquipmenttype(StringUtils.trim(out[30]));
            model.setReturnmonth(StringUtils.trim(out[31]));
            model.setReturnperiod(StringUtils.trim(out[32]));
            model.setInternetspeed(StringUtils.trim(out[33]));
            model.setPromspeed(StringUtils.trim(out[34]));
            model.setPeriodpromspeed(StringUtils.trim(out[35]));
            model.setInternettech(StringUtils.trim(out[36]));
            model.setTvsignal(StringUtils.trim(out[37]));
            model.setTvtech(StringUtils.trim(out[38]));
            model.setEquiplinea(StringUtils.trim(out[39]));
            model.setEquipinternet(StringUtils.trim(out[40]));
            model.setEquiptv(StringUtils.trim(out[41]));
            model.setOrigintvtechnology(StringUtils.trim(out[42]));
            model.setTypetypedecoorigin(StringUtils.trim(out[43]));
            model.setTypedecoorigin(StringUtils.trim(out[44]));
            model.setQuantitytypedeco(StringUtils.trim(out[45]));
            model.setInternettechnologyorigin(StringUtils.trim(out[46]));
            model.setInternetequipmentorigin(StringUtils.trim(out[47]));
            model.setComfortcomfortrepeaterequipment(StringUtils.trim(out[48]));
            model.setComfortrepeater(StringUtils.trim(out[49]));
            model.setVoicetechnology(StringUtils.trim(out[50]));
            model.setMigrationlogicfromtdmtovoip(StringUtils.trim(out[51]));
            model.setMigratevoiptovoip(StringUtils.trim(out[52]));
            model.setHfctoftthmigrationlogic(StringUtils.trim(out[53]));
            model.setHerramienta(fileNameTxt);

            outsCatalog.add(model);
        }

        in.close();
        fstream.close();

        InventResult resultCatalog = tdpCatalogDao.cartillaProudctosMT(outsCatalog, fileNameTxt);

        ProcessCatalogAditional(outsCatalog, fileNameTxt);

        resultCatalog.setSyntaxErrors(syntaxErrors);
        resultCatalog.setErrorCount(resultCatalog.getErrorCount() + syntaxErrors.size());

        logger.info(String.format("Total registros leidos: %s, insertados: %s, actualizados: %s,actualizados order: %s, errores: %s",
                outsCatalog.size(), resultCatalog.getInsertCount(), resultCatalog.getUpdateCount(), resultCatalog.getOrderCount(), resultCatalog.getErrorCount()));

        List<String> linesWithError = new ArrayList<>();
        for (InventSyntaxError error : syntaxErrors) {
            linesWithError.add(error.getLine());
        }

        logger.info("fin PuntosVenta.processEquipmentFile .....................");

    }

    public void ProcessCatalogAditional(List<TdpCatalogData> outsCatalog, String fileNameTxt) throws IOException, Exception {
        logger.info("PuntosVenta.ProcessCatalogAditional .........");

        List<String> listCaneles = new ArrayList<>();
        List<String> listEntidades = new ArrayList<>();
        List<String> listCampanas = new ArrayList<>();
        List<String> listProvincias = new ArrayList<>();
        List<String> listDistrito = new ArrayList<>();
        List<TdpCatalogAditionalData> outsCatalogAtional = new ArrayList<>();

        for (TdpCatalogData invent : outsCatalog) {

            String caneles = invent.getCanal().replace("ñ", "n");
            String entidades = invent.getEntidad().replace("ñ", "n");
            String campanas = invent.getCampaign().replace("ñ", "n");
            String provincias = invent.getProvincia().replace("ñ", "n");
            String distrito = invent.getDistrito().replace("ñ", "n");

            listCaneles.add(caneles);
            listEntidades.add(entidades);
            listCampanas.add(campanas);
            listProvincias.add(provincias);
            listDistrito.add(distrito);

            for (String prov: listProvincias) {
                if ('-' ==)
            }





            TdpCatalogAditionalData model = new TdpCatalogAditionalData();
            model.setProductId(invent.getProdtypecode());
            model.setParameterId("");
            model.setValue("");
            model.setHerramienta("");
            outsCatalogAtional.add(model);
        }

        InventResult resultCatalogCatalogAditional = tdpCatalogAditionalDao.CatalogAditional(outsCatalogAtional, fileNameTxt);

    }

}
