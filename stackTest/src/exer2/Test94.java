package exer2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @program: leetcode-1
 * @description:
 * @author: Mr.Yan
 * @create: 2021-07-29 10:48
 **/
public class Test94 {
}

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
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();

        while(root != null || !stack.isEmpty()){

            if (root != null){
                stack.add(root);
                root = root.left;
            }else {
                root = stack.pop();
                list.add(root.val);
                root = root.right;
            }
        }
        return list;
    }
}


//递归
//class Solution {
//    public List<Integer> inorderTraversal(TreeNode root) {
//        ArrayList<Integer> list = new ArrayList<>();
//
//        inorder(list, root);
//        return list;
//    }
//
//    public void inorder(List<Integer> list, TreeNode root){
//        if (root != null){
//            inorder(list, root.left);
//            list.add(root.val);
//            inorder(list, root.right);
//        }
//    }
//}