package duplicate_image_remover;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

public class CompareImages {
    File[] imgFile = new File[2];
    int[] rawImgHeight = new int[2];
    int[] rawImgWidth = new int[2];
    BufferedImage[] imgBuffer = new BufferedImage[2];
    
    private boolean imagesAreEqualSize(int height1, int height2, int width1, int width2) {
        if (height1 != height2) { return false; }
        else if (width1 != width2) { return false; }
        else { return true; }
    }
    public boolean imagesAreProportional() {
        double roomForError = getProportionError();
        float result = ((float) rawImgHeight[0] / rawImgWidth[0]) -
                       ((float) rawImgHeight[1] / rawImgWidth[1]);
        if (result < 0) { result *= -1; }
        
        return result < roomForError;
    }
    public double getProportionError() { return 0.03; }
    private boolean firstImageIsLarger() {
        return imgBuffer[0].getWidth() > imgBuffer[1].getWidth();
    }
    private void setImageHeightAndWidth(File imgFile, int num) {
        try //Get the image height and width quickly.
        {
            ImageInputStream in = ImageIO.createImageInputStream(imgFile);
            Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
            if (readers.hasNext())
            {
                ImageReader reader = readers.next();
                reader.setInput(in);
                rawImgHeight[num] = reader.getHeight(0);
                rawImgWidth[num] = reader.getWidth(0);
            }
        }
        catch (Exception ex) //If getting the image height and width failed, try again using the slow, more reliable method.
        {
            ImageIcon imgIcon = new ImageIcon(imgFile.getAbsolutePath());
            rawImgHeight[num] = imgIcon.getIconHeight();
            rawImgWidth[num] = imgIcon.getIconWidth();
        }
    }
    
    private Color getPixelColor(int pixRGB) {
        //This code is by Black Shadow on StackOverflow.
        //https://stackoverflow.com/a/22391906
        
        int red = (pixRGB & 0x00ff0000) >> 16;
        int green = (pixRGB & 0x0000ff00) >> 8;
        int blue = pixRGB & 0x000000ff;
        return new Color(red, green, blue);
    }
    private Color getSubtractedColor(Color col1, Color col2) {
        int red, green, blue;
        red = col1.getRed() - col2.getRed();
        green = col1.getGreen() - col2.getGreen();
        blue = col1.getBlue() - col2.getBlue();
        if (red < 0) { red *= -1; }
        if (green < 0) { green *= -1; }
        if (blue < 0) { blue *= -1; }
        return new Color(red, green, blue);
    }
    private Image getScaledImage(Image srcImg, int w, int h) {
        //This function's code is by user Suken Shah on StackOverflow.
        //https://stackoverflow.com/a/6714381
        
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        
        return resizedImg;
    } 
    
    // === === === PUBLIC ENUMS AND FUNCTIONS === === ===
    
    public enum CompareMethod {
        BASIC,
        SUBTRACT_COLOR
    }
    public enum FileNum {
        FIRST,
        SECOND
    }
    
