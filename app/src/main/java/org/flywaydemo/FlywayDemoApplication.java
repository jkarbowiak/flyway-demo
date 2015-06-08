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
        dataSource.setUsername(flywayProperties.getUser());
        dataSource.setPassword(flywayProperties.getPassword());
        dataSource.setUrl(flywayProperties.getUrl());
        dataSource.setDriverClass(OracleDriver.class);
        return dataSource;
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource, FlywayProperties properties) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        return flyway;
    }

    @Configuration
    @PropertySource(name="flywayProperties", value = "classpath:flyway.properties")
    public static class FlywayProperties {

        @Autowired
        private Environment env;

        public String getUser() {
            return env.getProperty("flyway.user");
        }

        public String getPassword() {
            return env.getProperty("flyway.password");
        }

        public String getUrl() {
            return env.getProperty("flyway.url");
        }
    }

}
