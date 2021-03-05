public class Main {

    private static BeautifulNumbersList beautifulNumbersList = BeautifulNumbersList.getInstance();

    private final static String COMMAND_EXIT = "EXIT";

    public static void main(String[] args) {

        while(true) {

            try {

                System.out.print("\nВедите номер автомобиля: ");
                String userInput = UserInput.getLine();

                if (userInput.equals(COMMAND_EXIT)) {
                    break;
                }

                if (!userInput.matches("^[АВЕКМНОРСТУХ](\\d)\\1{2}[АВЕКМНОРСТУХ]{2}\\d{2,3}RUS$"))  {
                    throw new Exception();
                }

                System.out.print("Поиск перебором: ");
                long start = System.nanoTime();
                searchAndMeasurement(beautifulNumbersList.directIterationSearch(userInput), start);

                System.out.print("Бинарный поиск: ");
                start = System.nanoTime();
                searchAndMeasurement(beautifulNumbersList.binarySearch(userInput) >= 0, start);

                System.out.print("Поиск в HashSet: ");
                start = System.nanoTime();
                searchAndMeasurement(beautifulNumbersList.hashSetSearch(userInput), start);

                System.out.print("Поиск в TreeSet: ");
                start = System.nanoTime();
                searchAndMeasurement(beautifulNumbersList.treeSetSearch(userInput), start);

            } catch (Exception ex)  {
                System.out.println("Вы ввели некорректные данные...");
            }
        }

        System.out.println("\nПрограмма завершила работу...");

    }

    private static void searchAndMeasurement(boolean searchResult, long start)  {
        if (searchResult)   {
            System.out.printf("номер найден, поиск занял %sнс\n", System.nanoTime() - start);
        } else {
            System.out.println("номер не найден");
        }
    }

}