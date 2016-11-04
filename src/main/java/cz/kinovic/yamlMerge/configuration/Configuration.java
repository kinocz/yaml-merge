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

    private String serverYamlName = "index.yaml";

    private File docRoot;

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

    public String getServerYamlName() {
        return serverYamlName;
    }

    @Option(name = "--serverFilename", aliases = {"-f"}, depends = {"--server"},
            metaVar = "index.yaml", usage = "Filename of generated yaml file.")
    public void setServerYamlName(String serverYamlName) {
        this.serverYamlName = serverYamlName;
    }

    public File getDocRoot() {
        return docRoot;
    }

    @Option(name = "--serverDocumentRoot", aliases = {"-r"}, depends = {"--server"},
            metaVar = "<docRootFolder>", usage = "Folder which we should serve files from.")
    public void setDocRoot(File docRoot) {
        this.docRoot = docRoot;
    }

    public boolean validate() {
        if (this.folder != null && !this.folder.isDirectory()) {
            this.addError("Input is not a directory or does not exists.");
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
