package cn.yyb.behavioral.chain_of_responsibility.chain_of_responsibility01;

/**
 * @author yyb
 * @create 2022-12-11 21:17
 */
public class OrcOfficer extends RequestHandler{

    public OrcOfficer(RequestHandler next) {
        super(next);
    }

    @Override
    public void handleRequest(Request req) {
        if (req.getRequestType().equals(RequestType.TORTURE_PRISONER)) {
            printHandling(req);
            req.markHandled();
        } else {
            super.handleRequest(req);
        }
    }

    @Override
    public String toString() {
        return "Orc Officer";
    }
}
