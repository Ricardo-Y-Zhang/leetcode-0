//给定一个只包含整数的有序数组 nums ，每个元素都会出现两次，唯有一个数只会出现一次，请找出这个唯一的数字。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,1,2,3,3,4,4,8,8]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: nums =  [3,3,7,7,10,11,11]
//输出: 10
// 
//
// 
//
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 105 
// 0 <= nums[i] <= 105 
// 
//
// 
//
// 进阶: 采用的方案可以在 O(log n) 时间复杂度和 O(1) 空间复杂度中运行吗？ 
//
// 
//
// 注意：本题与主站 540 题相同：https://leetcode-cn.com/problems/single-element-in-a-sorted-
//array/ 
// Related Topics 数组 二分查找 
// 👍 16 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int singleNonDuplicate(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int left = 0, right = nums.length - 1;
        while (left < right){
            int mid = left + (right-left)/2;
            if (mid%2 == 1){//下标为奇数，与前一个元素比较
                if (nums[mid] == nums[mid-1]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }else{//下标为偶数，与后一个元素比较
                if (nums[mid] == nums[mid+1]){
                    left = mid + 2;
                }else{
                    right = mid;
                }
            }
        }
        return nums[left];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


