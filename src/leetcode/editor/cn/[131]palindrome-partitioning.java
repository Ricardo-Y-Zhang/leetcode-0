//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。 
//
// 回文串 是正着读和反着读都一样的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：[["a"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 16 
// s 仅由小写英文字母组成 
// 
// Related Topics 字符串 动态规划 回溯 
// 👍 1082 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    boolean[][] dp;//记录[i,j]是否为回文串
    List<List<String>> ans = new ArrayList<>();
    List<String> temp = new ArrayList<>();
    public List<List<String>> partition(String s) {
        int n = s.length();
        dp = new boolean[n][n];
        char[] chs = s.toCharArray();
        for (int i = n-1; i >= 0; i--) {
            dp[i][i] = true;
            for (int j = i+1; j < n; j++) {
                if (j == i+1) {
                    dp[i][j] = chs[i] == chs[j];
                }else{
                    dp[i][j] = (chs[i] == chs[j]) && dp[i+1][j-1];
                }
            }
        }
        dfs(s, 0);
        return ans;

    }
    //回溯切割回文串
    public void dfs(String s, int index) {
        if (index == s.length()) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (dp[index][i]) {
                temp.add(s.substring(index, i+1));
                dfs(s, i+1);
                temp.remove(temp.size()-1);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


