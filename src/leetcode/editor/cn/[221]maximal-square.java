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

        int res = 0;

        int[][] dp = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[0].length; j++) {

                if (matrix[i][j] == '1'){
                    if (i == 0 || j == 0){
                        dp[i][j] = 1;
                    }else{
                        //左上位置的最大边长
                        int len = dp[i-1][j-1];

                        //记录行列同为'1'的个数
                        int plus = 0;
                        for (int k = i, l = j; k >= i-len && l >= j-len; k--, l--) {
                            if (matrix[k][j] == '0' || matrix[i][l] == '0'){
                                break;
                            }

                            plus++;
                        }

                        //更新以(i, j) 为底角的矩阵的最大正方形边长
                        dp[i][j] = plus;
                    }
                }

                //更新整个矩阵的最大正方形边长
                res = res > dp[i][j] ? res : dp[i][j];
            }
        }

        return res * res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


