//给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：[[1,2,3],[8,9,4],[7,6,5]]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 
// Related Topics 数组 矩阵 模拟 
// 👍 482 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] generateMatrix(int n) {

        int[][] res = new int[n][n];

        int num = 1;

        int left = 0, right = n-1, up = 0, down = n-1;

        while (num <= n * n){
            for (int i = left; i <= right; i++) {
                res[up][i] = num;
                num++;
            }

            for (int i = up+1; i <= down; i++) {
                res[i][right] = num;
                num++;
            }

            if (num > n*n){
                break;
            }

            for (int i = right-1; i > left; i--) {
                res[down][i] = num;
                num++;
            }

            for (int i = down; i > up; i--) {
                res[i][left] = num;
                num++;
            }

            left++;
            right--;
            up++;
            down--;
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
