package pages;

import framework.BasePage;
import framework.ConfProperties;
import org.openqa.selenium.By;

public class MainPage extends BasePage {

    private static final String pageName = pages.MainPage.class.getName();
    private static final By MainPageUniqueElement = By.className("home_page_content");
    private static final String menuItem = "//a[@class='pulldown_desktop'][contains(text(), '%s')]";
    private static final String subMenuItem= "//a[@class='popup_menu_item'][contains(text(),'%s')]";

    public MainPage() {
        super(MainPageUniqueElement,pageName );
    }

    public void navigateMenu(String menuItemParam, String subMenuItemParam){

        String menuItemValue = ConfProperties.getProperty(menuItemParam);
        String subMenuItemValue = ConfProperties.getProperty(subMenuItemParam);
        baseElement.clickElement(By.xpath(String.format(menuItem,menuItemValue)));
        baseElement.clickElement(By.xpath(String.format(subMenuItem,subMenuItemValue)));
    }
}
