//输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。 
//
// 
//
// 参考以下这颗二叉搜索树： 
//
//      5
//    / \
//   2   6
//  / \
// 1   3 
//
// 示例 1： 
//
// 输入: [1,6,3,2,5]
//输出: false 
//
// 示例 2： 
//
// 输入: [1,3,2,6,5]
//输出: true 
//
// 
//
// 提示： 
//
// 
// 数组长度 <= 1000 
// 
// Related Topics 栈 树 二叉搜索树 递归 二叉树 单调栈 
// 👍 378 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        return judge(postorder, 0, postorder.length-1);
    }

    public boolean judge(int[] postorder, int left ,int right){
        if (left >= right){
            return true;
        }

        int root = postorder[right];//根节点


        int rightChild = left;//第一个比root大的节点位置
        for (rightChild = left; rightChild < right; rightChild++){
            if (postorder[rightChild] > root){
                break;
            }
        }

        for (int i = rightChild; i < right; i++) {//判断该节点后是否有比root小的节点
            if (postorder[i] < root){
                return false;
            }
        }

        boolean flag1 = judge(postorder, left, rightChild-1);
        boolean flag2 = judge(postorder, rightChild, right-1);
        return flag1 && flag2;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


