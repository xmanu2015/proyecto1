package pe.com.tdp.ventafija.microservices.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import pe.com.tdp.ventafija.microservices.common.connection.Database;
import pe.com.tdp.ventafija.microservices.common.util.exception.Exception;
import pe.com.tdp.ventafija.microservices.domain.TdpCatalogAditionalData;
import pe.com.tdp.ventafija.microservices.domain.dto.InventResult;

import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Repository
public class TdpCatalogAditionalDao {
    private static final Logger logger = LogManager.getLogger();

    public InventResult CatalogAditional(List<TdpCatalogAditionalData> invents, String fileNameTxt) throws Exception {
        logger.info("iniciando registro en tdp_catalog_aditional para Movistar Total");

        int totalInsert = 0;
        int totalUpdate = 0;
        int totalError = 0;
        int totalOrderUpdate = 0;

        try (Connection con = Database.datasource().getConnection()) {
            String delete = " DELETE FROM ibmx_a07e6d02edaf552.tdp_catalog_aditional_2";
            PreparedStatement psDelete = con.prepareStatement(delete);
            int deletedRows = psDelete.executeUpdate();
            logger.info("Cantidad de rows eliminados del archivo " + fileNameTxt + ": " + deletedRows);

            String insert = " insert into ibmx_a07e6d02edaf552.tdp_catalog_aditional_2 " +
                    " (product_id," +
                    "parameter_id," +
                    "value," +
                    "herramienta," +
                    " values (?,?,?,?)";

            PreparedStatement psInsert = con.prepareStatement(insert);
            logger.info("numero de registros a procesar: "+ invents.size());

            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("America/Lima"));
            Date fechaActual = cal.getTime();
            Timestamp fechaActualTimestamp = new Timestamp(fechaActual.getTime());

            int countBatch=0;

            for (TdpCatalogAditionalData invent : invents) {

                psInsert.setString(1, invent.getProductId());
                psInsert.setString(2, invent.getParameterId());
                psInsert.setString(3, invent.getValue());
                psInsert.setString(3, invent.getHerramienta());
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

        logger.info("finalizados registro en tdp_catalog_aditional");
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
