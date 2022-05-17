//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 
//
// 注意： 
//
// 
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。 
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 示例 3: 
//
// 
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 105 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？ Related Topics 哈希表 字符串 滑动窗口 
// 👍 1693 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String minWindow(String s, String t) {
        char[] ss = s.toCharArray(), tt = t.toCharArray();
        int[] need = new int[52];
        for (int i = 0; i < tt.length; i++) {
            need[getindex(tt[i])]--;
        }
        int diff = 0;
        for (int temp : need) {
            if (temp <  0) diff++;
        }
        int left = 0, right = 0;
        String ans = "";
        while (right < s.length()) {
            char ch = ss[right++];
            int index = getindex(ch);
            need[index]++;
            if (need[index]==0) {
                diff--;
                if (diff == 0) {
                    while (need[getindex(ss[left])]>0) {
                        need[getindex(ss[left++])]--;
                    }
                    if (ans.equals("") || ans.length() > (right-left)) {
                        ans = s.substring(left, right);
                    }
                    need[getindex(ss[left++])]--;
                    diff++;
                }
            }

        }
        return ans;
    }
    public int getindex(char ch) {
        return (ch >= 'a' && ch <= 'z') ? (ch-'a') : (ch-'A' + 26);
    }

}
//leetcode submit region end(Prohibit modification and deletion)
