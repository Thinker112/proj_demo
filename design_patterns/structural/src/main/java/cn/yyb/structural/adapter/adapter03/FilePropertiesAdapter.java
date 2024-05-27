package cn.yyb.structural.adapter.adapter03;

import java.io.*;
import java.util.Properties;

/**
 * @author yueyubo <br>
 * @date 2024-05-26 14:41
 */
public class FilePropertiesAdapter extends Properties implements FileIO {

    @Override
    public void readFromFile(String filename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filename);
        load(fileInputStream);
    }

    @Override
    public void writeToFile(String filename) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        store(fileOutputStream, "written by FileProperties");
    }

    @Override
    public void setValue(String key, String value) {
        setProperty(key, value);
    }

    @Override
    public String getValue(String key) {
        return getProperty(key);
    }
}
