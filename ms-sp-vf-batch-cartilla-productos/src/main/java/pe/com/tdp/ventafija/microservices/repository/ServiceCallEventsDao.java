package pe.com.tdp.ventafija.microservices.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import pe.com.tdp.ventafija.microservices.common.connection.Database;
import pe.com.tdp.ventafija.microservices.domain.dto.ServiceCallEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class ServiceCallEventsDao {
    private static final Logger logger = LogManager.getLogger();

    public void registerEvent(ServiceCallEvent event) {

        try (Connection con = Database.datasource().getConnection()) {
            String insert = "INSERT INTO ibmx_a07e6d02edaf552.service_call_events (event_datetime, tag, username, msg,orderId, docNumber, serviceCode, serviceUrl, serviceRequest, serviceResponse, sourceApp,sourceAppVersion, result) VALUES (CURRENT_TIMESTAMP,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement psInsert = con.prepareStatement(insert);

            psInsert.setString(1, "CARTILLA");
            psInsert.setString(2, event.getUsername());
            psInsert.setString(3, event.getMsg());
            psInsert.setString(4, event.getOrderId());
            psInsert.setString(5, event.getDocNumber());
            psInsert.setString(6, event.getServiceCode());
            psInsert.setString(7, event.getServiceUrl());
            psInsert.setString(8, event.getServiceRequest());
            psInsert.setString(9, event.getServiceResponse());
            psInsert.setString(10, event.getSourceApp());
            psInsert.setString(11, event.getSourceAppVersion());
            psInsert.setString(12, event.getResult());
            psInsert.addBatch();
            psInsert.executeBatch();
            this.closeSession(con, psInsert);

        } catch (Exception var8) {
            this.logger.error("Error al registrar evento de llamada", var8);
        }


    }

    private void closeSession(Connection con, PreparedStatement ps) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException var5) {
                this.logger.error("Error al registrar evento al cerrar conexion", var5);
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException var4) {
                this.logger.error("Error al registrar evento al cerrar conexion", var4);
            }
        }
    }
}
