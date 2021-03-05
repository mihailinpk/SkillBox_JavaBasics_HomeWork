package su.typeofemployees;

import su.abstractclassesandinterfaces.Employee;
import su.abstractclassesandinterfaces.EmployeesWithIncomeForCompany;
import su.companies.Company;
import su.tools.Generator;

public class Manager extends Employee implements EmployeesWithIncomeForCompany {

    private final static String POSITION = "Менеджер";

    public Manager(String fullName, float fixedPartOfSalary, Company companyWhereEmployeeWorks) {
        super(fullName, fixedPartOfSalary, POSITION, companyWhereEmployeeWorks);
    }

    @Override
    public float getMonthSalary() {
        return getFixedPartOfSalary() + getIncomeForCompany()*getCompany().PERCENT_OF_EARNER_MONEY_TO_MANAGER_BONUS;
    }

    @Override
    public float getIncomeForCompany() {
        return (float)(Generator.GENERATION_AMOUNT_OF_MONEY_EARNED_BY_MANAGER_START_VALUE +
                Math.random()*(Generator.GENERATION_AMOUNT_OF_MONEY_EARNED_BY_MANAGER_END_VALUE-Generator.GENERATION_AMOUNT_OF_MONEY_EARNED_BY_MANAGER_START_VALUE));

    }
}