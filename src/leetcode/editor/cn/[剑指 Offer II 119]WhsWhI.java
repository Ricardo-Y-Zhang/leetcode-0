//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。 
//
// 示例 2： 
//
// 
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 104 
// -109 <= nums[i] <= 109 
// 
//
// 
//
// 进阶：可以设计并实现时间复杂度为 O(n) 的解决方案吗？ 
//
// 
//
// 注意：本题与主站 128 题相同： https://leetcode-cn.com/problems/longest-consecutive-sequen
//ce/ 
// Related Topics 并查集 数组 哈希表 
// 👍 16 👎 0


package leetcode.editor.cn;

import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums){
            set.add(num);
        }
        int length = 0;
        for (int start : set){
            if (set.contains(start-1)){//不是连续子序列的起始点
                continue;
            }
            int tempLength = 0;
            while (set.contains(start)){
                tempLength++;
                start++;
            }
            length = Math.max(length, tempLength);
        }
        return length;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


