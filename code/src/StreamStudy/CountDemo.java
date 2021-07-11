package StreamStudy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class CountDemo {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list,1,2,3,4,5,6,7);

        Stream<Integer> stream = list.stream();
        long count = stream.count();
        System.out.println(count);
    }
}
