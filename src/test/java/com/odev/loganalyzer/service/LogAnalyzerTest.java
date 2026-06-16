package com.odev.loganalyzer.service;

import com.odev.loganalyzer.model.LogEntry;
import com.odev.loganalyzer.util.FileReaderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LogAnalyzerTest {

    private LogAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        FileReaderUtil reader = new FileReaderUtil();
        List<LogEntry> logs = reader.readLogsFromResource("sample.log");

        analyzer = new LogAnalyzer(logs);
    }

    @Test
    void shouldCountTotalRequests() {
        long totalRequests = analyzer.countTotalRequest();

        assertEquals(5, totalRequests);
    }

    @Test
    void shouldCountStatusCodes() {
        Map<Integer, Long> statusCounts =
                analyzer.countStatusCodes();

        assertEquals(1, statusCounts.get(200));
        assertEquals(3, statusCounts.get(404));
    }

    @Test
    void shouldCountRequestsByIp() {
        Map<String, Long> ipCounts =
                analyzer.countRequestsByIpAddress();

        assertEquals(2, ipCounts.get("192.168.1.10"));
        assertEquals(3, ipCounts.get("10.0.0.1"));
    }

    @Test
    void shouldReturnTopIps() {
        List<Map.Entry<String, Long>> topIps =
                analyzer.getTopEndpoints(5);

        assertFalse(topIps.isEmpty());

        assertEquals(
                "192.168.1.10",
                topIps.get(1).getKey()
        );
    }

    @Test
    void shouldCountEndpoints() {
        Map<String, Long> endpointCounts =
                analyzer.countEndpoints();

        assertEquals(
                1,
                endpointCounts.get("/api/products")
        );

        assertEquals(
                4,
                endpointCounts.get("/api/login")
        );
    }

    @Test
    void shouldReturnOnlyErrorLogs() {
        List<LogEntry> errorLogs =
                analyzer.getErrorLogs();

        assertEquals(4, errorLogs.size());

        assertEquals(
                404,
                errorLogs.get(0).getResponseCode()
        );
    }
}
