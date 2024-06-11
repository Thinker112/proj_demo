package cn.yyb.structural.facade.facade01;

import cn.yyb.structural.facade.facade01.pagemaker.Database;
import cn.yyb.structural.facade.facade01.pagemaker.HtmlWriter;
import cn.yyb.structural.facade.facade01.pagemaker.PageMaker;

import javax.swing.text.html.HTMLWriter;

/**
 * @author yueyubo
 * @date 2024-06-11
 */
public class Main {
    public static void main(String[] args) {
        PageMaker.makeLinkPage("welcome.html");
    }
}
