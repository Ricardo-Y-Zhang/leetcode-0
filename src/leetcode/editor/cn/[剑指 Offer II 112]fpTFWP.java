//给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。 
//
// 对于每个单元格，你可以往上，下，左，右四个方向移动。 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
//输出：4 
//解释：最长递增路径为 [1, 2, 6, 9]。 
//
// 示例 2： 
//
// 
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
//
// 
//
// 注意：本题与主站 329 题相同： https://leetcode-cn.com/problems/longest-increasing-path-in
//-a-matrix/ 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 记忆化搜索 动态规划 
// 👍 9 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[][] dp;
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        dp = new int[n][m];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans = Math.max(ans, dfs(i, j, matrix));
            }
        }
        return ans;
    }
    public int dfs(int i, int j, int[][] matrix){//返回以 (i, j) 节点为起始点的最长递增序列长度
        if (dp[i][j] != 0){//节点 (i,j) 已被搜索过，状态确定
            return dp[i][j];
        }
        dp[i][j] = 1;//未被搜索，赋初值；上下左右没有比 (i,j) 大的元素时，即为 1
        int[][] col = {{-1,1,0,0},{0,0,-1,1}};
        for (int k = 0; k < 4; k++) {
            int newi = i+col[0][k], newj = j+col[1][k];
            if (0<=newi&&newi<matrix.length&&0<=newj&&newj<matrix[0].length&&matrix[i][j]<matrix[newi][newj]){
                dp[i][j] = Math.max(dp[i][j], dfs(newi, newj, matrix)+1);
            }
        }
        return dp[i][j];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


