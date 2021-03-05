import su.companies.Company;
import su.tools.HomeworkCompletion;

public class Main {

    public static void main(String[] args) {

        try {
            /*  компания "Газпром"   */
            HomeworkCompletion.homeworkComplete(15, 30, 50,
                    new Company("Газпром", 20_000f, 25_000f, 500_000f));
            /*  компания "Роснефть" */
            HomeworkCompletion.homeworkComplete(14, 31, 60,
                    new Company("Роснефть", 15_000f, 30_000f, 450_000f));
            /*  компания "ЛукОйл"   */
            HomeworkCompletion.homeworkComplete(16, 19, 40,
                    new Company("ЛукОйл", 18_000f, 29_000f, 650_000f));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}