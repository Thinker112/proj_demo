package cn.yyb.behavioral.chain_of_responsibility.chain_of_responsibility01;

/**
 * 兽王大声命令他的军队。最近响应的是指挥官，然后是军官，然后是士兵。指挥官，军官，士兵这里就形成了一个责任链。
 * @author yyb
 * @create 2022-12-11 21:26
 */
public class Client {
    public static void main(String[] args) {
        OrcKing king = new OrcKing();
        // Orc commander handling request "defend castle"
        king.makeRequest(new Request(RequestType.DEFEND_CASTLE, "defend castle"));

        // Orc officer handling request "torture prisoner"
        king.makeRequest(new Request(RequestType.TORTURE_PRISONER, "torture prisoner"));

        // Orc soldier handling request "collect tax"
        king.makeRequest(new Request(RequestType.COLLECT_TAX, "collect tax"));
    }
}
