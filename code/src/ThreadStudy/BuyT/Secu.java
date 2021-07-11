package ThreadStudy.BuyT;

public class Secu {
    public static void main(String[] args) {
        BuyT.Kid k = new BuyT.Kid();
        Thread t1 = new Thread(k);
        t1.start();

        Thread t2 = new Thread(k);
        t2.start();

        Thread t3 = new Thread(k);
        t3.start();
    }
}
