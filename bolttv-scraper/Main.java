import L0.C0114c;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting BoltTV Scraper...");
        try {
            // เรียกฟังก์ชันสร้าง M3U
            String m3uPlaylist = C0114c.a();
            
            // เขียนข้อมูลลงไฟล์ playlist.m3u
            FileWriter writer = new FileWriter("playlist.m3u");
            writer.write(m3uPlaylist);
            writer.close();
            
            System.out.println("Successfully generated playlist.m3u");
        } catch (Exception e) {
            System.err.println("Error generating playlist:");
            e.printStackTrace();
            System.exit(1); // ส่งสัญญาณบอก GitHub ว่าทำงานไม่สำเร็จ
        }
    }
}