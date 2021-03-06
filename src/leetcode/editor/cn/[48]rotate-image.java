//给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。 
//
// 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[[7,4,1],[8,5,2],[9,6,3]]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
//输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
// 
//
// 示例 3： 
//
// 
//输入：matrix = [[1]]
//输出：[[1]]
// 
//
// 示例 4： 
//
// 
//输入：matrix = [[1,2],[3,4]]
//输出：[[3,1],[4,2]]
// 
//
// 
//
// 提示： 
//
// 
// matrix.length == n 
// matrix[i].length == n 
// 1 <= n <= 20 
// -1000 <= matrix[i][j] <= 1000 
// 
// Related Topics 数组 数学 矩阵 
// 👍 1003 👎 0



package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
/*
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n-i-1; j++) {
                int x = i, y = j, x1 = j, y1 = n-1-i;
                int temp = matrix[x1][y1];
                matrix[x1][y1] = matrix[x][y];
                for (int k = 0; k < 4; k++){
                    int x2 = y1 , y2 = n - 1 - x1;
                    int temp1 = matrix[x2][y2];
                    matrix[x2][y2] = temp;
                    temp =  temp1;
                    x1 = x2;
                    y1 = y2;
                }
            }

        }
    }

}

 */

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n/2; i++) {
            int x = i, y = n-1-i;
            for (int j = x; j < y; j++) {
                swap(matrix, x, j, j, y);
            }
            for (int j = x; j < y; j++) {
                swap(matrix, x, j, y, n-1-j);
            }
            for (int j = x; j < y; j++) {
                swap(matrix, x, j, n-1-j, x);
            }
        }

    }

    public void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
        int temp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = temp;
    }
}


//leetcode submit region end(Prohibit modification and deletion)


