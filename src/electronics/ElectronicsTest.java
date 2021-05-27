package electronics;

import browserTesting.BaseTest;
import browserTesting.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class ElectronicsTest extends BaseTest {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setup() {

        openBrowser(baseUrl);
    }
    @Test

    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {

        mouseHoverToElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[2]"));
        mouseHoverAndClickElement(By.xpath("//body[1]/div[6]/div[2]/ul[1]/li[2]/ul[1]/li[2]/a[1]"));
        verifyPageNavigation(By.xpath("//h1[text()='Cell phones']"), "Cell phones");
    }
    @Test

    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {

        verifyUserShouldNavigateToCellPhonesPageSuccessfully();
        Thread.sleep(3000);
        clickOnElement(By.xpath("//a[@class='viewmode-icon list']"));
        JavascriptExecutor jsx = (JavascriptExecutor) driver;
        jsx.executeScript("window.scrollBy(0,700)", "");
        Thread.sleep(3000);
        clickOnElement(By.xpath("//a[text()='Nokia Lumia 1020']"));
        verifyPageNavigation(By.xpath("//h1[text()='Nokia Lumia 1020']"), "Nokia Lumia 1020");
        verifyPageNavigation(By.xpath("//span[contains(@id,'price-value')]"), "$349.00");
        Thread.sleep(2000);
        changeQuantityOnElement(By.xpath("//input[contains(@id,'product_enteredQuantity')]"), "2");
        clickOnElement(By.xpath("//button[contains(@id,'add-to-cart-button')]"));

        try {
            Alert alert = driver.switchTo().alert();
            String actualAlertMessage = alert.getText();
            String expectedAlertMessage = "The product has been added to your shopping cart";
            System.out.println(actualAlertMessage);
            Thread.sleep(3000);
            Assert.assertEquals("Product is not added in the shopping cart", expectedAlertMessage, actualAlertMessage);
            driver.switchTo().alert().dismiss();

        } catch (Exception e) {

            verifyPageNavigation(By.xpath("//body/div[@id='bar-notification']/div[1]"), "The product has been added to your shopping cart");
            Thread.sleep(2000);
            clickOnElement(By.xpath("//span[@class='close']"));
        }

        mouseHoverToElement(By.xpath("//span[text()='Shopping cart']"));
        Thread.sleep(2000);
        mouseHoverAndClickElement(By.xpath("//button[text()='Go to cart']"));
        verifyPageNavigation(By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping cart");

        Thread.sleep(2000);
        verifyPageNavigation(By.xpath("//li[@id='topcartlink']"), "Shopping cart (2)");
        Thread.sleep(500);
        verifyPageNavigation(By.xpath("//span[@class='product-subtotal']"), "$698.00");
        clickOnElement(By.id("termsofservice"));
        clickOnElement(By.id("checkout"));

        Thread.sleep(2000);
        verifyPageNavigation(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"), "Welcome, Please Sign In!");
        clickOnElement(By.xpath("//button[contains(text(),'Register')]"));
        verifyPageNavigation(By.xpath("//h1[contains(text(),'Register')]"), "Register");
        Thread.sleep(3000);

        clickOnElement(By.id("gender-female"));
        sendTextToElement(By.id("FirstName"), "lisa");
        sendTextToElement(By.id("LastName"), "shah");
        sendTextToElement(By.id("Email"), "wood9898@gmail.com");
        Thread.sleep(2000);
        sendTextToElement(By.id("Password"), "util5757");
        sendTextToElement(By.id("ConfirmPassword"), "util5757");
        clickOnElement(By.id("register-button"));

        Thread.sleep(2000);
        verifyPageNavigation(By.xpath("//div[contains(text(),'Your registration completed')]"), "Your registration completed");
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
        verifyPageNavigation(By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping cart");
        Thread.sleep(2000);
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));

        selectByValueDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "233");
        Thread.sleep(3000);
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "Southampton");
        sendTextToElement(By.xpath("//input[@data-val-required='Street address is required']"), "8 High Street");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "So15 3Ng");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "02380786980");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        Thread.sleep(3000);
        clickOnElement(By.xpath("//input[@value='2nd Day Air___Shipping.FixedByWeightByTotal']"));
        clickOnElement(By.xpath("//button[@onclick='ShippingMethod.save()']"));
        clickOnElement(By.xpath("//input[contains(@value,'Payments.Manual')]"));
        clickOnElement(By.xpath("//button[@onclick='PaymentMethod.save()']"));

        Thread.sleep(2000);
        selectByValueDropDown(By.xpath("//select[@id='CreditCardType']"), "visa");
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Lisa Shah");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "4539963872379806");
        Thread.sleep(2000);
        selectByValueDropDown(By.xpath("//select[@id='ExpireMonth']"), "12");
        selectByValueDropDown(By.xpath("//select[@id='ExpireYear']"), "2027");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "645");
        clickOnElement(By.xpath("//button[@onclick='PaymentInfo.save()']"));

        Thread.sleep(3000);
        verifyPageNavigation(By.xpath("//div[@class='payment-method-info']/ul"), "Payment Method: Credit Card");
        verifyPageNavigation(By.xpath("//div[@class='shipping-method-info']/ul"), "Shipping Method: 2nd Day Air");
        verifyPageNavigation(By.xpath("//tr[@class='order-total']"), "Total: $698.00");
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));

        Thread.sleep(2000);
        verifyPageNavigation(By.xpath("//h1[contains(text(),'Thank you')]"), "Thank you");
        verifyPageNavigation(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"), "Your order has been successfully processed!");
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        Thread.sleep(3500);
        verifyPageNavigation(By.xpath("//h2[contains(text(),'Welcome to our store')]"), "Welcome to our store");
        clickOnElement(By.xpath("//a[text()='Log out']"));
        Thread.sleep(1000);
        verifyCurrentUrl(baseUrl);
    }
    @After

    public void tearDown() {

        closeBrowser();
    }
}
