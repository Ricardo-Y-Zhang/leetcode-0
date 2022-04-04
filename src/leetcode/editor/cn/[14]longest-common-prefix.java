//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 👍 2153 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        lable : while (true) {
            char ch = ' ';
            for (int j = 0; j < strs.length; j++) {
                if (i == strs[j].length()) {
                    break lable;
                }else{
                    if (j == 0) {
                        ch = strs[j].charAt(i);
                    }else if (strs[j].charAt(i) != ch) {
                        break lable;
                    }
                }
            }
            sb.append(ch);
            i++;
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
