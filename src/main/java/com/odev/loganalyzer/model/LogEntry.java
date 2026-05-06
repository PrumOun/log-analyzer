package com.odev.loganalyzer.model;

import java.time.OffsetDateTime;

public class LogEntry {
    private final String ipAddress;
    private final OffsetDateTime timestamp;
    private final String requestMethod;
    private final String endpoint;
    private final int responseCode;
    private final Long responseSize;

    public LogEntry(
            String ipAddress,
            OffsetDateTime timestamp,
            String requestMethod,
            String endpoint,
            int responseCode,
            Long responseSize)
    {
        this.ipAddress = ipAddress;
        this.timestamp = timestamp;
        this.requestMethod = requestMethod;
        this.endpoint = endpoint;
        this.responseCode = responseCode;
        this.responseSize = responseSize;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public Long getResponseSize() {
        return responseSize;
    }

    @Override
    public String toString() {
        return String.format(
                "LogEntry{ipAddress='%s', timestamp=%s, requestMethod='%s', endpoint='%s', responseCode=%d, responseSize=%d}",
                ipAddress, timestamp, requestMethod, endpoint, responseCode, responseSize);
    }
}
