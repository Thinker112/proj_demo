package cn.yyb.behavioral.chain_of_responsibility.chain_of_responsibility01;

import java.util.Objects;

/**
 * @author yyb
 * @create 2022-12-11 21:04
 */
public class Request {

    private final RequestType requestType;
    private final String requestDescription;
    private boolean handled;

    public Request(final RequestType requestType, final String requestDescription) {
        this.requestType = Objects.requireNonNull(requestType);
        this.requestDescription = Objects.requireNonNull(requestDescription);
    }

    public String getRequestDescription() { return requestDescription; }

    public RequestType getRequestType() { return requestType; }

    public void markHandled() { this.handled = true; }

    public boolean isHandled() { return this.handled; }

    @Override
    public String toString() { return getRequestDescription(); }


}
