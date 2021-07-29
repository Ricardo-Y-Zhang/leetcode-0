package exer1;

import java.util.HashMap;
import java.util.Stack;

/**
 * @program: leetcode-1
 * @description:
 * @author: Mr.Yan
 * @create: 2021-07-29 10:12
 **/
public class Test20 {
}
/*
给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。

 */

class Solution {
    public boolean isValid(String s) {
        //1. 创建hashMap
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        //2. 遍历字符串
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (map.containsKey(c)){
                if (stack.isEmpty() || stack.pop() != map.get(c)){
                    return false;
                }
            }else{
                stack.add(c);
            }
        }

        return stack.isEmpty();
    }
}