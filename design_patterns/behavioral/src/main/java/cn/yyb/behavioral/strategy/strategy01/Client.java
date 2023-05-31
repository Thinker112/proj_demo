package cn.yyb.behavioral.strategy.strategy01;

import java.util.HashMap;
import java.util.function.Function;

public class Client {
    public static void main(String[] args) {

        HashMap<String, Function<Integer, String>> functionHashMap = new HashMap<>();

        functionHashMap.put("Alipay", amount -> "支付宝支付"+amount+"成功");
        functionHashMap.put("WeChatPay", amount -> "微信支付"+amount+"成功");

        String type = "WeChatPay";

        String apply = functionHashMap.get(type).apply(500);
        System.out.println(apply);
    }
}
