import org.apache.commons.validator.routines.EmailValidator;

public class Checks {

    private static String incorrectCommandFormatMessage = "Incorrect command format :(";
    private static String incorrectCommandMessage = "Incorrect command :(";
    private static String incorrectPhoneNumberFormantMessage = "Incorrect phone number format :(";
    private static String incorrectEmailAddressFormatMessage = "Incorrect email address message format :(";
    private static String incorrectNameFormatMessage = "Incorrect name format :(";

    public static String getIncorrectCommandFormatMessage() {
        return incorrectCommandFormatMessage;
    }

    public static String getIncorrectCommandMessage() {
        return incorrectCommandMessage;
    }

    public static String getIncorrectPhoneNumberFormantMessage() {
        return incorrectPhoneNumberFormantMessage;
    }

    public static String getIncorrectEmailAddressFormatMessage() {
        return incorrectEmailAddressFormatMessage;
    }

    public static String getIncorrectNameFormatMessage() {
        return incorrectNameFormatMessage;
    }

    public static void checkingNumberOfItemsInCommand(byte checkingNumberOfIntems, String [] commandArray) throws ConsoleCustomerListException {
        if (commandArray.length != checkingNumberOfIntems) {
            throw new ConsoleCustomerListException(incorrectCommandFormatMessage);
        }
    }

    public static void checkingName(String name) throws ConsoleCustomerListException    {
        if (!name.matches("[A-Za-zА-Яа-яЁё]+(\\s[A-Za-zА-Яа-яЁё]+)?")) {
            throw new ConsoleCustomerListException(incorrectNameFormatMessage);
        }
    }

    public static void checkingEmailAddress(String emailAddress) throws ConsoleCustomerListException   {
        if (!EmailValidator.getInstance().isValid(emailAddress))    {
            throw new ConsoleCustomerListException(incorrectEmailAddressFormatMessage);
        }
    }

    public static void checkingPhoneNumber(String phoneNumber) throws ConsoleCustomerListException  {
        boolean phoneNumberVerified = false;
        switch (phoneNumber.length())  {
            case 11 -> {
                if (phoneNumber.matches("[78]{1}\\d+"))   {
                    phoneNumberVerified = true;
                }
            }
            case 10 -> {
                if (phoneNumber.matches("\\d+")) {
                    phoneNumberVerified = true;
                }
            }
        }
        if (!phoneNumberVerified)   {
            throw new ConsoleCustomerListException(incorrectPhoneNumberFormantMessage);
        }
    }

}
