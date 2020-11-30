package Leetcode122;

/**
 * @ClassName Solution
 * @Description 买卖股票的最佳时机Ⅱ
 * @Author duxiao
 * @Version 1.0
 */
public class Solution {

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i + 1] > prices[i]) {
                maxProfit += prices[i + 1] - prices[i];
            }
        }
        return maxProfit;
    }
}
