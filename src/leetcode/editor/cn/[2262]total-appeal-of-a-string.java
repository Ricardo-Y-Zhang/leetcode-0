//字符串的 引力 定义为：字符串中 不同 字符的数量。 
//
// 
// 例如，"abbca" 的引力为 3 ，因为其中有 3 个不同字符 'a'、'b' 和 'c' 。 
// 
//
// 给你一个字符串 s ，返回 其所有子字符串的总引力 。 
//
// 子字符串 定义为：字符串中的一个连续字符序列。 
//
// 
//
// 示例 1： 
//
// 输入：s = "abbca"
//输出：28
//解释："abbca" 的子字符串有：
//- 长度为 1 的子字符串："a"、"b"、"b"、"c"、"a" 的引力分别为 1、1、1、1、1，总和为 5 。
//- 长度为 2 的子字符串："ab"、"bb"、"bc"、"ca" 的引力分别为 2、1、2、2 ，总和为 7 。
//- 长度为 3 的子字符串："abb"、"bbc"、"bca" 的引力分别为 2、2、3 ，总和为 7 。
//- 长度为 4 的子字符串："abbc"、"bbca" 的引力分别为 3、3 ，总和为 6 。
//- 长度为 5 的子字符串："abbca" 的引力为 3 ，总和为 3 。
//引力总和为 5 + 7 + 7 + 6 + 3 = 28 。
// 
//
// 示例 2： 
//
// 输入：s = "code"
//输出：20
//解释："code" 的子字符串有：
//- 长度为 1 的子字符串："c"、"o"、"d"、"e" 的引力分别为 1、1、1、1 ，总和为 4 。
//- 长度为 2 的子字符串："co"、"od"、"de" 的引力分别为 2、2、2 ，总和为 6 。
//- 长度为 3 的子字符串："cod"、"ode" 的引力分别为 3、3 ，总和为 6 。
//- 长度为 4 的子字符串："code" 的引力为 4 ，总和为 4 。
//引力总和为 4 + 6 + 6 + 4 = 20 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁵ 
// s 由小写英文字母组成 
// 
// Related Topics 哈希表 字符串 动态规划 👍 46 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*
    public long appealSum(String s) {
        int[] index = new int[26];//记录每个字符最近出现的下标
        Arrays.fill(index, -1);//初始化为-1
        int n = s.length();
        char[] chs = s.toCharArray();
        long pre = 0, ans = 0;//pre记录以s[i]结尾的子串的引力之和
        for (int i = 0; i < n; i++) {
            char ch = chs[i];
            int j = index[ch-'a'];//j为当前字符最近出现的下标
            //向子串s[j+1, i-1], s[j+2, i-1], ... ,s[i-1,i-1]添加 s[i] 会增加引力
            // 共有 (i-j-1) 个子串，再加上由 s[i] 单独组成的子串；引力值增加 (i-j)
            pre += i-j;
            ans += pre;
            index[ch-'a'] = i;
        }
        return ans;
    }
    */

    public long appealSum(String s) {
        //计算每一个字符的贡献值
        //贡献值：一个字符在子字符串中第一次出现产生贡献，后续重复出现字符不产生贡献
        int n = s.length();
        int[] index = new int[26];//记录每个字符最近出现的下标
        Arrays.fill(index, -1);
        char[] chs = s.toCharArray();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            char ch = chs[i];
            int j = index[ch-'a'];//ch最近一次出现的下标
            /*
            计算ch是一个出现的子字符串数
            右边界有(n-i)种选法，即 s[i], s[i+1], ..., s[n-1]
            左边界有(i-j)种选法，即 s[j+1], s[j+2], ..., s[i]
            总字符串数：(n-i)*(i-j)
             */
            int count = (n-i)*(i-j);
            ans += count;
            index[ch-'a'] = i;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
