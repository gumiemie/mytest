package com.guyang.sources.jvm.utils;

import java.io.InputStream;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-07-17 16:20
 */
public class U2 {

    public static int readStream(InputStream inputStream) {
        byte[] bytes = new byte[2];
        int result = 0;
        try {
            inputStream.read(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < bytes.length; i++) {
            result <<= 8;
            result |= bytes[i] & 0xFF;
        }

        return result;
    }

}
