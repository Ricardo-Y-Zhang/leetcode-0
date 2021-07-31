//给定一个经过编码的字符串，返回它解码后的字符串。 
//
// 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。 
//
// 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。 
//
// 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。 
//
// 
//
// 示例 1： 
//
// 输入：s = "3[a]2[bc]"
//输出："aaabcbc"
// 
//
// 示例 2： 
//
// 输入：s = "3[a2[c]]"
//输出："accaccacc"
// 
//
// 示例 3： 
//
// 输入：s = "2[abc]3[cd]ef"
//输出："abcabccdcdcdef"
// 
//
// 示例 4： 
//
// 输入：s = "abc3[cd]xyz"
//输出："abccdcdcdxyz"
// 
// Related Topics 栈 递归 字符串 
// 👍 831 👎 0


package leetcode.editor.cn;

import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String decodeString(String s) {

        Stack<String> stack = new Stack<>();
        String str = "";
        //遍历字符串
        for (int i = 0; i < s.length(); i++) {
            String sub = s.substring(i, i+1);
            if (sub.equals("]")){
                String temp = "", temp0 ="";
                //拼接栈内字符串
                while (!stack.isEmpty() && !stack.peek().equals("[")){
                    temp = stack.pop() + temp;
                }
                stack.pop();//"["出栈
                String times ="";
                //拼接重复次数k
                while (!stack.isEmpty() && (stack.peek().charAt(0) >= '0' && stack.peek().charAt(0) <= '9')){
                    times = stack.pop() + times;
                }
                int time = Integer.parseInt(times);
                //字符串重复k次拼接
                for (int j = 0; j < time; j++) {
                    temp0 += temp;
                }
                //拼接后的字符串入栈
                stack.add(temp0);
            }else{
                stack.add(sub);
            }
        }
        //栈内字符串拼接
        for (int i = 0; i < stack.size(); i++) {
            str += stack.get(i);
        }

        return str;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


