package su.tools;

import su.abstractclassesandinterfaces.Employee;
import su.companies.Company;
import su.exceptions.InvalidDataRequestException;
import su.typeofemployees.Manager;
import su.typeofemployees.Operator;
import su.typeofemployees.TopManager;

import java.util.ArrayList;
import java.util.List;

public class Generator {

    public static final float GENERATION_AMOUNT_OF_MONEY_EARNED_BY_MANAGER_START_VALUE = 115_000f;
    public static final float GENERATION_AMOUNT_OF_MONEY_EARNED_BY_MANAGER_END_VALUE = 140_000f;

    private static final String[] arrayOfNames = new String[]{"Александр", "Максим", "Иван", "Артем", "Дмитрий",
            "Никита", "Михаил", "Даниил", "Егор", "Андрей", "Петр", "Павел"};
    private static final String[] arrayOfSurnames = new String[]{"Смирнов", "Иванов", "Кузнецов", "Соколов", "Попов", "Лебедев",
            "Козлов", "Новиков", "Морозов", "Петров", "Волков", "Соловьёв",
            "Васильев", "Зайцев", "Павлов", "Семёнов", "Голубев", "Виноградов",
            "Богданов", "Воробьёв", "Фёдоров", "Михайлов", "Беляев", "Тарасов", "Белов"};
    private static final String[] arrayOfMiddleNames = new String[]{"Александрович", "Максимович", "Иванович", "Артемович", "Дмитриевич",
            "Никитович", "Михайлович", "Данилович", "Егорович", "Андреевич",
            "Петрович", "Павлович"};

    public static List<Employee> generateNewEmployeesList(int numberOfNewOperators, int numberOfNewmanagers, int numberOfNewTopManagers, Company company) throws InvalidDataRequestException {
        List<Employee> employeeList = new ArrayList<>();
        int i = 0;
        int breakpointForFillingTheList = 0;
        List<String> newEmployeesFullNameList = Generator.generateListOfFullNames(270);
        if (numberOfNewOperators > 0)    {
            breakpointForFillingTheList = numberOfNewOperators;
            while(i < breakpointForFillingTheList)  {
                employeeList.add(
                        new Operator(
                                newEmployeesFullNameList.get(i++),
                                company.getFixedPartOfPosition(Operator.class),
                                company
                        )
                );
            }
        }
        if (numberOfNewOperators + numberOfNewmanagers > 0)    {
            breakpointForFillingTheList += numberOfNewmanagers;
            while(i < breakpointForFillingTheList)  {
                employeeList.add(
                    new Manager(
                        newEmployeesFullNameList.get(i++),
                        company.getFixedPartOfPosition(Manager.class),
                        company)
                    );
            }
        }
        if (numberOfNewTopManagers > 0) {
            breakpointForFillingTheList += numberOfNewTopManagers;
            while (i < breakpointForFillingTheList) {
                employeeList.add(
                        new TopManager(
                                newEmployeesFullNameList.get(i++),
                                company.getFixedPartOfPosition(TopManager.class),
                                company
                        )
                );
            }
        }
        if (employeeList.size() > 0)
            return employeeList;
        else
            throw new InvalidDataRequestException();

    }

    public static List<String> generateListOfFullNames(int lenghtOfGeneratedList)  {
        List<String> listOfFullNames = new ArrayList<>();
        int i = 0;
        while (i < lenghtOfGeneratedList)   {
            String generatedFullName = generateFullName();
            if (!listOfFullNames.contains(generatedFullName)) {
                listOfFullNames.add(generatedFullName);
            } else  {
                continue;
            }
            i++;
        }
        return listOfFullNames;
    }

    public static String generateFullName()    {
        return lengthenStringContainsNewName(
                String.format("%s %s %s",
                        arrayOfNames[(int)(Math.random()*12)],
                        arrayOfMiddleNames[(int)(Math.random()*12)],
                        arrayOfSurnames[(int)(Math.random()*25)]),
                40
        );
    }

    public static String lengthenStringContainsNewName(String newName, int requiredLenthOfString)  {
        while(newName.length() < requiredLenthOfString)    {
            newName += " ";
        }
        return newName;
    }



}
