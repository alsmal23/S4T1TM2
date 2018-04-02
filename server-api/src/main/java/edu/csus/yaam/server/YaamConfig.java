package edu.csus.yaam.server;

import com.timvisee.yamlwrapper.ConfigurationSection;
import com.timvisee.yamlwrapper.YamlConfiguration;
import java.io.File;
import lombok.RequiredArgsConstructor;
import net.sourceforge.argparse4j.inf.Namespace;

/**
 * A rather unconventional approach and structure for holding a runtime configuration in memory
 *
 * @author Ryan R
 * @date 3/30/2018
 */
@RequiredArgsConstructor
public class YaamConfig {
    @RequiredArgsConstructor
    public static class WebAPIConfig {
        public final String host;
        public final int port;
    }

    public final WebAPIConfig webApi;

    @RequiredArgsConstructor
    public static class WebSocketConfig {
        public final String host;
        public final int port;
    }

    public final WebSocketConfig webSocket;

    public final File sqliteFile;

    public static YaamConfig load(Namespace namespace) {
        YamlConfiguration config = new YamlConfiguration();

        // load YAML configuration
        boolean noConfig = namespace.getBoolean("noConfig");
        File file = namespace.get("configFile");
        try {
            if (noConfig) {
                config.load(YaamConfig.class.getResourceAsStream("/yaam/defaultConfig.yml"));
            } else {
                config.load(namespace.<File>get("configFile"));
            }
        } catch (Throwable throwable) {
            throw new RuntimeException("failed to load configuration from file" + (!noConfig ? ": " + file.getAbsolutePath() : ""));
        }

        ConfigurationSection connectionSection = config.getConfigurationSection("connection");
        ConfigurationSection webAPISection = connectionSection.getConfigurationSection("webAPI");
        ConfigurationSection webSocketSection = connectionSection.getConfigurationSection("webSocket");

        // TODO: Validate configuration
        return new YaamConfig(
                new WebAPIConfig(
                        webAPISection.getString("host"),
                        webAPISection.getInt("port")
                ),

                new WebSocketConfig(
                        webSocketSection.getString("host"),
                        webSocketSection.getInt("port")
                ),
                new File(config.getString("sqliteDatabase"))
        );
    }
}