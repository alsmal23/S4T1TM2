package edu.csus.yaam.server;

import java.net.InetAddress;
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
        public final InetAddress host;
        public final int port;
    }

    public final WebAPIConfig webApi;

    @RequiredArgsConstructor
    public static class WebSocketConfig {
        public final InetAddress host;
        public final int port;
    }

    public final WebSocketConfig WebSocketConfig;


    public static YaamConfig load(Namespace namespace) {
        throw new UnsupportedOperationException();
    }
}