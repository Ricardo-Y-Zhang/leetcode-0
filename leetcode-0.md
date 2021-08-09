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

