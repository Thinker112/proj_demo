package cn.yyb.structural.adapter.adapter03;

import java.io.IOException;

/**
 * @author yueyubo <br>
 * @date 2024-05-26 14:38
 */
public interface FileIO {
    void readFromFile(String filename) throws IOException;

    void writeToFile(String filename) throws IOException;

    void setValue(String key, String value);

    String getValue(String key);
}
