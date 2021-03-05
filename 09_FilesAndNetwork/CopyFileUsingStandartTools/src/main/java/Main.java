import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        while (true)    {
            try {
                File sourceFile = getFile(true);
                File targetFile = getFile(false);
                copyFolder(sourceFile, targetFile);
            } catch (Exception ex)  {
                ex.printStackTrace();
                System.out.println("При работе программы произошла ошибка...\n" +
                        "Проверьте корректность имен папок и попробуйте еще раз !");
            }
        }
    }

    private static void copyFolder(File sourceFile, File targetFile) throws IOException {
        ArrayList<File> sourceFilesList = new ArrayList<>(Arrays.asList(sourceFile.listFiles()));
        int lastIndex = 0;
        label :
        while(true) {
            for(int i = lastIndex; i<sourceFilesList.size(); i++)    {
                if (sourceFilesList.get(i).isDirectory())    {
                    lastIndex = i;
                    sourceFilesList.addAll(new ArrayList<>(Arrays.asList(sourceFilesList.get(i).listFiles())));
                    sourceFilesList.remove(sourceFilesList.get(i));
                    break;
                }
                FileInputStream reader = new FileInputStream(sourceFilesList.get(i));
                File fileForWrite = new File(targetFile.getAbsolutePath() +
                        sourceFilesList.get(i).getAbsolutePath().substring(sourceFile.getAbsolutePath().length()));
                new File(fileForWrite.getParent()).mkdirs();
                new File(fileForWrite.getName()).createNewFile();
                FileOutputStream writer = new FileOutputStream(fileForWrite);
                while(reader.available() > 0)  {
                    writer.write(reader.read());
                }
                reader.close();
                writer.flush();
                writer.close();
                if (i == sourceFilesList.size() - 1) {
                    break label;
                }
            }
        }
    }

    private static File getFile(boolean toSourceFolder) {
        while (true)    {
            try {
                System.out.printf("Введите путь %s\n", (toSourceFolder ? "копируемой папки:" : "целевой папки:"));
                File file = new File(UserInput.getLine());
                if (toSourceFolder) {
                    if (file.exists() && file.isDirectory()) {
                        return file;
                    } else {
                        throw new FileNotFoundException();
                    }
                } else  {
                    return file;
                }
            } catch (FileNotFoundException ex)  {
                ex.printStackTrace();
                System.out.println("Такой папки не существует...\nПопробуйте еще раз !");
            }
        }
    }

}