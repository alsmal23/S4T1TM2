package edu.csus.yaam.server.db;

import com.timvisee.yamlwrapper.YamlConfiguration;
import edu.csus.yaam.server.util.IOUtils;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;
import lombok.extern.log4j.Log4j2;

/**
 * @author Ryan R
 * @date 4/1/2018
 */
@Log4j2
@RequiredArgsConstructor
public class SQLiteDatabase {
    @NonNull
    private final File file;

    /**
     * Since SQLite is local, there is no benefit in connection pooling
     */
    @Getter
    @Accessors(fluent = true)
    @Delegate(types = Delegates.class)
    private Connection connection;


    // method delegates

    private interface Delegates {
        Statement createStatement() throws SQLException;
        PreparedStatement prepareStatement(String sql) throws SQLException;
    }


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
                log.error("error executing database statement: /sqlite/schema/" + fileName, throwable);
            }
        }
        statement.closeOnCompletion();
    }


    // (hacky) execution

    @SneakyThrows
    public void executeSync(@NonNull ConnectionConsumer consumer) {
        consumer.accept(connection);
    }

    @SneakyThrows
    public <T> T executeSync(@NonNull ConnectionFunction<T> consumer) {
        return consumer.accept(connection);
    }


    @FunctionalInterface
    public interface ConnectionFunction<T> {
        T accept(Connection connection) throws Throwable;
    }

    @FunctionalInterface
    public interface ConnectionConsumer {
        void accept(Connection connection) throws Throwable;
    }
}