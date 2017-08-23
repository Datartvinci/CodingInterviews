package leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Datartvinci on 2017/6/2.
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class ThreeSum {

    @Test
    public void testThreeSum() {
//        List<List<Integer>> list = threeSum(new int[]{-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0});
        List<List<Integer>> list = threeSum(new int[]{2,0,-2,-5,-5,-3,2,-4});

        list.forEach(l ->
        {
            l.forEach(System.out::print);
            System.out.println();
        });
    }

    public List<List<Integer>> threeSum(int[] nums) {
        //先排序
        Arrays.sort(nums);
        //先来个暴力的循环方法
        List<List<Integer>> result = new LinkedList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        int left = 0;
        int mid = 0;
        int right = 0;
        //这个下标，考虑不到0，0，0的情况，如果0的下标在中点以后
        for (int i = 0; i < nums.length -2; ++i) {

            //避免重复的结果，因为当前项和前一项相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            left = i;
            mid = i + 1;
            right = nums.length - 1;
            //三种情况:l+m+r>0,l+m+r=0,l+m+r<0
            //以left作为遍历的点，固定left，递增mid和递减right
            //为了找出所有可能，遍历所有mid<right的情况
            while (mid < right) {
                if (nums[left] + nums[right] + nums[mid] == 0) {
                    //1.l+m+r=0,这就是答案了，添加到结果列表
                    result.add(Arrays.asList(nums[left], nums[mid], nums[right]));
                    //同时循环增加重复的mid以及减少重复的right下标，避免重复结果
                    //mid下标的后一项和当前相同，则下次比较中忽略掉后一项，因此下标加1
                    while (mid < right && nums[mid] == nums[mid + 1]) {
                        mid++;
                    }
                    //right下标的前一项和当前相同，则下次比较中忽略掉前一项，因此下标减1
                    while (mid < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    mid++;
                    right--;
                } else if (nums[left] + nums[right] + nums[mid] < 0) {
                    //2.l+m+r<0,说明mid项小了，需要增大
                    mid++;
                } else {
                    //3.l+m+r>0,说明right项大了，需要减小
                    right--;
                }
            }
        }

        return result;
    }
}
