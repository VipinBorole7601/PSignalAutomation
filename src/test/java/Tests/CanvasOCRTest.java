package Tests;

import Utils.CanvasOCRUtils;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CanvasOCRTest {

    @Test
    public void testCanvasRendering() throws IOException, TesseractException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://example.com/your-canvas-app");

        try {
            // Locate the canvas element
            WebElement canvas = driver.findElement(By.id("chartCanvas"));

            // Maybe interact with it first? (e.g., click a draw button)
            // driver.findElement(By.id("drawButton")).click();

            // Use our helper method to get the text drawn inside the canvas
            String extractedText = CanvasOCRUtils.getTextFromCanvas(driver, canvas);

            // Assert on the extracted text
            System.out.println("Text found in canvas: '" + extractedText + "'");
            Assert.assertTrue(extractedText.contains("Expected Text"), "The canvas did not contain the expected text. Found: " + extractedText);

        } finally {
            driver.quit();
        }
    }
}
