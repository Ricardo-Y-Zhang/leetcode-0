//输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。 
//
// 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。 
//
// 
//
// 示例 1： 
//
// 输入：target = 9
//输出：[[2,3,4],[4,5]]
// 
//
// 示例 2： 
//
// 输入：target = 15
//输出：[[1,2,3,4,5],[4,5,6],[7,8]]
// 
//
// 
//
// 限制： 
//
// 
// 1 <= target <= 10^5 
// 
//
// 
// Related Topics 数学 双指针 枚举 
// 👍 353 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] findContinuousSequence(int target) {
        ArrayList<int[]> res = new ArrayList();

        int left = 1, right = 2;

        int sum = left + right;
        while (left < target && right < target){
            if (sum == target){
                int[] temp = new int[right-left+1];
                for (int i = left; i <= right; i++) {
                    temp[i-left]=i;
                }
                res.add(temp);
                right++;
                sum += right;
            }else if (sum < target){//右指针右移
                right++;
                sum += right;
            }else{//左指针右移
                sum -= left;
                left++;
            }
        }
        return res.toArray(new int[0][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


