package ti.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImgIO {

    public Boolean save(String path, File image){

        BufferedImage bImage = null;

        try {

            bImage = ImageIO.read(image);

            ImageIO.write(bImage, "png", new File(path +"/image.png"));

        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
        System.out.println("Images were written succesfully.");


        return true;

    };
}
