//实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。 
//
// 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。 
//
// 必须 原地 修改，只允许使用额外常数空间。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[1,3,2]
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1]
//输出：[1,2,3]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,1,5]
//输出：[1,5,1]
// 
//
// 示例 4： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 100 
// 
// Related Topics 数组 双指针 
// 👍 1277 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void nextPermutation(int[] nums) {
        int target = -1;
        for (int i = 0; i < nums.length-1; i++) {
            if (i+1 < nums.length && nums[i] < nums[i+1]){
                target = i;
            }
        }

        if (target != -1){
            int min = 110, minIndex = -1;
            for (int i = target + 1; i < nums.length; i++) {
               if (nums[i] < min && nums[i] > nums[target]){
                   min = nums[i];
                   minIndex = i;
               }
            }

            nums[minIndex] = nums[target];
            nums[target] = min;
            Arrays.sort(nums, target+1, nums.length);
        }else{
            for (int i = 0; i < nums.length / 2; i++) {
                int temp = nums[i];
                int index = nums.length - 1 - i;
                nums[i] = nums[index];
                nums[index] = temp;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


