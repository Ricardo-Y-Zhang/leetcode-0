//给定一个字符串 s ，请将 s 分割成一些子串，使每个子串都是 回文串 ，返回 s 所有可能的分割方案。 
//
// 回文串 是正着读和反着读都一样的字符串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "google"
//输出：[["g","o","o","g","l","e"],["g","oo","g","l","e"],["goog","l","e"]]
// 
//
// 示例 2： 
//
// 
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出：[["a"]] 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 16 
// s 仅由小写英文字母组成 
// 
//
// 
//
// 注意：本题与主站 131 题相同： https://leetcode-cn.com/problems/palindrome-partitioning/ 
// Related Topics 深度优先搜索 广度优先搜索 图 哈希表 
// 👍 16 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<String>> res;
    public String[][] partition(String s) {
        res = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        String[] strs = s.split("");
        for (String str : strs){
            temp.add(str);
        }
        dfs(temp);
        String[][] ans = new String[res.size()][];
        for(int i = 0; i < ans.length; i++){
            ans[i] = res.get(i).toArray(new String[0]);
        }
        return ans;
    }
    public void dfs(List<String> temp){
        res.add(new ArrayList<>(temp));
        if (temp.size() == 1){
            return;
        }
        for (int i = 0; i < temp.size() - 1; i++) {
            if (i != temp.size()-2){
                if (temp.get(i).equals(temp.get(i+2))){
                    String comb = temp.get(i) + temp.get(i+1) + temp.get(i+2);
                    List<String> next = new ArrayList<>(temp);
                    next.set(i, comb);
                    next.remove(i+1);
                    next.remove(i+1);
                    dfs(next);
                }
            }
            if (temp.get(i).equals(temp.get(i+1))){
                String comb = temp.get(i) + temp.get(i+1);
                List<String> next = new ArrayList<>(temp);
                next.set(i, comb);
                next.remove(i+1);
                dfs(next);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


