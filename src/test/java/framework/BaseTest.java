package framework;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.io.File;

public class BaseTest {

private static Browser browser = Browser.getInstance();
private static final String startPage = ConfProperties.getProperty("startPage");

    @BeforeTest
    public static void initializeWebdriver(){

        browser.getInstance();
        browser.navigate(startPage);
    }

   @BeforeTest
   public static void deleteFile() {

       BaseElement.deleteInstallFile();
   }

   @AfterTest
   public static void tearDown(){
       browser.closeBrowser();
   }


}
