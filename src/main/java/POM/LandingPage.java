package POM;

import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class LandingPage {
    File file;
    public WebDriver driver;
    File uploadfile;
    Robot robot;
    WebDriverWait wait;

    JavascriptExecutor js;

    int c = 30;
    int d= 60;

    Actions a;

    @FindBy(xpath = "//div[@class='css-1njgnxn']//button[@class='chakra-button css-15wrx7t']")
    private WebElement chooseafilebtn;

    @FindBy(xpath = "//p[@class=\"chakra-text css-51pxgj\" and text()='Open local file']")
    private WebElement openLocalFile;

    @FindBy(xpath = "//button[@data-testid='choose-local-file-button']")
    private WebElement chooseLocalFileButton;

    @FindBy(xpath = "//apryse-webviewer[@id='wc-viewer']")
    private WebElement aprysewebviewer;

    @FindBy(xpath = "//button[@class='chakra-modal__close-btn css-1kt7ivw']")
    private WebElement popuphandle;

    @FindBy(xpath = "//div[@class='css-irjhuc']//a[@class='chakra-link css-f4h6uy']")
    private WebElement ApryseicontoMainPage;

    @FindBy(xpath ="//button[text()='Choose File']")
    private WebElement mainPageChooseFilebtn;




    public LandingPage(WebDriver driver){

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void VerifylandOnPdfUploadPage()
    {
        String url = driver.getCurrentUrl();
        String title=driver.getTitle();

        System.out.println("title of the page- "+title );
        System.out.println("url of the page- "+url);

    }

    public void setChooseafilebtn()
    {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(chooseafilebtn));
        try {
            if(chooseafilebtn.isDisplayed())
            {
                chooseafilebtn.click();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //opening local file operations
        openLocalFile.click();
    }
    public void uploadFileFromLocal() throws AWTException, InterruptedException {
        //file upload operation
        //chooseLocalFileButton.click();

        String filePath ="C:\\Users\\vipin\\Intellij Projects\\P_Signal_Autoamtion\\src\\test\\PDFfile\\file-sample_150kB.pdf";

        chooseLocalFileButton.click();

        Thread.sleep(3000);

        robot = new Robot();

        StringSelection pdfFilePath = new StringSelection(filePath);

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pdfFilePath,null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(1000);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(1000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        System.out.println("file is uploaded successfully");


        }

    public void Highlighte() throws IOException, InterruptedException {

        String underline1 = "return document.querySelector('#wc-viewer').shadowRoot.querySelector('#app > div.App.is-web-component > nav > div.ModularHeader.TopHeader.stroke.end > div > div > div:nth-child(1) > button:nth-child(2)')";
        WebElement button1 = (WebElement) ((JavascriptExecutor) driver).executeScript(underline1);
        Thread.sleep(5000);
        if(button1.isDisplayed())
        {
            button1.click();
        }
        Thread.sleep(3000);

    }

    public void select() throws AWTException, InterruptedException {
//       WebElement shadowhost = driver.findElement(By.cssSelector("apryse-webviewer"));
//       SearchContext rootnode=shadowhost.getShadowRoot();
//
//        WebElement firstpage = rootnode.findElement(By.cssSelector("app"));
//
//        System.out.println(firstpage.getShadowRoot());
//        System.out.println(firstpage.getText());


//        WebElement document =  driver.findElement(By.cssSelector("document.querySelector('apryse-webviewer').shadowRoot.querySelector('div#app').querySelector('div.App.is-web-component').querySelector('div#document-container-file-sample_150kb').querySelector('div#virtualListContainer').querySelector('div#pageContainer1.pageContainer').querySelector('canvas.auxiliary')"));
//
//        SearchContext searchContext=document.getShadowRoot();
//
//        js = (JavascriptExecutor)driver;
//        js.executeScript("window.scrollBy(0,600)",searchContext);

        WebElement canvas = (WebElement) ((JavascriptExecutor) driver).executeScript(
                "return document.querySelector('apryse-webviewer').shadowRoot.querySelector('div#app').querySelector('div.App.is-web-component').querySelector('div#document-container-file-sample_150kb').querySelector('div#virtualListContainer').querySelector('div#pageContainer1.pageContainer').querySelector('div#pageWidgetContainer1')");

        String data=canvas.getAttribute("id");
        System.out.println(data);
        Point point=  canvas.getLocation();

        robot = new Robot();

        for (int i = 0; i < 5; i++) {
            robot.mouseWheel(4);
            robot.delay(300);
        }
        robot.mouseMove(point.getX() + canvas.getSize().getWidth()/2,
                point.getY() + canvas.getSize().getHeight()/2);

        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        String comment = "return document.querySelector('#wc-viewer').shadowRoot.querySelector('#app > div.App.is-web-component > nav > div.ModularHeader.TopHeader.stroke.end > div > div > div:nth-child(1) > button:nth-child(10)')";
        WebElement commnentbtn =  (WebElement) ((JavascriptExecutor) driver).executeScript(comment);
        commnentbtn.click();

        String addcommenton4thpage = "return document.querySelector('#wc-viewer').shadowRoot.querySelector('#pageWidgetContainer4')";
        WebElement  addcommenton4thpagebtn=  (WebElement) ((JavascriptExecutor) driver).executeScript(addcommenton4thpage);
        addcommenton4thpagebtn.click();


        String commenttest ="return document.querySelector('apryse-webviewer').shadowRoot.querySelector('div.ModularHeader.TopHeader.stroke.start').querySelector('div.ModularHeaderItems>div:nth-child(4)').querySelector('button.Button.modular-ui.icon-only')";
        WebElement commnentbtn1=  (WebElement) ((JavascriptExecutor) driver).executeScript(commenttest);
        commnentbtn1.click();

        Thread.sleep(4000);

    }

    public void clickandSaveAsPDF() throws InterruptedException {

        String menu = "return document.querySelector('#wc-viewer').shadowRoot.querySelector('#app > div.App.is-web-component > nav > div.ModularHeader.TopHeader.stroke.start > div > div.GroupedItems > div:nth-child(1) > button')";
        WebElement menubar = (WebElement) ((JavascriptExecutor)driver).executeScript(menu);
        menubar.click();

        String saveas ="return document.querySelector('#wc-viewer').shadowRoot.querySelector('#FlyoutContainer > li:nth-child(3) > button > div')";
        WebElement saveasbtn =  (WebElement) ((JavascriptExecutor)driver).executeScript(saveas);
        saveasbtn.click();

        Thread.sleep(5000);

        a = new Actions(driver);
        a.keyDown(Keys.TAB).perform();
        a.keyDown(Keys.TAB).perform();
        a.keyDown(Keys.TAB).perform();
        a.keyDown(Keys.TAB).perform();
        a.keyDown(Keys.TAB).perform();
        a.keyDown(Keys.TAB).perform();
        a.keyDown(Keys.ENTER).perform();
        a.keyUp(Keys.ENTER);

        Thread.sleep(3000);

        //now go to the main page

        ApryseicontoMainPage.click();




    }


    public void OpenLocalFolderUpload() throws AWTException, InterruptedException, IOException {
     mainPageChooseFilebtn.click();
     openLocalFile.click();
     chooseLocalFileButton.click();
     robot = new Robot();

        String filePath = "C:\\Users\\vipin\\Downloads";

        StringSelection pdfFilePath = new StringSelection(filePath);

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pdfFilePath,null);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_V);
        robot.delay(1000);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(1000);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(10000);

    }

    public void uploadfile() throws InterruptedException {
        try {
            Runtime.getRuntime().exec("C:\\Users\\vipin\\Intellij Projects\\P_Signal_Autoamtion\\src\\test\\Resources\\fileClickScript.exe");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        Thread.sleep(10000);
    }

    public void check_CommentsandValidate() throws InterruptedException {

        wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        String commenttest2 = "return document.querySelector('apryse-webviewer').shadowRoot.querySelector('div.ModularHeader.TopHeader.stroke.start').querySelector('div.ModularHeaderItems>div:nth-child(4)').querySelector('button.Button.modular-ui.icon-only')";
        WebElement commenttest22 =  (WebElement) ((JavascriptExecutor)driver).executeScript(commenttest2);

        wait.until(ExpectedConditions.elementToBeClickable(commenttest22)).click();

        if(commenttest22.isDisplayed())
        {
            System.out.println("comment icon and its alignment is displyed on page 4");
        }
        else{
            System.out.println("comment details and alignment is hidden");
        }

        String pagenumber = "return document.querySelector('#wc-viewer').shadowRoot.querySelector('#app > div.App.is-web-component > div.content > div.ModularPanel.right.tools-header-open.modular-ui-panel > div.ModularPanel-container.notesPanel > div > div > div.normal-notes-container > div > h4')";
        WebElement editedpagenumber =(WebElement) ((JavascriptExecutor) driver).executeScript(pagenumber);

        String pagetext = editedpagenumber.getText();
        System.out.println(pagetext);

        if(pagetext.equals("Page 4"))
        {
            System.out.println("comment count is shown on correct page");
        }
        else {
            System.out.println("comment missmatch");
        }
    }
}












