package pages;

import framework.BasePage;
import org.openqa.selenium.By;

public class GamePage extends BasePage {

    private static final String pageName = GamePage.class.getName();
    private static final By gameUniqueElement = By.className("apphub_AppName");
    private static final By gameDiscount = By.xpath("//div[@class='game_area_purchase_game_wrapper'][2]//div[@class='discount_pct']");

    public GamePage() {
        super (gameUniqueElement, pageName);
    }

    public boolean verifyGameHasMaxDiscount(String currentDiscount){

        int discount = Integer.parseInt(currentDiscount);
        return baseElement.isElementContainsGivenValue(gameDiscount, discount);

    }
}
