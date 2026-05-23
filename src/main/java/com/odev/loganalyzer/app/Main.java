package com.odev.loganalyzer.app;

import com.odev.loganalyzer.model.LogEntry;
import com.odev.loganalyzer.service.LogAnalyzer;
import com.odev.loganalyzer.util.FileReaderUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileReaderUtil reader = new FileReaderUtil();
        List<LogEntry> logEntries = reader.readLogs("sample.log");

        LogAnalyzer analyzer = new LogAnalyzer(logEntries);

        //logEntries.forEach(System.out::println);
        System.out.println("Total Requests: " + analyzer.countTotalRequest());
        System.out.println("Status Code Counts: " + analyzer.countStatusCodes());
        System.out.println("Requests by IP Address: " + analyzer.countRequestsByIpAddress());

        System.out.println("Top IPs:");
        analyzer.getTopEndpoints(1).forEach(System.out::println);

        System.out.println("\nEndpoints:");
        System.out.println(analyzer.countEndpoints());

        System.out.println("\nErrors:");
        analyzer.getErrorLogs().forEach(System.out::println);
    }
}