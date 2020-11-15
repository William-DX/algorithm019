# 学习笔记

### HashMap总结

无参构造方法

HashMap(int initialCapacity)

​      this(initialCapacity, DEFAULT_LOAD_FACTOR);

HashMap(int initialCapacity, float loadFactor)

HashMap要求容量必须是2的幂

设定threshold，threshold = capacity * load factor。当HashMap的size到了threshold的时候，就要进行resize，也就是扩容

tableSizeFor()的主要功能是返回一个比给定整数大且最接近的2的幂次方整数

HashMap(Map<? extends K, ? extends V> m)

如果table为null，这时就设置合适的threshold，如果不为空并且指定的map的size>threshold,就进行resize()

HashMap由链表+数组组成，它的底层是个数组，而数组的元素是一个单向链表，每个数组储存的元素代表的是每个链表的头结点

Hash冲突 通过key进行Hash的计算，就可以获取到key对应的HashCode，如果不同的key计算出了相同的HashCode，就称作Hash碰撞

1.7之前-头插法

1.8之后-尾插法

HashMap的几个基本元素

public class HashMap<K,V> extends AbstractMap<K,V>

​    implements Map<K,V>, Cloneable, Serializable {

​    ......

​    //默认初始容量为16，这里这个数组的容量必须为2的n次幂。

​    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // 右移4位16

​    //最大容量为2的30次方

​    static final int MAXIMUM_CAPACITY = 1 << 30;

​    //默认加载因子

​    static final float DEFAULT_LOAD_FACTOR = 0.75f;

​    //转红黑树的阈值

​    static final int TREEIFY_THRESHOLD = 8;

​    //当桶数组容量小于该值时，优先进行扩容，而不是树化(容量大小会影响碰撞率)			

   static final int MIN_TREEIFY_CAPACITY = 64;

​			

   //红黑树转链表阈值(扩容时候红黑树拆分用到)

   static final int UNTREEIFY_THRESHOLD = 6;			

   //以Node<K,V>为元素的数组，也就是上图HashMap的纵向的长链数组，起始长度必须为2的n次幂

   transient Node<K,V>[] table;

   //已经储存的Node<key,value>的数量，包括数组中的和链表中的

   transient int size;

   //扩容的临界值，或者所能容纳的key-value对的极限。当size>threshold的时候就会扩容

   int threshold;

   //加载因子

  final float loadFactor;

HashMap的put添加功能源码

 public V put(K key, V value) {

​        return putVal(hash(key), key, value, false, true);

 }

​    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {

​        Node<K,V>[] tab; Node<K,V> p; int n, i;     //tab[]为数组，p是每个桶

 ①      if ((tab = table) == null || (n = tab.length) == 0) //第一步，table为空，则调用resize()函数创建一个

​            n = (tab = resize()).length;

 ②      if ((p = tab[i = (n - 1) & hash]) == null)   //第二步，计算元素所要储存的位置index,并对null做出处理

​            //注意这里，如果tab[i]==null，说明这个位置上没有元素，这个时候就创建一个新的Node元素

​            tab[i] = newNode(hash, key, value, null);

​        else {  //else,否则，也就是，这个要添加的位置上面已经有元素了，也就是发生了碰撞。这个时候就要具体情况分

​                //类讨论：1.key值相同，直接覆盖 2.链表已经超过了8位，变成了红黑树 3.正常的链表

​            Node<K,V> e; K k;

​           if (p.hash == hash &&            //如果节点key存在，则覆盖原来位置的key

​                ((k = p.key) == key || (key != null && key.equals(k))))

​                e = p;      

  ③     else if (p instanceof TreeNode)                 //第三步，判断该链是否为红黑树

​                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);

​           else {                                                      //如果是链表，遍历链表查找，如果没有找到，新建节点放到链表尾部

​                for (int binCount = 0; ; ++binCount) {

​                    if ((e = p.next) == null) {

​                        p.next = newNode(hash, key, value, null);

​                        //链表长度大于8转换为红黑树

​                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st

​                            treeifyBin(tab, hash);

​                        break;

​                    }

​                    //如果节点key存在，则覆盖原来位置的key，同时将原来位置的元素，沿着链表向后移一位

​                    if (e.hash == hash &&

​                        ((k = e.key) == key || (key != null && key.equals(k))))

​                        break;

​                    p = e;

​                }

​            }

​            if (e != null) { // existing mapping for key

​                V oldValue = e.value;

​                if (!onlyIfAbsent || oldValue == null)

​                    e.value = value;

​                afterNodeAccess(e);

​                return oldValue;

​            }

​        }

​        ++modCount;

​        if (++size > threshold)

  ④         resize();                                       //第四步：超过最大容量限制，扩容

​        afterNodeInsertion(evict);

​        return null;

​    }

