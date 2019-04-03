import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game extends Thread {

    public static void main(String[] args) {

        System.out.println("Hello Orc & Hobbit World!");

        System.out.println("Viser et enum eksempel:  ");
        System.out.println(WeatherType.COLD.toString());

        System.out.println("Opretter vejr");
        Weather w = new Weather();
        System.out.println("Vejret er  " + w.getWeatherNow() + " nu.");

        System.out.println("Opretter hobitter ");

        Hobbit frodo = new Hobbit("Frodo");
        w.addObserver(frodo);

        Hobbit sam = new Hobbit("Sam");
        w.addObserver(sam);


        Hobbit bilbo = new Hobbit("Bilbo");
        w.addObserver(bilbo);

        Hobbit gollum = new Hobbit("Smeagol");
        w.addObserver(gollum);

        ExecutorService executor = Executors.newCachedThreadPool();

        for(int i = 0; i <1000; i++) {
            String name = Integer.toString( i );
            Hobbit hobbit = new Hobbit("Cosby nr: " + i);
            executor.execute(hobbit);
        }

        System.out.println("Vejret ændrer sig");
        System.out.println("Hobitterne kan mærke varmen på deres små kroppe");
        w.timePasses();


        System.out.println("Frodo er død, han har ikke burg fo flere vejropdateringer.");
        w.removeObserver(frodo);
    }
}
