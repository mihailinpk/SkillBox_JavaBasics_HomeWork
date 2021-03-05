package su.abstractclassesandinterfaces;

import su.companies.Company;

public abstract class Employee implements PaidingSalary, Comparable<Employee> {

    private final String fullName;
    private final float fixedPartOfSalary;
    private final String positionInTheCompany;
    private Company company;

    public Employee(String fullName, float fixedPartOfSalary, String positionInTheCompany, Company company) {
        this.fullName = fullName;
        this.fixedPartOfSalary = fixedPartOfSalary;
        this.positionInTheCompany = positionInTheCompany;
        this.company = company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public String getFullName() {
        return fullName;
    }

    public float getFixedPartOfSalary() {
        return fixedPartOfSalary;
    }

    public String getPositionInTheCompany() {
        return positionInTheCompany;
    }

    @Override
    public int compareTo(Employee nextEmployee) {

        return (int) (this.getMonthSalary() - nextEmployee.getMonthSalary());

    }

}