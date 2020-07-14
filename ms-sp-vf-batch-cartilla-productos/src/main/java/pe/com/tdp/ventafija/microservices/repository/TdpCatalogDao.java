package pe.com.tdp.ventafija.microservices.repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import pe.com.tdp.ventafija.microservices.common.connection.Database;
import pe.com.tdp.ventafija.microservices.common.util.exception.Exception;
import pe.com.tdp.ventafija.microservices.domain.TdpCatalogData;
import pe.com.tdp.ventafija.microservices.domain.dto.InventResult;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Repository
public class TdpCatalogDao {
    private static final Logger logger = LogManager.getLogger();

    public InventResult cartillaProudctosMT(List<TdpCatalogData> invents, String fileNameTxt) throws Exception {
        logger.info("iniciando registro en tdp_catalog para Movistar Total");

        int totalInsert = 0;
        int totalUpdate = 0;
        int totalError = 0;
        int totalOrderUpdate = 0;

        try (Connection con = Database.datasource().getConnection()) {
            String delete = " DELETE FROM ibmx_a07e6d02edaf552.tdp_catalog_2";
            PreparedStatement psDelete = con.prepareStatement(delete);
            int deletedRows = psDelete.executeUpdate();
            logger.info("Cantidad de rows eliminados del archivo " + fileNameTxt + ": " + deletedRows);

            String insert = " insert into ibmx_a07e6d02edaf552.tdp_catalog_2 " +
                    " (codigoproducto," +
                    "commercialoperation," +
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
                    "herramienta)" +
                    " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement psInsert = con.prepareStatement(insert);
            logger.info("numero de registros a procesar: "+ invents.size());

            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Lima"));
            Date fechaActual = cal.getTime();
            Timestamp fechaActualTimestamp = new Timestamp(fechaActual.getTime());

            int countBatch=0;

            for (TdpCatalogData invent : invents) {

                psInsert.setString(1, invent.getCodigoproducto());
                psInsert.setString(2, invent.getCommercialoperation());
                psInsert.setString(3, invent.getSegmento());
                psInsert.setString(4, invent.getCanal());
                psInsert.setString(5, invent.getEntidad());
                psInsert.setString(6, invent.getProvincia());
                psInsert.setString(7, invent.getDistrito());
                psInsert.setString(8, invent.getCampaign());
                psInsert.setString(9, invent.getCampaingcode());
                psInsert.setString(10, invent.getPriority());
                psInsert.setString(11, invent.getProdtypecode());
                psInsert.setString(12, invent.getProdcategorycode());
                psInsert.setString(13, invent.getProducttype());
                psInsert.setString(14, invent.getProductcategory());
                psInsert.setString(15, invent.getProductcode());
                psInsert.setString(16, invent.getProductname());
                psInsert.setString(17, invent.getBloquetv());
                psInsert.setString(18, invent.getSvainternet());
                psInsert.setString(19, invent.getSvalinea());
                psInsert.setString(20, invent.getTiporegistro());
                psInsert.setString(21, invent.getDiscount());
                psInsert.setString(22, invent.getPrice());
                psInsert.setString(23, invent.getPromprice());
                psInsert.setString(24, invent.getMonthperiod());
                psInsert.setString(25, invent.getInstallcost());
                psInsert.setString(26, invent.getLinetype());
                psInsert.setString(27, invent.getPaymentmethod());
                psInsert.setString(28, invent.getCashprice());
                psInsert.setString(29, invent.getFinancingcost());
                psInsert.setString(30, invent.getFinancingmonth());
                psInsert.setString(31, invent.getEquipmenttype());
                psInsert.setString(32, invent.getReturnmonth());
                psInsert.setString(33, invent.getReturnperiod());
                psInsert.setString(34, invent.getInternetspeed());
                psInsert.setString(35, invent.getPromspeed());
                psInsert.setString(36, invent.getPeriodpromspeed());
                psInsert.setString(37, invent.getInternettech());
                psInsert.setString(38, invent.getTvsignal());
                psInsert.setString(39, invent.getTvtech());
                psInsert.setString(40, invent.getEquiplinea());
                psInsert.setString(41, invent.getEquipinternet());
                psInsert.setString(42, invent.getEquiptv());
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
