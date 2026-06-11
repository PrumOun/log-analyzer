package com.odev.loganalyzer.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odev.loganalyzer.model.AnalysisResult;

public class JsonReportGenerator {

    public String generateJsonReport(AnalysisResult result) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(result);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to generate JSON report", e);
        }
    }

    public String generateErrorJsonReport(Object errorReport) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(errorReport);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to generate JSON error report", e);
        }
    }
}
