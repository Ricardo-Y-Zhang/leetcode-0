import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @program: leetcode-0
 * @description:
 * @author: Mr.Yan
 * @create: 2021-08-30 11:27
 **/
public class Test {

    @org.junit.Test
    public void test(){
        System.out.println(0x3f3f3f3f);
    }
}

class Solution {
    public boolean placeWordInCrossword(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            boolean flag = find(board[i], word);
            if (flag){
                return true;
            }

            char[] temp = new char[board[i].length];
            for (int j = 0; j < temp.length; j++) {
                temp[j] = board[i][temp.length-1-j];
            }
            flag = find(temp, word);
            if (flag){
                return true;
            }


        }

        for (int i = 0; i < board[0].length; i++) {
            char[] temp = new char[board.length];
            for (int j = 0; j < temp.length; j++) {
                temp[j] = board[j][i];
            }
            boolean flag = find(temp, word);
            if (flag){
                return true;
            }

            char[] temp1 = new char[board.length];
            for (int j = 0; j < temp.length; j++) {
                temp1[j] = temp[board.length-j-1];
            }
            flag = find(temp1, word);
            if (flag){
                return true;
            }

        }

        return false;
    }

    public boolean find(char[] temp, String word){
        for (int i = 0; i < temp.length-word.length()+1; i++) {
            if (i == 0 || temp[i-1] == '#'){
                for (int j = 0; j < word.length(); j++) {
                    if (temp[i+j] == word.charAt(j) || temp[i+j] == ' '){
                        if (j == word.length()-1){
                            if (i+j == temp.length-1 || temp[i+j+1] == '#'){
                                return true;
                            }
                        }
                    }else{
                        break;
                    }
                }
            }
        }

        return false;
    }
}