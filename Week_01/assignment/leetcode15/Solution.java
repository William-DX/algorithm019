package assignment.leetcode15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Solution
 * Description three sum
 * @Author duxiao
 * @Date 2020/11/9 17:33
 * @Version 1.0
 **/
public class Solution {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        /** 对数组进行排序 */
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) return res;

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                if (nums[j] + nums[k] == -nums[i]) {
                    List<Integer> subList = new ArrayList<>();
                    subList.add(nums[i]);
                    subList.add(nums[j]);
                    subList.add(nums[k]);
                    res.add(subList);
                    while(j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    j++;
                    k--;
                } else if (nums[j] + nums[k] > -nums[i]) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
    }
}
