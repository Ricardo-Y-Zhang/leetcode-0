//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 
//输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
//
// 
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
// Related Topics 数组 二分查找 分治 
// 👍 4372 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //找到nums1和nums2中第(nums1.length+nums2.length)/2个数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int leng= nums1.length + nums2.length;
        int k = leng/2;
        if (leng%2==1){
            return (double)find(nums1, nums2, 0, nums1.length-1, 0, nums2.length-1, k+1);
        }
        double sum = find(nums1, nums2, 0, nums1.length-1, 0, nums2.length-1, k)+find(nums1, nums2, 0, nums1.length-1, 0, nums2.length-1, k+1);
        return sum/2;
    }
    public int find(int[] nums1, int[] nums2, int start1, int end1, int start2, int end2, int k){//找到 nums1 和 nums2中第 k 小的数
        if (start1 > end1){//nums1的子数组为空
            return nums2[start2+k-1];
        }
        if (start2 > end2){//nums2的子数组为空
            return nums1[start1+k-1];
        }
        if (k == 1){//递归边界
            return nums1[start1] <= nums2[start2] ? nums1[start1] : nums2[start2];
        }
        int mid1 = start1+k/2-1, mid2 = start2+k/2-1;//mid1，mid2表示nums1和nums2子数组中第 k/2 个数下标
        if (mid1 > end1){//mid1超过数组边界
            mid1 = end1;
        }
        if (mid2 > end2){//mid2超过数组边界
            mid2 = end2;
        }
        if (nums1[mid1] <= nums2[mid2]){//排除nums1中[start1,mid1]
            return find(nums1, nums2, mid1+1, end1, start2, end2, k-(mid1-start1+1));
        }
        //排除nums2中[start2,mid2]
        return find(nums1, nums2, start1, end1, mid2+1, end2, k-(mid2-start2+1));
    }

}
//leetcode submit region end(Prohibit modification and deletion)


