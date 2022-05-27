//有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。如果寻找过程在这个文件中会重复多次，而每次寻找的单词
//不同，你能对此优化吗? 
//
// 示例： 
//
// 
//输入：words = ["I","am","a","student","from","a","university","in","a","city"], 
//word1 = "a", word2 = "student"
//输出：1 
//
// 提示： 
//
// 
// words.length <= 100000 
// 
// Related Topics 数组 字符串 👍 57 👎 0




//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findClosest(String[] words, String word1, String word2) {
        if (word1.equals(word2)) return 0;
        int pos1 = -1, pos2 = -1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) pos1 = i;
            if (words[i].equals(word2)) pos2 = i;
            if (pos1!=-1&&pos2!=-1) {
                ans = Math.min(ans,  Math.abs(pos1-pos2));
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
