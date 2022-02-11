//实现一个 MapSum 类，支持两个方法，insert 和 sum： 
//
// 
// MapSum() 初始化 MapSum 对象 
// void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 ke
//y 已经存在，那么原来的键值对将被替代成新的键值对。 
// int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//inputs = ["MapSum", "insert", "sum", "insert", "sum"]
//inputs = [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
//输出：
//[null, null, 3, null, 5]
//
//解释：
//MapSum mapSum = new MapSum();
//mapSum.insert("apple", 3);  
//mapSum.sum("ap");           // return 3 (apple = 3)
//mapSum.insert("app", 2);    
//mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
// 
//
// 
//
// 提示： 
//
// 
// 1 <= key.length, prefix.length <= 50 
// key 和 prefix 仅由小写英文字母组成 
// 1 <= val <= 1000 
// 最多调用 50 次 insert 和 sum 
// 
//
// 
//
// 注意：本题与主站 677 题相同： https://leetcode-cn.com/problems/map-sum-pairs/ 
// Related Topics 设计 字典树 哈希表 字符串 
// 👍 10 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class MapSum {
    class TreeNode{
        int val;// 0 代表当前节点不是 key 单词的结尾，若不为 0 ，则代表 val
        TreeNode[] next;
        TreeNode(){
            val = 0;
            next = new TreeNode[26];
        }
    }
    TreeNode root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new TreeNode();
    }
    
    public void insert(String key, int val) {
        TreeNode node = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i)-'a';
            if (node.next[index] == null){
                node.next[index] = new TreeNode();
            }
            node = node.next[index];
        }
        node.val = val;
    }
    
    public int sum(String prefix) {
        TreeNode node = root;
        int res = 0;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i)-'a';
            if (node.next[index] == null){
                return 0;
            }
            node = node.next[index];
        }
        return getSum(node);
    }

    public int getSum(TreeNode node){
        if (node == null){
            return 0;
        }
        int res = node.val;
        for (int i = 0; i < 26; i++) {
            res += getSum(node.next[i]);
        }
        return res;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)


