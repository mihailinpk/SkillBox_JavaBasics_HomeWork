public class Main {

    public static void main(String[] args) {

        String[] verse = {"Каждый", "охотник", "желает", "знать,", "где", "сидит", "фазан"};

        verse = (String[]) reverseArray(verse);

        for(String line : verse)    {
            System.out.println(line);
        }

    }

    private static Object[] reverseArray(Object[] inputArray)    {

        Object buffer;
        for(int i=0; i<inputArray.length/2; i++)  {
            buffer = inputArray[inputArray.length - 1 - i];
            inputArray[inputArray.length - 1 - i] = inputArray[i];
            inputArray[i] = buffer;
        }

        return inputArray;

    }

}