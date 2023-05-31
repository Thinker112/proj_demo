package cn.yyb.behavioral.observer.observer02;

/**
 * 订阅者
 * @author yueyubo
 * @create 2022-10-28 0:14
 */
public class CurrentConditions implements Observer{

    private double temperature;
    private double pressure;
    private double humidity;

    public CurrentConditions() {
    }

    public CurrentConditions(double temperature, double pressure, double humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
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

    //更新天气情况
    @Override
    public void update(double temperture, double pressure, double humidate) {
        this.temperature = temperture;
        this.pressure = pressure;
        this.humidity = humidate;
        this.display();
    }

    public void display(){
        System.out.println("Today CurrentCondition  temperature:" + temperature);
        System.out.println("Today CurrentCondition  pressure:" + pressure);
        System.out.println("Today CurrentCondition  humidity:" + humidity);
    }
}
