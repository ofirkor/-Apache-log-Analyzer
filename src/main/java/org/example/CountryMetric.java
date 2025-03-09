package org.example;

public class CountryMetric extends Metric {
    @Override
    protected String extractKey(LogEntry logEntry) {
        return logEntry.getCountry();
    }
    @Override
    protected String getName(){
        return "Country";
    }
}
