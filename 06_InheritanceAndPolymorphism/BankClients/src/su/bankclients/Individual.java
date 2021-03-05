package su.bankclients;

import su.accounts.BankAccount;

public class Individual extends Client{

    public Individual(BankAccount clientBankAccount) {
        super(clientBankAccount);
    }

    public Individual(float amountOfMoney) {
        super(amountOfMoney);
    }

    @Override
    public void getAccountInformation() {
        System.out.println("У физических лиц пополнение и снятие происходит без комиссии.");
        System.out.printf("Остаток на счете: %.2f руб.\n", getBalanceFromBankAccount());
    }

}