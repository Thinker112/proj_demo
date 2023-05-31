package cn.yyb.behavioral.template.template02;

/**
 * @author yyb
 * @create 2022-11-27 10:59
 */
public class ClientTest {
    public static void main(String[] args) {
        HalflingThief thief = new HalflingThief(new HitAndRunMethod());
        thief.steal();
        thief.changeMethod(new SubtleMethod());
        thief.steal();
    }
}
