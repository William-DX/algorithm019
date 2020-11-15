package leetcode94;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Solution
 * @Description 中序遍历
 * @Author duxiao
 * @Date 2020/11/15 21:52
 * @Version 1.0
 **/
public class Solution {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        helper(root.left, res);
        res.add(root.val);
        helper(root.right, res);
    }
}
