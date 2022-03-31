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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] findContinuousSequence(int target) {
        int max = target/2;
        int l = 1, r = 1;
        List<int[]> ans = new ArrayList<>();
        int sum = 1;
        while (l <= max) {
            if (sum == target) {
                int[] temp = new int[r-l+1];
                for (int i = l; i <= r; i++) {
                    temp[i-l] = i;
                }
                ans.add(temp);
                sum -= l;
                l++;
            }else if (sum < target) {
                r++;
                sum += r;
            }else {
                sum -= l;
                l++;
            }
        }
        return ans.toArray(new int[0][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


