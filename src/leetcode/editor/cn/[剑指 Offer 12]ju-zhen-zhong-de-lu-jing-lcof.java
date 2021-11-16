//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CCED"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], word = "abcd"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/ 
// Related Topics 数组 回溯 矩阵 
// 👍 454 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    String words = "";
    int length = 0;
    boolean flag = false;
    int[] x = {-1, 1, 0, 0};
    int[] y = {0, 0, -1, 1};

    public boolean exist(char[][] board, String word) {
        words = word;
        length = words.length();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (flag == true){
                    return flag;
                }
                if (board[i][j] == word.charAt(0)){
                    bfs(board, i, j, new boolean[board.length][board[0].length], 0);
                }
            }
        }
        return flag;
    }
    void bfs(char[][] board,int i, int j, boolean[][] visited, int index){
        if (flag == true || index == words.length()-1){
            flag = true;
            return;
        }

        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int X = i + x[k], Y = j + y[k];
            if (X >= 0 && X < board.length && Y >= 0 && Y < board[0].length){
                if (visited[X][Y] == false && words.charAt(index+1) == board[X][Y]){
                    bfs(board, X, Y, visited, index+1);
                }
            }
        }
        visited[i][j] = false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


