package cz.kinovic.yamlMerge.service;

import cz.kinovic.yamlMerge.configuration.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Ondrej Kinovic (ondrej@kinovic.cz)
 * @since 27.10.16.
 */
public class FolderReader {
    public static Map getMapFromYaml(String inputFolder, String extension) {
        try {
            final Yaml snakeYaml = new Yaml();
            Stream<Path> paths = Files.find(Paths.get(inputFolder), 100, (path, attr) ->
                    String.valueOf(path).endsWith("." + extension));
            Optional<Map> mapOptional = paths.map(filePath -> {
                try {
                    return (Map) snakeYaml.load(new FileReader(filePath.toFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                    return new HashMap();
                }
            }).reduce(MapMerge::deepMerge);

            if (mapOptional.isPresent()) {
                return mapOptional.get();
            } else {
                return new HashMap<>();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }


}
