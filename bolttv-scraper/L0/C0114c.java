package L0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class C0114c {

    public static String a() throws IOException {
        String strD = d("/api/bolttv/tv/all");
        StringBuilder sb = new StringBuilder("#EXTM3U\n");

        // ใช้ Regex สแกนหาวัตถุชุดช่องสัญญาณสตรีมมิ่ง แทนการใช้ JSONObject
        Pattern channelPattern = Pattern.compile("\\{[^\\{]*?\"id\"\\s*:\\s*\"([^\"]+)\".*?\"name\"\\s*:\\s*\"([^\"]+)\"([^\\}]*?)\\}");
        Matcher matcher = channelPattern.matcher(strD);

        while (matcher.find()) {
            String id = matcher.group(1);
            String name = matcher.group(2);
            String extra = matcher.group(3);

            String image = "";
            Pattern imgPattern = Pattern.compile("\"image\"\\s*:\\s*\"([^\"]+)\"");
            Matcher imgMatcher = imgPattern.matcher(extra);
            if (imgMatcher.find()) { image = imgMatcher.group(1); }

            String category = "BoltTV";
            Pattern catPattern = Pattern.compile("\"category\"\\s*:\\s*\"([^\"]+)\"");
            Matcher catMatcher = catPattern.matcher(extra);
            if (catMatcher.find()) { category = catMatcher.group(1); }

            sb.append("#EXTINF:-1 group-title=\"").append(category.replace("\"", "'")).append("\" tvg-logo=\"");
            sb.append(image.replace("\"", "'")).append("\",").append(name).append("\n");
            sb.append("bolttv://").append(id).append("\n");
        }
        return sb.toString();
    }

    public static String d(String str) throws IOException {
        String string = str.contains("deviceid=") ? str : str + (str.contains("?") ? "&" : "?") + "deviceid=b20942e24f3e41f1a7c65374416f1d6cb13ca943ec6809e49d945fb8ec6b2bf2";
        if (!string.startsWith("/")) { string = "/" + string; }

        long timestamp = System.currentTimeMillis() / 1000;
        String string3 = "{\"body\":null,\"method\":\"GET\",\"time\":" + timestamp + ",\"url\":\"" + string.replace("\"", "\\\"") + "\",\"x-app-deviceid\":\"b20942e24f3e41f1a7c65374416f1d6cb13ca943ec6809e49d945fb8ec6b2bf2\",\"x-app-os\":\"android\",\"x-app-signature\":\"a13b05bb72d104514ed808fcdf50fe2e24f5f3ec5589c83d1a21916bdc085007\",\"x-app-version\":\"1\"}";

        String strB = p0.b(string3);
        String strG = p0.g(string3);
        String string4 = "{\"data\":\"" + strB.replace("\"", "\\\"") + "\"}";
        byte[] bytes = string4.getBytes(StandardCharsets.UTF_8);

        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://apiapi.thaiiptvfree.download/url/app").openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(8000);
        httpURLConnection.setReadTimeout(8000);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("User-Agent", "AppAllTV/1.0 SecureProxy/1.0");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("signature", strG);

        try (OutputStream outputStream = httpURLConnection.getOutputStream()) {
            outputStream.write(bytes);
        }

        String string2 = "";
        InputStream errorStream = httpURLConnection.getResponseCode() >= 400 ? httpURLConnection.getErrorStream() : httpURLConnection.getInputStream();
        if (errorStream != null) {
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, StandardCharsets.UTF_8))) {
                StringBuilder sb4 = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb4.append(line).append('\n');
                }
                string2 = sb4.toString();
            }
        }

        if ("true".equalsIgnoreCase(httpURLConnection.getHeaderField("x-encrypted"))) {
            return p0.a(string2);
        }
        return string2;
    }
}
