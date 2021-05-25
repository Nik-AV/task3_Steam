package framework;

import org.openqa.selenium.By;
import org.testng.Assert;


public class BasePage {
    protected BaseElement baseElement;
    protected By uniqueElementLocator;
    protected String pageName;

    public BasePage(By locator, String title) {
        baseElement = new BaseElement(locator);
        init(locator, title);
        isPageVisible();

    }
    public void init(By locator, String name){
        uniqueElementLocator = locator;
        pageName = name;
    }

    public void isPageVisible() {
        try{
            baseElement.waitElementBeVisible(uniqueElementLocator);
            Assert.assertTrue(baseElement.getElement(uniqueElementLocator).isDisplayed());
        } catch (Throwable e){
            System.out.println("Page " + pageName + " is not opened");
        }
        }


}
