package su.accounts;

import su.exceptions.MonthHasNotPassedSinceLastDepositException;

import java.time.LocalDateTime;

public class DepositAccount extends BankAccount {

    private LocalDateTime dateTimeOfLastDeposit;

    public DepositAccount(float currentAccountBalance) {
        super(currentAccountBalance);
        dateTimeOfLastDeposit = LocalDateTime.now();
    }

    @Override
    public float withdrawAmountOfMoneyFromAccount(float amountOfMoney) throws Exception  {
        if (monthHasPassedSinceLastDeposit())   {
            return super.withdrawAmountOfMoneyFromAccount(amountOfMoney);
        } else {
            throw new MonthHasNotPassedSinceLastDepositException();
        }
    }

    @Override
    public void putAmountOfMoneyIntoAccount(float amountOfMoney)   {
        super.putAmountOfMoneyIntoAccount(amountOfMoney);
        dateTimeOfLastDeposit = LocalDateTime.now();
    }

    private boolean monthHasPassedSinceLastDeposit() {
        return LocalDateTime.now().isAfter(dateTimeOfLastDeposit.plusMonths(1));
    }

}