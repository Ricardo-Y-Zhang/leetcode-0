//给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。 
//
// 示例 1: 
//
// 输入: num1 = "2", num2 = "3"
//输出: "6" 
//
// 示例 2: 
//
// 输入: num1 = "123", num2 = "456"
//输出: "56088" 
//
// 说明： 
//
// 
// num1 和 num2 的长度小于110。 
// num1 和 num2 只包含数字 0-9。 
// num1 和 num2 均不以零开头，除非是数字 0 本身。 
// 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。 
// 
// Related Topics 数学 字符串 模拟 
// 👍 737 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        StringBuilder sb1 = new StringBuilder(num1).reverse();
        StringBuilder sb2 = new StringBuilder(num2).reverse();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < sb1.length(); i++) {
            int i1 = sb1.charAt(i)-'0';
            int add = 0;
            for (int j = 0; j < sb2.length(); j++) {
                int i2 = sb2.charAt(j)-'0';
                add += i1*i2 + (ans.length() > (i+j) ? ans.charAt(i+j) : '0') - '0';
                char ch = (char)(add%10+'0');
                if (ans.length() > (i+j)) {
                    ans.setCharAt(i+j, ch);
                }else{
                    ans.append(ch);
                }
                add /= 10;
            }
            if (add != 0) {
                ans.append(add);
            }
        }
        return ans.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)


