package leetcode144;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Solution
 * @Description 前序遍历
 * @Author duxiao
 * @Date 2020/11/15 21:58
 * @Version 1.0
 **/
public class Solution {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        helper(root.left, res);
        helper(root.right, res);
    }
}
