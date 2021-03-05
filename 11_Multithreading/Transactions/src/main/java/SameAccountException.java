public class SameAccountException extends Exception {
    public SameAccountException() {
        super("Attempted transfer between the same account !");
    }
}
