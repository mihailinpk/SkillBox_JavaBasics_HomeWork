import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer implements Runnable    {

    private File[] files;
    private int newWidth;
    private String dstFolder;

    public ImageResizer(File[] files, int newWidth, String dstFolder) {
        this.files = files;
        this.newWidth = newWidth;
        this.dstFolder = dstFolder;
    }

    @Override
    public void run() {

        try {
            for(File file : files) {
                BufferedImage image = ImageIO.read(file);
                if (image == null) {
                    continue;
                }
                image = Scalr.resize(image, newWidth);
                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(image, "jpg", newFile);
            }
        }
        catch (Exception ex)    {
            ex.printStackTrace();
        }

    }

}