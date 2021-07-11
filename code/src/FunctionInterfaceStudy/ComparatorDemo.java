package FunctionInterfaceStudy;

import java.util.Comparator;

public class ComparatorDemo {
    public static Comparator<String> getComparator() {
        return (o1, o2) -> o2.length() - o1.length();
    }
}
