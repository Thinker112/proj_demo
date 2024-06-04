package cn.yyb.structural.decorator.decorator03;

/**
 * @author yueyubo <br>
 * @date 2024-06-04 22:18
 */
public class UpDownBorder extends Border{

    protected UpDownBorder(Display display) {
        super(display);
    }

    @Override
    public int getColumns() {
        return 0;
    }

    @Override
    public int getRows() {
        return 0;
    }

    @Override
    public String getRowText(int row) {
        return "";
    }
}
