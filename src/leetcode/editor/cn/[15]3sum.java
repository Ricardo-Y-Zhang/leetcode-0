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
        ArrayList<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {

            //去重，不选择重复的a
            if (i != 0 && nums[i] == nums[i-1]){
                continue;
            }

            //第二轮和第三轮选择同时进行
            int j = i + 1, k = nums.length - 1;
            while (j < k){
                if (nums[i] + nums[j] + nums[k] == 0){
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    res.add(list);
                    j++;
                    k--;

                    //去重，不选择重复的b,c
                    while (j < k && nums[j] == nums[j-1]){
                        j++;
                    }

                    while (j < k && nums[k] == nums[k+1]){
                        k--;
                    }
                }else if (nums[i] + nums[j] + nums[k] > 0){
                    k--;
                }else if (nums[i] + nums[j] + nums[k] < 0){
                    j++;
                }
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


