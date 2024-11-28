package com.pentalog.KitKat.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@Component
public class DataSeeder implements CommandLineRunner {
    private final DataSource dataSource;

    public DataSeeder(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            // Check and insert data for each table
            if (isTableEmpty(connection, "t_positions")) {
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("insert_positions.sql"));
            }
            if (isTableEmpty(connection, "t_seniority")) {
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("insert_seniority.sql"));
            }
            if (isTableEmpty(connection, "t_countries")) {
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("insert_countries.sql"));
            }
            if (isTableEmpty(connection, "t_cities")) {
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("insert_cities.sql"));
            }
            if (isTableEmpty(connection, "t_languages")) {
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("insert_languages.sql"));
            }
            if (isTableEmpty(connection, "t_roles")) {
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("insert_roles.sql"));
            }
            if (isTableEmpty(connection, "t_statuses")) {
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("insert_statuses.sql"));
            }
            if (isTableEmpty(connection, "t_skill_types")) {
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("insert_skill_types.sql"));
            }
            if (isTableEmpty(connection, "t_skills")) {
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("insert_skills.sql"));
            }
            if (isTableEmpty(connection, "t_users")) {
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("insert_users.sql"));
            }
            if (isTableEmpty(connection, "t_projects")) {
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("insert_projects.sql"));
            }
            if (isTableEmpty(connection, "t_user_languages")) {
                ScriptUtils.executeSqlScript(connection, new ClassPathResource("insert_user_languages.sql"));
            }
        }
    }

    // Method to check if a table is empty
    private boolean isTableEmpty(Connection connection, String tableName) throws Exception {
        String checkTableQuery = "SELECT COUNT(*) FROM " + tableName;  // Count rows in the table
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(checkTableQuery)) {

            if (resultSet.next()) {
                return resultSet.getInt(1) == 0;  // Return true if the table is empty
            }
        }
        return false;  // Default to false if there's an issue (e.g., table doesn't exist)
    }
}
