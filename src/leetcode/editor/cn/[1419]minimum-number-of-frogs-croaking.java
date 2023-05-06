//给你一个字符串 croakOfFrogs，它表示不同青蛙发出的蛙鸣声（字符串 "croak" ）的组合。由于同一时间可以有多只青蛙呱呱作响，所以 
//croakOfFrogs 中会混合多个 “croak” 。 
//
// 请你返回模拟字符串中所有蛙鸣所需不同青蛙的最少数目。 
//
// 要想发出蛙鸣 "croak"，青蛙必须 依序 输出 ‘c’, ’r’, ’o’, ’a’, ’k’ 这 5 个字母。如果没有输出全部五个字母，那么它就不会
//发出声音。如果字符串 croakOfFrogs 不是由若干有效的 "croak" 字符混合而成，请返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//输入：croakOfFrogs = "croakcroak"
//输出：1 
//解释：一只青蛙 “呱呱” 两次
// 
//
// 示例 2： 
//
// 
//输入：croakOfFrogs = "crcoakroak"
//输出：2 
//解释：最少需要两只青蛙，“呱呱” 声用黑体标注
//第一只青蛙 "crcoakroak"
//第二只青蛙 "crcoakroak"
// 
//
// 示例 3： 
//
// 
//输入：croakOfFrogs = "croakcrook"
//输出：-1
//解释：给出的字符串不是 "croak" 的有效组合。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= croakOfFrogs.length <= 10⁵ 
// 字符串中的字符只有 'c', 'r', 'o', 'a' 或者 'k' 
// 
// Related Topics 字符串 计数 👍 129 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minNumberOfFrogs(String croakOfFrogs) {
        char[] chs = croakOfFrogs.toCharArray();
        if (chs.length % 5 != 0) return -1;
        HashMap<Character, Integer> mp = new HashMap<>();
        mp.put('c', 0);
        mp.put('r', 1);
        mp.put('o', 2);
        mp.put('a', 3);
        mp.put('k', 4);
        //count记录各字符出现次数
        int[] count = new int[5];
        //ans记录所需最多青蛙，frog记录当前所需青蛙数量
        int ans = 0, frog = 0;
        for (char ch : chs) {
            int ind = mp.get(ch);
            if (ind == 0) {//当前字符为 'c'
                count[ind]++;
                //每一个'c'需要一个新的青蛙蛙鸣
                frog++;
            }else {
                if (count[ind-1] <= count[ind]) {//前序字符出现次数小于当前字符出现次数
                    return -1;
                }
                if (ind == 4) {//当前字符为'k'，代表蛙鸣完毕，青蛙数量-1
                    frog--;
                }
                count[ind]++;
            }
            //更新最大青蛙数量
            ans = Math.max(ans, frog);
        }
        if (count[4] != croakOfFrogs.length()/5) {
            return -1;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
