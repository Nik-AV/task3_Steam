package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Arrays;
import java.util.HashMap;


public class BrowserFactory {

    private WebDriver driver = null;

    public static String getFileDownloadDir() {
        return ConfProperties.getProperty("pathToProject") + ConfProperties.getProperty("downloadPath");
    }

    public WebDriver setUp(String browserName) {

        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
                options.addArguments("start-maximized");
                HashMap<String, Object> chromePref = new HashMap<>();
                chromePref.put("safebrowsing.enabled", true);
                chromePref.put("profile.default_content_settings.popups", 0);
                chromePref.put("download.default_directory", getFileDownloadDir());
                options.setExperimentalOption("prefs", chromePref);
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                options.merge(capabilities);
                System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
                driver = new ChromeDriver(options);

                break;
             case "firefox":
                 FirefoxOptions firefoxOptions = new FirefoxOptions();
                 FirefoxProfile firefoxProfile = new FirefoxProfile();
                 firefoxProfile.setPreference("browser.download.folderList",2);
                 firefoxProfile.setPreference("browser.download.dir", getFileDownloadDir());
                 firefoxProfile.setPreference("browser.helperApps.neverAsk.openFile", "application/octet-stream");
                 firefoxProfile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
                 firefoxOptions.setProfile(firefoxProfile);
                 System.setProperty("webdriver.gecko.driver", ConfProperties.getProperty("firefoxdriver"));
                 driver = new FirefoxDriver(firefoxOptions);
                 break;
            default:
                break;
       }
        return driver;
    }
}
