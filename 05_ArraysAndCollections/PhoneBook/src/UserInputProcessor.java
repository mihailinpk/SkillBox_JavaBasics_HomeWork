public class UserInputProcessor {

    public static boolean userInputIsName(String userInput)    {
        return userInput.matches("[A-Za-zА-Яа-яЁё]+(\\s+[A-Za-zА-Яа-яЁё]+)?");
    }

    public static boolean userInputIsNumber(String userInput)  {
        switch (userInput.length())  {
            case 11 -> {
                if (userInput.matches("[78]{1}\\d+"))   {
                        return true;
                    }
            }
            case 10 -> {
                if (userInput.matches("\\d+")) {
                        return true;
                    }
            }
        }
        return false;
    }

    public static String formattingThePhoneNumber(String inputPhoneNumber) {
        if (inputPhoneNumber.length() == 11)    {
            inputPhoneNumber = inputPhoneNumber.substring(1);
        }
        return inputPhoneNumber.replaceAll("(\\d{3})(\\d{3})(\\d+)", "+7($1)$2$3");
    }

}