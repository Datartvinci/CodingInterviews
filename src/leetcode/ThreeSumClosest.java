package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Datartvinci on 2017/8/23.
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * <p>
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {
    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 1, -4};
        int ans = new ThreeSumClosest().threeSumClosest(nums, 1);
        System.out.println(ans);
        new ThreeSumClosest().fourSum(new int[]{0, 0, 0, 0}, 0).forEach(System.out::println);
    }
    @Test
    public void testRemoveDup(){
        System.out.println(removeDuplicates(new int[]{1,1,2}));
    }
    public static int removeDuplicates(int[] nums) {
        int count=0;
        int mask=0;
        for(int i=0;i<nums.length;i++){
            int num=nums[i];
            if(((1<<num)&mask)==0){
                count++;
            }
            mask=mask|(1<<num);
        }
        return count;
    }
    public int threeSumClosest(int[] nums, int target) {
        //先排序
        Arrays.sort(nums);
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int result = nums[0] + nums[1] + nums[nums.length - 1];
        int left = 0;
        int mid = 0;
        int right = 0;
        //这个下标，考虑不到0，0，0的情况，如果0的下标在中点以后
        for (int i = 0; i < nums.length - 2; ++i) {

            //避免重复的结果，因为当前项和前一项相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            left = i;
            mid = i + 1;
            right = nums.length - 1;
            //二种情况:l+m+r>target,l+m+r<=target,找到小的那个差值
            //以left作为遍历的点，固定left，递增mid和递减right
            //为了找出所有可能，遍历所有mid<right的情况
            while (mid < right) {
                int sum = nums[left] + nums[right] + nums[mid];
                if (sum > target) {
                    --right;
                } else {
                    ++mid;
                }
                if (Math.abs(sum - target) < Math.abs(result - target))
                    result = sum;
            }

        }
        return result;
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        //先排序
        Arrays.sort(nums);
        if (nums == null || nums.length < 4) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        int left = 0;
        int mid = 0;
        int right = 0;
        //这个下标，考虑不到0，0，0的情况，如果0的下标在中点以后
        for (int i = 0; i < nums.length - 3; ++i) {

            //避免重复的结果，因为当前项和前一项相等
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; ++j) {
                //避免重复的结果，因为当前项和前一项相等
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                left = j;
                mid = j + 1;
                right = nums.length - 1;
                //二种情况:l+m+r>target,l+m+r<=target,找到小的那个差值
                //以left作为遍历的点，固定left，递增mid和递减right
                //为了找出所有可能，遍历所有mid<right的情况
                while (mid < right) {
                    int sum = nums[i] + nums[left] + nums[right] + nums[mid];
                    if (sum > target) {
                        --right;
                    } else if (sum < target) {
                        ++mid;
                    } else {
                        result.add(Arrays.asList(nums[i], nums[left], nums[mid], nums[right]));
                        while (mid < right && nums[mid] == nums[mid + 1]) mid++; //skipping over duplicate on low
                        while (mid < right && nums[right] == nums[right - 1]) right--; //skipping over duplicate on high
                        mid++;
                        right--;
                    }
                }
            }
        }
        return result;
    }
}
