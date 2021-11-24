//输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。 
//
// 
//
// 示例 1： 
//
// 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
//
// 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 限制： 
//
// 
// 0 <= matrix.length <= 100 
// 0 <= matrix[i].length <= 100 
// 
//
// 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/ 
// Related Topics 数组 矩阵 模拟 
// 👍 316 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0){
            return new int[0];
        }
        int n = matrix.length, m = matrix[0].length;
        int left = 0, right = m-1, up = 0, down = n-1, num = 0;
        int[] res = new int[m*n];
        while (num != m*n){
            for (int i = left; i <= right; i++) {
                res[num++] = matrix[up][i];
            }

            for (int i = up+1; i <= down; i++) {
               res[num++] = matrix[i][right];
            }
            if (num == m*n){
                break;
            }

            for (int i = right-1; i > left; i--) {
                res[num++] = matrix[down][i];
            }

            for (int i = down; i> up; i--){
                res[num++] = matrix[i][left];
            }
            up++;
            down--;
            left++;
            right--;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


