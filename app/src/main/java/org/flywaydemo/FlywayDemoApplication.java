package org.flywaydemo;

import oracle.jdbc.driver.OracleDriver;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@SpringBootApplication
@ConfigurationProperties
public class FlywayDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlywayDemoApplication.class, args);
    }

    @Bean
    public DataSource dataSource(FlywayProperties flywayProperties) {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setUsername(flywayProperties.get("flyway.user"));
        dataSource.setPassword(flywayProperties.get("flyway.password"));
        dataSource.setUrl(flywayProperties.get("flyway.url"));
        dataSource.setDriverClass(OracleDriver.class);
        return dataSource;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource, FlywayProperties flywayProperties) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setLocations(flywayProperties.get("flyway.locations").split(","));
        flyway.setCallbacksAsClassNames(flywayProperties.get("flyway.callbacks"));
        return flyway;
    }

    @Configuration
    @PropertySource(name="flywayProperties", value = "file:c:/projects/flyway-demo/workspace/app/flyway.properties")
    public static class FlywayProperties {

        @Autowired
        private Environment env;

        public String get(String key) {
            return env.getProperty(key);
        }
    }

}
