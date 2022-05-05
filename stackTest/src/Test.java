import com.sun.source.tree.Scope;

import java.util.*;

/**
 * @program: leetcode-0
 * @description:
 * @author: Mr.Yan
 * @create: 2021-08-30 11:27
 **/
public class Test {

    @org.junit.Test
    public void test(){
        double feiM = 8.06, yuanM = 10.4, yanM = 11.20;
        double sumM = feiM + yuanM + yanM;
        double sumZ = 12460 + 13350 + 13350;
        double feiZ = feiM/sumM * sumZ;
        double yuanZ = yuanM/sumM * sumZ;
        double yanZ = yanM/sumM * sumZ;
        System.out.println("feiZ = " + feiZ);
        System.out.println("yuanZ = " + yuanZ);
        System.out.println("yanZ = " + yanZ);

        double avg = sumZ/3;
        double feiGet = avg - feiZ;
        double yuanGet = avg - yuanZ;
        double yanGet = avg - yanZ;
        System.out.println("feiGet = " + feiGet);
        System.out.println("yuanGet = " + yuanGet);
        System.out.println("yanGet = " + yanGet);

        double water = 14.5, elec = 44.52, qi = 15.19, room = 75;
        yanGet -= elec;
        yuanGet += (elec)/2;
        feiGet += (elec)/2;

        System.out.println("feiGet = " + feiGet);
        System.out.println("yuanGet = " + yuanGet);
        System.out.println("yanGet = " + yanGet);


        double allget = 6525.79/3;
        double yan = allget + yanGet;
        double fei = allget + feiGet;
        double yuan = allget + yuanGet;
        System.out.println("yan = " + yan);
        System.out.println("fei = " + fei);
        System.out.println("yuan = " + yuan);

        System.out.println("(yan+fei+yuan) = " + (yan + fei + yuan));

        double allPay = (water+qi+room)/3;
//        feiGet-=allPay;
//        yuanGet -= allPay;
//        yanGet -= allPay;
//
//        System.out.println("feiGet = " + feiGet);
//        System.out.println("yuanGet = " + yuanGet);
//        System.out.println("yanGet = " + yanGet);
//

    }

    @org.junit.Test
    public void test1(){
        String[] temp = {"CHIR",
                "TTNPB",
                "SAG",
                "ABT",
                "JNKin",
                "5-aza",
                "UNC",
                "Ruxo",
                "SGC",
                "LiCl",
                "Vc",
                "NAM",
                "VTP",
                "DZnep",
                "Doramap",
                "Dorso",
                "bFGF"};
        for (int i = 0; i < 17; i++) {
            for (int j = i+1; j < 17; j++) {
                System.out.println(temp[i] + "，" + temp[j]);
            }
        }
        long res = 1;
        for (int i = 0; i < 17; i++) {
            res *= i+1;
        }
        res /= 2;
        for (int i = 0; i < 15; i++) {
            res /= i+1;
        }
        System.out.println("res = " + res);
        return;
    }


    @org.junit.Test
    public void test3(){
        int res = -5;
        res >>>= 2;
        System.out.println("res" + res);
    }
    @org.junit.Test
    public void test4() {
        Stack<Integer> temp = new Stack<>();
        System.out.println("temp.getClass() = " + temp.getClass());
        System.out.println("temp.toString() = " + temp.toString());
    }
}



class Solution {
    public boolean placeWordInCrossword(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            boolean flag = find(board[i], word);
            if (flag){
                return true;
            }

            char[] temp = new char[board[i].length];
            for (int j = 0; j < temp.length; j++) {
                temp[j] = board[i][temp.length-1-j];
            }
            flag = find(temp, word);
            if (flag){
                return true;
            }


        }

        for (int i = 0; i < board[0].length; i++) {
            char[] temp = new char[board.length];
            for (int j = 0; j < temp.length; j++) {
                temp[j] = board[j][i];
            }
            boolean flag = find(temp, word);
            if (flag){
                return true;
            }

            char[] temp1 = new char[board.length];
            for (int j = 0; j < temp.length; j++) {
                temp1[j] = temp[board.length-j-1];
            }
            flag = find(temp1, word);
            if (flag){
                return true;
            }

        }

        return false;
    }

    public boolean find(char[] temp, String word){
        for (int i = 0; i < temp.length-word.length()+1; i++) {
            if (i == 0 || temp[i-1] == '#'){
                for (int j = 0; j < word.length(); j++) {
                    if (temp[i+j] == word.charAt(j) || temp[i+j] == ' '){
                        if (j == word.length()-1){
                            if (i+j == temp.length-1 || temp[i+j+1] == '#'){
                                return true;
                            }
                        }
                    }else{
                        break;
                    }
                }
            }
        }

        return false;
    }
}


