package cn.yyb.behavioral.visitor.visitor01;

import java.util.Iterator;

/**
 * @author yueyubo <br>
 * @date 2024-06-05 21:11
 */
public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Making root entries...");
            Directory rootdir = new Directory("root");
            Directory bindir = new Directory("bin");
            Directory tmpdir = new Directory("tmp");
            Directory usrdir = new Directory("usr");

            rootdir.add(bindir);
            rootdir.add(tmpdir);
            rootdir.add(usrdir);

            bindir.add(new File("vi", 10000));
            bindir.add(new File("latex", 20000));
            rootdir.accept(new ListVisitor());

            System.out.println();
            System.out.println("Making user entries...");
            Directory yuki = new Directory("yuki");
            Directory hanako = new Directory("hanako");
            Directory tomura = new Directory("tomura");

            usrdir.add(yuki);
            usrdir.add(hanako);
            usrdir.add(tomura);

            yuki.add(new File("diary.html", 100));
            yuki.add(new File("Composite.html", 200));
            hanako.add(new File("memo.html", 300));
            tomura.add(new File("game.html", 400));
            tomura.add(new File("junk.mail", 500));

//            rootdir.accept(new ListVisitor());

            System.out.println("HTML files are:");
            FileFindVisitor fileFindVisitor = new FileFindVisitor(".html");
            rootdir.accept(fileFindVisitor);
            Iterator<Entry> foundFiles = fileFindVisitor.getFoundFiles();
            while (foundFiles.hasNext()) {
                Entry entry = foundFiles.next();
                System.out.println("entry = " + entry);
            }

        } catch (FileTreatmentException e) {
            e.printStackTrace();
        }
    }
}
