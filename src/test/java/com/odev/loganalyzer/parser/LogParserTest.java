package com.odev.loganalyzer.parser;

import com.odev.loganalyzer.model.LogEntry;
import com.odev.loganalyzer.model.LogParser;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogParserTest {
    private final LogParser logParser = new LogParser();

    @Test
    void shouldParseValidLogLine() {
        String line = "192.168.1.10 - - [29/Apr/2026:10:15:32 +0000] \"GET /api/products HTTP/1.1\" 200 1024";

        Optional<LogEntry> result = logParser.parse(line);

        assertTrue(result.isPresent());
        LogEntry entry = result.get();
        assertEquals("192.168.1.10", entry.getIpAddress());
        assertEquals("GET", entry.getRequestMethod());
        assertEquals("/api/products", entry.getEndpoint());
        assertEquals(200, entry.getResponseCode());
        assertEquals(1024, entry.getResponseSize());
    }

    @Test
    void shouldReturnEmptyForInvalidLogLine() {
        String line = "THIS IS NOT A VALID LOG";

        Optional<LogEntry> result = logParser.parse(line);

        assertTrue(result.isEmpty());
    }

    @Test
    void shouldParsePostRequestWithErrorStatus() {
        String line = "10.0.0.1 - - [29/Apr/2026:11:00:00 +0000] \"POST /api/login HTTP/1.1\" 404 512";

        Optional<LogEntry> result = logParser.parse(line);

        assertTrue(result.isPresent());

        LogEntry entry = result.get();
        assertEquals("POST", entry.getRequestMethod());
        assertEquals(404, entry.getResponseCode());
    }
}
