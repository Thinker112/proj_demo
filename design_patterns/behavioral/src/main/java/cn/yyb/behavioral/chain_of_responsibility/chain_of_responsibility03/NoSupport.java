package cn.yyb.behavioral.chain_of_responsibility.chain_of_responsibility03;

import com.sun.net.httpserver.Filter;

/**
 * 不解决问题的类
 * @author yueyubo <br>
 * @date 2024-06-06 22:28
 */
public class NoSupport extends Support{

    public NoSupport(String name) {
        super(name);
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        return false;
    }
}
