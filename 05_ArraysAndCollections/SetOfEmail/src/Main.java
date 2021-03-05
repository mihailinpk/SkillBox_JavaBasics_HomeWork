public class Main {

    private final static String COMMAND_ADD = "ADD";
    private final static String COMMAND_DELETE = "DELETE";
    private final static String COMMAND_LIST = "LIST";
    private final static String COMMAND_EXIT = "EXIT";

    private static EmailList emailList = EmailList.getInstance();

    public static void main(String[] args) {

        while (true)    {
            System.out.print("Введите команду: ");
            String userInput = UserInput.getLine();
            
            try {
                if (userInput.startsWith(COMMAND_ADD))   {
                    emailList.add(userInput.replaceAll(COMMAND_ADD, "").trim());
                } else if (userInput.startsWith(COMMAND_DELETE))    {
                    emailList.delete(userInput.replaceAll(COMMAND_DELETE, "").trim());
                } else if (userInput.equals(COMMAND_EXIT))  {
                    break;
                } else if (userInput.equals(COMMAND_LIST))  {
                    emailList.printList();
                } else  {
                    System.out.println("неверная команда, попробуйте, пожалуйста, еще раз !..");
                }

            }
            catch (InvalidAddressEmailException ex)    {}
        }
        System.out.println("\nПрограмма завершила работу...");

    }

}