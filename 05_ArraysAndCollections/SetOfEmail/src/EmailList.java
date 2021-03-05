import org.apache.commons.validator.routines.EmailValidator;

import java.util.Set;
import java.util.TreeSet;

public class EmailList {

    private static EmailList instance;

    private static Set<String> emailsSet = new TreeSet<>(){{
        add("one@anyemail.ru");
        add("two@mail.ru");
        add("second@ya.ru");
        add("solg@inbox.com");
    }};

    private EmailList() {}

    public static EmailList getInstance()   {
        if (instance == null)   {
            instance = new EmailList();
        }
        return instance;
    }

    public void printList()  {
        if (emailsSet.isEmpty()) {
            System.out.println("Список email-адресов пуст");
        }   else    {
            System.out.println("Список email-адресов:");
            for(String currentEmail : emailsSet)  {
                System.out.println(currentEmail);
            }
        }
        System.out.println();
    }

    public void add(String element) throws InvalidAddressEmailException {
        addOrDeleteElement(element, true);
    }

    public void delete(String element) throws InvalidAddressEmailException  {
        addOrDeleteElement(element, false);
    }

    private void addOrDeleteElement(String element, boolean addElement) throws InvalidAddressEmailException {
        if (addressIsCorrected(element))    {
            if (addElement) {
                System.out.println(emailsSet.add(element) ? "e-mail адрес удачно добавлен в список..." : "Добавить e-mail адрес в список не удалось...");
            }   else    {
                System.out.println(emailsSet.remove(element) ? "e-mail адрес удачно удален из списка..." : "Удалить e-mail адрес из списка не удалось...");
            }
        }   else    {
            throw new InvalidAddressEmailException();
        }

    }

    private boolean addressIsCorrected(String emailAddress)  {
        return EmailValidator.getInstance().isValid(emailAddress);
    }

}