//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。 
//
// 你可以按任意顺序返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,7,11,15], target = 9
//输出：[0,1]
//解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,4], target = 6
//输出：[1,2]
// 
//
// 示例 3： 
//
// 
//输入：nums = [3,3], target = 6
//输出：[0,1]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 104 
// -109 <= nums[i] <= 109 
// -109 <= target <= 109 
// 只会存在一个有效答案 
// 
//
// 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？ 
// Related Topics 数组 哈希表 
// 👍 13718 👎 0


package leetcode.editor.cn;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


//leetcode submit region begin(Prohibit modification and deletion)
/*
 O(n)的时间复杂度：
    （1）不能使用双重循环
    （2）使用HashMap存储数组元素和下标的映射，再对数组进行排序，最后使用双指针查询两数之和等于target；
        时间复杂度为O(logn)；
        如果数组中存在相同元素，则应使用HashMap<Integer, List<Integer>>存储下标集合；
    （3）HashMap.containsKey()的时间复杂度为O(1)，计算key的hash值进行查询
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])) {
                ans[0] = map.get(target-nums[i]);
                ans[1] = i;
                break;
            }
            map.put(nums[i], i);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


