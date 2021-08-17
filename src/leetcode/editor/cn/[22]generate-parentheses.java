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
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis(int n) {
        res.clear();
        generate(n, n, "");
        return res;
    }

    public void generate(int left, int right, String str){
        if (left == 0 && right == 0){
            res.add(str);
            return;
        }

        if (left > 0){
            generate(left-1, right, str + "(");
        }
        if (right > 0 && right > left){
            generate(left, right-1, str + ")");
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


