package com.odev.loganalyzer.app;

import com.odev.loganalyzer.model.AnalysisResult;
import com.odev.loganalyzer.model.ErrorResult;
import com.odev.loganalyzer.model.LogEntry;
import com.odev.loganalyzer.service.LogAnalyzer;
import com.odev.loganalyzer.service.MultiThreadedLogAnalyzer;
import com.odev.loganalyzer.service.ReportGenerator;
import com.odev.loganalyzer.util.FakeLogGenerator;
import com.odev.loganalyzer.util.FileReaderUtil;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//        FakeLogGenerator generator =
//                new FakeLogGenerator();
//
//        generator.generateFakeLogs("access.log", 100);

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

//        MultiThreadedLogAnalyzer analyzer =
//                new MultiThreadedLogAnalyzer(4);
//
//        Map<String, Integer> result =
//                analyzer.analyzer(logEntries);
//
//        System.out.println(result);

        // Perform performance comparison
        // Analyzer 1: Single-threaded
//        LogAnalyzer analyzer1 = new LogAnalyzer(logEntries);
//        long start1 = System.currentTimeMillis();
//        analyzer1.countRequestsByIpAddress();
//        long end1 = System.currentTimeMillis();
//        System.out.println("Single Thread: " + (end1 - start1) + " ms");
//
//        // Analyzer 2: Multi-threaded
//        MultiThreadedLogAnalyzer analyzer2 =
//                new MultiThreadedLogAnalyzer(4);
//        long start2 = System.currentTimeMillis();
//        analyzer2.analyzer(logEntries);
//        long end2 = System.currentTimeMillis();
//        System.out.println("Multi Thread: " + (end2 - start2) + " ms");

        LogAnalyzer analyzer =
                new LogAnalyzer(logEntries);

        AnalysisResult result =
                analyzer.generateReport();

        ErrorResult errorResult =
                analyzer.generateErrorReport();

        ReportGenerator reportGenerator =
                new ReportGenerator();

        reportGenerator.generateConsoleReport(result);
        reportGenerator.printErrorReport(errorResult);
    }
}