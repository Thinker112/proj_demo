package cn.yyb.behavioral.observer.observer02;

import java.util.ArrayList;
import java.util.List;

/**
 * 发布者
 * @author yueyubo
 * @create 2022-10-28 0:19
 */
public class WeathData implements Subject{

    private double temperature;
    private double pressure;
    private double humidity;
    //观察者集合
    private List<Observer> observers;

    public WeathData(double temperature, double pressure, double humidity, List<Observer> observers) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.observers = observers;
    }

    public WeathData() {
        this.observers = new ArrayList<Observer>();
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }

    public void dataChange(double temperature, double pressure, double humidity){
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.notifyObserver();//通知观察者
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.update(this.temperature, this.pressure, this.humidity);
        }
    }
}
