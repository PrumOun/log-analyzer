package com.odev.loganalyzer.util;

import com.odev.loganalyzer.model.LogEntry;
import com.odev.loganalyzer.parser.LogParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {
    private final LogParser logParser = new LogParser();

    public List<LogEntry> readLogs(String filePath){
        List<LogEntry> logEntries = new ArrayList<>();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);

        if(inputStream == null){
            throw new RuntimeException("File not found: " + filePath);
        }

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logParser.parse(line).ifPresent( logEntries::add );
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }

        return logEntries;
    }
}
