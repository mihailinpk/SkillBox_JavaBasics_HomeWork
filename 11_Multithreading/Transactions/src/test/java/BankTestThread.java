public class BankTestThread implements Runnable  {

    Bank bank;
    private String fromAccountNum;
    private String toAccountNum;
    long amount;

    public BankTestThread(Bank bank, String fromAccountNum, String toAccountNum, long amount) {
        this.bank = bank;
        this.fromAccountNum = fromAccountNum;
        this.toAccountNum = toAccountNum;
        this.amount = amount;
    }

    @Override
    public void run() {
        try {
            bank.transfer(fromAccountNum, toAccountNum, amount);
        } catch (Exception ex)  {
            System.out.println(ex.getMessage());
        }
    }

}