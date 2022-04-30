//给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。 
//请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,0]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,4,-1,1]
//输出：2
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,8,9,11,12]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁵ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
// Related Topics 数组 哈希表 👍 1457 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*
    //答案出现在[1, n+1]中，若[1,n]均出现，则为n+1，否则为[1,n]中未出现的最小数
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        //将非正数转换为n+1，方便后续处理
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n+1;
            }
        }

        //将nums[i]转换为负数，作为 i+1 出现的标记
        for (int num : nums) {
            int temp = Math.abs(num);//num可能已经被标记过，取绝对值
            if (temp <= n){//temp可以标记，将nums[temp-1]转换为负数
                nums[temp-1] = - Math.abs(nums[temp-1]);
            }
        }

        //遍历nums，若 nums[i] 未被标记，即nums[i]不为负数，则 i+1 未出现，若均被标记，则为n+1
        int ans = n+1;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= 0) {
                ans = i+1;
                break;
            }
        }
        return ans;
    }
    */

    //将数组中元素处理为第 x-1 个元素为 x
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            //交换nums[i]和nums[nums[i]-1]，使nums[i]在目标位置，swap(nums[i], nums[nums[i]-1])
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i]-1] != nums[i]) {
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i+1) {
                return i+1;
            }
        }
        return n+1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
