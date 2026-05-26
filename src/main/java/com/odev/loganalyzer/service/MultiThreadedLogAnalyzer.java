package com.odev.loganalyzer.service;

import com.odev.loganalyzer.model.LogEntry;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadedLogAnalyzer {
    private final ExecutorService executorService;
    private final ConcurrentHashMap<String, Integer> ipCount;

    public MultiThreadedLogAnalyzer(int threadCount) {
        this.executorService = Executors.newFixedThreadPool(threadCount);
        this.ipCount = new ConcurrentHashMap<>();
    }

    public Map<String, Integer> analyzer(List<LogEntry> logs) throws InterruptedException {
        int chunkSize = Math.max(1, logs.size() / 4); // divide into 4 threads

        for(int i = 0; i < logs.size(); i += chunkSize) {
            int end = Math.min(i + chunkSize, logs.size());
            List<LogEntry> chunk = logs.subList(i, end);
            executorService.submit(() -> processChunk(chunk));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, java.util.concurrent.TimeUnit.MINUTES);
        return ipCount;
    }

    private void processChunk(List<LogEntry> chunk) {
        for(LogEntry log : chunk) {
            ipCount.merge(log.getIpAddress(), 1, Integer::sum);
        }
    }
}
