//给定一个字符串 s ，请将 s 分割成一些子串，使每个子串都是 回文串 ，返回 s 所有可能的分割方案。 
//
// 回文串 是正着读和反着读都一样的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "google"
//输出：[["g","o","o","g","l","e"],["g","oo","g","l","e"],["goog","l","e"]]
// 
//
// 示例 2： 
//
// 
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出：[["a"]] 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 16 
// s 仅由小写英文字母组成 
// 
//
// 
//
// 注意：本题与主站 131 题相同： https://leetcode-cn.com/problems/palindrome-partitioning/ 
// Related Topics 深度优先搜索 广度优先搜索 图 哈希表 
// 👍 16 👎 0


package leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    boolean[][] dp;
    List<List<String>> res;
    List<String> temp;
    public String[][] partition(String s) {
        res = new ArrayList<>();
        temp = new ArrayList<>();
       int n = s.length();
       dp = new boolean[n][n];
        for (int i = n-1; i >= 0; i--) {
            dp[i][i] = true;//字符本身就是回文串
            for (int j = i+1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)){
                    if (j == i+1){
                        dp[i][j] = true;
                    }else{
                        dp[i][j] = dp[i+1][j-1];
                    }
                }
            }
        }
        dfs(s, 0);
        String[][] ans = new String[res.size()][];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.get(i).toArray(new String[0]);
        }
        return ans;
    }
    public void dfs(String s, int index){
        if (index == s.length()){
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (dp[index][i]){
                temp.add(s.substring(index, i+1));
                dfs(s, i+1);
                temp.remove(temp.size()-1);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


