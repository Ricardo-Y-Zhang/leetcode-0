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





## 二、数组

### 007. 数组中和为 0 的三个数

#### （1）题目

给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a ，b ，c ，使得 a + b + c = 0 ？请找出所有和为 0 且 不重复 的三元组。



#### （2）思路

* a + b + c = 0 可转换为 a + b = -c，将寻找 a + b + c = 0 **转换为寻找两数之和 a + b = -c**
* 对数组 nums 排序，遍历数组 [0, nums.length-2)，下标为 i
  * 双指针法寻找是否存在**两数之和 = -nums[i]**
  * 左指针初始化为 i+1，右指针初始化为 nums.length-1
  * 注意去除重复元素



#### （3）实现

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length-2; i++){
            //去重
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int m = i+1, n = nums.length-1;
            while (m<n){
                if (nums[m]+nums[n]==-nums[i]){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[m]);
                    list.add(nums[n]);
                    res.add(list);
                    m++;
                    while (m < n && nums[m]==nums[m-1]){//去重
                        m++;
                    }
                }else if (nums[m]+nums[n]<-nums[i]){
                    m++;
                    while (m < n && nums[m]==nums[m-1]){//去重
                        m++;
                    }
                }else{
                    n--;
                    while (n > m && nums[n] == nums[n+1]){//去重
                        n--;
                    }
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 008. 和大于等于 target 的最短子数组

#### （1）题目

给定一个含有 n 个正整数的数组和一个正整数 target 。

找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。



#### （2）思路

* 滑动窗口



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        if (nums.length == 0 || target == 0){
            return 0;
        }
        int i = 0, j = 0, sum = nums[0], res = nums.length+1;
        while (i<nums.length&&j < nums.length){
            if (sum >= target){
                res = Math.min(res , j-i+1);
                sum -= nums[i++];
            }else if (sum < target){
                j++;
                if (j < nums.length){
                    sum += nums[j];
                }
            }
        }
        if (res == nums.length+1){
            return 0;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 009. 乘积小于 k 的子数组

#### （1）题目

给定一个正整数数组 `nums`和整数 `k` ，请找出该数组内乘积小于 `k` 的连续的子数组的个数。

 

#### （2）思路

* 滑动窗口，i，j分别指向滑动窗口的左右边界，product 记录滑动窗口内元素的乘积
  * 循环条件：`i < nums.length && j < nums.length`
  * 若 product < k
    * 新增的符合要求的子数组个数为 **j-i+1**
    * 添加新的元素，`j++; product *= nums[j];`，注意边界是否越界
  * 若 product >= k
    * 去除元素，`product /= nums[i++];`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0){
            return 0;
        }
        int i = 0, j = 0, product = nums[0], res = 0;
        while (i < nums.length && j < nums.length){
            if (product < k){
                res += j-i+1;
                j++;
                if (j < nums.length){
                    product *= nums[j];
                }
            }else{
                product/=nums[i++];
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 010. 和为 k 的子数组

#### （1）题目

给定一个整数数组和一个整数 `k` **，**请找到该数组中和为 `k` 的连续子数组的个数。



#### （2）思路

* 前缀和
* 对于任意元素，其前缀和为 prefix，则只需找到**前缀和为 prefix-k 的个数**
* 算法流程：
  * HashMap<Integer, Integer> map 存储前缀和和其个数，初始化将 （0，1）压入map中，prefix 记录当前元素的前缀和
  * 遍历数组，更新prefix，在map中查找 **prefix-k 对应的值**
  * 在 map 中记录新的 prefix



#### （3）实现

```java
import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int subarraySum(int[] nums, int k) {
        //前缀和
        HashMap<Integer, Integer> map = new HashMap<>();
        int prefix = 0, res = 0;
        map.put(prefix, 1);
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            //找到前缀和为 prefix-k 的个数
            res += map.getOrDefault(prefix-k, 0);
            map.put(prefix, map.getOrDefault(prefix, 0)+1);
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 011. 0和1个数相同的子数组

#### （1）题目

给定一个二进制数组 `nums` , 找到含有相同数量的 `0` 和 `1` 的最长连续子数组，并返回该子数组的长度。



#### （2）思路

* 前缀和记录截至到当前元素，数组中 1 和 0 元素的数量差
* HashMap<Integer, Integer> map 记录数组中 [0, i] 间元素中 1 和 0 的数量差，key 为数量差，value 为最小的下标
  * 初始化添加键值对 (0, -1)
* 遍历数组，记录 1 和 0 的数量差 minus，当前下标为 i，res 为该子数组的长度
  * 若 map 中含有 key 为 minus 的键值对，则可得到含有相同数量的 0 和 1 的最长连续子数组，长度为 **i-map.get(minus)**，更新 res
  * 否则，将 minus-i 作为新的键值对添加到 map 中



#### （3）实现

```java
import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int minus = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]==1){
                minus++;
            }else{
                minus--;
            }
            if (map.containsKey(minus)){
                res = Math.max(res, i-map.get(minus));
            }else{
                map.put(minus, i);
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







### 012. 左右两边子数组的和相等

#### （1）题目

给你一个整数数组 nums ，请计算数组的 中心下标 。

数组 中心下标 是数组的一个下标，其左侧所有元素相加的和等于右侧所有元素相加的和。

如果中心下标位于数组最左端，那么左侧数之和视为 0 ，因为在下标的左侧不存在元素。这一点对于中心下标位于数组最右端同样适用。

如果数组有多个中心下标，应该返回 最靠近左边 的那一个。如果数组不存在中心下标，返回 -1 。



#### （2）思路

* 前缀和，sum为数组元素之和，prefix为当前元素的前缀和
* 遍历数组，对于下标为 i 的元素
  * prefix = sum - prefix - nums[i]，则 i 为中心下标



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        int res = -1, prefix = 0;
        for (int i = 0; i < nums.length; i++) {
            if (prefix == sum - prefix - nums[i]){
                res = i;
                break;
            }
            prefix += nums[i];
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 013. 二维子矩阵的和

#### （1）题目

给定一个二维矩阵 matrix，以下类型的多个请求：

计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
实现 NumMatrix 类：

NumMatrix(int[][] matrix) 给定整数矩阵 matrix 进行初始化
int sumRegion(int row1, int col1, int row2, int col2) 返回左上角 (row1, col1) 、右下角 (row2, col2) 的子矩阵的元素总和。



#### （2）思路

* 求取每一行元素的前缀和
* 遍历 row1~row2，根据前缀和求取每一行元素之和，累加即为子矩阵的元素总和



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class NumMatrix {
    int[][] prefix;
    public NumMatrix(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length+1;
        prefix = new int[n][m];
        for (int i = 0; i < n; i++) {
            prefix[i][0]=0;
            for (int j = 1; j < m; j++) {
                prefix[i][j]=prefix[i][j-1]+matrix[i][j-1];
            }
        }

    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = 0;
        for (int i = row1; i < row2+1; i++) {
            res += prefix[i][col2+1]-prefix[i][col1];
        }
        return res;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
//leetcode submit region end(Prohibit modification and deletion)
```





### 014. 字符串中的变位词

#### （1）题目

给定两个字符串 `s1` 和 `s2`，写一个函数来判断 `s2` 是否包含 `s1` 的某个变位词。

换句话说，第一个字符串的排列之一是第二个字符串的 **子串** 。



#### （2）思路

* 滑动窗口
* 使用 int[] cnt 统计 s1 和 s2 的子串之间的每个字符的差值，diff 统计字符不同的个数
* 滑动窗口每次向右移动，统计进入窗口的字符和离开窗口的字符，通过 cnt[ch-'a'] 是否为 0 来改变 diff 记录字符不同的个数
  * 若 diff  = 0，则当前子串是 s1 的变位词



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] cnt = new int[26];
        int m = s1.length(), n = s2.length(), diff = 0;
        if (m>n){
            return false;
        }
        for (int i = 0; i < m; i++) {
            cnt[s1.charAt(i)-'a']--;
            cnt[s2.charAt(i)-'a']++;
        }
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i]!=0) diff++;
        }
        if (diff==0){
            return true;
        }
        for (int i = m; i < n; i++) {
            int out = s2.charAt(i-m)-'a';
            cnt[out]--;
            if (cnt[out]==0) diff--;
            if (cnt[out]==-1) diff++;
            int in = s2.charAt(i)-'a';
            cnt[in]++;
            if (cnt[in]==0) diff--;
            if (cnt[in]==1) diff++;
            if (diff==0) return true;
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 015. 字符串中所有的变位词

#### （1）思路

给定两个字符串 s 和 p，找到 s 中所有 p 的 变位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。

变位词 指字母相同，但排列不同的字符串。



#### （2.1）思路

* 思路同上，遍历字符串 s，找出所有的变位词的起始位置



#### （3.1）实现

```java
import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int m = p.length(), n = s.length();
        List<Integer> res = new ArrayList<>();
        if (n < m){
            return res;
        }
        int[] cnt = new int[26];
        int diff = 0;
        for (int i = 0; i < m; i++) {
            cnt[p.charAt(i)-'a']--;
            cnt[s.charAt(i)-'a']++;
        }

        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i]!=0){
                diff++;
            }
        }
        if (diff == 0){
            res.add(0);
        }
        for (int i = m; i < n; i++) {
            int out = s.charAt(i-m)-'a';
            cnt[out]--;
            if (cnt[out]==0) diff--;
            else if (cnt[out]==-1) diff++;
            int in = s.charAt(i)-'a';
            cnt[in]++;
            if (cnt[in]==0) diff--;
            else if (cnt[in]==1) diff++;
            if (diff==0) res.add(i-m+1);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





#### （2.2）思路

* 双指针
* int[] cnt 记录当前子串和目标子串的字符差
* 右指针右移，统计新的字符 x，`cnt[x-'a']++;`
  * 若此时 cnt[x-'a']>0，则右移左指针，直到 cnt[x-'a'] = 0，保证**当前子串的字符为目标子串的字符的子集**
  * 若当前子串长度 = 目标子串长度，则当前子串为目标子串的异位词



#### （3.2）实现

```java
import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int m = p.length(), n = s.length();
        List<Integer> res = new ArrayList<>();
        if (n < m){
            return res;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < m; i++) {
            cnt[p.charAt(i)-'a']--;
        }

        int left = 0, right = 0;
        while (right < n){
            int in = s.charAt(right)-'a';
            cnt[in]++;
            while (cnt[in]>0){
                int out = s.charAt(left++)-'a';
                cnt[out]--;
            }
            right++;
            if (right-left==m){
                res.add(left);
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 016. 不含重复字符的最长子字符串

#### （1）题目

给定一个字符串 `s` ，请你找出其中不含有重复字符的 **最长连续子字符串** 的长度。



#### （2）思路

* 滑动窗口
* 思路同上



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n == 0){
            return 0;
        }
        int[] cnt = new int[200];
        int left = 0, right = 0, res = 0;
        while (right < n){
            int in = s.charAt(right);
            cnt[in]++;
            while (cnt[in]>1){
                int out = s.charAt(left);
                cnt[out]--;
                left++;
            }
            right++;
            res = Math.max(res, right-left);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







#### （2.2）思路

* HashMap<Character, Integer> key为字符，value为该字符最近的出现位置的下标

* 右指针右移时，将左指针移动到第一个重复字符的下一个位置



#### （3.2）实现

```java
import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n == 0){
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0, res = 0;
        while (right < n){
            Character ch = s.charAt(right);
            if (map.containsKey(ch)){
                left = Math.max(left, map.get(ch)+1);
            }
            map.put(ch, right);
            res = Math.max(res, right-left+1);
            right++;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 017. 含有所有字符的最短字符串

#### （1）题目

给定两个字符串 s 和 t 。返回 s 中包含 t 的所有字符的最短子字符串。如果 s 中不存在符合条件的子字符串，则返回空字符串 "" 。

如果 s 中存在多个符合条件的子字符串，返回任意一个。 

注意： 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。



#### （2）思路

* 滑动窗口
* 在 s 上滑动窗口，通过移动 right 指针不断扩张窗口。当窗口包含 t 所需的全部字符后，如果能收缩，就收缩窗口直到得到最小窗口



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String minWindow(String s, String t) {
        int[] chs = new int[52];
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (ch >= 'a' && ch <= 'z'){
                chs[ch-'a']--;
            }else{
                chs[ch-'A'+26]--;
            }
        }
        int diff = 0;
        for (int i = 0; i < chs.length; i++) {
            if (chs[i]<0){
                diff++;
            }
        }
        int left = 0, right = 0, len = s.length()+1;
        String str = "";
        while (right < s.length()){
            char ch = s.charAt(right);
            if (ch >= 'a' && ch <= 'z'){
                chs[ch-'a']++;
                if (chs[ch-'a']==0) diff--;
            }else{
                chs[ch-'A'+26]++;
                if (chs[ch-'A'+26]==0) diff--;
            }
            right++;
            //收缩窗口
            if (diff==0){
                while (diff==0){
                    ch = s.charAt(left);
                    if (ch>='a' && ch<='z'){
                        chs[ch-'a']--;
                        if (chs[ch-'a']<0) diff++;
                    }else{
                        chs[ch-'A'+26]--;
                        if (chs[ch-'A'+26]<0) diff++;
                    }
                    left++;
                }
                //[left-1, right-1]为当前的最短字符串
                if (right-left+1<len){
                    len = right-left+1;
                    str = s.substring(left-1, right);
                }
            }
        }
        return str;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 018. 有效的回文

#### （1）题目

给定一个字符串 `s` ，验证 `s` 是否是 **回文串** ，只考虑字母和数字字符，可以忽略字母的大小写。

本题中，将空字符串定义为有效的 **回文串** 。

 

#### （2）思路

* 将字符串 s 中的大写字母转换为小写字母，将字母和数字字符组成新的字符串 str
* 判断 str 是否为回文串



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z')){
                sb.append(ch);
            }
        }
        String str = sb.toString();
        int n = str.length();
        for (int i = 0; i < n / 2; i++) {
            if (str.charAt(i) != str.charAt(n-i-1)){
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 019. 最多删除一个字符得到回文

#### （1）题目

给定一个非空字符串 `s`，请判断如果 **最多** 从字符串中删除一个字符能否得到一个回文字符串



#### （2）思路

* 双指针，左右指针分别指向字符串的两侧
* 依次比较左右指针对应的字符是否相等
  * 如果相等，继续比较，`i++，j--;`
  * 如果不相等，分为两种情况，有一种为回文串即可
    * 删除左指针指向的字符，判断 [i+1, j] 为回文串
    * 删除右指针指向的字符，判断 [i, j-1] 为回文串



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean validPalindrome(String s) {
        int i = 0, j = s.length()-1;
        boolean flag = false;
        while (i < j){
            if (s.charAt(i) != s.charAt(j)){
                break;
            }else{
                i++;
                j--;
            }
        }
        if (i >= j){
            return true;
        }
        String str1 = s.substring(i, j);
        String str2 = s.substring(i+1, j+1);
        return judge(str1)||judge(str2);
    }
    public boolean judge(String str){
        int n = str.length();
        for (int i = 0; i < n / 2; i++) {
            if (str.charAt(i) != str.charAt(n-i-1)){
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 020. 回文子字符串的个数

#### （1）题目

给定一个字符串 `s` ，请计算这个字符串中有多少个回文子字符串。

具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。



#### （2）思路

* 对于字符串中每个字符
  * 将其当作对称中心，求回文串的最大长度
  * 将其当作对称中心的左边，求回文串的最大长度



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countSubstrings(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int m = i, n = i+1;
            while (m>=0 && n < s.length()){
                if (s.charAt(m) != s.charAt(n)){
                    break;
                }
                m--;
                n++;
            }
            res += n-i;
            m = i-1; n = i+1;
            while (m>=0 && n < s.length()){
                if (s.charAt(m) != s.charAt(n)){
                    break;
                }
                m--;
                n++;
            }
            res += i-m-1;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 三、链表

### 021. 删除链表的倒数第 n 个节点

#### （1）题目

给定一个链表，删除链表的倒数第 `n` 个结点，并且返回链表的头结点。



#### （2）思路

* 添加虚拟头节点，不必特殊考虑删除表头节点
* 使用快慢指针找到倒数第 n+1 个节点，然后删除第 n 个节点
  * 快慢指针 fast、slow 均初始化指向虚拟表头节点 nhead
  * 快指针先走 n+1 步，然后快慢指针同时右移，当快指针指向 null，慢指针指向倒数第 n+1 个节点
  * `slow.next = slow.next.next;`，删除倒数第 n 个节点



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
        ListNode nhead = new ListNode();
        nhead.next = head;
        ListNode fast = nhead, slow = nhead;
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }
        while (fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return nhead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







### 022. 链表中环的入口节点

#### （1）题目

给定一个链表，返回链表开始入环的第一个节点。 从链表的头节点开始沿着 next 指针进入环的第一个节点为环的入口节点。如果链表无环，则返回 null。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。

说明：不允许修改给定的链表。



#### （2）思路

* 快慢指针
  * 快慢指针初始化指向虚拟头节点
  * 快指针每次走两步，慢指针每次走一步，若链表中存在环，快慢指针会相遇
  * 相遇后，慢指针指向链表虚拟头节点，快慢指针每次**均走一步**，快慢指针会在环的入口节点相遇



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
        if (head == null || head.next == null){
            return null;
        }
        ListNode vhead = new ListNode();
        vhead.next = head;
        ListNode fast = vhead.next.next, slow = vhead.next;
        while (fast != slow && fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        if (fast != slow){
            return null;
        }
        slow = vhead;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







### 023. 两个链表的第一个重合节点

#### （1）题目

给定两个单链表的头节点 `headA` 和 `headB` ，请找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 `null` 。



#### （2）思路

* 拼接两个单链表，找到两个链表第一个相同节点



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
        ListNode tempA = headA, tempB = headB;
        while (tempA != tempB){
            tempA = tempA == null ? headB : tempA.next;
            tempB = tempB == null ? headA : tempB.next;
        }
        return tempA;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 024. 反转链表

#### （1）题目

给定单链表的头节点 `head` ，请反转链表，并返回反转后的链表的头节点。



#### （2）思路

* 头插法构建链表
* 使用虚拟头节点 vhead，遍历链表
  * 将当前节点用头插法插入虚拟头节点 vhead 后



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
    public ListNode reverseList(ListNode head) {
        ListNode vhead = new ListNode();
        vhead.next = null;
        ListNode node = head;
        while (node != null){
            ListNode temp = node;
            node = node.next;
            temp.next = vhead.next;
            vhead.next = temp;
        }
        return vhead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 025. 链表中的两数相加

#### （1）题目

给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。

可以假设除了数字 0 之外，这两个数字都不会以零开头。



#### （2）思路

* 使用头插法构建新链表，长链表 l1 长度为 n，短链表 l2 长度为 m
  * 复制 l1 的前 n-m 个节点插入新链表中
  * 将长短链表剩余的 m 个节点的值两两相加，构建新节点，插入链表
  * 遍历新链表，进位，将节点的值转换为 0~9，同时使用头插法反转链表



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
        int n = 0, m = 0;
        ListNode node = l1;
        while (node != null){
            node = node.next;
            n++;
        }
        node = l2;
        while (node != null){
            node = node.next;
            m++;
        }
        int diff = Math.abs(n-m);
        ListNode  lnode , snode;
        if (n >= m){
            lnode = l1;
            snode = l2;
        }else{
            lnode = l2;
            snode = l1;
        }

        ListNode vhead = new ListNode();
        vhead.next = null;
        ListNode node1 = lnode, node2 = snode;
        for (int i = 0; i < diff; i++) {
            ListNode nnode = new ListNode(node1.val);
            nnode.next = vhead.next;
            vhead.next = nnode;
            node1 = node1.next;
        }
        while (node1 != null){
            ListNode nnode = new ListNode(node1.val+node2.val);
            nnode.next = vhead.next;
            vhead.next = nnode;
            node1 = node1.next;
            node2 = node2.next;
        }

        ListNode vvhead = new ListNode();
        vvhead.next = null;
        node = vhead.next;
        int plus = 0;
        while (node != null){
            int sum = node.val;
            node.val = (sum+plus)%10;
            plus = (sum+plus)/10;
            ListNode temp = node;
            node = node.next;
            temp.next = vvhead.next;
            vvhead.next = temp;
        }
        if (plus > 0){
            ListNode temp = new ListNode(plus);
            temp.next = vvhead.next;
            vvhead.next = temp;
        }
        return vvhead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 026. 重排链表

#### （1）题目

给定一个单链表 L 的头节点 head ，单链表 L 表示为：

 L0 → L1 → … → Ln-1 → Ln 
请将其重新排列后变为：

L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。



#### （2）思路

* 找链表中点 
* 翻转链表的后半段
* 将前半段和后半段链表合并



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
    public void reorderList(ListNode head) {
        int length = 0;
        ListNode node = head;
        while (node != null){
            length++;
            node = node.next;
        }
        if (length <= 2){
            return;
        }

        //链表中点
        ListNode mid = head;
        for (int i = 0; i < (length+1)/2; i++) {
            ListNode temp = mid;
            mid = mid.next;
            if (i == (length+1)/2-1){
                temp.next = null;
            }
        }

        mid = reverse(mid);

        //链表合并
        ListNode node1 = head, node2 = mid;
        for (int i = 0; i < length / 2; i++) {
            ListNode temp1 = node1, temp2 = node2;
            node1 = node1.next;
            node2 = node2.next;
            temp2.next = node1;
            temp1.next = temp2;
        }
    }
    public ListNode reverse(ListNode temp){//反转链表
        ListNode vhead = new ListNode();
        vhead.next = null;
        while (temp != null){
            ListNode node = temp;
            temp = temp.next;
            node.next = vhead.next;
            vhead.next = node;
        }
        return vhead.next;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
```





### 027. 回文链表

#### （1）题目

给定一个链表的 **头节点** `head` **，**请判断其是否为回文链表。

如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。



#### （2）思路

* 找到链表的中间节点
* 翻转后半链表
* 链表是否为回文链表
* 恢复后半链表
* 返回结果



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
    public boolean isPalindrome(ListNode head) {
        if (head == null){
            return true;
        }
        ListNode mid = findMid(head);
        ListNode temp = reverse(mid.next);
        ListNode node1 = head, node2 = temp;
        boolean flag = true;
        while (node1 != null && node2 != null){
            if (node1.val != node2.val){
                flag = false;
                break;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        reverse(temp);
        return flag;
    }
    public ListNode findMid(ListNode head){//找中间节点
        ListNode vhead = new ListNode(0, head);
        ListNode fast = vhead, slow = vhead;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    public ListNode reverse(ListNode head){
        ListNode vhead = new ListNode(0, null);
        while (head != null){
            ListNode temp = head;
            head = head.next;
            temp.next = vhead.next;
            vhead.next = temp;
        }
        return vhead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```





### 028.  展平多级双向链表

#### （1）题目

多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。

给定位于列表第一级的头节点，请扁平化列表，即将这样的多级双向链表展平成普通的双向链表，使所有结点出现在单级双链表中。



#### （2）思路

* 深度优先搜索
* 对于任意节点 node，prev 为其前序节点
  * 将**前序节点 prev** 的 next 置为 node，node 的 prev 置为 prev
  * 修改当前的前序节点为 node
  * 记录节点 node 的 next 节点
  * 若 node 存在子节点 child，递归处理子节点 child（此时 node 的 next 可能发生变化）
  * 递归处理 next 节点



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

import java.util.ArrayList;

class Solution {
    Node prev = null;
    public Node flatten(Node head) {
        dfs(head);
        return head;
    }
    public void dfs(Node head){
        if (head == null){
            return ;
        }
        if (prev != null){
            prev.next = head;
        }
        head.prev = prev;
        prev = head;
        Node next = head.next;//记录下一节点，因为处理子节点后，head.next 可能发生变化
        //处理子节点
        if (head.child != null){
            dfs(head.child);
            head.child = null;//子节点置空
        }
        dfs(next);

    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 029. 排序的循环链表

#### （1）题目

给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。

给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。

如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。

如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。



#### （2）思路

* 遍历链表，找出真正的头节点即最小的节点 min 与其前序节点 minPre 和最大的节点值 max
* 若新元素 insertVal 属于 (min.val, max)，则再次遍历链表查找插入位置
* 若 insertVal 不属于 (min.val, max)，则将新节点插入在 minPre 和 min 之间



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/

class Solution {
    public Node insert(Node head, int insertVal) {
        if (head == null){
            head = new Node(insertVal, null);
            head.next = head;
            return head;
        }
        if (head.next == head){
            Node node = new Node(insertVal, head);
            head.next = node;
            return head;
        }
        Node min = head.next, minPre = head, node = head.next, pre = head;
        int max = head.val;
        while (node != head){
            if (node.val < min.val){
                min = node;
                minPre = pre;
            }
            max = Math.max(max, node.val);
            node = node.next;
            pre = pre.next;
        }
        if (node.val <= min.val){
            min = node;
            minPre = pre;
        }
        if (insertVal > min.val && insertVal < max){
            while (min.val < insertVal){
                min = min.next;
                minPre = minPre.next;
            }
        }
        node = new Node(insertVal, min);
        minPre.next = node;
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```





## 四、哈希表



### 030. 插入、删除和随机访问都是O(1)的容器

#### （1）题目

设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构：

* insert(val)：当元素 val 不存在时返回 true ，并向集合中插入该项，否则返回 false 。
* remove(val)：当元素 val 存在时返回 true ，并从集合中移除该项，否则返回 false 。
* getRandom：随机返回现有集合中的一项。每个元素应该有 相同的概率 被返回。



#### （2）思路

* 重点在于：**随机访问是O(1)**
* **使用 HashMap 和可变数组**实现该容器
  * HashMap<Integer, Integer>：
    * key：元素值
    * value：元素在可变数组中下标
  * list<Integer>：记录存在的元素
  * insert(val)：
    * 将 val 插入 list 中
    * 将 val 插入 map 中，value 为当前元素在 list 中的下标
  * remove(val)：
    * 在 map 中获得 val 在 list 中的下标 index
    * 将 val 和 list 中最后一个元素交换，并删除最后一个元素
    * 删除 map 中 val 对应键值对
  * getRandom()：
    * 使用 Random 返回 [0, list.size()-1] 中的随机值，从 list 中返回相应元素



#### （3）实现

```java

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

//leetcode submit region begin(Prohibit modification and deletion)
class RandomizedSet {
    List<Integer> list;
    HashMap<Integer, Integer> map;
    Random random;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        list = new ArrayList<Integer>();
        map = new HashMap<Integer, Integer>();
        random = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)){
            return false;
        }
        map.put(val, list.size());
        list.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)){
            return false;
        }
        int index = map.get(val);//删除元素的下标
        int last = list.get(list.size()-1);//最后一个元素
        map.put(last, index);//修改最后一个元素的位置值
        map.remove(val);//删除最后一个元素
        list.set(index, last);//list中修改val元素为last
        list.remove(list.size()-1);//list中删除最后一个元素
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int r  = random.nextInt(list.size());
        return list.get(r);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
//leetcode submit region end(Prohibit modification and deletion)
```





### 031. 最近最少使用内存

#### （1）题目

运用所掌握的数据结构，设计和实现一个  LRU (Least Recently Used，最近最少使用) 缓存机制 。

实现 LRUCache 类：

LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。



#### （2）思路

* 双链表 + HashMap<Integer, Node>
* 双链表记录**节点的使用先后顺序**，方便移动删除节点，所以使用双链表。HashMap 记录 **key 对 Node 的映射关系**
* LRUCache(int capacity)：
  * 全局变量 size 记录 LRU 缓存的容量
  * 创建虚拟头节点 head 和虚拟尾节点 tail，构建双链表
* int get(int key)
  * 若 HashMap 中不存在 key 的映射，返回 -1
  * 若 HashMap 中存在 key 的映射 node
    * 调整 node 的使用顺序，**将节点 node 移动到双链表的尾部**
    * 返回 node.value
* void put(int key, int value) 
  * 若 HashMap 中不存在 key 的映射
    * 创建新的节点 (key, value)，使用尾插法，插入双链表的尾部
    * HashMap 中记录 key-node 的映射
    * 若**容量超过 size**，则删除头节点 head.next，同时删除 map 中 head.next.key 的映射
  * 若 HashMap 中存在 key 的映射
    * 从 HashMap 中获取 key 的映射 node
    * 修改 node 的 value 值
    * 修改 node 的使用顺序，**将 node 移动到双链表的尾部**



#### （3）实现

```java

import java.util.HashMap;


//leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {
    class Node{
        int key, value;
        Node pre, next;
        public Node(){}
        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }
    Node head = new Node();
    Node tail = new Node();
    int size = 0;
    HashMap<Integer, Node> map;
    public void deleteNode(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
    public void addNode(Node node){//尾插法
        node.next = tail;
        tail.pre.next = node;
        node.pre = tail.pre;
        tail.pre = node;
    }
    public void moveNode(Node node){
        deleteNode(node);
        addNode(node);
    }
    public LRUCache(int capacity) {
        this.size = capacity;
        this.map = new HashMap<Integer, LRUCache.Node>();
        head.next = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        moveNode(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if (!map.containsKey(key)){
            if (size == map.size()){
                map.remove(head.next.key);
                deleteNode(head.next);
            }
            Node node = new Node(key,value);
            addNode(node);
            map.put(key, node);
        }else{
            Node node = map.get(key);
            node.value = value;
            moveNode(node);
        }
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



### 032. 有效的变位词

#### （1）题目

给定两个字符串 s 和 t ，编写一个函数来判断它们是不是一组变位词（字母异位词）。

注意：若 s 和 t 中每个字符出现的次数都相同且字符顺序不完全相同，则称 s 和 t 互为变位词（字母异位词）。

 

#### （2）思路

* 算法流程：
  * 判断字符串 s 和 t 的长度是否相等，不等返回 false
  * 判断字符串 s 和 t 是否相等，相等返回 false
  * 使用 HashMap<Character, Integer> map 记录字符出现的频次
  * 遍历字符串 s，记录每个字符的出现频次
  * 遍历字符串 t，**将 map 中记录的每个字符出现频次 -1**
    * 若出现某个字符的对应频次 < 0 的情况，则证明字符出现频次不同，返回 false
  * 返回 true



#### （3）实现

```java

import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length() || s.equals(t)){//长度不相等或完全一致
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            map.put(ch,map.getOrDefault(ch, 0)-1);
            if (map.get(ch)<0){
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







### 033. 变位词组

#### （1）题目

给定一个字符串数组 `strs` ，将 **变位词** 组合在一起。 可以按任意顺序返回结果列表。

**注意：**若两个字符串中每个字符出现的次数都相同，则称它们互为变位词。



#### （2）思路

* 将字符串按字典序排序，并以此作为 key 值，value值是**排序后均为此字符串的字符串列表 list**



#### （3）实现

```java

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for (int i = 0; i < strs.length; i++) {
            char[] chs = strs[i].toCharArray();
            Arrays.sort(chs);
            String sortStr = new String(chs);
            List<String> list = map.getOrDefault(sortStr, new ArrayList<String>());
            list.add(strs[i]);
            map.put(sortStr, list);
        }
        return new ArrayList<>(map.values());
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```







### 034. 外星语言是否排序

#### （1）题目 

某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。

给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。



#### （2）思路

* 按照字母表的顺序 order，比较字符串的大小



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        for (int i = 0; i < words.length - 1; i++) {
            String str1 = words[i], str2 = words[i+1];
            int index = 0;
            while (index < str1.length() && index < str2.length()){
                char ch1 = str1.charAt(index), ch2 = str2.charAt(index);
                int order1 = order.indexOf(ch1), order2 = order.indexOf(ch2);
                if (order1 > order2){
                    return false;
                }else if (order1 < order2){
                    break;
                }
                index++;
                if (index == str2.length() && str1.length() > str2.length()){
                    return false;
                }
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 035. 最小时间差

#### （1）题目

给定一个 24 小时制（小时:分钟 **"HH:MM"**）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。



#### （2）思路

* 将时间字符串 "HH:MM" 转换为分钟数，并按照自然序排序
* 遍历时间列表，求出最小时间差
  * 对于任意两个时间 time1 和 time2（time2 >time1），存在两个差值，均需要考虑
    * time2-times1
    * 24*60- ( time2-time1 )
  * 注意计算最小时间和最大时间之间的两种差值



#### （3）实现

```java

import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int[] times = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            times[i]=toInt(timePoints.get(i));
        }
        Arrays.sort(times);
        int res = 24*60-(times[times.length-1]-times[0]);
        for (int i = 0; i < times.length - 1; i++) {
            int minus = times[i+1]-times[i];
            int minTime = Math.min(minus, 24*60-minus);
            res = Math.min(res, minTime);
        }
        return res;
    }
    public int toInt(String str){
        String[] strs = str.split(":");
        return Integer.parseInt(strs[0])*60+Integer.parseInt(strs[1]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 036. 后缀表达式

#### （1）题目

有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。

 

说明：

整数除法只保留整数部分。
给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。



#### （2）思路

* 使用栈模拟后缀表达式，遍历后缀表达式
  * 当前字符不是运算符，则将其压入栈中
  * 当前字符是运算符
    * 从栈顶弹出元素：i2, i1
    * 根据运算符计算，并将结果压入栈中
* 遍历结束后，栈中元素即为结果



#### （3）实现

```java
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int evalRPN(String[] tokens) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < tokens.length; i++) {
            String str = tokens[i];
            if ("+".equals(str)||"-".equals(str)||"*".equals(str)||"/".equals(str)){
                Integer i2 = list.pollLast(), i1 = list.pollLast();
                if ("+".equals(str)){
                    list.add(i1+i2);
                }else if ("-".equals(str)){
                    list.offerLast(i1-i2);
                }else if ("*".equals(str)){
                    list.offerLast(i1*i2);
                }else{
                    list.offerLast(i1/i2);
                }
            }else{
                list.offerLast(Integer.parseInt(str));
            }
        }
        return list.poll();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 037. 小行星的碰撞

#### （1）题目

给定一个整数数组 asteroids，表示在同一行的小行星。

对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）。每一颗小行星以相同的速度移动。

找出碰撞后剩下的所有小行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。



#### （2）思路

* 栈
* 对于任意整数 temp
  * 正数：不会产生碰撞，直接压入栈中
  * 负数：
    * **栈为空**，不会发生碰撞，将 temp 压入栈中
    * 若栈顶元素 first 是**负数**，不会发生碰撞，将 temp 压入栈中
    * 若栈顶元素 first 是**正数**，会产生碰撞
      * first < -temp：正方向行星 first 爆炸，弹出栈顶元素，继续碰撞
      * first == -temp：正负方向行星均爆炸，弹出栈顶元素，结束碰撞
      * first > -temp：负方向行星 temp 爆炸，结束碰撞



#### （3）实现

```java

import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < asteroids.length; i++) {
            int temp = asteroids[i];
            if (list.isEmpty() || temp >0){
                list.offer(temp);
            }else{

                while (!list.isEmpty() && list.peekLast()>0 && list.peekLast() < -temp){
                    list.pollLast();
                }
                if (list.isEmpty() || list.peekLast() < 0){
                    list.offer(temp);
                }else if (list.peekLast() == -temp){
                    list.pollLast();
                }

            }
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.pollFirst();
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 038. 每日温度

#### （1）题目

请根据每日 气温 列表 temperatures ，重新生成一个列表，要求其对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。



#### （2）思路

* 使用栈维护一个**气温非递增的下标**序列
* 对于任意下标为 i 的气温 temp
  * 若栈为空，直接将下标 i 压入栈中
  * 栈不为空，栈顶元素为 index
    * temperatures[index] >= temp，即当前气温低于或等于栈顶下标对应的气温；将 i 压入栈中
    * temperatures[index] < temp，当前气温高于栈顶下标对应的气温
      * 下标为 index 天需要等待 **i-index** 天可观测到更高温度
      * 循环弹出栈顶元素，直到栈空或 temperatures[index] >= temp
      * 将 i 压入栈中



#### （3）实现

```java
import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < temperatures.length; i++) {
            int temp = temperatures[i];
            while (!list.isEmpty()  && temperatures[list.peekLast()] < temp){
                int index = list.pollLast();
                res[index] = i-index;
            }
            list.offer(i);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```









## 五、队列

### 041. 滑动窗口的平均值

#### （1）题目

给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值。

实现 MovingAverage 类：

* MovingAverage(int size) 用窗口大小 size 初始化对象。
* double next(int val) 成员函数 next 每次调用的时候都会往滑动窗口增加一个整数，请计算并返回数据流中最后 size 个值的移动平均值，即滑动窗口里所有数字的平均值。



#### （2）思路

* 使用双端队列模拟滑动窗口，sum 记录滑动窗口内元素之和
* 滑动窗口增加整数：将新元素添加到双端队列队尾，更新 sum
  * 元素个数 <= 滑动窗口大小
  * 元素个数 > 滑动窗口大小，将新元素队头元素出队，更新 sum



#### （3）实现

```java

import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class MovingAverage {
    int size;
    double sum;
    LinkedList<Integer> list;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        this.sum = 0;
        list = new LinkedList<>();
    }
    
    public double next(int val) {
        list.offer(val);
        sum += val;
        if (list.size() > size){
            sum -= list.pollFirst();
        }
        return sum/list.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
//leetcode submit region end(Prohibit modification and deletion)
```





### 042. 最近请求次数

#### （1）题目

写一个 RecentCounter 类来计算特定时间范围内最近的请求。

请实现 RecentCounter 类：

* RecentCounter() 初始化计数器，请求数为 0 。
* int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。

保证 每次对 ping 的调用都使用比之前更大的 t 值。




#### （2）思路

* 使用双端队列模拟滑动窗口
* 对于任意时间 t，将 t 从队尾入队
  * 将队头**小于 t-3000 的元素**出队
  * 队列元素个数即为在 [t-3000, t] 内发生的请求数



#### （3）实现

```java

import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class RecentCounter {
    LinkedList<Integer> list;
    public RecentCounter() {
        list = new LinkedList<>();
    }
    
    public int ping(int t) {
        list.offer(t);
        while (list.peekFirst()<t-3000){
            list.pollFirst();
        }
        return list.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */
//leetcode submit region end(Prohibit modification and deletion)
```





### 043. 往完全二叉树中添加节点

#### （1）题目

完全二叉树是每一层（除最后一层外）都是完全填充（即，节点数达到最大，第 n 层有 2n-1 个节点）的，并且所有的节点都尽可能地集中在左侧。

设计一个用完全二叉树初始化的数据结构 CBTInserter，它支持以下几种操作：

* CBTInserter(TreeNode root) 使用根节点为 root 的给定树初始化该数据结构；
* CBTInserter.insert(int v)  向树中插入一个新节点，节点类型为 TreeNode，值为 v 。使树保持完全二叉树的状态，并返回插入的新节点的父节点的值；
* CBTInserter.get_root() 将返回树的根节点。



#### （2）思路

* LinkedList<TreeNode>  list 记录完全二叉树层序遍历中**不存在左孩子或右孩子节点的节点**
* CBTInserter(TreeNode root) ：
  * 记录根节点 root
  * 层序遍历当前完全二叉树，并更新上述序列 list
* CBTInserter.insert(int v) :
  * 构建新节点 node，将 node 插入 list 的队尾
  * 获取队头节点 first
    * 若 first 左孩子结点为空， node 作为 first 的左孩子结点
    * 若 first 右孩子结点为空，node 作为 first  的右孩子节点，此时 first 的做右孩子节点均不为空，弹出队头节点
* CBTInserter.get_root() 
  * 返回根节点 root



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
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class CBTInserter {
    LinkedList<TreeNode> list;
    TreeNode root;
    public CBTInserter(TreeNode root) {
        this.root = root;
        list = new LinkedList<TreeNode>();
        list.offer(this.root);
        //层序遍历，记录做右孩子节点为空的节点
        while (true){
            TreeNode first = list.peekFirst();
            if (first.left != null){
                list.offer(first.left);
            }
            if (first.right != null){
                list.offer(first.right);
            }
            if (first.left != null && first.right != null){
                list.pollFirst();
            }else{
                break;
            }
        }
    }
    
    public int insert(int v) {
        TreeNode node = new TreeNode(v, null, null);
        list.offer(node);
        TreeNode ancestor = list.peekFirst();
        if (ancestor.left == null){
            ancestor.left = node;
        }else{
            ancestor.right = node;
            list.pollFirst();
        }
        return ancestor.val;
    }
    
    public TreeNode get_root() {
        return root;
    }
}

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(v);
 * TreeNode param_2 = obj.get_root();
 */
//leetcode submit region end(Prohibit modification and deletion)
```





### 044. 二叉树每层的最大值

#### （1）题目

给定一棵二叉树的根节点 `root` ，请找出该二叉树中每一层的最大值。



#### （2）思路

* 层序遍历二叉树，并使用 max 记录每一层节点的最大值



#### （3）实现

````java

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;

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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        TreeNode tail = root;
        int max = Integer.MIN_VALUE;
        while (!list.isEmpty()){
            TreeNode node = list.pollFirst();
            max = Math.max(max, node.val);
            if (node.left != null){
                list.add(node.left);
            }
            if (node.right != null){
                list.add(node.right);
            }

            //当前节点是该层最右节点
            if (node == tail){
                res.add(max);
                tail = list.peekLast();//更新下一层的最右节点
                max = Integer.MIN_VALUE;//重置最大值
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
````





### 045. 二叉树最底层最左边的值

#### （1）题目

给定一个二叉树的 **根节点** `root`，请找出该二叉树的 **最底层 最左边** 节点的值。

假设二叉树中至少有一个节点。



#### （2）思路

* 层序遍历二叉树
* res 记录每一层最左边节点的值，初始化为 root.val；tail 记录每一层最右边节点，初始化为 root；LinkedList<TreeNode> list 作为队列，初始化将 root 入队
* list 非空时，层序遍历：队头节点 node
  * node 的左右孩子节点非空时，做右孩子节点入队
  * 若 node = tail，即为该层最右边节点
    * list 非空时
    * 更新 res，此时队头节点即为下一层的最左边节点，`res = list.peekFirst().val;`
    * 更新 tail，此时队尾节点即为下一层的最右边节点，`tail = list.peekLast();`



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
    public int findBottomLeftValue(TreeNode root) {
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        list.offer(root);
        TreeNode tail = root;
        int res = root.val;
        while (!list.isEmpty()){
            TreeNode node = list.pollFirst();
            if (node.left != null){
                list.offer(node.left);
            }
            if (node.right != null){
                list.offer(node.right);
            }
            if (node == tail && !list.isEmpty()){
                res = list.peekFirst().val;
                tail = list.peekLast();
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







### 046. 二叉树的右侧视图

#### （1）题目

给定一个二叉树的 **根节点** `root`，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。



#### （2）思路

* 层序遍历二叉树并记录每一层最右边节点的值



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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();
        list.offer(root);
        TreeNode tail = root;
        while (!list.isEmpty()){
            TreeNode node = list.pollFirst();
            if (node.left != null){
                list.offer(node.left);
            }
            if (node.right != null){
                list.offer(node.right);
            }
            if (node == tail ){
                res.add(node.val);
                tail = list.peekLast();
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```







## 六、树



### 047. 二叉树剪枝

#### （1）题目

给定一个二叉树 根节点 root ，树的每个节点的值要么是 0，要么是 1。请剪除该二叉树中所有节点的值为 0 的子树。

节点 node 的子树为 node 本身，以及所有 node 的后代。



#### （2）思路

* 后序遍历，递归对二叉树的左右子树进行剪枝
* 若左右子树均被剪枝且当前节点值为 0 ，即 `root.left == null && root.right == null && root.val == 0`时，将当前子树剪枝，返回 null



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
    public TreeNode pruneTree(TreeNode root) {
        if (root == null){
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0){
            return null;
        }
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







### 048. 序列化与反序列化

#### （1）题目

序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。



#### （2）思路

* 层序遍历



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
        list.offer(root);
        while (!list.isEmpty()){
            TreeNode node = list.pollFirst();
            if (sb.length() != 0){
                sb.append(",");
            }
            if (node == null){
                sb.append("null");
            }else{
                sb.append(node.val);
                list.offer(node.left);
                list.offer(node.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strs = data.split(",");
        if (strs.length == 1){
            return null;
        }
        LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();//所有节点
        for (int i = 0; i < strs.length; i++) {
            nodes.offer(build(strs[i]));
        }
        TreeNode root = nodes.pollFirst();
        LinkedList<TreeNode> list = new LinkedList<TreeNode>();//层序遍历队列
        list.offer(root);
        while (!nodes.isEmpty()){
            TreeNode node = list.pollFirst();
            node.left = nodes.pollFirst();
            node.right = nodes.pollFirst();
            if (node.left != null){
                list.offer(node.left);
            }
            if (node.right != null){
                list.offer(node.right);
            }
        }
        return root;
    }
    public TreeNode build(String str){
        if (str.equals("null")){
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(str));
        node.left = null;
        node.right = null;
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

```





###  049. 从根节点到叶节点的路径数字之和

#### （1）题目

给定一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。

每条从根节点到叶节点的路径都代表一个数字：

* 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。

计算从根节点到叶节点生成的 所有数字之和 。

叶节点 是指没有子节点的节点。



#### （2）思路

* 深度优先搜索
* DFS(TreeNode root, int temp)：temp 记录从根节点到当前节点路径数字之和
* 对于任意节点
  * 叶节点：记录当前 temp
  * 非叶节点：递归遍历其左右孩子节点



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
    int res = 0;
    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return res;
    }
    public void dfs(TreeNode root, int temp){
        if (root == null){
            return;
        }
        temp = temp * 10 + root.val;
        if (root.left == null && root.right == null){
            res += temp;
        }
        if (root.left != null){
            dfs(root.left, temp);
        }
        if (root.right != null){
            dfs(root.right, temp);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 050. 向下的路径节点之和

#### （1）题目

给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。

路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。



#### （2）思路

* **深度优先搜索+前缀和**
* 将根节点到当前节点的路径节点之和存储在 HashMap 中



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
    int res = 0, target;
    HashMap<Integer, Integer> map;
    public int pathSum(TreeNode root, int targetSum) {
        this.target = targetSum;
        map = new HashMap<>();
        map.put(0, 1);
        dfs(root, 0);
        return res;
    }
    public void dfs(TreeNode root, int sum){
        if (root == null){
            return;
        }
        sum += root.val;
        res += map.getOrDefault(sum-target, 0);
        map.put(sum, map.getOrDefault(sum, 0)+1);
        dfs(root.left,sum);
        dfs(root.right, sum);
        map.put(sum, map.getOrDefault(sum,0)-1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 051. 节点之和最大的路径

#### （1）题目

路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。

路径和 是路径中各节点值的总和。

给定一个二叉树的根节点 root ，返回其 最大路径和，即所有路径上节点值之和的最大值。



#### （2）思路

* 深度优先搜索
* `public int dfs(TreeNode root)`：返回以 root 为起点的单边路径的最大值
  * 递归遍历左子树，left 记录以 root.left 为起点的单边路径的最大值
  * 递归遍历右子树，right 记录以 root.right 为起点的单边路径的最大值
  * 求出以 root 为左右中点的路径的最大值，并记录
  * 返回以 root 为起点的单边路径的最大值



#### （3）实现

```JAVA

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
    int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }
    public int dfs(TreeNode root){//以 root 为起点的单边路径的最大值
        if (root == null){
            return 0;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        int sum = root.val;//以 root 为左右中点的路径和的最大值
        if (left > 0){
            sum += left;
        }
        if (right > 0){
            sum += right;
        }
        max = Math.max(max, sum);
        return Math.max(left, right)>0?Math.max(left,right)+root.val:root.val;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







### 052. 展平二叉搜索树

#### （1）题目

给你一棵二叉搜索树，请 **按中序遍历** 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。



#### （2）思路

* 中序遍历二叉树
* 算法流程：inorder(TreeNode root)
  * 递归边界：root = null
  * 递归遍历左孩子结点，`inorder(root.left)`
  * 将左孩子节点置空
  * 将前序节点的右孩子节点置为当前节点，即此时中序遍历序列中最后一个节点
  * 递归遍历右孩子节点，`inorder(root.right)`



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
    LinkedList<TreeNode> list;
    public TreeNode increasingBST(TreeNode root) {
        if (root == null){
            return root;
        }
        list = new LinkedList<TreeNode>();
        inorder(root);
        return list.peekFirst();
    }
    public void inorder(TreeNode root){
        if (root == null){
            return;
        }
        inorder(root.left);
        root.left = null;
        if (!list.isEmpty()){
            list.peekLast().right = root;
        }
        list.offer(root);
        inorder(root.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 053. 二叉搜索树中的中序遍历

#### （1）题目

给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。

节点 p 的后继是值比 p.val 大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点。



#### （2）思路

* 中序遍历，记录遍历到的第一个比目标节点 p 的值大的节点



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
    TreeNode res = null;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        dfs(root, p);
        return res;
    }
    public void dfs(TreeNode root, TreeNode p){
        if (root == null){
            return;
        }
        dfs(root.left, p);
        if (res == null && root.val>p.val){//第一个比 p 节点的值大的节点
            res = root;
            return;
        }
        dfs(root.right, p);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```



### 054. 所有大于等于节点的值之和

#### （1）题目

给定一个二叉搜索树，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。

 

提醒一下，二叉搜索树满足下列约束条件：

* 节点的左子树仅包含键 小于 节点键的节点。
* 节点的右子树仅包含键 大于 节点键的节点。
* 左右子树也必须是二叉搜索树。



#### （2）思路

* 二叉搜索树中**大于节点键的节点为其右子树**
* 根据右中左的顺序遍历二叉搜索树，sum 记录所有遍历节点的值之和
* 算法流程：
  * 递归边界：root == null
  * 递归遍历右子树
  * 更新sum，`sum+=root.val;`
  * 修改当前节点的值，`root.val = sum;`
  * 递归遍历左子树



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
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }
    public void dfs(TreeNode root){
        if (root == null){
            return;
        }
        dfs(root.right);
        sum += root.val;
        root.val = sum;
        dfs(root.left);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 055. 二叉搜索树迭代器

#### （1）题目

实现一个二叉搜索树迭代器类BSTIterator ，表示一个按中序遍历二叉搜索树（BST）的迭代器：

* BSTIterator(TreeNode root) 初始化 BSTIterator 类的一个对象。BST 的根节点 root 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。
* boolean hasNext() 如果向指针右侧遍历存在数字，则返回 true ；否则返回 false 。
* int next()将指针向右移动，然后返回指针处的数字。

注意，指针初始化为一个不存在于 BST 中的数字，所以对 next() 的首次调用将返回 BST 中的最小元素。

可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 的中序遍历中至少存在一个下一个数字。



#### （2）思路

* 中序遍历二叉搜索树，将中序遍历序列存储在数组中



#### （3）实现

```java

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
class BSTIterator {
    ArrayList<Integer> list;
    int index;
    public BSTIterator(TreeNode root) {
        list = new ArrayList<>();
        dfs(root);
        index = -1;
    }
    
    public int next() {
        index++;
        return list.get(index);
    }
    
    public boolean hasNext() {
        return index < list.size()-1;
    }
    public void dfs(TreeNode root){
        if (root == null){
            return ;
        }
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
//leetcode submit region end(Prohibit modification and deletion)
```





### 056. 二叉搜索树中两个节点之和

#### （1）题目

给定一个二叉搜索树的 **根节点** `root` 和一个整数 `k` , 请判断该二叉搜索树中是否存在两个节点它们的值之和等于 `k` 。假设二叉搜索树中节点的值均唯一。



#### （2）思路

* 将二叉搜索树的中序遍历序列存储在数组中
* 双指针寻找数组中是否存在两个元素之和等于 k



#### （3）实现

```java

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
    ArrayList<Integer> list;
    public boolean findTarget(TreeNode root, int k) {
        list = new ArrayList<>();
        dfs(root);
        int left = 0, right = list.size()-1;
        while (left < right){
            int sum = list.get(left)+list.get(right);
            if (sum == k){
                return true;
            }else if (sum < k){
                left++;
            }else{
                right--;
            }
        }
        return false;
    }
    public void dfs(TreeNode root){
        if (root == null){
            return;
        }
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 057. 值和下标之差都在给定范围内

#### （1）题目

给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。

如果存在则返回 true，不存在返回 false。



#### （2）思路

* 滑动窗口 + 有序集合

* 对于序列中每一个元素 x 左侧的至多 k个元素，如果这 k个元素中存在一个元素落在区间 [x - t, x + t] 中，则是一对符合要求的元素

* **维护一个大小为 k的滑动窗口**，每次遍历到元素 x时，滑动窗口中包含元素 x 前面的最多 k个元素，我们检查窗口中是否存在元素落在区间 [x - t, x + t]中即可

* 在有序集合中查找**大于等于 x - t 的最小的元素** y ，如果 y 存在，且 **y <= x+t**，我们就找到了一对符合条件的元素

  



#### （3）实现

```java

import java.util.TreeSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set =  new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long ceil = set.ceiling((long)nums[i]-t);
            if (ceil != null && ceil <= (long)nums[i]+t){
                return true;
            }
            set.add((long)nums[i]);
            if (i>=k){
                set.remove((long)nums[i-k]);
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```







### 058. 日程表

#### （1）题目

请实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内没有其他安排，则可以存储这个新的日程安排。

MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end。

当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订。

每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。

请按照以下步骤调用 MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)



#### （2）思路

* 使用TreeMap 存储 <start, end> 的映射
* 当预定时间段 <start, end> 时：
  * 获取 map 中**在预定时间前最近的一段时间** floorEntry
  * 获取 map 中**在预定时间后最近的一段时间** ceilEntry
  * 若 floorEntry 的结束时间 <= start && end <= ceilEntry 的开始时间，则可以预定，反之不能预定



#### （3）实现

```java

import java.util.Map;
import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class MyCalendar {
    TreeMap<Integer, Integer> map;
    public MyCalendar() {
        map = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Map.Entry<Integer, Integer> floorEntry = map.floorEntry(start);//小于等于 start 且最接近的key的键值对
        Map.Entry<Integer, Integer> ceilEntry = map.ceilingEntry(start);//大于等于 start 且最接近的key的键值对
        int floor = Integer.MIN_VALUE, ceil = Integer.MAX_VALUE;
        if (floorEntry != null){
            floor = floorEntry.getValue();
        }
        if (ceilEntry != null){
            ceil = ceilEntry.getKey();
        }
        if (floor <= start && end <= ceil){
            map.put(start, end);
            return true;
        }
        return false;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
//leetcode submit region end(Prohibit modification and deletion)
```





## 七、堆

### 059. 数据流的第 k 大数值

#### （1）题目

设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。

请实现 KthLargest 类：

* KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
* int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。



#### （2）思路

* 优先队列，**从小到大排序**
* 维护**优先队列中元素个数 = k**，此时队首元素即为第 k 大元素



#### （3）实现

```java

import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class KthLargest {
    PriorityQueue<Integer> queue;
    int k;
    public KthLargest(int k, int[] nums) {
        queue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            queue.add(nums[i]);
        }
        this.k = k;
    }
    
    public int add(int val) {
        queue.add(val);
        while (queue.size() > k){
            queue.poll();
        }
        return queue.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
//leetcode submit region end(Prohibit modification and deletion)


```





### 060. 出现频率最高的 k 个数字

#### （1）题目

给定一个整数数组 `nums` 和一个整数 `k` ，请返回其中出现频率前 `k` 高的元素。可以按 **任意顺序** 返回答案。

 

#### （2）思路

* HashMap<Integer, Integer> 储存元素和其出现频率的映射
* 使用优先队列按照元素在 map 中的映射从大到小的顺序排序



#### （3）实现

```java

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int temp : nums){
            map.put(temp, map.getOrDefault(temp, 0)+1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
                return Integer.compare(map.get(i2), map.get(i1));
            }
        });
        for (int key : map.keySet()){
            queue.add(key);
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





### 061. 和最小的 k 个数对

#### （1）题目

给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。

定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。

请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。



#### （2）思路

* nums1 和 nums2 为升序数组，则和最小的 k 个数对存在于 nums1 和 nums2 的前 k 个数组成的数对中
* 使用大根堆，按照数对和从大到小排序
* 保证优先队列中的数对个数 <= k



#### （3）实现

```java

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        PriorityQueue<List<Integer>> queue = new PriorityQueue<>(new Comparator<List<Integer>>(){
            public int compare(List<Integer> list1, List<Integer> list2){
                int sum1 = list1.get(0)+list1.get(1);
                int sum2 = list2.get(0)+list2.get(1);
                return -Integer.compare(sum1, sum2);
            }
        });
        for (int i = 0; i < k && i < nums1.length; i++) {
            for (int j = 0; j < k && j < nums2.length; j++) {
                List<Integer> list = new ArrayList<>();
                list.add(nums1[i]);
                list.add(nums2[j]);
                queue.add(list);
                if (queue.size() > k){
                    queue.poll();
                }
            }
        }
        return new ArrayList<>(queue);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





## 八、前缀树



### 062. 实现前缀树

#### （1）题目

Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。

请你实现 Trie 类：

Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。



#### （2）思路

* 使用 26 叉树实现前缀树
* TreeNode 的成员变量：
  * boolean isEnd：标识该节点是否是一个单词的结尾
  * TreeNode[] next：长度为 26 的数组表示 26 个孩子节点即字母映射表
*  insert：
  * 从根节点 root 的 26 个子节点开始与 word 的第一个字符匹配，没有对应的节点时开辟新的节点，直到匹配完最后一个字符，同时将最后一个节点 isEnd 置为 true，表示它是一个单词的结尾
* search：
  * 从根节点 root 的子节点开始与 word 的第一个字符匹配，若出现空节点，返回 false；返回最后一个字符的 isEnd
* startsWith：
  * 从根节点 root 的子节点开始与 word 的第一个字符匹配，若出现空节点，返回 false；若匹配结束，返回 true



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
class Trie {
    class TreeNode {
        boolean isEnd;
        TreeNode[] next;//26个子树对应26个字母
        TreeNode(){
            isEnd = false;
            next = new TreeNode[26];
        }
    }
    TreeNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TreeNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i)-'a';//当前字母在字母表中的顺序
            if (node.next[index] == null){
                node.next[index] = new TreeNode();
            }
            node = node.next[index];
        }
        node.isEnd = true;//将该单词的最后一个字母的 isEnd 设为 true
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TreeNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.next[index] == null){
                return false;
            }
            node = node.next[index];
        }
        return node.isEnd;//若最后一个字母是当前的单词的结尾，则返回true，反之返回false
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TreeNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i)-'a';
            if (node.next[index] == null){
                return false;
            }
            node = node.next[index];
        }
        return true;
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





### 063. 替换单词

#### （1）题目

在英语中，有一个叫做 词根(root) 的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。

现在，给定一个由许多词根组成的词典和一个句子，需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。

需要输出替换之后的句子。



#### （2）思路

* 前缀树
* 在前缀树中存储所有的词根 `List<String> dictionary`
* 实现方法 `public String replaceBy(String word)`：返回前缀树中 word 的最短前缀单词，若不存在，返回 word
* 遍历句子中的单词，将其替换为其最短前缀单词



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        String[] words = sentence.split(" ");
        Trie tree = new Trie();
        for (String dict : dictionary){
            tree.insert(dict);
        }
        StringBuilder sb = new StringBuilder();
        for (String word : words){
            String rep = tree.replaceBy(word);
            if (sb.length() != 0){
                sb.append(" ");
            }
            sb.append(rep);
        }
        return sb.toString();
    }
    class Trie{
        class TreeNode{
            boolean isEnd;
            TreeNode[] next;
            TreeNode(){
                isEnd = false;
                next = new TreeNode[26];
            }
        }
        TreeNode root;
        public Trie(){
            root = new TreeNode();
        }
        public void insert(String word){
            TreeNode node = root;
            for (char ch : word.toCharArray()){
                int index = ch-'a';
                if (node.next[index] == null){
                    node.next[index] = new TreeNode();
                }
                node = node.next[index];
            }
            node.isEnd = true;
        }
        public boolean search(String word){
            TreeNode node = root;
            for (char ch : word.toCharArray()){
                int index = ch-'a';
                if (node.next[index] == null){
                    return false;
                }
                node = node.next[index];
            }
            return node.isEnd;
        }
        public String replaceBy(String word){
            TreeNode node = root;
            for (int i = 0; i < word.length(); i++){
                int index = word.charAt(i)-'a';
                if (node.next[index]==null){//不存在前缀单词
                    return word;
                }
                if (node.next[index].isEnd){//最短的前缀单词
                    return word.substring(0, i+1);
                }
                node = node.next[index];
            }
            return word;
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 064. 神奇的字典

#### （1）题目

设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于已构建的神奇字典中。

实现 MagicDictionary 类：

MagicDictionary() 初始化对象
void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。



#### （2）思路

* HashMap<Integer, HashSet<String>> 存储相同长度的字符串
* 暴力比较



#### （3）实现

```java

import java.util.HashMap;
import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class MagicDictionary {
    HashMap<Integer, HashSet<String>> map;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        map = new HashMap<>();
    }
    
    public void buildDict(String[] dictionary) {
        for (String str : dictionary){
            int len = str.length();
            HashSet<String> set = map.getOrDefault(len, new HashSet<>());
            set.add(str);
            map.put(len, set);
        }
    }
    
    public boolean search(String searchWord) {
        int len = searchWord.length();
        if (!map.containsKey(len)){
            return false;
        }
        HashSet<String> set = map.get(len);
        char[] words = searchWord.toCharArray();
        for (String str : set){
            int diff = 0;
            char[] strs = str.toCharArray();
            for (int i = 0; i < strs.length; i++) {
                if (words[i] != strs[i]){
                    diff++;
                }
                if (diff > 1){
                    break;
                }
            }
            if (diff == 1){
                return true;
            }
        }
        return false;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
//leetcode submit region end(Prohibit modification and deletion)
```





### 065. 最短的单词编码

#### （1）题目

单词数组 words 的 有效编码 由任意助记字符串 s 和下标数组 indices 组成，且满足：

* words.length == indices.length
* 助记字符串 s 以 '#' 字符结尾
* 对于每个下标 indices[i] ，s 的一个从 indices[i] 开始、到下一个 '#' 字符结束（但不包括 '#'）的 子字符串 恰好与 words[i] 相等

给定一个单词数组 words ，返回成功对 words 进行编码的最小助记字符串 s 的长度 。



#### （2）思路

* 字典树

* length 记录最小助记字符串 s 的长度

* **按长度递减对单词排序**，遍历 words，对于任意单词 word

  * 若字典树中**不存在以 word 为后缀**的单词，则需要将 "word#" 添加到 s 中，**length += word.length**

  * 若字典树中**存在以 word 为后缀**的单词，则 word 已被辅助记录，不需要添加到 s 中

  * 建树时，将单词**逆序插入**

    

#### （3）实现

```java

import java.util.Arrays;
import java.util.Comparator;


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minimumLengthEncoding(String[] words) {
        //字符串按长度降序排序
        Arrays.sort(words, new Comparator<String>(){
            @Override
            public int compare(String str1,String str2){
                return str2.length()-str1.length();
            }
        });
        Trie tree = new Trie();
        int res = 0;
        for (String str : words){
            if (!tree.endWith(str)){//是否存在以 str 为后缀的单词
                res += str.length()+1;
            }
            tree.insert(str);//将 str 插入字典树
        }
        return res;
    }
    class Trie {
        class TreeNode{
            boolean isEnd;
            TreeNode[] next;
            TreeNode(){
                isEnd = false;
                next = new TreeNode[26];
            }
        }
        TreeNode root;
        public Trie(){
            root = new TreeNode();
        }
        public void insert(String word){
            TreeNode node = root;
            int len = word.length();
            for (int i = 0; i < len; i++) {//倒序插入单词
                int index = word.charAt(len-1-i)-'a';
                if (node.next[index] == null){
                    node.next[index] = new TreeNode();
                }
                node = node.next[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word){
            TreeNode node = root;
            int len = word.length();
            for (int i = 0; i < len; i++) {
                int index = word.charAt(len-1-i)-'a';
                if (node.next[index] == null){
                    return false;
                }
                node = node.next[index];
            }
            return node.isEnd;
        }

        public boolean endWith(String prefix){//是否存在以 prefix 为后缀的单词
            TreeNode node = root;
            int len = prefix.length();
            for (int i = 0; i < len; i++) {
                int index = prefix.charAt(len-1-i)-'a';
                if (node.next[index] == null){
                    return false;
                }
                node = node.next[index];
            }
            return true;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 066. 单词之和

#### （1）题目

实现一个 MapSum 类，支持两个方法，insert 和 sum：

* MapSum() 初始化 MapSum 对象
* void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
* int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。



#### （2）思路

* 前缀树
* 使用前缀树节点中 isEnd 代表该 key 对应的 val 值
*  int sum(string prefix)：遍历字符串 prefix 对应节点的所有子节点，返回所有叶节点的 val 之和



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
class MapSum {
    class TreeNode{
        int val;// 0 代表当前节点不是 key 单词的结尾，若不为 0 ，则代表 val
        TreeNode[] next;
        TreeNode(){
            val = 0;
            next = new TreeNode[26];
        }
    }
    TreeNode root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new TreeNode();
    }
    
    public void insert(String key, int val) {
        TreeNode node = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i)-'a';
            if (node.next[index] == null){
                node.next[index] = new TreeNode();
            }
            node = node.next[index];
        }
        node.val = val;
    }
    
    public int sum(String prefix) {
        TreeNode node = root;
        int res = 0;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i)-'a';
            if (node.next[index] == null){
                return 0;
            }
            node = node.next[index];
        }
        return getSum(node);
    }

    public int getSum(TreeNode node){
        if (node == null){
            return 0;
        }
        int res = node.val;
        for (int i = 0; i < 26; i++) {
            res += getSum(node.next[i]);
        }
        return res;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
```





### 067. 最大的异或

#### （1）题目

给定一个整数数组 `nums` ，返回 `nums[i] XOR nums[j]` 的最大运算结果，其中 `0 ≤ i ≤ j < n` 。



#### （2）思路

* 将每个数的**二进制表达式从高位到低位，共 31 位（首位为符号位，不计算）**存储在字典树中
* 对于每个数，在字典树中查询其最大的异或
  * 遍历其二进制表达式的后 31 位
    * 当前位为 0，访问**当前节点的 next[1]**，若不存在，则访问 **next[0]**
    * 当前位为 1，访问**当前节点的 next[0]**，若不存在，则访问 **next[1]**



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMaximumXOR(int[] nums) {
        Trie tree = new Trie();
        int max = Integer.MIN_VALUE;
        for (int num : nums){
            tree.insert(num);
            max = Math.max(max, tree.searchMaxXOR(num));
        }
        return max;
    }

    class Trie{
        class TreeNode{
            TreeNode[] next;
            public TreeNode(){
                next = new TreeNode[2];
            }
        }
        TreeNode root;
        Trie(){
            root = new TreeNode();
        }
        public void insert(int num){
            TreeNode node = root;
            for (int i = 30; i >= 0; i--){
                int d = num >> i & 1;
                if (node.next[d] == null){
                    node.next[d] = new TreeNode();
                }
                node = node.next[d];
            }
        }

        public int searchMaxXOR(int num){
            TreeNode node = root;
            int xorNum = 0;
            for (int i = 30; i >= 0; i--){
                int d = num >> i & 1;
                int other = (d == 1 ? 0 : 1);
                if (node.next[other] == null){//相反位为空
                    node = node.next[d];
                    xorNum = (xorNum << 1) + d;
                }else{
                    node = node.next[other];
                    xorNum = (xorNum << 1) + other;
                }
            }
            return xorNum ^ num;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







## 九、二分查找



### 068. 查找插入位置(imp)

#### （1）题目

给定一个排序的整数数组 nums 和一个整数目标值 target ，请在数组中找到 target ，并返回其下标。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

请必须使用时间复杂度为 O(log n) 的算法。



#### （2）思路

* 二分查找**第一个比 target 大或等于的元素的下标**，即 **nums[pos-1] < target <= nums[pos]**
* 初始化：
  * 元素的插入位置可能在数组的尾部，`left = 0, right = nums.length;`
  * 跳出循环条件：`left < right`
  * nums[mid] < target：**mid 及 mid 左边的所有元素不是插入元素的位置**
    * `left = mid + 1;`
  * nums[mid] >= target：**mid 右边的所有元素不是插入元素的位置**
    * `right = mid;`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right){
            int mid = left + (right-left)/2;
            if (nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```



#### （4）模板

```java
// 查找第一个值等于给定值的元素
private int firstEquals(int[] arr, int target) {
    int l = 0, r = arr.length - 1;
    while (l < r) {
        int mid = l + ((r - l) >> 1);
        if (arr[mid] < target) l = mid + 1;
        else r = mid; // 收缩右边界不影响 first equals
    }
    if (arr[l] == target && (l == 0 || arr[l - 1] < target)) return l;
    return -1;
}
```



```java
// 查找最后一个值等于给定值的元素
private int lastEquals(int[] arr, int target) {
    int l = 0, r = arr.length - 1;
    while (l < r) {
        int mid = l + ((r - l + 1) >> 1);
        if (arr[mid] > target) r = mid - 1;
        else l = mid; // 收缩左边界不影响 last equals
    }
    if (arr[l] == target && (l == arr.length - 1 || arr[l + 1] > target)) return l;
    return -1;
}
```



```java
// 查找第一个大于等于给定值的元素
private int firstLargeOrEquals(int[] arr, int target) {
    int l = 0, r = arr.length - 1;
    while (l < r) {
        int mid = l + ((r - l) >> 1);
        if (arr[mid] < target) l = mid + 1;
        else r = mid; // 收缩右边界不影响 first equals
    }
    if (arr[l] >= target && (l == 0 || arr[l - 1] < target)) return l; // >=
    return -1;
}
```





```java
// 查找最后一个小于等于给定值的元素
private int lastLessOrEquals(int[] arr, int target) {
    int l = 0, r = arr.length - 1;
    while (l < r) {
        int mid = l + ((r - l + 1) >> 1);
        if (arr[mid] > target) r = mid - 1;
        else l = mid; // 收缩左边界不影响 last equals
    }
    if (arr[l] <= target && (l == arr.length - 1 || arr[l + 1] > target)) return l; // <=
    return -1;
}
```





### 069. 山峰数组的顶部

#### （1）题目

符合下列属性的数组 arr 称为 山峰数组（山脉数组） ：

* arr.length >= 3
* 存在 i（0 < i < arr.length - 1）使得：
  * arr[0] < arr[1] < ... arr[i-1] < arr[i]
  * arr[i] > arr[i+1] > ... > arr[arr.length - 1]

给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i ，即山峰顶部。



#### （2）思路

* 峰顶元素**左侧满足 arr[i-1] < arr[i] **性质，**右侧不满足**
* 根据 arr[i-1] < arr[i] **在 [1~n-1] 范围内**找值
  * 初始化：`left = 1, right = n-1;`
  * arr[mid-1] < arr[mid]：mid 左侧满足要求，峰顶在 mid 及右侧
    * `left = mid`
  * arr[mid-1] > arr[mid]：mid 右侧满足要求，峰顶在 mid 左侧
    * `right = mid-1`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int left = 1, right = arr.length - 1;
        while (left<right){
            int mid = left + (right-left+1)/2;
            if (arr[mid-1] < arr[mid]){
                left = mid;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```




### 070. 排序数组中只出现一次的数字

#### （1）题目

给定一个只包含整数的有序数组 `nums` ，每个元素都会出现两次，唯有一个数只会出现一次，请找出这个唯一的数字。



#### （2）思路

* 二分法
  * mid 为奇数，则 mid 之前共有奇数个元素，比较 mid 与 mid-1
    * nums[mid-1] == nums[mid]：[0, mid] 满足要求，唯一的数字出现在 mid 右侧
      * `left = mid+1;`
    * nums[mid-1] != nums[mid]：[0, mid-1] 不满足要求，唯一的数字出现在 mid 左侧
      * `right = mid-1;`
  * mid 为偶数，则 mid 之前共有偶数个元素，比较 mid 与 mid+1
    * nums[mid+1] == nums[mid]：[0, mid-1] 满足要求，唯一的数字出现在 mid+1 右侧
      * `left = mid+2;`
    * nums[mid+1] != nums[mid]：[0, mid] 不满足要求，唯一的数字出现在 mid 及其左侧
      * `right = mid;`



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int left = 0, right = nums.length - 1;
        while (left < right){
            int mid = left + (right-left)/2;
            if (mid%2 == 1){//下标为奇数，与前一个元素比较
                if (nums[mid] == nums[mid-1]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }else{//下标为偶数，与后一个元素比较
                if (nums[mid] == nums[mid+1]){
                    left = mid + 2;
                }else{
                    right = mid;
                }
            }
        }
        return nums[left];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

```



### 071. 按权重生成随机数

#### （1）题目

给定一个正整数数组 w ，其中 w[i] 代表下标 i 的权重（下标从 0 开始），请写一个函数 pickIndex ，它可以随机地获取下标 i，选取下标 i 的概率与 w[i] 成正比。

例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。

也就是说，选取下标 i 的概率为 w[i] / sum(w) 。



#### （2）思路

* 设数组 ww 的权重之和为 \textit{total}total。根据题目的要求，我们可以看成将 [1, \textit{total}][1,total] 范围内的所有整数分成 nn 个部分（其中 nn 是数组 ww 的长度），第 ii 个部分恰好包含 w[i]w[i] 个整数，并且这 nn 个部分两两的交集为空。随后我们在 [1, \textit{total}][1,total] 范围内随机选择一个整数 xx，如果整数 xx 被包含在第 ii 个部分内，我们就返回 ii。

* 使用 TreeMap 建立映射



#### （3）实现

```java
import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    Random random;
    TreeMap<Integer, Integer> map;
    int sum;
    public Solution(int[] w) {
        random = new Random();
        map = new TreeMap<>();
        for (int i = 0; i < w.length; i++) {
            map.put(sum, i);
            sum += w[i];
        }
    }
    
    public int pickIndex() {
        int next = random.nextInt(sum);
        int key = map.floorKey(next);
        return map.get(key);
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
//leetcode submit region end(Prohibit modification and deletion)
```





### 072. 求平方根

#### （1）题目

给定一个非负整数 x ，计算并返回 x 的平方根，即实现 int sqrt(int x) 函数。

正数的平方根有两个，只输出其中的正数平方根。

如果平方根不是整数，输出只保留整数的部分，小数部分将被舍去。



#### （2）思路

* 二分法查询平方根



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int mySqrt(int x) {
        int left = 0, right = x;
        while (left < right){
            long mid = ((long)left+right+1)/2;

            long product = mid * mid;
            if (product == x){
                return (int)mid;
            }else if (product < x){
                left = (int)mid;
            }else{
                right = (int)mid-1;
            }
        }
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```



### 073. 狒狒吃香蕉

#### （1）题目

狒狒喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。

狒狒可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉，下一个小时才会开始吃另一堆的香蕉。  

狒狒喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。

返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。



#### （2）思路

* 二分法查找满足要求的最小速度
* piles 数组的元素和为 sum，最大值为max；二分查找的**下界left为 sum/h**，即每一堆香蕉都能刚好被吃完，**上界right为 max**
* 循环条件：**left < right**
* 计算以 **mid = left+(right-left)/2** 速度吃完香蕉的耗时 spend
  * spend <= h：当前速度 mid 满足要求，找到满足要求的最小速度，**舍弃 mid 右侧 [mid+1, right] 区间**
    * `right = mid;`
  * spend > h：当前速度 mid 过慢，不满足要求，**舍弃 mid 及 mid 左侧 [left, mid] 区间**
    * `left = mid+1;`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int sum = 0, max = 0;
        for (int pile : piles){
            sum += pile;
            max = Math.max(max, pile);
        }
        int left = sum/h, right = max;
        while (left < right){
            int mid = left + (right-left)/2;
            int spend = 0;
            for (int pile : piles){
                spend += Math.ceil((double)pile/mid);
            }
            if (spend > h){//速度过慢
                left = mid+1;
            }else{//速度刚好或过快
                right = mid;
            }
        }
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





 

## 十、排序



### 074. 合并区间

#### （1）题目

以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。



#### （2）思路

* 对 intervals 数组按 **intervals[i] [0]** 自然序排序
* 依次将每个区间合并到数组中
  * 若当前区间的左端点 > 数组中最后一个区间的右端点，则不会重合，将该区间插入数组
  * 否则，合并两个区间，左端点为数组中最后一个区间的左端点，右端点为两个区间右端点的较大值



#### （3）实现

```java
import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                if (ints[0] != t1[0]){
                    return ints[0]-t1[0];
                }
                return ints[1]-t1[1];
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int[] interval : intervals){
            if (list.size() == 0){
                list.add(interval);
            }else{
                int[] temp = list.get(list.size()-1);
                if (temp[1] < interval[0]){
                    list.add(interval);
                }else{
                    list.remove(list.size()-1);
                    temp[1] = Math.max(temp[1], interval[1]);
                    list.add(temp);
                }
            }
        }

        return list.toArray(new int[list.size()][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







### 075. 数组相对排序

#### （1）题目

给定两个数组，arr1 和 arr2，

* arr2 中的元素各不相同
* arr2 中的每个元素都出现在 arr1 中

对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。



#### （2）思路

* 建立 **arr2数组元素和下标**之间的映射 HashMap<Integer, Integer> map
* 对 arr1 中元素 i, j 进行比较时：
  * 若 i 和 j 均存在于 arr2 中，比较其下标值
  * 若 map 中存在 i 的映射，不存在 j 的映射，则 j 大
  * 若 map 中不存在 i 的映射，存在 j 的映射，则 i 大
  * 若均不存在，则比较 i 和 j 的大小



#### （3）实现

```java

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }
        Integer[] temp = new Integer[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            temp[i] = arr1[i];
        }
        Arrays.sort(temp, new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
                if (map.containsKey(i1)&&map.containsKey(i2)){
                    return map.get(i1)-map.get(i2);
                }
                if (map.containsKey(i1)){
                    return -1;
                }
                if (map.containsKey(i2)){
                    return 1;
                }
                return i1-i2;
            }
        });
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = temp[i];
        }
        return arr1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 076. 数组中的第 k 大的数字

#### （1）题目

给定整数数组 `nums` 和整数 `k`，请返回数组中第 `**k**` 个最大的元素。

请注意，你需要找的是数组排序后的第 `k` 个最大的元素，而不是第 `k` 个不同的元素。



#### （2）思路

* 堆排序
* 使用 PriorityQueue<Integer> 对数组元素按自然序降序排序



#### （3）实现

```java
import java.util.Comparator;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
                return i2-i1;
            }
        });
        for (int num : nums){
            queue.offer(num);
        }
        for (int i = 0; i < k - 1; i++) {
            queue.poll();
        }
        return queue.peek();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 077. 链表排序

#### （1）题目

给定链表的头结点 `head` ，请将其按 **升序** 排列并返回 **排序后的链表** 。



#### （2）思路

* **自顶向下归并排序**
* **快慢指针**找到**链表的中点**，将链表拆分为**两个子链表**
* 递归对两个子链表分别排序
* 将两个排序后的子链表合并，得到排序后的链表



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
    public ListNode sortList(ListNode head) {
        return sortList(head, null);
    }
    public ListNode sortList(ListNode head, ListNode tail){
        if (head == null){//该子链 0 个节点
            return head;
        }
        if (head.next == tail){//该子链 1 个节点
            head.next = null;
            return head;
        }
        //快慢指针找中点
        ListNode slow = head, fast = head;
        while (fast != tail){
            slow = slow.next;
            fast = fast.next;
            if (fast != tail){
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        //递归对两个子链表排序
        ListNode node1 = sortList(head, mid);
        ListNode node2 = sortList(mid, tail);
        //两个子链表合并
        ListNode node = merge(node1, node2);
        return node;
    }

    public ListNode merge(ListNode node1, ListNode node2){
        ListNode vhead = new ListNode();
        ListNode node = vhead;
        while (node1 != null && node2 != null){
            if (node1.val < node2.val){
                node.next = node1;
                node1 = node1.next;
                node = node.next;
            }else{
                node.next = node2;
                node2 = node2.next;
                node = node.next;
            }
        }
        if (node1 != null){
            node.next = node1;
        }
        if (node2 != null){
            node.next = node2;
        }
        return vhead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 078. 合并排序链表

#### （1）题目

给定一个链表数组，每个链表都已经按升序排列。

请将所有链表合并到一个升序链表中，返回合并后的链表。



#### （2）思路

* 对数组中链表**两两合并**，直到只剩一个**链表**



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)


import java.util.LinkedList;


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
    public ListNode mergeKLists(ListNode[] lists) {
        LinkedList<ListNode> list = new LinkedList<>();
        for (ListNode node : lists){
            list.add(node);
        }
        if (list.size() == 0){
            return null;
        }
        LinkedList<ListNode> temp = new LinkedList<>();
        while (list.size() != 1){
            while (list.size() > 1){
                temp.offer(merge(list.poll(), list.poll()));
            }
            if (!list.isEmpty()){
                temp.offer(list.poll());
            }
            list = new LinkedList<>(temp);
            temp.clear();
        }
        return list.peek();
    }
    public ListNode merge(ListNode node1, ListNode node2){
        ListNode vhead = new ListNode();
        ListNode node = vhead;
        while (node1 != null && node2 != null){
            if (node1.val < node2.val){
                node.next = node1;
                node1 = node1.next;
            }else{
                node.next = node2;
                node2 = node2.next;
            }
            node = node.next;
        }
        if (node1 != null){
            node.next = node1;
        }
        if (node2 != null){
            node.next = node2;
        }
        return vhead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





## 十一、回溯法



### 079. 所有子集

#### （1）题目

给定一个整数数组 `nums` ，数组中的元素 **互不相同** 。返回该数组所有可能的子集（幂集）。

解集 **不能** 包含重复的子集。你可以按 **任意顺序** 返回解集。



#### （2）思路

* 回溯



#### （3）实现

```java

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res;
    List<Integer> temp;
    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        temp = new ArrayList<>();
        res.add(new ArrayList<>(temp));
        for (int i = 0; i < nums.length; i++) {
            dfs(nums, i);
        }
        return res;
    }
    public void dfs(int[] nums, int index){
        if (index == nums.length){
            return;
        }
        temp.add(nums[index]);
        res.add(new ArrayList<>(temp));
        for (int i = index+1; i < nums.length; i++) {
            dfs(nums, i);
        }
        temp.remove(temp.size()-1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 080. 含有 k 个元素的组合

#### （1）题目

给定两个整数 `n` 和 `k`，返回 `1 ... n` 中所有可能的 `k` 个数的组合。



#### （2）思路

* 回溯
* `public void dfs(int n, int k, int index)`
  * n：整数上界 n
  * k：集合中**缺少的元素个数 k**
  * index：当前递归的起始元素



#### （3）实现

```java

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res;
    List<Integer> temp;
    public List<List<Integer>> combine(int n, int k) {
        res = new ArrayList<>();
        temp = new ArrayList<>();
        dfs(n, k, 1);
        return res;
    }

    public void dfs(int n, int k, int index){
        if (k == 0){
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i <= n; i++) {
            temp.add(i);
            dfs(n, k-1, i+1);
            temp.remove(temp.size()-1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







## 十二、动态规划



### 088. 爬楼梯的最少成本

#### （1）题目

数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）。

每当爬上一个阶梯都要花费对应的体力值，一旦支付了相应的体力值，就可以选择向上爬一个阶梯或者爬两个阶梯。

请找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯。



#### （2）思路

* dp[i] 记录爬到第 i 层台阶的最少成本
* **dp.length = cost.length+1**，dp[length] 表示爬完阶梯，即爬到第 length 个阶梯的最少成本
* 初始化：
  * 可以爬一个或两个阶梯，因此爬到第 0 层和第 1 层的最少成本均为 0
  * `dp[0] = 0; dp[1] = 0;`
* 动态转移方程：
  * 可以选择从第 i-1 层或第 i-2 层阶梯爬到第 i 层阶梯
  * `dp[i] = Math.min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2]);`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int length = cost.length;
        int[] dp = new int[length+1];
        dp[0] = 0;dp[1] = 0;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.min(dp[i-1]+cost[i-1], dp[i-2]+cost[i-2]);
        }
        return dp[length];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 089. 房屋偷盗

#### （1）题目

一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响小偷偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。



#### （2）思路

* dp[i] 记录**前 i 个房间在满足条件下能偷窃到的最高金额**
* 状态转移：对于第 n 个房间
  * 不抢第 n 个房间，则等于前 n-1 个房间的最高金额，**dp[n] = dp[n-1]**
  * 抢第 n 个房间，此时不能抢第 n-1 个房间，等于前 n-2 个房间的最高金额+当前房间的价值，**dp[n] = dp[n-2]+num**
    * 前 n-1 个房间的最高金额不一定偷窃了第 n-1 个房间，则此时可以偷窃第 n 个房间
      * 假设第 n-1 个房间没有被偷，则**dp[n] = dp[n-1]+num = dp[n-2]+num，合并考虑**
  * **dp[n] = Math.max(dp[n-1], dp[n-2]+num)**



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int rob(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length+1];
        dp[0] = 0; dp[1] = nums[0];//初始化
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
        }
        return dp[length];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 090. 环形房屋偷盗

#### （1）题目

一个专业的小偷，计划偷窃一个环形街道上沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。

给定一个代表每个房屋存放金额的非负整数数组 nums ，请计算 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。



#### （2）思路

* 思路与 89 相同，需要考虑两种情况
  * 偷窃第 1 间房屋，此时不能偷窃最后一间房屋
  * 不偷窃第一间房屋，此时可以偷窃最后一间房屋
* 记录两种情况下能偷窃到的最高金额



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int rob(int[] nums) {
        int length = nums.length;
        int[][] dp = new int[length+1][2];//dp[0]记录未偷窃第 1 间房屋，dp[1]记录偷窃了第 1 间房屋
        dp[0][0] = 0; dp[1][0] = 0;
        dp[0][1] = 0; dp[1][1] = nums[0];
        for (int i = 2; i < length+1; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-2][0]+nums[i-1]);
            if (i != length){
                dp[i][1] = Math.max(dp[i-1][1], dp[i-2][1]+nums[i-1]);
            }else{
                dp[i][1] = Math.max(dp[i-1][1], dp[i-2][1]);
            }
        }
        return Math.max(dp[length][0], dp[length][1]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







### 091. 粉刷房子

#### （1）题目

假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。

当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的正整数矩阵 costs 来表示的。

例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。

请计算出粉刷完所有房子最少的花费成本。



#### （2）思路

* 数组 int[] dp 分别记录粉刷到当前房子，分别粉刷为红色、蓝色和绿色所需最少的成本
* 状态转移方程：
  * dp[0] = Math.min(dp[1], dp[2]) + cost(红色)
  * dp[1] = Math.min(dp[0], dp[2]) + cost(蓝色)
  * dp[2] = Math.min(dp[0], dp[1]) + cost(绿色)
* 最终返回 dp 中的最小值即可



#### （3）实现

```java

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minCost(int[][] costs) {
        int length = costs.length;
        int[] dp = new int[3];
        dp[0] = costs[0][0]; dp[1] = costs[0][1]; dp[2] = costs[0][2];
        for (int i = 1; i < length; i++) {
            int[] temp = new int[3];
            temp[0] = Math.min(dp[1], dp[2])+costs[i][0];
            temp[1] = Math.min(dp[0], dp[2])+costs[i][1];
            temp[2] = Math.min(dp[0], dp[1])+costs[i][2];
            dp = Arrays.copyOf(temp, 3);
        }
        return Math.min(Math.min(dp[0], dp[1]), dp[2]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```







### 092. 翻转字符

#### （1）题目

如果一个由 '0' 和 '1' 组成的字符串，是以一些 '0'（可能没有 '0'）后面跟着一些 '1'（也可能没有 '1'）的形式组成的，那么该字符串是 单调递增 的。

我们给出一个由字符 '0' 和 '1' 组成的字符串 s，我们可以将任何 '0' 翻转为 '1' 或者将 '1' 翻转为 '0'。

返回使 s 单调递增 的最小翻转次数。



#### （2）思路

* dp[i] 记录**使字符串中 [0, i] 子串单调递增的最小翻转次数**，one 记录字符串中 '1' 的个数

* 当前字符为 '0'：

  * '0' 不翻转，若要使 [0, i] 子串单调递增，则需要**将 [0, i] 子串中的 '1' 翻转为 '0'**，`dp[i] = one;`
  * '0' 翻转为 '1'，若要使 [0, i] 子串单调递增，则只需要**保证 [0, i-1] 子串单调递增** ，`dp[i] = dp[i-1] + 1;`

  * 状态转移方程：`dp[i] = Math.max(one, dp[i-1]+1);`

* 当前字符为 '1'：

  * '1' 翻转为 '0'，若要使 [0, i] 子串单调递增，则需要**将 [0, i] 子串中的 '1' 翻转为'0'**，`dp[i] = one+1;`
  * '1' 不翻转，若要使 [0, i] 子串单调递增，则只需要**保证 [0, i-1] 子串单调递增**，`dp[i] = dp[i-1];`
  * 状态转移方程：`dp[i] = Math.max(one+1, dp[i-1]);`



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minFlipsMonoIncr(String s) {
        int length = s.length();
        int[] dp = new int[length];
        dp[0] = 0;
        char[] chs = s.toCharArray();
        int one = chs[0] == '1' ? 1 : 0;//记录 1 的个数
        for (int i = 1; i < length; i++){
            if (chs[i] == '0'){
                //当前为 '0' 时，保持 '0'：需将之前所有 '1' 转为 '0'；转为 '1'：只需前序序列保持非递减即可
                dp[i] = Math.min(one, dp[i-1]+1);
            }else{
                //当前为 '1' 时，转为 '0'：需将之前所有 '1' 转为 '0'；保持 '1'：只需前序序列保持非递减即可
                dp[i] = Math.min(one+1, dp[i-1]);
                one++;
            }
        }
        return dp[length-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 093. 最长斐波那契数列

#### （1）题目

如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：

n >= 3
对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。

（回想一下，子序列是从原序列  arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）



#### （2.1）思路

* 使用 set 暴力
* 使用 set 可以快速确定下一项是否存在数组中
* 对于每一个起始 A<sub>[i]</sub> 和 A<sub>[j]</sub> ，令 x = A<sub>[i]</sub> ，y = A<sub>[j]</sub> ，若 x+y 存在于 set 中，则将 (x, y) 更新为 (y, x+y)，并更新当前斐波那契数列长度



#### （3.1）实现

```java
import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int temp : arr) set.add(temp);
        int max = 0, length = arr.length;
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length; j++) {
                int templength = 2;
                int x = arr[i], y = arr[j];
                while (set.contains(x+y)){
                    int temp = x+y;
                    x = y;
                    y = temp;
                    templength++;
                    max = Math.max(max, templength);
                }
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





#### （2.2）思路

* 斐波那契数列可以**由其最后两个元素精准定位**
* dp[i] [j] 表示**以 arr[i] 和 arr[j] 为最后两元素的斐波那契数列的长度**
* 初始化：
  * 将 dp[] [] 的元素初始化为 2
  * 使用 HashMap 记录 arr 数组中**元素—下标**的映射
* 动态转移方程：
  * 若存在 **arr[k] + arr[i] = arr[j] 且 arr[k] < arr[i] **
  * 则 **dp[i] [j] = max(dp[i] [j], dp[k] [i]+1)**

* 记录最大的 dp[i] [j]



#### （3.2）实现

```java

import java.util.Arrays;
import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();//建立元素和下标的映射
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            map.put(arr[i], i);
        }
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {//初始化为 2
            Arrays.fill(dp[i], 2);
        }
        int max = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j < length; j++) {//保证 i < j
                int arr1 = arr[i], arr2 = arr[j], arr0 = arr2-arr1;
                if (arr0 < arr1 && map.containsKey(arr0)){
                    int index0 = map.get(arr0);
                    dp[i][j] = Math.max(dp[index0][i]+1, dp[i][j]);
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 095. 最长公共子序列

#### （1）题目

给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。

一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

* 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。

两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。



#### （2）思路

* 状态定义：dp[i] [j] 表示 **text1 [0 : i-1] 和 text2 [0 : j-1] 的最长公共子序列长度**
  * 如此定义为了方便当 i=0 或者 j=0 时，dp[i] [j] 表示为空字符串和另外一个字符串的匹配，此时 dp[i] [j] 可初始化为 0
* 状态转移方程：
  * text1[i-1] == text2[j-1] 时，两个子字符串的最后一位相等，最长公共子序列增加  1
    * `dp[i][j] = dp[i-1][j-1]+1;`
  * text1[i-1] != text2[j-1] 时，两个子字符串的最后一位不相等，取**不使用 text1[i-1]** 形成最长公共子序列的长度和**不使用 text2[j-1]** 形成最长公共子序列的长度中的**最大值**
    * `dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length(), len2 = text2.length();
        int[][] dp = new int[len1+1][len2+1];
        char[] ch1 = text1.toCharArray(), ch2 = text2.toCharArray();
        for (int i = 1; i < len1+1; i++) {
            for (int j = 1; j < len2+1; j++) {
                if (ch1[i-1] == ch2[j-1]){
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[len1][len2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 096. 字符串交织

#### （1）题目

给定三个字符串 s1、s2、s3，请判断 s3 能不能由 s1 和 s2 交织（交错） 组成。

两个字符串 s 和 t 交织 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：

* s = s1 + s2 + ... + sn
* t = t1 + t2 + ... + tm
* |n - m| <= 1
* 交织 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...

提示：a + b 意味着字符串 a 和 b 连接。



#### （2）思路

* 状态定义：dp[i] [j] 表示 **s1 的 [0,i-1]** 与 **s2 的 [0, j-1]**子串能否交织组成 **s3 的 [0, i+j-1]**
* 初始化：dp[0] [0] = true
* 状态转移方程：
  * **i>0 && s1.charAt(i-1) == s3.charAt(i+j-1)**：`dp[i][j] = dp[i][j] || dp[i-1][j];`
  * **j>0 && s2.charAt(j-1) == s3.charAt(i+j-1)**：`dp[i][j] = dp[i][j] || dp[i][j-1];`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()){
            return false;
        }
        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i>0){
                    dp[i][j] = dp[i][j] || (dp[i-1][j]&& s1.charAt(i-1) == s3.charAt(i+j-1));
                }
                if (j>0){
                    dp[i][j] = dp[i][j] || (dp[i][j-1]&& s2.charAt(j-1) == s3.charAt(i+j-1));
                }
            }
        }
        return dp[m][n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 098. 路径的数目

#### （1）题目

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？



#### （2）思路

* 动态规划
* 每一网格可以从**左侧或上侧**移动到达
* 状态定义：dp[i] [j] 表示从网格的左上角到达 [i, j] 的路径数目
* 状态转移方程：
  * `dp[i][j]=dp[i-1][j]+dp[i][j-1];` **( 0<i<m&&0<j<n )**
  * i = 0 || j = 0 时需特殊考虑



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //边界
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 099. 最小路径之和

#### （1）题目

给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

说明：一个机器人每次只能向下或者向右移动一步。



#### （2）思路

* 状态定义：dp[i] [j] 表示**从左上角到 [i, j] 的最小路径之和**
* 状态转移方程：可以**从左侧或上侧**移动到达
  * `dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1])+grid[i][j];` **( 0 < i < m && 0 < j < n)**
  * **i = 0 || j = 0** 需特殊考虑



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        int start = 0;
        for (int i = 0; i < m; i++) {
            start += grid[i][0];
            dp[i][0] = start;
        }
        start = 0;
        for (int i = 0; i < n; i++) {
            start += grid[0][i];
            dp[0][i] = start;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1])+grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 100. 三角形中最小路径之和

#### （1）题目

给定一个三角形 triangle ，找出自顶向下的最小路径和。

每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。



#### （2）思路

* 状态定义：dp[i] 表示到达该层下标为 i 的节点的最小路径和
* 状态转移方程：下一层第 i 个节点
  * `min = Math.min(dp[i], dp[i-1])+value;`
  * 每一层的**第一个节点和最后一个节点**需特殊考虑
* 返回代表最后一层节点 dp 数组中最小值



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canPartition(int[] nums) {
        int length = nums.length;
        int target = 0;
        for (int temp : nums){
            target += temp;
        }
        if (target % 2 == 1){//奇数，不满足条件
            return false;
        }
        target /= 2;
        boolean[][] dp = new boolean[length+1][target+1];
        dp[0][0] = true;
        for (int i = 1; i < length+1; i++) {
            for (int j = 0; j < target+1; j++) {
                dp[i][j] = dp[i-1][j] || ((j-nums[i-1])>=0&&dp[i-1][j-nums[i-1]]);
            }
        }
        return dp[length][target];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 101. 分割等和子集

#### （1）题目

给定一个非空的正整数数组 `nums` ，请判断能否将这些数字分成元素和相等的两部分。



#### （2）思路

* 0-1背包问题，即是否能选取元素，使其和为元素总和的一半
* 状态定义：dp[i] [j] 表示**从数组的 [0, i] 这个子区间内挑选一些正整数，每个数只能用一次，使得这些数的和恰好等于 j**
* 状态转移方程：对于 dp[i] [j] 来说
  * 不选择 nums[i]，`dp[i-1] [j] = true`时，表示 [0, i-1] 子区间内存在一部分元素，它们的和为 j，此时 `dp[i] [j] = true`
  * 选择 nums[i]，若 [0, i-1] 子区间内存在一部分元素，它们的和为 **j-nums[i]**，即 `dp[i-1] [j-nums[i]]=true`时，`dp[i] [j] = true`
  * 即 `dp[i][j] = dp[i-1][j] || ((j-nums[i])>=0 && dp[i-1][j-nums[i]]);`
* 初始化：`dp[i] [0] = true`



#### （3）实现

```java

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canPartition(int[] nums) {
        int length = nums.length;
        int target = 0;
        for (int temp : nums){
            target += temp;
        }
        if (target % 2 == 1){//奇数，不满足条件
            return false;
        }
        target /= 2;
        boolean[][] dp = new boolean[length][target+1];
        for (int i = 0; i < length; i++) {
            dp[i][0]=true;
        }
        for (int i = 1; i < length; i++) {
            for (int j = 1; j < target+1; j++) {
                dp[i][j] = dp[i-1][j] || ((j-nums[i])>=0&&dp[i-1][j-nums[i]]);
            }
        }
        return dp[length-1][target];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 102. 加减的目标值

#### （1）题目

给定一个正整数数组 nums 和一个整数 target 。

向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：

例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。



#### （2）思路

* 动态规划，可转化为0-1背包问题
* 将 '+' 后整数之和设为 x，'-' 后整数之和设为 y；由x+y = sum, x-y = target，得 **x = (sum+target)/2**
* **即从数组 nums 中找出部分元素，使其和为 (sum+target)/2**



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        for (int num : nums){
            target += num;
        }
        if (target % 2 == 1){
            return 0;
        }
        target /= 2;// 0-1 背包目标值
        int length = nums.length;
        int[][] dp = new int[length+1][target+1];
        dp[0][0] = 1;
        for (int i = 1; i < length+1; i++) {
            for (int j = 0; j < target+1; j++) {
                dp[i][j] += dp[i-1][j];
                if (j-nums[i-1]>=0){
                    dp[i][j] += dp[i-1][j-nums[i-1]];
                }
            }
        }

        return dp[length][target];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 103. 最少得硬币数目

#### （1）题目

给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。

你可以认为每种硬币的数量是无限的。



#### （2）思路

* 状态定义：**dp[i] 表示组成金额 i 所需最少的硬币数量**
* 状态转移方程：
  * `dp[i] = min(dp[i-coins[j]])+1;` 
  * 0 <= j < n
  * coins[j] 代表第 j 枚硬币得面值
* 初始化：**dp[0] = 0** 代表组成金额 0 所需最少的硬币数量为 0



#### （3）实现

```java
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for(int coin : coins){
                if (i-coin >= 0 && dp[i-coin] != Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i], dp[i-coin]+1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```





### 104. 排列的数目

#### （1）题目

给定一个由 不同 正整数组成的数组 nums ，和一个目标整数 target 。请从 nums 中找出并返回总和为 target 的元素组合的个数。数组中的数字可以在一次排列中出现任意次，但是顺序不同的序列被视作不同的组合。

题目数据保证答案符合 32 位整数范围。



#### （2）思路

* 状态定义：dp[i] 表示**从 nums 中找出总和为 i 的元素组合的个数**
* 初始化：**dp[0] = 1**，当不选取任何元素时，元素之和才为 0，因此只有 1 种方案
* 状态转移方程：
  * **遍历数组 nums 中的每个元素 num**，当 num <= i 时，`dp[i] += dp[i-num];`



#### （3）实现

```java
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int num : nums){
                if (i-num >= 0){
                    dp[i] += dp[i-num];
                }
            }
        }
        return dp[target];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
```

