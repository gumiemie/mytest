package com.guyang.sources.jvm.utils;

import java.io.FileInputStream;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-07-17 10:40
 */
public class U1 {

    static{
        System.out.println("类已经加载,且已经解析。");
    }

    public static short readStream(FileInputStream fis) {

        byte[] bytes = new byte[1];

        try {
            fis.read(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        short result = (short) (bytes[0] & 0xFF);

        return result;
    }

}
