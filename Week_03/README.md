# 学习笔记

## 递归

### 递归代码模板

// Java
public void recur(int level, int param) { 
  // recursion terminator 
  if (level > MAX_LEVEL) { 
    // process result 
    return; 
  }
  // process current logic 
  process(level, param); 
  // drill down 
  recur( level: level + 1, newParam); 
  // restore current status 
}

在递归中，注意以下几个误区：

- 不要人肉进行递归（最大误区）
- 找到最小重复子问题，这是解决递归的关键（即找到循环内容）
- 时刻谨记 数学归纳法思维，先思考最基本情况，逐步归纳普遍情况，得到递归公式

## 分治

### 分治代码模板

private static int divide_conquer(Problem problem, ) {

  if (problem == NULL) {
    int res = process_last_result();
    return res;     
  }
  subProblems = split_problem(problem)

  res0 = divide_conquer(subProblems[0])
  res1 = divide_conquer(subProblems[1])

  result = process_result(res0, res1);

  return result;
}



## 回溯

采用试错的思想，它尝试分步的去解决一个问题。在分步解决问题的过程中，当它通过尝试发现现有的分步答案不能得到有效的正确的解答的时候，它将取消上一步甚至是上几步的计算，再通过其它的可能的分步解答再次尝试寻找问题的答案。回溯法通常用最简单的递归方法来实现，在反复重复上述的步骤后可能出现两种情况：

找到一个可能存在的正确的答案；
在尝试了所有可能的分步方法后宣告该问题没有答案。



## 总结

本周刷题相对于前两周有些困难，主要是对于递归的掌握还不熟练，需要多多练习
