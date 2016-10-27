package cz.kinovic.yamlMerge.configuration;

import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ondrej Kinovic (ondrej@kinovic.cz)
 * @since 27.10.16.
 */
public class Configuration {

    private File folder;

    private int serverPort = 0;

    private File outputFile;

    private String fileExtension = "yaml";

    private List<String> errors = new ArrayList<>();

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

    public String getFileExtension() {
        return fileExtension;
    }

    @Option(name = "--extension", aliases = {"-e"},
            metaVar = "yaml", usage = "Extension which we should look for")
    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public boolean validate() {
        if (this.folder != null && !this.folder.isDirectory()) {
            this.addError("Input is not a directory or does not exists.");
        }

        if (this.serverPort == 0 && this.outputFile == null) {
            this.addError("You have to specify one of [server, output] parameter.");
        }

        return this.getErrors().size() == 0;
    }

    private void addError(String error) {
        this.errors.add(error);
    }

    public List<String> getErrors() {
        return errors;
    }
}
