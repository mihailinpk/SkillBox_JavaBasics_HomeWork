import su.accounts.BankAccount;
import su.bankclients.Individual;
import su.bankclients.IndividualEntrepreneur;
import su.bankclients.LegalEntity;

public class Main {

    public static void main(String[] args) {

        try {

            System.out.println("------======              TEST: физическое лицо                   ======------");
            Individual individual = new Individual(12.5f);
            individual.putAmountOfMoneyIntoBankAccount(2.5f);
            individual.withdrawAmountOfMoneyFromBankAccount(1.5f);
            individual.getAccountInformation();
            System.out.println();

            System.out.println("------======              TEST: юридическое лицо                  ======------");
            BankAccount bankAccountOfLegalEntity = new BankAccount(10.5f);
            LegalEntity legalEntity = new LegalEntity(bankAccountOfLegalEntity);
            legalEntity.putAmountOfMoneyIntoBankAccount(1.0f);
            legalEntity.withdrawAmountOfMoneyFromBankAccount(2.5f);
            legalEntity.getAccountInformation();
            System.out.println();

            System.out.println("------======         TEST: индивидуальный предприниматель         ======------");
            IndividualEntrepreneur individualEntrepreneur = new IndividualEntrepreneur(10.5f);
            individualEntrepreneur.putAmountOfMoneyIntoBankAccount(1.5f);
            System.out.println(individualEntrepreneur.getBalanceFromBankAccount());
            individualEntrepreneur.withdrawAmountOfMoneyFromBankAccount(2.5f);
            individualEntrepreneur.getAccountInformation();

        } catch (Exception ex)  {}

    }

}