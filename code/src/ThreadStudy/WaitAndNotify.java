package StudyThread;

public class WaitAndNotify {
    public static void main(String[] args) {
        Object obj = new Object();

        new Thread() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("数量1");
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("谢谢");
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println("数量2");
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("谢谢");
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                System.out.println("请稍等");
                try {
                    sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj) {
                    System.out.println("已做好");
                    obj.notifyAll();
                }
            }
        }.start();
    }
}
