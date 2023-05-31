package cn.yyb.structural.adapter.Adapter01;

public class Client {
    public static void main(String[] args) {
        Captain captain = new Captain(new FishingBoatAdapter());
        captain.row();
    }
}