**第一步：table为空，则调用resize()函数创建一个**

如果table为空或者长度为0，则resize()

**第二步：计算元素所要储存的位置index,并对null做出处理**

确定插入table的位置，算法是（n - 1） & hash

如果找到key值对应的槽并且是第一个，直接加入

HashMap如何计算元素所要储存的位置

index = （n - 1） & hash

hash是由hash(key)这个方法计算所得，n是新创建table数组的长度：(tab = resize()).length

hash是由hash(key)这个方法计算所得

首先得到key的哈希值:h = key.hashCode(),然后通过hashCode()的高16位异或低16位实现的

hashCode()的高16位异或或低16位：这么做可以再数组table的length比较小的时候，也能保证考虑到高低Bit都参与到Hash的计算中。右移16位，让自己的高半区和低半区异或，就是为了混合原始哈希码的高位和低位，以此来加大低位随机性，减少了哈希碰撞的几率

取模运算：(n - 1) & hash  运算等价于对length取模，但是&比%具有更高的效率

hash冲突发生的几种情况:

1.两节点key值相同(hash值一定相同)，导致冲突

2.两节点key值不同，由于hash函数的局限性导致hash冲突

3.两节点key值不同，hash值不同，但hash值对数组长度取模后相同，冲突

在table的i位置发生碰撞，有两种情况：

a.key值一样，替换value值

b.key值不一样有两种处理方式:(1)存储在i位置的链表 (2)存储在红黑树中

**第三步，判断该链是否为红黑树并添加元素**

(1) e = p.next以及后面的p = e;实际上是在向后遍历链表

(2) 有两个if判断:

第一个if：if((e = p.next) == null)

//链表的尾端也没有找到key值相同的节点，则生成一个新的node

//超过了链表的设置长度8就转换成红黑树

第二个if：if(e.hash == hash && ((k = e.key) == key) || (key != null && key.equals(k)))

如果找到hash值与key的hash值相同的node，终止循环

如果e不为空就替换旧值

**第四步，超过最大容量限制，扩容**

hashMap扩容机制的实现

使用的是2次幂的扩展，所以，元素的位置要么是在原位置，要么是在原位置移动2次幂的位置。hashMap在扩容的时候，只需要看原来的hash值新增的bit是1还是0，是0的话索引不变，是1的话索引就变成原索引+oldCap



## 哈希表

哈希表也叫散列表，其数据结构存储的是 **key -value** 键值对，能够根据 key 快速访问到 value 的数据结构。
其快速访问的原理是通过特定的映射计算函数（哈希函数），key作为函数输入，然后计算出其索引下标，然后快速访问到相应数组元素。
哈希函数有个问题：不同的值可能会计算出同样的索引，这个问题也称为 **哈希冲突**。为了解决哈希冲突，常用的方法为 **拉链法**，通过在每个数组头节点后拉出一条链表，存储所有索引下标相同的元素。

拉链法有多种实现形式，最为常见的形式为链表和红黑树



## 二叉树

常用的树为二叉树，每个节点的孩子数最多为2个，一个节点有两个指针，分别指向其自身的左子树和右子树。
二叉树的遍历有：

- 前序遍历：序列为 **根-左-右**
- 中序遍历：序列为 **左-根-右**
- 后序遍历：序列为 **左-右-根**
- 层次遍历，按照树的层数来遍历结点

遍历的方法：

1. 递归
2. 栈
3. 莫里斯

4. 层次遍历(BFS & DFS)





### 二叉搜索树

左子树上所有节点的值均小于根节点的值

右子树上所有节点的值均大于根节点的值





## 堆

