package cn.yyb.behavioral.state.state01;

/**
 * 表示金库状态的接口
 * @author yueyubo
 * @date 2024-06-12
 */
public interface State {
    void doClock(Context context, int hour);    // 设置时间
    void doUse(Context context);                // 使用金库
    void doAlarm(Context context);              // 按下警铃
    void doPhone(Context context);              // 正常通话
}