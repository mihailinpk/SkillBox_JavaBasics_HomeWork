public class Main   {

    private static String addCommand = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static String commandExamples = "\t" + addCommand + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static String commandError = "Wrong command! Available command examples: \n" +
            commandExamples;
    private static String helpText = "Command examples:\n" + commandExamples;



    public static void main(String[] args)  {
        CustomerStorage executor = new CustomerStorage();
        while(true) {
            try {
                System.out.print("Enter the command: ");
                String command = UserInput.getLine();
                String[] tokens = command.split("\\s+", 2);
                if(tokens[0].equals("add")) {
                    Checks.checkingNumberOfItemsInCommand((byte) 2, tokens);
                    executor.addCustomer(tokens[1]);
                }
                else if(tokens[0].equals("list")) {
                    Checks.checkingNumberOfItemsInCommand((byte) 1, tokens);
                    executor.listCustomers();
                }
                else if(tokens[0].equals("remove"))
                {
                    Checks.checkingNumberOfItemsInCommand((byte) 2, tokens);
                    Checks.checkingName(tokens[1]);
                    executor.removeCustomer(tokens[1]);
                }
                else if(tokens[0].equals("count")) {
                    Checks.checkingNumberOfItemsInCommand((byte) 1, tokens);
                    System.out.println("There are " + executor.getCount() + " customers");
                }
                else if(tokens[0].equals("help")) {
                    Checks.checkingNumberOfItemsInCommand((byte) 1, tokens);
                    System.out.println(helpText);
                }
                else if(tokens[0].equals("exit"))   {
                    Checks.checkingNumberOfItemsInCommand((byte) 1, tokens);
                    break;
                }
                else {
                    throw new ConsoleCustomerListException(Checks.getIncorrectCommandMessage());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again...");
            }
        }
        System.out.println("The program has finished working...");
    }

}
