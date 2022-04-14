//设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单
//词存在于你构建的字典中。 
//
// 实现 MagicDictionary 类： 
//
// 
// MagicDictionary() 初始化对象 
// void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字
//符串互不相同 
// bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，使得
//所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。 
// 
//
// 
//
// 
// 
// 
// 示例： 
//
// 
//输入
//["MagicDictionary", "buildDict", "search", "search", "search", "search"]
//[[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
//输出
//[null, null, false, true, false, false]
//
//解释
//MagicDictionary magicDictionary = new MagicDictionary();
//magicDictionary.buildDict(["hello", "leetcode"]);
//magicDictionary.search("hello"); // 返回 False
//magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
//magicDictionary.search("hell"); // 返回 False
//magicDictionary.search("leetcoded"); // 返回 False
// 
//
// 
//
// 提示： 
//
// 
// 1 <= dictionary.length <= 100 
// 1 <= dictionary[i].length <= 100 
// dictionary[i] 仅由小写英文字母组成 
// dictionary 中的所有字符串 互不相同 
// 1 <= searchWord.length <= 100 
// searchWord 仅由小写英文字母组成 
// buildDict 仅在 search 之前调用一次 
// 最多调用 100 次 search 
// 
// 
// 
// 
// Related Topics 设计 字典树 哈希表 字符串 
// 👍 110 👎 0


package leetcode.editor.cn;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class MagicDictionary {
    HashSet<String> set;//记录dictionary中字符串
    HashMap<String, Integer> map;
    public MagicDictionary() {
        set = new HashSet<>();
        map = new HashMap<>();
    }
    public List<String> similarStr(String word) {//返回word替换一个字符构成的字符串集合
        List<String> ans = new ArrayList<>();
        char[] chs = word.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            char ch = chs[i];
            chs[i] = '*';//'*'作为万能符，表示当前字符可以替换为任意字符
            ans.add(new String(chs));
            chs[i] = ch;
        }
        return ans;
    }
    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            set.add(word);
            List<String> strs = similarStr(word);
            for (String str : strs) {
                map.put(str, map.getOrDefault(str, 0)+1);
            }
        }
    }

    public boolean search(String searchWord) {
        int tar = set.contains(searchWord) ? 1 : 0;//若searchWord存在于dictionary中，取1
        for (String word : similarStr(searchWord)) {
            if (map.getOrDefault(word, 0)-tar > 0) {
                return true;
            }
        }
        return false;
    }
}
/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dictionary);
 * boolean param_2 = obj.search(searchWord);
 */
//leetcode submit region end(Prohibit modification and deletion)
public class MagicDictionary {
    Set<String> words;//不同的字符串
    Map<String, Integer> count;//字符串及其个数的映射

    public MagicDictionary() {
        words = new HashSet();
        count = new HashMap();
    }

    private ArrayList<String> generalizedNeighbors(String word) {//返回word替换一个字母所有构成的字符串集合
        ArrayList<String> ans = new ArrayList();
        char[] ca = word.toCharArray();
        for (int i = 0; i < word.length(); ++i) {
            char letter = ca[i];
            ca[i] = '*';//将当前字符替换
            String magic = new String(ca);
            ans.add(magic);
            ca[i] = letter;
        }
        return ans;
    }

    public void buildDict(String[] words) {
        for (String word: words) {
            this.words.add(word);
            for (String nei: generalizedNeighbors(word)) {
                count.put(nei, count.getOrDefault(nei, 0) + 1);
            }
        }
    }

    public boolean search(String word) {
        for (String nei: generalizedNeighbors(word)) {
            int c = count.getOrDefault(nei, 0);
            if (c > 1 || c == 1 && !words.contains(word)) return true;
        }
        return false;
    }
}



