import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private final static String FILE_FOR_PARSING_FILE = "CSV_files/movementList.csv";
    private static Map<String, Double> breakDownOfCostsMap = new HashMap<>();
    private static double sumReceive = 0.0;
    private static double sumSpend = 0.0;

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(FILE_FOR_PARSING_FILE)));
            List<String[]> bankStatementList = new ArrayList<>();
            fillBankStatementList(bankStatementList, bufferedReader);
            performTask(bankStatementList);
            printResult();
        } catch (Exception ex)  {
            ex.printStackTrace();
        }
    }

    private static void fillBankStatementList(List<String[]> bankStatementList, BufferedReader bufferedReader) throws IOException {
        String line = null;
        while((line = bufferedReader.readLine()) != null) {
            line = prepareLineForSplit(line);
            bankStatementList.add(line.split(","));
        }
        bankStatementList.remove(0);
    }

    private static String prepareLineForSplit(String line)  {
        char[] chars = line.toCharArray();
        boolean nowIsQuotedCell = false;
        for(int i=0; i<chars.length; i++)   {
            if (chars[i] == '"')    {
                nowIsQuotedCell = !nowIsQuotedCell;
            }
            if (nowIsQuotedCell && (chars[i] == ','))    {
                chars[i] = '.';
            }
        }
        line = "";
        for(Character ch : chars)   {
            line += ch;
        }
        return line.replaceAll("\"", "");
    }

    private static void performTask(List<String[]> bankStatementList)   {
        for(String[] array : bankStatementList) {
            sumReceive += Double.parseDouble(array[6]);
            double thisSpend = Double.parseDouble(array[7]);
            sumSpend += thisSpend;
            String organizationName = array[5].split("[/\\u005c]", 3)[2];
            organizationName = organizationName.substring(0, organizationName.indexOf("          "));
            if (breakDownOfCostsMap.containsKey(organizationName))  {
                breakDownOfCostsMap.put(organizationName, breakDownOfCostsMap.get(organizationName));
            } else {
                breakDownOfCostsMap.put(organizationName, thisSpend);
            }
        }
    }

    private static void printResult()   {
        System.out.printf("Сумма доходов : %15.2f руб.\n", sumReceive);
        System.out.printf("Сумма расходов: %15.2f руб.\n", sumSpend);
        System.out.println("\nСуммы расходов по организациям:");
        for (Map.Entry<String, Double> pair : breakDownOfCostsMap.entrySet())   {
            System.out.println(pair.getKey() + "  :  " + pair.getValue() + " руб.");
        }
    }

}