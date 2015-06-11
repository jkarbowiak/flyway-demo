package org.flywaydemo.migration;

import org.flywaydb.core.api.migration.jdbc.JdbcMigration;
import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class V20150611_200000__Generate_emails implements SpringJdbcMigration {

    private static final Random RANDOM = new Random();

    private static final String UPDATE_PATTERN = "UPDATE APP_USER SET EMAIL=''{0}'' WHERE ID={1}";

    @Override
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
        List<Long> userIds = getUserIds(jdbcTemplate);
        List<String> statements = createUpdateEmailStatements(userIds);
        String[] statementsArray = statements.toArray(new String[statements.size()]);
        jdbcTemplate.batchUpdate(statementsArray);
    }

    private List<Long> getUserIds(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForList("SELECT ID from APP_USER", Long.class);
    }

    private List<String> createUpdateEmailStatements(List<Long> userIds) {
        List<String> statements = new ArrayList<>(userIds.size());
        for (Long userId : userIds) {
            String email = createUserEmail(userId);
            statements.add(MessageFormat.format(UPDATE_PATTERN, email, userId));
        }
        return statements;
    }

    private String createUserEmail(Long userId) {
        return userId + "-" + RANDOM.nextLong() + "@email.com";
    }
}
