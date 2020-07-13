package pe.com.tdp.ventafija.microservices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import pe.com.tdp.ventafija.microservices.common.connection.Database;

import pe.com.tdp.ventafija.microservices.common.connection.DatabasePropertiesSource;
import pe.com.tdp.ventafija.microservices.common.context.VentaFijaContextPersistenceFilter;

import javax.servlet.Filter;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
@EnableScheduling
public class CartillaProductosTdpApplication {
    private static final Logger logger = LogManager.getLogger();
    private static final String EQUIPMENT_STOREHOUSE_SERVICE_CODE = "EquipmentStorehouse";

    @Bean
    public Filter log4jMvcFilter() {
        return new Log4JMvcFilter();
    }

    @Bean
    public Filter ventaFijaContextPersistenceFilter() {
        return new VentaFijaContextPersistenceFilter(EQUIPMENT_STOREHOUSE_SERVICE_CODE);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        final PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
        DatabasePropertiesSource propertiesSource = new DatabasePropertiesSource(Database.datasource());
        Map<String, Object> props = propertiesSource.loadProperties();
        pspc.setIgnoreResourceNotFound(true);
        pspc.setIgnoreUnresolvablePlaceholders(true);
        Properties propps = new Properties();
        propps.putAll(props);
        pspc.setProperties(propps);
        return pspc;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        return executor;
    }

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(log4jMvcFilter());
        registration.addUrlPatterns("*");
        registration.setName("log4jMvcFilter");
        registration.setOrder(1);
        return registration;
    }

    public static void main(String[] args) {
        SpringApplication.run(CartillaProductosTdpApplication.class, args);
        logger.info("CartillaProductosTdpApplication 1.0.0 is running :)");
    }

}