//给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。 
//
// 换句话说，s1 的排列之一是 s2 的 子串 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s1 = "ab" s2 = "eidbaooo"
//输出：true
//解释：s2 包含 s1 的排列之一 ("ba").
// 
//
// 示例 2： 
//
// 
//输入：s1= "ab" s2 = "eidboaoo"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s1.length, s2.length <= 104 
// s1 和 s2 仅包含小写字母 
// 
// Related Topics 哈希表 双指针 字符串 滑动窗口 
// 👍 595 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        int[] num = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            num[s1.charAt(i)-'a']--;
            num[s2.charAt(i)-'a']++;
        }
        int diff = 0;//不同的字符数
        for (int i = 0; i < 26; i++) {
            if (num[i] != 0) diff++;
        }
        if (diff == 0) return true;
        for (int i = s1.length(); i < s2.length(); i++) {
            int out = s2.charAt(i-s1.length())-'a';
            int in = s2.charAt(i) - 'a';
            if (num[out] == 0) diff++;
            if (num[in] == 0) diff++;
            num[out]--;
            num[in]++;
            if (num[out] == 0) diff--;
            if (num[in] == 0) diff--;
            if (diff == 0) return true;
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


