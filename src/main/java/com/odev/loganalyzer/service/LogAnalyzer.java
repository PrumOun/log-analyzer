package com.odev.loganalyzer.service;

import com.odev.loganalyzer.model.AnalysisResult;
import com.odev.loganalyzer.model.ErrorResult;
import com.odev.loganalyzer.model.LogEntry;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LogAnalyzer {
    private final List<LogEntry> logEntries;

    public AnalysisResult generateReport() {
        return new AnalysisResult(
                countTotalRequest(),
                countRequestsByIpAddress(),
                countStatusCodes(),
                countEndpoints(),
                getTopEndpoints(5)
        );
    }

    public ErrorResult generateErrorReport() {
        return new ErrorResult(
                getErrorLogs().size(),
                countErrorByStatusCode(),
                countErrorByEndpoint(),
                topErrorIps()
        );
    }

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

    public List<Map.Entry<String, Long>> getTopEndpoints(int limits){
        return countRequestsByIpAddress().entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(limits)
                .toList();
    }

    public Map<String, Long> countEndpoints(){
        return logEntries.stream()
                .collect(Collectors.groupingBy(LogEntry::getEndpoint, Collectors.counting()));
    }

    public List<LogEntry> getErrorLogs(){
        return logEntries.stream()
                .filter(log -> log.getResponseCode() >= 400)
                .toList();
    }

    public Map<Integer, Long> countErrorByStatusCode(){
        return logEntries.stream()
                .filter(log -> log.getResponseCode() >= 400)
                .collect(Collectors.groupingBy(LogEntry::getResponseCode, Collectors.counting()));
    }

    public Map<String, Long> countErrorByEndpoint(){
        return logEntries.stream()
                .filter(log -> log.getResponseCode() >= 400)
                .collect(Collectors.groupingBy(LogEntry::getEndpoint, Collectors.counting()));
    }

    public Map<String, Long> topErrorIps() {
        return logEntries.stream()
                .filter(log -> log.getResponseCode() >= 400)
                .collect(Collectors.groupingBy(
                        LogEntry::getIpAddress,
                        Collectors.counting()
                ));
    }

}
