package FunctionInterfaceStudy;

import java.util.function.Predicate;

public class PredicateDemo {
    public static boolean checkString(String s, Predicate<String> pre, Predicate<String> pre1) {
        return pre.and(pre1).test(s);
    }

    public static void main(String[] args) {
        String s = "abcde";

        System.out.println(checkString(s, (s1) -> s1.length() > 5, (s1) -> s1.contains("a")));
    }
}
