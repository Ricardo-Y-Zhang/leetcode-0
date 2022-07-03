//给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。如果不存在这样的正整数，则返回 -1 。 
//
// 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12
//输出：21
// 
//
// 示例 2： 
//
// 
//输入：n = 21
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 2³¹ - 1 
// 
// Related Topics 数学 双指针 字符串 👍 213 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int nextGreaterElement(int n) {
        int max = Integer.MAX_VALUE;
        String str = String.valueOf(n);
        char[] chs = str.toCharArray();
        for(int i = chs.length - 2; i>=0; i--) {
            if (chs[i] < chs[i+1]) {
                reverse(chs, i+1, chs.length-1);
                for (int j = i+1; j < chs.length; j++) {
                    if (chs[j] > chs[i]) {
                        swap(chs, i, j);
                        break;
                    }
                }
                long temp = Long.parseLong(new String(chs));
                return temp > max ? -1 : (int)temp;
            }
        }
        return -1;
    }
    public void reverse(char[] chs, int start, int end) {
        for (int i = start; i <= (start+end)/2; i++) {
            char temp = chs[i];
            chs[i] = chs[start+end-i];
            chs[start+end-i] = temp;
        }
    }
    public void swap(char[] chs, int i, int j) {
        char ch = chs[i];
        chs[i] = chs[j];
        chs[j] = ch;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
