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
        int m = s.length(), n = t.length();
        if (m < n) return "";
        String ans = "";
        int[] dict = new int[52];
        for (int i = 0; i < n; i++) {
            char ch = t.charAt(i);
            int index = dicttoindex(ch);
            dict[index]--;
        }
        int diff = 0;//字母数量不同个数
        for (int i = 0; i < dict.length; i++) {
            if (dict[i] != 0) {
                diff++;
            }
        }
        int left = 0, right = 0;
        while (right < m) {
            char ch = s.charAt(right);
            int index = dicttoindex(ch);
            dict[index]++;
            if (dict[index] == 0) {
                diff--;
                if (diff == 0){//[left, right]包含所有元素
                    //缩小窗口，求当前满足条件的最小窗口
                    while (left < m && dict[dicttoindex(s.charAt(left))]>0) {
                        dict[dicttoindex(s.charAt(left))]--;
                        left++;
                    }
                    String temp = s.substring(left, right+1);
                    if (ans.equals("")) {
                        ans = temp;
                    }else if (ans.length() > temp.length()) {
                        ans = temp;
                    }
                    //再次右移左指针
                    dict[dicttoindex(s.charAt(left++))]--;
                    diff++;
                }
            }
            right++;
        }
        return ans;
    }
    public int dicttoindex (char ch) {//字符映射
        if (ch>='A'&&ch<='Z'){
            return ch-'A'+26;
        }
        return ch-'a';
    }

}
//leetcode submit region end(Prohibit modification and deletion)
