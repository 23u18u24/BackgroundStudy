package FunctionInterfaceStudy;

import java.util.function.Supplier;

public class SupplierDemo {
    public static String getString(Supplier<String> sup) {
        return sup.get();
    }

    public static int getMax(Supplier<Integer> sup) {
        return sup.get();
    }

    public static void main(String[] args) {
        System.out.println(getString(() -> "成功!"));

        int[] arr = {123,123,102,100,666,555,999};
        System.out.println(getMax(() -> {
            int max = arr[0];
            for (int i : arr) {
                if (i > max) {
                    max = i;
                }
            }
            return max;
        }));
    }
}
