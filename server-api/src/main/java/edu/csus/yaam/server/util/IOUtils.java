package edu.csus.yaam.server.util;

import edu.csus.yaam.server.YaamServerLauncher;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.SneakyThrows;

/**
 * @author Ryan R
 * @date 4/1/2018
 */
public class IOUtils {
    @SneakyThrows
    public static String resourceToString(String resourcePath) {
        return new String(Files.readAllBytes(Paths.get(YaamServerLauncher.class.getResource(resourcePath).toURI())));
    }
}