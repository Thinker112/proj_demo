package cn.yyb.behavioral.chain_of_responsibility.chain_of_responsibility03;

/**
 * 用来解决问题的抽象类
 *
 * @author yueyubo <br>
 * @date 2024-06-06 22:24
 */
public abstract class Support {
    private final String name;                    // 解决问题的实例的名字
    // 要推卸给的对象
    private Support next;

    public Support(String name) {           // 生成解决问题的实例
        this.name = name;
    }

    public Support setNext(Support next) {  // 设置要推卸给的对象
        this.next = next;
        return next;
    }

    // 解决问题的步骤
    public void support(Trouble trouble) {
//        if (resolve(trouble)) {
//            done(trouble);
//        } else if (next != null) {
//            next.support(trouble);
//        } else {
//            fail(trouble);
//        }
        for (Support obj = this; true; obj = obj.next) {
            if (obj.resolve(trouble)) {
                obj.done(trouble);
                break;
            } else if (obj.next == null) {
                obj.fail(trouble);
                break;
            }
        }
    }

    public String toString() {              // 显示字符串
        return "[" + name + "]";
    }

    // 解决问题的方法
    protected abstract boolean resolve(Trouble trouble);

    protected void done(Trouble trouble) {  // 解决
        System.out.println(trouble + " is resolved by " + this + ".");
    }

    protected void fail(Trouble trouble) {  // 未解决
        System.out.println(trouble + " cannot be resolved.");
    }
}
