package cn.yyb.behavioral.command.command02.drawer;

import cn.yyb.behavioral.command.command02.command.Command;

import java.awt.*;

/**
 * 绘制一个点的命令
 *
 * @author yueyubo
 * @date 2024-06-15
 */
public class DrawCommand implements Command {
    // 绘制对象
    protected Drawable drawable;
    // 绘制位置
    private final Point position;

    // 构造函数
    public DrawCommand(Drawable drawable, Point position) {
        this.drawable = drawable;
        this.position = position;
    }

    // 执行
    public void execute() {
        drawable.draw(position.x, position.y);
    }
}