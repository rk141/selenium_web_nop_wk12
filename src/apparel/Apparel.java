package apparel;

import browserTesting.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Apparel extends BaseTest {

    String baseUrl = "https://demo.nopcommerce.com/";

    @Before

    public void setup() {

        openBrowser(baseUrl);
    }
    @Test

    public void verifyUserShouldNavigateToAccessoriesPageSuccessfully() {

        mouseHoverToElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[3]/a[1]"));
        mouseHoverAndClickElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[3]/ul[1]/li[3]/a[1]"));
        verifyPageNavigation(By.xpath("//h1[contains(text(),'Accessories')]"), "Accessories");

    }
    @Test

    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {


        verifyUserShouldNavigateToAccessoriesPageSuccessfully();
        Thread.sleep(3500);
        selectByValueDropDown(By.xpath("//select[@id='products-pagesize']"), "3");

        List<WebElement> productList = driver.findElements(By.xpath("//div[@class='item-grid']/child::div"));
        int actualNoOFProduct = productList.size();
        int expectedNoOfProduct = 3;

        Thread.sleep(2500);
        Assert.assertEquals("product does not sort as per filter", expectedNoOfProduct, actualNoOFProduct);

        clickOnElement(By.xpath("//a[contains(text(),'Obey Propaganda Hat')]"));
        verifyPageNavigation(By.xpath("//h1[contains(text(),'Obey Propaganda Hat')]"), "Obey Propaganda Hat");
        verifyPageNavigation(By.xpath("//span[contains(@id,'price-value')]"), "$30.00");
        selectByValueDropDown(By.xpath("//select[contains(@id,'product_attribute')]"), "35");
        Thread.sleep(2500);
        clickOnElement(By.xpath("//button[contains(@id,'add-to-cart-button')]"));


        try {
            Alert alert = driver.switchTo().alert();
            String actualAlertMessage = alert.getText();
            String expectedAlertMessage = "The product has been added to your shopping cart";
            System.out.println(actualAlertMessage);
            Thread.sleep(3000);
            Assert.assertEquals("Product not added in cart", expectedAlertMessage, actualAlertMessage);
            driver.switchTo().alert().dismiss();

        } catch (Exception e) {

            verifyPageNavigation(By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]"), "The product has been added to your shopping cart");
            clickOnElement(By.xpath("//body/div[@id='bar-notification']/div[1]/span[1]"));
        }

        Thread.sleep(2500);
        mouseHoverToElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        mouseHoverAndClickElement(By.xpath("//button[contains(text(),'Go to cart')]"));

        Thread.sleep(2500);
        verifyPageNavigation(By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping cart");
        changeQuantityOnElement(By.xpath("//input[contains(@id,'itemquantity')]"), "5");
        clickOnElement(By.xpath("//button[contains(text(),'Update shopping cart')]"));
        verifyPageNavigation(By.xpath("//body/div[6]/div[1]/div[1]/div[2]/div[1]/ul[1]/li[4]/a[1]"), "Shopping cart (5)");
        verifyPageNavigation(By.xpath("//tbody/tr[1]/td[6]/span[1]"), "$150.00");
        Thread.sleep(2500);
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));

        Thread.sleep(2000);
        verifyPageNavigation(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"), "Welcome, Please Sign In!");
        sendTextToElement(By.xpath("//input[@id='Email']"), "etdy12@gmail.com");
        sendTextToElement(By.xpath("//input[@id='Password']"), "abou6565");
        clickOnElement(By.xpath("//button[contains(text(),'Log in')]"));

        Thread.sleep(3000);
        verifyPageNavigation(By.xpath("//h1[contains(text(),'Shopping cart')]"), "Shopping cart");
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        clickOnElement(By.xpath("//button[@id='checkout']"));

        Thread.sleep(2500);
        selectByValueDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "380");
        Thread.sleep(2500);
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "Southampton");
        sendTextToElement(By.xpath("//input[@data-val-required='Street address is required']"), "8 High Street");
        Thread.sleep(2000);
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "SO15 3NG");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "02380786980");
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        Thread.sleep(3500);
        clickOnElement(By.xpath("//label[contains(text(),'Ground ($0.00)')]"));
        clickOnElement(By.xpath("//button[@onclick='ShippingMethod.save()']"));
        clickOnElement(By.xpath("//input[@value='Payments.CheckMoneyOrder']"));
        clickOnElement(By.xpath("//button[@onclick='PaymentMethod.save()']"));
        clickOnElement(By.xpath("//button[@onclick='PaymentInfo.save()']"));

        Thread.sleep(3000);
        verifyPageNavigation(By.xpath("//div[@class='payment-method-info']/ul"), "Payment Method: Check / Money Order");
        verifyPageNavigation(By.xpath("//li[@class='shipping-method']"), "Shipping Method: Ground");
        verifyPageNavigation(By.xpath("//tr[@class='order-total']"), "Total: $150.00");
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));

        Thread.sleep(3000);
        verifyPageNavigation(By.xpath("//h1[contains(text(),'Thank you')]"), "Thank you");
        verifyPageNavigation(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"), "Your order has been successfully processed!");
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));

        Thread.sleep(3000);
        verifyPageNavigation(By.xpath("//h2[contains(text(),'Welcome to our store')]"), "Welcome to our store");
        clickOnElement(By.xpath("//a[contains(text(),'Log out')]"));

        Thread.sleep(2000);
        verifyCurrentUrl(baseUrl);

    }

    @After

    public void tearDown() {

        closeBrowser();
    }
}
