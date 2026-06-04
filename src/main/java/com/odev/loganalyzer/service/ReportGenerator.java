package com.odev.loganalyzer.service;

import com.odev.loganalyzer.model.AnalysisResult;
import com.odev.loganalyzer.model.ErrorResult;

import java.util.Map;

public class ReportGenerator {

    public void generateConsoleReport(AnalysisResult result){
        System.out.println();
        System.out.println("================================");
        System.out.println("      LOG ANALYSIS REPORT");
        System.out.println("================================");

        System.out.println();

        System.out.println("Total Requests: " + result.getTotalRequests());

        System.out.println();
        System.out.println("Top IP Addresses:");
        printMap(result.getIpCounts());

        System.out.println();
        System.out.println("Status Codes:");
        printMap(result.getStatusCounts());

        System.out.println();
        System.out.println("Endpoints:");
        printMap(result.getEndpointCounts());

        System.out.println();
        System.out.println("================================");
    }

    public void printErrorReport(ErrorResult report) {

        System.out.println("\n=================================");
        System.out.println("        ERROR REPORT");
        System.out.println("=================================");

        System.out.println("\nTotal Errors: " + report.getTotalErrors());

        System.out.println("\n--- Errors by Status Code ---");
        printMap(report.getErrorByStatusCode());

        System.out.println("\n--- Top Error Endpoints ---");
        printMap(report.getErrorByEndpoint());

        System.out.println("\n--- Top Error IPs ---");
        printMap(report.getErrorByIp());

        System.out.println("=================================\n");
    }

    private <T> void printMap(Map<T, Long> map) {
        map.entrySet().stream()
                .sorted(Map.Entry.<T, Long>comparingByValue().reversed())
                .forEach(entry -> {

                    if (entry.getKey() instanceof Integer) {
                        System.out.printf("HTTP %s -> %d%n",
                                entry.getKey(),
                                entry.getValue());
                    } else {
                        System.out.printf("%-30s %d%n",
                                entry.getKey(),
                                entry.getValue());
                    }
                });
    }
}
