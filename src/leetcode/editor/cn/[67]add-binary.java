//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 位运算 数学 字符串 模拟 
// 👍 751 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int m = a.length(), n = b.length();
        int add = 0;
        int index = 0;
        while (index < m && index < n){
            int sum = add + a.charAt(m-1-index)-'0' + b.charAt(n-1-index)-'0';
            sb.append(sum%2);
            add = sum/2;
            index++;
        }
        while (index < m){
            int sum = add + a.charAt(m-1-index)-'0';
            sb.append(sum%2);
            add = sum/2;
            index++;
        }
        while (index < n){
            int sum = add + b.charAt(n-1-index)-'0';
            sb.append(sum%2);
            add = sum/2;
            index++;
        }
        if (add == 1){
            sb.append(add);
        }
        return sb.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)


