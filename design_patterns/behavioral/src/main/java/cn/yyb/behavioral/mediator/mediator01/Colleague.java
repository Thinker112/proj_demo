package cn.yyb.behavioral.mediator.mediator01;

/**
 * 表示向仲裁者进行报告的组员的接口
 * @author yueyubo
 * @date 2024-06-11
 */
public interface Colleague {
    void setMediator(Mediator mediator);

    /**
     * 告知组员仲裁者所下达的指示
     * @param enabled
     */
    void setColleagueEnabled(boolean enabled);
}
