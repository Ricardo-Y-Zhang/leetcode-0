//在字典（单词列表） wordList 中，从单词 beginWord 和 endWord 的 转换序列 是一个按下述规格形成的序列： 
//
// 
// 序列中第一个单词是 beginWord 。 
// 序列中最后一个单词是 endWord 。 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典 wordList 中的单词。 
// 
//
// 给定两个长度相同但内容不同的单词 beginWord 和 endWord 和一个字典 wordList ，找到从 beginWord 到 endWord 
//的 最短转换序列 中的 单词数目 。如果不存在这样的转换序列，返回 0。 
//
// 
//
// 示例 1： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g","cog"]
//输出：5
//解释：一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。
// 
//
// 示例 2： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g"]
//输出：0
//解释：endWord "cog" 不在字典中，所以无法进行转换。 
//
// 
//
// 提示： 
//
// 
// 1 <= beginWord.length <= 10 
// endWord.length == beginWord.length 
// 1 <= wordList.length <= 5000 
// wordList[i].length == beginWord.length 
// beginWord、endWord 和 wordList[i] 由小写英文字母组成 
// beginWord != endWord 
// wordList 中的所有字符串 互不相同 
// 
//
// 
//
// 注意：本题与主站 127 题相同： https://leetcode-cn.com/problems/word-ladder/ 
// Related Topics 广度优先搜索 哈希表 字符串 
// 👍 4 👎 0


package leetcode.editor.cn;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)){
            return 0;
        }
        int n = wordList.size();
        wordList.add(beginWord);
        wordList.add(endWord);
        boolean[][] matrix  = new boolean[n+2][n+2];
        for (int i = 0; i < n+2; i++) {
            for (int j = i+1; j < n+2; j++) {
                boolean flag = convert(wordList.get(i),wordList.get(j));
                matrix[i][j] = flag;
                matrix[j][i] = flag;
            }
        }
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int step = 1;
        HashSet<String> set = new HashSet<>();
        set.add(beginWord);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String first = queue.poll();
                if (first.equals(endWord)){
                    return step;
                }
                int index = wordList.indexOf(first);
                for (int j = 0; j < n+2; j++) {
                    String next = wordList.get(j);
                    if (!set.contains(next) && matrix[index][j] == true){
                        queue.add(next);
                        set.add(next);
                    }
                }
            }
            step++;
        }
        return 0;
    }

    public boolean convert(String str1, String str2){
        int diff = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)){
                if (diff != 0){
                    return false;
                }
                diff++;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


