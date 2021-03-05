import java.io.File;

public class Main   {

    private static final int newWidth = 300;

    private static final String srcFolder = "D:/images/src";
    private static final String dstFolder = "D:/images/dst";
    private static final String threadGroupName = "MainThreadGroup";

    public static void main(String[] args) throws InterruptedException {

        int numberOfProcessorCores = Runtime.getRuntime().availableProcessors();
        long start = System.currentTimeMillis();

        ThreadGroup threadGroup = new ThreadGroup(threadGroupName);
        File srcDir = new File(srcFolder);
        File[] files = srcDir.listFiles();

        int lastPos = 0;
        for(int i = 1; i<=numberOfProcessorCores; i++)  {
            int thisPos = files.length * i / numberOfProcessorCores;
            createAndStartThread(threadGroup, files, thisPos - lastPos, lastPos, dstFolder);
            lastPos = thisPos;
        }

        while(threadGroup.activeCount() > 0)    {
            Thread.sleep(1);
        }

        System.out.println("Duration " + (System.currentTimeMillis() - start) + " ms");

    }

    private static void createAndStartThread(
        ThreadGroup threadGroup, File[] files, int threadFilesArraySize, int arrayCopyPos, String dstFolder
    )  {
        File[] filesForThread = new File[threadFilesArraySize];
        System.arraycopy(files, arrayCopyPos, filesForThread, 0, filesForThread.length);
        ImageResizer resizer = new ImageResizer(filesForThread, newWidth, dstFolder);
        new Thread(threadGroup, resizer).start();
    }

}