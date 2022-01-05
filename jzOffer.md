# 2021/12/1



## 一、栈与队列



### 1/09. 用两个栈实现队列

#### （1）题目

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )



#### （2）思路

* 使用一个栈 stack2 当作辅助栈
* 添加元素：向 stack1 中压入元素
* 删除队头元素：
  * 当 stack2 不为空时，stack2的栈顶元素即为队头元素
  * 当 stack2 为空时，将 stack1 中的元素依次压入 stack2 中
    * stack2 仍为空，即队列中没有元素，返回 -1
    * stack2 不为空，stack2的栈顶元素即为队头元素



#### （3）实现

```java
import java.util.LinkedList;


//leetcode submit region begin(Prohibit modification and deletion)
class CQueue {
    LinkedList<Integer> stack1, stack2;
    public CQueue() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }
    
    public void appendTail(int value) {
        stack1.offer(value);
    }
    
    public int deleteHead() {
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.offer(stack1.pollLast());
            }
        }
        if (stack2.isEmpty()){
            return -1;
        }
        return stack2.pollLast();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
//leetcode submit region end(Prohibit modification and deletion)
```





### 2/30. 包含min函数的栈

#### （1）题目

定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。



#### （2）思路

* 使用辅助栈stack2，stack2中栈顶元素即为stack1中的最小元素
* 入栈：有元素入栈时，同时更新stack2，比较该元素和栈顶元素，较小元素入栈
* 出栈：有元素出栈时，同时更新stack2，栈顶元素出栈



#### （3）实现

```java
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class MinStack {
    LinkedList<Integer> stack1, stack2;
    /** initialize your data structure here. */
    public MinStack() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }
    
    public void push(int x) {
        stack1.offer(x);
        if (stack2.isEmpty()){
            stack2.offer(x);
        }else{
            stack2.offer(Math.min(x, stack2.peekLast()));
        }
    }
    
    public void pop() {
        stack1.pollLast();
        stack2.pollLast();
    }
    
    public int top() {
        return stack1.peekLast();
    }
    
    public int min() {
        return stack2.peekLast();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */
//leetcode submit region end(Prohibit modification and deletion)
```





### 64/59-I. 滑动窗口的最大值

#### （1）题目

给定一个数组 `nums` 和滑动窗口的大小 `k`，请找出所有滑动窗口里的最大值。



#### （2）思路

* 队列 2 中维护**非严格递减序列**
  * 新元素入队 1 时，同时加入队列 2 ，**移除前面比它小的元素**
  * 元素出队时，若队列 2 中的队头元素与其相等，则出队
  * 队列 2 中的队头元素即为队列 1 中元素的最大值



#### （3）实现

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0){
            return new int[0];
        }
        LinkedList<Integer> list = new LinkedList();
        int[] res = new int[nums.length-k+1];
        int index = 0;
        for(int i = 0; i < k; i++){
            while(!list.isEmpty()&&list.peekLast()<nums[i]){
                list.pollLast();
            }
            list.offerLast(nums[i]);
        }
        res[index++]=list.peekFirst();
        for(int i = k; i < nums.length; i++){
            if(list.peekFirst()==nums[i-k]){
                list.pollFirst();
            }
            while(!list.isEmpty()&&list.peekLast()<nums[i]){
                list.pollLast();
            }
            list.offerLast(nums[i]);
            res[index++]=list.peekFirst();
        }
        return res;
    }
}
```



### 65/59-II. 队列中的最大值

#### （1）题目

请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。

若队列为空，pop_front 和 max_value 需要返回 -1



#### （2）思路

* 队列 2 中维护**非严格递减序列**
  * 新元素入队 1 时，同时加入队列 2 ，**移除前面比它小的元素**
  * 元素出队时，若队列 2 中的队头元素与其相等，则出队
  * 队列 2 中的队头元素即为队列 1 中元素的最大值



#### （3）实现

```java
class MaxQueue {
    LinkedList<Integer> list1, list2;
    public MaxQueue() {
        list1 = new LinkedList<Integer>();
        list2 = new LinkedList<Integer>();
    }
    
    public int max_value() {
        if(list2.isEmpty()){
            return -1;
        }
        return list2.peekFirst();
    }
    
    public void push_back(int value) {
        list1.offerLast(value);
        while(!list2.isEmpty()&&list2.peekLast()<value){
            list2.pollLast();
        }
        list2.offerLast(value);
    }
    
    public int pop_front() {
        if(list1.isEmpty()){
            return -1;
        }
        int pop = list1.pollFirst();
        if(list2.peekFirst()== pop){
            list2.pollFirst();
        }
        return pop;
    }
}
```







## 二、链表



### 3/06. 从尾到头打印链表

#### （1）题目

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

 

#### （2）思路

* 使用**辅助栈**
* 遍历链表，将节点的值压入栈中，**offerLast()**
* 遍历栈，将栈中元素出栈，**pollLast()**，并储存在数组中



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public int[] reversePrint(ListNode head) {
        if (head == null){
            return new int[0];
        }
        LinkedList<Integer> linkedList = new LinkedList<>();
        while (head != null){
            linkedList.offerLast(head.val);
            head = head.next;
        }
        int[] res = new int[linkedList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = linkedList.pollLast();
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```





### 4/24. 反转链表

#### （1）题目

定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。



#### （2）思路

* 使用**虚拟头节点和头插法**
* 遍历链表，将每一个节点**用头插法连接到虚拟头节点后**



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        //虚拟头节点
        ListNode vhead = new ListNode();
        vhead.next = null;

        //头插法
        ListNode temp = head;
        while (temp != null){
            ListNode next = temp.next;
            temp.next = vhead.next;
            vhead.next = temp;
            temp = next;
        }
        return vhead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 5/35. 复杂链表的复制

#### （1）题目

请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。



#### （2）思路

* 存储**节点和复制节点的映射**
* 遍历链表，复制节点的val，存储**节点和复制节点的映射**
* 再次遍历链表，给复制节点的**next指针和random指针**赋值，即为**原始节点的next指针和random指针的映射**



#### （3）实现

```java
import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
class Solution {
    public Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<Node, Node>();//节点和复制节点的映射
        Node temp = head;
        while (temp != null){
            map.put(temp, new Node(temp.val));
            temp = temp.next;
        }
        temp = head;
        while (temp != null){
            Node copy = map.get(temp);
            
            //给复制节点的next指针和random指针赋值
            copy.next = map.get(temp.next);
            copy.random = map.get(temp.random);
            temp = temp.next;
        }
        return map.get(head);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```





## 三、字符串



### 6/05. 替换空格

#### （1）题目

请实现一个函数，把字符串 `s` 中的每个空格替换成"%20"。



#### （2）思路

* 使用**StringBuilder**
* 遍历字符串 s，对于当前字符 ch
  * ch = ' '，需要将空格替换为 "%20"，**sb.append("%20")**
  * ch != ' '，不需要替换，**sb.append(ch)**
