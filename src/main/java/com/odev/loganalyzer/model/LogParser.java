package com.odev.loganalyzer.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    private static final Pattern LOG_PATTERN = Pattern.compile(
            "^(\\S+) - - \\[(.*?)] \"(GET|POST|PUT|DELETE|PATCH) (.*?) HTTP/.*\" (\\d{3}) (\\d+)$"
    );

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);

    public Optional<LogEntry> parse(String logLine){
        Matcher matcher = LOG_PATTERN.matcher(logLine);
        if(!matcher.matches()){
            return Optional.empty();
        }

        try {
            String ipAddress = matcher.group(1);
            LocalDateTime timestamp = LocalDateTime.parse(matcher.group(2), DATE_FORMATTER);
            String requestMethod = matcher.group(3);
            String endpoint = matcher.group(4);
            int responseCode = Integer.parseInt(matcher.group(5));
            Long responseSize = Long.parseLong(matcher.group(6));

            LogEntry logEntry = new LogEntry(ipAddress, timestamp, requestMethod, endpoint, responseCode, responseSize);

            return Optional.of(logEntry);
        }catch (Exception e){
            return Optional.empty();
        }
    }
}
