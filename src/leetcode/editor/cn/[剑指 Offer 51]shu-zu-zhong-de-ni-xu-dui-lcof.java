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
// 👍 559 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int reversePairs(int[] nums) {
        int[] temp = new int[nums.length];
        return sort(nums, 0, nums.length-1, temp);
    }

    public int sort(int[] arr, int left, int right, int[] temp){
        if (left >= right){//单个元素
            return 0;
        }
        int mid = (left+right)/2;
        int lres = sort(arr, left, mid, temp);//继续划分左半数组
        int rres = sort(arr, mid+1, right, temp);//划分右半数组

        //合并
        int index = left, i = left, j = mid+1, res = 0;
        while (i <= mid && j <= right){
            if (arr[i] <= arr[j]){
                temp[index++] = arr[i++];
            }else{
                temp[index++] = arr[j++];
                //记录逆序对
                res += mid-i+1;//mid-i+1即为左子数组当前剩余元素个数
            }
        }
        while (i <= mid){
            temp[index++] = arr[i++];
        }
        while (j <= right){
            temp[index++] = arr[j++];
        }

        //元素拷贝到原数组中
        while (left<=right){
            arr[left] = temp[left];
            left++;
        }

        return lres+rres+res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


