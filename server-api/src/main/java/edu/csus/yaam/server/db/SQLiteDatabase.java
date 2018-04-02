package edu.csus.yaam.server.db;

import com.timvisee.yamlwrapper.YamlConfiguration;
import edu.csus.yaam.server.util.IOUtils;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * @author Ryan R
 * @date 4/1/2018
 */
@RequiredArgsConstructor
public class SQLiteDatabase {
    @NonNull
    private final File file;

    /**
     * Since SQLite is local, there is no benefit in connection pooling
     */
    @Getter
    private Connection connection;


    // initialization

    /**
     * Establishes SQLite connection
     */
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());
        } catch (SQLException exception) {
            throw new RuntimeException("failed to load SQLite database from file: " + file.getAbsolutePath());
        }
    }

    /**
     * Runs schema initialization scripts, as defined in /sqlite/schema/database.yml
     */
    @SneakyThrows
    public void initialize() {
        YamlConfiguration databaseSettings = new YamlConfiguration();
        databaseSettings.loadFromString(IOUtils.resourceToString("/sqlite/schema/database.yml"));

        Statement statement = connection.createStatement();
        // update user_version
        statement.execute("PRAGMA `user_version`=" + databaseSettings.getInt("userVersion"));
        // execute initialization scripts
        for (String fileName : (List<String>) databaseSettings.getList("statements")) {
            try {
                statement.execute(IOUtils.resourceToString("/sqlite/schema/" + fileName));
            } catch (Throwable throwable) {
                throw new RuntimeException("error executing database statement: /sqlite/schema/" + fileName, throwable);
            }
        }
        statement.closeOnCompletion();
    }
}