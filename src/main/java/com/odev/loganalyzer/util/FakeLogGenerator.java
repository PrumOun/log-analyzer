package com.odev.loganalyzer.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FakeLogGenerator {
    private static final String[] IP_ADDRESSES = {
            "192.168.1.1",
            "10.0.0.1",
            "172.16.0.1",
            "192.168.1.10"
    };
    private static final String[] METHODS = {
            "GET",
            "POST",
            "PUT",
            "DELETE"
    };
    private static final String[] ENDPOINTS = {
            "/api/login",
            "/api/products",
            "/api/orders",
            "/api/users"
    };
    private static final int[] STATUS_CODES = {
            200, 201, 400, 404, 500
    };

    public void generateFakeLogs(String fileName, int count) {
        String filePath = "src/main/resources/" + fileName;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))){
            for (int i = 0; i < count; i++) {
                String ip = IP_ADDRESSES[(int) (Math.random() * IP_ADDRESSES.length)];
                String method = METHODS[(int) (Math.random() * METHODS.length)];
                String endpoint = ENDPOINTS[(int) (Math.random() * ENDPOINTS.length)];
                int statusCode = STATUS_CODES[(int) (Math.random() * STATUS_CODES.length)];
                int responseSize = (int) (Math.random() * 1000) + 200;

                String logLine = String.format(
                        "%s - - [29/Apr/2026:10:15:32 +0000] \"%s %s HTTP/1.1\" %d %d",
                        ip,
                        method,
                        endpoint,
                        statusCode,
                        responseSize
                );

                writer.write(logLine);
                writer.newLine();
            }
            System.out.println(count + " logs generated.");
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

}