* 返回**sb.toString()**



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' '){
                sb.append("%20");
            }else{
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 7/58-II. 左旋转字符串

#### （1）题目

字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。



#### （2）思路

* 使用**StringBuilder**
* 将字符串分为两个子串
  * str1 = s.subString(0, n)
  * str2 = s.subString(n)
* 拼接子串 str1 和 str2



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseLeftWords(String s, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(n));
        sb.append(s.substring(0, n));
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 62/20. 表示数值的字符串



### 63/67. 把字符串转换成整数

#### （1）题目

写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。

 

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0。

说明：

假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。



#### （2）思路

* 按照题意转换字符串
  * 去除字符串开头的空格，`str = str.trim();`
  * 若字符串长度为 0，返回 0
  * 根据字符串的第一个字符判断整数的正负并记录 isPos，若是正负号，则删除第一个字符
  * 将每个字符转换成整数，**long res** 记录当前整数的值
    * 当前字符为 '0'~'9'：根据 isPos，将 res 和 int_max 和 int_min 进行比较，若超出界限，则返回 int_max 或 int_min
    * 当前字符不是 '0'~'9'：跳出循环
  * 若 isPos 为 false，`res *= -1;`
  * 返回 res



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int strToInt(String str) {
        int int_max = (1 << 31)-1, int_min = (-1<<31);
        str = str.trim();
        if (str.length() == 0){
            return 0;
        }
        //判断正负，并去除符号
        boolean isPos = true;
        if (str.charAt(0) == '-') isPos = false;
        if (str.charAt(0) == '+' || str.charAt(0) == '-') str = str.substring(1);

        int i = 0;
        long res = 0;
        while (i<str.length()){
            if (str.charAt(i)>= '0' && str.charAt(i)<='9'){
                res = res * 10 + str.charAt(i)-'0';
                if (isPos && res > int_max){
                    return int_max;
                }
                if (!isPos &&(-res) < int_min){
                    return int_min;
                }
                i++;
            }else{
                break;
            }
        }
        if (!isPos){
            res *= -1;
        }
        return (int)res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







## 四、查找算法

### 8/03. 数组中重复的数字

#### （1）题目

在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。



#### （2）思路

* 使用HashSet
* set.add(temp)有返回值
  * 返回值为 true：元素 temp 添加成功
  * **返回值为 false**：元素 temp 添加失败，即 set 中包含重复元素 temp



#### （3）实现

```java
import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findRepeatNumber(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int temp : nums){
            if (!set.add(temp)){
                return temp;
            }
        }
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 9/53-I. 在排序数组中查找数字 I

#### （1）题目

统计一个数字在排序数组中出现的次数。



#### （2）思路

* 二分法
* 使用二分法查找该数字出现的**左右边界**
  * 查询左边界
    * nums[mid] = target时：不跳出循环，right = mid-1
  * 查询右边界
    * nums[mid] = target时：不跳出循环，left = mid+1



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int search(int[] nums, int target) {
        int res = 0, left = -1, right = -2, i = 0, j = nums.length-1;
        while (i <= j){
            int mid = i + (j-i)/2;
            if (nums[mid] == target){
                left = mid;
                j = mid-1;
            }else if (nums[mid] < target){
                i = mid+1;
            }else{
                j = mid-1;
            }
        }

        i = 0; j = nums.length-1;
        while (i <= j){
            int mid = i + (j-i)/2;
            if (nums[mid] == target){
                right = mid;
                i = mid+1;
            }else if (nums[mid] < target){
                i = mid+1;
            }else{
                j = mid-1;
            }
        }
        return right-left+1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 10/53-II. 0 ~ n-1中缺失的数字

#### （1）题目

一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。



#### （2）思路

* 二分法查询
* 数组中下标为index的元素有**两种情况**
  * **nums[index] = index**：该元素在本来的位置上，缺失的元素在**index的右边**
  * **nums[index] > index**：该元素不在本来的位置上，缺失的元素在**index位置上或index的左边**
* 二分法查找，左右边界分别为left，right：
  * 特殊情况：
    * 缺失元素 **0**
    * 缺失元素 **nums.length**
  * 循环条件：**left < right**
    * 计算中点 mid = left + (right-left)/2；
    * 若 nums[mid] = mid，缺失元素在 mid 的右边，**left = mid+1**
    * 若 nums[mid] != mid，缺失的元素在 mid 位置或 mid 的左边，**right = mid**
  * 跳出循环时，**left 指向缺失元素的位置**，返回 left



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int missingNumber(int[] nums) {
        if (nums[0] != 0){
            return 0;
        }
        if (nums[nums.length-1] == nums.length-1){
            return nums.length;
        }
        int left = 0, right = nums.length-1;
        while (left < right){
            int mid = left + (right-left)/2;
            if (nums[mid] == mid){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 11/04. 二维数组中的查找

#### （1）题目

在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。



#### （2）思路

* 二维数组中：每一行从左到右递增，每一列从上到下递增
* 从**左上角或右下角**开始搜索，以左上角为例
  * nums[i] [j] = target：返回 true
  * nums[i] [j] < target：i++，消除第 i 行元素
  * nums[i] [j] > target： j--，消除第 j 列元素



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0){
            return false;
        }
        int n = matrix.length, m = matrix[0].length, i = 0, j = m-1;
        while (i < n && j >= 0){
            if (matrix[i][j] == target){
                return true;
            }
            if (matrix[i][j] < target){
                i++;
            }else {
                j--;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 12/50. 第一个只出现一次的字符

#### （1）题目

在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。



#### （2）思路

* 有序哈希表
* 有序哈希表的键值对是**按照插入顺序排序**的，java中使用 **LinkedHashMap**
* 遍历字符串，将每个字符记录在map中
* 遍历map的key集合，若value=1，则返回
* 如无满足要求的key，返回 ' '



#### （3）实现

```java
import java.util.LinkedHashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public char firstUniqChar(String s) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap();
        char[] chs = s.toCharArray();
        for (char ch : chs){
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }
        for (char ch : map.keySet()){
            if (map.get(ch) == 1){
                return ch;
            }
        }
        return ' ';
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 13/11. 旋转数组的最小数字

#### （1）题目

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。

给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为1。  



#### （2）思路

* 二分查找

* 可以将数组分为**左半有序数组和右半有序数组**，需要查找右半有序数组的第一个元素
* 左边界 left，右边界 right，中间点mid = left +(right-left) /2
  * 循环条件：**left < right**
  * nums[left] = nums[mid] = nums[right]：由于数组有重复元素，**无法判断** mid 在哪一半数组中，**left++，right--**
  * nums[mid] <= nums[right] <= nums[left]：中间点 mid 在**右半**有序数组中，**right = mid**
  * nums[right] <= nums[left] <= nums[mid]：中间点 mid 在**左半**有序数组中，**left = mid + 1**
  * nums[left] <= nums[mid] <= nums[right]：left ~ right为有序数组，**right = mid - 1**
  * 跳出循环时，left 指向右半有序数组中的第一个元素，即数组的最小值



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minArray(int[] numbers) {
        int left = 0, right = numbers.length-1;
        while (left < right){
            int mid = left + (right-left)/2;
            if (numbers[left] == numbers[mid] && numbers[right] == numbers[mid]){
                left++;
                right--;
            }else if (numbers[mid] <= numbers[right] && numbers[right]<= numbers[left]){
                    right = mid;
            }else if (numbers[right] <= numbers[left] && numbers[left] <= numbers[mid]){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return numbers[left];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```





## 五、搜索与回溯算法

### 14/32-I. 从上到小打印二叉树

#### （1）题目

从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。



#### （2）思路

* 二叉树层序遍历，队列



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int[] levelOrder(TreeNode root) {
        if (root == null){
            return new int[0];
        }
        ArrayList<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offerLast(root);
        while (!queue.isEmpty()){
            TreeNode first = queue.pollFirst();
            list.add(first.val);
            if (first.left != null){
                queue.offerLast(first.left);
            }
            if (first.right != null){
                queue.offerLast(first.right);
            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 15/32-II. 从上到下打印二叉树 II

#### （1）题目

从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。



#### （2）思路

* 二叉树的层序遍历
* 记录每一层的最后一个节点last，**若当前遍历节点=last，则更新last节点**



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null){
            return new ArrayList<List<Integer>>();
        }
        ArrayList<List<Integer>> res = new ArrayList<>();//记录结果
        ArrayList<Integer> temp = new ArrayList<>();//记录当前层的节点值
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();//队列
        list.offerLast(root);
        TreeNode last = root;
        while (!list.isEmpty()){
            TreeNode first = list.pollFirst();
            temp.add(first.val);
            if (first.left != null){
                list.offerLast(first.left);
            }
            if (first.right != null){
                list.offerLast(first.right);
            }
            if (first == last){//当前节点为该层的最后一个节点
                res.add(new ArrayList<>(temp));
                temp.clear();
                last = list.peekLast();//更新last节点
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 16/32-III. 从上到下打印二叉树 III

#### （1）题目

请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。



#### （2）思路

* 层序遍历
* 记录当前层的深度，偶数层正常记录，奇数层则反转数组
* 使用双端队列，奇偶层入队时区分，偶数层从后入队，奇数层从前入队



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if (root == null){
            return res;
        }
        List<Integer> temp = new ArrayList<Integer>();
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        list.offerLast(root);
        TreeNode last = root;
        int deepth = 0;
        while (!list.isEmpty()){
            TreeNode first = list.pollFirst();
            temp.add(first.val);
            if (first.left != null){
                list.offerLast(first.left);
            }
            if (first.right != null){
                list.offerLast(first.right);
            }
            if (first == last){
                if (deepth%2 == 0){
                    res.add(new ArrayList<>(temp));
                }else{
                    ArrayList<Integer> rever = new ArrayList<>();
                    for (int i = 0; i < temp.size(); i++) {
                        rever.add(temp.get(temp.size()-i-1));
                    }
                    res.add(new ArrayList<>(rever));
                }
                temp.clear();
                deepth++;
                last = list.peekLast();
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 17/27. 二叉树的镜像

#### （1）题目

请完成一个函数，输入一个二叉树，该函数输出它的镜像。



#### （2）思路

* 递归交换其左右孩子节点



#### （3）实现

```java
package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null){
            return root;
        }
        mirrorTree(root.left);
        mirrorTree(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 18/28.对称的二叉树

#### （1）题目

请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。



#### （2）思路

* 对称二叉树中：对于树中**任意两个对称节点L和R**，一定有
  * L.val = R.val：此两对称节点的值相等
  * L.left.val = R.right.val：L的左孩子节点和R的右孩子节点对称
  * L.right.val = R.left.val：L的右孩子节点和R的左孩子节点对称

* 自顶向下**判断每对节点是否为对称节点**，从而判断二叉树是否对称
* 算法流程：
  * `isSymmetric(TreeNode root)`
    * 特例处理：root为null时，返回true
    * 返回 `judge(root.left, root.right);`，即递归判断每对节点是否对称
  * `judge(TreeNode root1, TreeNode root2)`
    * 终止条件：
      * root1 = root2，即均为null的情况，返回true
      * root1=null || root2=null，即有一个节点为null，不对称，返回false
    * 递推工作：
      * **递归判断L.left和R.right是否为对称节点**，`judge(root1.left, root2.right);`
      * **递归判断L.right和R.left是否为对称节点**，`judge(root1.right, root2.left);`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null){
            return true;
        }
        return judge(root.left, root.right);
    }
    public boolean judge(TreeNode root1, TreeNode root2){
        if (root1 == root2){//均为null
            return true;
        }
        if (root1 == null || root2 == null){//只有一个为null
            return false;
        }
        return root1.val == root2.val&&judge(root1.left, root2.right)&&judge(root1.right, root2.left);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 19/26. 树的子结构

#### （1）题目

输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

B是A的子结构， 即 A中有出现和B相同的结构和节点值。



#### （2）思路

* 先序遍历二叉树A的每个节点n<sub>A</sub>
* 判断**以n<sub>A</sub>为根节点的子树**是否包含树B



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    boolean flag = false;
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (B==null){//特例
            return false;
        }
        dfs(A, B);//先序遍历
        return flag;
    }
    public void dfs(TreeNode A, TreeNode B){
        if (flag || A == null){
            return;
        }
       if (judge(A, B)){//判断以A为根节点的子树是否包含树B
           flag = true;
       }
        if (!flag){//剪枝
            dfs(A.left, B);
            dfs(A.right, B);
        }
    }
    public boolean judge(TreeNode root1, TreeNode root2){
        if (root2 == null){//树B越过叶节点
            return true;
        }
        if (root1 == null){//子树root1越过叶节点
            return false;
        }
        return root1.val==root2.val&&judge(root1.left, root2.left)&&judge(root1.right, root2.right);//递归判断子树是否相同
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 34/12. 矩阵中的路径

#### （1）题目

给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。



#### （2）思路

* 深度优先搜索，使用 visited[i] [j] 记录当前路径下 grid[i] [j] 单元格是否被访问过
* DFS算法流程：i, j为当前单元格下标，index为当前字符在word中的索引
  * 终止条件：
    * 行或列索引越界；当前矩阵元素和目标字符不匹配；当前矩阵单元格以被访问过；返回false
    * 字符串 word 匹配完毕，即word.length()=index-1，返回 true
  * 递推：
    * 修改当前单元格的访问状态，visited[i] [j] = true
    * 搜索下一单元格，即当前单元格的上下左右四个方向，使用 || 连接（即只需找到一条路径即可），使用 res 记录
    * 还原当前单元格的访问状态，visited[i] [j] = false
  * 返回 res



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[][] plus = {{-1,1,0,0},{0,0,-1,1}};
    boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        int n = board.length, m = board[0].length;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (find(board, i, j, word, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean find(char[][] board , int i , int j, String word, int index){
        //不满足要求的情况
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length
                || board[i][j] != word.charAt(index) || visited[i][j]==true){
            return false;
        }
        //字符串匹配完毕
        if (index == word.length()-1){
            return true;
        }
        //搜索下一单元格
        visited[i][j] = true;
        boolean res = find(board, i-1, j, word, index+1)
                || find(board, i+1, j, word, index+1)
                || find(board, i, j-1, word, index+1)
                || find(board, i, j+1, word, index+1);
        visited[i][j]=false;
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
```





### 35/13. 机器人的运动范围

#### （1）题目

地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？



#### （2）思路

* 深度优先搜索，DFS通过递归，先向一个方向搜索到底，然后回溯到上一个节点，向另一个方向搜索
* 算法流程：
  * 全局变量 res 记录机器人可以到达的格子数量
  * judge(int i, int j)：判断行坐标 i 和列坐标 j 的数位之和是否小于等于 k
  * dfs(int i, int j)：i 和 j 为当前元素在矩阵中的行索引和列索引
    * 终止条件：
      * 行索引 i 和列索引 j 越界
      * 当前单元格已被访问过
      * 行索引 i 和列索引 j 的数位之和大于 k
    * 递推：
      * 记录当前单元格，res++
      * 修改当前单元格的访问状态，visited[i] [j] = true
      * 搜索下一单元格，即当前单元格的上下左右四个方向
  * 主函数中从(0, 0)开始搜索，即**dfs(0, 0)**，返回 res



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int m, n, k, res;
    boolean[][] visited;
    public int movingCount(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        visited = new boolean[m][n];
        dfs(0, 0);
        return res;
    }
    public void dfs(int i, int j){
        if (i<0||i>=m||j<0||j>=n||visited[i][j]==true){
            return;
        }
        if (!judge(i, j)){
            return;
        }
        res++;
        visited[i][j]=true;
        dfs(i-1, j);
        dfs(i+1, j);
        dfs(i, j-1);
        dfs(i, j+1);
    }
    public boolean judge(int i, int j){
        int res = 0;
        while (i != 0){
            res += i%10;
            i/=10;
        }
        while (j != 0){
            res += j%10;
            j/=10;
        }
        return res<=k;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
```





### 36/34. 二叉树中和为某一值的路径

#### （1）题目

给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

叶子节点 是指没有子节点的节点



#### （2）思路

* 深度优先搜索，List<List<Integer>> res 记录所有满足要求的路径，List<Integer> temp 记录当前的路径
* 算法流程：
  *  dfs(TreeNode root, int sum)：sum为当前路径总和
  * 终止条件：root = null，当前节点为空节点
  * 递推：
    * 将当前节点的值记录在当前路径 temp 中，`temp.add(root.val)`；更新路径和，`sum+=root.val`
    * 当前路径符合要求时，记录当前路径，`res.add(new ArrayList<>(temp))`
      * 当前节点为叶节点且路径和满足要求：root.left == null && root.right ==  null && sum == target
    * 左右孩子节点不为空时，递归搜索其左右孩子节点
      * `dfs(root.left, sum)`
      * `dfs(root.right, sum)`
    * 状态回溯：将当前节点移除路径
      * `temp.remove(temp.size()-1)`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    List<List<Integer>> res;
    ArrayList<Integer> temp;
    int target;
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        res = new ArrayList<>();
        temp = new ArrayList<>();
        this.target=target;
        dfs(root, 0);
        return res;
    }
    public void dfs(TreeNode root, int sum){
        if (root == null){
            return;
        }else{
            temp.add(root.val);
            sum+=root.val;
            if (root.left == null && root.right == null && sum == target){
                res.add(new ArrayList<>(temp));
            }
            if (root.left != null){
                dfs(root.left, sum);
            }
            if (root.right != null){
                dfs(root.right, sum);
            }
            temp.remove(temp.size()-1);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
```





### 37/36. 二叉搜索树与双向链表

#### （1）题目

输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。

为了让您更好地理解问题，以下面的二叉搜索树为例：

 

![img](https://assets.leetcode.com/uploads/2018/10/12/bstdlloriginalbst.png)



 

我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。

下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。

![img](https://assets.leetcode.com/uploads/2018/10/12/bstdllreturndll.png)

 

特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。



#### （2）思路

* 二叉搜索树的**先序优先遍历序列**为升序序列
* 使用 ArrayList<Node> list 记录该二叉树的先序优先遍历序列
* inorder(TreeNode root)：
  * 终止条件：当前节点为空节点，`root==null`
  * 递归左子树，`inorder(root.left)`
  * 构建双向链表：
    * 当 list 为空时，代表当前访问的节点为双向链表的表头节点，不做操作
    * 当 list 不为空时，修改双向节点的引用，**pre 为 list 中的最后一个节点，即 root 的前序节点**，`pre.right=root`，`root.left=pre`
  * list 记录当前节点，`list.add(root)`
  * 递归右子树，`inorder(root.right)`
* 在主函数中修改双向链表的头节点和尾节点的引用，`head.left=tail`，`tail.right=head`



#### （3）实现

```java
import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
    ArrayList<Node> list;
    public Node treeToDoublyList(Node root) {
        if (root == null){
            return root;
        }
        list = new ArrayList<Node>();
        inorder(root);
        Node head = list.get(0), tail = list.get(list.size()-1);
        head.left = tail;
        tail.right = head;
        return head;
    }
    public void inorder(Node root) {
        if (root == null){
            return;
        }
        inorder(root.left);
        if (list.size()!=0){
            Node tail = list.get(list.size()-1);
            tail.right=root;
            root.left=tail;
        }
        list.add(root);
        inorder(root.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 38/54. 二叉搜索树的第k大节点

#### （1）题目

给定一棵二叉搜索树，请找出其中第k大的节点。



#### （2）思路

* 按照**右根左**的顺序遍历二叉树，第 k 大的节点即为序列中第 k 个元素



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int k,res;
    public int kthLargest(TreeNode root, int k) {
        inorder(root, k);
        return res;
    }
    public void inorder(TreeNode root, int k){
        if (root == null || this.k > k){
            return;
        }
        inorder(root.right, k);
        this.k++;
        if (this.k == k){
            res = root.val;
        }
        inorder(root.left, k);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
```





### 43/55-I. 二叉树的深度

#### （1）题目

输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。



#### （2）思路

* 树的深度 = **max(左子树的深度，右子树的深度) + 1**



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
//leetcode submit region end(Prohibit modification and deletion
```





### 44/55-II.  平衡二叉树

#### （1）题目

输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。



#### （2）思路

* 平衡二叉树：
  * **左右子树的深度相差不超过 1**
  * **左右子树是平衡二叉树**
* `int dfs(TreeNode root)`
  * 返回值：若当前子树不是平衡二叉树，返回 -1；若当前子树是平衡二叉树，返回子树深度
  * 中止条件：root = null，返回 0
  * **递推**左右子树深度 left，right
  * 当前子树不是平衡二叉树：返回 **-1**
    * 左右子树不是平衡二叉树：`left==-1||right==-1`
    * 该子树不是平衡二叉树：`abs(left-right)>1`
  * 当前子树是平衡二叉树，返回深度：**1+max(left, right)**
* `boolean isBalanced(TreeNode root)`
  * 特殊情况：root=null，返回 true
  * 获取左右子树深度，`left=dfs(root.left), right=dfs(root.right)`
  * 返回 `left!=-1&&right!=-1&&Math.abs(left-right)<=1`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null){
            return true;
        }
        int left = dfs(root.left);//左子树深度
        int right = dfs(root.right);//右子树深度
        return left!=-1&&right!=-1&&Math.abs(left-right)<=1;
    }
    public int dfs(TreeNode root){//当前子树不是平衡树，返回-1；是平衡树，返回子树深度
        if (root == null){
            return 0;
        }
        int left = dfs(root.left), right = dfs(root.right);
        if (left==-1||right==-1||Math.abs(left-right)>1){
            return -1;
        }
        return 1+Math.max(left, right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 45/64. 求1+2+...+n

#### （1）题目

求 `1+2+...+n` ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。



#### （2）思路

* 不使用判断语句，使用逻辑判断的短路效应不让进入下一层递归：&&
* 算法流程：使用全局变量 res 记录和
  * res += n
  * `return n>1&&cal(n-1);`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int res = 0;
    public int sumNums(int n) {
        cal(n);
        return res;
    }
    public boolean cal(int n){
        res += n;
        return n>0 && cal(n-1);//短路
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 46/68-I. 二叉搜索树的最近公共祖先

#### （1）题目

给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”



#### （2）思路

* 二叉搜索树，左 < 根 < 右
* 算法流程：
  * 若p、q节点分别在 root 节点的两侧，或 p、q 节点和 root 节点重合，则 root 节点是最近公共节点
  * 若 p、q节点均在 root 节点的左侧，则在 root 的左子树中继续搜索
  * 若 p、q 节点均在 root 节点的右侧，则在 root 的右子树中继续搜索



#### （3）实现

```
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if ((p.val<=root.val&&q.val>=root.val)||(p.val>=root.val&&q.val<=root.val)){
            return root;
        }
        if (p.val<root.val&&q.val<root.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        return lowestCommonAncestor(root.right, p, q);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 47/68-II. 二叉树的最近公共祖先

#### （1）题目

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”



#### （2）思路

* `TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)`
  * 返回值说明：
    * 若以 root 为根节点的子树中，存在p、q的公共祖先，返回节点 p、q 的最近公共祖先
    * 若该子树中不存在公共祖先节点，返回该子树中存在的 p 节点或 q 节点；若 p、q均不存在，返回 null
  * 跳出条件：root = null，p、q节点均不在该子树中，返回 null
  * `root == p || root == q`，返回 root
    * 若该子树中存在 p、q节点，则 root 节点是其最近公共祖先节点
  * 递归在左右子树中搜索
    * `TreeNode left = lowestCommonAncestor(root.left, p, q);`
    * `TreeNode right = lowestCommonAncestor(root.right, p, q);`
  * 判断：
    * 若 left 和 right 均不为 null，则 p、q 节点分别在 root 的两侧，root 为其最近公共祖先节点
    * 否则，返回 left 和 right 中不为 null 的值, `return left != null? left : right;`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null){
            return null;
        }
        if (root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null){
            return root;
        }
        return left != null? left : right;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 66/37. 序列化二叉树

#### （1）题目

请实现两个函数，分别用来序列化和反序列化二叉树。

你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。



#### （2）思路

* 序列化：层序遍历，将二叉树序列化为**"1,2,3,null,null,4,5"**
* 反序列化：层序遍历，将**"1,2,3,null,null,4,5"**反序列化为二叉树
  * index 记录当前在字符串数组的下标
  * 将字符串数组的第一个元素反序列化为根节点 root，若为null，直接返回
  * 根节点 root 入队 list
  * 层序遍历：
    * 初始化 index = 1
    * 终止条件：list 为空
    * list 的队头节点出队 first
    * 将 index 和 index+1 分别转换为 first 节点的左右孩子节点，并更新 index += 2
    * first 的左右孩子节点不为空时，将其入队 
  * 返回 root



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)


import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        list.offerLast(root);
        while (!list.isEmpty()){
            if (sb.length() != 0){
                sb.append(",");
            }
            TreeNode first = list.pollFirst();
            if (first==null){
                sb.append("null");
            }else{
                sb.append(String.valueOf(first.val));
                list.offerLast(first.left);
                list.offerLast(first.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        if (nodes.length == 1){
            return null;
        }
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        list.add(root);
        int i = 1;
        while (!list.isEmpty()){
            TreeNode first = list.pollFirst();
            if (first != null){
                if ("null".equals(nodes[i])){
                    first.left = null;
                    i++;
                } else {
                    first.left = new TreeNode(Integer.parseInt(nodes[i++]));
                }
                if ("null".equals(nodes[i])){
                    first.right = null;
                    i++;
                }else{
                    first.right = new TreeNode(Integer.parseInt(nodes[i++]));
                }
                if (first.left != null){
                    list.offerLast(first.left);
                }
                if (first.right != null){
                    list.offerLast(first.right);
                }
            }
        }

        return root;
    }

}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
```





### 67/38. 字符串的排列

#### （1）题目

输入一个字符串，打印出该字符串中字符的所有排列。

你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。



#### （2）思路

* isvisit[] 记录当前排列添加了哪些元素

* `void salute(String s, int index)`：index 为当前排列的元素个数
  * 终止条件：index == s.length()，代表当前排列包含了所有元素，记录当前排列
  * 递推工作：遍历字符数组，选择元素加入排列，确保该轮不选择相同的元素
    * `i == 0||isvisit[i-1]==true||s.charAt(i)!=s.charAt(i-1)`：确保该轮次选择不同的元素
    * `sb.append(s.charAt(i))`：添加元素
    * `isvisit[i]=true`：修改访问状态
    * 递归选择下一个元素，`salute(s, index+1)`
    * 状态回溯：`sb.deleteCharAt(sb.length()-1); isvisit[i]=false`



#### （3）实现

```java

import java.util.ArrayList;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    ArrayList<String> list = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    boolean[] isvisit;
    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        s = new String(chars);
        isvisit = new boolean[s.length()];
        salute(s, 0);
        return list.toArray(new String[0]);
    }
    public void salute(String s, int index){
        if (index == s.length()){
            list.add(sb.toString());
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (isvisit[i]==false){
                if (i == 0||isvisit[i-1]==true||s.charAt(i)!=s.charAt(i-1)){//确保该轮不会选择相同的字符
                    sb.append(s.charAt(i));
                    isvisit[i]=true;
                    salute(s, index+1);
                    //状态回溯
                    sb.deleteCharAt(sb.length()-1);
                    isvisit[i]=false;
                }
            }
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
```







## 六、动态规划

### 20/10-I. 斐波那契数列

#### （1）题目

写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：

F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。



#### （2）思路

* 动态规划
* 使用 dp[n] 记录斐波那契数列的第 n 项
* 动态转移方程：dp[i] = dp[i-1] + dp[i-2]



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int fib(int n) {
        if (n < 2){
            return n;
        }
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < n+1; i++) {
            dp[i] =(dp[i-1]+dp[i-2])%(int)(1e9+7);
        }
        return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 21/10-II. 青蛙跳台阶问题

#### （1）题目

一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。



#### （2）思路

* 对于任意 i 级台阶，可以**从 i-1 台阶或 i-2 台阶**跳至
* 使用 dp[n] 记录第 n 级台阶共有多少种跳法
* 状态转移方程：dp[i] = dp[i-1] + dp[i-2]



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numWays(int n) {
        if (n < 2){
            return 1;
        }
        int[] dp = new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for (int i = 2; i < n+1; i++) {
            dp[i] = (dp[i-1]+dp[i-2])%(int)(1e9+7);
        }
        return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 22/63. 股票的最大利润

#### （1）题目

假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？



#### （2）思路

* 动态规划
*  使用 min 记录**截止到当天可以买的股票的最低价**
* 状态转移方程：min = Math.min(min, prices[i])
* 截至到当天能获得的最大利润为 **prices[i] - min**



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0||prices.length == 1){
            return 0;
        }
        int max = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            max = Math.max(max, prices[i]-min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 23/42. 连续子数组的最大和

#### （1）题目

输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

要求时间复杂度为O(n)。



#### （2）思路

* 使用 dp[i] 记录**以下标为 i 的元素 nums[i] 结尾**的连续子数组的最大值
* 状态转移方程：dp[i] 可以由 dp[i-1] 得到，也可以单独由 nums[i] 组成
  * dp[i] = **max( dp[i-1]+nums[i], nums[i] )**



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0]=nums[0];
        int res = dp[0];
        for (int i = 1; i < dp.length; i++) {
            dp[i]=Math.max(dp[i-1]+nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 24/47. 礼物的最大价值

#### （1）题目

在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？



#### （2）思路

* 对于棋盘中的每一格，可以从**其左侧或上侧**的格子移动到达（第一行和第一列的格子需单独考虑）
* dp[i] [j] 表示**达到 grid[i] [j]** 格子时可以拿到的最大价值礼物
* 状态转移方程
  * dp[i] [j] = **max( dp[i-1] [j], dp[i] [j-1] ) + grid[i] [j]**（ i > 0 && j > 0 ）
  * dp[i] [j] = grid[i] [j]（ i=0 && j =0，即第一个格子）
  * dp[i] [j] = dp[i] [j-1] + grid[i] [j]（ i=0 ，即第一行格子）
  * dp[i] [j] = dp[i-1] [j] + grid[i] [j]（ j= 0，即第一列格子）



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxValue(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i==0){
                    if (j==0) dp[i][j]=grid[i][j];
                    else dp[i][j]=dp[i][j-1]+grid[i][j];
                }else if (j==0){
                    dp[i][j]=dp[i-1][j]+grid[i][j];
                }else{
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1])+grid[i][j];
                }
            }
        }
        return dp[n-1][m-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 25/46. 把数字翻译成字符串

#### （1）题目

给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。



#### （2）思路

* 使用 dp[i] 表示截止到第 i 位，数字共有多少种翻译方式
* 状态转移方程：
  * dp[i] = dp[i-1] + dp[i-2]：第 i 位和第 i-1 位可以组合翻译成一个字符，即在[10,25]范围内
  * dp[i] = dp[i-1]：第 i 位和第 i-1 位无法组合翻译成一个字符
* 算法流程：
  * 将 num 转换成字符串str，`str = String.valueOf(num)`
  * 构建数组 `dp = new int[str.length()+1]`
  * 初始化：`dp[0]=1; dp[1]=1;` 
  * dp 数组的下标为 2 开始遍历，其对应的字符为 str 中下标为 1 的字符
    * 将当前位置字符与前一位置字符组合，`int temp=Integer.parseInt(str.subString(i-2,i));`
    * 判断 temp 是否符合要求
      * temp 在 [10,25] 内，`dp[i]=dp[i-1]+dp[i-2];`
      * temp不在 [10,25] 内，`dp[i]=dp[i-1];`
  * 返回 `dp[str.length()]`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int translateNum(int num) {
        String str = String.valueOf(num);
        int[] dp = new int[str.length()+1];
        dp[0]=1;
        dp[1]=1;
        for (int i = 2; i < dp.length; i++) {
            int temp = Integer.parseInt(str.substring(i-2, i));
            if (temp >= 10 && temp <= 25){
                dp[i]=dp[i-1]+dp[i-2];
            }else{
                dp[i]=dp[i-1];
            }
        }
        return dp[str.length()];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







### 26/48. 最长不含重复字符的子字符串

#### （1）题目

请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。



#### （2）思路

* dp[i] 记录以字符串 s 中下标为 i 的字符结尾的最长不含重复字符 的子字符串长度
* 算法流程：
  * 初始化：dp[0]=1
  * 当前字符为 temp，则以前一个字符结尾的最长不含重复字符的子字符串为`str=s.subString(i-dp[i-1],i);`
  * 动态转移：indexOf()会返回字符在该字符串中第一次出现的位置，未出现返回-1
    * `dp[i] = dp[i1-] - s.indexOf(temp);`
  * 记录最大的 dp[i]，`res = Math.max(res, dp[i]);`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length()==0){
            return 0;
        }
        int length = s.length();
        int[] dp = new int[length];
        dp[0]=1;
        int res = 1;
        for (int i = 1; i < length; i++) {
            String str = s.substring(i-dp[i-1],i);
            dp[i]=dp[i-1]-str.indexOf(s.substring(i,i+1));
            res = Math.max(dp[i],res);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```



### 68/19. 正则表达式匹配





### 69/49. 丑数

#### （1）题目

我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。



#### （2.1）思路

* 优先队列，小根堆
* PriorityQueue<Integer> queue记录丑数
  * 堆顶元素即为当前轮次最小的丑数 ugly
  * 若 queue 中不包含 ugly * 2，ugly * 3，ugly * 5 ，则将其加入队列



#### （3.1）实现

```java
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int nthUglyNumber(int n) {
        PriorityQueue<Long> queue = new PriorityQueue<>();
        queue.offer((long)1);
        long res = 1;
        for (int i = 0; i < n; i++) {
            res = queue.poll();
            if (!queue.contains(res*2)){
                queue.offer(res*2);
            }
            if (!queue.contains(res*3)){
                queue.offer(res*3);
            }
            if (!queue.contains(res*5)){
                queue.offer(res*5);
            }
        }
        return (int)res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





#### （2.2）思路

* 动态规划
* a,b,c 分别表示首个乘以 2,3,5 后比 x<sub>n</sub> 的丑数的下标
* x<sub>n+1</sub> = Math.min(x<sub>a</sub> * 2, x<sub>b</sub> * 3, x<sub>c</sub> * 5)，选取的 a,b,c 右移



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        dp[0]=1;
        int a = 0, b = 0, c = 0;
        for (int i = 1; i < n; i++) {
            int x = dp[a]*2, y = dp[b]*3, z = dp[c]*5;
            int temp = Math.min(Math.min(x, y), z);
            dp[i]=temp;
            if (temp == x) a++;
            if (temp == y) b++;
            if (temp == z) c++;
        }
        return dp[n-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







### 70/60. n个骰子的点数

#### （1）题目

把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。 

你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。



#### （2）思路

* 动态规划
* 模拟每次增加骰子的过程，n 个骰子，则有 5n+1 种可能的结果
  * n-1 个骰子的可能结果为 dp，则多一个骰子可能掷出 1~6，概率为 1/6
  * 模拟多的一个骰子的掷出结果，temp 记录 n 个骰子的掷出结果
  * temp[i+j] = dp[i] * 1/6 （0<= j <= 5）



#### （3）实现

```java
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double[] dicesProbability(int n) {
        double init = (double)1/6;
        double[] dp = new double[6];
        Arrays.fill(dp, init);
        for (int i = 1; i < n; i++) {
            double[] temp = new double[5*i+6];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    temp[j+k] += dp[j]*init;
                }
            }
            dp = temp;
        }
        return dp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







## 七、双指针

### 27/18. 删除链表的节点

#### （1）题目

给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。

返回删除后的链表的头节点。



#### （2）思路

* 双指针，temp 记录当前遍历节点，怕热 记录 temp 的前序节点
* 遍历链表
  * temp.val = val 时，删除节点，`pre.next=temp.next;`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode vhead = new ListNode();
        vhead.next = head;
        ListNode pre = vhead, temp = head;
        while (temp != null){
            if (temp.val == val){
                pre.next = temp.next;
                break;
            }
            pre = pre.next;
            temp = temp.next;
        }
        return vhead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 28/22. 链表中倒数第k个节点

#### （1）题目

输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。

例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。



#### （2）思路

* 快慢指针，fast 为快指针，slow 为慢指针
* 算法流程：
  * **快指针 fast 先走 k 步**，`fast=fast.next;`
  * 快慢指针 fast，slow 同时遍历，**当快指针 fast 指向空节点 null 时，slow即指向倒数第 k 个节点**



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode fast = head, slow = head;
        while (k !=0){
            fast=fast.next;
            k--;
        }
        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 29/25. 合并两个排序的链表

#### （1）题目

输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。



#### （2）思路

* 使用**虚拟头节点**合并两个链表



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode temp1 = l1, temp2 = l2, vhead = new ListNode(), tail = vhead;
        while (temp1 != null && temp2 != null){
            if (temp1.val <= temp2.val){
                tail.next=temp1;
                tail=tail.next;
                temp1=temp1.next;
            }else{
                tail.next=temp2;
                tail=tail.next;
                temp2=temp2.next;
            }
        }
        if (temp1 != null){
            tail.next=temp1;
        }
        if (temp2 != null){
            tail.next=temp2;
        }
        return vhead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 30/52. 两个链表的第一个公共节点

#### （1）题目

输入两个链表，找出它们的第一个公共节点。

如下面的两个链表**：**

[![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png)

在节点 c1 开始相交。



#### （2）思路

* 对于两个链表 'AC'，'BC'
* 遍历顺序为 'ACBC' 和 'BCAC'，可以在第一个公共节点相遇
* 算法流程：**指针 A 遍历完链表 headA，再遍历链表 headB；指针 B 遍历完链表 headB，再遍历链表 headA**；指针A，B在两链表的第一个公共节点相遇



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode temp1 = headA, temp2 = headB;
        while (temp1 != temp2){
            if (temp1 == null){
                temp1 = headB;
            }else{
                temp1 = temp1.next;
            }
            if (temp2 == null){
                temp2 = headA;
            }else{
                temp2 = temp2.next;
            }
        }
        return temp1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 31/21. 调整数组顺序使奇数位于偶数前面

#### （1）题目

输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。



#### （2）思路

* 双指针，指针 left 从左向右寻找偶数，指针 right 从右向左寻奇数
* 交换 nums[left] 和 nums[right]
* 跳出循环条件 left = right



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] exchange(int[] nums) {
        int left = 0, right = nums.length-1;
        while (left < right){
            while (left < right && nums[left]%2!=0){//找到第一个偶数
                left++;
            }
            while (left < right && nums[right]%2 == 0){//找到第一个奇数
                right--;
            }
            if (left < right){//交换奇偶数
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        return nums;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 32/57. 和为s的两个数字

#### （1）题目

输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。



#### （2）思路

* nums 为递增排序的数组，使用双指针法
* 指针 left 和 right 分别指向数组的两端
* 循环搜索：
  * 循环条件：left < right
  * nums[left] + nums[right] = target：返回结果
  * nums[left] + nums[right] < target：指针 left 右移
  * nums[left] + nums[right] > target：指针 right 左移



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        int left = 0, right = nums.length-1;
        while (left < right){
            if (nums[left]+nums[right]==target){
                res[0]=nums[left];
                res[1]=nums[right];
                break;
            }
            if (nums[left]+nums[right]<target){
                left++;
            }else{
                right--;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 33/58-I. 翻转单词顺序

#### （1）题目

输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。



#### （2）思路

* 以空格为分割符分割字符串，**若两单词间有 n 个空格，则两个单词间会多出 n-1 个空单词即 ""**
* 倒序遍历单词列表，将单词添加到 StringBuilder，遇到空单词则跳过



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] strs = s.split(" ");
        for (int i = strs.length-1; i >= 0; i--) {
            if (!"".equals(strs[i])){//空字符
                if (sb.length()!=0){
                    sb.append(" ");
                }
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





## 八、排序

### 39/45. 把数组排成最小的数

#### （1）题目

输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。



#### （2）思路

* 对数组 nums 中任意两数字的字符串为 x 和 y，规定排序判断规则为：
  * 若拼接字符串 x+y > y+x，则 x 大于 y
  * 反之，若 x+y < y+x，则 x 小于 y
* 按照上述规则，对 nums 数组排序



#### （3）实现

```java
import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String minNumber(int[] nums) {
        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            str[i]=String.valueOf(nums[i]);
        }
        Arrays.sort(str, new Comparator<String>(){
            public int compare(String s1, String s2){
                return (s1+s2).compareTo(s2+s1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String temp : str){
            sb.append(temp);
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 40/61. 扑克牌中的顺子

#### （1）题目

从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。



#### （2）思路

* 五张牌是顺子的充分条件：
  * 除大小王外，没有重复牌
  * 除大小王外的牌的最大值和最小值满足：max-min<=4
* 对数组排序，统计数组中非 0 元素的最大值和最小值
  * 若非 0 元素重复，返回 false
  * 返回 `max-min<=4`



#### （3）实现

```java
import java.util.Arrays;


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isStraight(int[] nums) {
        int min = 20;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                min = Math.min(nums[i],min);
                if (i!=0&&nums[i]==nums[i-1]){
                    return false;
                }
            }
        }

        return nums[4]-min<=4;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 41/40. 最小的k个数

#### （1）题目

输入整数数组 `arr` ，找出其中最小的 `k` 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。



#### （2）思路

* 小根堆：PriorityQueue
* 将数组 arr 中的元素压入优先队列中，再取队头的 k 个元素



#### （3）实现

```java
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int temp : arr){
            queue.offer(temp);
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 42/41. 数据流中的中位数

#### （1）题目

如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

* void addNum(int num) - 从数据流中添加一个整数到数据结构中。
* double findMedian() - 返回目前所有元素的中位数。



#### （2）思路

* 使用两个优先队列q1, q2作为数据结构
  * q1为大根堆，q2为小根堆
  * 保证 **q1.size() = q2.size() 或 q1.size() = q2.size()+1**
* void addNum(int num) :
  * q1.size() == 0：数据流中没有元素
    * 将元素 num 加入队列 q1中，`q1.offer(num)`
  * q1.size() != 0：数据流中有元素，比较 num 和 **q1的队头元素 peek**
    * num <= peek：将元素 num 加入队列 q1 中，`q1.offer(num)`
    * num > peek：将元素 num 加入队列 q2 中，`q2.offer(num)`
    * 调整队列 q1 和 q2 中元素个数，保证 **q1.size() = q2.size() 或 q1.size() = q2.size()+1**
* double findMedian()：
  * `q1.size() == q2.size()`：中位数为队列 q1 和 q2 的队首元素的中间值
  * `q1.size() != q2.size()`：中位数为队列 q1 的队首元素



#### （3）实现

```java
import java.util.Comparator;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class MedianFinder {
    PriorityQueue<Integer> q1, q2;
    /** initialize your data structure here. */
    public MedianFinder() {
        q1 = new PriorityQueue<>(new Comparator<Integer>() {//q1大根堆
            @Override
            public int compare(Integer integer, Integer t1) {
                return t1-integer;
            }
        });
        q2 = new PriorityQueue<>();//q1小根堆
    }
    
    public void addNum(int num) {
        if (q1.size() == 0){
            q1.offer(num);
        }else{
            if (num<=q1.peek()){
                q1.offer(num);
            }else{
                q2.offer(num);
            }
            if (q1.size() > q2.size()+1){
                q2.offer(q1.poll());
            }else if (q2.size() > q1.size()){
                q1.offer(q2.poll());
            }
        }
    }
    
    public double findMedian() {
        if (q1.size() == q2.size()){
            return ((double) q1.peek()+q2.peek())/2;
        }
        return q1.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
//leetcode submit region end(Prohibit modification and deletion)
```



## 九、分治算法

### 48/07. 重建二叉树

#### （1）题目

输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。

假设输入的前序遍历和中序遍历的结果中都不含重复的数字。



#### （2）思路

* 根据前序和中序遍历的结果重建二叉树
* 前序遍历的第一个节点为根节点，在中序遍历中找到根节点的下标 index，index之前为左子树，index之后为右子树
* 递归构建左右子树



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
    }
    public TreeNode build(int[] preorder, int[] inorder, int prel, int prer, int inl, int inr){
        if (prel > prer){
            return null;
        }
        TreeNode root = new TreeNode(preorder[prel]);
        int index = inl;//记录中序遍历中根节点位置
        for (; index <= inr; index++){
            if (inorder[index]==root.val) break;
        }
        int num = index-inl;//左子树节点数
        root.left = build(preorder, inorder, prel+1,prel+num, inl, index-1);
        root.right = build(preorder, inorder, prel+num+1, prer, index+1, inr);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 49/16. 数值的整数次方

#### （1）题目

实现 [pow(*x*, *n*)](https://www.cplusplus.com/reference/valarray/pow/) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。



#### （2）思路

* n = a<sub>0</sub> * 2<sup>0</sup> + a<sub>1</sub> * 2<sup>1</sup> + …… + a<sub>n</sub> * 2<sup>n</sup> (a=0或1)
* x<sup>n</sup> = x<sup>(a<sub>0</sub> * 2<sup>0</sup> + a<sub>1</sub> * 2<sup>1</sup> + …… + a<sub>n</sub> * 2<sup>n</sup> )</sup> = x <sup> a<sub>0</sub> * 2<sup>0</sup> </sup> * x<sup>a<sub>1</sub> * 2<sup>1</sup> </sup> * …… * x<sup>a<sub>n</sub> * 2<sup>n</sup></sup> = a<sub>0</sub> * x<sup>2<sup>0</sup></sup> * a<sub>1</sub> * x<sup>2<sup>1</sup></sup> …… * a<sub>n</sub> * x<sup>2<sup>n</sup></sup>



#### （3）实现

```java
class Solution {
    public double myPow(double x, int n) {
        double res = 1.0;
        long tempn = n;
        if(n < 0){
            tempn *= -1;
        }
        while(tempn!=0){
            if((tempn&1) == 1){
                res *= x;
            }
            x *= x;
            tempn >>= 1;
        }
        if(n<0){
            res = 1/res;
        }
        return res;
    }
}
```



### 50/33. 二叉搜索树的后序遍历序列

#### （1）题目

输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 `true`，否则返回 `false`。假设输入的数组的任意两个数字都互不相同。



#### （2）思路

* 二叉搜索树：
  * 左子树中所有节点的值 < 根节点的值 && 右子树中所有节点的值 > 根节点的值
  * 左子树和右子树也&**均为二叉搜索树**
* 算法流程：
  * `public boolean judge(int[] postorder, int left, int right)`：判断 left~right 范围内的序列是否为二叉搜索树的后序遍历序列
    * 终止条件：left >= right，当前树只有一个节点或没有节点，返回 true
    * 划分左右子树，找到第一个比根节点大的值的下标，lleft
    * 判断右子树区间节点是否满足要求，即 [lleft, right) 内的节点的值是否均比根节点大
      * 不满足，返回 false
    * 递归判断其左右子树是否为二叉搜索树
      * `judge(postorder, left, lleft-1)`
      * `judge(postorder, lleft, right-1)`
    * 返回 ``judge(postorder, left, lleft-1)`&&`judge(postorder, lleft, right-1)``



#### （3）实现

```java
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        return judge(postorder, 0, postorder.length-1);
    }
    public boolean judge(int[] postorder, int left, int right){
        if(left >= right){
            return true;
        }
        int lleft = left;
        for(;lleft < right; lleft++){
            if(postorder[lleft]>=postorder[right]){
                break;
            }
        }
        int rright = lleft;
        for(;rright < right; rright++){
            if(postorder[rright]<postorder[right]){
                return false;
            }
        }
        return judge(postorder, left, lleft-1)&&judge(postorder, lleft, right-1);
    }
}
```





### 71/17. 打印 1 到最大的 n 位数

#### （1）题目

输入数字 `n`，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。



#### （2）思路

* no 



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] printNumbers(int n) {
        int num = (int)Math.pow(10, n);
        int[] res = new int[num-1];
        for (int i = 0; i < res.length; i++) {
            res[i]=i+1;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 72/51. 数组中的逆序对

#### （1）题目

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。



#### （2）思路

* 归并排序

* 合并阶段 本质上是 合并两个排序数组 的过程，而每当遇到 **左子数组当前元素 > 右子数组当前元素 **时，意味着 **「左子数组当前元素 至 末尾元素」** 与 「右子数组当前元素」 构成了**若干 「逆序对」** 
* 归并阶段，当遇到左子数组当前元素 > 右子数组当前元素时，**左子数组的剩余元素个数** 即为右子数组当前元素所构成的**逆序对个数**



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[] temp;
    int res = 0;
    public int reversePairs(int[] nums) {
        temp = new int[nums.length];
        sort(nums, 0, nums.length-1);
        return res;
    }
    public void sort(int[] nums, int left, int right){
        if (left >= right){
            return;
        }
        int mid = left +(right-left)/2;
        sort(nums, left, mid);
        sort(nums, mid+1, right);
        int i = left, j = mid + 1, index = left;
        while (i <= mid && j <= right){
            if (nums[i] <= nums[j]){
                temp[index++]=nums[i++];
            }else{
                res += mid-i+1;
                temp[index++]=nums[j++];
            }
        }
        while (i <= mid){
            temp[index++]=nums[i++];
        }
        while (j <= right){
            temp[index++]=nums[j++];
        }
        for (int k = left; k <= right; k++) {
            nums[k]=temp[k];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```









## 十、位运算

### 51/15. 二进制中1的个数

#### （1）题目

编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。

 

提示：

* 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
* 在 Java 中，编译器使用 二进制补码 记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。



#### （2）思路

* 无符号右移 `>>>`
* n &= (n-1) ：将 n 的二进制表达中最右边的 1 变成 0，其余保持不变



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0){
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 52/65. 不用加减乘除做加法

#### （1）题目

写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。



#### （2）思路

* 两数之和可分为两部分：不进位的和 以及 只考虑进位的和
* 算法流程：
  * 对于任意两数 a ， b，**不进位的和为 a ^ b**，**只考虑进位的和为 (a&b) << 1**
  * 跳出条件：`b==0`，即当进位为0时，不进位的和即为结果
  * `a=a^b; `
  * `b=(a&b)<<1;`（注意使用中间值记录a和b）
  * 循环求取 a 和 b之间的不进位的和以及进位的和
  * 返回 a



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int add(int a, int b) {
        while (b != 0){
            int temp = a ^ b;//不进位
            b = a & b;//进位
            b <<= 1;//进位左移
            a = temp;
        }
        return a;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 53/56-I. 数组中数字出现的次数

#### （1）题目

一个整型数组 `nums` 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。



#### （2）思路

* 对于数组中两个只出现一次的数字 num1, num2，其二进制表达式中必然存在某一位不同，**num1 ^ num2的二进制表达式的该位为 1**，而 x^x = 0
* 对于数组 nums 中的每个元素，按位取异或，结果为  res != 0
* 找出 res 的二进制表达式中第 index 位不为0，即 num1 和 num2 的第 index 位不相同
* 将数组中的元素分为两组，一组中元素的二进制表达式的第 index 位为 1，一组元素的二进制表达式的第 index 位为 0
* 对两组元素分别按位取异或，结果即为只出现依次的数字 num1，num2



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] singleNumbers(int[] nums) {
        int res = 0;
        for (int temp : nums){
            res ^= temp;
        }
        int diff = 1;//记录两个数的二进制表达式中该位上的值不同
        while ((diff&res) == 0){
            diff<<=1;
        }
        int num1 = 0, num2 = 0;
        for (int temp : nums){
            if ((temp&diff)==0){
                num1 ^= temp;
            }else{
                num2 ^= temp;
            }
        }
        int[] number = {num1,num2};
        return number;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```



### 54/56-II. 数组中数字出现的次数

#### （1）题目

在一个数组 `nums` 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。

 

#### （2）思路

* 使用长度为 32 的 bits 数组记录数组 nums 中每一个元素的二进制表达式中 1 出现的次数
* 对 bits 中的元素对 3 取余，即为该数的二进制表达式



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int singleNumber(int[] nums) {
        int[] bits = new int[32];
        for (int temp : nums){
            int index = 0;
            while (temp != 0){
                bits[index++] += temp & 1;
                temp >>= 1;
            }
        }
        for (int i = 0; i < bits.length; i++) {
            bits[i] %= 3;
        }
        int res = 0, temp = 1;
        for (int i = 0; i < bits.length; i++) {
            if (bits[i]!=0){
                res += temp;
            }
            temp <<= 1;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







## 十一、数学

### 55/39. 数组中出现次数超过一半的数字

#### （1）题目

数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。



#### （2）思路

* 摩尔投票



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int majorityElement(int[] nums) {
        int cand = 0, num = 0;
        for (int temp : nums){
            if (cand == temp){
                num++;
            }else{
                if (num == 0){
                    cand = temp;
                    num = 1;
                }else{
                    num--;
                }
            }
        }
        return cand;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```



### 56/66. 构建乘积数组

#### （1）题目

给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。



#### （2）思路

* 前缀积+后缀积
* pre[i] 记录数组中第 [0, i) 元素的乘积
* post[i] 记录数组中第 (i, n] 元素的乘积 
* 结果 `res[i] = pre[i] * post[i]`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] constructArr(int[] a) {
        if (a.length == 0){
            return new int[0];
        }
        int[] pre = new int[a.length];
        int[] post = new int[a.length];
        pre[0]=1;
        post[a.length-1]=1;
        for (int i = 1; i < pre.length; i++) {//a 中 [0, i)的元素乘积
            pre[i] = pre[i-1]*a[i-1];
        }
        for (int i = post.length-2; i >= 0; i--) {//a 中 (i,n]的元素乘积
            post[i] = post[i+1]*a[i+1];
        }
        int[] res = new int[a.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = pre[i] * post[i];
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 57/14-I. 减绳子

#### （1）题目

给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。



#### （2）思路

* **每段绳子剪成 3 时**，得到最大乘积
* 当剩余绳子长度为 1 时，需要分为 2 和 2
* 特殊情况讨论，绳子长度为 2，3



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int cuttingRope(int n) {
        //每一段剪成 3 时，乘积取得最大值
        if (n <= 3){//特殊情况：2，3
            return n-1;
        }
        int num = n/3, remain=n%3, res = 0;
        if (remain == 0){
            res = (int) Math.pow(3, num) ;
        }else if (remain == 1){
            res = (int) Math.pow(3, num-1) * 4;
        }else{
            res = (int) Math.pow(3, num) * 2;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 58/57-II. 和为 s 的连续正数序列

#### （1）题目

输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。

序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。



#### （2）思路

* 双指针



#### （3）实现

```java
import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] findContinuousSequence(int target) {
        ArrayList<int[]> res = new ArrayList();

        int left = 1, right = 2;

        int sum = left + right;
        while (left < target && right < target){
            if (sum == target){
                int[] temp = new int[right-left+1];
                for (int i = left; i <= right; i++) {
                    temp[i-left]=i;
                }
                res.add(temp);
                right++;
                sum += right;
            }else if (sum < target){//右指针右移
                right++;
                sum += right;
            }else{//左指针右移
                sum -= left;
                left++;
            }
        }
        return res.toArray(new int[0][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 59/62. 圆圈中最后剩下的数字

#### （1）题目

0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。

例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。

 

#### （2）思路

* 对于每一轮中选中的数字相交于上一轮中向前移动了 m 个位置
* 最后一轮中，只存在一个元素，其下标为 0 
  * 在倒数第二轮中，此元素下标为 (0+m) % 2：该元素在倒数第二轮中的下标为 **0+m**，由于环的存在并只有两个元素，故真实下标为 (0+m)%2
  * 依次向前递推，直到第一轮中，找出该元素的下标



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lastRemaining(int n, int m) {
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = (res + m)%i;//每次向前移动 m 个位置
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 73/14-II. 减绳子 II

#### （1）题目

给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。



#### （2）思路

* 每个绳段**长度为 3 时**，乘积最大
* 强制转换符优先级 > 取余，乘法的优先级



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int max = 1000000007;
    public int cuttingRope(int n) {
        if (n <= 3){
            return n-1;
        }
        int m = n/3, remain = n%3;
        if (remain == 0){
            return (int)cal(m);
        }
        if (remain == 1){
            return (int)(cal(m-1)*4%max);
        }

        return (int)(cal(m)*2%max);

    }
    public long cal(int m){
        long res = 1;
        for (int i = 0; i < m; i++) {
            res = res * 3 % max;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 74/44. 数字序列中某一位的数字

#### （1）题目

数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。

请写一个函数，求任意第n位对应的数字。



#### （2）思路

* 模拟



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findNthDigit(int n) {
        int digit = 1;
        long start = 1, count = 9;
        while (n > count){
            n-=count;
            digit++;
            start *= 10;
            count = digit * start * 9;
        }
        long target = (n-1)/digit+start;
        String str = String.valueOf(target);
        return str.charAt((n-1)%digit)-'0';
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 75/43. 1~n整数中 1 出现的次数

#### （1）题目

输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。

例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。



#### （2）思路







## 十二、模拟

### 60/29. 顺时针打印矩阵

#### （1）题目

输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。



#### （2）思路

* 模拟顺时针打印矩阵
* left，right，up，down分别表示当前打印圈次的左、右、上、下边界
* 依次打印
  * 第 up 行 **[left, right)** 元素
  * 第 right 列 **[up, down]** 元素
  * **判断所有元素是否打印结束**，防止当前只有一列或一行元素时，出现重复打印
  * 第 down 行 **(right, left)** 元素
  * 第 left 列 **(down, up]** 元素
  * 更新边界：left++, right--, up++, down--



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0){
            return new int[0];
        }
        int n = matrix.length, m = matrix[0].length;
        int left = 0, right = m-1, up = 0, down = n-1;
        int[] res =  new int[n*m];
        int index = 0;

        while (left <= right && up <= down){
            for (int i = left; i < right; i++) {
                res[index++] = matrix[up][i];
            }
            for (int i = up; i <= down; i++) {
                res[index++] = matrix[i][right];
            }
            if (index == n*m){
                break;
            }
            for (int i = right-1; i > left; i--) {
                res[index++] = matrix[down][i];
            }
            for (int i = down; i > up; i--) {
                res[index++] = matrix[i][left];
            }
            left++;
            right--;
            up++;
            down--;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 61/31. 栈的压入和弹出序列

#### （1）题目

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。



#### （2）思路

* 模拟栈的压入和弹出
  * 将当前压入序列的元素压入栈中
  * **若栈顶元素和弹出序列当前元素相等，则循环弹出栈顶元素**
* 返回栈内元素是否为空



#### （3）实现

```java
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        LinkedList<Integer> list = new LinkedList<>();
        int pop = 0;
        for (int temp : pushed){
            list.offerLast(temp);
            while ((!list.isEmpty()) && list.peekLast() == popped[pop]){
                list.pollLast();
                pop++;
            }

        }
        return list.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```

