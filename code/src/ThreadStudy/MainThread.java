package ThreadStudy;

public class MainThread {
    public static void main(String[] args) throws InterruptedException {
//        Vehicle v1 = new Vehicle("船");
//        v1.run();
//
////        System.out.println(0/0);
//
//        Vehicle v2 = new Vehicle("车");
//        v2.run();
        StudyThread.CreateThreadM1 c1 = new StudyThread.CreateThreadM1("船");
        c1.start();
        System.out.println(c1.getName());
        Thread.sleep(1000L);
        c1.setName("线程c1");
        System.out.println(c1.getName());
//        CreateThreadM1 c2 = new CreateThreadM1("车");
//        c2.start();
//        for (int i = 0; i < 20; i++) {
//            System.out.println("main线程执行" + i +"次");
//        }
    }
}
