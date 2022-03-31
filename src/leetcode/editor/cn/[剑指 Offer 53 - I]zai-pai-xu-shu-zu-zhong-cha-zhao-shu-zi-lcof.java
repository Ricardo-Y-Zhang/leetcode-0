//统计一个数字在排序数组中出现的次数。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [5,7,7,8,8,10], target = 8
//输出: 2 
//
// 示例 2: 
//
// 
//输入: nums = [5,7,7,8,8,10], target = 6
//输出: 0 
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
//
// 
//
// 注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-
//position-of-element-in-sorted-array/ 
// Related Topics 数组 二分查找 
// 👍 225 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int l = -1, r = n-1;
        while (l < r) {
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
        return right - left - 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


