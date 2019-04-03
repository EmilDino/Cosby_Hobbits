import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestClass {
    public static void main(String[] args) {
        Weather w = new Weather();


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    String name = Integer.toString( i );
                    w.addObserver(new Hobbit( name ));
                    System.out.println("A hobbit was born and is number " + i + " from this thread");
                }
                System.out.println("Total observers: " + w.totalObservers());
            }
        };

        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            threadPool.execute( runnable );
        }

    }
}
