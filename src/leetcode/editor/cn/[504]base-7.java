//给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。 
//
// 
//
// 示例 1: 
//
// 
//输入: num = 100
//输出: "202"
// 
//
// 示例 2: 
//
// 
//输入: num = -7
//输出: "-10"
// 
//
// 
//
// 提示： 
//
// 
// -107 <= num <= 107 
// 
// Related Topics 数学 
// 👍 136 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String convertToBase7(int num) {
        if (num == 0){
            return "0";
        }
        boolean flag = num < 0 ? true : false;
        StringBuilder sb = new StringBuilder();
        num = Math.abs(num);
        while (num != 0){
            sb.append(num%7);
            num /= 7;
        }
        if (flag) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)


