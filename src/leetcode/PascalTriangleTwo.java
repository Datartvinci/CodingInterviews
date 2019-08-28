package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangleTwo {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>(numRows);
        for (int i = 0; i < numRows; i++) {
            List<Integer> temp = new ArrayList<>();
            List<Integer> arr = i == 0 ? new ArrayList<>(0) : list.get(i - 1);
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i ) {
                    temp.add(1);
                } else {
                    temp.add(arr.get(j) + arr.get(j - 1));
                }
            }
            list.add(temp);
        }
        return list;
    }
    public List<Integer> getRow(int rowIndex) {
        int[] nums = new int[rowIndex+1];
        nums[0] = 1;

        for(int count = 1; count <= rowIndex; count++){
            for(int i = count; i >= 0; i-- ){
                nums[i] = nums[i] + (i >= 1 ? nums[i-1] : 0);
            }
        }

        List<Integer> res = new ArrayList<>();
        for(int i = 0; i <= rowIndex; i++){
            res.add(nums[i]);
        }
        return res;
    }
}
