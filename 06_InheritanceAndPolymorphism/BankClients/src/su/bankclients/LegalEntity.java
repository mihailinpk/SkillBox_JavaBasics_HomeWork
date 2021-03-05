package su.bankclients;

import su.accounts.BankAccount;

public class LegalEntity extends Client {

    public LegalEntity(BankAccount clientBankAccount) {
        super(clientBankAccount);
    }

    public LegalEntity(float amountOfMoney) {
        super(amountOfMoney);
    }

    @Override
    public float withdrawAmountOfMoneyFromBankAccount(float amountOfMoney) throws Exception {
        amountOfMoney += getBalanceFromBankAccount()/100;
        return super.withdrawAmountOfMoneyFromBankAccount(amountOfMoney);
    }

    @Override
    public void getAccountInformation() {
        System.out.println("У юридических лиц снятие происходит с комиссией 1%");
        System.out.printf("Остаток на счете: %.2f руб.\n", getBalanceFromBankAccount());
    }

}