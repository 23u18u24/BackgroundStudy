package FunctionInterfaceStudy;

public class Demo {
    public static void show(DefineFunctionInterface ff) {
        ff.method();
    }

    public static void main(String[] args) {
//        show(new MainImp());
//
//        show(new DefineFunctionInterface() {
//            @Override
//            public void method() {
//                System.out.println("这是匿名内部类！");
//            }
//        });
//
//        show(() -> System.out.println("这是lambda表达式"));
    }
}
