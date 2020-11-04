package assignment.leetcode01;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Solution
 * Description twoSum
 * @Author duxiao
 * @Date 2020/11/4 17:37
 * @Version 1.0
 **/
public class Solution {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length;i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }
}
