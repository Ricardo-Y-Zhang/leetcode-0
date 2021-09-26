//给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。 
//
// 
//
// 示例 1： 
//
// 
//输入：a = 1, b = 2
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：a = 2, b = 3
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// -1000 <= a, b <= 1000 
// 
// Related Topics 位运算 数学 
// 👍 467 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int getSum(int a, int b) {
         while (b != 0){
             int temp = a ^ b;
             b = (a & b) << 1;
             a = temp;
         }

         return a;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


