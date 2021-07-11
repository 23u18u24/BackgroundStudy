package StudyThread;

public class Vehicle {
    private final String name;
    public Vehicle(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(this.name + "跑了" + i + "次");
        }
    }
}
