package F;

import android.net.Uri;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import x.InterfaceC0424A;

/* JADX INFO: loaded from: classes.dex */
public final class a implements x.h {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final x.h f1284f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final byte[] f1285g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final byte[] f1286h;
    public CipherInputStream i;

    public a(x.h hVar, byte[] bArr, byte[] bArr2) {
        this.f1284f = hVar;
        this.f1285g = bArr;
        this.f1286h = bArr2;
    }

    @Override // x.h
    public final long b(x.k kVar) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            try {
                cipher.init(2, new SecretKeySpec(this.f1285g, "AES"), new IvParameterSpec(this.f1286h));
                x.j jVar = new x.j(this.f1284f, kVar);
                this.i = new CipherInputStream(jVar, cipher);
                jVar.t();
                return -1L;
            } catch (InvalidAlgorithmParameterException | InvalidKeyException e2) {
                throw new RuntimeException(e2);
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e3) {
            throw new RuntimeException(e3);
        }
    }

    @Override // x.h
    public final void close() {
        if (this.i != null) {
            this.i = null;
            this.f1284f.close();
        }
    }

    @Override // x.h
    public final void f(InterfaceC0424A interfaceC0424A) {
        interfaceC0424A.getClass();
        this.f1284f.f(interfaceC0424A);
    }

    @Override // x.h
    public final Uri i() {
        return this.f1284f.i();
    }

    @Override // x.h
    public final Map n() {
        return this.f1284f.n();
    }

    @Override // s.InterfaceC0360j
    public final int s(byte[] bArr, int i, int i2) throws IOException {
        this.i.getClass();
        int i3 = this.i.read(bArr, i, i2);
        if (i3 < 0) {
            return -1;
        }
        return i3;
    }
}