//给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。 
//
// “最近的”定义为两个整数差的绝对值最小。 
//
// 
//
// 示例 1: 
//
// 
//输入: n = "123"
//输出: "121"
// 
//
// 示例 2: 
//
// 
//输入: n = "1"
//输出: "0"
//解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= n.length <= 18 
// n 只由数字组成 
// n 不含前导 0 
// n 代表在 [1, 1018 - 1] 范围内的整数 
// 
// Related Topics 数学 字符串 
// 👍 174 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
//取字符串的前半部分half
//分别将 half-1，half，half+1 作为回文串的前半部分，组成回文串
//选出其中离 n 最近且最小的回文串
//注意分别考虑字符串 n 的长度为偶数和奇数的情况
//分开判断 half%10 == 0 和 half+1 %10 == 0 的情况，也可将"99...99" 和 "100...001"加入结果判断
class Solution {
    public String nearestPalindromic(String n) {
        long x = Long.parseLong(n);
        if (x >= 1 && x <= 10){
            return String.valueOf(x-1);
        }
        if (x == 11){
            return String.valueOf(9);
        }
        if (x <= 16){
            return String.valueOf(11);
        }
        if (x <= 19){
            return String.valueOf(22);
        }
        int length = n.length();
        String str = "";
        if (length % 2 == 1){
            str = n.substring(0, length/2+1);
        }else{
            str = n.substring(0, length/2);
        }
        long half = Long.parseLong(str);
        String str1 , str2, str3;
        if (length%2==1){
            if (half%10 == 0){//"99..99"
                str1 = String.valueOf(half-1) + new StringBuilder(String.valueOf((half-1))).reverse().toString();
            }else{
                str1 = String.valueOf(half-1) + new StringBuilder(String.valueOf((half-1)/10)).reverse().toString();
            }
            str2 = str + new StringBuilder(String.valueOf(half/10)).reverse().toString();
            if ((half+1)%10 == 0){//"100..001"
                str3 = String.valueOf(half+1) + new StringBuilder(String.valueOf((half+1)/100)).reverse().toString();
            }else{
                str3 = String.valueOf(half+1) + new StringBuilder(String.valueOf((half+1)/10)).reverse().toString();
            }
        }else{
            if (half%10 == 0){
                str1 = String.valueOf(half-1) +"9"+ new StringBuilder(String.valueOf(half-1)).reverse().toString();
            }else{
                str1 = String.valueOf(half-1) + new StringBuilder(String.valueOf(half-1)).reverse().toString();
            }
            str2 = str + new StringBuilder(str).reverse().toString();
            if ((half+1)%10 == 0){
                str3 = String.valueOf(half+1) + new StringBuilder(String.valueOf((half+1)/10)).reverse().toString();
            }else{
                str3 = String.valueOf(half+1) + new StringBuilder(String.valueOf(half+1)).reverse().toString();
            }
        }
        return get(n, str1, str2, str3);
    }
    public String get(String str, String str1, String str2, String str3){
        Long a = Long.parseLong(str), b = Long.parseLong(str1), c = Long.parseLong(str2), d = Long.parseLong(str3);
        long res = 0;
        if (Math.abs(a-b) <= Math.abs(a-d)){
            res = b;
        }else{
            res = d;
        }
        if (str.equals(str2)){
            return String.valueOf(res);
        }
        if (Math.abs(a-c) < Math.abs(a-res)){
            res = c;
        }else if (Math.abs(a-c) == Math.abs(a-res)){
            res = Math.min(res, c);
        }
        return String.valueOf(res);
    }

}
//leetcode submit region end(Prohibit modification and deletion)


