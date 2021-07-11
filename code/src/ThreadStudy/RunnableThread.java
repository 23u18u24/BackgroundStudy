package ThreadStudy;

public class RunnableThread {
    public static void main(String[] args) {
        RunnableImp runnableImp = new RunnableImp();
        Thread t = new Thread(runnableImp);
        t.start();

        new Thread(new RunnableImp()) {
        }.start();
    }
}
