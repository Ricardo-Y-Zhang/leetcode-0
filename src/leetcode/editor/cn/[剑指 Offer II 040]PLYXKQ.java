//给定一个由 0 和 1 组成的矩阵 matrix ，找出只包含 1 的最大矩形，并返回其面积。 
//
// 注意：此题 matrix 输入格式为一维 01 字符串数组。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：matrix = ["10100","10111","11111","10010"]
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
//输入：matrix = ["0"]
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：matrix = ["1"]
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：matrix = ["00"]
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
// 0 <= row, cols <= 200 
// matrix[i][j] 为 '0' 或 '1' 
// 
//
// 
//
// 注意：本题与主站 85 题相同（输入参数格式不同）： https://leetcode-cn.com/problems/maximal-rectangle
/// 
// Related Topics 栈 数组 动态规划 矩阵 单调栈 
// 👍 24 👎 0


package leetcode.editor.cn;

import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximalRectangle(String[] matrix) {
        if (matrix.length == 0 || matrix[0].length()==0){
            return 0;
        }
        int row = matrix.length, cols = matrix[0].length();
        int[] heights = new int[cols];
        int ans = 0;
        for (int i = 0; i < row; i++) {
            String str = matrix[i];
            for (int j = 0; j < cols; j++) {
                if (str.charAt(j) == '1'){
                    heights[j] += 1;
                }else{
                    heights[j] = 0;
                }
            }
            ans = Math.max(ans, largestRectangleArea(heights));
        }
        return ans;
    }
    public int largestRectangleArea(int[] heights){
        int n = heights.length;
        int[] left = new int[n], right = new int[n];
        int ans = 0;
        //确定每个height的左右边界
        Stack<Integer> maxStack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!maxStack.isEmpty() && heights[maxStack.peek()] >= heights[i]){
                maxStack.pop();
            }
            if (maxStack.isEmpty()){
                left[i] = -1;
            }else{
                left[i] = maxStack.peek();
            }
            maxStack.push(i);
        }
        maxStack.clear();
        for (int i = n-1; i >= 0; i--) {
            while (!maxStack.isEmpty() && heights[maxStack.peek()] >= heights[i]){
                maxStack.pop();
            }
            if (maxStack.isEmpty()){
                right[i] = n;
            }else{
                right[i] = maxStack.peek();
            }
            maxStack.push(i);
        }
        for (int i = 0; i < n; i++) {
            int area = heights[i] * (right[i]-left[i]-1);
            ans = Math.max(ans, area);
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


