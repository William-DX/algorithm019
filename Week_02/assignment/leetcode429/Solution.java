package leetcode429;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @ClassName Solution
 * @Description N叉树的层序遍历
 * @Author duxiao
 * @Date 2020/11/15 21:47
 * @Version 1.0
 **/
public class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                currentLevel.add(node.val);
                if (!node.children.isEmpty()) {
                    queue.addAll(node.children);
                }
            }
            res.add(currentLevel);
        }

        return res;
    }
}
