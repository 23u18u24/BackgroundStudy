public class Demo {
    public static void demo(int...arr) {
        for (int i : arr) {
            System.out.println(i);
        }
    }
    public static void main(String[] args) {
        demo(1,2,3,4,5,6,78,9754,512);
    }
}
