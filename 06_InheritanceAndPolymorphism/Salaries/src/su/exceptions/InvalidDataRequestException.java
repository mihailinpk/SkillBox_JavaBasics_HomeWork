package su.exceptions;

public class InvalidDataRequestException extends Exception {

    public InvalidDataRequestException() {
        System.out.println("Некорректный запрос данных...");
    }

}