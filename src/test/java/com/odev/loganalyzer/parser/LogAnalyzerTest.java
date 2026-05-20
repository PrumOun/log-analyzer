package com.odev.loganalyzer.parser;

import com.odev.loganalyzer.model.LogEntry;
import com.odev.loganalyzer.service.LogAnalyzer;
import com.odev.loganalyzer.util.FileReaderUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogAnalyzerTest {

    @Test
    void shouldAnalyzeLogs() {
        // Given
        LogParser parser = new LogParser();
        FileReaderUtil reader = new FileReaderUtil();
        List<LogEntry> logEntries = reader.readLogs("sample.log");
        LogAnalyzer analyzer = new LogAnalyzer(logEntries);

        // When
        long totalRequests = analyzer.countTotalRequest();
        Map<Integer, Long> statusCodeCounts = analyzer.countStatusCodes();
        Map<String, Long> requestsByIp = analyzer.countRequestsByIpAddress();

        // Then
        assertEquals(4, totalRequests);;
        assertEquals(1, statusCodeCounts.get(200));
        assertEquals(3, statusCodeCounts.get(404));
        assertEquals(1, statusCodeCounts.get(500));
        assertEquals(2, requestsByIp.get("192.168.1.10"));
    }
}
