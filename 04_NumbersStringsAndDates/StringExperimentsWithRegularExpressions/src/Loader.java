public class Loader {

    public static void main(String[] args) {

        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        System.out.println(text);
        String[] salaries = text.replaceAll("[^0-9 ]", "").trim().split("\\s+");
        int totalSalaries = 0;
        for(String line: salaries)  {
            totalSalaries += Integer.parseInt(line);
        }

        System.out.printf("Общий заработок составил: %s рублей", totalSalaries);

    }

}