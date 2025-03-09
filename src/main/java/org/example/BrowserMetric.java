package org.example;

public class BrowserMetric extends Metric {
    @Override
    protected String extractKey(LogEntry logEntry) {
        return logEntry.getBrowser();
    }
    @Override
    protected String getName(){
        return "Browser";
    }
}
