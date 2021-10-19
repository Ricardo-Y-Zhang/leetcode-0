//给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。 
//
// 整数除法仅保留整数部分。 
//
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：s = "3+2*2"
//输出：7
// 
//
// 示例 2： 
//
// 
//输入：s = " 3/2 "
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：s = " 3+5 / 2 "
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 3 * 105 
// s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开 
// s 表示一个 有效表达式 
// 表达式中的所有整数都是非负整数，且在范围 [0, 231 - 1] 内 
// 题目数据保证答案是一个 32-bit 整数 
// 
// 
// 
// Related Topics 栈 数学 字符串 
// 👍 477 👎 0


package leetcode.editor.cn;


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        Stack<Character> stack1 = new Stack<>();
        s = s.trim();
        for (int i = 0; i < s.length();) {
            while (i < s.length() && s.charAt(i) == ' ') {
                i++;
            }

            if (i == s.length()){
                break;
            }

            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                int j = i;
                while (j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                    j++;
                }
                int temp = Integer.parseInt(s.substring(i, j));
                stack.add(temp);
                i = j;
            }

            while (i < s.length() && s.charAt(i) == ' ') {
                i++;
            }

            if (i == s.length()){
                break;
            }

            char ch = s.charAt(i);
            i++;

            while (s.charAt(i) == ' ') {
                i++;
            }

            int temp = 0;
            if (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                int j = i;
                while (j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9') {
                    j++;
                }
                temp = Integer.parseInt(s.substring(i, j));

                i = j;
            }

            if (ch == '-' || ch == '+') {
                stack1.add(ch);
                stack.add(temp);
            } else if (ch == '*') {
                int pop = stack.pop();
                stack.add(pop * temp);
            } else {
                int pop = stack.pop();
                stack.add(pop / temp);
            }
        }


        int res = stack.get(0);
        for (int i = 0; i < stack1.size(); i++) {
            if (stack1.get(i) == '+'){
                res = res + stack.get(i+1);
            }else {
                res = res - stack.get(i+1);
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


