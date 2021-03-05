package su.typeofemployees;

import su.abstractclassesandinterfaces.Employee;
import su.companies.Company;

public class Operator extends Employee {

    private final static String POSITION = "Оператор";

    public Operator(String fullName, float fixedPartOfTheOperatorsSalary, Company companyWhereEmployeeWorks) {
        super(fullName, fixedPartOfTheOperatorsSalary, POSITION, companyWhereEmployeeWorks);
    }

    @Override
    public float getMonthSalary() {
        return getFixedPartOfSalary();
    }

}