//输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。 
//
// 
//
// 为了让您更好地理解问题，以下面的二叉搜索树为例： 
//
// 
//
// 
//
// 
//
// 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是
//第一个节点。 
//
// 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。 
//
// 
//
// 
//
// 
//
// 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。 
//
// 
//
// 注意：本题与主站 426 题相同：https://leetcode-cn.com/problems/convert-binary-search-tree-
//to-sorted-doubly-linked-list/ 
//
// 注意：此题对比原题有改动。 
// Related Topics 栈 树 深度优先搜索 二叉搜索树 链表 二叉树 双向链表 
// 👍 360 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
//    ArrayList<Node> list;
//    public Node treeToDoublyList(Node root) {
//        if (root == null){
//            return root;
//        }
//        list = new ArrayList<Node>();
//        inorder(root);
//        Node head = list.get(0), tail = list.get(list.size()-1);
//        head.left = tail;
//        tail.right = head;
//        return head;
//    }
//    public void inorder(Node root) {
//        if (root == null){
//            return;
//        }
//        inorder(root.left);
//        if (list.size()!=0){
//            Node tail = list.get(list.size()-1);
//            tail.right=root;
//            root.left=tail;
//        }
//        list.add(root);
//        inorder(root.right);
//    }
    Node pre = null, head = null,tail = null;
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        inorder(root);
        head.left = tail;
        tail.right = head;
        return head;
    }
    public void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        if (head == null) head = root;
        if (pre != null) pre.right = root;
        root.left = pre;
        pre = root;
        tail = root;
        inorder(root.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

class Solution {
    public int longestSubsequence(String s, int k) {
        int n = s.length();
        int m = n+1;
        int[][] dp = new int[n+1][m];
        int ans = 0;
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0; i < n+1; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i < n+1; i++) {
            for (int j = 1; j < m; j++) {
                if (dp[i-1][j] != -1) dp[i][j] = dp[i-1][j];
                if(dp[i-1][j-1] != -1) {
                    int temp = (dp[i-1][j-1] << 1) +(s.charAt(i-1) == '1' ? 1 : 0);
                    if (temp <= k) {
                        if (dp[i][j] == -1) dp[i][j] = temp;
                        else dp[i][j] = Math.min(dp[i][j], temp);
                    }
                }
                if (dp[i][j] != -1 && dp[i][j] <= k && j > ans) ans = j;
            }
        }
        return ans;
    }
}
