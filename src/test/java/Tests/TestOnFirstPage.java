package Tests;

import Base.Base;
import POM.LandingPage;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class TestOnFirstPage extends Base{

    LandingPage l;

    @Test(priority = 1 ,description = "used to verify and validate URL and Title of page")
    public void testlandingPage(){
           l = new LandingPage(driver);
           l.VerifylandOnPdfUploadPage();
    }

    @Test(priority = 2 , description = "select and upload file from local to website")
    public void Open_a_file()
    {
        l = new LandingPage(driver);
        l.setChooseafilebtn();

    }

    @Test(priority = 3 , description = "Upload PDF file")
    public void upload_a_file() throws AWTException, InterruptedException {
        l = new LandingPage(driver);
        l.uploadFileFromLocal();

    }

    @Test(priority = 4)
    public void testHighlight() throws IOException, InterruptedException, AWTException {
        l = new LandingPage(driver);
        l.Highlighte();
        l.select();
    }

    @Test(priority = 5)
    public void saveasPDF() throws InterruptedException {
        l = new LandingPage(driver);
        l.clickandSaveAsPDF();

    }

    @Test(priority = 6 ,description = "open local folder and upload the file")
    public void reuploadedDownloded() throws IOException, InterruptedException, AWTException {
        l = new LandingPage(driver);
        l.OpenLocalFolderUpload();
        l.uploadfile();


    }


    @Test(priority = 7 )
    public void testcomments() throws InterruptedException {
        l = new LandingPage(driver);
        l.check_CommentsandValidate();

    }




}
