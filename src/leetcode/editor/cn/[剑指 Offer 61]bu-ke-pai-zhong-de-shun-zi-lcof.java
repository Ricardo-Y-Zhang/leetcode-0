//从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，
//可以看成任意数字。A 不能视为 14。 
//
// 
//
// 示例 1: 
//
// 
//输入: [1,2,3,4,5]
//输出: True 
//
// 
//
// 示例 2: 
//
// 
//输入: [0,0,1,2,5]
//输出: True 
//
// 
//
// 限制： 
//
// 数组长度为 5 
//
// 数组的数取值为 [0, 13] . 
// Related Topics 数组 排序 
// 👍 176 👎 0


package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isStraight(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int num = 0;//记录大小王的个数
        int min = 20, max = 0;//记录大小王以外的最小和最大的数
        for(int temp : nums){
            if (temp == 0){
                num++;
            }else{
                set.add(temp);
                min = Math.min(min, temp);
                max = Math.max(max, temp);
            }
        }

        //有重复卡牌
        if (set.size() != 5-num){
            return false;
        }

        //无法组成顺子
        if (max - min > 4){
            return false;
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


