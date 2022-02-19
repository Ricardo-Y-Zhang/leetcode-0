//给定一个可包含重复数字的整数集合 nums ，按任意顺序 返回它所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
//
// 
//
// 注意：本题与主站 47 题相同： https://leetcode-cn.com/problems/permutations-ii/ 
// Related Topics 数组 回溯 
// 👍 11 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res;
    List<Integer> temp;
    boolean[] isvisit;
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        temp = new ArrayList<>();
        isvisit = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, nums.length);
        return res;
    }

    public void dfs(int[] nums, int remain){
        if (remain == 0){
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (isvisit[i] == false){
                if (i > 0 && isvisit[i-1] == false && nums[i] == nums[i-1]){//剪枝，避免选取重复元素
                    continue;
                }
                temp.add(nums[i]);
                isvisit[i] = true;
                dfs(nums, remain-1);
                //状态回溯
                temp.remove(temp.size()-1);
                isvisit[i] = false;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


