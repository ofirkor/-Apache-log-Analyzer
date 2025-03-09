package org.example;

import eu.bitwalker.useragentutils.UserAgent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogEntry {
    private String ip;
    private String country;
    private String os;
    private String browser;

    // regular expression to extract the IP and User-Agent from an Apache log
    private static final String LOG_PATTERN =
           // "(\\d+\\.\\d+\\.\\d+\\.\\d+) .* \"[A-Z]+ .*\" \\d+ \\d+ \".*\" \"([^\"]+)\"";
            "^(\\S+) \\S+ \\S+ \\[.*\\] \"[^\"]+\" \\d+ \\S+ \"[^\"]*\" \"([^\"]+)\"";

    public LogEntry(String rawLogLine) {
        parseLog(rawLogLine);
    }

    private void parseLog(String rawLogLine) {
        Pattern pattern = Pattern.compile(LOG_PATTERN);
        Matcher matcher = pattern.matcher(rawLogLine);

        if (matcher.find()) {
            this.ip = matcher.group(1);
            String userAgentString = matcher.group(2);

            UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
            this.os = userAgent.getOperatingSystem().getName().split("\\s+")[0]; //.replaceAll("\\s+\\d+.*", "").trim();
            this.browser = userAgent.getBrowser().getName().replaceAll("\\s+\\d+.*", "").trim();

            this.country = getCountryFromIP(ip);
        }
    }

    private String getCountryFromIP(String ip) {
        return IpInfoApi.getCountryFromIp(ip);
    }

    public String getIp() { return ip; }
    public String getCountry() { return country; }
    public String getOs() { return os; }
    public String getBrowser() { return browser; }


    // todo erase
    @Override
    public String toString() {
        return "IP: " + ip + ", Country: " + country + ", OS: " + os + ", Browser: " + browser;
    }
}
