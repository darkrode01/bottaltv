package L0;

import B.C0024k;
import android.util.Base64;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public abstract class p0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f2618a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final ConcurrentHashMap f2619b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final SecureRandom f2620c;

    static {
        Charset charset = StandardCharsets.UTF_8;
        f2618a = e("appalltv_shared".getBytes(charset), "appalltv_v1_fixed".getBytes(charset));
        f2619b = new ConcurrentHashMap();
        f2620c = new SecureRandom();
    }

    public static String a(String str) {
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(Base64.decode(str, 0)).order(ByteOrder.BIG_ENDIAN);
        long j2 = byteBufferOrder.getLong();
        byte[] bArr = new byte[24];
        byteBufferOrder.get(bArr);
        byte[] bArr2 = new byte[byteBufferOrder.remaining()];
        byteBufferOrder.get(bArr2);
        String str2 = "fixed|" + j2;
        Charset charset = StandardCharsets.UTF_8;
        return new String(h(false, bArr2, str2.getBytes(charset), bArr, f2618a), charset);
    }

    public static String b(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        byte[] bArr = new byte[24];
        f2620c.nextBytes(bArr);
        Charset charset = StandardCharsets.UTF_8;
        byte[] bArrH = h(true, str.getBytes(charset), ("fixed|" + jCurrentTimeMillis).getBytes(charset), bArr, f2618a);
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(32 + bArrH.length).order(ByteOrder.BIG_ENDIAN);
        byteBufferOrder.putLong(jCurrentTimeMillis);
        byteBufferOrder.put(bArr);
        byteBufferOrder.put(bArrH);
        return Base64.encodeToString(byteBufferOrder.array(), 2);
    }

    public static void c(byte[] bArr, int i, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    public static int d(int i, byte[] bArr) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static byte[] e(byte[] bArr, byte[] bArr2) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        A.u uVar;
        A.u uVar2;
        int i6;
        long j2;
        int i7;
        int i8;
        int i9;
        int i10 = 6;
        int i11 = 3;
        int i12 = 1;
        l1.a aVar = new l1.a(128);
        aVar.e(bArr2.length, bArr2);
        byte[] bArr3 = new byte[16];
        int i13 = 0;
        aVar.c(0, bArr3);
        byte[] bArrB = r1.a.b(r1.a.b(bArr3));
        int iMax = Math.max(65536, 1 * 8) / (1 * 4);
        int i14 = iMax * 4;
        A.u[] uVarArr = new A.u[1 * i14];
        for (int i15 = 0; i15 < uVarArr.length; i15++) {
            uVarArr[i15] = new A.u(27);
        }
        byte[] bArr4 = new byte[32];
        byte[] bArr5 = new byte[1024];
        l1.a aVar2 = new l1.a(512);
        int[] iArr = {1, 32, 65536, 3, 19, 2};
        int i16 = 4;
        int i17 = 0;
        int i18 = 0;
        while (i17 < i10) {
            r1.a.g(bArr5, iArr[i17], i18);
            i18 += 4;
            i17++;
            i10 = i10;
        }
        int i19 = i10;
        aVar2.e(24, bArr5);
        n1.a.b(bArr5, aVar2, bArr);
        n1.a.b(bArr5, aVar2, r1.a.b(bArrB));
        n1.a.b(bArr5, aVar2, r1.a.b(null));
        n1.a.b(bArr5, aVar2, r1.a.b(null));
        byte[] bArr6 = new byte[72];
        aVar2.c(0, bArr6);
        byte[] bArr7 = new byte[72];
        System.arraycopy(bArr6, 0, bArr7, 0, 64);
        bArr7[64] = 1;
        int i20 = 0;
        while (i20 < 1) {
            r1.a.g(bArr6, i20, 68);
            r1.a.g(bArr7, i20, 68);
            n1.a.c(bArr6, bArr5, 1024);
            A.u uVar3 = uVarArr[i20 * i14];
            uVar3.getClass();
            long[] jArr = (long[]) uVar3.f88f;
            int i21 = i13;
            int i22 = i21;
            for (int i23 = i22; i23 < jArr.length; i23++) {
                jArr[i23] = r1.a.i(i22, bArr5);
                i22 += 8;
            }
            n1.a.c(bArr7, bArr5, 1024);
            A.u uVar4 = uVarArr[(i20 * i14) + 1];
            uVar4.getClass();
            long[] jArr2 = (long[]) uVar4.f88f;
            int i24 = i21;
            int i25 = i24;
            while (i24 < jArr2.length) {
                jArr2[i24] = r1.a.i(i25, bArr5);
                i25 += 8;
                i24++;
            }
            i20++;
            i13 = i21;
        }
        int i26 = i13;
        B.J j3 = new B.J();
        j3.f451a = new A.u(27);
        j3.f452b = new A.u(27);
        j3.f453c = new A.u(27);
        j3.f454d = new A.u(27);
        int i27 = i26;
        while (true) {
            long j4 = 0;
            if (i27 >= i11) {
                break;
            }
            int i28 = i26;
            while (true) {
                i = i16;
                if (i28 < i) {
                    int i29 = i26;
                    while (i29 < i12) {
                        int i30 = (i27 != 0 || i28 >= 2) ? i26 : i12;
                        int i31 = (i27 == 0 && i28 == 0) ? 2 : i26;
                        int i32 = (i28 * iMax) + (i29 * i14) + i31;
                        int i33 = i32 % i14 == 0 ? (i32 + i14) - i12 : i32 - 1;
                        if (i30 != 0) {
                            i5 = i12;
                            uVar2 = (A.u) j3.f453c;
                            int i34 = i11;
                            Arrays.fill((long[]) uVar2.f88f, j4);
                            uVar = (A.u) j3.f454d;
                            i4 = i30;
                            Arrays.fill((long[]) uVar.f88f, j4);
                            long[] jArr3 = (long[]) uVar.f88f;
                            jArr3[i26] = ((long) i27) & 4294967295L;
                            jArr3[i5] = ((long) i29) & 4294967295L;
                            jArr3[2] = ((long) i28) & 4294967295L;
                            jArr3[i34] = ((long) uVarArr.length) & 4294967295L;
                            i3 = i28;
                            jArr3[4] = ((long) i34) & 4294967295L;
                            i2 = i27;
                            jArr3[5] = ((long) 2) & 4294967295L;
                            if (i2 == 0 && i3 == 0) {
                                jArr3[i19] = jArr3[i19] + 1;
                                A.u uVar5 = (A.u) j3.f452b;
                                uVar5.getClass();
                                int i35 = i26;
                                System.arraycopy(jArr3, i35, (long[]) uVar5.f88f, i35, 128);
                                j3.e();
                                A.u.P(uVar2, uVar, uVar5);
                                A.u uVar6 = (A.u) j3.f452b;
                                uVar6.getClass();
                                System.arraycopy((long[]) uVar2.f88f, 0, (long[]) uVar6.f88f, 0, 128);
                                j3.e();
                                A.u.P(uVar2, uVar2, uVar6);
                            }
                        } else {
                            i2 = i27;
                            i3 = i28;
                            i4 = i30;
                            i5 = i12;
                            uVar = null;
                            uVar2 = null;
                        }
                        int i36 = i2 != 0 ? i5 : 0;
                        while (i31 < iMax) {
                            if (i4 != 0) {
                                int i37 = i31 % 128;
                                if (i37 == 0) {
                                    long[] jArr4 = (long[]) uVar.f88f;
                                    jArr4[i19] = jArr4[i19] + 1;
                                    A.u uVar7 = (A.u) j3.f452b;
                                    uVar7.getClass();
                                    i6 = i36;
                                    i9 = i37;
                                    System.arraycopy((long[]) uVar.f88f, 0, (long[]) uVar7.f88f, 0, 128);
                                    j3.e();
                                    A.u.P(uVar2, uVar, uVar7);
                                    A.u uVar8 = (A.u) j3.f452b;
                                    uVar8.getClass();
                                    System.arraycopy((long[]) uVar2.f88f, 0, (long[]) uVar8.f88f, 0, 128);
                                    j3.e();
                                    A.u.P(uVar2, uVar2, uVar8);
                                } else {
                                    i6 = i36;
                                    i9 = i37;
                                }
                                j2 = ((long[]) uVar2.f88f)[i9];
                            } else {
                                i6 = i36;
                                j2 = ((long[]) uVarArr[i33].f88f)[0];
                            }
                            int i38 = (int) ((j2 >>> 32) % ((long) i5));
                            if (i2 == 0 && i3 == 0) {
                                i38 = i29;
                            }
                            boolean z2 = i38 == i29;
                            if (i2 == 0) {
                                i7 = z2 ? ((i3 * iMax) + i31) - 1 : (i3 * iMax) + (i31 != 0 ? 0 : -1);
                                i8 = 0;
                            } else {
                                int i39 = ((i3 + 1) * iMax) % i14;
                                i7 = z2 ? ((i14 - iMax) + i31) - 1 : (i14 - iMax) + (i31 != 0 ? 0 : -1);
                                i8 = i39;
                            }
                            long j5 = j2 & 4294967295L;
                            int i40 = i38;
                            A.u uVar9 = uVar;
                            int i41 = ((int) (((long) i8) + (((long) (i7 - 1)) - ((((long) i7) * ((j5 * j5) >>> 32)) >>> 32)))) % i14;
                            A.u uVar10 = uVarArr[i33];
                            A.u uVar11 = uVarArr[(i14 * i40) + i41];
                            A.u uVar12 = uVarArr[i32];
                            if (i6 != 0) {
                                A.u uVar13 = (A.u) j3.f451a;
                                A.u.P(uVar13, uVar10, uVar11);
                                A.u uVar14 = (A.u) j3.f452b;
                                uVar14.getClass();
                                long[] jArr5 = (long[]) uVar13.f88f;
                                long[] jArr6 = (long[]) uVar14.f88f;
                                System.arraycopy(jArr5, 0, jArr6, 0, 128);
                                j3.e();
                                uVar12.getClass();
                                int i42 = 0;
                                for (int i43 = 128; i42 < i43; i43 = 128) {
                                    long[] jArr7 = (long[]) uVar12.f88f;
                                    jArr7[i42] = jArr7[i42] ^ (jArr5[i42] ^ jArr6[i42]);
                                    i42++;
                                }
                            } else {
                                A.u uVar15 = (A.u) j3.f451a;
                                A.u.P(uVar15, uVar10, uVar11);
                                A.u uVar16 = (A.u) j3.f452b;
                                uVar16.getClass();
                                System.arraycopy((long[]) uVar15.f88f, 0, (long[]) uVar16.f88f, 0, 128);
                                j3.e();
                                A.u.P(uVar12, uVar15, uVar16);
                            }
                            i31++;
                            i5 = 1;
                            i33 = i32;
                            i36 = i6;
                            uVar = uVar9;
                            i32++;
                        }
                        int i44 = i5;
                        i29 += i44;
                        i12 = i44;
                        i26 = 0;
                        i27 = i2;
                        i28 = i3;
                        i11 = 3;
                        j4 = 0;
                    }
                    i16 = 4;
                    i28++;
                    i27 = i27;
                    i11 = 3;
                    j4 = 0;
                }
            }
            i16 = i;
            i27++;
            i11 = 3;
        }
        int i45 = i26;
        int i46 = i12;
        A.u uVar17 = uVarArr[i14 - 1];
        int i47 = i46;
        while (i47 < i46) {
            A.u uVar18 = uVarArr[(i14 - 1) + (i47 * i14)];
            uVar17.getClass();
            long[] jArr8 = (long[]) uVar18.f88f;
            for (int i48 = i45; i48 < 128; i48++) {
                long[] jArr9 = (long[]) uVar17.f88f;
                jArr9[i48] = jArr9[i48] ^ jArr8[i48];
            }
            i47++;
            i46 = 1;
        }
        uVar17.getClass();
        int i49 = i45;
        int i50 = i49;
        while (true) {
            long[] jArr10 = (long[]) uVar17.f88f;
            if (i49 >= jArr10.length) {
                break;
            }
            r1.a.j(jArr10[i49], bArr5, i50);
            i50 += 8;
            i49++;
        }
        n1.a.c(bArr5, bArr4, 32);
        for (int i51 = i45; i51 < uVarArr.length; i51++) {
            A.u uVar19 = uVarArr[i51];
            if (uVar19 != null) {
                Arrays.fill((long[]) uVar19.f88f, 0L);
            }
        }
        return bArr4;
    }

    public static void f(int i, int i2, int i3, int i4, int[] iArr) {
        int i5 = iArr[i] + iArr[i2];
        iArr[i] = i5;
        int iRotateLeft = Integer.rotateLeft(i5 ^ iArr[i4], 16);
        iArr[i4] = iRotateLeft;
        int i6 = iArr[i3] + iRotateLeft;
        iArr[i3] = i6;
        int iRotateLeft2 = Integer.rotateLeft(iArr[i2] ^ i6, 12);
        iArr[i2] = iRotateLeft2;
        int i7 = iArr[i] + iRotateLeft2;
        iArr[i] = i7;
        int iRotateLeft3 = Integer.rotateLeft(iArr[i4] ^ i7, 8);
        iArr[i4] = iRotateLeft3;
        int i8 = iArr[i3] + iRotateLeft3;
        iArr[i3] = i8;
        iArr[i2] = Integer.rotateLeft(iArr[i2] ^ i8, 7);
    }

    public static String g(String str) {
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec((byte[]) f2619b.computeIfAbsent("b20942e24f3e41f1a7c65374416f1d6cb13ca943ec6809e49d945fb8ec6b2bf2", new C0024k(1)), "HmacSHA256"));
        byte[] bArrDoFinal = mac.doFinal(str.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder(bArrDoFinal.length * 2);
        for (byte b2 : bArrDoFinal) {
            sb.append(String.format("%02x", Integer.valueOf(b2 & 255)));
        }
        return sb.toString();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x039f  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0281  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] h(boolean r27, byte[] r28, byte[] r29, byte[] r30, byte[] r31) throws B.C {
        /*
            Method dump skipped, instruction units count: 1076
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: L0.p0.h(boolean, byte[], byte[], byte[], byte[]):byte[]");
    }
}