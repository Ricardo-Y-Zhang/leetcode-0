//给定一个字符串 s ，请你找出其中不含有重复字符的 最长连续子字符串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子字符串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子字符串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
//
// 
//
// 注意：本题与主站 3 题相同： https://leetcode-cn.com/problems/longest-substring-without-re
//peating-characters/ 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 17 👎 0


package leetcode.editor.cn;

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


