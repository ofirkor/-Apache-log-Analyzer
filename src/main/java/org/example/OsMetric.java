package org.example;

public class OsMetric extends Metric {
    @Override
    protected String extractKey(LogEntry logEntry) {
        return logEntry.getOs();
    }
    @Override
    protected String getName(){
        return "Operating systems";
    }
}
