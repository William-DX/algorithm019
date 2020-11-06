package assignment.leetcode26;

/**
 * @ClassName Solution
 * @Description 删除数组
 * @Author duxiao
 * @Date 2020/11/6 10:57
 * @Version 1.0
 **/
public class Solution {

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        int j = i + 1;
        while (j < nums.length) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
            j++;
        }
        return i + 1;
    }
}
