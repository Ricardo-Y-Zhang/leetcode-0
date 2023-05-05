//给定一个整数 n ，返回 可表示为两个 n 位整数乘积的 最大回文整数 。因为答案可能非常大，所以返回它对 1337 取余 。 
//
// 
//
// 示例 1: 
//
// 
//输入：n = 2
//输出：987
//解释：99 x 91 = 9009, 9009 % 1337 = 987
// 
//
// 示例 2: 
//
// 
//输入： n = 1
//输出： 9
// 
//
// 
//
// 提示: 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 数学 👍 110 👎 0


import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int largestPalindrome(int n) {
        if (n == 1) return 9;
        int max = (int)Math.pow(10, n)-1;//最大的n位数，即回文数前半部分的最大值
        int min = (int)Math.pow(10, n-1);//最小的n位数
        //枚举前半部分
        for (int i = max; i >= min; i--) {
            //组装回文数
            long number = i, temp = i;
            while (temp != 0) {
                number = number*10 + temp%10;
                temp /= 10;
            }
            //从最大的n位数max向下枚举，判断number是否能由n位数相乘
            for (long j = max; j*j>=number; j--) {
                if (number%j == 0) return (int)(number%1337);
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
