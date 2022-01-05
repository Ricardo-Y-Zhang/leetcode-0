# 2021/1/4

## 一、整数

### 001. 整数除法

#### （1）题目

给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。

 注意：

整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231−1]。本题中，如果除法结果溢出，则返回 231 − 1



#### （2）思路

* 



#### （3）实现

```java
class Solution {
    int res = 0;
    long target = 0;
    public int divide(int dividend, int divisor) {
        if (dividend == (-1) << 31 && divisor == -1){
            return (1 << 31) - 1;
        }

        if (divisor == 1){
            return dividend;
        }

        if (divisor == -1){
            return -dividend;
        }

        boolean flag = false;
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)){
            flag = true;
        }
        target = Math.abs((long)dividend);
        dfs(Math.abs((long) divisor), 1);
        if (flag == true){
            res = -res;
        }

        return res;
    }

    public void dfs(long temp, int tempSum){
        if (target > temp) {
            dfs(temp << 1, tempSum << 1);
        }

        if (target >= temp){
            res += tempSum;
            target -= temp;
        }
    }
}
```







### 002. 二进制加法

#### （1）题目

给定两个 01 字符串 `a` 和 `b` ，请计算它们的和，并以二进制字符串的形式输出。

输入为 **非空** 字符串且只包含数字 `1` 和 `0`。



#### （2）思路

* 模拟，逢 2 进 1
* `StringBuilder.reverse().toString();`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int n = a.length()-1, m = b.length()-1, plus = 0;
        while (n>=0||m>=0){
            int sum = plus;
            if (n>=0){
                sum += a.charAt(n--)-'0';
            }
            if (m>=0){
                sum += b.charAt(m--)-'0';
            }
            sb.append(sum&1);
            plus = sum >> 1;
        }
        if (plus == 1){
            sb.append(1);
        }
        return sb.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 003. 前 n 个数字二进制中 1 的个数

#### （1）题目

给定一个非负整数 `n` ，请计算 `0` 到 `n` 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。

 

#### （2）思路

* b & （b-1）：将 b 的二进制表达中最后一位 1 变为 0，其他不变
* 动态转移方程：`res[n] = res[n&(n-1)]+1`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n+1];
        res[0]=0;
        for (int i = 1; i <= n; i++) {
            res[i]=res[i&(i-1)]+1;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 004. 只出现一次的数字 

#### （1）题目

给你一个整数数组 `nums` ，除某个元素仅出现 **一次** 外，其余每个元素都恰出现 **三次 。**请你找出并返回那个只出现了一次的元素。



#### （2）思路

* 建立长度为 32 的数组 bits，记录所有数字的二进制位的 1 的出现次数
* 通过**无符号右移操作**，获取 num 所有位的值



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
                temp >>>= 1;
            }
        }
        int res = 0;
        for (int i = 0; i < bits.length; i++) {
            res += (bits[i]%3) << i;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 005. 单词长度的最大乘积

#### （1）题目

给定一个字符串数组 words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。



#### （2）思路

* 将字符串转换为 hash 储存，a~z 分别表示 0~25 位
* 对于字符串 i 和 j
  * hash<sub>i</sub> & hash<sub>j</sub> == 0：i 和 j 不包含相同字符
  * hash<sub>i</sub> & hash<sub>j</sub> != 0：i 和 j 包含相同字符



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProduct(String[] words) {
        int[] hash = new int[words.length];
        int index = 0;
        for (String str : words){
            int temphash = 0;
            for (int i = 0; i < str.length(); i++) {
                temphash |= 1 << (str.charAt(i)-'a');
            }
            hash[index++] = temphash;
        }
        int res = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i+1; j < words.length; j++) {
                if ((hash[i] & hash[j]) == 0){
                    res = Math.max(res, words[i].length()*words[j].length());
                }
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 006. 排序数组中两个数字之和

#### （1）题目

给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。

函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 0 开始计数 ，所以答案数组应当满足 0 <= answer[0] < answer[1] < numbers.length 。

假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。



#### （2）思路

* 双指针
* 左指针指向数组的首位， 右指针指向数组的末尾



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length-1;
        int[] res = new int[2];
        while (true){
            if (numbers[i]+numbers[j] == target){
                break;
            }else if (numbers[i]+numbers[j] < target){
                i++;
            }else{
                j--;
            }
        }
        res[0]=i;
        res[1]=j;
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```

