//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
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
// 
// 
// 
// Related Topics 位运算 数组 回溯 
// 👍 657 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        res.add(new ArrayList<>());

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            find(nums, 0,new ArrayList<Integer>(), i+1);
        }

        return res;
    }

    public void find(int[] nums, int index, List<Integer> temp, int target){


        for (int i = index; i < nums.length; i++) {
            if (i == index || nums[i] != nums[i-1]){
                temp.add(nums[i]);

                if (target == temp.size()){
                    res.add(new ArrayList<Integer>(temp));
                }else{
                    find(nums, i+1, temp, target);
                }

                temp.remove(temp.size()-1);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


