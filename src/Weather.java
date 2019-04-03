/*
 *  Developed by András Ács (acsandras@gmail.com)
 *  Zealand / www.zealand.dk
 *  Licensed under the MIT License
 *  2019-03-21/03/2019
 *
 */

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Weather {

    private Lock lock = new ReentrantLock(  );
    public Object addObserver;
    private WeatherType weatherNow;
    private ArrayList<WeatherObserver> observers;

    public Weather() {
        // Lucas siger, at spillet starter i regnvejr altid
        this.setWeatherNow(WeatherType.RAINY);
        observers = new ArrayList<WeatherObserver>();
    }

    public synchronized int totalObservers(){
        return observers.size();
    }

    public void addObserver(WeatherObserver w) {
        lock.lock();
        observers.add(w);
        lock.unlock();
    }

    public void removeObserver(WeatherObserver w) {
        lock.lock();
        observers.remove(w);
        lock.unlock();
    }

    public void timePasses() {
        weatherNow = WeatherType.SUNNY;
        System.out.println("Vejret ændrer sig til " + weatherNow);
        notifyObservers();
    }

    private void notifyObservers() {
        if (!observers.isEmpty()) {
            for (WeatherObserver hobbit : observers) {
                hobbit.update(getWeatherNow());
            }
        }
    }

    public WeatherType getWeatherNow() {
        return weatherNow;
    }

    public void setWeatherNow(WeatherType weatherNow) {
        this.weatherNow = weatherNow;
    }


}
