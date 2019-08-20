package com.guyang.sources.jvm.utils;

import java.io.InputStream;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-07-17 17:22
 */
public class U4 {


    public static long readStream(InputStream inputStream) {
        long result = 0;
        byte[] bytes = new byte[4];
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
