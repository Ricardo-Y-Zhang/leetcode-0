//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。 
//
// 异位词 指字母相同，但排列不同的字符串。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 104 
// s 和 p 仅包含小写字母 
// 
// Related Topics 哈希表 字符串 滑动窗口 
// 👍 609 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> res = new ArrayList<>();

        for (int i = 0; i <= s.length() - p.length(); i++) {
            if (res.size() == 0){
                String temp = s.substring(i, i+p.length());
                if (Judge(temp, p)){
                    res.add(i);
                }
            }else{
                int left = res.get(res.size()-1);
                int right = left + p.length();

                if (i >= right){
                    String temp = s.substring(i, i+p.length());
                    if (Judge(temp, p)){
                        res.add(i);
                    }
                }else{
                    String temp1 = s.substring(left, i);
                    String temp2 = s.substring(right, i + p.length());

                    if (Judge(temp1, temp2)){
                        res.add(i);
                    }
                }
            }
        }

        return res;
    }


    public boolean Judge(String str1, String str2){
        char[] char1 = str1.toCharArray();

        Arrays.sort(char1);

        str1 = new String(char1);

        char[] char2 = str2.toCharArray();

        Arrays.sort(char2);

        str2 = new String(char2);

        return str1.equals(str2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


