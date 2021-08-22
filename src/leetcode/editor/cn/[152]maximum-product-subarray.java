//给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。 
//
// 
//
// 示例 1: 
//
// 输入: [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 
//
// 示例 2: 
//
// 输入: [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
// Related Topics 数组 动态规划 
// 👍 1241 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProduct(int[] nums) {

        int res = nums[0];

        int[] dpMax = new int[nums.length];

        int[] dpMin = new int[nums.length];

        dpMax[0] = nums[0];
        dpMin[0] = nums[0];

        for (int i = 1; i < nums.length; i++){
            dpMax[i] = getMax(dpMax[i-1] * nums[i], dpMin[i-1] * nums[i], nums[i]);
            dpMin[i] = getMin(dpMax[i-1] * nums[i], dpMin[i-1] * nums[i], nums[i]);

            res = dpMax[i] > res ? dpMax[i] : res;
         }

        return res;
    }

    public int getMax(int a, int b, int c){
        int max = a > b ? a : b;
        max = max > c ? max : c;
        return max;
    }

    public int getMin(int a, int b, int c){
        int min = a < b ? a : b;
        min = min < c ? min : c;
        return min;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


