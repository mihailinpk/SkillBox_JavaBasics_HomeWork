import java.util.HashMap;
import java.util.Random;

public class Bank   {

    private final Random random = new Random();

    private final long amountMoneyForCheck = 50000;
    private volatile HashMap<String, Account> accounts;

    public synchronized boolean isFraud(Account sourceAccount, Account targetAccount, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public void transfer(String fromAccountNum, String toAccountNum, long amount)
        throws AccountIsBlockedException, TooLittleMoneyException, InterruptedException, SameAccountException {

            Account sourceAccount = accounts.get(fromAccountNum);
            Account targetAccount = accounts.get(toAccountNum);
            int sourceAccountHash = sourceAccount.hashCode();
            int targetAccountHash = targetAccount.hashCode();
            if (sourceAccountHash == targetAccountHash) {
                throw new SameAccountException();
            }
            boolean sourceLessTarget = sourceAccountHash < targetAccountHash;
            synchronized (sourceLessTarget ? sourceAccount : targetAccount)    {
                synchronized (sourceLessTarget ? targetAccount : sourceAccount)    {
                    doTransfer(sourceAccount, targetAccount, amount);
                }
            }

    }

    public long getTotalBalance() {
        return accounts.values().stream().mapToLong(Account::getMoney).sum();
    }

    public long getAccountBalance(String accountNum)   {
        return accounts.get(accountNum).getMoney();
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(HashMap<String, Account> accounts) {
        this.accounts = accounts;
    }

    public void doTransfer(Account sourceAccount, Account targetAccount, long amount)
        throws AccountIsBlockedException, TooLittleMoneyException, InterruptedException   {

            if (sourceAccount.isBlocked())  {
                throw new AccountIsBlockedException(sourceAccount.getAccNumber());
            }
            if (targetAccount.isBlocked())  {
                throw new AccountIsBlockedException(targetAccount.getAccNumber());
            }
            if (sourceAccount.getMoney() < amount)  {
                throw new TooLittleMoneyException(sourceAccount.getMoney());
            }
            if ((amount>amountMoneyForCheck) && this.isFraud(sourceAccount, targetAccount, amount)) {
                sourceAccount.setBlocked(true);
                targetAccount.setBlocked(true);
                throw new AccountIsBlockedException();
            }   else    {
                sourceAccount.takeMoney(amount);
                targetAccount.addMoney(amount);
            }

    }

}