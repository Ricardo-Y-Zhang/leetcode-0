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


