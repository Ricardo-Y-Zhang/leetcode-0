//给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。 
//
// 进阶： 
//
// 
// 一个直观的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。 
// 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。 
// 你能想出一个仅使用常量空间的解决方案吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
//输出：[[1,0,1],[0,0,0],[1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[0].length 
// 1 <= m, n <= 200 
// -231 <= matrix[i][j] <= 231 - 1 
// 
// Related Topics 数组 哈希表 矩阵 
// 👍 568 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void setZeroes(int[][] matrix) {

        int m = matrix.length, n = matrix[0].length;

        //记录第一行和第一列中是否有0
        boolean flag1 = false, flag2 = false;

        //判断第一行是否有0
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0){
                flag1 = true;
                break;
            }
        }

        //判断第一列是否有0
        for (int i = 0; i < m; i++){
            if (matrix[i][0] == 0){
                flag2 = true;
                break;
            }
        }


        //判断(i, j)是否为0，并将对应的第一行和第一列的对应元素置为0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                if (matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        //将第一行中为0的元素的所在列置为0
        for (int i = 1; i < n; i++) {
            if (matrix[0][i] == 0){
                for (int j = 0; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }

        //将第一列中为0的元素的所在行置为0
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0){
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        //若第一行第一列中有0，则将第一行第一列置为0
        if (flag1 == true){
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }

        if (flag2 == true){
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


