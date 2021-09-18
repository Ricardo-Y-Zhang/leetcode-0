//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
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
// Related Topics 数组 回溯 
// 👍 804 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    List<List<Integer>> res = new ArrayList<>();

    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        find(new boolean[nums.length], nums);
        return res;
    }

    public void find(boolean[] isChoosen, int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (isChoosen[i] == true || (i != 0 && nums[i] == nums[i-1] && isChoosen[i] == false && isChoosen[i-1] == false)){
            }else{
                isChoosen[i] = true;
                temp.add(nums[i]);
                if (temp.size() == nums.length){
                    res.add(new ArrayList<>(temp));
                }else{
                    find(isChoosen, nums);
                }
                isChoosen[i] = false;
                temp.remove(temp.size()-1);
            }
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)


