package cz.kinovic.yamlMerge.service;

import fi.iki.elonen.NanoHTTPD;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;

/**
 * @author Ondrej Kinovic (ondrej@kinovic.cz)
 * @since 30.10.16.
 */
public class SimpleServer extends NanoHTTPD {
    private String path;
    private String extension;

    public SimpleServer(int port, String path, String extension) throws IOException {
        super(port);
        this.path = path;
        this.extension = extension;
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    @Override
    public Response serve(IHTTPSession session) {
        Yaml snakeYaml = new Yaml();

        String outString = snakeYaml.dumpAsMap(FolderReader.getMapFromYaml(path, extension));

        Response response = newFixedLengthResponse(Response.Status.OK, "application/x-yaml", outString);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET");
        return response;
    }
}
