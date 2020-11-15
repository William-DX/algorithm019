package leetcode589;

import java.util.List;

/**
 * @ClassName Node
 * @Description Node类
 * @Author duxiao
 * @Date 2020/11/15 21:44
 * @Version 1.0
 **/
public class Node {

    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
