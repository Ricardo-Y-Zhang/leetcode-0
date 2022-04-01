//如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。 
//
//
// 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与 "t
//ars"，"rats"，或 "arts" 相似。 
//
// 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同
//一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。 
//
// 给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？ 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["tars","rats","arts","star"]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：strs = ["omv","ovm"]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 300 
// 1 <= strs[i].length <= 300 
// strs[i] 只包含小写字母。 
// strs 中的所有单词都具有相同的长度，且是彼此的字母异位词。 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集 字符串 
// 👍 140 👎 0


package leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        boolean[][] issimilar = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            issimilar[i][i] = true;
            for (int j = i+1; j < n; j++) {
                boolean flag = judge(strs[i], strs[j]);
                issimilar[i][j] = flag;
                issimilar[j][i] = flag;
            }
        }
        boolean[] isvisit = new boolean[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Queue<Integer> queue = new LinkedList<>();
            if (!isvisit[i]) {
                ans++;
                queue.add(i);
                isvisit[i] = true;
                while (!queue.isEmpty()) {
                    int index = queue.poll();
                    for (int j = 0; j < n; j++) {
                        if (!isvisit[j] && issimilar[index][j]) {
                            queue.add(j);
                            isvisit[j] = true;
                        }
                    }
                }
            }
        }
        return ans;
    }
    public boolean judge(String str1, String str2) {
        int diff = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                diff++;
                if (diff > 2) {
                    return false;
                }
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


