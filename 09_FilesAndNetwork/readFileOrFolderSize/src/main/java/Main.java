import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    private static final long BYTES_TO_KILOBYTE = 1024;
    private static final long BYTES_TO_MEGABYTE = 1048576;
    private static final long BYTES_TO_GIGABYTE = 1073741824;

    public static void main(String[] args) {

        while (true) {
            try {
                System.out.print("Введите путь к папке или файлу: ");
                String line = UserInput.getLine();
                if (line.equals("exit")) {
                    break;
                }
                performTheTask(line);
            } catch (FileNotFoundException ex)  {
                ex.printStackTrace();
                System.out.println("Такого файла или папки не существует...\nПопробуйте еще раз !");
            } catch (Exception ex)  {
                ex.printStackTrace();
                System.out.println("Произошла ошибка...\nПопробуйте еще раз !");
            }
        }

        System.out.print("Программа успешно завершила работу...");

    }

    private static void performTheTask(String line) throws Exception   {
        Path path = Paths.get(line);
        if (Files.exists(path)) {
            if (Files.isDirectory(path))    {
                System.out.println(convertToHumanReadableFormat(calculateTheSizeOfFolder(path), false));
            } else if (Files.isRegularFile(path)) {
                System.out.println(convertToHumanReadableFormat(Files.size(path), true));
            } else {
                throw new Exception();
            }
        } else if (Files.notExists(path))   {
            throw new FileNotFoundException();
        } else  {
            throw new Exception();
        }
    }

    private static long calculateTheSizeOfFolder(Path path) throws IOException {
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
        long filesSize = 0;
        for (Path fileOrFolder : directoryStream) {
            if (Files.isDirectory(fileOrFolder)) {
                filesSize += calculateTheSizeOfFolder(fileOrFolder);
            } else if (Files.isRegularFile(fileOrFolder)) {
                filesSize += Files.size(fileOrFolder);
            }
        }
        return filesSize;
    }

    private static String convertToHumanReadableFormat(long sizeInBytes, boolean isFile)    {
        String type = (isFile ? "файла" : "папки");
        if (sizeInBytes > BYTES_TO_KILOBYTE && sizeInBytes <= BYTES_TO_MEGABYTE)    {
            return String.format("Размер %s: %.2f килобайт", type, (float)sizeInBytes / (float)BYTES_TO_KILOBYTE);
        } else if (sizeInBytes > BYTES_TO_MEGABYTE && sizeInBytes <= BYTES_TO_GIGABYTE) {
            return String.format("Размер %s: %.2f мегабайт", type, (float)sizeInBytes / (float)BYTES_TO_MEGABYTE);
        } else if (sizeInBytes > BYTES_TO_GIGABYTE) {
            return String.format("Размер %s: %.2f гигабайт", type, (float)sizeInBytes /(float)BYTES_TO_GIGABYTE);
        } else  {
            return String.format("Размер %s: %d байт", isFile, sizeInBytes);
        }
    }

}