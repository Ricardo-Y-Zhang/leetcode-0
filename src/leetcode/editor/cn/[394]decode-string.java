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
//
//    public String decodeString(String s) {
//        char[] chs = s.toCharArray();
//        Stack<Integer> number = new Stack<>();
//        Stack<String> string = new Stack<>();
//        String str = "";
//        int num = 0;
//        for (char ch : chs) {
//            if (ch >= '0' && ch <= '9') {
//                num = num*10+ch-'0';
//            }else if (ch >= 'a' && ch <= 'z') {
//                str = str + ch;
//            }else if (ch == '[') {
//                number.add(num);
//                string.add(str);
//                num = 0;
//                str = "";
//            }else if (ch == ']') {
//                String temp = "";
//                int count = number.pop();
//                for (int i = 0; i < count; i++) {
//                    temp += str;
//                }
//                str = string.pop() + temp;//栈中肯定元素，首个为""
//            }
//        }
//        return str;
//    }

    public String decodeString(String s) {
        char[] chs = s.toCharArray();
        Stack<Integer> multi = new Stack<>();
        Stack<String> strs = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int number = 0;
        for (char ch : chs) {
            if (ch >= '0' && ch <= '9') {
                number = number*10+ch-'0';
            }else if (ch >= 'a' && ch <= 'z') {
                sb.append(ch);
            }else if (ch == '[') {
                multi.push(number);
                strs.push(sb.toString());
                number = 0;
                sb = new StringBuilder();
            }else{
                int count = multi.pop();
                StringBuilder temp = new StringBuilder();
                for (int i = 0; i < count; i++) {
                    temp.append(sb.toString());
                }
                sb = new StringBuilder(strs.pop()+temp.toString());
            }
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)


