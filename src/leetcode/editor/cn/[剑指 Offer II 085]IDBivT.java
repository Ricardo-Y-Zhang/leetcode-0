//正整数 n 代表生成括号的对数，请设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
//
// 
//
// 注意：本题与主站 22 题相同： https://leetcode-cn.com/problems/generate-parentheses/ 
// Related Topics 字符串 动态规划 回溯 
// 👍 16 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<String> res;
    StringBuilder sb;
    public List<String> generateParenthesis(int n) {
        res =  new ArrayList<>();
        sb = new StringBuilder();
        dfs(n, n);
        return res;

    }

    public void dfs(int remain1, int remain2){
        if (remain1 == 0 && remain2 == 0){
            res.add(sb.toString());
            return;
        }
        if (remain1 != 0){
            sb.append("(");
            dfs(remain1-1, remain2);
            sb.delete(sb.length()-1, sb.length());
        }
        if (remain2 != 0 && remain1 < remain2){
            sb.append(")");
            dfs(remain1, remain2-1);
            sb.delete(sb.length()-1, sb.length());
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)


