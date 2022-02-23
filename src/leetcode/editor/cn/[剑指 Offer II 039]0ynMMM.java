//给定非负整数数组 heights ，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。 
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入：heights = [2,1,5,6,2,3]
//输出：10
//解释：最大的矩形为图中红色区域，面积为 10
// 
//
// 示例 2： 
//
// 
//
// 
//输入： heights = [2,4]
//输出： 4 
//
// 
//
// 提示： 
//
// 
// 1 <= heights.length <=105 
// 0 <= heights[i] <= 104 
// 
//
// 
//
// 注意：本题与主站 84 题相同： https://leetcode-cn.com/problems/largest-rectangle-in-histog
//ram/ 
// Related Topics 栈 数组 单调栈 
// 👍 21 👎 0


package leetcode.editor.cn;


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n  = heights.length;
        int[] left = new int[n], right = new int[n];
        Stack<Integer> stack = new Stack<>();//单调栈
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }
            if (stack.isEmpty()){
                left[i] = -1;
            }else{
                left[i] = stack.peek();
            }
            stack.push(i);
        }
        stack.clear();
        for (int i = n-1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }
            if (stack.isEmpty()){
                right[i] = n;
            }else{
                right[i] = stack.peek();
            }
            stack.push(i);
        }
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            int product = heights[i] * (right[i]-left[i]-1);
            ans = Math.max(product, ans);
        }

        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


