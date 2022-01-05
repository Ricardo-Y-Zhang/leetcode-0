//输入一个字符串，打印出该字符串中字符的所有排列。 
//
// 
//
// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。 
//
// 
//
// 示例: 
//
// 输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
// 
//
// 
//
// 限制： 
//
// 1 <= s 的长度 <= 8 
// Related Topics 字符串 回溯 
// 👍 469 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    ArrayList<String> list = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    boolean[] isvisit;
    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        s = new String(chars);
        isvisit = new boolean[s.length()];
        salute(s, 0);
        return list.toArray(new String[0]);
    }
    public void salute(String s, int index){
        if (index == s.length()){
            list.add(sb.toString());
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (isvisit[i]==false){
                if (i == 0||isvisit[i-1]==true||s.charAt(i)!=s.charAt(i-1)){//确保该轮不会选择相同的字符
                    sb.append(s.charAt(i));
                    isvisit[i]=true;
                    salute(s, index+1);
                    //状态回溯
                    sb.deleteCharAt(sb.length()-1);
                    isvisit[i]=false;
                }
            }
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)


