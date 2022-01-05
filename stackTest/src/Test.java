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
        double feiM = 8.06, yuanM = 10.4, yanM = 11.20;
        double sumM = feiM + yuanM + yanM;
        double sumZ = 12460 + 13350 + 13350;
        double feiZ = feiM/sumM * sumZ;
        double yuanZ = yuanM/sumM * sumZ;
        double yanZ = yanM/sumM * sumZ;
        System.out.println("feiZ = " + feiZ);
        System.out.println("yuanZ = " + yuanZ);
        System.out.println("yanZ = " + yanZ);

        double avg = sumZ/3;
        double feiGet = avg - feiZ;
        double yuanGet = avg - yuanZ;
        double yanGet = avg - yanZ;
        System.out.println("feiGet = " + feiGet);
        System.out.println("yuanGet = " + yuanGet);
        System.out.println("yanGet = " + yanGet);

        double water = 14.5, elec = 44.52, qi = 15.19, room = 75;
        yanGet -= elec;
        yuanGet += (elec)/2;
        feiGet += (elec)/2;

        System.out.println("feiGet = " + feiGet);
        System.out.println("yuanGet = " + yuanGet);
        System.out.println("yanGet = " + yanGet);


        double allget = 6525.79/3;
        double yan = allget + yanGet;
        double fei = allget + feiGet;
        double yuan = allget + yuanGet;
        System.out.println("yan = " + yan);
        System.out.println("fei = " + fei);
        System.out.println("yuan = " + yuan);

        System.out.println("(yan+fei+yuan) = " + (yan + fei + yuan));

        double allPay = (water+qi+room)/3;
//        feiGet-=allPay;
//        yuanGet -= allPay;
//        yanGet -= allPay;
//
//        System.out.println("feiGet = " + feiGet);
//        System.out.println("yuanGet = " + yuanGet);
//        System.out.println("yanGet = " + yanGet);
//

    }

    @org.junit.Test
    public void test1(){
        String[] temp = {"V", "C", "6", "T", "E0", "A", "P", "Y", "E6"};
        int num = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = i+1; j < 9; j++) {
                for (int k = j+1; k < 9; k++) {
                    for (int l = k+1; l < 9; l++) {
                        for (int m = l+1; m < 9; m++) {
                            num++;
                            System.out.print(temp[i] + temp[j] + temp[k] + temp[l] + temp[m] + " , ");
                        }
                    }
                }
            }
        }
        System.out.println("num = " + num);

    }


    @org.junit.Test
    public void test3(){
        int res = -5;
        res >>>= 2;
        System.out.println("res" + res);
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