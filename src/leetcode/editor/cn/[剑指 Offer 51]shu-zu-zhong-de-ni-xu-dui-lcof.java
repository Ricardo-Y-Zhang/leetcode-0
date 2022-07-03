//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。 
//
// 
//
// 示例 1: 
//
// 输入: [7,5,6,4]
//输出: 5 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 50000 
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 
// 👍 579 👎 0


package leetcode.editor.cn;


//leetcode submit region begin(Prohibit modification and deletion)
/*
class Solution {
    int[] temp;
    int res = 0;
    public int reversePairs(int[] nums) {
        temp = new int[nums.length];
        sort(nums, 0, nums.length-1);
        return res;
    }
    public void sort(int[] nums, int left, int right){
        if (left >= right){
            return;
        }
        int mid = left +(right-left)/2;
        sort(nums, left, mid);
        sort(nums, mid+1, right);
        int i = left, j = mid + 1, index = left;
        while (i <= mid && j <= right){
            if (nums[i] <= nums[j]){
                temp[index++]=nums[i++];
            }else{
                res += mid-i+1;
                temp[index++]=nums[j++];
            }
        }
        while (i <= mid){
            temp[index++]=nums[i++];
        }
        while (j <= right){
            temp[index++]=nums[j++];
        }
        for (int k = left; k <= right; k++) {
            nums[k]=temp[k];
        }
    }
}

 */


import java.util.Arrays;
import java.util.HashMap;

class Solution {
    int[] tree;//树状数组，原数组中d[i]表示i出现的次数
    int lowbit(int x) {
        return x&-x;
    }
    void add(int x) {
        for(int i = x+1; i < tree.length; i+=lowbit(i)) {
            tree[i]++;
        }
    }
    int getSum(int x) {
        int res = 0;
        for (int i = x+1; i > 0; i-=lowbit(i)) {
            res += tree[i];
        }
        return res;
    }
    public int reversePairs(int[] nums) {
        int n = nums.length;
        tree = new int[n+1];

        //离散化为 0,1,2,3....
        int[] sortnums = Arrays.copyOf(nums, n);
        Arrays.sort(sortnums);//nums数组排序
        HashMap<Integer, Integer> map = new HashMap<>();
        int temp = 0;
        for(int i = 0; i < sortnums.length; i++) {
            if (!map.containsKey(sortnums[i])) {
                map.put(sortnums[i], temp++);
            }
        }
        int res = 0;
        for(int i = n-1; i>=0; i--) {
            int index = map.get(nums[i]);
            res += getSum(index-1);//找到比nums[i]小的元素数量
            add(index);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


