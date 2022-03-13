//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 
//
// 说明：本题中，我们将空字符串定义为有效的回文串。 
//
// 
//
// 示例 1: 
//
// 
//输入: "A man, a plan, a canal: Panama"
//输出: true
//解释："amanaplanacanalpanama" 是回文串
// 
//
// 示例 2: 
//
// 
//输入: "race a car"
//输出: false
//解释："raceacar" 不是回文串
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 2 * 105 
// 字符串 s 由 ASCII 字符组成 
// 
// Related Topics 双指针 字符串 
// 👍 490 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch>='A' && ch<='Z') {
                ch = (char)(ch-'A'+'a');
            }
            if (ch>='0' && ch<= '9' || ch>='a' && ch<='z'){
                sb.append(ch);
            }
        }
        int n = sb.length();
        char[] chs = sb.toString().toCharArray();
        for (int i = 0; i < n / 2; i++) {
            if (chs[i] != chs[n-i-1]) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
