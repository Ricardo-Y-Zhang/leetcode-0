//给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。 
//
// 井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。 
//
// 以下是井字游戏的规则： 
//
// 
// 玩家轮流将字符放入空位（' '）中。 
// 玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。 
// 'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。 
// 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。 
// 当所有位置非空时，也算为游戏结束。 
// 如果游戏结束，玩家不允许再放置字符。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：board = ["O  ","   ","   "]
//输出：false
//解释：玩家 1 总是放字符 "X" 。
// 
//
// 示例 2： 
//
// 
//输入：board = ["XOX"," X ","   "]
//输出：false
//解释：玩家应该轮流放字符。 
//
// 示例 3： 
//
// 
//输入：board = ["XXX","   ","OOO"]
//输出：false
// 
//
// Example 4: 
//
// 
//输入：board = ["XOX","O O","XOX"]
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// board.length == 3 
// board[i].length == 3 
// board[i][j] 为 'X'、'O' 或 ' ' 
// 
// Related Topics 数组 字符串 
// 👍 63 👎 0


package leetcode.editor.cn;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[][] game = new int[3][3];
    public boolean validTicTacToe(String[] board) {
        int num1 = 0, num2 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'X'){
                    game[i][j] = 1;//一号玩家
                    num1++;
                }else if (board[i].charAt(j) == 'O'){
                    game[i][j] = 2;//二号玩家
                    num2++;
                }else{
                    game[i][j] = 0;//空格子
                }
            }
        }
        if (num1 < num2 || num1 > num2+1){//一号玩家的棋子少或比二号玩家多下两次
            return false;
        }
        boolean win1 = judge(1), win2 = judge(2);
        if (win1&&win2){//一号玩家和二号玩家均赢
            return false;
        }
        if (win2&&(num1!=num2)){//二号玩家赢但棋子数量不等
            return false;
        }
        if (win1&&num1==num2){//一号玩家赢但棋子数量相等
            return false;
        }
        return true;
    }
    public boolean judge(int x){//判断胜出情况
        for (int i = 0; i < 3; i++) {
            if (game[i][0] == x && game[i][1] == x && game[i][2] == x){
                return true;
            }
            if (game[0][i] == x && game[1][i]==x && game[2][i]==x){
                return true;
            }
        }
        if (game[0][0]==x&&game[1][1]==x&&game[2][2]==x){
            return true;
        }
        if (game[0][2]==x&&game[1][1]==x&&game[2][0]==x){
            return true;
        }
        return false;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


