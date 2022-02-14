//单词数组 words 的 有效编码 由任意助记字符串 s 和下标数组 indices 组成，且满足： 
//
// 
// words.length == indices.length 
// 助记字符串 s 以 '#' 字符结尾 
// 对于每个下标 indices[i] ，s 的一个从 indices[i] 开始、到下一个 '#' 字符结束（但不包括 '#'）的 子字符串 恰好与 wor
//ds[i] 相等 
// 
//
// 给定一个单词数组 words ，返回成功对 words 进行编码的最小助记字符串 s 的长度 。 
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
//
// 
//
// 注意：本题与主站 820 题相同： https://leetcode-cn.com/problems/short-encoding-of-words/ 
// Related Topics 字典树 数组 哈希表 字符串 
// 👍 9 👎 0


package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minimumLengthEncoding(String[] words) {
        //字符串按长度降序排序
        Arrays.sort(words, new Comparator<String>(){
            @Override
            public int compare(String str1,String str2){
                return str2.length()-str1.length();
            }
        });
        Trie tree = new Trie();
        int res = 0;
        for (String str : words){
            if (!tree.endWith(str)){//是否存在以 str 为后缀的单词
                res += str.length()+1;
            }
            tree.insert(str);//将 str 插入字典树
        }
        return res;
    }
    class Trie {
        class TreeNode{
            boolean isEnd;
            TreeNode[] next;
            TreeNode(){
                isEnd = false;
                next = new TreeNode[26];
            }
        }
        TreeNode root;
        public Trie(){
            root = new TreeNode();
        }
        public void insert(String word){
            TreeNode node = root;
            int len = word.length();
            for (int i = 0; i < len; i++) {//倒序插入单词
                int index = word.charAt(len-1-i)-'a';
                if (node.next[index] == null){
                    node.next[index] = new TreeNode();
                }
                node = node.next[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word){
            TreeNode node = root;
            int len = word.length();
            for (int i = 0; i < len; i++) {
                int index = word.charAt(len-1-i)-'a';
                if (node.next[index] == null){
                    return false;
                }
                node = node.next[index];
            }
            return node.isEnd;
        }

        public boolean endWith(String prefix){//是否存在以 prefix 为后缀的单词
            TreeNode node = root;
            int len = prefix.length();
            for (int i = 0; i < len; i++) {
                int index = prefix.charAt(len-1-i)-'a';
                if (node.next[index] == null){
                    return false;
                }
                node = node.next[index];
            }
            return true;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


