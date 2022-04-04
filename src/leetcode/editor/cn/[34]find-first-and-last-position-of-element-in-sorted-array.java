//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// nums 是一个非递减数组 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 
// 👍 1154 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int l = -1, r = n-1;
        while (l < r) {//最后一个小于target
            int mid = l + (r-l+1)/2;
            if (nums[mid] < target) {
                l = mid;
            }else{
                r = mid-1;
            }
        }
        int left = l;
        l = 0; r = n;
        while (l < r) {
            int mid = l + (r-l)/2;
            if (nums[mid] <= target){
                l = mid+1;
            }else {
                r = mid;
            }
        }
        int right = l;
        if (left == right-1){
            return new int[]{-1,-1};
        }
        return new int[]{left+1, right-1};
    }
}
//leetcode submit region end(Prohibit modification and deletion)


