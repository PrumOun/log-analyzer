package com.odev.loganalyzer.app;

import com.odev.loganalyzer.model.LogEntry;
import com.odev.loganalyzer.service.MultiThreadedLogAnalyzer;
import com.odev.loganalyzer.util.FakeLogGenerator;
import com.odev.loganalyzer.util.FileReaderUtil;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        FakeLogGenerator generator =
                new FakeLogGenerator();

        generator.generateFakeLogs("sample.log", 100);

        FileReaderUtil reader = new FileReaderUtil();
        List<LogEntry> logEntries = reader.readLogsFromResource("sample.log");

//        LogAnalyzer analyzer = new LogAnalyzer(logEntries);
//
//        //logEntries.forEach(System.out::println);
//        System.out.println("Total Requests: " + analyzer.countTotalRequest());
//        System.out.println("Status Code Counts: " + analyzer.countStatusCodes());
//        System.out.println("Requests by IP Address: " + analyzer.countRequestsByIpAddress());
//
//        System.out.println("Top IPs:");
//        analyzer.getTopEndpoints(1).forEach(System.out::println);
//
//        System.out.println("\nEndpoints:");
//        System.out.println(analyzer.countEndpoints());
//
//        System.out.println("\nErrors:");
//        analyzer.getErrorLogs().forEach(System.out::println);

        MultiThreadedLogAnalyzer analyzer =
                new MultiThreadedLogAnalyzer(4);

        Map<String, Integer> result =
                analyzer.analyzer(logEntries);

        System.out.println(result);
    }
}