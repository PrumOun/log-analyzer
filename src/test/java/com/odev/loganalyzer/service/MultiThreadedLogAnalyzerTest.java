package com.odev.loganalyzer.service;

import com.odev.loganalyzer.model.LogEntry;
import com.odev.loganalyzer.util.FileReaderUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiThreadedLogAnalyzerTest {
    @Test
    void shouldAnalyzeLogsInMultipleThreads() throws InterruptedException {
        // Given
        FileReaderUtil reader = new FileReaderUtil();
        List<LogEntry> logs = reader.readLogsFromResource("sample.log");
        MultiThreadedLogAnalyzer multiThreadedAnalyzer = new MultiThreadedLogAnalyzer(4);

        // When
        Map<String, Integer> ipCounts = multiThreadedAnalyzer.analyzer(logs);

        // Then
        int totalCount =
                ipCounts.values()
                        .stream()
                        .mapToInt(Integer::intValue)
                        .sum();

        assertEquals(logs.size(), totalCount);
    }

    @Test
    void multithreadedAndSingleThreadedShouldMatch()
            throws InterruptedException {

        FileReaderUtil reader =
                new FileReaderUtil();

        List<LogEntry> logs =
                reader.readLogsFromResource("sample.log");

        LogAnalyzer single =
                new LogAnalyzer(logs);

        MultiThreadedLogAnalyzer multi =
                new MultiThreadedLogAnalyzer(4);

        Map<String, Long> singleResult =
                single.countRequestsByIpAddress();

        Map<String, Integer> multiResult =
                multi.analyzer(logs);

        for (String ip : singleResult.keySet()) {

            assertEquals(
                    singleResult.get(ip).intValue(),
                    multiResult.get(ip)
            );
        }
    }
}
