//给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。 
//
// 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 10⁴ 。 
//
// 
//
// 示例 1： 
//
// 
//输入：expression = "2-1-1"
//输出：[0,2]
//解释：
//((2-1)-1) = 0 
//(2-(1-1)) = 2
// 
//
// 示例 2： 
//
// 
//输入：expression = "2*3-4*5"
//输出：[-34,-14,-10,-10,10]
//解释：
//(2*(3-(4*5))) = -34 
//((2*3)-(4*5)) = -14 
//((2*(3-4))*5) = -10 
//(2*((3-4)*5)) = -10 
//(((2*3)-4)*5) = 10
// 
//
// 
//
// 提示： 
//
// 
// 1 <= expression.length <= 20 
// expression 由数字和算符 '+'、'-' 和 '*' 组成。 
// 输入表达式中的所有整数值在范围 [0, 99] 
// 
// Related Topics 递归 记忆化搜索 数学 字符串 动态规划 👍 611 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> diffWaysToCompute(String expression) {
        return dfs(expression.toCharArray(), 0, expression.length());
    }

    List<Integer> dfs(char[] chs, int l, int r) {
        List<Integer> ans = new ArrayList<>();
        for (int i = l; i < r; i++) {
            if (Character.isDigit(chs[i])) continue;
            List<Integer> left = dfs(chs, l, i), right = dfs(chs, i+1, r);
            for (int a : left) {
                for (int b : right) {
                    if (chs[i] == '+') ans.add(a+b);
                    else if (chs[i] == '-') ans.add(a-b);
                    else ans.add(a*b);
                }
            }
        }
        if (ans.size() == 0) {
            int temp = 0;
            for (int i = l; i < r; i++) {
                temp = temp*10+chs[i]-'0';
            }
            ans.add(temp);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
