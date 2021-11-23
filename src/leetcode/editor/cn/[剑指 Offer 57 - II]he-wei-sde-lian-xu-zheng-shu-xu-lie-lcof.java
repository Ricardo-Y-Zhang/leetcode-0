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
// 👍 343 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] findContinuousSequence(int target) {
        ArrayList<int[]> list = new ArrayList();
        int i = 1, j = 2, sum = 3;
        while (i < j){
            if (sum == target){
                int[] temp = new int[j-i+1];
                for(int k = i; k <= j; k++){
                    temp[k-i] = k;
                }
                list.add(temp);
                sum -= i;
                i++;
            }else if (sum < target){
                j++;
                sum += j;
            }else{
                sum -= i;
                i++;
            }
        }
        return list.toArray(new int[0][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


