//输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。 
//
// 
//
// 示例： 
//
// 
//输入：nums = [1,2,3,4]
//输出：[1,3,2,4] 
//注：[3,1,2,4] 也是正确的答案之一。 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 50000 
// 0 <= nums[i] <= 10000 
// 
// Related Topics 数组 双指针 排序 
// 👍 173 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] exchange(int[] nums) {
        int even = 0, odd = 0, n = nums.length;
        while (even < n && odd < n){
            while (even < n && nums[even] % 2 == 1){
                even++;
            }
            while (odd < n && nums[odd] % 2 == 0){
                odd++;
            }
            if (odd == n || even == n){
                break;
            }
            if (even < odd){
                int temp = nums[even];
                nums[even] = nums[odd];
                nums[odd] = temp;
            }else{
                odd = even + 1;
            }
        }
        return nums;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


