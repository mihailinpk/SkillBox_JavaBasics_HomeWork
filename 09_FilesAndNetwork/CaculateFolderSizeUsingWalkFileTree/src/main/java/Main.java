import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class Main {

    private static long fileOrFolderSize;

    public static long getFileOrFolderSize() {
        return fileOrFolderSize;
    }

    public static void setFileOrFolderSize(long fileOrFolderSize) {
        Main.fileOrFolderSize = fileOrFolderSize;
    }

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.print("Введите путь к папке или файлу: ");
                String line = UserInput.getLine();
                if (line.equals("exit")) {
                    break;
                }
                Files.walkFileTree(Paths.get(line), new CalculateFolderSizeFileVisitor());
                System.out.println(convertToHumanReadableFormat(fileOrFolderSize));
                fileOrFolderSize = 0;
            } catch (Exception ex)  {
                ex.printStackTrace();
                System.out.println("Произошла ошибка...\nПопробуйте еще раз !");
            }
        }
        System.out.print("Программа успешно завершила работу...");
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