    public boolean checkIfValidImage(File checkFile) {
        try
        {
            String fileType = Files.probeContentType(checkFile.toPath());
            if (fileType.compareToIgnoreCase("image/jpeg") != 0 && fileType.compareToIgnoreCase("image/png") != 0)
            {
                return false;
            }
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }
    
    public double getHeightWidthRatio(double height, double width) { return height / width; }
    public int getHeightFromFile(File imgFile) {
        if (checkIfValidImage(imgFile))
        {
            int ratio = 0;
            try //Get the image height and width quickly.
            {
                ImageInputStream in = ImageIO.createImageInputStream(imgFile);
                Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
                if (readers.hasNext())
                {
                    ImageReader reader = readers.next();
                    reader.setInput(in);
                    ratio = reader.getHeight(0);
                }
            }
            catch (Exception ex) //If getting the image height and width failed, try again using the slow, more reliable method.
            {
                ImageIcon imgIcon = new ImageIcon(imgFile.getAbsolutePath());
                ratio = imgIcon.getIconHeight();
            }
            return ratio;
        }
        else
        {
            return -1;
        }
    }
    public int getWidthFromFile(File imgFile) {
        if (checkIfValidImage(imgFile))
        {
            int ratio = 0;
            try //Get the image height and width quickly.
            {
                ImageInputStream in = ImageIO.createImageInputStream(imgFile);
                Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
                if (readers.hasNext())
                {
                    ImageReader reader = readers.next();
                    reader.setInput(in);
                    ratio = reader.getWidth(0);
                }
            }
            catch (Exception ex) //If getting the image height and width failed, try again using the slow, more reliable method.
            {
                ImageIcon imgIcon = new ImageIcon(imgFile.getAbsolutePath());
                ratio = imgIcon.getIconWidth();
            }
            return ratio;
        }
        else
        {
            return -1;
        }
    }
    public double getHeightWidthRatioFromFile(File imgFile) {
        if (checkIfValidImage(imgFile))
        {
            double ratio = 0.0;
            try //Get the image height and width quickly.
            {
                ImageInputStream in = ImageIO.createImageInputStream(imgFile);
                Iterator<ImageReader> readers = ImageIO.getImageReaders(in);
                if (readers.hasNext())
                {
                    ImageReader reader = readers.next();
                    reader.setInput(in);
                    ratio = (double) reader.getHeight(0) / (double) reader.getWidth(0);
                }
            }
            catch (Exception ex) //If getting the image height and width failed, try again using the slow, more reliable method.
            {
                ImageIcon imgIcon = new ImageIcon(imgFile.getAbsolutePath());
                ratio = (double) imgIcon.getIconHeight() / (double) imgIcon.getIconWidth();
            }
            return ratio;
        }
        else
        {
            return -1;
        }
    }
    
    public BufferedImage importImage(File imgFile) throws IOException {
        if (!imgFile.exists()) { throw new IOException("The image file provided does not exist."); }
        if (!checkIfValidImage(imgFile)) { throw new IOException("The file provided to the 'importImage' function is not valid."); }
        
        ImageIcon imgIcon = new ImageIcon(imgFile.getAbsolutePath());
        if (imgIcon.getIconWidth() < 1 || imgIcon.getIconHeight() < 1)
        {
            //The file could not be imported correctly.
            //This is probably because it's not a PNG or JPEG, but rather something disguised as one.
            //Have the file removed from the image list.
            System.out.println("Invalid file found. Throwing -1.");
            throw new IOException("-1"); 
        }
        
        double tempHeight = imgIcon.getIconHeight(), tempWidth = imgIcon.getIconWidth();
        int setWidth = (int) tempWidth, setHeight = (int) tempHeight;
        
        int targetPixCount = 400000;
        double sizeRatio = getHeightWidthRatio(tempHeight, tempWidth);
        if (tempHeight * tempWidth != targetPixCount) //Shrink the image if it's too large.
        {
            tempWidth = Math.sqrt(targetPixCount/sizeRatio);
            tempHeight = (tempWidth * sizeRatio);
            if (tempWidth > 1 && tempHeight > 1)
            {
                setWidth = (int) Math.round(tempWidth);
                setHeight = (int) Math.round(tempHeight);
            }
            imgIcon.setImage(getScaledImage(imgIcon.getImage(), setWidth, setHeight));
        }
        
        BufferedImage newIMGBuff = new BufferedImage(setWidth, setHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics convert = newIMGBuff.createGraphics();
        imgIcon.paintIcon(null, convert, 0, 0);
        convert.dispose();
        
        
        
        return newIMGBuff;
   }
    
    public void setImage(File newFile, FileNum num) throws IOException {
        if (!checkIfValidImage(newFile)) { throw new IOException("File provided to the 'setImage' function was not valid."); }
        switch (num) {
            case FIRST:
                //if (this.imgBuffer[0] != null) { this.imgBuffer[0].flush(); }
                this.rawImgWidth[0] = 0;
                this.rawImgHeight[0] = 0;
                this.imgFile[0] = newFile;
                setImageHeightAndWidth(newFile, 0);
                break;
            case SECOND:
                //if (this.imgBuffer[1] != null) { this.imgBuffer[1].flush(); }
                this.rawImgWidth[1] = 0;
                this.rawImgHeight[1] = 0;
                this.imgFile[1] = newFile;
                setImageHeightAndWidth(newFile, 1);
        }
    }
    public void setImage(BufferedImage newImageBuffer, FileNum num) {
        switch (num) {
            case FIRST:
                //if (this.imgBuffer[0] != null) { this.imgBuffer[0].flush(); }
                this.imgBuffer[0] = newImageBuffer;
                rawImgHeight[0] = newImageBuffer.getHeight();
                rawImgWidth[0] = newImageBuffer.getWidth();
                break;
            case SECOND:
                //if (this.imgBuffer[1] != null) { this.imgBuffer[1].flush(); }
                this.imgBuffer[1] = newImageBuffer;
                rawImgHeight[1] = newImageBuffer.getHeight();
                rawImgWidth[1] = newImageBuffer.getWidth();
        }
    }
    public BufferedImage getImage(FileNum num) {
        if (num == FileNum.FIRST) { return imgBuffer[0]; }
        else { return imgBuffer[1]; }
    }
    public BufferedImage resizeImageBuff(BufferedImage img, int newWidth, int newHeight) {
        Image newImage = img.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH);
        BufferedImage newBuff = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB); //https://stackoverflow.com/questions/37335/how-to-deal-with-java-lang-outofmemoryerror-java-heap-space-error

        Graphics2D graphics = newBuff.createGraphics();
        graphics.drawImage(newImage, 0, 0, null);
        graphics.dispose();
        
        return newBuff;
    }
    
