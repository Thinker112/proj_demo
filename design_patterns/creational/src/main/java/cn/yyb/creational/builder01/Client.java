package cn.yyb.creational.builder01;

public class Client {
    public static void main(String[] args) {
        Computer build = Computer.builder().HDD("xixu").RAM("DDR4").build();
    }
}
