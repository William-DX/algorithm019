package assignment.leetcode88;

/**
 * @ClassName Solution
 * @Description 合并两个有序数组
 * @Author duxiao
 * @Date 2020/11/8 17:44
 * @Version 1.0
 **/
public class Solution {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] mergeArray = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < m && j < n) {
            if (nums1[i] < nums2[j]) {
                mergeArray[k++] = nums1[i++];
            } else {
                mergeArray[k++] = nums2[j++];
            }
        }

        /** 将nums1剩余元素复制到新数组尾部 */
        while (i < m) {
            mergeArray[k++] = nums1[i++];
        }

        /** 将nums2剩余元素复制到新数组尾部 */
        while (j < n) {
            mergeArray[k++] = nums2[j++];
        }

        System.arraycopy(mergeArray, 0, nums1, 0, mergeArray.length);
    }
}
