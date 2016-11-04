package cz.kinovic.yamlMerge.service;

import cz.kinovic.yamlMerge.configuration.Configuration;
import fi.iki.elonen.NanoHTTPD;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

/**
 * @author Ondrej Kinovic (ondrej@kinovic.cz)
 * @since 30.10.16.
 */
public class SimpleServer extends NanoHTTPD {
    private String path;
    private String extension;
    private String yamlName;
    private String docRoot;
    private boolean useDocRoot = false;

    public SimpleServer(Configuration config) throws IOException {
        super(config.getServerPort());
        this.extension = config.getFileExtension();
        this.path = config.getFolder().getAbsolutePath();
        //if the file does not start with slash we should add it
        this.yamlName = config.getServerYamlName().startsWith("/") ? config.getServerYamlName() : "/" + config.getServerYamlName();

        if (config.getDocRoot() != null && config.getDocRoot().exists() && config.getDocRoot().isDirectory()) {
            this.docRoot = config.getDocRoot().getAbsolutePath();
            this.useDocRoot = true;
        }

        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    @Override
    public Response serve(IHTTPSession session) {
        Response response = newFixedLengthResponse(Response.Status.NOT_FOUND, "test/plain", "NOT FOUND");

        if (yamlName.equals(session.getUri())) {
            Yaml snakeYaml = new Yaml();
            String outString = snakeYaml.dumpAsMap(FolderReader.getMapFromYaml(path, extension));
            response = newFixedLengthResponse(Response.Status.OK, "application/x-yaml", outString);
        } else if (this.useDocRoot) {
            String filePath = session.getUri();
            if (filePath.equals("/")) {
                filePath = "index.html"; //really? shouldn't we use something more generic?
            }
            filePath = docRoot + File.separator + filePath;
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                try {
                    String contentType = Files.probeContentType(file.toPath());
                    response = newFixedLengthResponse(Response.Status.OK, contentType, new FileInputStream(file), file.length());
                } catch (IOException e) {
                    e.printStackTrace();
                    response = newFixedLengthResponse(Response.Status.INTERNAL_ERROR, "text/plain", e.getMessage());
                }
            }
        }
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET");
        System.out.println(new Date().toString() + " [" + response.getStatus().getRequestStatus() + "] " + session.getUri());
        return response;

    }
}
