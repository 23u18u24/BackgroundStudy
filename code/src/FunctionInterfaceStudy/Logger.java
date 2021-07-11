package FunctionInterfaceStudy;

public class Logger {
    public static void showLog(int level, String message) {
        if (level == 1) {
            System.out.println(message);
        }
    }

    public static void showLogBetter(int level, DefineFunctionInterface ff) {
        if (level == 1) {
            System.out.println(ff.method());
        }
    }

    public static void main(String[] args) {
        String msg1 = "Hello";
        String msg2 = "World";
//        showLog(1, msg1+msg2);
        showLogBetter(1, () -> msg1 + msg2);
    }
}
