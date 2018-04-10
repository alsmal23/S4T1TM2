package edu.csus.yaam.server;

import com.timvisee.yamlwrapper.ConfigurationSection;
import com.timvisee.yamlwrapper.YamlConfiguration;
import edu.csus.yaam.server.util.IOUtils;
import java.io.File;
import java.nio.file.Files;
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

    public final File sqliteFile;

    public static YaamConfig load(Namespace namespace) {
        YamlConfiguration config = new YamlConfiguration();

        // load/save YAML configuration
        boolean noConfig = namespace.getBoolean("noConfig");
        File file = namespace.get("configFile");
        try {
            if (noConfig) {
                config.load(YaamConfig.class.getResourceAsStream("/yaam/defaultConfig.yml"));
            } else {
                File configFile = namespace.get("configFile");
                if (!configFile.exists()) {
                    Files.write(configFile.toPath(), IOUtils.resourceToString("/yaam/defaultConfig.yml").getBytes());
                }
                config.load(configFile);
            }
        } catch (Throwable throwable) {
            throw new RuntimeException("failed to load configuration from file" + (!noConfig ? ": " + file.getAbsolutePath() : ""));
        }


        // parse configuration
        ConfigurationSection connectionSection = config.getConfigurationSection("connection");
        ConfigurationSection webAPISection = connectionSection.getConfigurationSection("webAPI");

        // TODO: Validate configuration
        return new YaamConfig(
                new WebAPIConfig(
                        webAPISection.getString("host"),
                        webAPISection.getInt("port")
                ),
                !namespace.getBoolean("memoryDatabase") ? new File(config.getString("sqliteDatabase")) : null
        );
    }
}