package com.odev.loganalyzer.model;

import java.util.List;
import java.util.Map;

public class AnalysisResult {
    private final long totalRequests;
    private final Map<String, Long> ipCounts;
    private final Map<Integer, Long> statusCounts;
    private final Map<String, Long> endpointCounts;
    private final List<Map.Entry<String, Long>> topIps;

    public AnalysisResult(long totalRequests, Map<String, Long> ipCounts, Map<Integer, Long> statusCounts,
                          Map<String, Long> endpointCounts, List<Map.Entry<String, Long>> topIps) {
        this.totalRequests = totalRequests;
        this.ipCounts = ipCounts;
        this.statusCounts = statusCounts;
        this.endpointCounts = endpointCounts;
        this.topIps = topIps;
    }

    public long getTotalRequests() {
        return totalRequests;
    }

    public Map<String, Long> getIpCounts() {
        return ipCounts;
    }

    public Map<Integer, Long> getStatusCounts() {
        return statusCounts;
    }

    public List<Map.Entry<String, Long>> getTopIps() {
        return topIps;
    }

    public Map<String, Long> getEndpointCounts() {
        return endpointCounts;
    }

}
