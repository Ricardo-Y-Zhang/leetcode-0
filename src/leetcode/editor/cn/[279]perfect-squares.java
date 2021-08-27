//给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。 
//
// 给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。 
//
// 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
// 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12
//输出：3 
//解释：12 = 4 + 4 + 4 
//
// 示例 2： 
//
// 
//输入：n = 13
//输出：2
//解释：13 = 4 + 9 
// 
//
// 提示： 
//
// 
// 1 <= n <= 104 
// 
// Related Topics 广度优先搜索 数学 动态规划 
// 👍 1036 👎 0


package leetcode.editor.cn;

import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int res = 10000;
    public int numSquares(int n) {
        HashSet<Integer> set = new HashSet<>();

        for (int i = 1; i < 101; i++) {
            set.add(i*i);
        }

        if (set.contains(n)){
            return 1;
        }

        find(0, set, n);

        return res;
    }

    public void find(int num, HashSet<Integer> set, int target) {
        if (num >= res || target < 0){
            return;
        }


        for (int i = 100; i >= 1; i--) {
            int newTarget = target - i*i;
            int newNum  = num + 1;
            if (newNum < res && newTarget > 0){
                if (set.contains(newTarget)){
                    res = newNum + 1;
                }else {
                    find(newNum, set, newTarget);
                }
            }
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)


