public class TooLittleMoneyException extends Exception  {

    public TooLittleMoneyException() {
        super("You are pauper !!! This is too little money in your account");
    }

    public TooLittleMoneyException(long currentMoney) {
        super("You are pauper !!! On your account " + currentMoney);
    }

}