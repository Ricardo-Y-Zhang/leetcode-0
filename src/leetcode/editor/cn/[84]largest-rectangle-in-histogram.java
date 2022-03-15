//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。 
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
// Related Topics 栈 数组 单调栈 
// 👍 1809 👎 0


package leetcode.editor.cn;

import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] l = new int[n];
        int[] r = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){//单调递增栈
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
        for (int i = n-1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                r[i] = n;
            }else{
                r[i] = stack.peek();
            }
            stack.add(i);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, heights[i]*(r[i]-l[i]-1));
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


