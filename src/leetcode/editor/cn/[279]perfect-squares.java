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

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int res = 10000;
    int[] k = new int[101];
    public int numSquares(int n) {
        res = 10000;
        for (int i = 0; i < 101; i++) {
            k[i] = i * i;
        }

//        for (int i = 1; i <= (int) Math.sqrt(n); i++) {
//            find(n, i, 0, 0);
//            System.out.println("res = " + res);
//        }
        find(43, 5, 0, 0);
        return res;
    }

    public void find(int n, int x, int temp, int num){
        if (x == 0){
            return;
        }

        if (temp + k[x] < n){
            System.out.println("x = " + x);
            find(n, x, temp + k[x], num+1);
        }else {
            if (temp + k[x] == n) {
                System.out.println("x = " + x);
                num++;
                if (num < res) {
                    res = num;
                }
                num--;
            }
            find(n,x-1, temp, num);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)


