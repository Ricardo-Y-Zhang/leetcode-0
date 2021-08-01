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
    public int longestValidParentheses(String s) {
        int maxLength = 0;
        int length = 0;
        int num = 0;
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            String ch = s.substring(i, i+1);

            if (ch.equals("(")){
                stack.add(ch);
                num++;
            }else{
                if (num == 0){
                    length = 0;
                    while (!stack.isEmpty()){
                        length += Integer.parseInt(stack.pop());
                    }
                    stack.clear();
                    maxLength = length > maxLength ? length : maxLength;
                }else{
                    length = 2;
                    boolean flag = false;
                    while (!stack.isEmpty() && flag == false){
                        if (stack.peek().equals("(")){
                            stack.pop();
                            num--;
                            stack.add(String.valueOf(length));
                            flag = true;
                        }else{
                            length += Integer.parseInt(stack.pop());
                        }
                    }
                }
            }
        }
        System.out.println("stack = " + stack);
        length = 0;
        while (!stack.isEmpty()){
            if (stack.peek().equals("(")){
                maxLength = length > maxLength ? length : maxLength;
                length = 0;
                stack.pop();
            }else{
                length += Integer.parseInt(stack.pop());
            }
        }
        maxLength = length > maxLength ? length : maxLength;
        return maxLength;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


