//把字符串 s 看作是 “abcdefghijklmnopqrstuvwxyz” 的无限环绕字符串，所以 s 看起来是这样的： 
//
// 
// "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd...." . 
// 
//
// 现在给定另一个字符串 p 。返回 s 中 唯一 的 p 的 非空子串 的数量 。 
//
// 
//
// 示例 1: 
//
// 
//输入: p = "a"
//输出: 1
//解释: 字符串 s 中只有一个"a"子字符。
// 
//
// 示例 2: 
//
// 
//输入: p = "cac"
//输出: 2
//解释: 字符串 s 中的字符串“cac”只有两个子串“a”、“c”。.
// 
//
// 示例 3: 
//
// 
//输入: p = "zab"
//输出: 6
//解释: 在字符串 s 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= p.length <= 10⁵ 
// p 由小写英文字母构成 
// 
// Related Topics 字符串 动态规划 👍 245 👎 0


import java.util.Arrays;
import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findSubstringInWraproundString(String p) {
        char[] chs = p.toCharArray();
        int n = p.length();
        int[] len = new int[26];//len[i]记录以 'a'+i 结尾的最长连续子串长度
        int[] dp = new int[n];//记录以p[i]结尾的最长连续子串长度
        dp[0] = 1;
        len[chs[0]-'a'] = 1;
        int ans = 1;
        for (int i = 1; i < n; i++) {
            if (chs[i]-chs[i-1] == 1 || (chs[i] == 'a' && chs[i-1] == 'z')) {//连续
                dp[i] = dp[i-1]+1;
            }else{
                dp[i] = 1;
            }
            if (dp[i] > len[chs[i]-'a']) {
                ans += dp[i] - len[chs[i]-'a'];
                len[chs[i]-'a'] = dp[i];
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
