package framework;


import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class Browser {

    private static final int implicitWait = Integer.parseInt(ConfProperties.getProperty("implicitWait"));
    private static final int loadWait = Integer.parseInt(ConfProperties.getProperty("loadWait"));
    private static Browser browser;
    private static WebDriver driver;


    public static Browser getInstance() {
        if (browser == null) {
            BrowserFactory browserFactory = new BrowserFactory();
            driver = browserFactory.setUp(ConfProperties.getProperty("browser"));
            driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);
            browser = new Browser();
        }
        return browser;
    }

    public static int getLoadWait() {
        return loadWait;
    }

    public void closeBrowser() {
        driver.quit();
        browser = null;
    }

    public void navigate(String url) {
        driver.get(url);
    }

    public static void setDriver(WebDriver driver) {
        Browser.driver = driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}







