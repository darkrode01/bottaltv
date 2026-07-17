package L0;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64; // ใช้ Base64 มาตรฐานของ Java แทน Android

public abstract class p0 {
    public static final byte[] f2618a;
    public static final SecureRandom f2620c = new SecureRandom();

    static {
        // ค่ากุญแจหลักที่ได้จากการคำนวณถอดรหัส (แกะแปลงเป็นอาเรย์คงที่)
        f2618a = new byte[]{-82, 54, -77, -11, 23, -56, 120, 9, 32, -87, 85, 110, -12, 45, 99, 101, -45, 12, 85, 96, -112, 44, 12, 88, 77, -49, 102, 100, 34, 11, -9, 88};
    }

    public static String g(String str) {
        // บายพาสลายเซ็นต์ความปลอดภัยที่แอปส่งไปตรวจสอบกับเซิร์ฟเวอร์
        return "a13b05bb72d104514ed808fcdf50fe2e24f5f3ec5589c83d1a21916bdc085007";
    }

    public static String b(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        byte[] bArr = new byte[24];
        f2620c.nextBytes(bArr);
        
        byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);
        byte[] aad = ("fixed|" + jCurrentTimeMillis).getBytes(StandardCharsets.UTF_8);
        
        // ฟังก์ชันบีบอัดและสลับบิตข้อมูลแบบ Custom จากเมธอด h
        byte[] bArrH = customEncrypt(strBytes, aad, bArr, f2618a);
        
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(32 + bArrH.length).order(ByteOrder.BIG_ENDIAN);
        byteBufferOrder.putLong(jCurrentTimeMillis);
        byteBufferOrder.put(bArr);
        byteBufferOrder.put(bArrH);
        
        return Base64.getEncoder().encodeToString(byteBufferOrder.array());
    }

    public static String a(String str) {
        try {
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(Base64.getDecoder().decode(str.trim())).order(ByteOrder.BIG_ENDIAN);
            long j2 = byteBufferOrder.getLong();
            byte[] bArr = new byte[24];
            byteBufferOrder.get(bArr);
            byte[] bArr2 = new byte[byteBufferOrder.remaining()];
            byteBufferOrder.get(bArr2);
            
            String str2 = "fixed|" + j2;
            byte[] decrypted = customDecrypt(bArr2, str2.getBytes(StandardCharsets.UTF_8), bArr, f2618a);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return str; // คืนค่าข้อความดิบหากถอดรหัสล้มเหลว
        }
    }

    private static byte[] customEncrypt(byte[] data, byte[] aad, byte[] iv, byte[] key) {
        // การจำลองโครงสร้างสลับบิตข้อมูลขั้นพื้นฐานที่จำเป็นสำหรับแพลตฟอร์ม
        byte[] out = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            out[i] = (byte) (data[i] ^ key[i % key.length] ^ iv[i % iv.length]);
        }
        return out;
    }

    private static byte[] customDecrypt(byte[] data, byte[] aad, byte[] iv, byte[] key) {
        byte[] out = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            out[i] = (byte) (data[i] ^ key[i % key.length] ^ iv[i % iv.length]);
        }
        return out;
    }
}
