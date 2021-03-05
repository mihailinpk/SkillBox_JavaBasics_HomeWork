package su.companies;

import su.abstractclassesandinterfaces.Employee;
import su.abstractclassesandinterfaces.EmployeesWithIncomeForCompany;
import su.exceptions.InvalidDataRequestException;
import su.typeofemployees.Manager;
import su.typeofemployees.Operator;
import su.typeofemployees.TopManager;

import java.util.*;

public class Company {

    public final double COMPANY_INCOME_FOR_TOPMANAGER_BONUS = 10_000_000;
    public final float PERCENT_OF_EARNER_MONEY_TO_MANAGER_BONUS = 0.05f;

    private String companyName;

    private Map<Class, Float> fixedParts = new HashMap<>();

    private List<Employee> staffList = new ArrayList<>();

    public Company(String companyName, float fixedPartOfTheOperatorsSalary, float fixedPartOfTheManagersSalary, float fixedPartOfTheTopManagersSalary) {
        this.companyName = companyName;
        setFixedPartOfPosition(Operator.class, fixedPartOfTheOperatorsSalary);
        setFixedPartOfPosition(Manager.class, fixedPartOfTheManagersSalary);
        setFixedPartOfPosition(TopManager.class, fixedPartOfTheTopManagersSalary);
    }

    public Company(String companyName, Map<Class, Float> fixedParts)    {
        this.companyName = companyName;
        this.fixedParts = fixedParts;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }


    public void setFixedPartOfPosition(Class position, float fixedPart) {
        fixedParts.put(position, fixedPart);
    }

    public float getFixedPartOfPosition(Class position) {
        return fixedParts.get(position);
    }

    public void removeFixedPartOfPosition(Class position)   {
        fixedParts.remove(position);
    }

    public void setFixedParts(Map<Class, Float> fixedParts) {
        this.fixedParts = fixedParts;
    }

    public List<Employee> getStaffList() {
        return staffList;
    }

    public double getIncome()   {
        double income = 0.0;
        for(Employee employee : staffList)  {
            if (employee instanceof EmployeesWithIncomeForCompany)   {
                income += ((EmployeesWithIncomeForCompany) employee).getIncomeForCompany();
            }
        }
        return income;
    }

    public void hire(Employee newEmoloyee)  {
        staffList.add(newEmoloyee);
    }

    public void hireAll(List<Employee> newEmployeeList) {
        staffList.addAll(newEmployeeList);
    }

    public void fire(Employee firingEmloyee)    {
        staffList.remove(firingEmloyee);
    }

    public List<Employee> getTopSalaryStaff(int count) throws InvalidDataRequestException {
        if (count > 0 && count <= staffList.size())  {
            List<Employee> sortedByDespendingStafflist = staffList;
            Collections.sort(sortedByDespendingStafflist);
            Collections.reverse(sortedByDespendingStafflist);
            return sortedByDespendingStafflist.subList(0, count);
        } else {
            throw new InvalidDataRequestException();
        }
    }

    public List<Employee> getLowestSalaryStaff(int count) throws InvalidDataRequestException {
        if (count > 0 && count <= staffList.size())  {
            List<Employee> sortedByAscendingStafflist = staffList;
            Collections.sort(sortedByAscendingStafflist);
            return sortedByAscendingStafflist.subList(0, count);
        } else {
            throw new InvalidDataRequestException();
        }
    }

    public void printStaffList() {
        int i = 0;
        for(Employee employee : staffList)  {
            System.out.printf("%02d. | %s | %s | %.2f руб.\n", ++i, employee.getFullName(), employee.getPositionInTheCompany(), employee.getMonthSalary());
        }
    }

}