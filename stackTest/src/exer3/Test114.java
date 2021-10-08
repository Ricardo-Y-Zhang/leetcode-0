package exer3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @program: leetcode-1
 * @description:
 * @author: Mr.Yan
 * @create: 2021-07-29 14:48
 **/
public class Test114 {

    @Test
    public void test(){
        System.out.println("6ABF6D0BEDA15529AA231F21EA6F207100000000000000000301310000000000000000000000000000000000000000000000000000000000000000000000000000000001010000000000000040420F000000000000000000000000000358555300E87648170000000400204EE0B16AFD3E3353475EE9B8296754ABB49A895245B4EF916A9BDEF458AEC3C9400E2E60C352CA61F88694CD23244C2EC8F2A0896B3F190B5D937089E731C06A57F71256F57F409E10275CFD9C946C1F0C539974E507F34208D9C574F6080A630E".length());

        System.out.println("634A19B04E6EBB9F3EFE727D57BCA000000000000000000003013102000000000000000200000000000000020000000000000002000000000000000C0000000000000000010000000000000040420F000000000000000000000000000358555300E8764817000000040020B4BF7D2153B1938ACF8A2E247EB12A1CC82FDD21883597C4A54D440EAB0926C0408C9BD4934CFDB5B95A24A750681F11E92DFEFAA90D63BF0AAEB2F6B7CD31DF5E0EA9AA973C96556500E0A6EEE0A56859A18CA02EF60EBBC60BFC19B8A5BF1E0D".length());


        String str = "lkdsjfal";

        str.indexOf()
    }
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