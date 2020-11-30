学习笔记

## DFS代码模板

#Python

```python
visited = set()

def dfs(node, visited):
    if node in visited:
    #terminator
        return 
    visited.add(node)    
    #process current node
    for next_node in node.children():
        if not next_node in visited:
            dfs(next_node, visited)
```

#Java

```
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> allResults = new ArrayList<>();
    if (root == null) return allResults;
    travel(root, 0, allResults);
    return allResults;
}

private void travel(TreeNode root,int level,List<List<Integer>> results) {
      if (results.size() == level){
          results.add(new ArrayList<>());
      }
      results.get(level).add(root.val);
      if(root.left != null){
         travel(root.left,level+1,results);
      }
      if(root.right != null){
         travel(root.right,level+1,results);
      }
}
```

## BFS代码模板

#Java

```
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> allResults = new ArrayList<>();
    if (root == null) return allResults;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
        int size = queue.size();
        List<Integer> subList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            subList.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        allResults.add(subList);
    }
    return allResults;
}
```

## 二分查找模板

#Java

```
public int binarySearch(int[] array, int target) {
    int left = 0, right = array.length - 1, mid;
    while (left <= right) {
        mid = (right - left) / 2 + left;
        if (array[mid] == target) {
            return mid;
        } else if (array[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    return -1;
}
```


