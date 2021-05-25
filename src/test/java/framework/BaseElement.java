package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.util.List;


import static framework.Browser.getLoadWait;

public class BaseElement {
    protected static Browser browser = Browser.getInstance();
    protected WebElement webElement;
    protected Actions actions = new Actions(browser.getDriver());

    public BaseElement(By by) {
        this.webElement = getElement(by);
    }


    public static WebElement getElement(By locator) {
        return new WebDriverWait(Browser.getDriver(), getLoadWait())
                .until(driver -> driver.findElement(locator));
    }


    public static List<WebElement> getElements(By locator) {
        return new WebDriverWait(Browser.getDriver(), getLoadWait())
                .until(driver -> driver.findElements(locator));
    }

    public void clickElement(By locator) {
        waitElementBeClickable(locator);
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", getElement(locator));
        }
        getElement(locator).click();
    }

    public void clickAndWait(By locator) {
        clickElement(locator);
        waitForPageToLoad();
    }

    public void waitElementBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), getLoadWait());
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitElementBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(Browser.getDriver(), getLoadWait());
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String getText(By locator) {
        waitElementBeClickable(locator);
        return getElement(locator).getText();
    }

    public void sendKeys(By locator, String key)
    {
        waitElementBeClickable(locator);
    getElement(locator).sendKeys(key);
    }

    public boolean isElementContainsGivenValue(By locator, int givenValue){
        String elementText = getElement(locator).getText();
        elementText = elementText.substring(1,elementText.length()-1);
        return Integer.parseInt(elementText) == givenValue;
    }

    public boolean isElementPresentedOnPage(By locator){
        boolean elementPresence = true;
        try {
            getElement(locator);
        } catch (Exception e){
            elementPresence = false;
        }
    return elementPresence;
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(browser.getDriver(), getLoadWait());
        wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {
            public Boolean apply(final WebDriver d) {
                Object result = ((JavascriptExecutor) d)
                        .executeScript("return document['readyState'] ? 'complete' == document.readyState : true");

                if (result != null && result instanceof Boolean && (Boolean) result) {
                    return true;
                }
                return false;
            }
        });
    }

    public Integer getMaxInt(By locator) {

        List<WebElement> elements = getElements(locator);
        int maxInt = 0;

        for (int i = 0; i < elements.size(); i++) {

            String value = elements.get(i).getText();
            value = value.substring(1, value.length() - 1);
            int intValue = Integer.parseInt(value);
            if (intValue > maxInt) {
                maxInt = intValue;
            }
        }
        return maxInt;
    }

    public void selectRandomGameWithMaxDiscount(By locator){

        List<WebElement> elements = getElements(locator);
        int random  = (int) (Math.random()*(elements.size()-1));
        elements.get(random).click();
    }

    public static void deleteInstallFile() {

        File file = new File(ConfProperties.getProperty("installFile"));

        if (file.delete()) {
            System.out.println(file.getName() + " deleted");
        } else {
            System.out.println(file.getName() + " not deleted");
        }
    }

    public boolean isFileExists() {

        WebDriverWait wait = new WebDriverWait(browser.getDriver(), Integer.parseInt(ConfProperties.getProperty("downloadwait")));
        File file = new File(ConfProperties.getProperty("installFile"));

        wait.until(webDriver -> file.exists());
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

}
