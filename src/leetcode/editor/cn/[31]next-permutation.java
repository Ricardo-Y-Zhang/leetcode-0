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
        int n = nums.length;
        int i = n-2;
        //从后向前，找到第一个升序对 (i,i+1)
        while (i >= 0 && nums[i] >= nums[i+1]){
            i--;
        }

        if (i >= 0) {
            //在[i+1, n-1]中从后向前，找到第一个比nums[i]大的数nums[k]
            int j = i+1, k = n-1;
            while (k >= j && nums[k] <= nums[i]) {
                k--;
            }
            //交换nums[i]和nums[k]
            int temp = nums[i];
            nums[i] = nums[k];
            nums[k] = temp;
        }
        //[i+1, n-1]降序排列，反转
        reverse(nums, i+1);
    }

    public void reverse(int[] nums, int start) {
        int n = nums.length;
        int i = start, j = n-1;
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


