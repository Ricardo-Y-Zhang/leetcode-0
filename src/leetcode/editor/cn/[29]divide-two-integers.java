//给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。 
//
// 返回被除数 dividend 除以除数 divisor 得到的商。 
//
// 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2 
//
// 
//
// 示例 1: 
//
// 输入: dividend = 10, divisor = 3
//输出: 3
//解释: 10/3 = truncate(3.33333..) = truncate(3) = 3 
//
// 示例 2: 
//
// 输入: dividend = 7, divisor = -3
//输出: -2
//解释: 7/-3 = truncate(-2.33333..) = -2 
//
// 
//
// 提示： 
//
// 
// 被除数和除数均为 32 位有符号整数。 
// 除数不为 0。 
// 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。 
// 
// Related Topics 位运算 数学 
// 👍 690 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int MAX = Integer.MAX_VALUE, MIN = Integer.MIN_VALUE;
    int LIMIT_MIN = (-1) << 30;
    int res;
    int target;
    public int divide(int dividend, int divisor) {
        if (dividend == MIN && divisor == -1){
            return MAX;
        }
        //将divide和divisor均转换成负数
        boolean flag = false;
        if ((dividend<0&&divisor>0)||(dividend>0&&divisor<0)){//最终结果为负数
            flag = true;
        }
        //dividend和divisor均转换为负数
        target = dividend < 0 ? dividend : -dividend;
        divisor = divisor < 0 ? divisor : -divisor;
        dfs(divisor, 1);
        if (flag){
            res = -res;
        }
        return res;
    }
    public void dfs(int divisor, int num){
        if (divisor >= LIMIT_MIN && divisor > target){
            dfs(divisor << 1, num << 1);
        }
        if (target <= divisor){
            target -= divisor;
            res += num;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)


