//给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [0,1]
//输出: 2
//说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。 
//
// 示例 2: 
//
// 
//输入: nums = [0,1,0]
//输出: 2
//说明: [0, 1] (或 [1, 0]) 是具有相同数量 0 和 1 的最长连续子数组。 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// nums[i] 不是 0 就是 1 
// 
//
// 
//
// 注意：本题与主站 525 题相同： https://leetcode-cn.com/problems/contiguous-array/ 
// Related Topics 数组 哈希表 前缀和 
// 👍 28 👎 0


package leetcode.editor.cn;

import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findMaxLength(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int minus = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]==1){
                minus++;
            }else{
                minus--;
            }
            if (map.containsKey(minus)){
                res = Math.max(res, i-map.get(minus));
            }else{
                map.put(minus, i);
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


