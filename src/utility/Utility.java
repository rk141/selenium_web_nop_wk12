package utility;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utility {

    public WebDriver driver;

    public void clickOnElement(By by){
        driver.findElement(by).click();
    }
    public String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }
    public void sendTextToElement(By by,String text){
        driver.findElement(by).sendKeys(text);
    }
    public void selectByValueDropDown(By by,String value){
        Select select = new Select(driver.findElement(by));
        select.selectByValue(value);

    }
    //this method verify text of navigated page
    public void verifyPageNavigation(By by, String expectedMessage) {

        String actualMessage = driver.findElement(by).getText();

        Assert.assertEquals("User does not navigate to " + expectedMessage + "page", expectedMessage, actualMessage);
    }

    //this method hover mouse on selected element
    public void mouseHoverToElement(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).build().perform();
    }

    //this method hover mouse and click on selected element
    public void mouseHoverAndClickElement(By by){

        Actions actions=new Actions(driver);
        actions.moveToElement(driver.findElement(by)).click().build().perform();

    }

    //this method verify product sorted in ascending order
    public boolean checkAscendingOrder(String[] Names) {
        String previous = ""; // empty string

        for (String current : Names) {
            if (current.compareTo(previous) < 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    //this method verify product sorted in descending order
    public boolean checkDescendingOrder(String[] names) {

        String previous = ""; // empty string

        for (String current : names) {
            if (previous.compareTo(current) < 0) {
                return true;
            }
            previous = current;
        }

        return false;
    }
    //this method change the quantity of selected element
    public void changeQuantityOnElement(By by,String quantity) {

        WebElement quantityElement = driver.findElement(by);
        quantityElement.clear();
        quantityElement.sendKeys(quantity);
    }
    //this method verify current and base url
    public void verifyCurrentUrl(String baseUrl) {

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("Current url and baseurl does not match", baseUrl, currentUrl);
    }
}
