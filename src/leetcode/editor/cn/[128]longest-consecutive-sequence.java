//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。 
//
// 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。 
//
// 示例 2： 
//
// 
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// 
// Related Topics 并查集 数组 哈希表 
// 👍 878 👎 0


package leetcode.editor.cn;

import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    HashMap<Integer, Integer> parent = new HashMap<>();
    public int findFa(int x){
        if (x != parent.get(x)){
            parent.put(x, findFa(parent.get(x)));
        }
        return parent.get(x);
    }
    public void union(int x, int y) {
        int fa1 = findFa(x), fa2 = findFa(y);
        if (fa1 != fa2) {
            parent.put(fa1, fa2);
        }
    }
    public int longestConsecutive(int[] nums) {
       for (int num : nums) {
           parent.put(num, num);//初始化并查集
       }

       for (int num : nums) {
           if (parent.containsKey(num+1)) {
               union(num, num+1);
           }
       }
       int ans = 0;
       for (int num : nums) {
           int fa = findFa(num);
           ans = Math.max(ans,  fa-num+1);
       }
       return ans;
    }

}



//leetcode submit region end(Prohibit modification and deletion)




