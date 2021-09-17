//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用一次。 
//
// 注意：解集不能包含重复的组合。 
//
// 
//
// 示例 1: 
//
// 
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//] 
//
// 示例 2: 
//
// 
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//] 
//
// 
//
// 提示: 
//
// 
// 1 <= candidates.length <= 100 
// 1 <= candidates[i] <= 50 
// 1 <= target <= 30 
// 
// Related Topics 数组 回溯 
// 👍 687 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {


        Arrays.sort(candidates);

        find(candidates, target,  0, new ArrayList<>(), 0);

        return res;
    }

    public void find(int[] candidates, int target, int num, List<Integer> temp, int sum){
        if (sum == target){
            res.add(new ArrayList<>(temp));
        }
        if (sum >= target){
            return;
        }

        for (int i = num; i < candidates.length; i++) {

            if ((i == num) || (i != num && candidates[i] != candidates[i-1])){
                temp.add(candidates[i]);
                find(candidates, target, i+1, temp, sum + candidates[i]);
                temp.remove(temp.size()-1);
            }

        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


