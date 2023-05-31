package cn.yyb.behavioral.chain_of_responsibility.chain_of_responsibility01;

/**
 * @author yyb
 * @create 2022-12-11 21:20
 */
public class OrcSoldier extends RequestHandler{

    public OrcSoldier(RequestHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(Request req) {
        if (req.getRequestType().equals(RequestType.COLLECT_TAX)) {
            printHandling(req);
            req.markHandled();
        } else {
            super.handleRequest(req);
        }
    }


    @Override
    public String toString() {
        return "Orc Soldier";
    }
}
