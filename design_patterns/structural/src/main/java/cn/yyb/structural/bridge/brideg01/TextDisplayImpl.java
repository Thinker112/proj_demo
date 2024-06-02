package cn.yyb.structural.bridge.brideg01;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author yueyubo <br>
 * @date 2024-06-02 18:30
 */
public class TextDisplayImpl extends DisplayImpl{

    private String filename;

    private String content;

    public TextDisplayImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public void rawOpen() {
        try {
            content = Files.readString(Paths.get(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rawPrint() {
        System.out.println("+++++Start+++++");
        System.out.print(content);
    }

    @Override
    public void rawClose() {
        System.out.println("+++++EOF+++++");
    }
}
