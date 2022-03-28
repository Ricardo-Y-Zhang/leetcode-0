//给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。 
//
// 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
//输出：4 
//解释：最长递增路径为 [1, 2, 6, 9]。 
//
// 示例 2： 
//
// 
//输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
//输出：4 
//解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
// 
//
// 示例 3： 
//
// 
//输入：matrix = [[1]]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 200 
// 0 <= matrix[i][j] <= 231 - 1 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 记忆化搜索 动态规划 
// 👍 620 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[][] dp;
    int m , n;
    int[][] plus = {{-1,1,0,0}, {0,0,1,-1}};
    public int longestIncreasingPath(int[][] matrix) {
        m = matrix.length;
        n = matrix[0].length;
        dp = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans = Math.max(ans, dfs(i, j, matrix));
            }
        }
        return ans;
    }
    public int dfs(int x, int y, int[][] matrix) {
        if (dp[x][y] != 0) {//已经遍历过
            return dp[x][y];
        }
        dp[x][y] = 1;//初始化为1
        for (int i = 0; i < 4; i++) {
            int X = x+plus[0][i], Y=y+plus[1][i];
            if (X<0||X>=m||Y<0||Y>=n) continue;
            if (matrix[X][Y] > matrix[x][y]) {
                dp[x][y] = Math.max(dp[x][y], dfs(X, Y, matrix)+1);
            }
        }
        return dp[x][y];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


