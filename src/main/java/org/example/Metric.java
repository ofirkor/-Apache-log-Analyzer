package org.example;

import java.util.HashMap;
import java.util.Map;

public abstract class Metric {
    protected final Map<String, Integer> occurrences;
    protected int totalCount;

    public Metric() {
        occurrences = new HashMap<>();
        totalCount = 0;
    }

    public void processEntry(LogEntry logEntry){
        String key = extractKey(logEntry);
        occurrences.put(key, occurrences.getOrDefault(key, 0) + 1);
        totalCount++;
    }

    public void printResults(){
        Map<String,Double> percentages = new HashMap<>();
        for(Map.Entry<String, Integer> entry: occurrences.entrySet()){
            //percentages.put(entry.getKey(), roundToTwoDecimals(entry.getValue()) * 100.0 / totalCount); //todo erase
            percentages.put(entry.getKey(), entry.getValue() * 100.0 / totalCount);
        }

        System.out.printf(getName() + ":\n");

        percentages.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .forEach(e -> System.out.printf("\t%s: %.2f%%\n", e.getKey(), e.getValue()));
    }

    protected abstract String extractKey(LogEntry logEntry);
    protected abstract String getName();


}
