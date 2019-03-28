package com.guyang.sources.io;

import org.junit.Test;

/**
 * @author 顾洋 <guyang@ebnew.com>
 * @description 字符编码解码
 * @date 2018/3/19 17:14$
 */
public class CharSetEncoding {

    @Test
    public void test()throws Exception{
        char c = 'c';

        //字符串，在解编为字节时其指定iso-8859-1的charSet不支持中文，所以会输出？？,其它同理
        String  s= "i im 顾洋";
        System.out.println(s);
        byte[] iso8859 = s.getBytes("iso-8859-1");
        System.out.println(iso8859);
        byte[] gb2312 = s.getBytes("gb2312");
        System.out.println(new String(gb2312));
        byte[] gbk = s.getBytes("gbk");
        System.out.println(new String(gbk));
        byte[] utf8 = s.getBytes("utf-8");
        System.out.println(new String(utf8));

        Character a='c';
    }

}
