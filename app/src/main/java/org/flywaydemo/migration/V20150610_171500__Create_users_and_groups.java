package org.flywaydemo.migration;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;

import java.sql.Connection;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class V20150610_171500__Create_users_and_groups implements JdbcMigration {

    private static final int USER_GROUP_COUNT = 10;
    private static final int USER_PRO_USER_GROUP_COUNT = 5;

    private static final String INSERT_USER_GROUP_PATTERN = "INSERT INTO APP_USER_GROUP(ID, NAME, REMARKS) VALUES ({0}, ''{1}'', ''{2}'')";
    private static final String INSERT_USER_PATTERN = "INSERT INTO APP_USER(ID, FIRST_NAME, LAST_NAME, EMAIL, USER_GROUP_ID) " +
            "VALUES ({0}, ''{1}'', ''{2}'', ''{3}'', {4})";

    private static final String USER_FIRST_NAME_PREFIX = "EXAMPLE_USER_FIRST_NAME_";
    private static final String USER_LAST_NAME_PREFIX = "EXAMPLE_USER_LAST_NAME_";
    private static final String USER_GROUP_NAME_PREFIX = "EXAMPLE_USER_GROUP_";
    private static final String REMARKS_FOR_USER_GROUP_PREFIX = "Remarks for user group ";
    private static final String DEFAULT_EMAIL = "n/a";

    @Override
    public void migrate(Connection connection) throws Exception {
        for (String command : createDataInsertCommands()) {
            try (Statement stmt = connection.createStatement()) {
                stmt.execute(command);
            }
        }
    }

    private List<String> createDataInsertCommands() {
        List<String> commands = new ArrayList<>();

        for (int userGroupId = 0; userGroupId < USER_GROUP_COUNT; userGroupId++) {
            commands.add(createInsertUserGroupStatement(userGroupId));

            for (int userIndexInGroup = 0; userIndexInGroup < USER_PRO_USER_GROUP_COUNT; userIndexInGroup++) {
                commands.add(createInsertUserStatement(userIndexInGroup, userGroupId));
            }
        }

        return commands;
    }

    private String createInsertUserGroupStatement(long id) {
        String userGroupName = USER_GROUP_NAME_PREFIX + id;
        String userGroupRemarks = REMARKS_FOR_USER_GROUP_PREFIX + id;
        return MessageFormat.format(INSERT_USER_GROUP_PATTERN, id, userGroupName, userGroupRemarks);
    }

    private String createInsertUserStatement(int userIndexInGroup, int userGroupId) {
        long userId = userIndexInGroup + userGroupId * USER_PRO_USER_GROUP_COUNT;
        String firstName = USER_FIRST_NAME_PREFIX + userGroupId + "_" + userIndexInGroup ;
        String lastName = USER_LAST_NAME_PREFIX + userGroupId + "_" + userIndexInGroup;
        return MessageFormat.format(INSERT_USER_PATTERN, userId, firstName, lastName, DEFAULT_EMAIL, userGroupId);
    }
}
