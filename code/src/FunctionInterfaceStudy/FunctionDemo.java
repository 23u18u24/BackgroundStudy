package FunctionInterfaceStudy;

import java.util.function.Function;

public class FunctionDemo {
    public static void method(String s, Function<String, Integer> f) {
        Integer apply = f.apply(s);
        System.out.println(apply);
    }

    public static void main(String[] args) {
        String s = "10123";
        method(s, (s1) -> Integer.parseInt(s1));
    }
}
