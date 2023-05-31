package cn.yyb.behavioral.chain_of_responsibility.chain_of_responsibility02;

/**
 * The base interface should have a method to define the next processor in the chain and the method that will process the request.
 * Our ATM Dispense interface will look like below
 */
public interface DispenseChain {

    void setNextChain(DispenseChain nextChain);

    void dispense(Currency cur);
}
