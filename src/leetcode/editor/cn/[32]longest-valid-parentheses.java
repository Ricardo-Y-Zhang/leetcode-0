//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 104 
// s[i] 为 '(' 或 ')' 
// 
// 
// 
// Related Topics 栈 字符串 动态规划 
// 👍 1389 👎 0


package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
/*
动态规划：
    public int longestValidParentheses(String s) {
        char[] chs = s.toCharArray();
        int n = s.length();
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (chs[i] == ')')  {
                if (chs[i-1] == '(') {
                    dp[i] = (i>=2 ? dp[i-2] : 0) + 2;
                }else if (i-dp[i-1] > 0 && chs[i-dp[i-1]-1] == '('){
                    dp[i] = ((i-dp[i-1]-2) >= 0 ? dp[i-dp[i-1]-2] : 0) + dp[i-1] +2;
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }

 */

//    public int longestValidParentheses(String s) {
//        int left = 0, right = 0, ans = 0;//left, right 分别记录左右括号数量
//        char[] chs = s.toCharArray();
//        for (char ch : chs) {
//            if (ch == '(') left++;
//            else right++;
//            if (left == right) {
//                ans = Math.max(ans , left+right);
//            }else if (left < right) {
//                left = 0;
//                right = 0;
//            }
//        }
//        left = 0; right = 0;
//        for (int i = 0; i < s.length(); i++) {
//            char ch = chs[chs.length-i-1];
//            if (ch == '(') left++;
//            else right++;
//            if (left == right) {
//                ans = Math.max(ans, left+right);
//            }else if (right < left) {
//                left = 0;
//                right = 0;
//            }
//        }
//        return ans;
//    }


    public int longestValidParentheses(String s) {
        char[] chs = s.toCharArray();
        //栈底元素为已遍历过的字符串中最后一个没有被匹配的右括号下标
        Stack<Integer> stack = new Stack<>();
        stack.add(-1);
        int ans = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            if (chs[i] == '(') {
                stack.add(i);
            }else {
                stack.pop();//弹出栈顶元素，匹配当前右括号
                if (stack.isEmpty()) {//栈为空，则当前右括号并未被匹配，将当前下标压入栈
                    stack.add(i);
                }else{//栈不空，以当前右括号为结尾的最长有效括号长度：下标-栈顶元素
                    ans = Math.max(ans , i-stack.peek());
                }
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


