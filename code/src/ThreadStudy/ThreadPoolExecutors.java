package ThreadStudy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutors {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(2);
        es.submit(new RunnableImp());
        es.submit(new RunnableImp());
        es.submit(new RunnableImp());
        es.submit(new RunnableImp());
    }
}
