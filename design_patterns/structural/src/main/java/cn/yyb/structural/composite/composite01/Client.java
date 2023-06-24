package cn.yyb.structural.composite.composite01;

public class Client {
    public static void main(String[] args) {
        var orcMessage = new Messenger().messageFromOrcs();
        orcMessage.print(); // Where there is a whip there is a way.
        var elfMessage = new Messenger().messageFromElves();
        elfMessage.print(); // Much wind pours from your mouth.

    }
}
