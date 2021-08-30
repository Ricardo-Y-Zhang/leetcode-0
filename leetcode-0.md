# leetcode 21/7/29

## 一、栈

### 1/20. 有效的括号



#### （1）题目

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。



#### （2）思路

* 利用hashMap存储括号对，右括号为key，左括号为value

* 遍历字符串，利用栈进行存储
* 遍历元素为左括号时，元素进栈；元素为右括号时，栈顶元素出栈（注意栈是否为空），比较栈顶元素与右括号所对应的value值是否相等；遍历结束后，注意判断栈是否为空



#### （3）实现

```java
class Solution {
    public boolean isValid(String s) {
        //1. 创建hashMap
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        //2. 遍历字符串
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
			
            //右括号
            if (map.containsKey(c)){
                if (stack.isEmpty() || stack.pop() != map.get(c)){
                    return false;
                }
            }else{//左括号
                stack.add(c);
            }
        }

        return stack.isEmpty();
    }
}
```



### 2/94. 二叉树的中序遍历



#### （1）题目

给定一个二叉树的根节点 `root` ，返回它的 **中序** 遍历。



#### （2）思路

* 二叉树的中序遍历顺序为**左根右**
* 手动维护一个栈和一个指向树节点的指针，**模仿递归的中序遍历**
* root指向当前遍历的节点，root非空时，root入栈，root指向左孩子节点（无需考虑左孩子节点是否为空）；root为空时，栈顶元素出栈，加入list，root指向其右孩子节点（无需考虑右孩子节点是否为空）



#### （3）实现

* 非递归

```java
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
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();

        while(root != null || !stack.isEmpty()){
			
            if (root != null){
                stack.add(root);
                root = root.left;
            }else {
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }
        }
        return list;
    }
}
```



* 递归

```java
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
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();

        inorder(list, root);
        return list;
    }
    
    public void inorder(List<Integer> list, TreeNode root){
        if (root != null){
            inorder(list, root.left);
            list.add(root.val);
            inorder(list, root.right);
        }
    }
}
```



### 3/114. 二叉树展开为链表



#### （1）题目

给你二叉树的根结点 root ，请你将它展开为一个单链表：

展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
展开后的单链表应该与二叉树 先序遍历 顺序相同。



#### （2）思路

* 手动维护一个栈和一个指针，模仿二叉树先序遍历，将节点存储于list中；遍历后，将list中节点以尾插法连接，左孩子节点置为null
* 指针不为空，节点加入list并入栈，指针指向左孩子节点；指针为空，栈顶节点出栈，指针指向其右孩子节点



#### （3）实现

* 非递归

```java
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
    public void flatten(TreeNode root) {
        if (root != null) {
            ArrayList<TreeNode> list = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            while(root != null || !stack.isEmpty()){
                if (root != null) {
                    list.add(root);
                    stack.add(root);
                    root = root.left;
                }else{
                    root = stack.pop();
                    root = root.right;
                }
            }

            root = list.get(0);
            var tail = root;
            for (int i = 1; i < list.size(); i++) {
                tail.left = null;
                tail.right = list.get(i);
                tail = tail.right;
            }
        }
    }
}
```



* 递归

```java
class Solution{
    public void flatten(TreeNode root){
        ArrayList<TreeNode> list = new ArrayList<>();
        preorder(list, root);
        var tail = root;
        for (int i = 1; i < list.size(); i++) {
            tail.right = list.get(i);
            tail.left = null;
            tail = tail.right;
        }
    }

    private void preorder(ArrayList<TreeNode> list, TreeNode root){
        if (root != null) {
            list.add(root);
            preorder(list, root.left);
            preorder(list, root.right);
        }
    }
}
```



### 4/155. 最小栈



#### （1）题目

设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

push(x) —— 将元素 x 推入栈中。
pop() —— 删除栈顶的元素。
top() —— 获取栈顶元素。
getMin() —— 检索栈中的最小元素。



#### （2）思路

* 模拟一个栈并维护当前栈中的最小值
* 使用ArrayList模拟一个栈；同样使用ArrayList维护当前栈中的最小值，n位置上的元素为栈中0~n位置上的最小值
* push(x)时，比较x与前一个最小值，更新当前位置上的最小值



#### （3）实现

```java
package leetcode.editor.cn;

import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class MinStack {

    private ArrayList<Integer> list;
    private ArrayList<Integer> minList;
    /** initialize your data structure here. */
    public MinStack() {
        list = new ArrayList<>();
        minList = new ArrayList<>();//前 n 个元素的最小值
    }
    
    public void push(int val) {
        list.add(val);
        if (minList.size() == 0){
            minList.add(val);
        }else{
            int lastMin = minList.get(minList.size() - 1);
            int min = val < lastMin ? val : lastMin;
            minList.add(min);
        }
    }
    
    public void pop() {
        list.remove(list.size() - 1);
        minList.remove(minList.size() - 1);
    }
    
    public int top() {
        return list.get(list.size() - 1);
    }
    
    public int getMin() {
        return minList.get(minList.size() - 1);
    }
}

```



### 5/234. 回文链表



#### （1）题目

请判断一个链表是否为回文链表。

**进阶：**
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？



#### （2）思路

* O(n)空间复杂度和O(n)时间复杂度：遍历链表，将其value存于ArrayList中；遍历ArrayList，比较k和n-1-k位置元素是否相同



#### （3）实现

```java
package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        ArrayList<Integer> list = new ArrayList<>();
        
        while (head != null){
            list.add(head.val);
            head = head.next;
        }
        
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != list.get(list.size()-1-i)){
                return false;
            }
        }
        return true;
    }
}
```



### 6/394. 字符串解码



#### （1）题目

给定一个经过编码的字符串，返回它解码后的字符串。

编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。

你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。

此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入



#### （2）思路

* 维护一个栈，遍历字符串
  * 非 ']' 元素入栈
  * 遇到 ']' 元素时，栈顶元素出栈拼接起来为temp，直至栈顶元素为 '[' 
  * '[' 出栈，栈顶元素为重复次数k（注意k>=10情况），str = k * temp
  * 再将拼接后的str压入栈
* 遍历后，再将栈内元素拼接起来，即为解码后的字符串



#### （3）实现

```java
package leetcode.editor.cn;

import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String decodeString(String s) {

        Stack<String> stack = new Stack<>();
        String str = "";
        //遍历字符串
        for (int i = 0; i < s.length(); i++) {
            String sub = s.substring(i, i+1);
            if (sub.equals("]")){
                String temp = "", temp0 ="";
                //拼接栈内字符串
                while (!stack.isEmpty() && !stack.peek().equals("[")){
                    temp = stack.pop() + temp;
                }
                stack.pop();//"["出栈
                String times ="";
                //拼接重复次数k
                while (!stack.isEmpty() && (stack.peek().charAt(0) >= '0' && stack.peek().charAt(0) <= '9')){
                    times = stack.pop() + times;
                }
                int time = Integer.parseInt(times);
                //字符串重复k次拼接
                for (int j = 0; j < time; j++) {
                    temp0 += temp;
                }
                //拼接后的字符串入栈
                stack.add(temp0);
            }else{
                stack.add(sub);
            }
        }
        //栈内字符串拼接
        for (int i = 0; i < stack.size(); i++) {
            str += stack.get(i);
        }

        return str;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


```







### 7/581. 最短无序连续子数组



#### （1）题目

给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

请你找出符合题意的 最短 子数组，并输出它的长度。



#### （2）思路

* O(n<sup>2</sup>)时间复杂度：

  * 使用Arrays.sort()对复制的数组进行排序
  * 比较原数组与排序后的数组，记录第一个不相同元素位置left和最后一个不相同元素位置right
  * right-left+1即为最短无序连续数组的长度

* O(n)时间复杂度：

  * 维护一个ArrayList，n位置记录nums数组中0~n上的最大值

  * 遍历数组，将nums[n]与ArrayList.get(n-1)比较

  * 遍历过程中，记录nums[n] < ArrayList.get(n-1)中最小的nums[n]，即为乱序子数组中的最小元素min；记录nums[n] < ArrayList.get(n-1)中最后一个元素位置n，即为乱序子数组中的最后一个元素位置lastIndex

  * 再次遍历数组，找出第一个比min大的元素，即为乱序子数组中的最小元素min应该在的位置minIndex

  * 最短乱序子数组长度即为lastIndex - minIndex + 1

    

#### （3）实现

* O(n<sup>2</sup>)时间复杂度

```java
package leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int[] clone = nums.clone();
        Arrays.sort(clone);
        
        int length = 0, left = -1, right = -2;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != clone[i]){
                if (left == -1){
                    left = i;
                }else{
                    right = i;
                }
            }
        }

        return right - left + 1;
    }

  
}
//leetcode submit region end(Prohibit modification and deletion)
```



* O(n)时间复杂度

```java
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int findUnsortedSubarray(int[] nums) {
        if (nums.length == 0){
            return 0;
        }else{
            int min = 100001, lastInd = -1, minInd = 0 ;
            ArrayList<Integer> preMax = new ArrayList<>();
            preMax.add(nums[0]);

            for (int i = 1; i < nums.length; i++) {
                int pre = preMax.get(i-1);
                if (nums[i] < pre){
                    lastInd = i;
                    if (nums[i] < min){
                        min = nums[i];
                    }
                    preMax.add(pre);
                }else{
                    preMax.add(nums[i]);
                }
            }

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > min){
                    minInd = i;
                    break;
                }
            }

            return lastInd - minInd + 1;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```



### 8/739. 每日温度



#### （1）题目

请根据每日 `气温` 列表 `temperatures` ，请计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 `0` 来代替



#### （2）思路

* 维护两个个栈，遍历temperatures
* stack1中存放气温下标，stack2中存放每日气温
* 遍历到第i个元素时，与stack2栈顶元素比较
  * 若比栈顶元素大，stack1和stack2中栈顶元素出栈，则需要等stack1.pop()-i天；并继续与栈顶元素比较
  * 若比栈顶元素小，temperatures[n]压入栈stack2，i压入栈stack1
* 遍历结束后，stack1中剩余位置对应的天数为0
* **题解**：维护一个储存下标的栈，遍历数组，第i个温度：比栈顶元素所对应的温度高，出栈，等待天数为 i - stack.peek()，直至比栈顶元素对应的温度低，相等或栈为空，i入栈



#### （3）实现

* 双栈

```java
package leetcode.editor.cn;

import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        int[] wait = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            if (i == 0){
                stack1.add(i);
                stack2.add(temperatures[i]);
            }else{
                int now = temperatures[i];
                while (!stack2.isEmpty() && stack2.peek() < now){
                    int day = i - stack1.peek();
                    wait[stack1.peek()] = day;
                    stack1.pop();
                    stack2.pop();
                }
                stack1.add(i);
                stack2.add(temperatures[i]);
            }
        }
        return wait;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```



* 题解：单栈

