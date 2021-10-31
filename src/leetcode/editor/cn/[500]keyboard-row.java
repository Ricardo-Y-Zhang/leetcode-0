//给你一个字符串数组 words ，只返回可以使用在 美式键盘 同一行的字母打印出来的单词。键盘如下图所示。 
//
// 美式键盘 中： 
//
// 
// 第一行由字符 "qwertyuiop" 组成。 
// 第二行由字符 "asdfghjkl" 组成。 
// 第三行由字符 "zxcvbnm" 组成。 
// 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：words = ["Hello","Alaska","Dad","Peace"]
//输出：["Alaska","Dad"]
// 
//
// 示例 2： 
//
// 
//输入：words = ["omk"]
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：words = ["adsdf","sfd"]
//输出：["adsdf","sfd"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 20 
// 1 <= words[i].length <= 100 
// words[i] 由英文字母（小写和大写字母）组成 
// 
// Related Topics 数组 哈希表 字符串 
// 👍 173 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String[] findWords(String[] words) {
        String[] keyboards = new String[3];
        keyboards[0] = "qwertyuiop";
        keyboards[1] = "asdfghjkl";
        keyboards[2] = "zxcvbnm";
        List<String> list = new ArrayList<>();

        for (String temp : words){
            String ltemp = temp.toLowerCase();
            String str = ltemp.substring(0, 1);
            int num = 0;
            for (; num < 3; num++) {
                if (keyboards[num].contains(str)){
                    break;
                }
            }
            boolean flag = true;
            for (int i = 0; i < ltemp.length(); i++) {
                String str1 = ltemp.substring(i, i+1);
                if (!keyboards[num].contains(str1)) {
                    flag = false;
                    break;
                }
            }
            if (flag){
                list.add(temp);
            }

        }
        String[] res = new String[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


