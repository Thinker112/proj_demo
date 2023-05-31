package cn.yyb.behavioral.observer.observer01;

/**
 * @author yueyubo
 * @create 2022-11-01 21:23
 */
public class Secretary implements Subject{


    private String action;

    @FunctionalInterface
    public interface Event{
        void update();
    }

    @Override
    public void nofityObserver() {
        
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
