package com.odev.loganalyzer.model;

import java.util.Map;

public class ErrorResult {
    private final long totalErrors;
    private final Map<Integer, Long> errorByStatusCode;
    private final Map<String, Long> errorByEndpoint;
    private final Map<String, Long> errorByIp;

    public ErrorResult(long totalErrors, Map<Integer, Long> errorByStatusCode,
                       Map<String, Long> errorByEndpoint, Map<String, Long> errorByIp) {
        this.totalErrors = totalErrors;
        this.errorByStatusCode = errorByStatusCode;
        this.errorByEndpoint = errorByEndpoint;
        this.errorByIp = errorByIp;
    }

    public long getTotalErrors() {
        return totalErrors;
    }

    public Map<Integer, Long> getErrorByStatusCode() {
        return errorByStatusCode;
    }

    public Map<String, Long> getErrorByEndpoint() {
        return errorByEndpoint;
    }

    public Map<String, Long> getErrorByIp() {
        return errorByIp;
    }
}
