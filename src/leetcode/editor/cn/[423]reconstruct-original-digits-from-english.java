//给你一个字符串 s ，其中包含字母顺序打乱的用英文单词表示的若干数字（0-9）。按 升序 返回原始的数字。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "owoztneoer"
//输出："012"
// 
//
// 示例 2： 
//
// 
//输入：s = "fviefuro"
//输出："45"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 105 
// s[i] 为 ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"] 这些字符之一 
// s 保证是一个符合题目要求的字符串 
// 
// Related Topics 哈希表 数学 字符串 
// 👍 108 👎 0


package leetcode.editor.cn;

import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String originalDigits(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }
        int zero = map.getOrDefault('z', 0);
        if (zero > 0){
            map.put('o', map.get('o')-zero);
        }
        int four = map.getOrDefault('u', 0);
        if (four > 0){
            map.put('f', map.get('f')-four);
            map.put('o', map.get('o')-four);
        }
        int two = map.getOrDefault('w', 0);
        if (two > 0){
            map.put('t', map.get('t')-two);
            map.put('o', map.get('o')-two);
        }
        int eight = map.getOrDefault('g', 0);
        if (eight > 0){
            map.put('i', map.get('i')-eight);
            map.put('t', map.get('t')-eight);
        }
        int six = map.getOrDefault('x', 0);
        if (six > 0){
            map.put('i', map.get('i')-six);
            map.put('s', map.get('s')-six);
        }
        int five = map.getOrDefault('f', 0);
        if (five > 0){
            map.put('i', map.get('i')-five);
        }
        int nine = map.getOrDefault('i', 0);
        int three = map.getOrDefault('t', 0);
        int one = map.getOrDefault('o', 0);
        int seven = map.getOrDefault('s', 0);
        String res = "";
        for (int i = 0; i < zero; i++) {
            res += "0";
        }
        for (int i = 0; i < one; i++) {
            res += "1";
        }
        for (int i = 0; i < two; i++) {
            res += "2";
        }
        for (int i = 0; i < three; i++) {
            res += "3";
        }
        for (int i = 0; i < four; i++) {
            res += "4";
        }
        for (int i = 0; i < five; i++) {
            res += "5";
        }
        for (int i = 0; i < six; i++) {
            res += "6";
        }
        for (int i = 0; i < seven; i++) {
            res += "7";
        }
        for (int i = 0; i < eight; i++) {
            res += "8";
        }
        for (int i = 0; i < nine; i++) {
            res += "9";
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


