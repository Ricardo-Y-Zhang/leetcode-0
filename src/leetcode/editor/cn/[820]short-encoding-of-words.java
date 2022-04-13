//单词数组 words 的 有效编码 由任意助记字符串 s 和下标数组 indices 组成，且满足： 
//
// 
// words.length == indices.length 
// 助记字符串 s 以 '#' 字符结尾 
// 对于每个下标 indices[i] ，s 的一个从 indices[i] 开始、到下一个 '#' 字符结束（但不包括 '#'）的 子字符串 恰好与 wor
//ds[i] 相等 
// 
//
// 给你一个单词数组 words ，返回成功对 words 进行编码的最小助记字符串 s 的长度 。 
//
// 
//
// 示例 1： 
//
// 
//输入：words = ["time", "me", "bell"]
//输出：10
//解释：一组有效编码为 s = "time#bell#" 和 indices = [0, 2, 5] 。
//words[0] = "time" ，s 开始于 indices[0] = 0 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
//words[1] = "me" ，s 开始于 indices[1] = 2 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
//words[2] = "bell" ，s 开始于 indices[2] = 5 到下一个 '#' 结束的子字符串，如加粗部分所示 "time#bell#"
// 
//
// 示例 2： 
//
// 
//输入：words = ["t"]
//输出：2
//解释：一组有效编码为 s = "t#" 和 indices = [0] 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 2000 
// 1 <= words[i].length <= 7 
// words[i] 仅由小写字母组成 
// 
// Related Topics 字典树 数组 哈希表 字符串 
// 👍 268 👎 0


package leetcode.editor.cn;

import java.time.Instant;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class TreeNode{
        TreeNode[] next;
        boolean isEnd;
        TreeNode(){
            next = new TreeNode[26];
            isEnd = false;
        }
    }
    void insert(String word) {
        TreeNode node = root;
        char[] chs = word.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            int index = chs[chs.length-i-1] - 'a';
            if (node.next[index] == null) node.next[index] = new TreeNode();
            node = node.next[index];
        }
        node.isEnd = true;
    }
    boolean endWith(String word) {//判断word是否为某个单词的后缀
        TreeNode node = root;
        char[] chs = word.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            int index = chs[chs.length-i-1] - 'a';
            if (node.next[index] == null) return false;
            node = node.next[index];
        }
        return true;
    }
    TreeNode root = new TreeNode();
    public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, new Comparator<String>(){
            public int compare(String str1, String str2) {
                return str2.length()-str1.length();
            }
        });
        int ans = 0;
        for (String word : words) {
            if (!endWith(word)) {//word不是任何单词的后缀
                ans += word.length()+1;
                insert(word);
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


