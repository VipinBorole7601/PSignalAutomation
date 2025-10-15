package Utils;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CanvasOCRUtils {


    public static String getTextFromCanvas(WebDriver driver, WebElement canvasElement) throws IOException, TesseractException {
        // 1. Take a screenshot of the specific canvas element
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        BufferedImage fullImg = ImageIO.read(screenshot);

        // 2. Get location and size of the canvas element
        Point point = canvasElement.getLocation();
        int eleWidth = canvasElement.getSize().getWidth();
        int eleHeight = canvasElement.getSize().getHeight();

        // 3. Crop the full screenshot to get only the canvas element's image
        BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
        ImageIO.write(eleScreenshot, "png", screenshot); // Overwrite the file with the cropped image

        // 4. (Optional) Process the image for better OCR accuracy
        // BufferedImage processedImg = ImageHelper.convertImageToGrayscale(eleScreenshot);
        // BufferedImage processedImg = ImageHelper.increaseImageContrast(eleScreenshot);
        // ImageIO.write(processedImg, "png", screenshot);

        // 5. Perform OCR on the cropped image
        ITesseract tesseract = new Tesseract();

        // If Tesseract isn't in your system PATH, set its datapath manually
        // tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");

        // Set language if needed (ensure you have the language pack)
        // tesseract.setLanguage("eng");

        // Extract text from the image file
        String result = tesseract.doOCR(screenshot);

        return result.trim(); // Trim extra whitespace
    }

}
