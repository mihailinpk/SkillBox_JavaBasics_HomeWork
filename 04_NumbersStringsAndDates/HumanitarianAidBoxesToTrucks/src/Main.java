import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    private static final int MAX_NUM_CONTAINERS_IN_TRUCK  =   12;    // максимальное количество контейнеров в грузовике
    private static final int MAX_NUM_BOXES_IN_CONTAINER   =   27;     // максимальное количество ящиков в контейнере

    private static final int MAX_NUM_BOXES_IN_TRUCK = MAX_NUM_CONTAINERS_IN_TRUCK * MAX_NUM_BOXES_IN_CONTAINER;

    public static void main(String[] args) {

        int currentTruckNumber = 0;
        int currentContainerNumber = 0;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {

            System.out.print("Введите количество ящиков: ");
            int numberOfBoxes = Integer.parseInt(reader.readLine());
            if (numberOfBoxes > 0)  {
                for(int i=0; i<numberOfBoxes; i++)  {

                    if (i%MAX_NUM_BOXES_IN_TRUCK == 0)  {
                        currentTruckNumber += 1;
                        System.out.printf("Грузовик %s:\n", currentTruckNumber);
                    }

                    if (i%MAX_NUM_BOXES_IN_CONTAINER == 0)  {
                        currentContainerNumber += 1;
                        System.out.printf("\tКонтейнер %s:\n", currentContainerNumber);
                    }

                    System.out.println("\t\tЯщик " + (i+1));
                }
            }
            else
                System.out.println("В качестве гуманитарной помощи отправить нечего :(");


        }
        catch (Exception ex)    {
            System.out.println("Вы ввели некорректное значение :(");
        }

    }

}