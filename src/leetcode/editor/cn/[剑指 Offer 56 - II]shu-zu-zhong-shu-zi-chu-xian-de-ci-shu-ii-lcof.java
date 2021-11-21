//在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [3,4,3,3]
//输出：4
// 
//
// 示例 2： 
//
// 输入：nums = [9,1,7,9,7,9,7]
//输出：1 
//
// 
//
// 限制： 
//
// 
// 1 <= nums.length <= 10000 
// 1 <= nums[i] < 2^31 
// 
//
// 
// Related Topics 位运算 数组 
// 👍 252 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int singleNumber(int[] nums) {
        int[] bit = new int[32];
        for (int temp : nums){
            int index = 0;
            while (temp != 0){
                bit[index++] += temp & 1;
                temp >>= 1;
            }
        }
        for (int i = 0; i < 32; i++) {
            bit[i] %= 3;
        }
        int res = 0, temp = 1;
        for (int i = 0; i < 32; i++) {
            res |= temp * bit[i];
            temp <<= 1;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
