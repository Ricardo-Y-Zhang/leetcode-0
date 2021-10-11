//给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。 
//
// 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：[3,2,3]
//输出：[3] 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：[1]
// 
//
// 示例 3： 
//
// 
//输入：[1,1,1,3,3,2,2,2]
//输出：[1,2] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 104 
// -109 <= nums[i] <= 109 
// 
// Related Topics 数组 哈希表 计数 排序 
// 👍 409 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();

        int cand1 = nums[0], cand2 = nums[0];
        int count1 = 0, count2 = 0;

        for (int temp : nums){
            if (temp == cand1) {
                count1++;
                continue;
            }

            if (temp == cand2) {
                count2++;
                continue;
            }

            if (count1 == 0) {
                count1 = 1;
                cand1 = temp;
                continue;
            }

            if (count2 == 0){
                count2 = 1;
                cand2 = temp;
                continue;
            }

            count1--;
            count2--;
        }

        //验证
        count1 = 0;
        count2 = 0;

        for (int temp : nums){
            if (temp == cand1) {
                count1++;
            }else if (temp == cand2){
                count2++;
            }
        }

        if (count1 > nums.length/3) {
            res.add(cand1);
        }

        if (count2 > nums.length/3) {
            res.add(cand2);
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