### **堆的实现源码**

// Java

**import** java.util.Arrays;

**import** java.util.NoSuchElementException;

**public** **class** **BinaryHeap** {

  **private** **static** **final** **int** d = 2;

  **private** **int**[] heap;

  **private** **int** heapSize;

  /**

   \* This will initialize our heap with default size.

   */

  **public** **BinaryHeap**(**int** capacity) {

​    heapSize = 0;

​    heap = **new** **int**[capacity + 1];

​    Arrays.fill(heap, -1);

  }

  **public** **boolean** **isEmpty**() {

​    **return** heapSize == 0;

  }

  **public** **boolean** **isFull**() {

​    **return** heapSize == heap.length;

  }

  **private** **int** **parent**(**int** i) {

​    **return** (i - 1) / d;

  }

  **private** **int** **kthChild**(**int** i, **int** k) {

​    **return** d * i + k;

  }

  /**

   \* Inserts new element in to heap

   \* Complexity: O(log N)

   \* As worst case scenario, we need to traverse till the root

   */

  **public** **void** **insert**(**int** x) {

​    **if** (isFull()) {

​      **throw** **new** NoSuchElementException("Heap is full, No space to insert new element");

​    }

​    heap[heapSize] = x;

​    heapSize ++;

​    heapifyUp(heapSize - 1);

  }

  /**

   \* Deletes element at index x

   \* Complexity: O(log N)

   */

  **public** **int** **delete**(**int** x) {

​    **if** (isEmpty()) {

​      **throw** **new** NoSuchElementException("Heap is empty, No element to delete");

​    }

​    **int** maxElement = heap[x];

​    heap[x] = heap[heapSize - 1];

​    heapSize--;

​    heapifyDown(x);

​    **return** maxElement;

  }

  /**

   \* Maintains the heap property while inserting an element.

   */

  **private** **void** **heapifyUp**(**int** i) {

​    **int** insertValue = heap[i];

​    **while** (i > 0 && insertValue > heap[parent(i)]) {

​      heap[i] = heap[parent(i)];

​      i = parent(i);

​    }

​    heap[i] = insertValue;

  }

  /**

   \* Maintains the heap property while deleting an element.

   */

  **private** **void** **heapifyDown**(**int** i) {

​    **int** child;

​    **int** temp = heap[i];

​    **while** (kthChild(i, 1) < heapSize) {

​      child = maxChild(i);

​      **if** (temp >= heap[child]) {

​        **break**;

​      }

​      heap[i] = heap[child];

​      i = child;

​    }

​    heap[i] = temp;

  }

  **private** **int** **maxChild**(**int** i) {

​    **int** leftChild = kthChild(i, 1);

​    **int** rightChild = kthChild(i, 2);

​    **return** heap[leftChild] > heap[rightChild] ? leftChild : rightChild;

  }

  /**

   \* Prints all elements of the heap

   */

  **public** **void** **printHeap**() {

​    System.out.print("nHeap = ");

​    **for** (**int** i = 0; i < heapSize; i++)

​      System.out.print(heap[i] + " ");

​    System.out.println();

  }

  /**

   \* This method returns the max element of the heap.

   \* complexity: O(1)

   */

  **public** **int** **findMax**() {

​    **if** (isEmpty())

​      **throw** **new** NoSuchElementException("Heap is empty.");

​    **return** heap[0];

  }

  **public** **static** **void** **main**(String[] args) {

​    BinaryHeap maxHeap = **new** BinaryHeap(10);

​    maxHeap.insert(10);

​    maxHeap.insert(4);

​    maxHeap.insert(9);

​    maxHeap.insert(1);

​    maxHeap.insert(7);

​    maxHeap.insert(5);

​    maxHeap.insert(3);

​    maxHeap.printHeap();

​    maxHeap.delete(5);

​    maxHeap.printHeap();

​    maxHeap.delete(2);

​    maxHeap.printHeap();

  }

}



### 二叉堆

- 二叉堆是一个完全二叉树
- 其任意节点的值大于其子节点的值





## 图

Graph(V,E)

V—vertex：点

E-Edge：边



### 图的分类

- 无向无权图
- 有向无权图
- 无向有权图
- 有向有权图

