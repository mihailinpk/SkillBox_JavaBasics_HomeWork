public class InvalidAddressEmailException extends Exception {

    public InvalidAddressEmailException() {
        System.out.println("Вы ввели некорректный адрес email, попробуйте еще раз !");
    }
}