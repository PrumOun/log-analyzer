package com.odev.loganalyzer.report;

import com.odev.loganalyzer.model.AnalysisResult;
import com.odev.loganalyzer.model.ErrorResult;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CsvReportGenerator {
    public void generateCsvReport(AnalysisResult result, String fileName) {
        try(FileWriter writer = new FileWriter(fileName)){
            // Header
            writer.write("LOG ANALYSIS REPORT\n");

            // Summary
            writer.write("SUMMARY\n");
            writer.write("Metric,Value\n");
            writer.write("Total Requests," + result.getTotalRequests() + "\n");

            // Top IPs
            writer.write("\nTOP IP ADDRESSES\n");
            writer.write("IP Address,Request Count\n");
            result.getTopIps()
                    .stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .forEach(entry -> {
                        try {
                            writer.write(entry.getKey() + "," + entry.getValue() + "\n");
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to write top IPs to CSV", e);
                        }
                    })
                    ;

            // Status Codes
            writer.write("\nSTATUS CODES\n");
            writer.write("Status Code,Request Count\n");
            for (Map.Entry<Integer, Long> entry : result.getStatusCounts().entrySet().stream().sorted(Map.Entry.<Integer, Long>comparingByValue().reversed()).toList()){
                try {
                    writer.write(entry.getKey() + "," + entry.getValue() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException("Failed to write status codes to CSV", e);
                }
            }

            // Endpoints
            writer.write("\nENDPOINTS\n");
            writer.write("Endpoint,Request Count\n");
            for (Map.Entry<String, Long> entry : result.getEndpointCounts().entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).toList()){
                try {
                    writer.write(entry.getKey() + "," + entry.getValue() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException("Failed to write endpoints to CSV", e);
                }
            }

            System.out.println("Report exported successfully: " + fileName);

        }catch (IOException e) {
            throw new RuntimeException("Failed to generate CSV report", e);
        }
    }

    public void generateErrorCsvReport(ErrorResult errorResult, String fileName) {
        try(FileWriter writer = new FileWriter(fileName)) {
            // Header
            writer.write("ERROR REPORT\n");

            // Summary
            writer.write("SUMMARY\n");
            writer.write("Metric,Value\n");
            writer.write("Total Errors," + errorResult.getTotalErrors() + "\n");

            // Errors by Status Code
            writer.write("\nERRORS BY STATUS CODE\n");
            writer.write("Status Code,Error Count\n");
            for (Map.Entry<Integer, Long> entry : errorResult.getErrorByStatusCode().entrySet().stream().sorted(Map.Entry.<Integer, Long>comparingByValue().reversed()).toList()){
                try {
                    writer.write(entry.getKey() + "," + entry.getValue() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException("Failed to write errors by status code to CSV", e);
                }
            }

            // Top Error Endpoints
            writer.write("\nTOP ERROR ENDPOINTS\n");
            writer.write("Endpoint,Error Count\n");
            for (Map.Entry<String, Long> entry : errorResult.getErrorByEndpoint().entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).toList()){
                try {
                    writer.write(entry.getKey() + "," + entry.getValue() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException("Failed to write top error endpoints to CSV", e);
                }
            }

            // Top Error IPs
            writer.write("\nTOP ERROR IPS\n");
            writer.write("IP Address,Error Count\n");
            for (Map.Entry<String, Long> entry : errorResult.getErrorByIp().entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed()).toList()){
                try {
                    writer.write(entry.getKey() + "," + entry.getValue() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException("Failed to write top error IPs to CSV", e);
                }
            }

            System.out.println("Error report exported successfully: " + fileName);

        } catch (IOException e) {
            throw new RuntimeException("Failed to generate error CSV report", e);
        }
    }
}
