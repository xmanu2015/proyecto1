package pe.com.tdp.ventafija.microservices.repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.com.tdp.ventafija.microservices.common.connection.Database;
import pe.com.tdp.ventafija.microservices.common.util.exception.Exception;
import pe.com.tdp.ventafija.microservices.domain.TdpCatalogData;
import pe.com.tdp.ventafija.microservices.domain.dto.InventResult;
import pe.com.tdp.ventafija.microservices.domain.dto.ServiceCallEvent;
import pe.com.tdp.ventafija.microservices.service.AzureService;
import pe.com.tdp.ventafija.microservices.service.CartillaProductoServiceImpl;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Repository
public class TdpCatalogDao {
    private static final Logger logger = LogManager.getLogger();

    public InventResult cartillaProudctosMT(List<TdpCatalogData> invents, String fileNameTxt, String prefix) throws Exception {
        logger.info("iniciando registro en tdp_catalog para Movistar Total");

        int totalInsert = 0;
        int totalUpdate = 0;
        int totalError = 0;
        int totalOrderUpdate = 0;

        try (Connection con = Database.datasource().getConnection()) {
            String delete = "DELETE FROM ibmx_a07e6d02edaf552.tdp_catalog_2 WHERE prefijo = ?";
            PreparedStatement psDelete = con.prepareStatement(delete);
            psDelete.setString(1,prefix);
            int deletedRows = psDelete.executeUpdate();
            logger.info("Cantidad de rows eliminados del archivo " + fileNameTxt + ": " + deletedRows);

            String insert = " insert into ibmx_a07e6d02edaf552.tdp_catalog_2 " +
                    "(commercialoperation," +
                    "segmento," +
                    "canal," +
                    "entidad," +
                    "provincia," +
                    "distrito," +
                    "campaign," +
                    "campaingcode," +
                    "priority," +
                    "prodtypecode," +
                    "producttype," +
                    "prodcategorycode," +
                    "productcategory," +
                    "productcode," +
                    "productname," +
                    "bloquetv," +
                    "svainternet," +
                    "svalinea," +
                    "tiporegistro," +
                    "discount," +
                    "price," +
                    "promprice," +
                    "monthperiod," +
                    "installcost," +
                    "linetype," +
                    "paymentmethod," +
                    "cashprice," +
                    "financingcost," +
                    "financingmonth," +
                    "equipmenttype," +
                    "returnmonth," +
                    "returnperiod," +
                    "internetspeed," +
                    "promspeed," +
                    "periodpromspeed," +
                    "internettech," +
                    "tvsignal," +
                    "tvtech," +
                    "equiplinea," +
                    "equipinternet," +
                    "equiptv," +
                    "product_codigo," +
                    "origintvtechnology," +
                    "typetypedecoorigin," +
                    "typedecoorigin," +
                    "quantitytypedeco," +
                    "internettechnologyorigin," +
                    "internetequipmentorigin," +
                    "comfortcomfortrepeaterequipment," +
                    "comfortrepeater," +
                    "voicetechnology," +
                    "migrationlogicfromtdmtovoip," +
                    "migratevoiptovoip," +
                    "hfctoftthmigrationlogic," +
                    "herramienta," +
                    "linearegistro," +
                    "prefijo)" +
                    " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement psInsert = con.prepareStatement(insert);
            logger.info("numero de registros a procesar: "+ invents.size());

            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Lima"));
            Date fechaActual = cal.getTime();
            Timestamp fechaActualTimestamp = new Timestamp(fechaActual.getTime());

            int countBatch=0;

            for (TdpCatalogData invent : invents) {

                psInsert.setString(1, invent.getCommercialoperation());
                psInsert.setString(2, invent.getSegmento());
                psInsert.setString(3, invent.getCanal());
                psInsert.setString(4, invent.getEntidad());
                psInsert.setString(5, invent.getProvincia());
                psInsert.setString(6, invent.getDistrito());
                psInsert.setString(7, invent.getCampaign());
                psInsert.setString(8, invent.getCampaingcode());
                psInsert.setInt(9, invent.getPriority());
                psInsert.setString(10, invent.getProdtypecode());
                psInsert.setString(11, invent.getProdcategorycode());
                psInsert.setString(12, invent.getProducttype());
                psInsert.setString(13, invent.getProductcategory());
                psInsert.setString(14, invent.getProductcode());
                psInsert.setString(15, invent.getProductname());
                psInsert.setString(16, invent.getBloquetv());
                psInsert.setString(17, invent.getSvainternet());
                psInsert.setString(18, invent.getSvalinea());
                psInsert.setString(19, invent.getTiporegistro());
                psInsert.setString(20, invent.getDiscount());
                psInsert.setDouble(21, invent.getPrice());
                psInsert.setDouble(22, invent.getPromprice());
                psInsert.setInt(23, invent.getMonthperiod());
                psInsert.setDouble(24, invent.getInstallcost());
                psInsert.setString(25, invent.getLinetype());
                psInsert.setString(26, invent.getPaymentmethod());
                psInsert.setDouble(27, invent.getCashprice());
                psInsert.setDouble(28, invent.getFinancingcost());
                psInsert.setInt(29, invent.getFinancingmonth());
                psInsert.setString(30, invent.getEquipmenttype());
                psInsert.setInt(31, invent.getReturnmonth());
                psInsert.setString(32, invent.getReturnperiod());
                psInsert.setInt(33, invent.getInternetspeed());
                psInsert.setInt(34, invent.getPromspeed());
                psInsert.setInt(35, invent.getPeriodpromspeed());
                psInsert.setString(36, invent.getInternettech());
                psInsert.setString(37, invent.getTvsignal());
                psInsert.setString(38, invent.getTvtech());
                psInsert.setString(39, invent.getEquiplinea());
                psInsert.setString(40, invent.getEquipinternet());
                psInsert.setString(41, invent.getEquiptv());
                psInsert.setString(42, invent.getCodigoproducto());
                psInsert.setString(43, invent.getOrigintvtechnology());
                psInsert.setString(44, invent.getTypetypedecoorigin());
                psInsert.setString(45, invent.getTypedecoorigin());
                psInsert.setString(46, invent.getQuantitytypedeco());
                psInsert.setString(47, invent.getInternettechnologyorigin());
                psInsert.setString(48, invent.getInternetequipmentorigin());
                psInsert.setString(49, invent.getComfortcomfortrepeaterequipment());
                psInsert.setString(50, invent.getComfortrepeater());
                psInsert.setString(51, invent.getVoicetechnology());
                psInsert.setString(52, invent.getMigrationlogicfromtdmtovoip());
                psInsert.setString(53, invent.getMigratevoiptovoip());
                psInsert.setString(54, invent.getHfctoftthmigrationlogic());
                psInsert.setString(55, invent.getHerramienta());
                psInsert.setInt(56, invent.getLinearegistro());
                psInsert.setString(57, invent.getPrefijo());
                psInsert.addBatch();

                countBatch++;

                if (countBatch == 5000) {
                    int[] result = psInsert.executeBatch();
                    totalInsert = processInsertCount(totalInsert, result);
                    psInsert.clearBatch();
                    countBatch = 0;
                }
            }

            if (countBatch > 0) {
                int[] result = psInsert.executeBatch();
                totalInsert = processInsertCount(totalInsert, result);
            }
        } catch (SQLException e) {
            logger.error("error en conexion: " + e.getMessage());
            throw new Exception(e);
        }

        logger.info("finalizados registro en tdp_catalog");
        return new InventResult(totalInsert, totalUpdate, totalError, totalOrderUpdate/*, response, errors*/);
    }

    private int processInsertCount(int currentCount, int[] insertResults) {
        int count = currentCount;
        for (int insertResult : insertResults) {
            switch (insertResult) {
                case Statement.SUCCESS_NO_INFO:
                    count += 1;
                    break;
                case Statement.EXECUTE_FAILED:
                    break;
                default:
                    count += insertResult;
                    break;
            }
        }
        return count;
    }
}
