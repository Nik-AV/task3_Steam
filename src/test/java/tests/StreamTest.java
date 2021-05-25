package tests;

import framework.BaseTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;
import pages.*;



public class StreamTest extends BaseTest {

    @Test
    @Parameters({"language"})
    public void downloadSteamInstallFile(String language) {

        Header header = new Header();
        header.selectLanguage(language);

        MainPage mainPage = new MainPage();
        mainPage.navigateMenu((language+"_Categories"), (language+"_Action"));

        CategoryGamesPage categoryGamesPage = new CategoryGamesPage();
        categoryGamesPage.openRandomGameWithMaxDiscount();
        String currentDiscount = categoryGamesPage.getMaxDiscount();

        GamePage gamePage = new GamePage();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(gamePage.verifyGameHasMaxDiscount(currentDiscount), true);

        header.clickInstallSteam();

        AboutPage aboutPage = new AboutPage();
        aboutPage.downloadInstallFile();
        softAssert.assertTrue(aboutPage.isInstallFileExists());
        softAssert.assertAll();
    }
}




