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
        /** BFS */
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                subList.add(node.val);
                queue.addAll(node.children);
            }
            res.add(subList);
        }

        return res;
    }
}
