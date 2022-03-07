//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 3000 
// -105 <= nums[i] <= 105 
// 
// Related Topics 数组 双指针 排序 
// 👍 3639 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
//class Solution {
//    public List<List<Integer>> threeSum(int[] nums) {
//
//        List<List<Integer>> res = new ArrayList<List<Integer>>();
//
//        Arrays.sort(nums);
//
//        int[] times = new int[200002];
//
//        for (int i = 0; i < nums.length; i++) {
//            times[nums[i] + 100000]++;
//        }
//
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] > 0){
//                break;
//            }
//
//            if (i != 0 && nums[i] == nums[i-1]){
//                continue;
//            }
//
//            for (int j = i + 1; j < nums.length; j++) {
//
//                if (j != i + 1 && nums[j] == nums[j-1]){
//                    continue;
//                }
//
//                int add = nums[i] + nums[j];
//
//                int index = -add + 100000;
//
//                if (add > 0 || -add < nums[j] || index < 0){
//                    break;
//                }
//
//                if (index > 200001){
//                    continue;
//                }
//
//                int time = times[index];
//
//                if (-add == nums[i]){
//                    time--;
//                }
//                if (-add == nums[j]){
//                    time--;
//                }
//
//                if (time > 0){
//                    ArrayList<Integer> list = new ArrayList<>();
//
//                    list.add(nums[i]);
//                    list.add(nums[j]);
//                    list.add(-add);
//                    res.add(list);
//                }
//            }
//        }
//
//        return res;
//    }
//}

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n-2; i++) {
            if (i != 0 && nums[i] == nums[i-1]){
                continue;
            }
            int j = i+1, k = n-1;
            while (j < k){
                if (nums[j]+nums[k] == -nums[i]){
                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    ans.add(temp);
                    while (j < k) {
                        j++;
                        if (nums[j] != nums[j-1]) break;
                    }
                    while (j < k) {
                        k--;
                        if (nums[k] != nums[k+1]) break;
                    }
                }else if (nums[j]+nums[k] < -nums[i]){
                    j++;
                }else{
                    k--;
                }
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


