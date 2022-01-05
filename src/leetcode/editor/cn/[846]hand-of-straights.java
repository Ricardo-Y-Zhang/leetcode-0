//Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。 
//
// 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，
//返回 false 。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
//输出：true
//解释：Alice 手中的牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。 
//
// 示例 2： 
//
// 
//输入：hand = [1,2,3,4,5], groupSize = 4
//输出：false
//解释：Alice 手中的牌无法被重新排列成几个大小为 4 的组。 
//
// 
//
// 提示： 
//
// 
// 1 <= hand.length <= 104 
// 0 <= hand[i] <= 109 
// 1 <= groupSize <= hand.length 
// 
//
// 
//
// 注意：此题目与 1296 重复：https://leetcode-cn.com/problems/divide-array-in-sets-of-k-co
//nsecutive-numbers/ 
// Related Topics 贪心 数组 哈希表 排序 
// 👍 157 👎 0


package leetcode.editor.cn;


import java.util.Arrays;
import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length%groupSize != 0){
            return false;
        }
        Arrays.sort(hand);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int temp : hand){
            map.put(temp, map.getOrDefault(temp, 0)+1);
        }
        for (int i = 0; i < hand.length; i++) {
            if (map.get(hand[i]) != 0){
                int start = hand[i];
                for (int j = 0; j < groupSize; j++) {
                    if (map.getOrDefault(start+j, 0)==0){
                        return false;
                    }
                    map.put(start+j, map.get(start+j)-1);
                }
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


