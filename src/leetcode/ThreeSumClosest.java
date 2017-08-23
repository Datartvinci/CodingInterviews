package leetcode;

import java.util.Arrays;

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
}
