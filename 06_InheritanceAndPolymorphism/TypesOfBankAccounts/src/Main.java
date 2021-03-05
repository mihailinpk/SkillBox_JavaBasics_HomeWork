import su.accounts.BankAccount;
import su.accounts.CardAccount;
import su.accounts.DepositAccount;

public class Main {

    public static void main(String[] args) {

        try {
            BankAccount bankAccount = new BankAccount(10.0f);
            DepositAccount depositAccount = new DepositAccount(11.0f);
            CardAccount cardAccount = new CardAccount(12.0f);
        }
        catch (Exception ex)    {
            ex.printStackTrace();
        }

    }

}