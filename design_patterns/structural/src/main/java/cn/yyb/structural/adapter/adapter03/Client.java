package cn.yyb.structural.adapter.adapter03;

import java.io.IOException;

/**
 * @author yueyubo <br>
 * @date 2024-05-26 14:59
 */
public class Client {
    public static void main(String[] args) {
        FilePropertiesAdapter filePropertiesAdapter = new FilePropertiesAdapter();
        try {
            filePropertiesAdapter.readFromFile("D:\\project_code\\java\\myself_demo_all\\design_patterns\\structural\\src\\main\\java\\cn\\yyb\\structural\\adapter\\adapter03\\t.txt");
            filePropertiesAdapter.setValue("d", "4");

            filePropertiesAdapter.writeToFile("new.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
