//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 0 <= n <= 3 * 104 
// 0 <= height[i] <= 105 
// 
// Related Topics 栈 数组 双指针 动态规划 单调栈 
// 👍 2531 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        int[] left = new int[n], right = new int[n];//left[i]记录[0,i]中最大值，right[i]记录[i,n-1]中最大值
        int lmax = 0, rmax = 0;
        for (int i = 0; i < n; i++) {
            lmax = Math.max(lmax, height[i]);
            left[i] = lmax;
            rmax = Math.max(rmax, height[n-i-1]);
            right[n-i-1] = rmax;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.min(left[i], right[i]) - height[i];
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


