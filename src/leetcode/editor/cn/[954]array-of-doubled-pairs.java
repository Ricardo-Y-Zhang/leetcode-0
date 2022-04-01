//给定一个长度为偶数的整数数组 arr，只有对 arr 进行重组后可以满足 “对于每个 0 <= i < len(arr) / 2，都有 arr[2 * i 
//+ 1] = 2 * arr[2 * i]” 时，返回 true；否则，返回 false。 
//
// 
//
// 示例 1： 
//
// 
//输入：arr = [3,1,3,6]
//输出：false
// 
//
// 示例 2： 
//
// 
//输入：arr = [2,1,2,6]
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：arr = [4,-2,2,-4]
//输出：true
//解释：可以用 [-2,-4] 和 [2,4] 这两组组成 [-2,-4,2,4] 或是 [2,4,-2,-4]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= arr.length <= 3 * 104 
// arr.length 是偶数 
// -105 <= arr[i] <= 105 
// 
// Related Topics 贪心 数组 哈希表 排序 
// 👍 121 👎 0


package leetcode.editor.cn;

import java.util.HashMap;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canReorderDoubled(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();//整数出现次数
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int temp : arr) {
            queue.add(temp);
            map.put(temp, map.getOrDefault(temp, 0)+1);
        }
        while (!queue.isEmpty()) {
            int first = queue.poll();
            if (map.get(first) == 0) {
                continue;
            }
            map.put(first, map.get(first)-1);
            int need;
            if (first < 0 ){
                if (first%2 != 0) {
                    return false;
                }
                need = first/2;

            }else{
                need = first * 2;
            }
            int count = map.getOrDefault(need, 0);
            if (count == 0) {
                return false;
            }
            map.put(need, count-1);
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


