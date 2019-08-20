package com.guyang.sources.algorithm.arraySort;

import org.junit.Test;

import java.util.*;

/**
 * @author guyang <guyang@ebnew.com>
 * @description 数组排序各种算法实现
 * @date 2019-06-23 13:34
 */
public class SortTest {

    /**
     * 选择排序 每次选择最大的一个，每次用时为O(n) 需要执行n 次这样的操作，总用时为 O(n*n)
     */
    @Test
    public void execute1() {
        ArraySortUtil arraySortUtil = (list) -> {
            Objects.requireNonNull(list);
            LinkedList linkedList = new LinkedList();
            int size = list.size();
            while (linkedList.size() != size) {
                int tempIndex = 0;
                int temp = 0;

                for (int i = 0; i < list.size(); i++) {
                    Integer integer = list.get(i);
                    if (temp < integer) {
                        temp = integer;
                        tempIndex = i;
                    }
                }
                list.remove(tempIndex);
                linkedList.add(temp);
            }
            return linkedList;
        };

        List<Integer> integers = new ArrayList<>();
        Integer[] ins = new Integer[]{1, 2, 54, 233, 214, 35, 46};
        integers.addAll(Arrays.asList(ins));
        List sort = arraySortUtil.sort(integers);
        System.out.println(sort);
    }

    @Test
    public void test() {
        int[] params = new int[]{30, 2, 10, 9, 23, 75, 20, 55, 3};
//        bubbleSort(params);
//        selectSort(params);
//        insertSort(params);

        System.out.println(params);
    }


    /**
     * 冒泡排序 如果当前元素大于下一个元素，则互换
     * 嵌套循环 O(n*n) 每次内循环都会将最大的数放到最后。
     *
     * @param nums
     */
    private void bubbleSort(int[] nums) {
        for (int y = 0; y < nums.length; y++) {
            for (int x = 0; x < nums.length - y - 1; x++) {
                if (nums[x] > nums[x + 1]) {
                    nums[x] = nums[x] ^ nums[x + 1];
                    nums[x + 1] = nums[x] ^ nums[x + 1];
                    nums[x] = nums[x] ^ nums[x + 1];
                }
            }
        }
    }


    /**
     * 选择排序 每次遍历选择最大（小）的数 放到first or last
     * 时间复杂度 O(n*n)
     *
     * @param nums
     */
    private void selectSort(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int temp = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[temp] > nums[j]) {
                    temp = j;
                }
            }
            nums[i] = nums[i] ^ nums[temp];
            nums[temp] = nums[i] ^ nums[temp];
            nums[i] = nums[i] ^ nums[temp];
        }
    }

    /**
     * 插入排序
     * hypothesis the first element is a sorted array;
     * traverse second to last elements , then insert to the sorted array
     * O(n*n)
     */
    private void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int j = i - 1;
            for (; j >= 0 && nums[j] > temp; j--) {
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = temp;
        }
    }



}

