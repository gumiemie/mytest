package com.guyang.sources.jvm;

import com.guyang.sources.jvm.utils.U2;
import com.guyang.sources.jvm.utils.U4;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-07-19 14:06
 */
public class JvmTest {

    public static void main(String[] args) {
        try {
            File file = new File("/Users/guyang/IdeaProjects/mytest/target/classes/AreaTest.class");
            FileInputStream fileInputStream = new FileInputStream(file);
            System.out.println(U4.readStream(fileInputStream));
            System.out.println(U2.readStream(fileInputStream));
            System.out.println(U2.readStream(fileInputStream));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