    public BufferedImage getDifferenecs(CompareMethod methodOfComparison) throws IOException {
        BufferedImage returnImage;
        try
        {
            if (imgFile[0] != null) { this.imgBuffer[0] = importImage(imgFile[0]); }
            else if (imgBuffer[0] == null) { throw new IOException("The first imageBuffer was null, and there was no file to import from."); }
            if (imgFile[1] != null) { this.imgBuffer[1] = importImage(imgFile[1]); }
            else if (imgBuffer[1] == null) { throw new IOException("The second imageBuffer was null, and there was no file to import from."); }
            
            if (imagesAreEqualSize(this.imgBuffer[0].getHeight(),
                    this.imgBuffer[1].getHeight(),
                    this.imgBuffer[0].getWidth(),
                    this.imgBuffer[1].getWidth()))
            {
                switch (methodOfComparison) {
                    case BASIC:
                        returnImage = new BufferedImage(imgBuffer[0].getWidth(), imgBuffer[0].getHeight(), BufferedImage.TYPE_INT_ARGB);
                        Color rgbWhite = new Color(255, 255, 255), rgbBlack = new Color(0, 0, 0);
                        for (int yPos = 0; yPos < imgBuffer[0].getHeight(); yPos++) {
                            for (int xPos = 0; xPos < imgBuffer[0].getWidth(); xPos++) {
                                if (imgBuffer[0].getRGB(xPos, yPos) == imgBuffer[1].getRGB(xPos, yPos)) {
                                    returnImage.setRGB(xPos, yPos, rgbBlack.getRGB());
                                } else {
                                    returnImage.setRGB(xPos, yPos, rgbWhite.getRGB());
                                }
                            }
                        }
                        return returnImage;
                    case SUBTRACT_COLOR:
                        returnImage = new BufferedImage(imgBuffer[0].getWidth(), imgBuffer[0].getHeight(), BufferedImage.TYPE_INT_ARGB);
                        Color[] pixColor = new Color[2];
                        for (int yPos = 0; yPos < imgBuffer[0].getHeight(); yPos++) {
                            for (int xPos = 0; xPos < imgBuffer[0].getWidth(); xPos++) {
                                pixColor[0] = getPixelColor(imgBuffer[0].getRGB(xPos, yPos));
                                pixColor[1] = getPixelColor(imgBuffer[1].getRGB(xPos, yPos));

                                returnImage.setRGB(xPos, yPos, getSubtractedColor(pixColor[0], pixColor[1]).getRGB());
                            }
                        }
                        return returnImage;
                }
            }
            else //if (imagesAreProportional())
            {
                CompareImages compare = new CompareImages();
                
                if (firstImageIsLarger()) {
                    compare.setImage(resizeImageBuff(this.imgBuffer[0], this.imgBuffer[1].getWidth(), this.imgBuffer[1].getHeight()),
                            FileNum.FIRST);
                    compare.setImage(this.imgBuffer[1], FileNum.SECOND);
                } else {
                    compare.setImage(this.imgBuffer[0], FileNum.FIRST);
                    compare.setImage(resizeImageBuff(this.imgBuffer[1], this.imgBuffer[0].getWidth(), this.imgBuffer[0].getHeight()),
                            FileNum.SECOND);
                }
                return compare.getDifferenecs(methodOfComparison);
            }
        } catch (IOException ex) {
            if (ex.getMessage().equals("-1"))
            {
                throw ex;
            }
            
            System.out.println("SOMETHING WENT WRONG WHILE TRYING TO GET DIFFERENCE IMAGES. RETURNING A BLANK IMAGE.");
            System.out.println("\t" + ex.toString());
            System.out.println("\t" + ex.getMessage());
        }
        return new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
    }
    public float getPercentSimilar(CompareMethod searchMethod) throws IOException {
        float percentSimilar = 0;
        long counter, maxNum;
        try {
            if (imgFile[0] != null) { this.imgBuffer[0] = importImage(imgFile[0]); }
            else if (imgBuffer[0] == null) { throw new IOException("The first imageBuffer was null, and there was no file to import from."); }
            if (imgFile[1] != null) { this.imgBuffer[1] = importImage(imgFile[1]); }
            else if (imgBuffer[1] == null) { throw new IOException("The second imageBuffer was null, and there was no file to import from."); }
            
            if (imagesAreEqualSize(this.imgBuffer[0].getHeight(),
                    this.imgBuffer[1].getHeight(),
                    this.imgBuffer[0].getWidth(),
                    this.imgBuffer[1].getWidth()))
            {
                switch (searchMethod) {
                    case BASIC:
                        maxNum = (imgBuffer[0].getWidth() * imgBuffer[0].getHeight());
                        counter = maxNum;
                        for (int yPos = 0; yPos < imgBuffer[0].getHeight(); yPos++)
                        {
                            for (int xPos = 0; xPos < imgBuffer[0].getWidth(); xPos++)
                            {
                                if (imgBuffer[0].getRGB(xPos, yPos) != imgBuffer[1].getRGB(xPos, yPos))
                                {
                                    counter--;
                                }
                            }
                        }
                        percentSimilar = (float) counter / maxNum;
                        break;
                    case SUBTRACT_COLOR:
                        Color[] pixColor = new Color[2];
                        Color subtractColor;
                        
                        maxNum = 3 * 255 * imgBuffer[0].getWidth() * imgBuffer[0].getHeight();
                        counter = maxNum;
                        for (int yPos = 0; yPos < imgBuffer[0].getHeight(); yPos++)
                        {
                            for (int xPos = 0; xPos < imgBuffer[0].getWidth(); xPos++)
                            {
                                pixColor[0] = getPixelColor(imgBuffer[0].getRGB(xPos, yPos));
                                pixColor[1] = getPixelColor(imgBuffer[1].getRGB(xPos, yPos));

                                subtractColor = getSubtractedColor(pixColor[0], pixColor[1]);

                                counter -= subtractColor.getRed();
                                counter -= subtractColor.getGreen();
                                counter -= subtractColor.getBlue();
                            }
                        }
                        percentSimilar = (float) counter / maxNum;
                }
            }
            else if (imagesAreProportional())
            {
                CompareImages compare = new CompareImages();

                if (firstImageIsLarger()) {
                    compare.setImage(resizeImageBuff(this.imgBuffer[0], this.imgBuffer[1].getWidth(), this.imgBuffer[1].getHeight()),
                            FileNum.FIRST);
                    compare.setImage(this.imgBuffer[1], FileNum.SECOND);
                } else {
                    compare.setImage(this.imgBuffer[0], FileNum.FIRST);
                    compare.setImage(resizeImageBuff(this.imgBuffer[1], this.imgBuffer[0].getWidth(), this.imgBuffer[0].getHeight()),
                            FileNum.SECOND);
                }
                percentSimilar = compare.getPercentSimilar(searchMethod);
            }
            else
            {
                return -1;
            }
        }
        catch (IOException ex)
        {
            if (ex.getMessage().equals("-1"))
            {
                throw ex;
            }
            
            System.out.println("SOMETHING WENT WRONG WHILE TRYING TO GET PERCENTAGE SIMILAR. RETURNING -1.\n\n" + ex.getMessage());
            return -1;
        }
        
        return percentSimilar;
    }
    public float getPercentSimilar(CompareMethod searchMethod, javax.swing.JSlider percentRequired) throws IOException {        
        float percentSimilar = 0;
        long counter, maxNum;
        try {
            if (imgFile[0] != null) { this.imgBuffer[0] = importImage(imgFile[0]); }
            else if (imgBuffer[0] == null) { throw new IOException("The first imageBuffer was null, and there was no file to import from."); }
            if (imgFile[1] != null) { this.imgBuffer[1] = importImage(imgFile[1]); }
            else if (imgBuffer[1] == null) { throw new IOException("The second imageBuffer was null, and there was no file to import from."); }
            
            if (imagesAreEqualSize(this.imgBuffer[0].getHeight(),
                    this.imgBuffer[1].getHeight(),
                    this.imgBuffer[0].getWidth(),
                    this.imgBuffer[1].getWidth()))
            {
                switch (searchMethod) {
                    case BASIC:
                        maxNum = (imgBuffer[0].getWidth() * imgBuffer[0].getHeight());
                        counter = maxNum;
                        for (int yPos = 0; yPos < imgBuffer[0].getHeight(); yPos++)
                        {
                            if (((float) counter / maxNum) < ((float) percentRequired.getValue() / 100))
                            { return 0; }
                            for (int xPos = 0; xPos < imgBuffer[0].getWidth(); xPos++)
                            {
                                if (imgBuffer[0].getRGB(xPos, yPos) != imgBuffer[1].getRGB(xPos, yPos))
                                {
                                    counter--;
                                }
                            }
                        }
                        percentSimilar = (float) counter / maxNum;
                        break;
                    case SUBTRACT_COLOR:
                        Color[] pixColor = new Color[2];
                        Color subtractColor;
                        
                        maxNum = 3 * 255 * imgBuffer[0].getWidth() * imgBuffer[0].getHeight();
                        counter = maxNum;
                        for (int yPos = 0; yPos < imgBuffer[0].getHeight(); yPos++)
                        {
                            if (((float) counter / maxNum) < ((float) percentRequired.getValue() / 100))
                            { return 0; }
                            for (int xPos = 0; xPos < imgBuffer[0].getWidth(); xPos++)
                            {
                                pixColor[0] = getPixelColor(imgBuffer[0].getRGB(xPos, yPos));
                                pixColor[1] = getPixelColor(imgBuffer[1].getRGB(xPos, yPos));

                                subtractColor = getSubtractedColor(pixColor[0], pixColor[1]);

                                counter -= subtractColor.getRed();
                                counter -= subtractColor.getGreen();
                                counter -= subtractColor.getBlue();
                            }
                        }
                        percentSimilar = (float) counter / maxNum;
                }
            }
            else if (imagesAreProportional())
            {
                CompareImages compare = new CompareImages();

                if (firstImageIsLarger()) {
                    compare.setImage(resizeImageBuff(this.imgBuffer[0], this.imgBuffer[1].getWidth(), this.imgBuffer[1].getHeight()),
                            FileNum.FIRST);
                    compare.setImage(this.imgBuffer[1], FileNum.SECOND);
                } else {
                    compare.setImage(this.imgBuffer[0], FileNum.FIRST);
                    compare.setImage(resizeImageBuff(this.imgBuffer[1], this.imgBuffer[0].getWidth(), this.imgBuffer[0].getHeight()),
                            FileNum.SECOND);
                }
                percentSimilar = compare.getPercentSimilar(searchMethod, percentRequired);
            }
            else
            {
                return -1;
            }
        }
        catch (IOException ex)
        {
            if (ex.getMessage().equals("-1"))
            {
                throw ex;
            }
            
            System.out.println("SOMETHING WENT WRONG WHILE TRYING TO GET PERCENTAGE SIMILAR. RETURNING -1.\n\n" + ex.getMessage());
            return -1;
        }
        
        return percentSimilar;
    }
}
