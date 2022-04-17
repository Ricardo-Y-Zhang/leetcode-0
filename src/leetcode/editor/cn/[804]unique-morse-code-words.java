//国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串， 比如: 
//
// 
// 'a' 对应 ".-" ， 
// 'b' 对应 "-..." ， 
// 'c' 对应 "-.-." ，以此类推。 
// 
//
// 为了方便，所有 26 个英文字母的摩尔斯密码表如下： 
//
// 
//[".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--
//","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.
//."] 
//
// 给你一个字符串数组 words ，每个单词可以写成每个字母对应摩尔斯密码的组合。 
//
// 
// 例如，"cab" 可以写成 "-.-..--..." ，(即 "-.-." + ".-" + "-..." 字符串的结合)。我们将这样一个连接过程称作 单
//词翻译 。 
// 
//
// 对 words 中所有单词进行单词翻译，返回不同 单词翻译 的数量。 
//
// 
//
// 示例 1： 
//
// 
//输入: words = ["gin", "zen", "gig", "msg"]
//输出: 2
//解释: 
//各单词翻译如下:
//"gin" -> "--...-."
//"zen" -> "--...-."
//"gig" -> "--...--."
//"msg" -> "--...--."
//
//共有 2 种不同翻译, "--...-." 和 "--...--.".
// 
//
// 示例 2： 
//
// 
//输入：words = ["a"]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 100 
// 1 <= words[i].length <= 12 
// words[i] 由小写英文字母组成 
// 
// Related Topics 数组 哈希表 字符串 👍 190 👎 0



import java.util.*;


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] alp = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        HashSet<String> set = new HashSet<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            char[] chs = word.toCharArray();
            for (char ch : chs){
                sb.append(alp[ch-'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    List<Integer> list = new ArrayList<>();
    HashMap<Integer, Integer> map = new HashMap<>();
    public int getNumber(TreeNode root, int[][] ops) {
        dfs(root);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, map.size()-1);
        
    }
    public void dfs(TreeNode root) {
        if (root == null) return;
        if (root.left!=null) dfs(root.left);
        map.put(root.val, list.size());
        list.add(0);
        if (root.right != null) dfs(root.right);
    }
}