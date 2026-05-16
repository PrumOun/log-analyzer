package com.odev.loganalyzer.app;

import com.odev.loganalyzer.model.LogEntry;
import com.odev.loganalyzer.util.FileReaderUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileReaderUtil reader = new FileReaderUtil();
        List<LogEntry> logEntries = reader.readLogs("sample.log");

        logEntries.forEach(System.out::println);
    }
}