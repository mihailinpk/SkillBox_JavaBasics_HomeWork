import java.util.*;

public class BeautifulNumbersList {

    private static BeautifulNumbersList instance;

    private static List<String> beautifulNumbersList = new ArrayList<>();
    private static HashSet<String> hashSet = new HashSet<>();
    private static TreeSet<String> treeSet = new TreeSet<>();

    private BeautifulNumbersList()  {
        beautifulNumbersListGenerate();
        hashSet.addAll(beautifulNumbersList);
        treeSet.addAll(beautifulNumbersList);
    }

    public static BeautifulNumbersList getInstance()    {
        if (instance == null)   {
            instance = new BeautifulNumbersList();
        }
        return instance;
    }

    private void beautifulNumbersListGenerate()  {
        char[] lettersArray = new char[]{'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};
        String[] numbersArray = new String[9];
        for (byte j = 1; j<=9; j++)  {
            numbersArray[j-1] = j + "" + j + "" + j;
        }
        for (short i=1; i<=199; i++)    { // коды регионов
            for (byte j=0; j<lettersArray.length; j++)  { // буквы 1
                for(byte x=0; x<lettersArray.length; x++)   { // буквы 2
                    for (byte z=1; z<=9; z++)   { // номера
                        for(byte y=0; y<lettersArray.length; y++)   { // буквы 3
                            beautifulNumbersList.add(
                                    lettersArray[y] + numbersArray[z-1] + lettersArray[x] + lettersArray[j] + String.format("%02d", i) + "RUS"
                            );
                        }
                    }
                }
            }
        }
        Collections.sort(beautifulNumbersList);
    }

    public void printBeautifulNumberslist() {
        long i = 0;
        for(String number : beautifulNumbersList)   {
            System.out.println(++i + ". " + number);
        }
    }

    public String getElement(int i) {
        return beautifulNumbersList.get(i);
    }

    public boolean directIterationSearch(String inputLine)   {
        return beautifulNumbersList.contains(inputLine);
    }

    public int binarySearch(String inputLine)   {
        return Collections.binarySearch(beautifulNumbersList, inputLine);
    }

    public boolean hashSetSearch(String inputLine)  {
        return hashSet.contains(inputLine);
    }

    public boolean treeSetSearch(String inputLine)  {
        return treeSet.contains(inputLine);
    }

}