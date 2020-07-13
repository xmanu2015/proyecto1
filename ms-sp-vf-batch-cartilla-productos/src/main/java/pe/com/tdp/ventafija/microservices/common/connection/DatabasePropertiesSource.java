package pe.com.tdp.ventafija.microservices.common.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabasePropertiesSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabasePropertiesSource.class);
    private final DataSource datasource;

    public DatabasePropertiesSource(DataSource datasource) {
        this.datasource = datasource;
    }

    public Map<String, Object> loadProperties() {
        Map<String, Object> props = new HashMap<>();
        try (Connection con = datasource.getConnection()) {
            //sprint 3 - se adiciona nombre de esquema para la consulta en la tabla parameters
            String query = "select element, strValue from ibmx_a07e6d02edaf552.parameters where domain = 'CONFIG' and category = 'PARAMETER'";
            LOGGER.debug("query: " + query);
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String key = rs.getString(1);
                        String value = rs.getString(2);
                        props.put(key, value);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("waa :/", e);
        }
        return props;
    }

}
