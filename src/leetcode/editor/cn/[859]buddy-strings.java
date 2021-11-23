//给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。 
//
// 交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。 
//
// 
// 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ab", goal = "ba"
//输出：true
//解释：你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。 
//
// 示例 2： 
//
// 
//输入：s = "ab", goal = "ab"
//输出：false
//解释：你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。 
//
// 示例 3： 
//
// 
//输入：s = "aa", goal = "aa"
//输出：true
//解释：你可以交换 s[0] = 'a' 和 s[1] = 'a' 生成 "aa"，此时 s 和 goal 相等。
// 
//
// 示例 4： 
//
// 
//输入：s = "aaaaaaabc", goal = "aaaaaaacb"
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, goal.length <= 2 * 104 
// s 和 goal 由小写英文字母组成 
// 
// Related Topics 哈希表 字符串 
// 👍 208 👎 0


package leetcode.editor.cn;


import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()){
            return false;
        }
        int num = 0;
        char ch1 = ' ', ch2 = ' ';
        boolean flag = false;
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
            if (s.charAt(i) != goal.charAt(i)){
                num++;
                if (num == 1){
                    ch1 = s.charAt(i);
                    ch2 = goal.charAt(i);
                }else if (num == 2){
                    if (s.charAt(i) != ch2 || goal.charAt(i) != ch1){
                        return false;
                    }
                }else if (num == 3){
                    return false;
                }
            }
        }

        if (num == 0 && set.size() == s.length()){
            return false;
        }
        if (num == 1){
            return false;
        }
        return true;

    }
}
//leetcode submit region end(Prohibit modification and deletion)


