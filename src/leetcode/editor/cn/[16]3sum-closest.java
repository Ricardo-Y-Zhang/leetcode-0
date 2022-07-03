//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
//。假定每组输入只存在唯一答案。 
//
// 
//
// 示例： 
//
// 输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 10^3 
// -10^3 <= nums[i] <= 10^3 
// -10^4 <= target <= 10^4 
// 
// Related Topics 数组 双指针 排序 
// 👍 880 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
//leetcode submit region begin(Prohibit modification and deletion)
//class Solution {
//    public int threeSumClosest(int[] nums, int target) {
//        Arrays.sort(nums);
//
//        int res = 0x3f3f3f3f;
//
//        for (int i = 0; i < nums.length - 2; i++) {
//            for (int j = i + 1, k = nums.length-1; j != k;){
//                int temp = nums[i] + nums[j] + nums[k];
//                int min1 = Math.abs(temp - target), min2 = Math.abs(res - target);
//                if (min1 == 0){
//                    return target;
//                }
//
//                if (min1 < min2){
//                    res = temp;
//                }
//
//                if (temp < target){
//                    j++;
//                }else if (temp > target){
//                    k--;
//                }
//            }
//        }
//
//        return res;
//    }
//}


class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 1000000;
        for (int i = 0; i < n-2; i++) {
            int a = nums[i];
            int l = i+1, r = n-1;
            while (l < r) {
                int sum = a + nums[l] + nums[r];
                if (sum == target) return target;
                if (Math.abs(sum-target) < Math.abs(ans-target)) ans = sum;
                if (sum < target) {
                    while (l < r && nums[l] == nums[++l] ){}

                }else{
                    while (l < r && nums[r] == nums[--r]){}
                }
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


