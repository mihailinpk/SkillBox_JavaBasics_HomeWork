package su.exceptions;

public class MonthHasNotPassedSinceLastDepositException extends Exception {
    public MonthHasNotPassedSinceLastDepositException() {
        System.out.println("С момента последнего внесения денег на счет месяц еще не прошел :(");
    }
}