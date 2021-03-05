package su.exceptions;

public class RequestedAmountIsTooLargeException extends Exception {
    public RequestedAmountIsTooLargeException() {
        System.out.println("Запрошенная сумма превышает сумму на вашем счете :(");
    }
}