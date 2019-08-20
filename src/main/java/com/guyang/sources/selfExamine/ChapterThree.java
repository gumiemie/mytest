package com.guyang.sources.selfExamine;

import org.junit.Test;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 第三章 练习题
 * @date 2019-08-11 14:51
 */
public class ChapterThree {

    @Test
    public void question4() {
        //行数
        int rows = 9;
        //前面/后面 空格的个数
        int y = 0;
        for (int i = 0; i < rows; i++) {
            int temp = y;
            int x = rows - (y << 1);
            while (temp > 0) {
                System.out.print(" ");
                temp--;
            }
            while (x > 0) {
                System.out.print("*");
                x--;
            }
            while (temp < y) {
                System.out.print(" ");
                temp++;
            }
            System.out.println();
            if (i < rows >> 1) {
                y++;
            } else {
                y--;
            }
        }

    }


}
