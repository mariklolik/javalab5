package org.example.utils;

import org.example.Configuration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static volatile Logger instance;
    private final String logFilePath;
    private PrintWriter writer=null;
    private final DateTimeFormatter formatter;

    private Logger(String logFilePath) {
        this.logFilePath = logFilePath;
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        initializeWriter();
    }

    public static Logger getInstance(String logFilePath) {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger(logFilePath);
                }
            }
        }
        return instance;
    }

    private void initializeWriter() {
        if (Configuration.saveLogs) {
            try {
                writer = new PrintWriter(new FileWriter(logFilePath, true));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void log(String message) {
        String logMessage = "[" + getCurrentTimestamp() + "] " + message;
        System.out.println(logMessage);
        System.out.flush();
        if (writer != null) {
            writer.println(logMessage);
            writer.flush();
        }
    }

    private String getCurrentTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        return formatter.format(now);
    }

}