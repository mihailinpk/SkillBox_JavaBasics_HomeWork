import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class BankTest {

    private Random random = new Random(60000);
    private Bank bank = new Bank();

    private final String threadGroupName = "TestThreadGroupName";

    @Before
    public void initTest()  {
        HashMap<String, Account> accounts = new HashMap<>();
        Account account1 = new Account(150000, "acc1522", false);
        Account account2 = new Account(150000, "acc3582", false);
        Account account3 = new Account(150000, "acc5478", false);
        Account account4 = new Account(150000, "acc6431", false);
        Account account5 = new Account(150000, "acc9173", false);
        Account account6 = new Account(150000, "acc6842", false);
        accounts.put(account1.getAccNumber(), account1);
        accounts.put(account2.getAccNumber(), account2);
        accounts.put(account3.getAccNumber(), account3);
        accounts.put(account4.getAccNumber(), account4);
        accounts.put(account5.getAccNumber(), account5);
        accounts.put(account6.getAccNumber(), account6);
        bank.setAccounts(accounts);
    }

    @Test
    public void getTotalBalanceTest()   {
        long expected = 900000;
        long actual = bank.getTotalBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void getAccountBalanceTest() {
        long expected = 150000;
        long actual = bank.getAccountBalance("acc6842");
        assertEquals(expected, actual);
    }

    @Test
    public void transferTest()  {

        String sourceAccountNum1 = "acc1522";
        String sourceAccountNum2 = "acc3582";
        String targetAccountNum1 = "acc5478";
        String targetAccountNum2 = "acc6431";
        long moneyTransferAmount1 = 1550;
        long moneyTransferAmount2 = 1750;
        long totalBankMoney = 900000;
        try {
            bank.transfer(sourceAccountNum1, targetAccountNum1, moneyTransferAmount1);
            bank.transfer(sourceAccountNum2, targetAccountNum2, moneyTransferAmount2);
            long expectedSourceAccountMoney1 = 148450;
            long expectedSourceAccountMoney2 = 148250;
            long expectedTargetAccountMoney1 = 151550;
            long expectedTargetAccountMoney2 = 151750;
            long actualSourceAccountMoney1 = bank.getAccountBalance(sourceAccountNum1);
            long actualSourceAccountMoney2 = bank.getAccountBalance(sourceAccountNum2);
            long actualTargetAccountMoney1 = bank.getAccountBalance(targetAccountNum1);
            long actualTargetAccountMoney2 = bank.getAccountBalance(targetAccountNum2);
            long actualTotalBankMoney = bank.getTotalBalance();
            assertEquals(expectedSourceAccountMoney1, actualSourceAccountMoney1);
            assertEquals(expectedSourceAccountMoney2, actualSourceAccountMoney2);
            assertEquals(expectedTargetAccountMoney1, actualTargetAccountMoney1);
            assertEquals(expectedTargetAccountMoney2, actualTargetAccountMoney2);
            assertEquals(totalBankMoney, actualTotalBankMoney);
        } catch (Exception ex)  {
            System.out.println(ex.getMessage());
        }

    }

    @Test
    public void workingInMultithreadedMode() throws InterruptedException {

        List<Thread> bankTestThreadsthreads = new ArrayList<>();
        for (int i=0; i<20; i++)    {
            BankTestThread bankTestThread = new BankTestThread(
                bank,
                BankTestAuxiliaryMethods.getRandomAccNumber(),
                BankTestAuxiliaryMethods.getRandomAccNumber(),
                random.nextInt(60000)
            );
            bankTestThreadsthreads.add(new Thread(bankTestThread));
        }
        for (int i=0; i<20; i++)    {
            bankTestThreadsthreads.get(i).start();
        }
        for(int i=0; i<20; i++) {
            bankTestThreadsthreads.get(i).join();
        }
        long expectedBankMoney = 900000;
        long actualBankMoney = bank.getTotalBalance();
        assertEquals(expectedBankMoney, actualBankMoney);

    }

}