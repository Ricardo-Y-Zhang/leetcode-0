//给你两个数组，arr1 和 arr2，arr2 中的元素各不相同，arr2 中的每个元素都出现在 arr1 中。 
//
// 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末
//尾。 
//
// 
//
// 示例 1： 
//
// 
//输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
// 
//
// 示例 2: 
//
// 
//输入：arr1 = [28,6,22,8,44,17], arr2 = [22,28,8,6]
//输出：[22,28,8,6,17,44]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= arr1.length, arr2.length <= 1000 
// 0 <= arr1[i], arr2[i] <= 1000 
// arr2 中的元素 arr2[i] 各不相同 
// arr2 中的每个元素 arr2[i] 都出现在 arr1 中 
// 
// Related Topics 数组 哈希表 计数排序 排序 
// 👍 205 👎 0


import java.security.Key;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            map.put(arr2[i], i);
        }
        Integer[] temparr = new Integer[arr1.length];
        for (int i = 0; i < temparr.length; i++) {
            temparr[i] = arr1[i];
        }
        Arrays.sort(temparr, new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
                if (map.containsKey(i1) && map.containsKey(i2)){
                    return Integer.compare(map.get(i1), map.get(i2));
                }else if (map.containsKey(i1)){
                    return -1;
                }else if (map.containsKey(i2)){
                    return 1;
                }
                return Integer.compare(i1, i2);
            }
        });
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = temparr[i];
        }
        return arr1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
