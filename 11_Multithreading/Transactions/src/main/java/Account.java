import java.util.Objects;

public class Account    {

    private long money;
    private String accNumber;
    private boolean blocked;

    public Account() {}

    public Account(long money, String accNumber, boolean blocked) {
        this.money = money;
        this.accNumber = accNumber;
        this.blocked = blocked;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void takeMoney(long amount)  {
        money = money - amount;
    }

    public void addMoney(long amount)   {
        money = money + amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return money == account.money &&
                blocked == account.blocked &&
                Objects.equals(accNumber, account.accNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(money, accNumber, blocked);
    }

    @Override
    public String toString() {
        return "Account{" +
                "money=" + money +
                ", accNumber='" + accNumber + '\'' +
                ", active=" + blocked +
                '}';
    }

}