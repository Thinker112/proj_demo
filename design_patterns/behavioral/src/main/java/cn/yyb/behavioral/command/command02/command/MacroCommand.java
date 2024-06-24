package cn.yyb.behavioral.command.command02.command;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 由多条命令整合成的命令
 * @author yueyubo
 * @date 2024-06-15
 */
public class MacroCommand implements Command {
    // 命令的集合
    Deque<Command> commands = new ArrayDeque<>();

    @Override
    public void execute() {
        for (Command command : commands) {
            command.execute();
        }
    }

    // 添加命令
    public void append(Command cmd) {
        if (cmd != this) {
            commands.push(cmd);
        }
    }

    // 删除最后一条命令
    public void undo() {
        if (!commands.isEmpty()) {
            commands.pop();
        }
    }

    // 删除所有命令
    public void clear() {
        commands.clear();
    }
}
