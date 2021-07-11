package LambdaExpStudy;

import java.util.Arrays;

public class LambdaExpBasic {
    public static void main(String[] args) {
//        new Thread(()-> {
//            System.out.println(Thread.currentThread().getName());
//            }
//        ).start();
        invokeCalc(120, 130, new Calculator() {
            @Override
            public int calc(int a, int b) {
                return a + b;
            }
        });

        invokeCalc(120, 130, (int a, int b) -> {
            return a + b;
        });

        invokeCalc(120, 130, (a, b) -> a + b);

        invokeCalc(120, 130, Integer::sum);
    }

    public static void invokeCalc(int a, int b, Calculator c) {
        int sum = c.calc(a, b);
        System.out.println(sum);
    }
}
