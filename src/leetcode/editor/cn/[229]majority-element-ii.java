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
        //摩尔投票法
        List<Integer> res = new ArrayList<>();

        int cand1 = nums[0], cand2 = nums[0], num1 = 0, num2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == cand1){
                num1++;
                continue;
            }
            if (nums[i] == cand2){
                num2++;
                continue;
            }
            if (num1 == 0){
                cand1 = nums[i];
                num1 = 1;
                continue;
            }
            if (num2 == 0){
                cand2 = nums[i];
                num2 = 1;
                continue;
            }
            num1--;
            num2--;
        }

        //核验是否出现超过1/3
        num1 = 0;
        num2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == cand1){
                num1++;
            }else if (nums[i] == cand2){
                num2++;
            }
        }

        if (num1 > nums.length/3){
            res.add(cand1);
        }
        if (num2 > nums.length/3){
            res.add(cand2);
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


