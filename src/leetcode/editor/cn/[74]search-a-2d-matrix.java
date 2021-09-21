//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性： 
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 100 
// -104 <= matrix[i][j], target <= 104 
// 
// Related Topics 数组 二分查找 矩阵 
// 👍 508 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int i1 = 0, j1 = 0, i2 = m-1, j2 = n-1;
        int midi = 0, midj = 0;
        while (i1 <= i2){
            midi = (i1 + i2) / 2;
            if (matrix[midi][0] <= target && matrix[midi][n-1] >= target){
                break;
            }else if (matrix[midi][0] > target){
                i2 = midi - 1;
            }else if (matrix[midi][n-1] < target){
                i1 = midi + 1;
            }
        }
        if (i1 > i2){
            return false;
        }

        while (j1 <= j2){
            midj = (j1 + j2) / 2;

            if (matrix[midi][midj] == target){
                return true;
            }else if (matrix[midi][midj] < target){
                j1 = midj + 1;
            }else if (matrix[midi][midj] > target){
                j2 = midj - 1;
            }
        }

        return false;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
