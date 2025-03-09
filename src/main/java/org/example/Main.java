package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Analyzer analyzer = new Analyzer();
        analyzer.addMetric(new CountryMetric());
        analyzer.addMetric(new OsMetric());
        analyzer.addMetric(new BrowserMetric());
        analyzer.loadLog("src/main/resources/b.log");
        analyzer.analyze();
        analyzer.printResults();
    }
}