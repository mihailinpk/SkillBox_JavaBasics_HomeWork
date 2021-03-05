import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        while (true)    {
            try {
                Path sourceFolderPath = getFolderPath(true);
                Path targetFolderPath = getFolderPath(false);
                copyFolder(sourceFolderPath, targetFolderPath);
            } catch (Exception ex)  {
                ex.printStackTrace();
                System.out.println("При копировании произошла ошибка...\n" +
                        "Проверьте корректность имен папко и попробуйте еще раз !");
            }
        }
    }

    private static void copyFolder(Path sourceFolderPath, Path targetFolderPath) throws IOException {
        if (Files.notExists(targetFolderPath))  {
            Files.createDirectory(targetFolderPath);
        }
        DirectoryStream<Path> stream = Files.newDirectoryStream(sourceFolderPath);
        for(Path pathFileOrFolder : stream) {
            if (Files.isRegularFile(pathFileOrFolder))  {
                Files.copy(pathFileOrFolder, Paths.get(targetFolderPath.toString() + "\\" +
                        pathFileOrFolder.getFileName()));
            }
            if (Files.isDirectory(pathFileOrFolder))    {
                copyFolder(
                    Paths.get(sourceFolderPath.toString() + "\\" + pathFileOrFolder.getFileName()),
                    Paths.get(targetFolderPath.toString() + "\\" + pathFileOrFolder.getFileName())
                );
            }
        }
    }

    private static Path getFolderPath(boolean toSourceFolder) {
        while (true)    {
            try {
                System.out.printf("Введите путь %s\n", (toSourceFolder ? "копируемой папки:" : "целевой папки:"));
                Path folderPath = Paths.get(UserInput.getLine());
                if (toSourceFolder) {
                    if (Files.exists(folderPath) && Files.isDirectory(folderPath)) {
                        return folderPath;
                    } else {
                        throw new FileNotFoundException();
                    }
                } else  {
                    return folderPath;
                }
            } catch (FileNotFoundException ex)  {
                ex.printStackTrace();
                System.out.println("Такой папки не существует...\nПопробуйте еще раз !");
            }
        }
    }

}