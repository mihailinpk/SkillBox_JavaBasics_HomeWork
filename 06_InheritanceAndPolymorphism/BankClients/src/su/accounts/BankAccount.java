package su.accounts;

import su.exceptions.RequestedAmountIsTooLargeException;

public class BankAccount {

    private float currentAccountBalance;

    protected void setCurrentAccountBalance(float currentAccountBalance) {
        this.currentAccountBalance = currentAccountBalance;
    }

    public float getCurrentAccountBalance() {
        return currentAccountBalance;
    }

    public BankAccount(float currentAccountBalance) {
        this.currentAccountBalance = currentAccountBalance;
    }

    public float withdrawAmountOfMoneyFromAccount(float amountOfMoney) throws Exception {
        if (amountOfMoney <= currentAccountBalance) {
            return currentAccountBalance -= amountOfMoney;
        } else {
            throw new RequestedAmountIsTooLargeException();
        }
    }

    public void putAmountOfMoneyIntoAccount(float amountOfMoney)   {
        currentAccountBalance += amountOfMoney;
    }

    public boolean send(BankAccount receiver, float amount) {

        try {

            float thisAccountBalanceBeforeSending = currentAccountBalance;
            float thatAccountBalanceBeforeSending = receiver.getCurrentAccountBalance();
            this.withdrawAmountOfMoneyFromAccount(amount);
            receiver.putAmountOfMoneyIntoAccount(amount);
            if (currentAccountBalance == thisAccountBalanceBeforeSending - amount &&
                    receiver.getCurrentAccountBalance() == thatAccountBalanceBeforeSending + amount)    {
                return true;
            } else {
                return false;
            }

        }
        catch (Exception ex)    {
            return false;
        }

    }

}