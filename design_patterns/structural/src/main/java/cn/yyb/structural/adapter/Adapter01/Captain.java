package cn.yyb.structural.adapter.Adapter01;

/**
 * 船长需要有一个RowingBoat接口的实现，这样就可以移动
 * 现在海盗来了，我们的船长需要逃跑但是只有一个渔船可用。
 * 我们需要创建一个可以让船长使用其划船技能来操作渔船的适配器。
 */
public class Captain {

    private final RowingBoat rowingBoat;

    // default constructor and setter for rowingBoat
    public Captain(RowingBoat rowingBoat) {
        this.rowingBoat = rowingBoat;
    }

    public void row() {
        rowingBoat.row();
    }
}
