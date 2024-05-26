package cn.yyb.structural.adapter.adapter01;

public class Client {
    public static void main(String[] args) {
        Captain captain = new Captain(new FishingBoatAdapter());
        captain.row();
    }
}
