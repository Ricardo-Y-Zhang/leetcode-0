//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b
//], nums[c], nums[d]] ： 
//
// 
// 0 <= a, b, c, d < n 
// a、b、c 和 d 互不相同 
// nums[a] + nums[b] + nums[c] + nums[d] == target 
// 
//
// 你可以按 任意顺序 返回答案 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// -109 <= nums[i] <= 109 
// -109 <= target <= 109 
// 
// Related Topics 数组 双指针 排序 
// 👍 953 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length-3; i++) {
            if (i != 0 && nums[i] == nums[i-1]){
                continue;
            }
            for (int j = i+1; j < nums.length-2; j++) {
                if (j != i + 1 && nums[j] == nums[j-1]){
                    continue;
                }
                int k = j + 1, l = nums.length - 1;
                while (k < l){
                    int temp = nums[i] + nums[j] + nums[k] + nums[l];
                    if (temp == target){
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        list.add(nums[l]);
                        res.add(list);
                        k++;
                    }else if (temp < target){
                        k++;
                    }else {
                        l--;
                    }
                    while (k < l && k != j + 1 && nums[k] == nums[k-1]){
                        k++;
                    }
                    while (k < l && l != nums.length-1 && nums[l] == nums[l+1]){
                        l--;
                    }
                }
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


