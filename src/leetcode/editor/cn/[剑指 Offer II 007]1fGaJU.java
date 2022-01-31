//给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a ，b ，c ，使得 a + b + c = 0 ？请找出所有和为 0 且 
//不重复 的三元组。 
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
//
// 
//
// 注意：本题与主站 15 题相同：https://leetcode-cn.com/problems/3sum/ 
// Related Topics 数组 双指针 排序 
// 👍 31 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length-2; i++){
            //去重
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int m = i+1, n = nums.length-1;
            while (m<n){
                if (nums[m]+nums[n]==-nums[i]){
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[m]);
                    list.add(nums[n]);
                    res.add(list);
                    m++;
                    while (m < n && nums[m]==nums[m-1]){//去重
                        m++;
                    }
                }else if (nums[m]+nums[n]<-nums[i]){
                    m++;
                    while (m < n && nums[m]==nums[m-1]){//去重
                        m++;
                    }
                }else{
                    n--;
                    while (n > m && nums[n] == nums[n+1]){//去重
                        n--;
                    }
                }
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


