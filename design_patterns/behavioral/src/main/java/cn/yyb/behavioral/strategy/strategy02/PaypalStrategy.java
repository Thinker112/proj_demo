package cn.yyb.behavioral.strategy.strategy02;

/**
 * Paypal支付
 * @author yyb
 * @create 2022-11-27 9:50
 */
public class PaypalStrategy implements PaymentStrategy{

    private String emailId;
    private String password;

    public PaypalStrategy(String email, String pwd){
        this.emailId=email;
        this.password=pwd;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using Paypal.");
    }
}
