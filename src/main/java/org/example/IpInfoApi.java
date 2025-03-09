package org.example;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class IpInfoApi {

    private static boolean printedTooManyRequests = false;

    public static String getCountryFromIp(String ipAddress) {
        try {
            //  request for API ipinfo.io
            String urlString = "http://ipinfo.io/" + ipAddress + "/json";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == 429){
                if(!printedTooManyRequests) {
                    // Too Many Requests (HTTP 429)
                    System.out.println("Problem with IP API: Too many requests. Returning 'Unknown country'.");
                    printedTooManyRequests = true;
                }
                return "Unknown";
            }

            //read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            return jsonResponse.optString("country", "Unknown");
        } catch (Exception e) {
            e.printStackTrace();
            return "Unknown";
        }
    }
}
