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
//leetcode submit region end(Prohibit modification and deletion)


