package StreamStudy;

import java.util.ArrayList;
import java.util.Collections;

public class OldTest {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "迪丽热巴", "宋远桥", "苏星河", "世博天", "老子", "庄子", "洪七公");
        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "古力娜扎", "张无忌", "赵丽颖", "张三丰", "尼古拉斯赵四", "张天爱", "张二狗");

        ArrayList<String> list = new ArrayList<>();

        int count1 = 0;
        for (String s : list1) {
            if (s.length() == 3 && count1 < 3) {
                list.add(s);
                count1++;
            }
        }
        int count2 = 2;
        for (String s : list2) {
            if (count2 < 1 && s.length() == 3 && s.startsWith("张")) {
                list.add(s);
            }
            count2--;
        }
        ArrayList<Person> endList = new ArrayList<>();
        for (String s : list) {
            endList.add(new Person(s));
        }

        for (Person person : endList) {
            System.out.println(person.getName());
        }
    }
}
