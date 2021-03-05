import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.print("Введите путь к папке или файлу: ");
                String line = UserInput.getLine();
                if (line.equals("exit")) {
                    break;
                }
                System.out.println(convertToHumanReadableFormat(
                    calculateFileOrFolderSize(new File(line))
                ));

            } catch (Exception ex)  {
                ex.printStackTrace();
                System.out.println("Произошла ошибка...\nПопробуйте еще раз !");
            }
        }
        System.out.print("Программа успешно завершила работу...");
    }

    private static long calculateFileOrFolderSize(File folderOrFilePath)    {
        ArrayList<File> filesOrFoldersList = new ArrayList<>(Arrays.asList(folderOrFilePath.listFiles()));
        long fileOrFolderSize = 0;
        int lastIndex = 0;
        label :
        while(true) {
            for(int i = lastIndex; i<filesOrFoldersList.size(); i++)    {
                if (filesOrFoldersList.get(i).isFile()) {
                    fileOrFolderSize += filesOrFoldersList.get(i).length();
                }
                if (filesOrFoldersList.get(i).isDirectory())    {
                    lastIndex = i;
                    filesOrFoldersList.addAll(new ArrayList<>(Arrays.asList(
                        filesOrFoldersList.get(i).listFiles()))
                    );
                    filesOrFoldersList.remove(filesOrFoldersList.get(i));
                    break;
                }
                if (i == filesOrFoldersList.size() - 1) {
                    break label;
                }
            }
        }
        return fileOrFolderSize;
    }

    private static String convertToHumanReadableFormat(long sizeInBytes) throws Exception   {
        if (sizeInBytes <= 0)   {
            throw new Exception();
        }
        final String[] units = new String[]{"байт", "килобайт", "мегабайт", "гигабайт", "терабайт"};
        int digitGroups = (int)(Math.log10(sizeInBytes) / Math.log10(1024));
        return "Размер: " + new DecimalFormat("#,##0.#").format(
            sizeInBytes/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

}