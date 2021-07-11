package StreamStudy;

import java.util.*;
import java.util.stream.Stream;

public class GetStream {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        Set<String> set = new HashSet<>();
        Stream<String> stream2 = set.stream();

        Map<String, String> map = new HashMap<>();
        Set<String> keySet = map.keySet();
        Stream<String> stream3 = keySet.stream();
        Collection<String> values = map.values();
        Stream<String> stream4 = values.stream();

        Set<Map.Entry<String, String>> entries = map.entrySet();
        Stream<Map.Entry<String, String>> streams = entries.stream();

        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6, 7);

        Integer[] arr = {1,2,3,45,6,45,4,8,5};
        Stream<Integer> arr1 = Stream.of(arr);
    }
}
