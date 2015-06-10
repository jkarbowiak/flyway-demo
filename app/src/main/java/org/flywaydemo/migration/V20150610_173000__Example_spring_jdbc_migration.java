package org.flywaydemo.migration;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;

public class V20150610_173000__Example_spring_jdbc_migration implements JdbcMigration {

    @Override
    public void migrate(Connection connection) throws Exception {

    }
}
