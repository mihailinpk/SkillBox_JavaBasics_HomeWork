public class Main   {

    private final static String COMMAND_EXIT = "EXIT";
    private final static String COMMAND_LIST = "LIST";
    private final static String COMMAND_DELETE = "DELETE";
    private final static String COMMAND_ADD = "ADD";

    private static PhoneBookMap phoneBookMap = PhoneBookMap.getInstance();

    public static void main(String[] args)  {

        while (true)    {

            try {
                System.out.print("Введите команду / тел. номер / полное имя абонента:\n>> ");
                String userInput = UserInput.getLine();
                if (userInput.equals(COMMAND_EXIT)) {
                    break;
                } else if (userInput.equals(COMMAND_LIST)) {
                    phoneBookMap.printMap();
                } else if (userInput.startsWith(COMMAND_DELETE)) {
                    userInput = userInput.replaceAll(COMMAND_DELETE, "").trim();
                    if (UserInputProcessor.userInputIsNumber(userInput)) {
                        userInput = UserInputProcessor.formattingThePhoneNumber(userInput);
                        removeEntry(userInput, true);
                    } else if (UserInputProcessor.userInputIsName(userInput)) {
                        removeEntry(userInput, false);
                    } else {
                        throw new Exception();
                    }
                } else {
                    boolean ignoreExitingName = userInput.startsWith(COMMAND_ADD);
                    userInput = userInput.replaceAll(COMMAND_ADD, "").trim();
                    if (UserInputProcessor.userInputIsNumber(userInput)) {
                        userInput = UserInputProcessor.formattingThePhoneNumber(userInput);
                        printCurrentOrAddNewEntry(userInput, true, ignoreExitingName);
                    } else if (UserInputProcessor.userInputIsName(userInput)) {
                        printCurrentOrAddNewEntry(userInput, false, ignoreExitingName);
                    } else {
                        throw new Exception();
                    }
                }
            } catch (NoEntryInPhoneBookException ex)    {
            } catch (Exception ex)  {
                System.out.println("Вы ввели некорректные данные...");
            }
        }

        System.out.println("\nПрограмма завершила работу...");

    }

    private static void removeEntry(String inputLine, boolean deleteByNumber) throws NoEntryInPhoneBookException {
        if (phoneBookMap.containsEntry(inputLine))  {
            if (deleteByNumber) {
                phoneBookMap.removeEntryByNumber(inputLine);
            } else {
                phoneBookMap.removeEntryByName(inputLine);
            }
            System.out.println(
                    !phoneBookMap.containsEntry(inputLine) ?
                            "Запись(и) успешно удалена(ы) из телефонной книги" :
                            "Удалить запись(и) из телефонной книги не удалось :("
            );
        } else {
            throw new NoEntryInPhoneBookException();
        }
    }

    private static void printCurrentOrAddNewEntry(String inputLine, boolean addByNumber, boolean ignoreExitingName) throws Exception {
        if (phoneBookMap.containsEntry(inputLine) && !ignoreExitingName) {
            if (addByNumber) {
                phoneBookMap.printEntryByNumber(inputLine, true);
            } else {
                phoneBookMap.printEntryByName(inputLine);
            }
        } else if (phoneBookMap.containsEntryByNumber(inputLine)) {
            if (addByNumber)    {
                phoneBookMap.printEntryByNumber(inputLine, true);
            }
        } else {
            System.out.printf("Введите %s абонента: ", addByNumber ? "имя" : "номер");
            String userInput = UserInput.getLine();
            if (addByNumber ? UserInputProcessor.userInputIsName(userInput) : UserInputProcessor.userInputIsNumber(userInput)) {
                phoneBookMap.putNewEntry(
                        addByNumber ? inputLine : UserInputProcessor.formattingThePhoneNumber(userInput),
                        addByNumber ? userInput : inputLine
                );
                System.out.println(
                    phoneBookMap.containsEntry(inputLine) ?
                    "Запись успешно добавлена в телефонную книгу" :
                    "Добавить запись в телефонную книгу не удалось :("
                );
            } else {
                throw new Exception();
            }
        }
    }

}