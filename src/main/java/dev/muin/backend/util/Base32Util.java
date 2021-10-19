package dev.muin.backend.util;

import org.apache.commons.codec.binary.Base32;
import org.springframework.stereotype.Component;

// INFO: 빈등록하지 않으면 초기화되지 않는다.
@Component
public class Base32Util {

    private static Base32 base32;

    public Base32Util(){
        base32 = new Base32();
    }

    public static String encode(String target){
        return base32.encodeAsString(target.getBytes());
    }

    public static String decode(String target) {
        return new String(base32.decode(target));
    }
}
