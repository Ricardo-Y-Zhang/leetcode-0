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
// 👍 219 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int search(int[] nums, int target) {
        int res = 0;
        int left = 0, right = nums.length-1;
        while (left <= right){
            int mid = (left + right)/2;
            if (nums[mid] == target) {
                left = mid;
                while (left >= 0){
                    if (nums[left--] == target){
                        res++;
                    }else{
                        break;
                    }
                }
                right = mid + 1;
                while (right < nums.length){
                    if (nums[right++] == target){
                        res++;
                    }else{
                        break;
                    }
                }
                break;
            }
            if (nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


