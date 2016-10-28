package cz.kinovic.yamlMerge;

import cz.kinovic.yamlMerge.configuration.Configuration;
import cz.kinovic.yamlMerge.service.FolderReader;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
            this.getParser().parseArgument(args);
        } catch (CmdLineException e) {
            printError(e.getMessage());
            System.exit(1);
        }

        if (!this.getConfig().validate()) {
            this.getConfig().getErrors().forEach(System.out::print);
            System.exit(1);
        }

        if (this.getConfig().getOutputFile() != null) {
            final Yaml snakeYaml = new Yaml();
            String outputFilePath = this.getConfig().getOutputFile().getAbsolutePath();
            System.out.println("creating output file: " + outputFilePath);
            try {
                //noinspection ResultOfMethodCallIgnored
                this.getConfig().getOutputFile().getParentFile().mkdirs();
                FileWriter fileWriter = new FileWriter(outputFilePath);
                fileWriter.write(snakeYaml.dumpAsMap(FolderReader.getMapFromYaml(this.getConfig())));
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
