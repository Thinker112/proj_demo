package cn.yyb.behavioral.chain_of_responsibility.chain_of_responsibility01;

/**
 * @author yyb
 * @create 2022-12-11 21:06
 */
public abstract class RequestHandler {

    private final RequestHandler next;

    public RequestHandler(RequestHandler next) {
        this.next = next;
    }

    public void handleRequest(Request req) {
        if (next != null) {
            next.handleRequest(req);
        }
    }

    protected void printHandling(Request req) {
        System.out.println(this + "handling request  " + req);
    }

    @Override
    public abstract String toString();
}


