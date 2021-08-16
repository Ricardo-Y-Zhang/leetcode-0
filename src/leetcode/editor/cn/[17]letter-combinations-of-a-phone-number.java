//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = "23"
//输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
// 
//
// 示例 2： 
//
// 
//输入：digits = ""
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：digits = "2"
//输出：["a","b","c"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= digits.length <= 4 
// digits[i] 是范围 ['2', '9'] 的一个数字。 
// 
// Related Topics 哈希表 字符串 回溯 
// 👍 1445 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> letterCombinations(String digits) {

        List<String> res = new ArrayList<String>();

        ArrayList<ArrayList<String>> list = new ArrayList<>();

        String[] str1 = {"a", "b", "c"};
        list.add(new ArrayList<String>(Arrays.asList(str1)));

        String[] str2 = {"d", "e", "f"};
        list.add(new ArrayList<String>(Arrays.asList(str2)));

        String[] str3 = {"g", "h", "i"};
        list.add(new ArrayList<String>(Arrays.asList(str3)));

        String[] str4 = {"j", "k", "l"};
        list.add(new ArrayList<String>(Arrays.asList(str4)));

        String[] str5 = {"m", "n", "o"};
        list.add(new ArrayList<String>(Arrays.asList(str5)));

        String[] str6 = {"p", "q", "r", "s"};
        list.add(new ArrayList<String>(Arrays.asList(str6)));

        String[] str7 = {"t", "u", "v"};
        list.add(new ArrayList<String>(Arrays.asList(str7)));

        String[] str8 = {"w", "x", "y", "z"};
        list.add(new ArrayList<String>(Arrays.asList(str8)));

        for (int i = 0; i < digits.length(); i++) {
            int number = Integer.parseInt(digits.substring(i, i+1));
            ArrayList<String> strings = list.get(number - 2);

            ArrayList<String> temp = new ArrayList<>();

            if (res.isEmpty()){
                temp.addAll(strings);
            }

            while (!res.isEmpty()){
                String str0 = res.get(res.size()-1);
                res.remove(res.size()-1);
                for (int j = 0; j < strings.size(); j++) {
                    temp.add(str0 + strings.get(j));
                }
            }

            res.addAll(temp);
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


