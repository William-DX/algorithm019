package assignment.movezero;

/**
 * @ClassName Solution
 * Description 移动零
 * @author duxiao
 * @Date 2020/11/4 11:11
 * @Version 1.0
 **/
public class Solution {

    public void moveZeroes(int[] nums) {
        if (null == nums || nums.length == 0) { return; }
        int j = 0;
        for (int i = 0;i < nums.length;i++) {
            if (nums[i] != 0) {
                nums[j] = nums[i];
                if (i != j) {
                    nums[i] = 0;
                }
                j++;
            }
        }
    }
}
