package Leetcode455;

import java.util.Arrays;

/**
 * @ClassName Solution
 * @Description 分发饼干
 * @Author duxiao
 * @Version 1.0
 **/
public class Solution {

    public int findContentChildren(int[] g, int[] s) {
        //g-胃口
        //s-尺寸
        Arrays.sort(g);
        Arrays.sort(s);
        //饼干数组下标
        int index = s.length - 1;
        int result = 0;
        for (int i = g.length - 1; i >= 0; i--) {
            if (index >= 0 && s[index] >= g[i]) {
                result++;
                index--;
            }
        }
        return result;
    }
}
