package dev.muin.backend.util;

import org.apache.commons.codec.binary.Base32;

public class Base32Util {
    private static Base32 base32 = new Base32();

    public static String encode(String target){
        return base32.encodeAsString(target.getBytes());
    }
    public static String decode(String target) {
        return new String(base32.decode(target));
    }
}
