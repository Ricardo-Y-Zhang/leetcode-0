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
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestConsecutive(int[] nums) {

        if (nums.length == 0){
            return 0;
        }

        UnionFind uf = new UnionFind(nums);

        for (int v : nums){
            uf.union(v, v+1);//结盟
        }

        //二次遍历，记录领队距离
        int max = 1;
        for (int v : nums){
            max = max > uf.find(v) - v + 1 ? max : (uf.find(v) - v +1);
        }

        return max;
    }

}

class UnionFind {
    private int count;
    private Map<Integer, Integer> parent;

    UnionFind(int[] arr){
        parent = new HashMap<Integer, Integer>();

        for (int v : arr){
            parent.put(v, v);
        }

        count = parent.size();//无用
    }

    void union(int p, int q){
        Integer rootP = find(p), rootQ = find(q);
        if (rootP == rootQ){
            return;
        }
        if (rootP == null || rootQ == null){
            return;
        }

        parent.put(rootP, rootQ);;

        count--;
    }

    Integer find(int p){
        if (!parent.containsKey(p)){
            return null;
        }

        int root = p;
        while (root != parent.get(root)){
            root = parent.get(root);
        }

        //路径压缩
        while (p != parent.get(p)){
            int curr = p;
            p = parent.get(p);
            parent.put(curr, root);
        }

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


