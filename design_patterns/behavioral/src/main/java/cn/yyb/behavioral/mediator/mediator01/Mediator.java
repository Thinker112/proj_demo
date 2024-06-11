package cn.yyb.behavioral.mediator.mediator01;

/**
 * 表示仲裁者（中介者）的接口
 * @author yueyubo
 * @date 2024-06-11
 */
public interface Mediator {
    /**
     * 用于生成Mediator要管理的成员
     */
    void createColleagues();

    /**
     * 被各个Colleague组员调用
     */
    void colleagueChanged();
}
