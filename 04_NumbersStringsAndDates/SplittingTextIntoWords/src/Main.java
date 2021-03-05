public class Main {

    public static void main(String[] args) {

        String textFromFoxNews = "Bloomberg, who briefly joined the race for the Democratic presidential nomination earlier this year, has reportedly raised more\n" +
                "than $16 million for the Florida Rights Restoration Coalition.\n" +
                "Under the Florida state constitution, convicted felons can regain their voting rights after having served their time. However, a\n" +
                "law enacted by Republican Gov. Ron DeSantis states that felons must pay all fines, restitution, and other legal financial obligations\n" +
                "before their sentences could be considered fully served.\n" +
                "\"[Under Florida law] it’s a third-degree felony for someone to either directly or indirectly provide something of value to\n" +
                "impact whether or not someone votes,\" Gaetz explained. \"So the question is whether or not paying off someone’s fines and\n" +
                "legal obligations counts as something of value, and it clearly does.";

        String[] onlyWordsInText = textFromFoxNews.replaceAll("\\p{Punct}", "").split("\\s+");
        int i = 0;
        for(String currentWord : onlyWordsInText)   {
            System.out.printf("word %s: %s\n", ++i, currentWord);
        }



    }

}