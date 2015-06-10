package org.flywaydemo.migration;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;

public class V20150610_171500__Example_jdbc_migration implements JdbcMigration {

    @Override
    public void migrate(Connection connection) throws Exception {

    }
}
