package com.guyang.sources.BitOperation;

import org.junit.Test;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 位运算
 * 原码,反码,补码.计算机中的运算使用的都是补码，正数的补码是他自己，负数的补码是反码+1，就是符号位不变，其它位取反再加1
 * @date 2018/3/20 16:56$
 */
public class BitOperationTest {
    int a = 3;
    int b = -4;
    byte c = -13;
    byte d = 14;

    @Test
    public void test() {
        /**
         * b的原码: 10000000 00000000 00000000 00000100
         * b的反码: 11111111 11111111 11111111 11111011
         * b的补码: 11111111 11111111 11111111 11111100
         * a原反补: 00000000 00000000 00000000 00000011
         * a+b的补: 11111111 11111111 11111111 11111111
         * 这个时候,符号为1,是负数,要取到原码
         * a+b的反: 11111111 11111111 11111111 11111110
         * a+b的原: 10000000 00000000 00000000 00000001
         * 结果就是-1
         */

        //s的值是b的补码
        String s = Integer.toBinaryString(b);
        System.out.println(s);


        /**
         * a原反补: 00000000 00000000 00000000 00000011
         * 右移,去掉右侧值,左侧补0:000 00000000 00000000 00000000 00000
         * 左移,去掉左侧值,右侧补0:00000 00000000 00000000 00000011 000
         */
        System.out.println(a >> 3);
        System.out.println(a << 3);

        /**
         * b的原码: 10000000 00000000 00000000 00000100
         * b的反码: 11111111 11111111 11111111 11111011
         * b的补码: 11111111 11111111 11111111 11111100
         * 右移>补码:11111111 11111111 11111111 11111111
         * 获取反码: 11111111 11111111 11111111 11111110
         * 获取原码: 10000000 00000000 00000000 00000001
         * 结果是-1
         * 左移>补码:11111 11111111 11111111 11111100 000
         * 获取反码: 11111 11111111 11111111 11111011 111
         * 获取反码: 10000 00000000 00000000 00000100 000
         * 结果是-32
         */
        System.out.println(b >> 3);
        System.out.println(b << 3);


        /**
         * 正数的无符号右移与右移结果是一致的,因为左侧都会补0
         * 负数的无符号右移会变成正数
         * b的原码: 10000000 00000000 00000000 00000100
         * b的反码: 11111111 11111111 11111111 11111011
         * b的补码: 11111111 11111111 11111111 11111100
         * 无符合右移: 000 11111111 11111111 11111111 11111
         * 反码原码不变:结果是536870911
         */
        System.out.println(a >>> 3);
        System.out.println(b >>> 3);
    }

    @Test
    public void execute() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        System.out.println(classLoader);
        ClassLoader parent = classLoader.getParent();
        System.out.println(parent);
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);

        try {
            classLoader.loadClass("com.guyang.sources.jvm.utils.U1");
            System.out.println("-----------------------------------");
            //Class.forName("com.guyang.sources.jvm.utils.U1");
            System.out.println("-----------------------------------");
            Class.forName("com.guyang.sources.jvm.utils.U1",true,classLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
