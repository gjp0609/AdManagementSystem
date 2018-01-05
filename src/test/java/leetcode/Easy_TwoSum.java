package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/articles/two-sum/">leetcode.com/articles/two-sum/</a>
 * <p>
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * <p>
 * 给定一个整数数组，返回两个数字的索引，使它们加起来的值为给定的数。
 * 您可以认为每个输入都只有一个解决方案，不能使用相同的元素两次。
 * <p>
 * eg: Given nums = [2, 7, 11, 15], target = 9, Because nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
 *
 * @author guojinpeng
 * @date 18.1.4 17:43
 */
public class Easy_TwoSum {

    int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int num1 = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                int num2 = nums[j];
                if (num1 + num2 == target) return new int[]{i, j};
            }
        }
        return new int[]{};
    }

    int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        return new int[]{};
    }

    int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{};
    }

    public static void main(String[] args) {
        Easy_TwoSum twoSum = new Easy_TwoSum();
        int length = 200000;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        arr[length - 99999] = 150;
        arr[100000] = 150;

        int target = 300;
        long millis;

        millis = System.currentTimeMillis();
        int[] ints = new int[]{0, 1};
        System.out.println(System.currentTimeMillis() - millis + ": " + Arrays.toString(ints));

        millis = System.currentTimeMillis();
        ints = twoSum.twoSum1(arr, target);
        System.out.println(System.currentTimeMillis() - millis + ": " + Arrays.toString(ints));

        millis = System.currentTimeMillis();
        ints = twoSum.twoSum2(arr, target);
        System.out.println(System.currentTimeMillis() - millis + ": " + Arrays.toString(ints));

        millis = System.currentTimeMillis();
        ints = twoSum.twoSum3(arr, target);
        System.out.println(System.currentTimeMillis() - millis + ": " + Arrays.toString(ints));
    }
}
