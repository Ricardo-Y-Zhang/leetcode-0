//给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。 
//
// 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "barfoothefoobarman", words = ["foo","bar"]
//输出：[0,9]
//解释：
//从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
//输出的顺序不重要, [9,0] 也是有效答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
//输出：[6,9,12]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 由小写英文字母组成 
// 1 <= words.length <= 5000 
// 1 <= words[i].length <= 30 
// words[i] 由小写英文字母组成 
// 
// Related Topics 哈希表 字符串 滑动窗口 👍 711 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
//class Solution {
//    public List<Integer> findSubstring(String s, String[] words) {
//        HashMap<String, Integer> map = new HashMap<>();
//        for(String word : words) {
//            map.put(word, map.getOrDefault(word, 0)+1);
//        }
//        int m = words.length, n = s.length(), len = words[0].length();
//        List<Integer> ans = new ArrayList<>();
//        flag : for (int i = 0; i + m*len <= n; i++) {
//            HashMap<String, Integer> cmpmap = new HashMap<>();
//            //System.out.println(s.substring(i, i + m * len));
//            for (int j = i; j < i+m*len; j+=len) {
//                String temp = s.substring(j, j+len);
//                if (!map.containsKey(temp)) continue flag;
//                cmpmap.put(temp, cmpmap.getOrDefault(temp, 0)+1);
//            }
//            if (map.equals(cmpmap)){
//                ans.add(i);
//            }
//        }
//        return ans;
//    }
//}

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        int n = s.length(), m = words.length, len = words[0].length();
        HashMap<String, Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        for(String word : words) map.put(word, map.getOrDefault(word, 0)+1);
        for (int i = 0; i < len; i++) {
            HashMap<String, Integer> cmpmap = new HashMap<>();
            for (int j = i; j+len <= n; j+=len) {
                String in = s.substring(j, j+len);
                cmpmap.put(in, cmpmap.getOrDefault(in, 0)+1);
                if (j >= i+m*len) {
                    String out = s.substring(j-m*len, j-m*len+len);
                    if (cmpmap.get(out) == 1) cmpmap.remove(out);
                    else cmpmap.put(out, cmpmap.get(out)-1);
                }
                if (map.equals(cmpmap)) ans.add(j-(m-1)*len);
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
