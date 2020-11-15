package leetcode242;

/**
 * @ClassName Solution
 * @Description 有效的字母异位词
 * @Author duxiao
 * @Date 2020/11/15 21:40
 * @Version 1.0
 **/
public class Solution {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] freq = new int[256];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (--freq[t.charAt(i)] < 0) {
                return false;
            }
        }
        return true;
    }
}
