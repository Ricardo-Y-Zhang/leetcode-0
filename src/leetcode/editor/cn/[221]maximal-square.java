//在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：matrix = [["0","1"],["1","0"]]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：matrix = [["0"]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 300 
// matrix[i][j] 为 '0' 或 '1' 
// 
// Related Topics 数组 动态规划 矩阵 
// 👍 848 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m][n];//dp[i][j]表示以(i,j)为右下角的最大正方形边长
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') continue;
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                }else{
                    int len = dp[i-1][j-1];
                    int len1 = 0, len2 = 0;
                    for (int k = i; k >= i-len; k--) {
                        if (matrix[k][j] == '0') break;
                        len1++;
                    }
                    for (int k = j; k >= j-len; k--) {
                        if (matrix[i][k] == '0') break;
                        len2++;
                    }
                    len = Math.min(len1, len2);
                    dp[i][j] = len;

                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans*ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


