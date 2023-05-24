package org.example.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class Utils {
    private static final String pathToConfiguration = "src/main/resources/configuration.properties";
    public static <T> T readSetting(String targetSetting, T defaultValue, Function<String, T> parser) {
        Path filePath = Paths.get(pathToConfiguration);
        List<String> lines;
        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            System.err.println("Unable to read config file at:" + filePath);
            return defaultValue;
        }

        Optional<String> line = lines.stream().filter(l -> l.startsWith(targetSetting + "=")).findFirst();

        if (line.isPresent()) {
            String[] parts = line.get().split("=");


            if (parts.length == 2) {
                try {
                    T result = parser.apply(parts[1]);
                    if (defaultValue instanceof String) {
                        if (((String) defaultValue).matches("^src/main/resources/[\\w.-]+\\.[\\w]+$")) {
                            String resExt = result.toString().split("\\.")[1];
                            String expExt = defaultValue.toString().split("\\.")[1];
                            if (!resExt.equals(expExt)) {
                                return defaultValue;
                            }
                        }
                    }
                    return result;
                } catch (NumberFormatException e) {
                    System.err.println("Expected type of value " + targetSetting + " was: " + defaultValue.getClass());
                    return defaultValue;
                }
            }
        }
        System.err.println("Setting " + targetSetting + " was not found");
        return defaultValue;
    }
}
