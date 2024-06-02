package cn.yyb.creational.builder02;

import org.w3c.dom.Text;

/**
 * @author yueyubo <br>
 * @date 2024-06-02 14:09
 */
public class Client {
    public static void main(String[] args) {
        if (args.length != 1) {
            usage();
            System.exit(0);
        }

        if (args[0].equals("plain")) {
            TextBuilder textBuilder = new TextBuilder();
            Director director = new Director(textBuilder);
            director.construct();
            StringBuffer result = textBuilder.getResult();
            System.out.println(result);
        }else if (args[0].equals("html")) {
            HTMLBuilder htmlBuilder = new HTMLBuilder();
            Director director = new Director(htmlBuilder);
            director.construct();
            String result = htmlBuilder.getResult();
            System.out.println(result);
        }else {
            usage();
            System.exit(0);
        }
    }

    static void usage(){
        System.out.println("Usage: plain 编写纯文本文档");
        System.out.println("Usage: HTML 编写HTML文档");

    }
}
