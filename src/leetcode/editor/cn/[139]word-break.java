//给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。 
//
// 说明： 
//
// 
// 拆分时可以重复使用字典中的单词。 
// 你可以假设字典中没有重复的单词。 
// 
//
// 示例 1： 
//
// 输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
// 
//
// 示例 2： 
//
// 输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
//     注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
// 
// Related Topics 字典树 记忆化搜索 哈希表 字符串 动态规划 
// 👍 1125 👎 0


package leetcode.editor.cn;

import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
/*
class Solution {

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] state = new boolean[s.length() + 1];
        state[0] = true;

        for (int i = 1; i < state.length; i++) {
            for (String str : wordDict){
                int fromIndex = i - str.length();
                if (fromIndex >= 0 && state[fromIndex] == true && str.equals(s.substring(fromIndex, i))){
                    state[i] = true;
                    break;
                }
            }

        }

        return state[s.length()];

    }

}
 */

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s.length() == 0) return true;
        int n = s.length();
        boolean[] dp = new boolean[n+1];//dp[i]表示s[0, i)是否能由wordDict组成
        dp[0] = true;
        for (int i = 1; i < n+1; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {//s[0,j)能被组成且s[j,i)在wordDict中
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


