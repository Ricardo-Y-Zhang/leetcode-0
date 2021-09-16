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
    public int maxmiumScore(int[] cards, int cnt) {
        if (cnt > cards.length){
            return 0;
        }
        Arrays.sort(cards);

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();

        for (int temp : cards){
            if (temp % 2 == 0){
                list2.add(temp);
            }else{
                list1.add(temp);
            }
        }

        while (list1.size() >= 2){
            list3.add(list1.get(list1.size()-1) + list1.get(list1.size()-2));
            list1.remove(list1.size()-1);
            list1.remove(list1.size()-1);
        }


        int res = 0;
        if (cnt % 2 == 1){
            if (list2.size() > 0){
                res += list2.get(list2.size()-1);
                list2.remove(list2.size()-1);
                cnt--;
            }else{
                return 0;
            }
        }

        while (cnt > 0){
            if (list3.size() > 0 && list2.size() >= 2){
                int temp = list2.get(list2.size()-1) + list2.get(list2.size()-2);

                if (temp > list3.get(0)){
                    res += temp;
                    list2.remove(list2.size()-1);
                    list2.remove(list2.size()-1);
                }else{

                    res += list3.get(0);
                    list3.remove(0);
                }
            }else if (list3.size() > 0  &&  list2.size() < 2){
                res += list3.get(0);
                list3.remove(0);
            }else if (list3.size() == 0 && list2.size() >= 2){
                int temp = list2.get(list2.size()-1) + list2.get(list2.size()-2);
                res += temp;
                list2.remove(list2.size()-1);
                list2.remove(list2.size()-1);
            }else {
                return 0;
            }

            cnt -= 2;
        }

        return res;
    }
}