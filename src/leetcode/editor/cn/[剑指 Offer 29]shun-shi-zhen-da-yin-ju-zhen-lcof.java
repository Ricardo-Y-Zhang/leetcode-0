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
// 👍 328 👎 0


package leetcode.editor.cn;



//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0){
            return new int[0];
        }
        int n = matrix.length, m = matrix[0].length;
        int left = 0, right = m-1, up = 0, down = n-1;
        int[] res =  new int[n*m];
        int index = 0;

        while (left <= right && up <= down){
            for (int i = left; i < right; i++) {
                res[index++] = matrix[up][i];
            }
            for (int i = up; i <= down; i++) {
                res[index++] = matrix[i][right];
            }
            if (index == n*m){
                break;
            }
            for (int i = right-1; i > left; i--) {
                res[index++] = matrix[down][i];
            }
            for (int i = down; i > up; i--) {
                res[index++] = matrix[i][left];
            }
            left++;
            right--;
            up++;
            down--;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


