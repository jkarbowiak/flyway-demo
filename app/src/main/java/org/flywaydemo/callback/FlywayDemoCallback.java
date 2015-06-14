package org.flywaydemo.callback;

import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.callback.FlywayCallback;

import java.sql.Connection;

public class FlywayDemoCallback implements FlywayCallback {

    @Override
    public void beforeClean(Connection connection) {

    }

    @Override
    public void afterClean(Connection connection) {

    }

    @Override
    public void beforeMigrate(Connection connection) {
        System.out.println("Migrating will start soon.");
    }

    @Override
    public void afterMigrate(Connection connection) {

    }

    @Override
    public void beforeEachMigrate(Connection connection, MigrationInfo info) {

    }

    @Override
    public void afterEachMigrate(Connection connection, MigrationInfo info) {

    }

    @Override
    public void beforeValidate(Connection connection) {

    }

    @Override
    public void afterValidate(Connection connection) {

    }

    @Override
    public void beforeBaseline(Connection connection) {

    }

    @Override
    public void afterBaseline(Connection connection) {

    }

    @Override
    public void beforeInit(Connection connection) {

    }

    @Override
    public void afterInit(Connection connection) {

    }

    @Override
    public void beforeRepair(Connection connection) {

    }

    @Override
    public void afterRepair(Connection connection) {

    }

    @Override
    public void beforeInfo(Connection connection) {

    }

    @Override
    public void afterInfo(Connection connection) {

    }
}
