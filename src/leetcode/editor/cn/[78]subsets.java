//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// nums 中的所有元素 互不相同 
// 
// Related Topics 位运算 数组 回溯 
// 👍 1263 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        //使用二进制的前 n 位表示，nums[i]是否加入集合
        int n = nums.length;
        int max = (1 << n) -1;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int bit = (i>>j) & 1;
                if (bit == 1) {
                    temp.add(nums[j]);
                }
            }
            ans.add(temp);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


