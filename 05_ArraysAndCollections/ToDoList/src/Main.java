import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        List<String> toDoList = new ArrayList<>(){{
            add("сходить в магазин");
            add("погулять с собакой");
            add("убраться в комнате");
        }};
        while (true)    {
            System.out.print("Введите команду: ");
            String enteredLine = keyboard.nextLine();
            if (enteredLine.equals("EXIT")) {
                break;
            }
            try {
                commandExecution(enteredLine.split("\\s+", 3), toDoList);
            }   catch (Exception ex)    {
                System.out.println("неверная команда, попробуйте, пожалуйста, еще раз !..");
            }
        }
        System.out.println("\nПрограмма завершила работу...");
    }

    private static void commandExecution(String[] stringArray, List list) throws Exception  {
        switch (stringArray[0])  {
            case "LIST" -> showTheListOnScreen(list);
            case "ADD" -> addElement(list, stringArray[1], stringArray[2]);
            case "EDIT" -> list.set(Integer.parseInt(stringArray[1]), stringArray[2]);
            case "DELETE" -> deleteElement(list, Integer.parseInt(stringArray[1]));
            default -> throw new Exception();
        }
    }

    private static void showTheListOnScreen(List list)  {
        if (list.isEmpty()) {
            System.out.println("Список дел пуст");
        }   else    {
            System.out.println("\nПолный список дел:");
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%s. %s\n", i, list.get(i));
            }
        }
        System.out.println();
    }

    private static void addElement(List list, String firstElementOfArrayInterLine, String thirdElementOfArrayInterLine)    {
        if (firstElementOfArrayInterLine.matches("\\d"))  {
            int insertNumber = Integer.parseInt(firstElementOfArrayInterLine);
            if (numberIsCorrected(list, insertNumber))  {
                list.add(insertNumber, thirdElementOfArrayInterLine);
            }   else    {
                list.add(thirdElementOfArrayInterLine);
            }
        }   else    {
            list.add(firstElementOfArrayInterLine.concat(" " + thirdElementOfArrayInterLine));
        }
    }

    private static void deleteElement(List list, int number) throws Exception{
        if (numberIsCorrected(list, number))  {
            list.remove(number);
        }   else    {
            throw new Exception();
        }
    }

    private static boolean numberIsCorrected(List list, int number) {
        return (number < list.size() ? true : false);
    }

}