//给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "aba"
//输出: true
// 
//
// 示例 2: 
//
// 
//输入: s = "abca"
//输出: true
//解释: 你可以删除c字符。
// 
//
// 示例 3: 
//
// 
//输入: s = "abc"
//输出: false 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length <= 105 
// s 由小写英文字母组成 
// 
// Related Topics 贪心 双指针 字符串 
// 👍 462 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean validPalindrome(String s) {
        if (judge(s)) {
            return true;
        }
        int left = 0, right = s.length()-1;
        String str1 = "", str2 = "";
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                str1 = s.substring(0, left) + s.substring(left+1);
                str2 = s.substring(0, right) + s.substring(right+1);
                break;
            }
            left++;
            right--;
        }
        if (judge(str1)||judge(str2)) {
            return true;
        }
        return false;
    }
    public boolean judge(String str) {
        int left = 0, right = str.length()-1;
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


