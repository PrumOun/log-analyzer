package com.odev.loganalyzer.service;

import com.odev.loganalyzer.model.LogEntry;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogAnalyzer {
    private final List<LogEntry> logEntries;

    public LogAnalyzer(List<LogEntry> logEntries) {
        this.logEntries = logEntries;
    }

    public long countTotalRequest() {
        return logEntries.size();
    }

    public Map<Integer, Long> countStatusCodes() {
        return logEntries.stream()
                .collect(Collectors.groupingBy(LogEntry::getResponseCode, Collectors.counting()));
    }

    public Map<String, Long> countRequestsByIpAddress() {
        return logEntries.stream()
                .collect(Collectors.groupingBy(LogEntry::getIpAddress, Collectors.counting()));
    }
}
