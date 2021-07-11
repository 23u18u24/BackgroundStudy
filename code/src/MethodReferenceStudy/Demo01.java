package MethodReferenceStudy;

public class Demo01 {
    public static void printString(Printable p) {
        p.print("Hello");
    }
    public static void main(String[] args) {
        printString(System.out::println);
        Method1 m = new Method1();
        printString(m::printUpperCase);
        printString(Method1::printLowerCase);
    }
}
