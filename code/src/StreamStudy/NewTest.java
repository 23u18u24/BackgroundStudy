package StreamStudy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class NewTest {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "迪丽热巴", "宋远桥", "苏星河", "世博天", "老子", "庄子", "洪七公");
        ArrayList<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "古力娜扎", "张无忌", "赵丽颖", "张三丰", "尼古拉斯赵四", "张天爱", "张二狗");

        ArrayList<Person> endList = new ArrayList<>();
        Stream.concat(list1.stream().filter((s) -> s.length() == 3).limit(3), list2.stream().filter((s) -> s.startsWith("张")).skip(2)).map((s) -> new Person(s)).forEach((s) -> endList.add(s));
        endList.forEach((s) -> System.out.println(s.getName()));
    }
}
