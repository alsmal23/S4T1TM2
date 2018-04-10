package edu.csus.yaam.server;


import java.io.File;
import java.io.PrintWriter;
import lombok.extern.log4j.Log4j2;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentGroup;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

/**
 * Procedurally starts a YAAM server
 *
 * @author Ryan R
 * @date 3/8/2018
 */
@Log4j2
public class YaamServerLauncher {
    public static void main(String... args) {
        // build argument parser
        ArgumentParser parser = ArgumentParsers.newFor("TaskManager")
                .addHelp(false)
                .build()
                .defaultHelp(true)
                .description("YAAM WebServer");


        // add additional parameters
        ArgumentGroup optionalArguments = parser.addArgumentGroup("Optional Arguments");
        // allow specifying a configuration file by a filepath
        optionalArguments.addArgument("--conf", "--config")
                .dest("configFile")
                .nargs(1)
                .type(File.class)
                .metavar("config.yml")
                .setDefault(new File("yaamServer.yml"))
                .help("Specifies a configuration file by filepath");
        // use no external configuration file, use bundled config
        optionalArguments.addArgument("--noconf")
                .action(Arguments.storeTrue())
                .dest("noConfig")
                .type(boolean.class)
                .help("Uses default bundled config");
        // use strictly a in-memory sqlite database
        optionalArguments.addArgument("--memorydb")
                .action(Arguments.storeTrue())
                .dest("memoryDatabase")
                .type(boolean.class)
                .help("Uses a in-memory database");


        // parse arguments
        Namespace namespace;
        try {
            namespace = parser.parseArgs(args);
        } catch (ArgumentParserException exception) {
            System.err.println("error: " + exception.getMessage());
            System.err.println();
            parser.printHelp(new PrintWriter(System.err, true));
            System.exit(-1);
            return;
        }


        // load YAAM server configuration and settings into memory
        YaamConfig config;
        try {
            config = YaamConfig.load(namespace);
            log.info("Loaded configuration");
        } catch (Throwable throwable) {
            log.fatal("Failed to load configuration", throwable);
            System.exit(-1);
            return;
        }

        // launch YAAM server endpoints
        YaamServer yaamServer = new YaamServer(config);
        try {
            yaamServer.launch();
            log.info("Loaded configuration");
        } catch (Throwable throwable) {
            log.fatal("Failed to load configuration", throwable);
            System.exit(-1);
            return;
        }

        System.out.println();
        log.info("YAAM server running");
    }
}