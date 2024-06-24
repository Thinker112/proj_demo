package cn.yyb.behavioral.command.command02.drawer;

import cn.yyb.behavioral.command.command02.command.MacroCommand;

import java.awt.*;

/**
 * @author yueyubo
 * @date 2024-06-15
 */
public class DrawCanvas extends Canvas implements Drawable {
    // 颜色
    private final Color color = Color.red;
    // 要绘制的圆点的半径
    private int radius = 6;
    // 命令的历史记录
    private final MacroCommand history;

    // 构造函数
    public DrawCanvas(int width, int height, MacroCommand history) {
        setSize(width, height);
        setBackground(Color.white);
        this.history = history;
    }

    // 重新全部绘制
    public void paint(Graphics g) {
        history.execute();
    }

    // 绘制
    public void draw(int x, int y) {
        Graphics g = getGraphics();
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
