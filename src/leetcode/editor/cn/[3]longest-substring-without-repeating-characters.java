//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 示例 4: 
//
// 
//输入: s = ""
//输出: 0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 104 
// s 由英文字母、数字、符号和空格组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 5899 👎 0


package leetcode.editor.cn;



//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*
    public int lengthOfLongestSubstring(String s) {
        int[] count = new int[300];
        int left = 0, right = 0, ans = 0;
        while (right < s.length()) {
            int index = s.charAt(right);
            count[index]++;
            if (count[index] == 2) {//存在重复字符
                while (s.charAt(left) != s.charAt(right)) {//左指针右移
                    count[s.charAt(left)]--;
                    left++;
                }
                left++;
                count[index]=1;
            }
            ans = Math.max(ans, right-left+1);
            right++;
        }
        return ans;
    }
     */


    /*
    双指针+哈希表：
        （1）双指针初始化均指向第一个字符，哈希表记录指针间的字符串
        （2）右移右指针，直到到达字符串末尾或哈希表中存在该字符
     */
    public int lengthOfLongestSubstring(String s) {
        //哈希表，记录字符是否出现
        int[] count = new int[400];
        int l = 0, r  = 0, len = s.length();
        int ans = 0;
        while (r < len) {
            //当前字符出现次数+1
            count[s.charAt(r)]++;
            //出现两次，则代表当前字符重复
            if (count[s.charAt(r)] == 2) {
                //更新最长子串长度
                ans = Math.max(ans, r-l);
                //使左指针 l 与右指针 r 指向相同字符
                while (s.charAt(l) != s.charAt(r)) {
                    count[s.charAt(l++)]--;
                }
                count[s.charAt(l++)]--;
            }
            r++;
        }
        ans = Math.max(ans, r-l);
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


