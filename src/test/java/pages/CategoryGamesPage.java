package pages;

import framework.BasePage;
import org.openqa.selenium.By;

public class CategoryGamesPage extends BasePage {

    private static final String pageName = CategoryGamesPage.class.getName();
    private static final By CategoryGamesPageUniqueElement = By.className("pageheader");
    private final By SpecialsDiscount = By.xpath("//div[@id='specials_container']//div[@class='discount_pct']");
    private final String gameWithMaxDiscount = "//div[@id='specials_container']//div[@class='discount_pct'][contains(text(), '%s')]";
    private final By ageValidationPageUniqueElement = By.xpath("//select[@id='ageYear']");
    public String maxDiscount;

    public CategoryGamesPage() {
        super(CategoryGamesPageUniqueElement,pageName );
    }

    public GamePage openRandomGameWithMaxDiscount(){

       maxDiscount = baseElement.getMaxInt(SpecialsDiscount).toString();
       baseElement.selectRandomGameWithMaxDiscount(By.xpath(String.format(gameWithMaxDiscount, maxDiscount)));

       if (baseElement.isElementPresentedOnPage(ageValidationPageUniqueElement)){

           AgeValidationPage ageValidationPage = new AgeValidationPage();
           ageValidationPage.passAgeValidation();
        }
       return new GamePage();
    }

    public String getMaxDiscount(){
        return maxDiscount;
    }
}