```java
	public int[] dailyTemperatures(int[] temperatures){
        Stack<Integer> indexStack = new Stack<>();
        int[] wait = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            if (i != 0) {
                while (!indexStack.isEmpty() && temperatures[indexStack.peek()] < temperatures[i]){
                    wait[indexStack.peek()] = i - indexStack.peek();
                    indexStack.pop();
                }
            }
            indexStack.add(i);
        }

        return wait;
    }
```





### 9/32. 最长有小括号(hard)



#### （1）题目

给你一个只包含 `'('` 和 `')'` 的字符串，找出最长有效（格式正确且连续）括号子串的长度。



#### （2）思路

* 维护一个栈，遍历字符串，第一个元素需为'('，入栈，当前元素需为栈顶元素对应的value；违反时，清空栈内元素，有效括号为stack.size()/2；直至遍历完，记录最大的有效括号长度



#### （3）实现

```
package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestValidParentheses(String s) {
        int maxLength = 0;
        int length = 0;
        int num = 0;
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            String ch = s.substring(i, i+1);

            if (ch.equals("(")){
                stack.add(ch);
                num++;
            }else{
                if (num == 0){
                    length = 0;
                    while (!stack.isEmpty()){
                        length += Integer.parseInt(stack.pop());
                    }
                    stack.clear();
                    maxLength = length > maxLength ? length : maxLength;
                }else{
                    length = 2;
                    boolean flag = false;
                    while (!stack.isEmpty() && flag == false){
                        if (stack.peek().equals("(")){
                            stack.pop();
                            num--;
                            stack.add(String.valueOf(length));
                            flag = true;
                        }else{
                            length += Integer.parseInt(stack.pop());
                        }
                    }
                }
            }
        }
        System.out.println("stack = " + stack);
        length = 0;
        while (!stack.isEmpty()){
            if (stack.peek().equals("(")){
                maxLength = length > maxLength ? length : maxLength;
                length = 0;
                stack.pop();
            }else{
                length += Integer.parseInt(stack.pop());
            }
        }
        maxLength = length > maxLength ? length : maxLength;
        return maxLength;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


```





### 10/42. 接雨水(hard未解决)



#### （1）题目

给定 *n* 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。



#### （2）思路

* 维护一个栈，遍历数组height
* height[n] > stack.peek()：
  * 栈顶元素出栈，直至height[n] <= stack.peek()
* height[n] <= stack.peek()
  * 元素入栈







## 二、贪心

### 11/11. 盛最多水的容器



#### （1）题目

给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器。



#### （2）思路

* 贪心算法（双指针）：左指针left和右指针right分别指向数组的两端
* 移动两个指针中指向较小的height的指针，直至两指针相遇，并记录最大储水量
* 证明：
  * 令左右指针分别指向x,y，假设x<=y，同时x，y之间的距离为t，则组成容器容量为min(x,y) * t
  * 若不移动x，向左移动y，指向y<sub>1</sub>，同时x，y<sub>1</sub>之间的距离为t<sub>1</sub>，则组成容器容量为min(x, y<sub>1</sub>) * t<sub>1</sub>
  * y<sub>1</sub><=y时，min(x, y<sub>1</sub>)<=min(x, y)；y<sub>1</sub>>y时，y<sub>1</sub>>y>=x，min(x, y<sub>1</sub>) = x = min(x, y)；因此min(x, y<sub>1</sub>)<=min(x, y<sub>1</sub>)
  * 又t<sub>1</sub><t，则min(x, y<sub>1</sub>) * t<sub>1</sub><min(x,y) * t；即无论怎么向左移动y，得到的容器容量均小于移动前的容器容量
  * 即移动较小的指针时，可能得到容量更大的容器；移动较大的指针时，只能得到容量更小的容器



#### （3）实现

```java
package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0, j = height.length-1; i != j; ){
            int temp = Math.min(height[i], height[j]) * (j - i);
            max = temp > max ? temp : max;
            if (height[i] < height[j]){
                i++;
            }else{
                j--;
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 12/55. 跳跃游戏



#### （1）题目

给定一个非负整数数组 `nums` ，你最初位于数组的 **第一个下标** 。

数组中的每个元素代表你在该位置可以跳跃的最大长度。

判断你是否能够到达最后一个下标。



#### （2）思路

* 贪心算法：在每一位置上都跳到**可以跳到更远的位置**的位置上，即跳到拥有最大的nums[i] + i的位置i上 
* 遍历nums，在第n位上，选择可以达到的nums[i] + i的最大值的位置上；能达到最后一个下标，返回true，否则，返回false
* **题解**：
  * 遍历数组，并维护一个可到达的最远距离max
  * 遍历过程中，对于当前遍历位置i，如果其在可到达的最远距离范围内，使用i+nums[i]更新可到达的最远距离
  * 若遍历过程中可到达的最远距离大于数组的最后一个下标，返回true；反之，在遍历结束后，仍无法到达最后一个位置，返回false



#### （3）实现

```java

package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canJump(int[] nums) {
        int max = 0, now = 0;
        while (true){
            int maxi = 0;
            for (int i = now; i <= max && i < nums.length; i++) {
                maxi = i + nums[i];
                if (maxi >= max){
                    max = maxi;
                    now = i;
                }
            }
            if (max >= nums.length-1){
                return true;
            }
            
            if (nums[now] == 0){
                return false;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```



* 题解

```java

package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            //在可到达的最远距离范围内
            if (i <= max){
                int tempMax = i + nums[i];
                max = tempMax > max ? tempMax : max;
                if (max >= nums.length - 1){
                    return true;
                }
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```





### 13/406. 根据身高重建队列



#### （1）题目

假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。

请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。



#### （2）思路

* 按照hi从高到低，ki从低到高的顺序排序
* 维护一个ArrayList，遍历people数组，people[i] = [hi, ki] ，其中ki即为people[i]在目前ArrayList中位置，使用ArrayList.add(ki, people[i])将people[i]插入ArrayList中
* 证明：
  * 令people[i] = [hi, ki]，而按照hi从高到低排序，即保证后续people[n]中不存在h<sub>n</sub>>h<sub>i</sub>
  * 又因按照k<sub>i</sub>从低到高排序，即当前people[i]比后续与h<sub>i</sub>相等的people[n]靠前
  * 综上，后续不在people[n]使h<sub>n</sub>>h<sub>i</sub>且后续与h<sub>i</sub>相等的people[n]在people[i]的后面；即后续的people[n]不影响当前people[i]在ArrayList中的位置



#### （3）实现

```java

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                if (ints[0] != t1[0]){
                    return -Integer.compare(ints[0], t1[0]);
                }
                return Integer.compare(ints[1], t1[1]);
            }
        });

        ArrayList<int[]> ints = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            ints.add(people[i][1], people[i]);
        }
        int[][] ints1 = new int[people.length][2];
        for (int i = 0; i < ints.size(); i++) {
            ints1[i] = ints.get(i);
        }
        return ints1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```







### 14/621. 任务调度器（百度二面面试题）



#### （1）题目

给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。

然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。

你需要计算完成所有任务所需要的 最短时间 。



#### （2）思路

![image-20210802165036462](C:\Users\Ricardo.Y\AppData\Roaming\Typora\typora-user-images\image-20210802165036462.png)

* 贪心策略：先安排出现次数最多的任务，让这个任务两次执行的时间间隔正好为n，在这个时间间隔内填充其他的任务
* maxTimes：出现次数最多的任务出现的次数；maxCount：出现maxTimes次的任务数量
* 所需时间为：**(maxTimes - 1) * (n + 1) + maxCount**



#### （3）实现

```java

package leetcode.editor.cn;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            if (hashMap.containsKey(tasks[i])){
                hashMap.replace(tasks[i], hashMap.get(tasks[i])+1);
            }else{
                hashMap.put(tasks[i], 1);
            }
        }

        int maxTime = 0;
        int maxCount = 0;
        Set<Character> keySet = hashMap.keySet();
        for(Character i : keySet){
            int times = hashMap.get(i);
            if (times == maxTime){
                maxCount++;
            }else if(times > maxTime){
                maxTime = times;
                maxCount = 1;
            }
        }

        int res = (n + 1) * (maxTime - 1) + maxCount;
        return res > tasks.length ? res : tasks.length;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


