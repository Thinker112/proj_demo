package cn.yyb.behavioral.chain_of_responsibility.chain_of_responsibility02;

import java.util.Scanner;

/**
 * One of the great example of Chain of Responsibility pattern is ATM Dispense machine.
 * The user enters the amount to be dispensed and the machine dispense amount in terms of defined currency bills such as 50$, 20$, 10$ etc.
 * If the user enters an amount that is not multiples of 10, it throws error.
 * We will use Chain of Responsibility pattern to implement this solution.
 */
public class Client {
    public static void main(String[] args) {
        ATMDispenseChain atmDispenser = new ATMDispenseChain();
        while (true) {
            int amount = 0;
            System.out.println("Enter amount to dispense");
            Scanner input = new Scanner(System.in);
            amount = input.nextInt();
            if (amount % 10 != 0) {
                System.out.println("Amount should be in multiple of 10s.");
                return;
            }
            // process the request
            atmDispenser.c1.dispense(new Currency(amount));
        }

    }
}
