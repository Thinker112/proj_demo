package cn.yyb.behavioral.chain_of_responsibility.chain_of_responsibility01;

/**
 * @author yyb
 * @create 2022-12-11 21:14
 */
public class OrcKing {

    RequestHandler chain;

    public OrcKing() {
        buildChain();
    }

    private void buildChain() {
        chain = new OrcCommander(new OrcOfficer(new OrcSoldier(null)));
    }

    public void makeRequest(Request req) {
        chain.handleRequest(req);
    }
}
