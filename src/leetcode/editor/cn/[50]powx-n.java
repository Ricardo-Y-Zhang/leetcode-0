//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。 
//
// 
//
// 示例 1： 
//
// 
//输入：x = 2.00000, n = 10
//输出：1024.00000
// 
//
// 示例 2： 
//
// 
//输入：x = 2.10000, n = 3
//输出：9.26100
// 
//
// 示例 3： 
//
// 
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/22 = 1/4 = 0.25
// 
//
// 
//
// 提示： 
//
// 
// -100.0 < x < 100.0 
// -231 <= n <= 231-1 
// -104 <= xn <= 104 
// 
// Related Topics 递归 数学 
// 👍 765 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


    public double myPow(double x, int n) {
        double res = 1.0;
        long m = n;
        boolean flag = false;
        if (m < 0){
            flag = true;
        }
        m = Math.abs(m);
        while(m != 0){
            if((m&1) == 1) res = res * x;
            x = x * x;
            m >>= 1;
        }
        if (flag ==true) res = 1/ res;
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


