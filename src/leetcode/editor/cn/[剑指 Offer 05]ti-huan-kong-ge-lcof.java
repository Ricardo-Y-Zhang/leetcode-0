//请实现一个函数，把字符串 s 中的每个空格替换成"%20"。 
//
// 
//
// 示例 1： 
//
// 输入：s = "We are happy."
//输出："We%20are%20happy." 
//
// 
//
// 限制： 
//
// 0 <= s 的长度 <= 10000 
// Related Topics 字符串 
// 👍 168 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String replaceSpace(String s) {
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int i = 0; i < s.length(); i++) {
//            char ch = s.charAt(i);
//            if (ch == ' '){
//                stringBuilder.append("%20");
//            }else{
//                stringBuilder.append(ch);
//            }
//        }
//        return stringBuilder.toString();

        char[] bts = new char[s.length()*3];
        int index = 0;
        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if (ch == ' '){
                bts[index++] = '%';
                bts[index++] = '2';
                bts[index++] = '0';
            }else{
                bts[index++] = ch;
            }
        }
        String res = new String(bts, 0, index);
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


