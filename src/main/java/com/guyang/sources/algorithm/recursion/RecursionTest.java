package com.guyang.sources.algorithm.recursion;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 递归算法练习
 * @date 2019-06-23 19:04
 */
public class RecursionTest {

    /**
     * 使用递归计算数组所有元素之和
     */
    public int arraySum(List<Integer> nums) {
        Objects.requireNonNull(nums);
        int sum = 0;
        if (nums.size() == 0) {
            return sum;
        }
        if (nums.size() == 1) {
            sum = nums.get(0);
        } else {
            sum += nums.get(0);
            nums.remove(0);
            sum += arraySum(nums);
        }
        return sum;
    }

    /**
     * 找出列表中最大的数字
     *
     * @param nums
     * @return
     */
    public int arrayMax(int max, List<Integer> nums) {
        Objects.requireNonNull(nums);
        if (nums.size() == 1) {
            max = Math.max(max,nums.get(0));
        } else {
            max = Math.max(max,nums.get(0));
            nums.remove(0);
            return arrayMax(max,nums);
        }
        return max;
    }


    @Test
    public void execute() {
        LinkedList<Integer> integers = new LinkedList<>();
        integers.addAll(Arrays.asList(new Integer[]{2, 3, 4, 6, 7, 5}));
        int i = arrayMax(0,integers);

        System.out.println(i);

    }

}
