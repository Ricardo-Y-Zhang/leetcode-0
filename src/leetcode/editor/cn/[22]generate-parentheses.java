//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 有效括号组合需满足：左括号必须以正确的顺序闭合。 
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
// Related Topics 字符串 动态规划 回溯 
// 👍 1962 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //有效括号：右括号前存在可匹配的左括号
    List<String> ans = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    public List<String> generateParenthesis(int n) {
        dfs(n, n);
        return ans;
    }
    public void dfs(int l, int r) {//l,r分别代表左右括号的剩余数量
        if (l == 0 && r == 0) {
            ans.add(sb.toString());
            return;
        }
        if (l > 0) {
            sb.append("(");
            dfs(l-1, r);
            sb.deleteCharAt(sb.length()-1);
        }
        if (r > l) {
            sb.append(")");
            dfs(l, r-1);
            sb.deleteCharAt(sb.length()-1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


