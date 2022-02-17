//给定一个非负整数 x ，计算并返回 x 的平方根，即实现 int sqrt(int x) 函数。 
//
// 正数的平方根有两个，只输出其中的正数平方根。 
//
// 如果平方根不是整数，输出只保留整数的部分，小数部分将被舍去。 
//
// 
//
// 示例 1: 
//
// 
//输入: x = 4
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: x = 8
//输出: 2
//解释: 8 的平方根是 2.82842...，由于小数部分将被舍去，所以返回 2
// 
//
// 
//
// 提示: 
//
// 
// 0 <= x <= 231 - 1 
// 
//
// 
//
// 注意：本题与主站 69 题相同： https://leetcode-cn.com/problems/sqrtx/ 
// Related Topics 数学 二分查找 
// 👍 10 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int mySqrt(int x) {
        int left = 0, right = x;
        while (left < right){
            long mid = ((long)left+right+1)/2;

            long product = mid * mid;
            if (product == x){
                return (int)mid;
            }else if (product < x){
                left = (int)mid;
            }else{
                right = (int)mid-1;
            }
        }
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


