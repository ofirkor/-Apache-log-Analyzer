package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Analyzer {
    private List<Metric> metrics;
    private List<LogEntry> logEntries;

    public Analyzer() {
        metrics = new ArrayList<>();
        logEntries = new ArrayList<>();
    }

    public void addMetric(Metric metric) {
        metrics.add(metric);
    }

    public void loadLog(String filePath){
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    logEntries.add(new LogEntry(line.trim()));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading log file: " + e.getMessage());
        }
    }

    public void analyze() {
        for (LogEntry entry : logEntries) {
            for (Metric metric : metrics) {
                metric.processEntry(entry);
            }
        }
    }

    public void printResults(){
        for(Metric metric : metrics){
            metric.printResults();
        }
    }
}
