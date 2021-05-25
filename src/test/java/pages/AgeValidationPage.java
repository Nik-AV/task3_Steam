package pages;

import framework.BasePage;
import org.openqa.selenium.By;

import java.util.Calendar;

public class AgeValidationPage extends BasePage {

    private static final String pageName = AgeValidationPage.class.getName();
    private static final By yearDrowdown = By.xpath("//select[@id='ageYear']");
    private static final By viewPage = By.xpath("//a[@onclick='ViewProductPage()']");

    public AgeValidationPage() {
        super(yearDrowdown,pageName );
    }

    public void passAgeValidation() {

        baseElement.sendKeys(yearDrowdown, "2000");
        baseElement.clickAndWait(viewPage);
    }
}
