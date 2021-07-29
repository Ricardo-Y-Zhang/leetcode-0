package exer3;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @program: leetcode-1
 * @description:
 * @author: Mr.Yan
 * @create: 2021-07-29 14:48
 **/
public class Test114 {
}

/*
给你二叉树的根结点 root ，请你将它展开为一个单链表：

展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
展开后的单链表应该与二叉树 先序遍历 顺序相同。

 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

//迭代
class Solution {
    public void flatten(TreeNode root) {
        ArrayList<TreeNode> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        var temp = root;
        while(temp != null || !stack.isEmpty()){
            if (temp != null) {
                list.add(temp);
                stack.add(temp);
                temp = temp.left;
            }else{
                temp = stack.pop();
                temp = temp.right;
            }
        }

        var tail = root;
        for (int i = 1; i < list.size(); i++) {
            tail.left = null;
            tail.right = list.get(i);
            tail = tail.right;
        }
    }
}


//递归
//class Solution{
//    public void flatten(TreeNode root){
//        ArrayList<TreeNode> list = new ArrayList<>();
//        preorder(list, root);
//        var tail = root;
//        for (int i = 1; i < list.size(); i++) {
//            tail.right = list.get(i);
//            tail.left = null;
//            tail = tail.right;
//        }
//    }
//
//    private void preorder(ArrayList<TreeNode> list, TreeNode root){
//        if (root != null) {
//            list.add(root);
//            preorder(list, root.left);
//            preorder(list, root.right);
//        }
//    }
//}