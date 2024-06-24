package cn.yyb.behavioral.command.command02;

import cn.yyb.behavioral.command.command02.command.Command;
import cn.yyb.behavioral.command.command02.command.MacroCommand;
import cn.yyb.behavioral.command.command02.drawer.DrawCanvas;
import cn.yyb.behavioral.command.command02.drawer.DrawCommand;

import javax.swing.*;
import java.awt.event.*;

/**
 * @author yueyubo
 * @date 2024-06-15
 */
public class Main extends JFrame implements ActionListener {
    // 绘制的历史记录
    private final MacroCommand history = new MacroCommand();
    // 绘制区域
    private final DrawCanvas canvas = new DrawCanvas(400, 400, history);
    // 删除按钮
    private final JButton clearButton  = new JButton("clear");

    // 构造函数
    public Main(String title) {
        super(title);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Command cmd = new DrawCommand(canvas, e.getPoint());
                history.append(cmd);
                cmd.execute();
            }
        });

        clearButton.addActionListener(this);

        Box buttonBox = new Box(BoxLayout.X_AXIS);
        buttonBox.add(clearButton);
        Box mainBox = new Box(BoxLayout.Y_AXIS);
        mainBox.add(buttonBox);
        mainBox.add(canvas);
        getContentPane().add(mainBox);

        pack();
        show();
    }

    // ActionListener接口中的方法
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearButton) {
            history.clear();
            canvas.repaint();
        }
    }

    public static void main(String[] args) {
        new Main("Command Pattern Sample");
    }
}
