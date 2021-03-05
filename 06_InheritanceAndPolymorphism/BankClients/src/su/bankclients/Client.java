package su.bankclients;

import su.accounts.BankAccount;

public abstract class Client {

    private final BankAccount clientBankAccount;

    /*  клинет заносится в базу данных банка либо с уже готовым расчетным счетом
        либо вносит сумму денег и расчетный счет создается автоматически    */
    public Client(BankAccount clientBankAccount) {
        this.clientBankAccount = clientBankAccount;
    }
    public Client(float amountOfMoney)  {
        this.clientBankAccount = new BankAccount(amountOfMoney);
    }

    public void putAmountOfMoneyIntoBankAccount(float amountOfMoney) throws Exception {
        clientBankAccount.putAmountOfMoneyIntoAccount(amountOfMoney);
    }

    public float withdrawAmountOfMoneyFromBankAccount(float amountOfMoney) throws Exception {
        return clientBankAccount.withdrawAmountOfMoneyFromAccount(amountOfMoney);
    }

    public float getBalanceFromBankAccount()    {
        return clientBankAccount.getCurrentAccountBalance();
    }



    public abstract void getAccountInformation();

}