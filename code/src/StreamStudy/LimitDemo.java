package StreamStudy;

import java.util.stream.Stream;

public class LimitDemo {
    public static void main(String[] args) {
        String[] arr = {"美羊羊","喜羊羊","懒洋洋","灰太狼","红太狼"};
        Stream<String> stream = Stream.of(arr);

        stream.limit(3).forEach((s) -> System.out.println(s));
    }
}
