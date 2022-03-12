//给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。同时给你一个下标从 0 开始的整数数组 parents 表示这棵
//树，其中 parents[i] 是节点 i 的父节点。由于节点 0 是根，所以 parents[0] == -1 。 
//
// 一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。求出某个节点分数的方法是，将这个节点和与它相连的边全部 删除 ，剩余部分是若
//干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。 
//
// 请你返回有 最高得分 节点的 数目 。 
//
// 
//
// 示例 1: 
//
// 
//
// 输入：parents = [-1,2,0,2,0]
//输出：3
//解释：
//- 节点 0 的分数为：3 * 1 = 3
//- 节点 1 的分数为：4 = 4
//- 节点 2 的分数为：1 * 1 * 2 = 2
//- 节点 3 的分数为：4 = 4
//- 节点 4 的分数为：4 = 4
//最高得分为 4 ，有三个节点得分为 4 （分别是节点 1，3 和 4 ）。
// 
//
// 示例 2： 
//
// 
//
// 输入：parents = [-1,2,0]
//输出：2
//解释：
//- 节点 0 的分数为：2 = 2
//- 节点 1 的分数为：2 = 2
//- 节点 2 的分数为：1 * 1 = 1
//最高分数为 2 ，有两个节点分数为 2 （分别为节点 0 和 1 ）。
// 
//
// 
//
// 提示： 
//
// 
// n == parents.length 
// 2 <= n <= 105 
// parents[0] == -1 
// 对于 i != 0 ，有 0 <= parents[i] <= n - 1 
// parents 表示一棵二叉树。 
// 
// Related Topics 树 深度优先搜索 数组 二叉树 
// 👍 124 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class Node {
        int count;//以当前节点为根节点的子树的节点数
        Node left, right;
        public Node() {}
    }
    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        Node[] nodes = new Node[n];
        nodes[0] = new Node();
        Node root = nodes[0];
        root.count = n;
        for (int i = 1; i < n; i++) {//建树
            if (nodes[i] == null) {
                nodes[i] = new Node();
            }
            Node temp = nodes[i];
            int fa = parents[i];//父节点编号
            if (nodes[fa] == null) {//父节点为空，初始化父节点
                nodes[fa] = new Node();
            }
            if (nodes[fa].left == null) {
                nodes[fa].left = temp;
            }else{
                nodes[fa].right = temp;
            }
        }
        dfs(root);
        int ans = 0;
        long maxCount = 0;
        //遍历所有节点，去除该节点后，二叉树分为三部分，父节点树，左子树，右子树
        for (int i = 0; i < n; i++) {
            int left = 0, right = 0;
            if (nodes[i].left != null) {
                left = nodes[i].left.count;
            }
            if (nodes[i].right != null) {
                right = nodes[i].right.count;
            }
            int father = n-left-right-1;//父节点树节点数
            //注意节点树为 0 时，设为 1
            left = left == 0 ? 1 : left;
            right = right == 0 ? 1 : right;
            father = father == 0 ? 1 : father;
            long tempCount = (long)left * right * father;//int类型溢出
            if (tempCount == maxCount) {
                ans++;
            }else if (tempCount > maxCount) {
                maxCount = tempCount;
                ans = 1;
            }
        }
        return ans;
    }

    public int dfs(Node root) {//深度优先搜索，给节点的count赋值
        if (root == null) {
            return 0;
        }
        int l = dfs(root.left);//左子树节点数
        int r = dfs(root.right);//右子树节点数
        root.count = l+r+1;
        return root.count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
