public class AccountIsBlockedException extends Exception    {

    public AccountIsBlockedException() {
        super("Both accounts are blocked !!!");
    }

    public AccountIsBlockedException(String accNumber) {
        super(accNumber + "is blocked !!!");
    }

}