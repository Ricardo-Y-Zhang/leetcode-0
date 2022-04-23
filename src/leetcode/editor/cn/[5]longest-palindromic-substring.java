//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3944 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        String ans = "";
        int n = s.length();
        char[] chs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            //以 i 为回文串的中心位置
            int l = i, r = i;
            while (l >= 0 && r < n && chs[l] == chs[r]) {
                l--;
                r++;
            }
            if (l < 0 || r >= n || chs[l] != chs[r]){
                l++;
                r--;
            }
            if (r-l+1 > ans.length()) ans = s.substring(l, r+1);

            //以 i 为回文串的左中心位置
            l = i; r = i+1;
            while (l >= 0 && r < n && chs[l] == chs[r]) {
                l--;
                r++;
            }
            if (l < 0 || r>=n || chs[l] != chs[r]) {
                l++;
                r--;
            }
            if (r-l+1 > ans.length()) ans = s.substring(l, r+1);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


