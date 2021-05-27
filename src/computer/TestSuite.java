package computer;

import browserTesting.BaseTest;
import homepage.TopMenuTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class TestSuite extends TopMenuTest {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before

    public void setUp() {

        openBrowser(baseUrl);
    }
    @Test

    public void verifyProductArrangeInAlphaBaticalOrder() throws InterruptedException {


        verifyPageNavigation();
        Thread.sleep(2500);
        clickOnElement(By.xpath("//a[text()=' Desktops ']"));
        verifyPageNavigation(By.xpath("//h1[text()='Desktops']"), "Desktops");

        Select select = new Select(driver.findElement(By.id("products-orderby")));
        select.selectByVisibleText("Name: Z to A");

        List<WebElement> productList = driver.findElements(By.xpath("//div[@class='products-container']/descendant::h2"));

        String[] linkText = new String[productList.size()];
        int i = 0;
        for (WebElement a : productList) {
            linkText[i] = a.getText();
            i++;
        }


        if (!checkDescendingOrder(linkText)) {

            System.out.println("not sorted");
        }
    }
    @Test
    public void verifyProductAddedToShopingCartSuccessFully() throws InterruptedException {
        verifyPageNavigation();
        Thread.sleep(3000);
        clickOnElement(By.xpath("//a[text()=' Desktops ']"));
        verifyPageNavigation(By.xpath("//h1[text()='Desktops']"), "Desktops");

        Select select = new Select(driver.findElement(By.id("products-orderby")));
        select.selectByVisibleText("Name: Z to A");
        selectByValueDropDown(By.id("products-orderby"), "Name: Z to A");
        Thread.sleep(3000);

        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/button[1]"));
        String actualMessage = getTextFromElement(By.xpath("//h1[contains(text(),'Build your own computer')]"));
        String expectedMessage = "Build your own computer";
        Assert.assertEquals("user is not on page", expectedMessage, actualMessage);

        selectByValueDropDown(By.xpath("//select[@id='product_attribute_1']"), "1");
        selectByValueDropDown(By.xpath("//select[@id='product_attribute_2']"), "5");
        Thread.sleep(2000);
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));
        clickOnElement(By.xpath("//input[@id='product_attribute_4_9']"));
        clickOnElement(By.xpath("//input[@id='product_attribute_5_12']"));
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1'"));
        actualMessage = getTextFromElement(By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]"));
        expectedMessage = "please select right spec";
        Assert.assertEquals("user didnt select right spec", expectedMessage, actualMessage);
        Thread.sleep(2000);
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "jay");
        sendTextToElement(By.id("BillingNewAddress_LastName"), "Patel");
        selectByValueDropDown(By.xpath("//input[@id='billingNewAddress_Email']"), "abc@gmail.com");
        Thread.sleep(3000);
        selectByValueDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "750");
        sendTextToElement(By.id("BillingNewAddress_City"), "southampton");
        sendTextToElement(By.id("BillingNewAddress_Address1"), "8 High Street");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"), "SO15 3NG");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"), "02380786980");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        Thread.sleep(2000);
        clickOnElement(By.id("shippingoption_1"));
        clickOnElement(By.xpath("//button[@onclick='ShippingMethod.save()']"));
        clickOnElement(By.id("paymentmethod_1"));
        clickOnElement(By.xpath("//button[@onclick='PaymentMethod.save()']"));
        Thread.sleep(3000);
        selectByValueDropDown(By.id("CreditCardType"), "Amex");
        sendTextToElement(By.id("CardholderName"), "Jay patel");
        sendTextToElement(By.id("CardNumber"), "373785316098859");
        Thread.sleep(2000);
        selectByValueDropDown(By.id("ExpireMonth"), "8");
        selectByValueDropDown(By.id("ExpireYear"), "2023");
        sendTextToElement(By.id("CardCode"), "298");
        clickOnElement(By.xpath("//button[@onclick='PaymentInfo.save()']"));

        Thread.sleep(2000);
        verifyPageNavigation(By.xpath("//li[@class='payment-method']"), "Payment Method: Credit Card");
        verifyPageNavigation(By.xpath("//li[@class='shipping-method']"), "Shipping Method: Next Day Air");
        verifyPageNavigation(By.xpath("//span[@class='value-summary']/child::strong"), "$2,950.00");

        Thread.sleep(2000);
        clickOnElement(By.xpath("//button[@onclick='ConfirmOrder.save()']"));
        verifyPageNavigation(By.xpath("//h1[text()='Thank you']"), "Thank you");
        verifyPageNavigation(By.xpath("//strong[text()='Your order has been successfully processed!']"), "Your order has been successfully processed!");
        Thread.sleep(2000);
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        Thread.sleep(2000);

        verifyPageNavigation(By.xpath("//h2[text()='Welcome to our store']"), "Welcome to our store");

    }

    @After

    public void tearDown() {

        closeBrowser();
    }
}
