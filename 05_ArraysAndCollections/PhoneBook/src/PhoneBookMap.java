import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class PhoneBookMap {

    private static PhoneBookMap instance;

    private static Map<String, String> phoneBookMap = new TreeMap<>(){{
        put("+7(345)6543215", "Пупкин Вася");
        put("+7(952)4578951", "Пин Лосяшич");
        put("+7(524)8749658", "Крош Ёжиков");
        put("+7(547)1234578", "Нюша Совуньина");
    }};

    private PhoneBookMap()  {}

    public static PhoneBookMap getInstance()    {
        if (instance == null)   {
            instance = new PhoneBookMap();
        }
        return instance;
    }

    public void printMap()  {
        if (phoneBookMap.isEmpty()) {
            System.out.println("Телефонная книга пуста...");
        }   else    {
            System.out.println("+-------------------------------------------------+");
            System.out.println("              Телефонная книга                     ");
            printTableColumnHeader();
            TreeSet<String > setForDisplay = new TreeSet<>();
            for (String number : phoneBookMap.keySet()) {
                String currentName = lengthenStringContainsCurrentName(phoneBookMap.get(number));
                setForDisplay.add("  " + currentName + "|     " + number);
            }
            for (String line : setForDisplay)   {
                System.out.println(line);
            }
            System.out.println("+--------------------+----------------------------+");
        }
        System.out.println();
    }

    public void putNewEntry(String number, String name) {
        phoneBookMap.put(number, name);
    }

    public void removeEntryByNumber(String number)  {
        phoneBookMap.remove(number);
    }

    public void removeEntryByName(String name)  {
        while (true)    {
            if (phoneBookMap.containsValue(name)) {
                for(String number : phoneBookMap.keySet())  {
                    if (phoneBookMap.get(number).equals(name))  {
                        phoneBookMap.remove(number);
                        break;
                    }
                }
            } else  {
                break;
            }
        }
    }

    public boolean containsEntry(String checkedValue)   {
        return phoneBookMap.containsKey(checkedValue) || phoneBookMap.containsValue(checkedValue);
    }

    public boolean containsEntryByNumber(String checkedValue)   {
        return phoneBookMap.containsKey(checkedValue);
    }

    public boolean containsEntryByName(String checkedValue) {
        return phoneBookMap.containsValue(checkedValue);
    }

    public void printEntryByNumber(String number , boolean usePrintTableColumnHeader)   {
        if (usePrintTableColumnHeader)  {
            printTableColumnHeader();
        }
        String currentName = lengthenStringContainsCurrentName(phoneBookMap.get(number));
        System.out.println("  " + currentName + "|     " + number);
        if (usePrintTableColumnHeader)  {
            System.out.println("+--------------------+----------------------------+");
        }
    }

    public void printEntryByName(String name)   {
        printTableColumnHeader();
        for(String number : phoneBookMap.keySet())  {
            if (phoneBookMap.get(number).equals(name))  {
                printEntryByNumber(number, false);
            }
        }
        System.out.println("+--------------------+----------------------------+");
    }

    private void printTableColumnHeader()   {
        System.out.println("+--------------------+----------------------------+");
        System.out.println("         Имя         |         Номер              ");
        System.out.println("+--------------------+----------------------------+");
    }

    private String lengthenStringContainsCurrentName(String currentName)  {
        while(currentName.length() < 19)    {
            currentName += " ";
        }
        return currentName;
    }

}
