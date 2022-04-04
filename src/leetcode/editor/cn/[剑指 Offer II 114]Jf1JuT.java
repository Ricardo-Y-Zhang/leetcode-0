//现有一种使用英语字母的外星文语言，这门语言的字母顺序与英语顺序不同。 
//
// 给定一个字符串列表 words ，作为这门语言的词典，words 中的字符串已经 按这门新语言的字母顺序进行了排序 。 
//
// 请你根据该词典还原出此语言中已知的字母顺序，并 按字母递增顺序 排列。若不存在合法字母顺序，返回 "" 。若存在多种可能的合法字母顺序，返回其中 任意一种
// 顺序即可。 
//
// 字符串 s 字典顺序小于 字符串 t 有两种情况： 
//
// 
// 在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么 s 的字典顺序小于 t 。 
// 如果前面 min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：words = ["wrt","wrf","er","ett","rftt"]
//输出："wertf"
// 
//
// 示例 2： 
//
// 
//输入：words = ["z","x"]
//输出："zx"
// 
//
// 示例 3： 
//
// 
//输入：words = ["z","x","z"]
//输出：""
//解释：不存在合法字母顺序，因此返回 "" 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 100 
// 1 <= words[i].length <= 100 
// words[i] 仅由小写英文字母组成 
// 
//
// 
//
// 注意：本题与主站 269 题相同： https://leetcode-cn.com/problems/alien-dictionary/ 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 数组 字符串 
// 👍 11 👎 0


package leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    boolean[][] matrix = new boolean[26][26];//matrix[i][j]表示 i < j
    int[] in = new int[26];
    HashSet<Integer> set = new HashSet<>();
    public String alienOrder(String[] words) {
        //统计出现的字母
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                set.add(ch-'a');
            }
        }
        //建图
        for (int i = 0; i < words.length - 1; i++) {
            if (!compare(words[i], words[i+1])) {
                return "";
            }
        }

        //拓扑排序
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        //入度为0的节点入队
        for (int i = 0; i < 26; i++) {
            if (set.contains(i) && in[i] == 0){
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int first = queue.poll();
            char ch = (char)('a'+first);
            sb.append(ch);
            for (int i = 0; i < 26; i++) {
                if (matrix[first][i]) {
                    in[i]--;
                    if (in[i] == 0){
                        queue.add(i);
                    }
                }
            }
        }
        if (sb.length() != set.size()) {
            return "";
        }
        return sb.toString();
    }
    public boolean compare(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int index = 0;
        while (index < len1 && index < len2 && word1.charAt(index) == word2.charAt(index)) {
            index++;
        }
        if (index == len1) {
            return true;
        }
        if (index == len2) {
            return false;
        }
        int i1 = word1.charAt(index) - 'a', i2 = word2.charAt(index) - 'a';
        if (matrix[i2][i1]) {
            return false;
        }
        if (!matrix[i1][i2]) {
            matrix[i1][i2] = true;
            in[i2]++;
        }
        return true;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


