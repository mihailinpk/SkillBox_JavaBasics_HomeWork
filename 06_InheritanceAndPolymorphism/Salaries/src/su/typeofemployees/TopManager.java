package su.typeofemployees;

import su.abstractclassesandinterfaces.Employee;
import su.companies.Company;

public class TopManager extends Employee {

    private final static String POSITION = "Топ-менеджер";

    public TopManager(String fullName, float fixedPartOfSalary, Company companyWhereEmployeeWorks) {
        super(fullName, fixedPartOfSalary, POSITION, companyWhereEmployeeWorks);
    }

    @Override
    public float getMonthSalary() {
        if (getCompany().getIncome() > getCompany().COMPANY_INCOME_FOR_TOPMANAGER_BONUS) {
            return getFixedPartOfSalary() * 2.5f;
        } else
            return getFixedPartOfSalary();

    }

}