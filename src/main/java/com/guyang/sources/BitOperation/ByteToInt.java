package com.guyang.sources.BitOperation;

import org.junit.Test;

/**
 * @author guyang <guyang@ebnew.com>
 * @description byte数组与int互转
 * @date 2019-07-17 11:54
 */
public class ByteToInt {

    private int byteToInt(byte[] bytes) {
        int result = 0;
        for (int i = bytes.length - 1; i >= 0; i--) {
            result |= (bytes[i] & 0xFF) << (i * 8);
        }
        System.out.println(result);
        return result;
    }

    @Test
    public void execute() {
        byte[] bytes = new byte[]{-1, 3, 4, 5};
        int i = byteToInt(bytes);
        byte[] bytes1 = intToByte(i);
        int x = 1919191;
        byte[] bytes2 = intToByte(x);
        int i1 = byteToInt(bytes2);
        System.out.print(i);
    }

    private byte[] intToByte(int value) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (value >>> (i * 8));
        }
        return bytes;
    }


}
