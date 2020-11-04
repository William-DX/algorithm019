package assignment.leetcode66;

/**
 * @ClassName Solution
 * Description plusOne
 * @author duxiao
 * @Date 2020/11/4 17:49
 * @Version 1.0
 **/
public class Solution {

    public int[] plusOne(int[] digits) {
        int overflow = 1;
        for (int i = digits.length - 1;i >= 0;i--) {
            digits[i] += overflow;
            if (10 == digits[i]) {
                digits[i] = 0;
            } else {
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
