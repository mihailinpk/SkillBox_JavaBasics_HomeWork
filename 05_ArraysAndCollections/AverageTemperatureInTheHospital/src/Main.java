public class Main {

    private static int NUMBER_OF_PATIENTS = 30;
    private static float MIN_TEMP_OF_PATIENT = 32.0f;
    private static float MAX_TEMP_OF_PATIENT = 40.0f;
    private static float MIN_TEMP_OF_HEALTHY_PATIENT = 36.2f;
    private static float MAX_TEMP_OF_HEALTHY_PATIENT = 36.9f;

    public static void main(String[] args) {

        float[] temperatureArray = generateTemperatureArray(NUMBER_OF_PATIENTS, MIN_TEMP_OF_PATIENT, MAX_TEMP_OF_PATIENT);

        System.out.print("Температуры пациентов: ");
        for(float currentTemperature: temperatureArray) {
            System.out.printf("%.2f " , currentTemperature);
        }
        System.out.printf("\nСредняя температура: %.2f", calculateAverageTemperature(temperatureArray));
        System.out.println("\nКоличество здоровых: " + calculateHealthyPatients(temperatureArray, MIN_TEMP_OF_HEALTHY_PATIENT, MAX_TEMP_OF_HEALTHY_PATIENT));

    }

    private static float[] generateTemperatureArray(int numberOfPatients, float minTempOfPatient, float maxTempOfPatient)   {
        float[] temperatureArray = new float[numberOfPatients];
        for(int i=0; i<numberOfPatients; i++)   {
            temperatureArray[i] = (float) (minTempOfPatient + Math.random()*(maxTempOfPatient-minTempOfPatient));
        }
        return temperatureArray;
    }

    private static float calculateAverageTemperature(float[] temperatureArray)  {
        float sumOfTemperatures = 0;
        for(float currentTemperature : temperatureArray)    {
            sumOfTemperatures += currentTemperature;
        }
        return sumOfTemperatures/temperatureArray.length;
    }

    private static int calculateHealthyPatients(float[] temperatureArray, float minTempOfHealthyPatient, float maxTempOfHealthyPatient)    {
        int counterOfHealthyPatients = 0;
        for(float currentTemperature : temperatureArray)    {
            counterOfHealthyPatients += (currentTemperature>=minTempOfHealthyPatient && currentTemperature<=maxTempOfHealthyPatient ? 1 : 0);
        }
        return counterOfHealthyPatients;
    }

}