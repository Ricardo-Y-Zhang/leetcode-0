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
        List<Integer> ans = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = n-1, up = 0, down = m-1;
        while (left <= right && up <= down) {
            for (int i = left; i <= right; i++) {
                ans.add(matrix[up][i]);
            }
            for (int i = up+1; i <= down; i++) {
                ans.add(matrix[i][right]);
            }
            if (left == right || up == down) {
                break;
            }
            for (int i = right-1; i >= left; i--) {
                ans.add(matrix[down][i]);
            }
            for (int i = down-1; i > up; i--) {
                ans.add(matrix[i][left]);
            }
            left++;
            right--;
            up++;
            down--;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


