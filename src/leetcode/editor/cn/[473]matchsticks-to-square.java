//你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。你要用 所有的火柴棍 拼成一个正方形。你 不能
//折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。 
//
// 如果你能使这个正方形，则返回 true ，否则返回 false 。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: matchsticks = [1,1,2,2,2]
//输出: true
//解释: 能拼成一个边长为2的正方形，每边两根火柴。
// 
//
// 示例 2: 
//
// 
//输入: matchsticks = [3,3,3,3,4]
//输出: false
//解释: 不能用所有火柴拼成一个正方形。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= matchsticks.length <= 15 
// 1 <= matchsticks[i] <= 10⁸ 
// 
// Related Topics 位运算 数组 动态规划 回溯 状态压缩 👍 315 👎 0


import java.util.ArrayList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int max = 0;
    ArrayList<Integer> list = new ArrayList<>();
    public boolean makesquare(int[] matchsticks) {
        int len = matchsticks.length;
        int SUM = 0;
        for (int match : matchsticks) {
            SUM += match;
        }
        if (SUM % 4 != 0) return false;
        int avg = SUM/4;
        max = (int)Math.pow(2,len)-1;
        for (int i = 1; i <= max; i++) {
            int j = i;
            int sum = 0;
            int ind = 0;
            while (j != 0) {
                sum += (j & 1) == 0 ? 0 : matchsticks[ind];
                ind++;
                j >>= 1;
                if (sum > avg) break;
            }
            if (sum == avg) list.add(i);
        }

        if (list.size() < 4) return false;
        isvisit = new boolean[list.size()];
        dfs(0, 0);
        return flag;
    }
    boolean[] isvisit;
    boolean flag;
    public void dfs(int now, int index) {
        if (flag || index > 4) return;
        if (now == max && index == 4){
            flag = true;
            return ;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!isvisit[i] && (now & list.get(i)) == 0) {
                isvisit[i] = true;
                dfs(now^list.get(i), index+1);
                isvisit[i] = false;
            }
            if (flag) break;
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)
