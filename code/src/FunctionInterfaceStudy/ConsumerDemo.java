package FunctionInterfaceStudy;

import java.util.function.Consumer;

public class ConsumerDemo {
    public static void method(String name, Consumer<String> con) {
        con.accept(name);
    }

    public static void method1(String[] arr, Consumer<String> con1) {
        for (String s : arr) {
            con1.accept(s);
        }
    }

    public static void main(String[] args) {
//        method("wendy", (name) -> {
////            System.out.println(name);
//            String rename = new StringBuilder(name).reverse().toString();
//            System.out.println(rename);
//        });
        String[] arr = {"Candy,18", "Wendy,19", "Banana,20"};
        method1(arr, (s)->{
            String name = s.split(",")[0];
            String age = s.split(",")[1];
            System.out.println("name: " + name + "\tage: " + age);
        });
    }
}