```





## 三、位运算



#### 15/87. 子集



#### （1）题目

给你一个整数数组 `nums` ，数组中的元素 **互不相同** 。返回该数组所有可能的子集（幂集）。

解集 **不能** 包含重复的子集。你可以按 **任意顺序** 返回解集。



#### （2）思路

* n个元素，每一个元素都有两种状态，取或不取
* 不妨将每个元素的状态设为0或1，0代表不取，1代表取；即在n位的二进制表示式中，n位上每个位置上的0或1代表当前位的数取或不取
* 从0 ~ 2<sup>n</sup>-1，获取二进制中每一位的值（&1），将值为1的位置对应的数加入list中，值为0的位置对应的数不加入list中



#### （3）实现

```java
package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < (int) Math.pow(2, nums.length); i++) {
            int temp = i;
            ArrayList<Integer> list = new ArrayList<>();
            for (int j = 0; j < nums.length && temp != 0; j++) {
                int state = temp & 1;
                if (state == 1){
                    list.add(nums[j]);
                }
                temp >>= 1;
            }
            res.add(list);
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```





### 16/136. 只出现一次的数



#### （1）题目

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？



#### （2）思路

* **x ^ x = 0**
* 将数组中数按位异或，出现两次的元素，按位异或结果为0；最后的结果即为只出现一次的元素



#### （3）实现

```java

package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```





### 17/287. 寻找重复数（未解决）



#### （1）题目

给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。

假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。

你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。



#### （2）思路

* 我们先设置慢指针slow 和快指针fast ，慢指针每次走一步，快指针每次走两步，根据「Floyd 判圈算法」两个指针在有环的情况下一定会相遇，此时我们再将 slow 放置起点 00，两个指针每次同时移动一步，相遇的点就是答案



链表中的环
假设有这样一个样例：[1,2,3,4,5,6,7,8,9,5]。如果我们按照上面的循环下去就会得到这样一个路径: 1 2 3 4 5 [6 7 8 9] [6 7 8 9] [6 7 8 9] . . .这样就有了一个环，也就是6 7 8 9。point 会一直在环中循环的前进。
这时我们设置两个一快(fast)一慢(slow)两个指针，一个每次走两步，一个每次走一步，这样让他们一直走下去，直到他们在重复的序列中相遇，

![image.png](https://pic.leetcode-cn.com/970cf34694dd893c64924e1559617f64ad6b5b272a81ac3de5836cb6fb42fed7-image.png)

```java
int fast = 0, slow = 0;
while(true){
    fast = nums[nums[fast]];
    slow = nums[slow];
    if(fast == slow)
        break;
}
```

如上图，slow和fast会在环中相遇，先假设一些量：起点到环的入口长度为m，环的周长为c，在fast和slow相遇时slow走了n步。则fast走了2n步，fast比slow多走了n步，而这n步全用在了在环里循环（n%c==0）。
当fast和last相遇之后，我们设置第三个指针finder，它从起点开始和slow(在fast和slow相遇处)同步前进，当finder和slow相遇时，就是在环的入口处相遇，也就是重复的那个数字相遇。

**为什么 finder 和 slow 相遇在入口**
fast 和 slow 相遇时，slow 在环中行进的距离是n-m，其中 n%c==0。这时我们再让 slow 前进 m 步——也就是在环中走了 n 步了。而 n%c==0 即 slow 在环里面走的距离是环的周长的整数倍，就回到了环的入口了，而入口就是重复的数字。
**我们不知道起点到入口的长度m，所以弄个 finder 和 slow 一起走，他们必定会在入口处相遇。**



#### （3）实现

```java

package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findDuplicate(int[] nums) {
        int fast = nums[nums[0]];
        int slow = nums[0];
        while (fast != slow){
            fast = nums[nums[fast]];
            slow = nums[slow];
        }

        slow = 0;
        while (fast != slow){
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







### 18/338. 比特币计数



#### （1）题目

给定一个非负整数 **num**。对于 **0 ≤ i ≤ num** 范围中的每个数字 **i** ，计算其二进制数中的 1 的数目并将它们作为数组返回。



#### （2.1）思路

* 遍历0~num，对于每个数求取其二进制数中的1的数目



#### （3.1）实现

```java

package leetcode.editor.cn;

import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] countBits(int n){
        int[] res = new int[n+1];
        for (int i = 0; i <= n; i++) {
            int times = 0;
            int temp = i;
            while (temp != 0){
                times += temp & 1;
                temp >>= 1;
            }
            res[i] = times;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```



#### （2.2）思路

* 奇数：比前一个偶数多一个1
  * 因为前一个偶数的末位为0，该奇数的末位为1
* 偶数：1的个数和其1/2的那个数一样多
  * 因为其末位为0，右移1位不影响其1的个数



#### （3.2）实现

```java
class Solution{
    public int[] countBits(int n){
        int[] res = new int[n + 1];
        res[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0){
                res[i] = res[i/2];
            }else{
                res[i] = res[i-1] + 1;
            }
        }
        return res;
    }
}
```



### 19/461. 汉明距离



#### （1）题目

两个整数之间的 [汉明距离](https://baike.baidu.com/item/汉明距离) 指的是这两个数字对应二进制位不同的位置的数目。

给你两个整数 `x` 和 `y`，计算并返回它们之间的汉明距离。



#### （2）思路

* 按位异或^：二进制中，相同取0，相异取1

* 求对应二进制中位不同的位置的数目
  * k = x^y
  * k&1即为k末位位置上的数，k >>= 1即为去掉末位位置



#### （3）实现

```java
package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int hammingDistance(int x, int y) {
        int k = x ^ y;
        int times = 0;
        while (k != 0){
            times += k & 1;
            k >>= 1;
        }
        return times;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





## 四、树



### 20/94. 二叉树的中序遍历（同2）





### 21/96. 不同的二叉搜索树



#### （1）题目

给你一个整数 `n` ，求恰由 `n` 个节点组成且节点值从 `1` 到 `n` 互不相同的 **二叉搜索树** 有多少种？返回满足题意的二叉搜索树的种数。



#### （2）思路

* 动态规划
  * 令n个节点时，互不相同的二叉搜索树共有G(n)种
  * n个节点时，当x为根节点时，则左子树中共有x-1个节点（小于x），右子树中有n-x个节点（大于x）
  * 则以x为根节点的互不相同的二叉搜索树有G(x-1) * G(n-x)种
  * G(n) = G(0) * G(n-1)  + G(1) * G(n-2)  + ... + G(x-1) * G(n-x) + ... + G(n-1) * G(0)（其中G(0) = 1）



#### （3）实现

```java
package leetcode.editor.cn;

import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numTrees(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        int times;
        for (int i = 1; i <= n; i++) {
            times = 0;
            for (int j = 0; j < i; j++) {
                times += list.get(j) * list.get(i-j-1);
            }
            list.add(times);
        }
        return list.get(n);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







### 22/98. 验证二叉搜索树



#### （1）题目

给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。



#### （2）思路

* 有效的二叉搜索树的**中序遍历**结果为顺序数组，左 < 根 < 右
* 中序遍历二叉树得中序遍历结果list
* 遍历list，查看是否为顺序数组
* **也可以定义全局变量preValue记录中序遍历中上一个节点的值，在中序遍历的过程中比较是否为顺序数组**





#### （3）实现

```java
package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;

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
    public boolean isValidBST(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        inorder(root, list);
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) >= list.get(i + 1)){
                return false;
            }
        }
        return true;
    }
    public void inorder(TreeNode root, ArrayList<Integer> list){
        if (root != null){
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 23/101. 对称二叉树



#### （1）题目

给定一个二叉树，检查它是否是镜像对称的。



#### （2）思路

* 根左右和根右左遍历二叉树，若遍历结果一致，则二叉树是镜像对称的



#### （3）实现

* 递归

```java

package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
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
    public boolean isSymmetric(TreeNode root) {
        return judge(root, root);
    }

    public boolean judge(TreeNode root1, TreeNode root2){
        if (root1 != null && root2 != null){
            if (root1.val == root2.val){
                return judge(root1.left, root2.right) && judge(root1.right, root2.left);
            }else{
                return false;
            }
        }else if (root1 == null && root2 == null){
            return true;
        }else{
            return false;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





* 迭代

```java
class Solution{
    public boolean isSymmetric(TreeNode root){
        Stack<TreeNode> stack1 = new Stack<TreeNode>();
        Stack<TreeNode> stack2 = new Stack<TreeNode>();
        TreeNode root1 = root, root2 = root;
        while (root1 != null || root2 != null || !stack1.isEmpty() || !stack2.isEmpty()){
            if (root1 != null && root2 != null){
                stack1.add(root1);
                stack2.add(root2);
                if (root1.val != root2.val){
                    return false;
                }
                root1 = root1.left;
                root2 = root2.right;
            }else if(root1 == null && root2 == null){
                root1 = stack1.pop();
                root2 = stack2.pop();
                root1 = root1.right;
                root2 = root2.left;
            }else{
                return false;
            }
        }
        return true;
    }
}
```





### 24/102. 二叉树的层序遍历



#### （1）题目


给你一个二叉树，请你返回其按 **层序遍历** 得到的节点值。 （即逐层地，从左到右访问所有节点）。

 

**示例：**
二叉树：`[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

返回其层序遍历结果：

```
[
  [3],
  [9,20],
  [15,7]
]
```



#### （2）思路

* 层序遍历树：维护一个队列LinkedList，记录每一层的最后一个节点last，遍历到该层的最后一个节点时，队列中的最后一个元素即为下一层的最后一个节点；将每一层节点的val存于list中，遍历到每一层的最后一个节点时，将list压入res中，并清空list，队列非空时更新last
* **LinkedList作为Queue的实现**



#### （3）实现

```java

package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> list = new ArrayList<>();
        ArrayList<Integer> list1 = new ArrayList<>();

        if (root == null){
            return list;
        }

        TreeNode last = root;//记录每一层的最后一个节点

        LinkedList<TreeNode> list2 = new LinkedList<TreeNode>();
        list2.add(root);

        while (!list2.isEmpty()){
            TreeNode  now = list2.poll();
            list1.add(now.val);

            if (now.left != null){
                list2.add(now.left);
            }
            if (now.right != null){
                list2.add(now.right);
            }

            if (now == last){
                ArrayList<Integer> tempList = new ArrayList<>(list1);
                list.add(tempList);
                list1.clear();
                if (!list2.isEmpty()){
                    last = list2.getLast();
                }
            }
        }
        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```





### 25/104. 二叉树的最大深度



#### （1）题目

给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

说明: 叶子节点是指没有子节点的节点。

示例：
给定二叉树 [3,9,20,null,null,15,7]，

        3
       / \
      9  20
        /  \
       15   7

返回它的最大深度 3 。



#### （2）思路

* 深度优先搜索：维护一个全局变量maxDeepth，使用dfs(TreeNode root, int deepth)遍历树





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
    int maxDeepth = 0;
    public int maxDepth(TreeNode root) {
        maxDeepth = 0;
        dps(root, 1);
        return maxDeepth;
    }

    public void dps(TreeNode root, int deepth){
        if (root != null){
            if (deepth > maxDeepth){
                maxDeepth = deepth;
            }
            dps(root.left, deepth+1);
            dps(root.right, deepth+1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```



* 题解

```java
class Solution {
    public int maxDepth(TreeNode root){
        if (root == null){
            return 0;
        }else{
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }
}
```





### 26/105. 从前序和中序遍历序列构造二叉树



#### （1）题目

给定一棵树的前序遍历 `preorder` 与中序遍历 `inorder`。请构造二叉树并返回其根节点。



#### （2）思路

* 递归通过前序和中序遍历序列构造二叉树
* 前序遍历序列的结果：[根节点，[左子树的前序遍历结果]，[右子树的前序遍历结果]]
* 中序遍历序列的结果：[[左子树的中序遍历结果]，根节点，[右子树的中序遍历结果]]
* 前序遍历序列中的第一个元素即为根节点，找到其在中序遍历序列中的位置，即可得到其左子树的节点数，从而得到左子树的中序遍历结果和前序遍历结果，右子树同理
* 递归构造其左右子树



#### （3）实现

```java

package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)


import java.util.ArrayList;

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
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return build(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
    }
    
	//left1,right1,left2,right2分别为当前子树在前序遍历序列和中序遍历序列中的起始和中止位置
    public TreeNode build(int[] preorder, int[] inorder, int left1, int right1, int left2, int right2){
        if (left1 > right1){
            return null;
        }
        TreeNode root = new TreeNode();
        
        //当前根节点在inorder序列中的位置
        int i = 0;
        for (; i <=  right2; i++){
            if (preorder[left1] == inorder[i]){
                break;
            }
        }
		
        //左子树节点的个数
        int nums = i - left2;
        
        root.val = preorder[left1];
        //递归构造左子树和右子树
        root.left = build(preorder, inorder, left1+1, left1+nums, left2, i-1);
        root.right = build(preorder, inorder, left1+nums+1, right1, i+1, right2);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







### 27/114. 二叉树展开为链表（同3）







### 28/124. 二叉树中最大的路径和



#### （1）题目

路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。

路径和 是路径中各节点值的总和。

给你一个二叉树的根节点 root ，返回其 最大路径和 。



#### （2）思路

* 所求最大路径中**除根节点外的节点**只能有左孩子结点和右孩子节点中的一个，**不能同时拥有左子树和右子树**
* 动态规划
  * 维护一个max，储存最大路径和
  * left储存以左孩子节点为根节点的最大单节点（均只有一个孩子节点）路径和
  * right储存以右孩子节点为根节点的最大单节点（均只有一个孩子节点）路径和
  * 则以当前节点为根节点的最大单节点（均只有一个孩子节点）路径和为max(0, left+root.val , right+root.val)
  * 以当前节点为根节点的最大路径和（当前节点可以拥有左右孩子节点）为max(left+root.val, right+root.val, left+right+root.val)



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
    //记录最大路径和
    int max = -10001;
    public int maxPathSum(TreeNode root) {
        max = -10001;
        getMax(root);
        return max;
    }

    public int getMax(TreeNode root){
        if (root == null){
            return 0;
        }
        //左右孩子节点为根节点的最大单路径和（均只有一个孩子节点）
        int left = getMax(root.left);
        int right = getMax(root.right);
        int tempMax = Math.max(left+root.val, right+root.val);
        
        //以当前节点为根节点的最大路劲和
        int tempMax1 = Math.max(tempMax, left+right+root.val);
        if (tempMax1 > max){
            max = tempMax1;
        }
        return Math.max(0, tempMax);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
```





### 29/226. 翻转二叉树



#### （1）题目

翻转一棵二叉树。

示例：

输入：

     	 4
       /   \
      2     7
     / \   / \
    1   3 6   9

输出：

         4
       /   \
      7     2
     / \   / \
    9   6 3   1




#### （2）思路

* 先交换左右孩子节点，再递归翻转左右子树（所占内存小）

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
    public TreeNode invertTree(TreeNode root) {
        if (root == null){
            return root;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 30/236. 二叉树的最近公共祖先



#### （1）题目

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

 

#### （2.1）思路

* 深度优先搜索DFS，记录根节点到目标节点路径上的节点，分别存储于list1，list2中
* 比较list1和list2，从root节点开始，最后一个公共节点即为最近公共祖先



#### （3.1）实现

```java

package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;

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

    ArrayList<TreeNode> list0 = new ArrayList<TreeNode>();
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> list1 = new ArrayList<>();
        ArrayList<TreeNode> list2 = new ArrayList<>();

        DFS(root, p, new ArrayList<TreeNode>());
        list1.addAll(list0);

        DFS(root, q, new ArrayList<TreeNode>());
        list2.addAll(list0);

        TreeNode res = new TreeNode();
        for (int i = 0; i < Math.min(list1.size(), list2.size()); i++) {
            res = list1.get(i);
            if (i == list1.size() - 1 || i == list2.size()-1 || list1.get(i+1) != list2.get(i+1)){
                break;
            }
        }
        return res;
    }

    public void DFS(TreeNode root, TreeNode target, ArrayList<TreeNode> list){
        if (root == null){
            return;
        }
        list.add(root);
        if (root == target){
            list0.clear();
            list0.addAll(list);
            return;
        }
        DFS(root.left, target, list);
        DFS(root.right, target, list);
        list.remove(list.size()-1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```



#### （2.2）思路（ipt）

* 递归：递归遍历整棵二叉树，定义 f<sub>x</sub>表示x节点的子树中是否含有节点p或节点q，如果包含为 true，否则为 false。那么符合条件的最近公共祖先 xx 一定满足如下条件：**(f<sub>lson</sub>&&f<sub>rson</sub>)||((x = p || x = q) && (f<sub>lson</sub>||f<sub>rson</sub>))**
* 其中lson和rson表示x节点的左右孩子节点



#### （3.2）实现

```java

class Solution {
    TreeNode res = null;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        res = null;
        DFS(root, p, q);
        return res;
    }

    //返回值为当前节点及其子树中是否含有p,q节点
    public boolean DFS(TreeNode root, TreeNode p, TreeNode q){
        if (root == null){
            return false;
        }

        //左右子树是否符合条件
        boolean lson = DFS(root.left, p, q);
        boolean rson = DFS(root.right, p, q);

        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))){
            res = root;
        }

        return lson || rson || (root.val == p.val || root.val == q.val);
    }
}
```









### 31/297. 二叉树的序列化与反序列化



#### （1）题目

序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。



#### （2）思路

* 每一个节点转换为字符串，首位表示该节点是否为空，0为空，1为非空；次位表示val的正负，1为正，0为负；第三位表示val的位数；后面为Math.abs(val)
* 层序遍历二叉树，序列化该二叉树，空孩子节点也入队，将其序列化为0
* root=[1,2,3,null,null,4,5]序列化后为 1111 1112 0 0 1114 1115
* 反序列化时，继续层序遍历二叉树，先创建root节点并入队，依次补充队首节点的左右孩子节点；左右孩子节点均补齐后，队首元素出队；直至序列化的字符串为空
* **思路较麻烦，题解BFS中**
  * 序列化时，**空节点为"null"，非空节点为"node.val"，中间以","分隔**
  * 反序列化时，**以","分割字符串：**`String[] nodes = data.substring(1, data.length()-1).split(",");`

#### （3）实现

```java

package leetcode.editor.cn;

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
        String str = "";
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        str += nodeToString(root);
        while (!list.isEmpty()){
            TreeNode first = list.pollFirst();
            if (first != null){
                list.add(first.left);
                str += nodeToString(first.left);
                list.add(first.right);
                str += nodeToString(first.right);
            }
        }
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
        LinkedList<TreeNode> list = new LinkedList<>();
        
        //创建root节点
        TreeNode root = null;
        String state = data.substring(0, 1);
        data = data.substring(1);
        if ("0".equals(state)){
            root = null;
        }else{
            String temp = data.substring(0, 1);//正负
            data = data.substring(1);
            int digit = Integer.parseInt(data.substring(0, 1));//val的位数
            data = data.substring(1);
            int val = Integer.parseInt(data.substring(0, digit));//val
            data = data.substring(digit);
            if ("0".equals(temp)){
                val *= -1;
            }
            root = new TreeNode(val);
        }
        //root节点入队
        list.add(root);
        //标识队首节点的左右孩子节点是否填充
        boolean flag1 = false;
        boolean flag2 = false;

        TreeNode first = list.pollFirst();
        while (!data.isEmpty()){
			
            //反序列化data的第一个节点
            state = data.substring(0, 1);
            data = data.substring(1);
            TreeNode node = null;
            if ("0".equals(state)){
                node = null;
            }else{
                String temp = data.substring(0, 1);//正负
                data = data.substring(1);
                int digit = Integer.parseInt(data.substring(0, 1));
                data = data.substring(1);
                int val = Integer.parseInt(data.substring(0, digit));
                data = data.substring(digit);
                if ("0".equals(temp)){
                    val *= -1;
                }
                node = new TreeNode(val);
                //非空节点入队
                list.add(node);
            }
            //将节点连接到队首节点的左右孩子节点
            if (flag1 == false){
                first.left = node;
                flag1 = true;
            }else{
                first.right = node;
                flag2 = true;
            }
            if (flag1 == true && flag2 == true && !data.isEmpty()){
                //队首节点的左右孩子节点均补充后，更换队首节点并重置flag1，flag2
                first = list.pollFirst();
                flag1 = false;
                flag2 = false;
            }
        }
        return root;
    }

    //节点转换为字符串
    public String nodeToString(TreeNode root){
        var str = "";
        if (root == null){
            str += 0;
        }else{
            str += 1;
            int val = root.val;
            if (val < 0){
                str += 0;
            }else{
                str += 1;
            }
            val = Math.abs(val);
            int digit = 0;
            if (val == 0){
                digit++;
            }
            int temp = val;
            while (temp != 0){
                temp /= 10;
                digit++;
            }
            str += digit;
            str += val;
        }
        return str;
    }

}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
```



* 题解

```java
public class Codec {
    public String serialize(TreeNode root) {
        //tree: [v1,v2,null,...]
        StringBuilder res = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode cur = queue.remove();
            if(cur == null){
                res.append("null,");
            }else{
                res.append(cur.val + ",");
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        res.setLength(res.length() - 1);
        res.append("]");
        return res.toString();
    }

    public TreeNode deserialize(String data) {
        String[] nodes = data.substring(1, data.length()-1).split(",");
        TreeNode root = getNode(nodes[0]);
        Queue<TreeNode> parents = new LinkedList();
        TreeNode parent = root;
        boolean isLeft = true;
        for(int i = 1; i < nodes.length; i++){
            TreeNode cur = getNode(nodes[i]);
            if(isLeft){
                parent.left = cur;
            }else{
                parent.right = cur;
            }
            if(cur != null){
                parents.add(cur);
            }
            isLeft = !isLeft;
            if(isLeft){
                parent = parents.poll();
            }
        }
        return root;
    }

    private TreeNode getNode(String val){
        if(val.equals("null")){
            return null;
        }
        return new TreeNode(Integer.valueOf(val));
    }
}
```





### 32/437. 路径总和 III（ipt）



#### （1）题目

给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。





#### （2）思路

* root.val可能为负数
* 前缀和：到达前当前元素的路径上，所有元素的和；两节点间的路径和=两节点的前缀和之差
* 维护一个HashMap<Integer, Integer>，记录从根节点到当前节点的路径上所有节点的前缀和；key为前缀和，value为该前缀和出现次数
* tempSum记录当前节点的前缀和，若路径上存在前缀和为tempSum-targetSum的节点，则该节点到当前节点的路径和为targetSum；即res +=  map.get(tempSum - targetSum)
* 题目要求**路径方向必须是向下的（只能从父节点到子节点）**
  * 即讨论前缀和时，一个节点必须是另一个节点的祖先节点
  * **状态恢复：遍历完一个节点的所有子节点后，将其前缀和从map中移除**
* 细节：考虑节点的前缀和 = targetSum的情况，**map.put(0, 1)**



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)

import java.util.HashMap;

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
    int res;
    public int pathSum(TreeNode root, int targetSum) {
        res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        getSum(root, targetSum, 0, map);
        return res;
    }

    public void getSum(TreeNode root, int targetSum, int tempSum, HashMap<Integer, Integer> map){
        if (root == null){
            return;
        }

        tempSum += root.val;

        int need = tempSum - targetSum;

        res += map.getOrDefault(need, 0);

        map.put(tempSum, map.getOrDefault(tempSum, 0)+1);

        getSum(root.left, targetSum, tempSum, map);
        getSum(root.right, targetSum, tempSum, map);
        map.put(tempSum, map.get(tempSum)-1);
    }

}
//leetcode submit region end(Prohibit modification and deletion)

```





### 33/337. 打家劫舍III



#### （1）题目

在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。





#### （2）思路

* 每一个节点有两种状态，打劫或不打劫
  * 打劫，则根据题目规则，不能打劫其子节点，**root.val + leftl + leftr + rightl + rightr**
  * 不打劫，可以打劫其子节点，**left + right**
  * 其中left，right为从其左右孩子节点开始，可以打劫到的最高金额；leftl，leftr，rightl，rightr为从其孩子节点的左右孩子节点开始，可以打劫到的最高金额
* 该方法需要**剪枝**
  * 维护一个map<TreeNode, Integer>，存储从该节点开始，能打劫到的最高金额
  * 遍历到某节点时，先查看是否map中是否含有该key，若有直接返回value



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)


import java.util.HashMap;


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
    HashMap<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
    public int rob(TreeNode root) {
        if (root == null){
            return 0;
        }
		//剪枝
        if (map.containsKey(root)){
            return map.get(root);
        }
		//不打劫该节点
        int notChoose = rob(root.left) + rob(root.right);

        //打劫该节点
        int lefl = 0, lefr = 0, rigl = 0, rigr = 0;
        if (root.left != null){
            lefl = rob(root.left.left);
            lefr = rob(root.left.right);
        }
        if (root.right != null){
            rigl = rob(root.right.left);
            rigr = rob(root.right.right);
        }

        int choose = root.val + lefl + lefr + rigl + rigr;


        int max = Math.max(notChoose, choose);
        map.put(root, max);
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```



#### （2.2）思路（ipt）

* 每个节点都存在两种状态：打劫rob或不打劫notRob
  * 打劫
    * 状态转移：不能打劫其子节点
    * **rob = L<sub>notRob</sub> + R<sub>notRob</sub> **
  * 不打劫
    * 状态转移：可以打劫其子节点，也可不打劫其子节点
    * 若要打劫到最高金额，则需**选择其中最大值**
    * **notRob = max( L<sub>notRob</sub>, L<sub>rob</sub>) + max(R<sub>notRob</sub>, R<sub>rob</sub> )**
  * 从此节点开始，能打劫到的最高金额 = max(rob, notRob)
* 因为每个节点都有**两种状态**，不妨将函数的返回值定义为长度为2的数组：**value[]**
  * value[0] = 打劫该节点能获取的最高金额
  * value[1] = 不打劫该节点能获取的最高金额



#### （3.2）实现

```java

class Solution {

    public int rob (TreeNode root){
        int[] results = get(root);
        return Math.max(results[0], results[1]);
    }

    public int[] get(TreeNode root){
        int[] value = new int[2];
        if (root == null){
            return ints;
        }

        int[] left = get(root.left);
        int[] right = get(root.right);

        //打劫，即不打劫其子节点
        int choose = root.val + left[1] + right[1];

        //不打劫，可打劫其子节点，也可不打劫其子节点，取最大值
        int leftMax = Math.max(left[0], left[1]);
        int rightMax = Math.max(right[0], right[1]);

        int notChoose = leftMax + rightMax;

        value[0] = choose;
        value[1] = notChoose;

        return value;
    }
}
```





### 34/538. 把二叉搜索树转换为累加树



#### （1）题目

给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。

提醒一下，二叉搜索树满足下列约束条件：

节点的左子树仅包含键 小于 节点键的节点。
节点的右子树仅包含键 大于 节点键的节点。
左右子树也必须是二叉搜索树。



#### （2）思路

* 由二叉搜索树的定义可知，大于节点node的值的节点为node的右子树
* 根据右根左的顺序遍历二叉树，**遍历序列中在node之前的节点的value >  node.value**
* **node.val = 遍历序列中上一个节点的value + node.val**，维护一个队列记录遍历序列，先递归遍历其右子树，更改节点值，节点值入队，再递归遍历其左子树，**注意不能状态恢复**



#### （3）实现

```java
import java.util.ArrayList;

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
    ArrayList<Integer> list = new ArrayList<>();
    public TreeNode convertBST(TreeNode root) {
        list.clear();
        convert(root);
        return root;
    }

    public void convert(TreeNode root){
        if (root == null){
            return ;
        }

        convert(root.right);
        
        if (!list.isEmpty()){
            root.val += list.get(list.size()-1);
        }
        
        list.add(root.val);
        
        convert(root.left);
    }

}
```





### 35/543. 二叉树的直径



#### （1）题目

 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。



#### （2）思路

* 深度优先搜索二叉树，返回值为该节点的高度，left，right分别为其左右孩子节点的高度
  * 则以该节点为起点的路径的最大值 = left + right
  * 该节点高度 = max(left, right) + 1
* 维护一个全局变量max，记录二叉树中路径长度中的最大值；遍历每个节点，以每个节点为起点，计算其路径长度的最大值



#### （3）实现

```java
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
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        max = 0;
        DFS(root);
        return max;
    }

    public int DFS(TreeNode root){

        if (root == null){
            return 0;
        }
        int left = DFS(root.left);
        int right = DFS(root.right);

        int length = left + right;
        if (length > max){
            max = length;
        }
        return Math.max(left, right) + 1;
    }

}
```





### 36/617. 合并二叉树



#### （1）题目

给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。



#### （2）思路

* **深度优先遍历树**，根据当前root1和root2的状态，决定新树的状态
  * root1 == null && root2 == null：root =  null
  * root1 == null && root2 != null：root = root2
  * root1 != null && root2 == null：root = root1
  * root1 != null && root2 != null：root.val = root1.val + root2.val，**并继续构造root的左右孩子节点**



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
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
    
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        
        TreeNode root = new TreeNode();
        
        if (root1 != null && root2 != null){
            
            root.val = root1.val + root2.val;
            root.left = mergeTrees(root1.left, root2.left);
            root.right = mergeTrees(root1.right, root2.right);
            
        }else if (root1 != null && root2 == null){
            root = root1;
        }else if (root1 == null && root2 != null){
            root = root2;
        }else if (root1 == null && root2 == null){
            root = null;
        }
        
        return root;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
```





## 五、深度优先搜索



### 37/207. 课程表



#### （1）题目

你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。



#### （2）思路

* 拓扑排序
* 使用 **ArrayList<Integer>[ ] edge = new ArrayList[numCourses]**存储边，使用 int[ ] k = new int[numCourses]存储节点的入度
* 使用栈存储入度为0的节点，遍历数组k，将所有入度 = 0的节点压入栈
* 弹出栈顶元素pop，遍历以该节点为起点的边 edge[pop]，对应节点的入度 -  1；并将入度 = 0的节点压入栈
* 记录入栈节点数，栈空时比较入栈节点数和numCourses是否相等



#### （3）实现

```java
import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        //节点入度
        int[] k = new int[numCourses];

        //边
        ArrayList<Integer>[] edge = new ArrayList[numCourses];

        boolean[] flag = new boolean[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            int[] temp = prerequisites[i];
            int left = temp[0];
            int right = temp[1];

            //更新入度
            k[left]++;

            //更新边
            if (flag[right] == false){
                ArrayList<Integer> e = new ArrayList<>();
                e.add(left);
                edge[right] = e;
                flag[right] = true;
            }else{
                edge[right].add(left);
            }
        }

        //记录入度为0的节点数
        int nums = 0;

        //存放入度为0的节点
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (k[i] == 0){
                stack.add(i);
                nums++;
            }
        }


        while (!stack.isEmpty()){
            Integer pop = stack.pop();
            if (flag[pop] == true){
                ArrayList<Integer> list = edge[pop];
                for (int temp : list){
                    k[temp]--;
                    if (k[temp] == 0){
                        stack.add(temp);
                        nums++;
                    }
                }
            }
        }
        
        return nums == numCourses;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 38/399. 除法求值（未解决）



#### （1）题目

给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。

另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。

返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。

注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。



#### （2）思路

* 将A<sub>i</sub> / B<sub>i</sub>看成 节点A<sub>i</sub>指向节点B<sub>i</sub>的有向边，值看为有向边的值，则其逆向边为值的倒数
* C<sub>j</sub> / D<sub>j</sub> = C<sub>j</sub> → D<sub>j</sub>的路径中边的乘积，若两节点不连通，即答案无法确定
* A<sub>i</sub> , B<sub>i</sub>由小写英文字母和数字组成，将a~z转化为0~25；edge [25] [25]存储有向边，同时将其逆向边也存储





## 六、广度优先搜索



### 39/279. 完全平方数



#### （1）题目

给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。



#### （2）思路









## 七、动态规划



### 53/62. 不同路径



#### （1）题目

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？



#### （2）思路

* 动态规划；每个位置有两种选择，向下或向右走，若用简单递归，时间复杂度为O( 2<sup>m*n</sup> )
* path[n] [m]记录每个位置的路径数
  * path[0] [0] = 1
  * **状态转移方程：path[x] [y] = path[x-1] [y] + path[x] [y-1]**
  * x或y < 0 时，path[x] [y] = 0



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int uniquePaths(int m, int n) {
        int[][] path = new int[n][m];
        for (int i = 0; i < m; i++) {
            
            for (int j = 0; j < n; j++) {
                
                if (i == 0 && j == 0){
                    path[j][i] = 1;
                }else if (i == 0){
                    path[j][i] = path[j-1][i];
                }else if (j == 0){
                    path[j][i] = path[j][i-1];
                }else {
                    path[j][i] = path[j][i-1] + path[j-1][i];
                }
            }
        }

        return path[n-1][m-1];

    }

}
//leetcode submit region end(Prohibit modification and deletion)
```





### 54/64. 最小路径和



#### （1）题目

给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：每次只能向下或者向右移动一步。



#### （2）思路

* 动态规划
* 由题可知，每一个点的最短路径和只与其上方和左方节点有关
* pathSum[n] 记录每一行节点的最短路径和，**不需要记录所有节点的最短路径和**
  * **状态转移方程**：第x层的第y个节点，**pathSum[y] = min(pathSum[y-1] , pathSum[y]) + grid[x] [y]**
  * 当计算到第y个节点时，pathSum[y-1]为**当前层的y-1节点**的最小路径和，pathSum[y]为**上一层的y节点**的最小路径和
  * 注意特殊情况，x=0时，无上一层节点，只有左节点；y=0时，只有上一层节点，无左节点；x=0&&y=0



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minPathSum(int[][] grid) {
        int x = grid[0].length, y = grid.length;

        int[] pathSum = new int[x];

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {

                if (i == 0 && j == 0){
                    pathSum[0] = grid[0][0];
                }else if (i == 0){
                    pathSum[j] = pathSum[j-1] + grid[i][j];
                }else if (j == 0){
                    pathSum[j] = pathSum[j] + grid[i][j];
                }else {
                    pathSum[j] = ( pathSum[j-1] < pathSum[j] ? pathSum[j-1] : pathSum[j] ) + grid[i][j];
                }
            }
        }

        return pathSum[x-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 59/139. 单词拆分



#### （1）题目

给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。

说明：

拆分时可以重复使用字典中的单词。
你可以假设字典中没有重复的单词。



#### （2）思路

* 动态规划（若无好的策略，考虑动态规划）
* state[s.length() + 1]记录字符串s到当前下标，是否能成功拆分
  * state[x] ，true表示s中[0, x) 部分可成功拆分，false表示不能拆分
  * state[0] = true
* 遍历state[]，更新每个位置状态
* 遍历到state[x] 时，**遍历wordDict中的元素str，考虑str是否能和已经拆分成功的字符串组合成功**
  * 考虑state[x-str.length()]是否为true
  * 考虑s.subString(x - str.length(), x)是否和str相同
  * **状态转移方程：state[x] = state[x-str.length()] && s.subString(x-str.length(), x).equals(str)**



#### （3）实现

```java
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    
    public boolean wordBreak(String s, List<String> wordDict) {
        
        boolean[] state = new boolean[s.length() + 1];
        state[0] = true;

        for (int i = 1; i < state.length; i++) {
            
            //遍历wordDict，查看是否能和已经拆分成功的字符串组合
            for (String str : wordDict){
                int fromIndex = i - str.length();
                
                //状态转移方程
                if (fromIndex >= 0 && state[fromIndex] == true && str.equals(s.substring(fromIndex, i))){
                    state[i] = true;
                    break;
                }
            }

        }

        return state[s.length()];

    }

}
//leetcode submit region end(Prohibit modification and deletion)
```







### 61/152. 乘积最大子数组



#### （1）题目

给你一个整数数组 `nums` ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。



#### （2）思路

* 动态规划
* 考虑到元素可能为负数，则需要记录乘积的最大值和最小值
* dpMin[ ], dpMax[ ]记录以当前下标为终点的连续子数组的乘积的最大值和最小值
* 状态转移方程：
  * **dpMin[i] = min ( dpMin[i-1] * nums[i], dpMax[i-1] * nums[i], nums[i] )**
  * **dpMax[i] = max ( dpMin[i-1] * nums[i], dpMax[i-1] * nums[i], nums[i] )**



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProduct(int[] nums) {

        int res = nums[0];

        int[] dpMax = new int[nums.length];

        int[] dpMin = new int[nums.length];

        //初始化
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];

        for (int i = 1; i < nums.length; i++){
            //状态转移
            dpMax[i] = getMax(dpMax[i-1] * nums[i], dpMin[i-1] * nums[i], nums[i]);
            dpMin[i] = getMin(dpMax[i-1] * nums[i], dpMin[i-1] * nums[i], nums[i]);

            //记录最大乘积
            res = dpMax[i] > res ? dpMax[i] : res;
         }

        return res;
    }

    public int getMax(int a, int b, int c){
        int max = a > b ? a : b;
        max = max > c ? max : c;
        return max;
    }

    public int getMin(int a, int b, int c){
        int min = a < b ? a : b;
        min = min < c ? min : c;
        return min;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
```





### 62/221. 最大正方形



#### （1）题目

在一个由 `'0'` 和 `'1'` 组成的二维矩阵内，找到只包含 `'1'` 的最大正方形，并返回其面积。



#### （2）思路

* 动态规划
* dp[x] [y] 记录以(x, y)为底角的矩阵内的最大正方形的边长
* 状态转移方程：
  * 若matrix[x] [y] = '0' ，dp[x] [y] = 0
  * 若matrix[x] [y] = '1'， 考虑dp[x-1] [y-1]（注意x=0，y=0的情况）
    * dp[x-1] [y-1] = len
    * 考虑  matrix[x] [y] ~ matrix[x-len] [y] 和 matrix[x] [y] ~ matrix[x] [y-len] 上连续为 '1' 的个数 plus
    * dp[x] [y] = plus
  * 记录最大边长res



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximalSquare(char[][] matrix) {

        int res = 0;

        int[][] dp = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            
            for (int j = 0; j < matrix[0].length; j++) {
                
                if (matrix[i][j] == '1'){
                    
                    //边界位置
                    if (i == 0 || j == 0){
                        dp[i][j] = 1;
                    }else{
                        //左上位置的最大边长
                        int len = dp[i-1][j-1];

                        //记录行列同为'1'的个数
                        int plus = 0;
                        for (int k = i, l = j; k >= i-len && l >= j-len; k--, l--) {
                            if (matrix[k][j] == '0' || matrix[i][l] == '0'){
                                break;
                            }

                            plus++;
                        }

                        //更新以(i, j) 为底角的矩阵的最大正方形边长
                        dp[i][j] = plus;
                    }
                }
                
                //更新整个矩阵的最大正方形边长
                res = res > dp[i][j] ? res : dp[i][j];
            }
        }

        return res * res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 66/300. 最长递增子序列



#### （1）题目

给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

 



#### （2）思路

* 动态规划
* **dp[index]记录以下标为index结尾的上升子序列的最大长度，dp[ ]初始化为1**
* 动态转移方程：
  * **dp[i] = max ( dp[i]，dp[j] + 1)，nums[i] > nums[j] && 0 <= j < i**



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLIS(int[] nums) {

        int res = 0;

        int[] dp = new int[nums.length];

        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {

            for (int j = 0; j < i; j++) {

                if (nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            res = Math.max(res, dp[i]);
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```





### 八、背包问题



### 65/279. 完全平方数



#### （1）题目

给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。



#### （2）思路

* 将1~100的平方数压入HashSet中，全局变量res记录当前的组成和的完全平方数的最少个数
* BFS：num为已经组合的元素个数，target为n剩余的和，从大到小，将1~100的平方数的当作组成元素；更新num，target
  * num >= res || target < 0，跳出
  * num < res && target > 0 
    * set.contains(target)：res = num + 1
    * ! set.contains(target)：进入下一层递归



#### （3）实现

```java
import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int res = 10000;
    public int numSquares(int n) {
        HashSet<Integer> set = new HashSet<>();

        for (int i = 1; i < 101; i++) {
            set.add(i*i);
        }

        if (set.contains(n)){
            return 1;
        }

        find(0, set, n);

        return res;
    }

    public void find(int num, HashSet<Integer> set, int target) {
        if (num >= res || target < 0){
            return;
        }


        for (int i = 100; i >= 1; i--) {
            int newTarget = target - i*i;
            int newNum  = num + 1;
            if (newNum < res && newTarget > 0){
                if (set.contains(newTarget)){
                    res = newNum + 1;
                }else {
                    find(newNum, set, newTarget);
                }
            }
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
```





#### （2.2）思路

* 完全背包（元素可以选无限个）（朴素解法）
  * f[i] [j]为考虑前 i 个数字，凑出数字总和 j 所需用到的最少数字数量
  * 状态转移方程：f[i] [j] = min ( f [i - 1] [j - k * t] + k ) ，0 <= k * t <= j
  * 超时风险
* 完全背包（进阶）
  * f[i] [j]为考虑前 i 个数字，凑出数字总和 j 所需用到的最少数字数量
  * t为第 i 个数字大小
  * f [i] [j] = min ( f [i-1] [j] + 0, **f [ i-1 ] [ j-t ] + 1，f [ i-1 ] [ j-2t ] + 2，...... ，f [ i-1 ] [ j-kt ] + k )** ，0 <= k*t <= j
  * f [i] [ j-t ] =                   **min ( f [ i-1 ] [ j-t ] + 0，f [ i-1 ] [ j-2t ] + 1，f [i - 1] [j - 3t] + 2，...... ，f [ i-1 ] [ j-kt ] + (k - 1) )** ，0 <= k*t <=j
  * 由上 **f [i] [j] = min ( f [ i-1 ] [j]，f [i] [ j-t ] + 1 ) **
  * i 的维度消除：f [j] = min ( f [j], f[ j-t ] + 1 )



#### （3.2）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int numSquares(int n) {
        int[] dp = new int[n+1];

        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = 0;

        for (int t = 1; t * t <= n; t++){
            int x  = t * t;
            for (int j = x; j <= n; j++){
                //状态转移方程
                dp[j] = Math.min(dp[j], dp[j-x] + 1);
            }
        }

        return dp[n];
    }

}
//leetcode submit region end(Prohibit modification and deletion)
```





## 九、unknown



### 40/2. 两数相加



#### （1）题目

给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头。



#### （2）思路

* 链表节点数过大，不能转换为整数做简单的加法
* 同时从链表头开始，将两个链表节点的val相加，满10进1
  * **用k记录进位**，node.value = ( x + y + k) % 10，k = ( x + y + k) / 10
  * L<sub>1</sub>.val = x，L<sub>2</sub>.val = y；**注意L<sub>1</sub>, L<sub>2</sub> = null的情况，此时对应的x=0, y = 0**
  * 递归构建新链表的后续节点，L<sub>1</sub>，L<sub>2</sub> = null时，其后续节点继续置为null
  * **当 L<sub>1</sub> == null && L<sub>2</sub> == null时，终止递归**



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode root = build(l1, l2, 0);
        return root;
    }

    public ListNode build (ListNode l1, ListNode l2, int k){
        //递归终止条件
        if (l1 == null && l2 == null && k == 0){
            return null;
        }

        ListNode node = new ListNode();

        int x = (l1 != null) ? l1.val : 0;
        int y = (l2 != null) ? l2.val : 0;

        node.val = (x + y + k) % 10;
        k = (x + y + k) / 10;

        l1 = (l1 != null) ? l1.next : null;
        l2 = (l2 != null) ? l2.next : null;

        node.next = build(l1, l2, k);
        return node;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 41/3. 无重复字符的最长子串



#### （1）题目

给定一个字符串 `s` ，请你找出其中不含有重复字符的 **最长子串** 的长度。



#### （2）思路

* **双指针遍历字符串**，分别指向截取字符串的两端；**HashMap<Character, Integer>记录截取字符串里的字符**
* 右指针right指向的字符c
  * map.getOrDefault ( c, 0 ) = 0，不重复，更新字符串的长度
  * map.getOrDefault ( c, 0 ) = 1，重复，移动左指针指向重复元素的右边（并非right的右边），即找到新的无重复的最长子串



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        //记录无重复字符的最长字符串长度
        int length = 0;
        //记录截取字符串里的字符
        HashMap<Character, Integer> map = new HashMap<>();
        
        for (int i = 0, j = 0; j < s.length(); j++){
            
            char c = s.charAt(j);
            //判断右指针j指向的字符是否重复
            int time = map.getOrDefault(c, 0);
            if (time == 0){//非重复
                map.put(c, 1);
                //更新length
                int tempLength = j + 1 - i;
                length = (tempLength > length) ? tempLength : length;
            }else {//重复
                //找到截取字符串中与c重复的元素
                while (s.charAt(i) != s.charAt(j)){
                    map.put(s.charAt(i), 0);
                    i++;
                }
                //更新左指针
                i++;
            }
        }
        return length;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 42/5. 最长回文子串



#### （1）题目

给你一个字符串 `s`，找到 `s` 中最长的回文子串。



#### （2）思路

* 判断字符串是否为回文串：
  * 首尾指针
  * 从中间位置开始，左右指针
* 遍历字符串，以当前字符为回文串的中间字符，向两边发散，找到以当前字符为中间字符的最长回文串
  * 回文串长度为奇数，当前字符为中间字符
  * 回文串长度为偶数，当前字符为中间字符之一



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {

        String res = "";
        for (int i = 0; i < s.length(); i++) {
            int left = i, right = i;
            for (; left >= 0 && right < s.length(); left--, right++){
                if (s.charAt(left) == s.charAt(right)){
                    String temp = s.substring(left, right+1);
                    if (temp.length() > res.length()){
                        res = temp;
                    }
                }else{
                    break;
                }
            }



            for (left = i, right = i + 1; left >= 0 && right < s.length(); left--, right++){
                if (s.charAt(left) == s.charAt(right)){
                    String temp = s.substring(left, right+1);
                    if (temp.length() > res.length()){
                        res = temp;
                    }
                }else{
                    break;
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```





### 43/17. 电话号码的字母组合



#### （1）题目

给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。



#### （2）思路

* 模拟



#### （3）实现

```java
import java.util.ArrayList;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> letterCombinations(String digits) {

        List<String> res = new ArrayList<String>();

        ArrayList<ArrayList<String>> list = new ArrayList<>();

        String[] str1 = {"a", "b", "c"};
        list.add(new ArrayList<String>(Arrays.asList(str1)));

        String[] str2 = {"d", "e", "f"};
        list.add(new ArrayList<String>(Arrays.asList(str2)));

        String[] str3 = {"g", "h", "i"};
        list.add(new ArrayList<String>(Arrays.asList(str3)));

        String[] str4 = {"j", "k", "l"};
        list.add(new ArrayList<String>(Arrays.asList(str4)));

        String[] str5 = {"m", "n", "o"};
        list.add(new ArrayList<String>(Arrays.asList(str5)));

        String[] str6 = {"p", "q", "r", "s"};
        list.add(new ArrayList<String>(Arrays.asList(str6)));

        String[] str7 = {"t", "u", "v"};
        list.add(new ArrayList<String>(Arrays.asList(str7)));

        String[] str8 = {"w", "x", "y", "z"};
        list.add(new ArrayList<String>(Arrays.asList(str8)));

        for (int i = 0; i < digits.length(); i++) {
            int number = Integer.parseInt(digits.substring(i, i+1));
            //当前数字对应的字母
            ArrayList<String> strings = list.get(number - 2);

            //暂时储存组合后的字符串
            ArrayList<String> temp = new ArrayList<>();
			
            //第一位的情况
            if (res.isEmpty()){
                temp.addAll(strings);
            }
			
            //将前面组合的字符串与当前数字对应的字母一一组合
            while (!res.isEmpty()){
                String str0 = res.get(res.size()-1);
                res.remove(res.size()-1);
                for (int j = 0; j < strings.size(); j++) {
                    temp.add(str0 + strings.get(j));
                }
            }
			
            //更新结果
            res.addAll(temp);
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```





### 44/19. 删除链表的倒数第n个节点



#### （1）题目

给你一个链表，删除链表的倒数第 `n` 个结点，并且返回链表的头结点。

**进阶：**你能尝试使用一趟扫描实现吗？



#### （2）思路

* 删除某个节点，需要找到其**前序节点**
  * pre.next = pre.next.next
* 一次遍历，使用**快慢指针**，quick指针**先走n次**，slow指针再出发，当quick指针指向尾节点时，slow指针指向倒数第n个节点
  * 需考虑slow指针一步未走的情况，即删除head节点



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode quick = head, slow = head;

        for (int i = 0; i < n; i++) {
            quick = quick.next;
        }

        //删除表头head的情况
        if (quick == null){
            head = head.next;
        }else{
            while (quick.next != null){
                quick = quick.next;
                slow = slow.next;
            }

            slow.next = slow.next.next;
        }

        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 45/15. 三数之和



#### （1）题目

给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

注意：答案中不可以包含重复的三元组。



#### （2）思路

* 三重循环的时间复杂度为O(n<sup>3</sup>)，会超时；需要优化
* 使用times[200002]储存number出现的次数，**number = 数组下标 - 100000**
* Arrays.sort(nums)给nums排序，两重循环遍历nums，要保证没有重复的三元组，需要i,j指向的number与上一次的不相同
* a = nums[i], b = nums[j]，则 c = - (a+ b)，通过times数组判断是否存在这样的c，注意a，b，c相等的情况





#### （3）思路

```java
import java.util.ArrayList;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();

        Arrays.sort(nums);

        //记录number出现的次数
        int[] times = new int[200002];

        for (int i = 0; i < nums.length; i++) {
            times[nums[i] + 100000]++;
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0){
                break;
            }

            if (i != 0 && nums[i] == nums[i-1]){
                continue;
            }

            for (int j = i + 1; j < nums.length; j++) {

                if (j != i + 1 && nums[j] == nums[j-1]){
                    continue;
                }
				//add = a+b
                int add = nums[i] + nums[j];
				
                int index = -add + 100000;

                if (add > 0 || -add < nums[j] || index < 0){
                    break;
                }

                if (index > 200001){
                    continue;
                }

                int time = times[index];

                if (-add == nums[i]){
                    time--;
                }
                if (-add == nums[j]){
                    time--;
                }

                if (time > 0){
                    ArrayList<Integer> list = new ArrayList<>();

                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(-add);
                    res.add(list);
                }
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





#### （2.2）思路（ipt）

* **在有序的序列中，找a+b=0，可使用双指针法**
  * left，right指向序列的首尾
  * nums[left] + nums[right] > 0，right--
  * nums[left] + nums[right] < 0，left++
  * nums[left] + nums[right] = 0，left++, right--
* 此题中，需要**不包含重复的三元组**，则在选择a, b, c 的过程中，需要去重，不选择与上一轮相同的数



#### （3.2）实现

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        ArrayList<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

            //去重，不选择重复的a
            if (i != 0 && nums[i] == nums[i-1]){
                continue;
            }

            //第二轮和第三轮选择同时进行
            int j = i + 1, k = nums.length - 1;
            while (j < k){
                if (nums[i] + nums[j] + nums[k] == 0){
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                    j++;
                    k--;

                    //去重，不选择重复的b,c
                    while (j < k && nums[j] == nums[j-1]){
                        j++;
                    }

                    while (j < k && nums[k] == nums[k+1]){
                        k--;
                    }
                }else if (nums[i] + nums[j] + nums[k] > 0){
                    k--;
                }else if (nums[i] + nums[j] + nums[k] < 0){
                    j++;
                }
            }
        }

        return res;
    }
}
```





### 47/22. 括号的生成



#### （1）题目

数字 `n` 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 **有效的** 括号组合。

有效括号组合需满足：左括号必须以正确的顺序闭合。



#### （2）思路

* 以left，right分别表示剩余的左右括号数
* 每个位置有两种可能，左括号或右括号
  * left > 0时，可以为左括号
  * **right > 0 && left > right时，可以为右括号**；即已放置的左括号数大于右括号数
* 递归添加每个位置上的括号，递归边界为left == 0 && right == 0



#### （3）实现

```java
import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        res.clear();
        generate(n, n, "");
        return res;
    }

    public void generate(int left, int right, String str){
        if (left == 0 && right == 0){
            res.add(str);
            return;
        }

        if (left > 0){
            generate(left-1, right, str + "(");
        }
        if (right > 0 && right > left){
            generate(left, right-1, str + ")");
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 48/31. 下一个排列



#### （1）题目

实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须 原地 修改，只允许使用额外常数空间。

 

#### （2）思路

* 若当前排列为**降序排列**，即不存在下一个更大的排列，要将序列重新排列为最小的排列，首位指针交换
* 若当前排序**不是降序排列**，则存在下一个更大的排列
  * target记录序列中**最后一个非降序排列的元素位置**；即nums[target + 1] > nums[target]
  * **minIndex记录在target后且大于nums[target]的最小的元素的位置**
  * **交换minIndex 和 target元素，并对target后的元素排序，Arrays.sort(nums, target+1, nums.length)**



#### （3）实现

```java
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void nextPermutation(int[] nums) {
        int target = -1;
        for (int i = 0; i < nums.length-1; i++) {
            if (i+1 < nums.length && nums[i] < nums[i+1]){
                target = i;
            }
        }

        if (target != -1){
            int min = 110, minIndex = -1;
            for (int i = target + 1; i < nums.length; i++) {
               if (nums[i] < min && nums[i] > nums[target]){
                   min = nums[i];
                   minIndex = i;
               }
            }

            nums[minIndex] = nums[target];
            nums[target] = min;
            Arrays.sort(nums, target+1, nums.length);
        }else{
            for (int i = 0; i < nums.length / 2; i++) {
                int temp = nums[i];
                int index = nums.length - 1 - i;
                nums[i] = nums[index];
                nums[index] = temp;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 49/33. 搜索旋转排序数组



#### （1）题目

整数数组 nums 按升序排列，数组中的值 互不相同 。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。

给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

 

#### （2）思路

* 旋转排序数组也是部分有序的
* left, right分别表示当前的搜索数组的最左边和最右边的数，mid = (left + right) / 2
  * 若nums[mid] = target，返回mid
  * 若**nums[left] <= nums[mid]**
    * **left~mid为有序数组，mid+1~right为无序数组**
    * 判断nums[left], nums[mid], target的大小关系，可以知道target在左半还是在右半数组
  * 若**nums[left] > nums[mid]**
    * **left~mid-1为无序数组，mid~right为有序数组 **
    * 判断nums[mid], nums[right], target的大小关系，可以知道target在左半还是右半数组
  * left > right时，跳出循环



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while (left <= right){
            int mid = (left + right)/2;

            if (nums[mid] == target){
                return mid;
            }

            //左半有序
            if (nums[mid] >= nums[left]){
                if (nums[left] <= target && target < nums[mid]){
                    right = mid-1;
                }else{
                    left = mid + 1;
                }
            }else {
                if (nums[mid] < target && target <= nums[right]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 50/34. 在排序数组中查找元素的第一个和最后一个位置



#### （1）题目

给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

进阶：

你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？



#### （2）思路

* nums为一个非递减数组，可以使用二分查找
* left, right表示当前查找数组的左右边界，mid = (left + right) / 2；leftIndex，rightIndex记录元素的第一个和最后一个位置
  * nums[mid] > target，继续在左半区间查找，right = mid - 1
  * nums[mid] < target，继续在右半区间查找，left = mid + 1
  * nums[mid] = target
    * 更新leftIndex，并在其左半区间继续查找，right = mid - 1
    * 更新rightIndex，并在其右半区间继续查找，left = mid + 1



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        int leftIndex = -1, rightIndex = -1;

        //确定leftIndex
        while (left <= right &&  right >= 0){
            int mid = (left + right)/2;
            if (nums[mid] < target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else{
                //更新leftIndex，并在左半区间继续查找
                leftIndex = mid;
                right = mid - 1;
            }
        }

        left = 0;
        right = nums.length - 1;

        //确定rightIndex
        while (left <= right && left < nums.length){
            int mid = (left + right)/2;
            if (nums[mid] < target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else{
                //更新rightIndex，并在右半区间继续查找
                rightIndex = mid;
                left = mid + 1;
            }
        }

        int[] res = {leftIndex, rightIndex};

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 51/46. 全排列



#### （1）题目

给定一个不含重复数字的数组 `nums` ，返回其 **所有可能的全排列** 。你可以 **按任意顺序** 返回答案。



#### （2）思路

* 模拟，回溯
* ArrayList<ArrayList<Integer>> res记录结果；ArrayList<Integer> list记录当前遍历序列；boolean[] flag 记录已经遍历的元素，false为未遍历，true为已经遍历；count记录已经遍历的元素个数
* count = nums.length时，表示已经遍历完全部元素，将list加入res中
  * `res.add(new ArrayList<Integer>(list));`
  * **需新建list，否则因为传入为地址值，后续修改list时，会修改res中的值**
* 每一轮中选取nums中未遍历的元素（flag = false），加入list，更新flag，count后，进入下一轮遍历
* **遍历结束后，需要回溯，更新flag，list**



#### （3）实现

```java
import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    public List<List<Integer>> permute(int[] nums) {
        boolean[]   flag = new boolean[nums.length];
        generate(nums, flag, 0, new ArrayList<>());

        return res;
    }

    void generate(int[] nums, boolean[] flag, int count, ArrayList<Integer> list){
        
        if (count == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == false){
                list.add(nums[i]);
                flag[i] = true;
                generate(nums, flag, count+1, list);
                
                //回溯
                list.remove(count);
                flag[i] = false;
            }
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
```





### 52/39. 组合总和



#### （1）题目

给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。

candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。 

对于给定的输入，保证和为 target 的唯一组合数少于 150 个。



#### （2）思路

* 模拟
* ArrayList<ArrayList<Integer>> res记录答案，ArrayLIst<Integer> list记录当前遍历序列，index记录遍历当前遍历元素的下标，sum记录遍历序列元素和
* **当前轮次，sum = target，将list加入res**
* 每一轮遍历中，**依次将从当前index开始的元素试图加入list中**， tempSum = sum + candidate[i]
  * **tempSum <= target，更新sum，list，并递归进入下一轮遍历；递归结束，回溯，更新sum，list**
  * tempSum > target，跳出循环
* 剪枝：对candidate排序



#### （3）实现

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        Arrays.sort(candidates);

        generate(candidates, target, 0, 0, new ArrayList<>());

        return res;
    }

    void generate(int[] candidates, int target, int index, int sum, ArrayList<Integer> list){
        if (sum == target){
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            int tempSum = sum + candidates[i];

            if (tempSum <= target){
                list.add(candidates[i]);
                generate(candidates, target, i, tempSum, list);
                list.remove(list.size()-1);
            }else{
                break;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 55/49. 字母异位词分组



#### （1）题目

给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。

字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。



#### （2）思路

* **字母异位词按字典序升序排序后相同**
* HashMap<String, List<String>>，**将排序之后的字符串作为key**，互为字母异位词的字符串组成的List作为value
* `map.getOrDefault(temp, new ArrayList<String>())`，获取排序后的字符串为key的字母异位词组成的List，没有返回空的List



#### （3）实现

```java
import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {


        HashMap<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
			
            //转换为字典序升序的字符串
            String key = strs[i];

            char[] chars = key.toCharArray();
            Arrays.sort(chars);

            key = new String(chars);

            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(strs[i]);

            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 56/79. 单词搜索



#### （1）题目

给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

 

#### （2）思路

* 回溯
* choose[] []记录当前轮次中board中哪些字母被选择； count记录当前在寻找word中的字符下标；i，j记录当前遍历字符在board中下标；全局变量flag记录是否找到该word
* 以board中**每个符合要求的字符为起点**进行搜索，即与word[0]相同的字符
* 当前搜索字符为board[x] [y]，对应word中count下标的字符，将choose[x] [y] = true；下次搜索可以搜索其上下左右的字符且满足要求的未搜索过的字符，即**choose[x1] [y1] = false && board[x1] [y1] = word.charAt(count+1)**；搜索结束回溯，将**choose[x] [y] = false**;
* 剪枝：flag == true时，说明已经搜索到word，直接返回



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    boolean flag = false;
    //上下左右
    int[][] plus = {{-1,1,0,0}, {0,0,-1,1}};
    
    public boolean exist(char[][] board, String word) {
        boolean[][] choose;
        flag = false;
        Label: for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //在之前轮次中已经搜索到word，直接跳出
                if (flag == true){
                    break Label;
                }
                //从与word[0]相等的字母开始搜索
                if (board[i][j] == word.charAt(0)){
                    choose = new boolean[board.length][board[0].length];
                    judge(board, word, 0, i, j, choose);
                }

            }
        }
        return flag;
    }

    void judge(char[][] board, String word, int count, int x, int y, boolean[][] choose){

        if (flag == true){
            return;
        }
        //修改当前位置的搜索状态
        choose[x][y] = true;

        if (count == word.length() - 1){
            flag = true;
            return;
        }else if (count < word.length()-1){
            //上下左右位置
            for (int i = 0; i < 4; i++) {
                int x1 = x + plus[0][i];
                int y1 = y + plus[1][i];
                
                //满足要求：未搜索过且和word中下一个字符相等
                if (x1 >= 0 && x1 < board.length && y1 >= 0 && y1 < board[0].length && choose[x1][y1] == false && board[x1][y1] == word.charAt(count+1)){
                    judge(board, word, count+1, x1, y1, choose);
                }

            }
        }

        //回溯
        choose[x][y] = false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 57/128. 最长连续序列（ipt）



#### （1）题目

给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。



#### （2）思路

* 对数组排序
* 时间复杂度：O(nlog<sub>n</sub>)
* 遍历数组，temp记录以当前元素结尾的最长连续序列长度
  * i == 0 || nums[i] == nums[i-1] + 1时，temp++
  * nums[i] == nums[i-1]时，temp = temp
  * 其他情况，temp = 1
  * 记录最大的temp



#### （3）实现

```java
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestConsecutive(int[] nums) {

        //temp记录以上一个元素结尾的最长序列长度
        int res = 0, temp  = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            //更新当前以当前元素结尾的最长序列长度
            if (i == 0 || nums[i] == nums[i-1]+1){
                temp++;
                res = res > temp ? res : temp;
            }else if (nums[i] == nums[i-1]){
                temp = temp;
            }else {
                temp = 1;
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





#### （2.2）思路（ipt）

* 集合去重，利用**O(1)**的时间复杂度取查询是否有下一个
* 遍历集合，**若存在比当前元素小的连续元素，则让小的元素取搜索**
* 时间复杂度：O(n)， 空间复杂度：O(n)



#### （3.2）实现

```java

import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestConsecutive(int[] nums) {

        int res = 0;
        HashSet<Integer> set = new HashSet<>();

        //HashSet去重
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        //
        for (int num : set){

            //优化搜索，只对没有更小的连续数的值搜索
            if (!set.contains(num-1)){
                int length = 1, number = num + 1;
                while (set.contains(number)){
                    length++;
                    number++;
                }

                res = res > length ? res : length;
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





#### （2.3）思路

* 并查集



#### （3.3）实现

```java
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestConsecutive(int[] nums) {

        if (nums.length == 0){
            return 0;
        }

        //初始化并查集，各自为战
        UnionFind uf = new UnionFind(nums);

        for (int v : nums){
            uf.union(v, v+1);//结盟
        }

        //二次遍历，记录领队距离
        int max = 1;
        for (int v : nums){
            max = max > uf.find(v) - v + 1 ? max : (uf.find(v) - v +1);
        }

        return max;
    }

}

class UnionFind {
    private int count;
    private Map<Integer, Integer> parent;

    UnionFind(int[] arr){
        parent = new HashMap<Integer, Integer>();

        for (int v : arr){
            parent.put(v, v);//各自为战
        }

        count = parent.size();//无用
    }

    //合并
    void union(int p, int q){
        Integer rootP = find(p), rootQ = find(q);
        if (rootP == rootQ){
            return;
        }
        if (rootP == null || rootQ == null){
            return;
        }

        parent.put(rootP, rootQ);

        count--;
    }

    Integer find(int p){
        if (!parent.containsKey(p)){
            return null;
        }

        int root = p;
        while (root != parent.get(root)){
            root = parent.get(root);
        }

        //路径压缩
        while (p != parent.get(p)){
            int curr = p;
            p = parent.get(p);
            parent.put(curr, root);
        }

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 58/142. 环形链表II



#### （1）题目

给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。

说明：不允许修改给定的链表。

进阶：

你是否可以使用 O(1) 空间解决此题？



#### （2）思路

* 快慢指针
* 快慢指针均指向头节点head，快指针fast每次走两步，慢指针slow每次走一步
  * 若是环形链表，则fast，slow将在某一节点相遇
  * 若不是环形链表，fast将指向空节点
* 当快慢指针相遇后，将快指针指向头节点head，与慢指针一样每次走一步，快慢指针相遇的节点即为环的起点
* 数学分析：
  * 第一次相遇：f，s分别为第一次相遇时快慢指针走的路程，a为头节点到环入口的距离，b为环的长度
    * f = 2s（快指针每次两步，慢指针每次一步）
    * f - s = nb（快指针追上慢指针，比慢指针多走几个环）
    * 由上 s = nb
  * 第二次相遇：快指针指向头节点head，每次走一步，当快指针走到环的起点时
    * f = a，s = nb + a
    * 慢指针也将走到环的起点



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {

        //快慢指针
        ListNode fast = head, slow = head;
        
        //第一次相遇
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                break;
            }
        }


        if (fast == null || fast.next == null){
            return null;
        }
	
        //第二次相遇
        fast = head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 60/146. LRU缓存机制（未解决）



#### （1）题目

运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制 。
实现 LRUCache 类：

LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。


进阶：你是否可以在 O(1) 时间复杂度内完成这两种操作？



#### （2）思路

* 使用双向链表和哈希表实现



#### （3）实现

```java
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {


    class DLinkedNode{
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode(){}
        public DLinkedNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null){
            return -1;
        }

        moveToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null){

            DLinkedNode newNode = new DLinkedNode(key, value);

            cache.put(key, newNode);

            addToHead(newNode);

            size++;
            if (size > capacity){
                DLinkedNode tail = removeTail();

                cache.remove(tail.key);
                size--;
            }
        }else{
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node){
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail(){
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
```





### 63/208. 实现Trie（前缀树）（ipt）



#### （1）题目

Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。

请你实现 Trie 类：

Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 



#### （2）思路





#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
class Trie {

    private Trie[] children;
    private boolean isEnd;

    /** Initialize your data structure here. */
    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null){
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }

        node.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix){
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null){
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
```





### 64/238. 除自身以外数组的乘积



#### （1）题目

给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。



#### （2）思路

* prefix[ ], suffix[ ]记录数组的前缀乘积和后缀乘积
* 返回数组res[i] = prefix[i] * suffix[i]
* 时间复杂度：O(n)，空间复杂度：O(n)



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] prefix = new int[nums.length];

        int[] suffix = new int[nums.length];

        prefix[0] = 1;
        int temp = 1;

        for (int i = 1; i < nums.length; i++) {
            temp *= nums[i-1];
            prefix[i] = temp;
        }

        suffix[nums.length-1] = 1;
        temp = 1;
        for (int i = nums.length-2; i >= 0 ; i--) {
            temp *= nums[i+1];
            suffix[i] = temp;
        }

        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {

            res[i] = prefix[i] * suffix[i];
        }

        return res;
    }
}
```





#### （2.2）思路（改进）

* 使用prefix[ ]记录nums数组的前缀乘积，不使用suffix[ ]记录nums数组的后缀乘积，并将prefix[ ]作为最后的输出数组
* 第一次遍历填充prefix[ ]，第二次遍历记录当前位置的后缀乘积temp，同时修改prefix[ ]
  * prefix[i] *= temp

* 时间复杂度：O(n)，空间复杂度：O(1)



#### （3.2）实现

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] prefix = new int[nums.length];

        prefix[0] = 1;
        int temp = 1;

        for (int i = 1; i < nums.length; i++) {

            temp *= nums[i-1];

            prefix[i] = temp;
        }

        temp = 1;

        for (int i = nums.length-1; i >= 0 ; i--) {

            prefix[i] = prefix[i] * temp;

            temp *= nums[i];

        }

        return prefix;
    }
}
```









