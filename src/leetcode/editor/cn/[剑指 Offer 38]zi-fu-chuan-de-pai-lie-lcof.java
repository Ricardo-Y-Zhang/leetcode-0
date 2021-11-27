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
// 👍 455 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<String> res = new ArrayList<>();
    public String[] permutation(String s) {

        //字符串按字典序排序
        char[] chs = s.toCharArray();
        Arrays.sort(chs);
        s = new String(chs);

        dfs("", s, new boolean[s.length()]);

        return res.toArray(new String[0]);
    }
    public void dfs(String temp, String s, boolean[] visited){
        for (int i = 0; i < s.length(); i++) {
            if (visited[i] == false){//未被访问
                if (i == 0 || !(s.charAt(i) == s.charAt(i-1) && visited[i-1] == false)){//去除重复字符串
                    temp += s.substring(i, i+1);
                    visited[i] = true;
                    if (temp.length() == s.length()){//s中所有字符均遍历完，temp为目标字符串
                        res.add(temp);
                    }else{//进入下一层递归
                        dfs(temp, s, visited);
                    }
                    //状态回溯
                    temp = temp.substring(0, temp.length()-1);
                    visited[i] = false;
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


