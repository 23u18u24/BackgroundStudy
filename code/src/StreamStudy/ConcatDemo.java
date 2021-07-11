package StreamStudy;

import java.util.stream.Stream;

public class ConcatDemo {
    public static void main(String[] args) {
        Stream<String> stream1 = Stream.of("喜羊羊");
        Stream<String> stream2 = Stream.of("美羊羊");

        Stream.concat(stream1, stream2).forEach((s) -> System.out.println(s));
    }
}
