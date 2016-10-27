package cz.kinovic.yamlMerge;

import cz.kinovic.yamlMerge.configuration.Configuration;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

/**
 * @author Ondrej Kinovic (ondrej@kinovic.cz)
 * @since 27.10.16.
 */
public class Main {
    public static void main(String... args) {
        Main main = new Main();
        main.init(args);
    }


    private Configuration config = new Configuration();
    private CmdLineParser parser = new CmdLineParser(config);

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }

    public CmdLineParser getParser() {
        return parser;
    }

    public void setParser(CmdLineParser parser) {
        this.parser = parser;
    }


    private void printUsage() {
        System.out.println("Usage:");
        parser.printUsage(System.out);
    }

    private void printError(String error) {
        System.out.println("Error:");
        System.out.println(error + "\n");
        printUsage();
    }

    private void init(String... args) {
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            printError(e.getMessage());
        }
    }

}