//class Solution {
//
//    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
//        HashSet<Integer> set = new HashSet<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] == key) {
//                int left = i-k, right = i+k;
//                left = left >= 0 ? left : 0;
//                right = right < nums.length ? right : nums.length-1;
//                for (int j = left; j <= right; j++) {
//                    set.add(j);
//                }
//            }
//        }
//        ArrayList<Integer> ans = new ArrayList<>(set);
//        Collections.sort(ans);
//        return ans;
//    }
//}
//
//
//class Solution {
//    public int digArtifacts(int n, int[][] artifacts, int[][] dig) {
//        boolean[][] matrix = new boolean[n][n];
//        for (int[] temp : dig){
//            matrix[temp[0]][temp[1]] = true;//代表已挖掘
//        }
//        int ans = 0;
//        for (int[] artifact : artifacts) {
//            int r1 = artifact[0], c1 = artifact[1], r2  = artifact[2], c2 = artifact[3];
//            boolean flag = true;
//            table : for (int i = r1; i <= r2; i++) {
//                for (int j = c1; j <= c2; j++) {
//                    if (!matrix[i][j]) {
//                        flag = false;
//                        break table;
//                    }
//                }
//            }
//            if (flag) {
//                ans++;
//            }
//        }
//        return ans;
//    }
//}
//
//class Solution {
//    public int maximumTop(int[] nums, int k) {
//        int n = nums.length;
//        if (n == 1 && k % 2 ==1){
//            return -1;
//        }
//        if (k == 0) {
//            return nums[0];
//        }
//        if (k == 1) {
//            return nums[1];
//        }
//        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return Integer.compare(o2, o1);
//            }
//        });
//        for (int i = 0; i < n && i < k-1; i++) {
//            queue.add(nums[i]);
//        }
//        int max = queue.peek();
//        if (k < n){
//            max =  Math.max(max, nums[k]);
//        }
//        return max;
//    }
//}
//
//class Solution {
//    int[][] matrix;
//    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
//        matrix = new int[n][n];
//        for (int i = 0; i < n; i++) {
//            Arrays.fill(matrix[i], Integer.MAX_VALUE);
//        }
//        for (int[] edge : edges) {
//            int from = edge[0], to = edge[1], weight = edge[2];
//            matrix[from][to] = weight;
//        }
//
//    }
//    public long find(int src, int dest) {
//
//    }
//}

class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>(), set2 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> temp1 = new ArrayList<>();
        for (int num : set1) {
            if (!set2.contains(num)) {
                temp1.add(num);
            }
        }
        ans.add(temp1);
        List<Integer> temp2 = new ArrayList<>();
        for (int num : set2) {
            if (!set1.contains(num)) {
                temp2.add(num);
            }
        }
        ans.add(temp2);
        return ans;
    }
}

class Solution {
    public int minDeletion(int[] nums) {
        int ans = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i+1]) {
                ans++;
            }else{
                i++;
            }
        }
        int n = nums.length;
        if ((n-ans)%2==1){
            ans++;
        }
        return ans;
    }
}

class Solution {
    public long[] kthPalindrome(int[] queries, int intLength) {
        if ()
    }
    public long getnum(int length, int num) {
        if (length == 1) {
            return num-1;
        }

    }
    public String get(int length, int num) {
        if (length  == 1){
            if (num <= 10)
            return String.valueOf(num);
        }
        String temp;
        if (length%2 == 0) {
            String pre = get(length-1, num);
            String half = pre.substring(0, (pre.length()+1)/2);
            StringBuilder sb = new StringBuilder(half);
            temp = half + sb.reverse();
        }else {
            String pre = get(length-1, (int)Math.ceil((double)num/10));
            String half = pre.substring(0, (pre.length()+1)/2);
            StringBuilder sb = new StringBuilder(half);
            temp = half + (num-1) + sb.reverse();
        }
        return temp;
    }

    public String convert(String str) {
        StringBuilder sb = new StringBuilder(str);
        return str + sb.reverse().toString();
    }
}

class Solution {
    public int strongPasswordChecker(String password) {
        char[] cs = password.toCharArray();
        int n = cs.length;
        int A = 0, B = 0, C = 0;
        for (char c : cs) {
            if (c >= 'a' && c <= 'z') A = 1;
            else if (c >= '0' && c <= '9') B = 1;
            else if (c >= 'A' && c <= 'Z') C = 1;
        }
        int m = A + B + C;
        if (n < 6) {
            return Math.max(6 - n, 3 - m);
        } else if (n <= 20) {
            int tot = 0;
            for (int i = 0; i < n; ) {
                int j = i;
                while (j < n && cs[j] == cs[i]) j++;
                int cnt = j - i;
                if (cnt >= 3) tot += cnt / 3;
                i = j;
            }
            return Math.max(tot, 3 - m);
        } else {
            int tot = 0;
            int[] cnts = new int[3];
            for (int i = 0; i < n; ) {
                int j = i;
                while (j < n && cs[j] == cs[i]) j++;
                int cnt = j - i;
                if (cnt >= 3) {
                    tot += cnt / 3; cnts[cnt % 3]++;
                }
                i = j;
            }
            int base = n - 20, cur = base;
            for (int i = 0; i < 3; i++) {
                if (i == 2) cnts[i] = tot;
                if (cnts[i] != 0 && cur != 0) {
                    int t = Math.min(cnts[i] * (i + 1), cur);
                    cur -= t; tot -= t / (i + 1);
                }
            }
            return base + Math.max(tot, 3 - m);
        }
    }
}
class Solution {
    HashMap<Integer, Integer> map = new HashMap<>();
    public long appealSum(String s) {
        HashSet<Character> set = new HashSet<>();
        char[] chs = s.toCharArray();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            set.add(chs[i]);
            map.put(i, set.size());
        }

    }

    public int judge(int left, int right) {
        return map.get(right)-map.get(left);
    }
}