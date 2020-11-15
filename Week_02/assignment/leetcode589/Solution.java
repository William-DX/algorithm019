package leetcode589;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Solution
 * @Description N叉树的前序遍历
 * @Author duxiao
 * @Date 2020/11/15 21:44
 * @Version 1.0
 **/
public class Solution {

    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList();
        helper(root, res);
        return res;
    }

    public void helper(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        for(int i = 0; i < root.children.size(); i++) {
            helper(root.children.get(i), res);
        }
    }
}
