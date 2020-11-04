package assignment.leetcode11;

/**
 * @ClassName Solution
 * Description Container with most water
 * @Author duxiao
 * @Date 2020/11/4 18:17
 * @Version 1.0
 **/
public class Solution {

    public int maxArea(int[] height) {
        /** 双指针法 */
        int i = 0;
        int j = height.length - 1;
        int res = 0;
        while (i < j) {
            int minHeight = Math.min(height[i], height[j]);
            res = Math.max(res, minHeight * (j - i));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }
}
