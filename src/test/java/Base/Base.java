package Base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class Base {

    public WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser){

        ChromeOptions options;

        if(browser.equals("chrome"))
        {

            options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            WebDriverManager.chromedriver().setup();
            driver  = new ChromeDriver(options);
            driver.get("https://showcase.apryse.com/pdf-search");
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


    }

    @AfterClass
    public void setDown()
    {
        if(driver!=null)
        {
            driver.quit();
        }
    }

}
