//给定字符串列表 strs ，返回其中 最长的特殊序列 。如果最长特殊序列不存在，返回 -1 。 
//
// 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。 
//
// s 的 子序列可以通过删去字符串 s 中的某些字符实现。 
//
// 
// 例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。"aebdc"的子序列还包括
//"aebdc"、 "aeb" 和 "" (空字符串)。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入: strs = ["aba","cdc","eae"]
//输出: 3
// 
//
// 示例 2: 
//
// 
//输入: strs = ["aaa","aaa","aa"]
//输出: -1
// 
//
// 
//
// 提示: 
//
// 
// 2 <= strs.length <= 50 
// 1 <= strs[i].length <= 10 
// strs[i] 只包含小写英文字母 
// 
// Related Topics 数组 哈希表 双指针 字符串 排序 👍 121 👎 0



import java.util.HashMap;


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findLUSlength(String[] strs) {
        HashMap<String, Integer> map = new HashMap<>();
        for(String str : strs) {
            map.put(str, map.getOrDefault(str, 0)+1);
        }
        int ans = -1;
        lable : for (String str : map.keySet()) {
            if (map.get(str) == 1) {//只出现一次的字符串才可能是最长特殊子序列
                for (String temp : map.keySet()) {
                    if (str.length() >= temp.length()) continue;//无需和等长或更短的字符串比较
                    int i = 0, j = 0;
                    while (i < temp.length() && j < str.length()) {
                        if (str.charAt(j) != temp.charAt(i)) {
                            i++;
                        }else{
                            i++;
                            j++;
                        }
                    }
                    if (j == str.length()) continue lable;
                }
                ans = Math.max(ans , str.length());
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
