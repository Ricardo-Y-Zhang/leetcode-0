//给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。 
//
// 
//
// 示例 1： 
//
// 
//输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：6
//解释：最大矩形如上图所示。
// 
//
// 示例 2： 
//
// 
//输入：matrix = []
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：matrix = [["0"]]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：matrix = [["1"]]
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：matrix = [["0","0"]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// rows == matrix.length 
// cols == matrix[0].length 
// 1 <= row, cols <= 200 
// matrix[i][j] 为 '0' 或 '1' 
// 
// Related Topics 栈 数组 动态规划 矩阵 单调栈 
// 👍 1201 👎 0


package leetcode.editor.cn;

import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        int[] nums = new int[col];
        int ans = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '1'){
                    nums[j] += 1;
                }else {
                    nums[j] = 0;
                }
            }
            ans = Math.max(ans, findmax(nums));
        }
        return ans;
    }

    public int findmax(int[] nums) {
        int n = nums.length;
        int[] l = new int[n], r = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]){
                stack.pop();
            }
            if (stack.isEmpty()) {
                l[i] = -1;
            }else{
                l[i] = stack.peek();
            }
            stack.add(i);
        }
        stack.clear();
        for (int i = n-1; i>=0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                r[i] = n;
            }else {
                r[i] = stack.peek();
            }
            stack.add(i);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int temp = (r[i]-l[i]-1)*nums[i];
            ans = Math.max(ans, temp);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


