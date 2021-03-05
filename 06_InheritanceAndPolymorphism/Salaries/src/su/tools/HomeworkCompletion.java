package su.tools;

import su.abstractclassesandinterfaces.Employee;
import su.companies.Company;
import su.exceptions.InvalidDataRequestException;

import java.util.List;

public class HomeworkCompletion {

    public static void homeworkComplete(int countLagrestSalaries, int countSmallestSalaries, int percentToLayOff, Company company) throws InvalidDataRequestException  {
        System.out.printf("------------------==================== %s ====================------------------", company.getCompanyName());
        company.hireAll(Generator.generateNewEmployeesList(180, 80, 10, company));
        printEmployeesWithLargestAndSmallestSalaries(countLagrestSalaries, countSmallestSalaries, company);
        layOffCertainPercentOfEmployess(percentToLayOff, company);
        printEmployeesWithLargestAndSmallestSalaries(countLagrestSalaries, countSmallestSalaries, company);
        System.out.print("\n\n");
    }

    private static void layOffCertainPercentOfEmployess(int percentToLayOff, Company company)   {
        List<Employee> companyStaffList = company.getStaffList();
        int numberOfEmployeesBeforeReduction = companyStaffList.size();
        for (int j=0; j < numberOfEmployeesBeforeReduction*((float)percentToLayOff/100); j++)  {
            company.fire(companyStaffList.get((int)(Math.random()*companyStaffList.size())));
        }
        System.out.printf("\nМы сократили %d%% процентов сотрудников :(\n", percentToLayOff);
    }

    private static void printEmployeesWithLargestAndSmallestSalaries(int countLagrestSalaries, int countSmallestSalaries, Company company) throws InvalidDataRequestException {
        System.out.printf("\n%d сотрудников с самыми высокими зарплатами:\n", countLagrestSalaries);
        printSeparator();
        int i = 0;
        for (Employee employee : company.getTopSalaryStaff(countLagrestSalaries)) {
            System.out.printf("%02d. | %s | %s | %.2f руб.\n",
                    ++i,
                    employee.getFullName(),
                    Generator.lengthenStringContainsNewName(employee.getPositionInTheCompany(), 12),
                    employee.getMonthSalary()
            );
        }
        printSeparator();
        i = 0;
        System.out.printf("\n%d сотрудников с самыми низкими зарплатами:\n", countSmallestSalaries);
        printSeparator();
        for (Employee employee : company.getLowestSalaryStaff(countSmallestSalaries)) {
            System.out.printf("%02d. | %s | %s | %.2f руб.\n",
                    ++i,
                    employee.getFullName(),
                    Generator.lengthenStringContainsNewName(employee.getPositionInTheCompany(), 12),
                    employee.getMonthSalary()
            );
        }
        printSeparator();

    }

    private static void printSeparator()    {
        System.out.println("----+------------------------------------------+--------------+-----------------");
    }

}