package pe.com.tdp.ventafija.microservices.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pe.com.tdp.ventafija.microservices.common.util.Constants;
import pe.com.tdp.ventafija.microservices.common.util.exception.ApplicationException;
import pe.com.tdp.ventafija.microservices.common.util.exception.Exception;
import pe.com.tdp.ventafija.microservices.domain.Response;
import pe.com.tdp.ventafija.microservices.domain.TdpCatalogAditionalData;
import pe.com.tdp.ventafija.microservices.domain.TdpCatalogData;
import pe.com.tdp.ventafija.microservices.domain.dto.InventResult;
import pe.com.tdp.ventafija.microservices.domain.dto.InventSyntaxError;
import pe.com.tdp.ventafija.microservices.domain.dto.ServiceCallEvent;
import pe.com.tdp.ventafija.microservices.repository.ServiceCallEventsDao;
import pe.com.tdp.ventafija.microservices.repository.TdpCatalogAditionalDao;
import pe.com.tdp.ventafija.microservices.repository.TdpCatalogDao;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class CartillaProductoServiceImpl {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private TdpCatalogDao tdpCatalogDao;
    @Autowired
    private TdpCatalogAditionalDao tdpCatalogAditionalDao;
    @Autowired
    private ServiceCallEventsDao serviceCallEventsDao;

    @Autowired
    private AzureService azureService;

    @Value("${ftp.api.connection.dirout}")
    private String remoteDirectoryOut;
    @Value("${azure.api.connection.replace.sftp}")
    private String azureConnectionReplaceSftp;
    @Value("${visor.api.sync.enabled}")
    private Boolean syncEnabled;

    List<TdpCatalogAditionalData> outsCatalogAditional = new ArrayList<>();

    //@Scheduled(cron = "${carga.cartilla.productosMT.api.sync.cron}")
    @Scheduled(cron = "*/59 * * * * *")
    public void loadFileOutMT() {
        if (!syncEnabled) {
            logger.info("Carga del archivo para cartilla de productos MT no habilitada");
            return;
        }

        Date ahora = new Date();
        long ahoramilisecs = ahora.getTime();
        logger.info("iniciando carga de cartilla de productos Movistar Total " + ahora + " .....");
        List<String> filesToProcess = azureService.listFiles(azureConnectionReplaceSftp);
        logger.info(String.format("listado de archivos del container storage en %s minutos", ((double) ((new Date()).getTime() - ahoramilisecs)) / 60000));
        ahoramilisecs = new Date().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MMyyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("America/Lima"));
        String actualMonth = Constants.NOMBRE_CARTILLA_MT;

        for (String nameFile : filesToProcess) {
            if (nameFile != null && nameFile.startsWith(actualMonth)) {
                logger.info("archivo: " + nameFile);
                if (nameFile.toLowerCase().contains("cartilla")) {
                    processFile(nameFile, ahoramilisecs, Constants.CARTILLA_MT);
                } else {
                    logger.info("Este archivo no esta siendo considerado.");
                }
            }
        }
    }

    @Scheduled(cron = "*/30 * * * * *")
    public void loadFileOutSA() {
        if (!syncEnabled) {
            logger.info("Carga del archivo para cartilla de productos SA no habilitada");
            return;
        }

        Date ahora = new Date();
        long ahoramilisecs = ahora.getTime();
        logger.info("iniciando carga de cartilla de productos SA " + ahora + " .....");
        List<String> filesToProcess = azureService.listFiles(azureConnectionReplaceSftp);
        logger.info(String.format("listado de archivos del container storage en %s minutos", ((double) ((new Date()).getTime() - ahoramilisecs)) / 60000));
        ahoramilisecs = new Date().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MMyyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("America/Lima"));
        String actualMonth = Constants.NOMBRE_CARTILLA_SA;

        for (String nameFile : filesToProcess) {
            if (nameFile != null && nameFile.startsWith(actualMonth)) {
                logger.info("archivo: " + nameFile);
                if (nameFile.toLowerCase().contains("cartilla")) {
                    processFile(nameFile, ahoramilisecs, Constants.CARTILLA_SA);
                } else {
                    logger.info("Este archivo no esta siendo considerado.");
                }
            }
        }
    }

    public void processFile(String nameFile, Long ahoramilisecs, String prefix) {
        logger.info("inicio procesar archivo: " + nameFile);
        String newFileName = String.format("COF_%s_%s", new Date().getTime(), nameFile);
        logger.info(String.format("Archivo cambiado de nombre en %s minutos", ((double) ((new Date()).getTime() - ahoramilisecs)) / 60000));
        ahoramilisecs = new Date().getTime();

        String filePath = "." + remoteDirectoryOut + nameFile;

        try {
            syncCartillaProductFileOutlets(filePath, nameFile, prefix);
            azureService.moveFile(nameFile, newFileName, azureConnectionReplaceSftp, "BASESOUT", "Backup");
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            int position = nameFile.lastIndexOf(".txt");
            String errorFileName = nameFile;
            if (position != -1) {
                errorFileName = nameFile.substring(0, position) + "0.txt";
            }
            azureService.moveFile(nameFile, errorFileName, azureConnectionReplaceSftp, "BASESOUT", "Error");
        }
        logger.info(String.format("Archivo procesado en %s minutos", ((double) ((new Date()).getTime() - ahoramilisecs)) / 60000));
    }

    public Response<String> syncCartillaProductFileOutlets(String remoteFilePath, String fileNameTxt, String prefix) throws Exception {
        logger.info("starting syncEquipmentOutlets of file: " + remoteFilePath);
        Response<String> response = new Response<>();
        File file = null;
        try {
            file = azureService.retrieveFile(fileNameTxt, azureConnectionReplaceSftp);
            boolean validateFile = false;
            if (file != null) {
                validateFile = processNameFile(fileNameTxt, prefix);
                if (validateFile){
                    processCartillaProductFile(file, fileNameTxt, prefix);
                }
            } else {
                throw new ApplicationException("file.not.found");
            }

            response.setResponseCode("0");
            response.setResponseMessage("Cartilla de productos %s se sincronizo correctamente.");
            logger.info("Cartilla de productos %s se sincronizo correctamente.");
        } catch (IOException | ApplicationException e) {
            logger.info("Error al buscar el archivo");
            response.setResponseCode("1");
            response.setResponseMessage("Error al sincronizar cartilla.");
        } finally {
            if (file != null) {
                file.delete();
            }
        }
        logger.info("sync1 finished");
        return response;
    }

    /*procesar archivo de cartilla txt y guardado en tdp_catalog*/
    public void processCartillaProductFile(File file, String fileNameTxt, String prefix) throws IOException, Exception {
        logger.info("CartillaProductos.processEquipmentFile .........");
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
            model.setCodigoproducto(normalizeUsingJavaText(StringUtils.trim(out[0])));
            model.setCommercialoperation(normalizeUsingJavaText(StringUtils.trim(out[1])));
            model.setSegmento(normalizeUsingJavaText(StringUtils.trim(out[2])));
            model.setCanal(normalizeUsingJavaText(StringUtils.trim(out[3])));
            model.setEntidad(normalizeUsingJavaText(StringUtils.trim(out[4])));
            model.setProvincia(normalizeUsingJavaText(StringUtils.trim((out[5]))));
            model.setDistrito(normalizeUsingJavaText(StringUtils.trim((out[6]))));
            model.setCampaign(normalizeUsingJavaText(StringUtils.trim(out[7])));
            model.setCampaingcode(normalizeUsingJavaText(StringUtils.trim(out[8])));
            model.setPriority(Integer.parseInt(normalizeUsingJavaText(StringUtils.trim(out[9]))));
            model.setProdtypecode(normalizeUsingJavaText(StringUtils.trim(out[10])));
            model.setProdcategorycode(normalizeUsingJavaText(StringUtils.trim(out[11])));
            model.setProducttype(normalizeUsingJavaText(StringUtils.trim(out[12])));
            model.setProductcategory(normalizeUsingJavaText(StringUtils.trim(out[13])));
            model.setProductcode(normalizeUsingJavaText(StringUtils.trim(out[14])));
            model.setProductname(normalizeUsingJavaText(StringUtils.trim(out[15])));
            model.setBloquetv(normalizeUsingJavaText(StringUtils.trim(out[16])));
            model.setSvainternet(normalizeUsingJavaText(StringUtils.trim(out[17])));
            model.setSvalinea(normalizeUsingJavaText(StringUtils.trim(out[18])));
            model.setTiporegistro(normalizeUsingJavaText(StringUtils.trim(out[19])));
            model.setDiscount(normalizeUsingJavaText(StringUtils.trim(out[20])));
            model.setPrice(Double.parseDouble(StringUtils.trim(out[21])));
            model.setPromprice(Double.parseDouble(StringUtils.trim(out[22])));
            model.setMonthperiod(Integer.parseInt(StringUtils.trim(out[23])));
            model.setInstallcost(Double.parseDouble(StringUtils.trim(out[24])));
            model.setLinetype(normalizeUsingJavaText(StringUtils.trim(out[25])));
            model.setPaymentmethod(normalizeUsingJavaText(StringUtils.trim(out[26])));
            model.setCashprice(Double.parseDouble(StringUtils.trim(out[27])));
            model.setFinancingcost(Double.parseDouble(StringUtils.trim(out[28])));
            model.setFinancingmonth(Integer.parseInt(StringUtils.trim(out[29])));
            model.setEquipmenttype(normalizeUsingJavaText(StringUtils.trim(out[30])));
            model.setReturnmonth(Integer.parseInt(StringUtils.trim(out[31])));
            model.setReturnperiod(normalizeUsingJavaText(StringUtils.trim(out[32])));
            model.setInternetspeed(Integer.parseInt(StringUtils.trim(out[33])));
            model.setPromspeed(Integer.parseInt(StringUtils.trim(out[34])));
            model.setPeriodpromspeed(Integer.parseInt(StringUtils.trim(out[35])));
            model.setInternettech(normalizeUsingJavaText(StringUtils.trim(out[36])));
            model.setTvsignal(normalizeUsingJavaText(StringUtils.trim(out[37])));
            model.setTvtech(normalizeUsingJavaText(StringUtils.trim(out[38])));
            model.setEquiplinea(normalizeUsingJavaText(StringUtils.trim(out[39])));
            model.setEquipinternet(normalizeUsingJavaText(StringUtils.trim(out[40])));
            model.setEquiptv(normalizeUsingJavaText(StringUtils.trim(out[41])));
            model.setOrigintvtechnology(normalizeUsingJavaText(StringUtils.trim(out[42])));
            model.setTypetypedecoorigin(normalizeUsingJavaText(StringUtils.trim(out[43])));
            model.setTypedecoorigin(normalizeUsingJavaText(StringUtils.trim(out[44])));
            model.setQuantitytypedeco(normalizeUsingJavaText(StringUtils.trim(out[45])));
            model.setInternettechnologyorigin(normalizeUsingJavaText(StringUtils.trim(out[46])));
            model.setInternetequipmentorigin(normalizeUsingJavaText(StringUtils.trim(out[47])));
            model.setComfortcomfortrepeaterequipment(normalizeUsingJavaText(StringUtils.trim(out[48])));
            model.setComfortrepeater(normalizeUsingJavaText(StringUtils.trim(out[49])));
            model.setVoicetechnology(normalizeUsingJavaText(StringUtils.trim(out[50])));
            model.setMigrationlogicfromtdmtovoip(normalizeUsingJavaText(StringUtils.trim(out[51])));
            model.setMigratevoiptovoip(normalizeUsingJavaText(StringUtils.trim(out[52])));
            model.setHfctoftthmigrationlogic(normalizeUsingJavaText(StringUtils.trim(out[53])));
            model.setHerramienta(fileNameTxt);
            model.setLinearegistro(count);
            model.setPrefijo(prefix);
            outsCatalog.add(model);
        }
        in.close();
        fstream.close();

        InventResult resultCatalog = tdpCatalogDao.cartillaProudctosMT(outsCatalog, fileNameTxt, prefix);
        ProcessCatalogAditional(outsCatalog, fileNameTxt, prefix);

        resultCatalog.setSyntaxErrors(syntaxErrors);
        resultCatalog.setErrorCount(resultCatalog.getErrorCount() + syntaxErrors.size());

        String resultados = String.format("Total registros leidos: %s, insertados: %s, actualizados: %s,actualizados order: %s, errores: %s",
                outsCatalog.size(), resultCatalog.getInsertCount(), resultCatalog.getUpdateCount(), resultCatalog.getOrderCount(), resultCatalog.getErrorCount());


        logger.info(resultados);

        ServiceCallEvent datos;

        for (InventSyntaxError error : syntaxErrors) {
            String response = error.getLine() + " : " + error.getCause();
            datos = cargarDatosServiceCallEvent(Constants.RESULT_ERROR, Constants.SERVICE_CODE, fileNameTxt, response);
            serviceCallEventsDao.registerEvent(datos);
        }

        datos = cargarDatosServiceCallEvent(Constants.RESULT_OK, Constants.SERVICE_CODE, fileNameTxt, resultados);
        serviceCallEventsDao.registerEvent(datos);

        logger.info("fin cartilla productos.processCartillaFile .....................");
    }

    /*guardado en tdp_catalog_aditional*/
    public void ProcessCatalogAditional(List<TdpCatalogData> outsCatalog, String fileNameTxt, String prefix) throws IOException, Exception {
        logger.info("PuntosVenta.ProcessCatalogAditional .........");

        List<String> listCaneles = new ArrayList<>();
        List<String> listEntidades = new ArrayList<>();
        List<String> listCampanas = new ArrayList<>();
        List<String> listProvincias = new ArrayList<>();
        List<String> listDistrito = new ArrayList<>();


        for (TdpCatalogData invent : outsCatalog) {

            List<String> ubicaciones = new ArrayList<>();
            String caneles = invent.getCanal().replace("ñ", "n");
            String entidades = invent.getEntidad().replace("ñ", "n");
            String campanas = invent.getCampaign().replace("ñ", "n");
            String provincias = invent.getProvincia().replace("ñ", "n");
            String distrito = invent.getDistrito().replace("ñ", "n");

            listCaneles = Arrays.asList(caneles.split("-"));
            listEntidades = Arrays.asList(entidades.split("-"));
            listCampanas = Arrays.asList(campanas.split("-"));
            listProvincias = Arrays.asList(provincias.split("-"));
            listDistrito = Arrays.asList(distrito.split("-"));

            int indiceUbicacion = 0;
            for (String prov: listProvincias) {
                if (distrito.equals("-")){
                    ubicaciones.add(prov + "- todo");
                }else{
                    if(listDistrito.get(indiceUbicacion) !=null
                    && !listDistrito.get(indiceUbicacion).isEmpty()){
                        List<String> distritosPorProvincia = Arrays.asList(listDistrito.get(indiceUbicacion).split("-"));
                        indiceUbicacion++;
                        for (String distritoPorProvincia: distritosPorProvincia ) {
                            ubicaciones.add(prov.trim() + "-" + distritoPorProvincia.trim());
                        }
                    }
                }
            }
            addAditionalData(invent, "6527", listCaneles, fileNameTxt, prefix);
            addAditionalData(invent, "6528", listEntidades, fileNameTxt, prefix);
            addAditionalData(invent, "6529", ubicaciones, fileNameTxt, prefix);
            addAditionalData(invent, "6530", listCampanas, fileNameTxt, prefix);

        }
        tdpCatalogAditionalDao.CatalogAditional(outsCatalogAditional, fileNameTxt, prefix);
    }

    /*armado de objeto para tdp_catalog_aditional*/
    private void addAditionalData(TdpCatalogData invent, String additionalCode, List<String> lista, String fileNameTxt, String prefix) {

        for (String obj:lista) {
            TdpCatalogAditionalData model = new TdpCatalogAditionalData();
            model.setProductId(invent.getLinearegistro());
            model.setParameterId(Integer.parseInt(additionalCode));
            model.setValue(obj.trim().toUpperCase());
            model.setHerramienta(fileNameTxt);
            model.setPrefijo(prefix);
            outsCatalogAditional.add(model);
        }
    }

    /*limpieza de lineas de archivos*/
    private static String normalizeUsingJavaText(String source){
        source = Normalizer.normalize(source, Normalizer.Form.NFD);
        return source.replaceAll("[^\\p{ASCII}]", "");
    }

    /*guardado en service_call_event*/
    private static ServiceCallEvent cargarDatosServiceCallEvent(String msg, String code, String req, String res) {
        ServiceCallEvent dataCallEvent = new ServiceCallEvent();

        dataCallEvent.setUsername(Constants.USERNAME);
        dataCallEvent.setMsg(msg);
        dataCallEvent.setOrderId(StringUtils.EMPTY);
        dataCallEvent.setDocNumber(StringUtils.EMPTY);
        dataCallEvent.setServiceCode(code);
        dataCallEvent.setServiceUrl(StringUtils.EMPTY);
        dataCallEvent.setServiceRequest(req);
        dataCallEvent.setServiceResponse(res);
        dataCallEvent.setSourceApp(Constants.SOURCE_APP);
        dataCallEvent.setSourceAppVersion(Constants.SOURCE_APP_VERSION);
        dataCallEvent.setResult(msg);
        return dataCallEvent;
    }

    /*validacion de nombre de archivo*/
    private static boolean processNameFile(String nameFile, String prefix) {
        boolean valor = false;
        String[] b = nameFile.split("_");
        int cant = b.length - 1;
        int e = 0;
        while (e <= cant) {
            if (b[e].equalsIgnoreCase(prefix)) {
                valor = true;
            }
            e++;
        }
        return valor;
    }
}
