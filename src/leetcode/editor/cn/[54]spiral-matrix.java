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
        int m = matrix.length, n = matrix[0].length;
        int l = 0 , r = n-1, u = 0, d = m-1;
        List<Integer> ans = new ArrayList<>();
        while (l <= r && u <= d) {
            for (int i = l; i <= r; i++) {
                ans.add(matrix[u][i]);

            }
            for (int i = u+1; i <= d; i++) {
                ans.add(matrix[i][r]);
            }
            if (l==r||u==d) break;//只剩一行或一列
            for (int i = r-1; i > l; i--) {
                ans.add(matrix[d][i]);
            }
            for (int i = d; i>u; i--) {
                ans.add(matrix[i][l]);
            }
            l++;
            r--;
            u++;
            d--;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


