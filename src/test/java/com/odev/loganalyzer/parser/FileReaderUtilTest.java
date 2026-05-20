package com.odev.loganalyzer.parser;

import com.odev.loganalyzer.model.LogEntry;
import com.odev.loganalyzer.util.FileReaderUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileReaderUtilTest {
    @Test
    void shouldReadAndParseLogFile() {
        FileReaderUtil reader = new FileReaderUtil();

        List<LogEntry> logs = reader.readLogs("sample.log");

        assertNotNull(logs);
        assertEquals(5, logs.size()); // invalid line ignored
    }
}
