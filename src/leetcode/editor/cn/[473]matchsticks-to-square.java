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
import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
/*
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
 */

/*
class Solution {

    public boolean makesquare(int[] matchsticks) {
        int n = matchsticks.length;
        Arrays.sort(matchsticks);
        for (int i = 0; i < n/2; i++) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[n-i-1];
            matchsticks[n-i-1] = temp;
        }
        int len = 0;
        for (int stick : matchsticks) len += stick;
        if (len % 4 != 0) return false;
        len /= 4;
        return dfs(matchsticks, 0, new int[4], len);
    }

    public boolean dfs(int[] matchsticks, int index, int[] edges, int len) {
        if (index == matchsticks.length) return true;
        //加入4条边中的任意一条边
        for (int i = 0; i < 4; i++) {
            edges[i] += matchsticks[index];
            if (edges[i] <= len && dfs(matchsticks, index+1, edges, len)) {
                return true;
            }
            edges[i] -= matchsticks[index];
        }
        return false;
    }
}

 */

class Solution {

    public boolean makesquare(int[] matchsticks) {
        int n = matchsticks.length;
        int len = 0;
        for (int stick : matchsticks) len += stick;
        if (len % 4 != 0) return false;
        len /= 4;
        int[] dp = new int[1<<n];//当前状态下，未被放满的边的长度
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i < 1<<n; i++) {//遍历所有的状态
            for (int j = 0; j < n; j++) {
                if ((i&(1<<j)) == 0) continue;
                int pre = i ^ (1<<j);//删除第j条火柴
                if (dp[pre] >= 0 && dp[pre]+matchsticks[j] <= len) {//状态转移
                    dp[i] = (dp[pre]+matchsticks[j]) % len;
                    break;//从任意状态转移，当前边的长度都应相等
                }
            }
        }
        return dp[(1<<n)-1]== 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


