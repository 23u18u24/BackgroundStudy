package StreamStudy;

import java.util.stream.Stream;

public class MapDemo {
    public static void main(String[] args) {
        Stream<String> integerStream = Stream.of("1", "2", "3", "4", "5", "6");
        Stream<Integer> rStream = integerStream.map((String s) -> Integer.parseInt(s));
        rStream.forEach((Integer i) -> System.out.println(i));
    }
}
