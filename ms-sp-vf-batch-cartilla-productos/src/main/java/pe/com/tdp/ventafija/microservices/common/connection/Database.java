package pe.com.tdp.ventafija.microservices.common.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

@Configuration
public class Database {
    private static final Logger logger = LogManager.getLogger();
    private static HikariDataSource datasource;
    private static HikariDataSource datasourceMT;

    static Properties prop = new Properties();
    static Connection con;

    static boolean production = false;

    @Primary
    @Bean(name = "dataSource", destroyMethod = "close")
    public static DataSource datasource() {

        if (datasource == null) {
            HikariConfig config = new HikariConfig();

            config.setDriverClassName(System.getenv("TDP_FIJA_DB_DRIVER"));
            config.setJdbcUrl(System.getenv("TDP_FIJA_DB_URL"));
            config.setUsername(System.getenv("TDP_FIJA_DB_USR"));
            config.setPassword(System.getenv("TDP_FIJA_DB_PW"));

            if (production) {
                config.setJdbcUrl("jdbc:postgresql://sl-us-south-1-portal.13.dblayer.com:28973/compose?useSSL=false");
                config.setPassword("IXECEUBFNJLAZZEV");
            }

            config.addDataSourceProperty("ApplicationName", "Equipment-Storehouse-Batch");
            config.setMinimumIdle(Integer.parseInt(System.getenv("TDP_FIJA_DB_MINIMUM_IDLE")));
            config.setMaximumPoolSize(Integer.parseInt(System.getenv("TDP_FIJA_DB_POOLING")));
            config.setIdleTimeout(Integer.parseInt(System.getenv("TDP_FIJA_DB_TIMEOUT_IDLE")));
            config.setMaxLifetime(Integer.parseInt(System.getenv("TDP_FIJA_DB_MAX_LIFETIME")));

            datasource = new HikariDataSource(config);
        }
        return datasource;
    }

//    public static DataSource datasourceMT() {
//        if (datasourceMT == null) {
//            HikariConfig config = new HikariConfig();
//
//            config.setDriverClassName(System.getenv("TDP_FIJA_DB_DRIVER"));
//            config.setJdbcUrl(System.getenv("TDP_MT_DB_URL"));
//            config.setUsername(System.getenv("TDP_FIJA_DB_USR"));
//            config.setPassword(System.getenv("TDP_MT_DB_PW"));
//
//            if (production) {
//                config.setJdbcUrl("jdbc:postgresql://sl-us-south-1-portal.53.dblayer.com:15311/compose?useSSL=false");
//                config.setPassword("GNGMMAMGWYKNEGZQ");
//            }
//
//            config.addDataSourceProperty("ApplicationName", "Equipment-Storehouse-Batch");
//            config.setMinimumIdle(Integer.parseInt(System.getenv("TDP_FIJA_DB_MINIMUM_IDLE")));
//            config.setMaximumPoolSize(Integer.parseInt(System.getenv("TDP_FIJA_DB_POOLING")));
//            config.setIdleTimeout(Integer.parseInt(System.getenv("TDP_FIJA_DB_TIMEOUT_IDLE")));
//            config.setMaxLifetime(Integer.parseInt(System.getenv("TDP_FIJA_DB_MAX_LIFETIME")));
//
//            datasourceMT = new HikariDataSource(config);
//        }
//        return datasourceMT;
//    }

}
