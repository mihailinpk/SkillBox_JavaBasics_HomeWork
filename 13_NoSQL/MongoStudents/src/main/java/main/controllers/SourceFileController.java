package main.controllers;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class SourceFileController {

    private final String SOURCE_FILE_NAME = "mongo.csv";

    private final Path path;

    public SourceFileController() {
        path = Paths.get(SOURCE_FILE_NAME);
    }

    public List<String> getLinesFromFile() throws IOException {
        return Files.readAllLines(path);
    }

}
