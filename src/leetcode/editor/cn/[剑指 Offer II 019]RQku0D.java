//给定一个非空字符串 s，请判断如果 最多 从字符串中删除一个字符能否得到一个回文字符串。 
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
//解释: 可以删除 "c" 字符 或者 "b" 字符
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
//
// 
//
// 注意：本题与主站 680 题相同： https://leetcode-cn.com/problems/valid-palindrome-ii/ 
// Related Topics 贪心 双指针 字符串 
// 👍 19 👎 0


package leetcode.editor.cn;

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


