package pages;

import framework.BasePage;
import org.openqa.selenium.By;

public class Header extends BasePage {

    private static final String pageName = Header.class.getName();
    private static final By headerUniqueElement = By.className("logo");
    private static final By languagesDropdown = By.xpath("//span[@id='language_pulldown']");
    private final String languageSelect = "//div[@id='language_dropdown']//a[contains(text(),'%s')]";
    private static final By installSteam = By.xpath("//a[@class='header_installsteam_btn_content']");

    public Header() {
        super(headerUniqueElement,pageName );
    }

    public void selectLanguage(String languageValue){

        baseElement.clickElement(languagesDropdown);
        try {
            baseElement.clickElement(By.xpath(String.format(languageSelect, languageValue)));
        } catch (Exception e){
            System.out.println("Selected language is set by default");
            baseElement.clickElement(languagesDropdown);
        }
    }

    public void clickInstallSteam(){
        baseElement.clickElement(installSteam);
    }
}
