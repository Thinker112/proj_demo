package cn.yyb.creational.singleton01;

/**
 * @author yueyubo <br>
 * @date 2024-05-30 21:04
 */
public class Triple {

    private int id;

    private static final Triple[] instances = new Triple[3];

    private Triple(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Triple getInstance(int id) {
        if (id >= 0 && id < 3) {
            if (instances[id] == null) {
                instances[id] = new Triple(id);
            }
            return instances[id];
        } else {
            throw new IllegalArgumentException("ID must be 0, 1, or 2");
        }
    }
}
