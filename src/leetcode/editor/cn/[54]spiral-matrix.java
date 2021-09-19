//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
// Related Topics 数组 矩阵 模拟 
// 👍 868 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        int m = matrix.length, n = matrix[0].length;

        int left = 0, right = n-1, up = 0, down = m-1;

        int num = 0;

        while (num < n * m){
            for (int i = left; i <= right; i++) {
                res.add(matrix[up][i]);
            }

            for (int i = up + 1; i < down; i++) {
                res.add(matrix[i][right]);
            }

            for (int i = right; i >= left; i--) {
                res.add(matrix[down][i]);
            }

            for (int i = down-1; i > up; i--) {
                res.add(matrix[i][left]);
            }

            num += 4;

            left++;
            right--;
            up++;
            down--;
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


