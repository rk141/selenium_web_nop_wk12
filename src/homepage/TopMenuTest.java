package homepage;

import browserTesting.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class TopMenuTest extends BaseTest {

    String baseUrl = "https://demo.nopcommerce.com/";
    String expectedMessage;

    @Before
    public void setUp(){

        openBrowser(baseUrl);
    }
    public void selectMenu(String menu){
        clickOnElement(By.xpath("//body/div[6]/div[2]/ul[1]/child::li[1]"));
        expectedMessage = menu;
    }
    @Test
    public void verifyPageNavigation(){
        selectMenu("Computers");
        String actualMessage = getTextFromElement(By.xpath("//h1[contains(text(),'Computers')]"));
        Assert.assertEquals("user is not on page", expectedMessage,actualMessage);
    }
    @After

    public void tearDown(){
        closeBrowser();
    }

}
