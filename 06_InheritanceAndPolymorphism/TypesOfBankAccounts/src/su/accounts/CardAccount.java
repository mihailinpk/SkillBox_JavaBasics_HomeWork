package su.accounts;

public class CardAccount extends BankAccount    {

    public CardAccount(float currentAccountBalance) {
        super(currentAccountBalance);
    }

    @Override
    public float withdrawAmountOfMoneyFromAccount(float amountOfMoney) throws Exception {
        amountOfMoney += getCurrentAccountBalance()/100;
        return super.withdrawAmountOfMoneyFromAccount(amountOfMoney);
    }

}