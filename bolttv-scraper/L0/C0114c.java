package L0;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: L0.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0114c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final ConcurrentHashMap f2555a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ConcurrentHashMap f2556b = new ConcurrentHashMap();

    public static String a() throws IOException {
        JSONObject jSONObjectOptJSONObject;
        JSONArray jSONArrayOptJSONArray;
        String strD = d("/api/bolttv/tv/all");
        StringBuilder sb = new StringBuilder("#EXTM3U\n");
        JSONObject jSONObject = new JSONObject(strD);
        if ("true".equals(jSONObject.optString("status")) && (jSONObjectOptJSONObject = jSONObject.optJSONObject("data")) != null && (jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("channels")) != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject2 != null) {
                    String strOptString = jSONObjectOptJSONObject2.optString("id", "");
                    String strOptString2 = jSONObjectOptJSONObject2.optString("name", "");
                    String strOptString3 = jSONObjectOptJSONObject2.optString("image", "");
                    String strOptString4 = jSONObjectOptJSONObject2.optString("category", "BoltTV");
                    if (!strOptString.isEmpty() && !strOptString2.isEmpty()) {
                        sb.append("#EXTINF:-1 group-title=\"");
                        sb.append(strOptString4 == null ? "" : strOptString4.replace("\"", "'"));
                        sb.append("\" tvg-logo=\"");
                        sb.append(strOptString3 != null ? strOptString3.replace("\"", "'") : "");
                        sb.append("\",");
                        sb.append(strOptString2);
                        sb.append("\nbolttv://");
                        sb.append(strOptString);
                        sb.append('\n');
                    }
                }
            }
            return sb.toString();
        }
        return sb.toString();
    }

    public static boolean b(String str) {
        if (str == null) {
            return false;
        }
        String strTrim = str.trim();
        return strTrim.equals("/api/bolttv/tv/all") || strTrim.contains("bolttv/tv/all") || strTrim.equalsIgnoreCase("bolttv") || strTrim.startsWith("https://apiapi.thaiiptvfree.download/api/bolttv/tv/all");
    }

    public static boolean c(String str) {
        if (str == null) {
            return false;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase.startsWith("bolttv://") || lowerCase.contains("cdn-edge-sg3.retromovie.tv");
    }

    public static String d(String str) throws IOException {
        String string = "/";
        if (str != null && !str.isEmpty()) {
            if (!str.startsWith("/")) {
                str = "/".concat(str);
            }
            if (str.contains("deviceid=")) {
                string = str;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(str.contains("?") ? "&" : "?");
                sb.append("deviceid=b20942e24f3e41f1a7c65374416f1d6cb13ca943ec6809e49d945fb8ec6b2bf2");
                string = sb.toString();
            }
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        StringBuilder sb2 = new StringBuilder("{\"body\":null,\"method\":\"GET\",\"time\":");
        sb2.append(System.currentTimeMillis() / 1000);
        sb2.append(",\"url\":\"");
        String string2 = "";
        sb2.append(string == null ? "" : string.replace("\\", "\\\\").replace("\"", "\\\""));
        sb2.append("\",\"x-app-deviceid\":\"b20942e24f3e41f1a7c65374416f1d6cb13ca943ec6809e49d945fb8ec6b2bf2\",\"x-app-os\":\"android\",\"x-app-signature\":\"a13b05bb72d104514ed808fcdf50fe2e24f5f3ec5589c83d1a21916bdc085007\",\"x-app-version\":\"1\"}");
        String string3 = sb2.toString();
        Log.d("BoltTvClient", "crypto (encrypt+hmac) took " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms");
        String strB = p0.b(string3);
        String strG = p0.g(string3);
        StringBuilder sb3 = new StringBuilder("{\"data\":\"");
        sb3.append(strB == null ? "" : strB.replace("\\", "\\\\").replace("\"", "\\\""));
        sb3.append("\"}");
        String string4 = sb3.toString();
        Charset charset = StandardCharsets.UTF_8;
        byte[] bytes = string4.getBytes(charset);
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://apiapi.thaiiptvfree.download/url/app").openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(8000);
        httpURLConnection.setReadTimeout(8000);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("User-Agent", "AppAllTV/1.0 SecureProxy/1.0");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("signature", strG);
        OutputStream outputStream = httpURLConnection.getOutputStream();
        try {
            outputStream.write(bytes);
            outputStream.close();
            Log.d("BoltTvClient", "http connect+write took " + (System.currentTimeMillis() - jCurrentTimeMillis2) + "ms");
            try {
                InputStream errorStream = httpURLConnection.getResponseCode() >= 400 ? httpURLConnection.getErrorStream() : httpURLConnection.getInputStream();
                if (errorStream != null) {
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, charset));
                        StringBuilder sb4 = new StringBuilder();
                        while (true) {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                break;
                            }
                            sb4.append(line);
                            sb4.append('\n');
                        }
                        string2 = sb4.toString();
                    } finally {
                    }
                }
                if (!"true".equalsIgnoreCase(httpURLConnection.getHeaderField("x-encrypted"))) {
                    if (errorStream != null) {
                        errorStream.close();
                    }
                    httpURLConnection.disconnect();
                    return string2;
                }
                String strA = p0.a(string2);
                if (errorStream != null) {
                    errorStream.close();
                }
                httpURLConnection.disconnect();
                return strA;
            } catch (Throwable th) {
                httpURLConnection.disconnect();
                throw th;
            }
        } catch (Throwable th2) {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Throwable th3) {
                    th2.addSuppressed(th3);
                }
            }
            throw th2;
        }
    }
}