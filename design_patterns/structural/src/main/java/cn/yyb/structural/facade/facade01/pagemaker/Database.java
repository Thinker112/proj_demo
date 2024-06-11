package cn.yyb.structural.facade.facade01.pagemaker;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author yueyubo
 * @date 2024-06-11 19:38
 */
public class Database {
    private Database() {    // 防止外部new出Database的实例，所以声明为private方法
    }

    protected static Properties getProperties(String dbname) { // 根据数据库名获取Properties
        String filename = dbname + ".txt";
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(filename));
        } catch (IOException e) {
            System.out.println("Warning: " + filename + " is not found.");
        }
        return prop;
    }
}
