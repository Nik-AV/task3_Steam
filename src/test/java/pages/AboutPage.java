package pages;

import framework.BasePage;
import org.openqa.selenium.By;

public class AboutPage extends BasePage {

    private static final String pageName = AboutPage.class.getName();
    private static final By AboutPageUniqueElement = By.className("steam_logo");
    private static final By installSteam = By.xpath("//div[@id='about_greeting']//a[@class='about_install_steam_link']");

    public AboutPage(){super(AboutPageUniqueElement, pageName);}

    public void downloadInstallFile(){

        baseElement.clickElement(installSteam);
    }

    public Boolean isInstallFileExists(){
       return  baseElement.isFileExists();
    }

}
