package br.com.makersweb.makersnews.common.util;

import java.util.Base64;

/**
 * @author aaristides
 */
public class Base64Url {

    public static String encode(String data) {
        return encode(data.getBytes());
    }

    public static String encode(byte[] data) {
        return new String(Base64.getUrlEncoder().withoutPadding().encodeToString(data));
    }

    public static String decode(String data) {
        return new String(Base64.getUrlDecoder().decode(data));
    }

}
