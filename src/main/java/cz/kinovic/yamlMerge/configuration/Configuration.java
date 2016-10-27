package cz.kinovic.yamlMerge.configuration;

import org.kohsuke.args4j.Option;

import java.io.File;

/**
 * @author Ondrej Kinovic (ondrej@kinovic.cz)
 * @since 27.10.16.
 */
public class Configuration {

    private File folder;

    private int serverPort = 0;

    private File outputFile;

    private String fileRegex = "yaml";


    public File getFolder() {
        return folder;
    }

    @Option(name = "--input", aliases = {"-i"}, required = true,
            metaVar = "<folder>", usage = "Path to source yaml files.")
    public void setFolder(File folder) {
        this.folder = folder;
    }

    public int getServerPort() {
        return serverPort;
    }

    @Option(name = "--server", aliases = {"-s"}, forbids = {"--output"},
            metaVar = "<server port>", usage = "Server port where it should start.")
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public File getOutputFile() {
        return outputFile;
    }

    @Option(name = "--output", aliases = {"-o"}, forbids = {"--server"},
            metaVar = "<file>.yaml", usage = "Where the result should be stored")
    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public String getFileRegex() {
        return fileRegex;
    }

    @Option(name = "--extension", aliases = {"-e"},
            metaVar = "yaml", usage = "Extension which we should look for")
    public void setFileRegex(String fileRegex) {
        this.fileRegex = fileRegex;
    }
}
