package StudyThread;

public class CreateThreadM1 extends Thread {
    public CreateThreadM1(String name) {
        super(name);
    }
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("执行了" + i + "次");
        }
        System.out.println(currentThread());
        System.out.println(getName());
    }
}
