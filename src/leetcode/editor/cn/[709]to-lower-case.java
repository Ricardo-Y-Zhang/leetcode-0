//给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "Hello"
//输出："hello"
// 
//
// 示例 2： 
//
// 
//输入：s = "here"
//输出："here"
// 
//
// 示例 3： 
//
// 
//输入：s = "LOVELY"
//输出："lovely"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 由 ASCII 字符集中的可打印字符组成 
// 
// Related Topics 字符串 
// 👍 188 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String toLowerCase(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chs = s.toCharArray();
        for (char ch : chs){
            if (ch>='A'&&ch<='Z'){
                sb.append((char)(ch-'A'+'a'));
            }else{
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

