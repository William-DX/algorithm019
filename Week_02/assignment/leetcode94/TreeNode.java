package leetcode94;

/**
 * @ClassName TreeNode
 * @Description 二叉树Node
 * @Author duxiao
 * @Date 2020/11/15 21:51
 * @Version 1.0
 **/
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
