package su.bankclients;

import su.accounts.BankAccount;

public class IndividualEntrepreneur extends Client {

    private final float AMOUNT_F0R_DETERMINING_COMISSION = 1000f;
    private final float PERCENT_BEFORE_AMOUNT_F0R_DETERMINING_COMISSION = 1.0f;
    private final float PERCENT_AFTER_AMOUNT_F0R_DETERMINING_COMISSION = 0.5f;

    public IndividualEntrepreneur(BankAccount clientBankAccount) {
        super(clientBankAccount);
    }

    public IndividualEntrepreneur(float amountOfMoney) {
        super(amountOfMoney);
    }

    @Override
    public void putAmountOfMoneyIntoBankAccount(float amountOfMoney) throws Exception {
        if (amountOfMoney < AMOUNT_F0R_DETERMINING_COMISSION)   {
            amountOfMoney -= amountOfMoney/100*PERCENT_BEFORE_AMOUNT_F0R_DETERMINING_COMISSION;
        } else  {
            amountOfMoney -= amountOfMoney/100*PERCENT_AFTER_AMOUNT_F0R_DETERMINING_COMISSION;
        }
        super.putAmountOfMoneyIntoBankAccount(amountOfMoney);
    }

    @Override
    public void getAccountInformation() {
        System.out.println("У индивидуальных предпринимателей пополнение происходит с комиссией 1%, если\nсумма меньше 1000 руб., и с комиссией 0,5% - " +
                "если больше или равна 1000 руб.");
        System.out.printf("Остаток на счете: %.2f руб.\n", getBalanceFromBankAccount());
    }
}
