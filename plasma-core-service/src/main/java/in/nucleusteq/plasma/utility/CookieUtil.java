package in.nucleusteq.plasma.utility;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Utility class for handling cookies in a Servlet environment.
 */
@Component
public class CookieUtil {

    /**
     * secret key.
     */
    private static final String secretKey = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    /**
     * int Vector.
     */
    private static final String initVector = "9C5A1DF2518F949D";

    /**
     * Creates a new cookie with the specified parameters and adds it to the HTTP response.
     * @param httpServletResponse The HTTP response to which the cookie will be added.
     * @param name                 The name of the cookie.
     * @param value                The value of the cookie.
     * @param secure               Indicates whether the cookie should only be sent over HTTPS.
     * @param maxAge               The maximum age of the cookie in seconds.
     * @param domain               The domain to which the cookie belongs.
     */
    public static void create(HttpServletResponse httpServletResponse, String name, String value, Boolean secure,
            Integer maxAge, String domain) {

        Cookie cookie = new Cookie(name, value);
        cookie.setSecure(secure);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(domain);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
    }

    /**
     * Clears the cookie with the specified name by setting its value to null and maximum age to 1 second.
     * @param httpServletResponse The HTTP response to which the cookie will be added.
     * @param name                 The name of the cookie to be cleared.
     */
    public static void clear(HttpServletResponse httpServletResponse, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(1);
        cookie.setDomain("localhost");
        httpServletResponse.addCookie(cookie);
    }

    /**
     * Decrypts an encrypted value using AES encryption.
     * @param encryptedValue The encrypted value to decrypt.
     * @return The decrypted value, or null if decryption fails.
     */
    public static String decrypt(String encryptedValue) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encryptedValue));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Encrypts a value using AES encryption.
     * @param value The value to encrypt.
     * @return The encrypted value, or null if encryption fails.
     */
    private static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
