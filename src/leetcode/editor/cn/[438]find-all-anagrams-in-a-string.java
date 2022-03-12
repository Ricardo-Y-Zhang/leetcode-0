//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。 
//
// 异位词 指字母相同，但排列不同的字符串。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 104 
// s 和 p 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 609 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (s.length() < p.length()) {
            return ans;
        }
        int[] chs = new int[26];
        for (int i = 0; i < p.length(); i++) {
            chs[p.charAt(i)-'a']--;
            chs[s.charAt(i)-'a']++;
        }
        int diff = 0;
        for(int i = 0; i < 26; i++) {
            if (chs[i] != 0) diff++;
        }
        if (diff == 0) {
            ans.add(0);
        }
        for (int i = 1; i < s.length()-p.length()+1; i++) {
            int out = s.charAt(i-1)-'a', in = s.charAt(i+p.length()-1)-'a';
            if (chs[out] == 1) diff--;
            else if (chs[out] == 0) diff++;
            chs[out]--;
            if (chs[in] == -1) diff--;
            else if (chs[in] == 0) diff++;
            chs[in]++;
            if (diff == 0) {
                ans.add(i);
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


