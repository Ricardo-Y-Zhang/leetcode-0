//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
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
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SE
//E"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CB"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n = board[i].length 
// 1 <= m, n <= 6 
// 1 <= word.length <= 15 
// board 和 word 仅由大小写英文字母组成 
// 
//
// 
//
// 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？ 
// Related Topics 数组 回溯 矩阵 
// 👍 996 👎 0


package leetcode.editor.cn;


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    boolean flag = false;
    //上下左右
    int[][] plus = {{-1,1,0,0}, {0,0,-1,1}};

    public boolean exist(char[][] board, String word) {
        boolean[][] choose;
        flag = false;
        Label: for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //在之前轮次中已经搜索到word，直接跳出
                if (flag == true){
                    break Label;
                }
                //从与word[0]相等的字母开始搜索
                if (board[i][j] == word.charAt(0)){
                    choose = new boolean[board.length][board[0].length];
                    judge(board, word, 0, i, j, choose);
                }

            }
        }
        return flag;
    }

    void judge(char[][] board, String word, int count, int x, int y, boolean[][] choose){

        if (flag == true){
            return;
        }
        //修改当前位置的搜索状态
        choose[x][y] = true;

        if (count == word.length() - 1){
            flag = true;
            return;
        }else if (count < word.length()-1){
            //上下左右位置
            for (int i = 0; i < 4; i++) {
                int x1 = x + plus[0][i];
                int y1 = y + plus[1][i];

                //满足要求：未搜索过且和word中下一个字符相等
                if (x1 >= 0 && x1 < board.length && y1 >= 0 && y1 < board[0].length && choose[x1][y1] == false && board[x1][y1] == word.charAt(count+1)){
                    judge(board, word, count+1, x1, y1, choose);
                }

            }
        }

        //回溯
        choose[x][y] = false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


