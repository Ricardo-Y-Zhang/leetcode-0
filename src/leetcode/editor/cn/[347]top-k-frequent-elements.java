//给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,1,1,2,2,3], k = 2
//输出: [1,2]
// 
//
// 示例 2: 
//
// 
//输入: nums = [1], k = 1
//输出: [1] 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// k 的取值范围是 [1, 数组中不相同的元素的个数] 
// 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的 
// 
//
// 
//
// 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。 
// Related Topics 数组 哈希表 分治 桶排序 计数 快速选择 排序 堆（优先队列） 
// 👍 851 👎 0


package leetcode.editor.cn;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();


        for (int i = 0; i < nums.length; i++) {
            int num = map.getOrDefault(nums[i], 0);
            num++;
            map.put(nums[i], num);
        }

        Set<Integer> keySet = map.keySet();

        ArrayList<Integer> times = new ArrayList<>();
        for (Integer key : keySet){
            times.add(map.get(key));
        }

        Collections.sort(times, new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return Integer.compare(t1, integer);
            }
        });

        //第k多的出现次数
        int ktime = times.get(k-1);

        int[] res = new int[k];

        int i = 0;

        for (Integer key : keySet) {
            int value = map.get(key);

            if (value >= ktime){
                res[i++] = key;
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


