

import java.util.HashMap;

public class CustomerStorage    {
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws ConsoleCustomerListException    {
        String[] components = data.split("\\s+");
        Checks.checkingNumberOfItemsInCommand((byte) 4, components);
        String name = components[0] + " " + components[1];
        Checks.checkingName(name);
        Checks.checkingEmailAddress(components[2]);
        Checks.checkingPhoneNumber(components[3]);
        storage.put(name, new Customer(name, formattingThePhoneNumber(components[3]), components[2]));
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }

    private static String formattingThePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 11)    {
            phoneNumber = phoneNumber.substring(1);
        }
        return phoneNumber.replaceAll("(\\d{3})(\\d{3})(\\d+)", "+7($1)$2$3");
    }

}