//给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。 
//
// s 的 旋转操作 就是将 s 最左边的字符移动到最右边。 
//
// 
// 例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。 
// 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcde", goal = "cdeab"
//输出: true
// 
//
// 示例 2: 
//
// 
//输入: s = "abcde", goal = "abced"
//输出: false
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, goal.length <= 100 
// s 和 goal 由小写英文字母组成 
// 
// Related Topics 字符串 字符串匹配 👍 170 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && indexof(s+s, goal)!=-1;
    }

    //KMP
    public int indexof(String s, String p) {
        int n = s.length(), m = p.length();
        s = " " + s;
        p = " " + p;
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        int[] next = new int[m+1];
        for (int i = 2,j = 0; i <= m; i++) {
            while (j > 0 && pp[i] != pp[j+1]) j = next[j];
            if (pp[i] == pp[j+1]) j++;
            next[i] = j;
        }
        for (int i=1, j=0; i<=n; i++) {
            while (j>0&&ss[i]!=pp[j+1]) j = next[j];
            if (ss[i] == pp[j+1]) j++;
            if (j==m) return i-m;
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
