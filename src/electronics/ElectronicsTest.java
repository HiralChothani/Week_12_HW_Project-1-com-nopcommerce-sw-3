package electronics;

import homepage.TopMenuTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.UUID;

public class ElectronicsTest extends TopMenuTest {
    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {
        //  Mouse Hover on “Electronics” Tab
        mouseHoverOnTheElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Electronics']"));

        //1.2 Mouse Hover on “Cell phones” and click
        mouseHoverOnTheElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[normalize-space()='Cell phones']"));

        //1.3 Verify the text “Cell phones”
        compareText("Text does not match", "Cell phones", By.xpath("//h1[normalize-space()='Cell phones']"));
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        verifyUserShouldNavigateToCellPhonesPageSuccessfully();

        // Click on List View Tab
        clickOnElement(By.xpath("//a[normalize-space()='List']"));

        //2.5 Click on product name “Nokia Lumia 1020” link
        Thread.sleep(2000);
        clickOnElement(By.xpath("//a[normalize-space()='Nokia Lumia 1020']"));

        // Verify the text “Nokia Lumia 1020”
        compareText("Text does not match", "Nokia Lumia 1020", By.xpath("//h1[normalize-space()='Nokia Lumia 1020']"));

        //2.7 Verify the price “$349.00
        compareText("Price does not match", "$349.00", By.xpath("//span[@id='price-value-20']"));

        //  Change quantity to 2
        clickOnElement(By.xpath("//input[@id='product_enteredQuantity_20']"));
        clearText(By.xpath("//input[@id='product_enteredQuantity_20']"));
        sendTextToTheElement(By.xpath("//input[@id='product_enteredQuantity_20']"), "2");

        //2.9 Click on “ADD TO CART” tab
        Thread.sleep(2000);
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-20']"));

        //2.10 Verify the Message "The product has been added to your shopping cart" on Top green Bar
      Thread.sleep(1000);
       compareText("Message not displayed", "The product has been added to your shopping cart", By.xpath("//p[@class='content']"));
        // After that close the bar clicking on the cross button
        clickOnElement(By.xpath("//span[@title='Close']"));

        //  Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverOnTheElement(By.xpath("//span[@class='cart-label']"));
        clickOnElement(By.xpath("//button[normalize-space()='Go to cart']"));

        // Verify the message "Shopping cart"
        compareText("Message not displayed", "Shopping cart", By.xpath("//h1[normalize-space()='Shopping cart']"));

        //2.13 Verify the quantity is 2
        Thread.sleep(3000);
        String actualQuantity = driver.findElement(By.xpath("//input[@class='qty-input']")).getAttribute("value");
        String expectedQuantity = "2";
        Assert.assertEquals("Qty not matched", expectedQuantity, actualQuantity);

        //2.14 Verify the Total $698.00
        compareText("Price not correct", "$698.00", By.xpath("//span[@class='product-subtotal']"));

        //2.15 click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));

        //2.16 Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));

        // Verify the Text “Welcome, Please Sign In!”
        compareText("Text not displayed", "Welcome, Please Sign In!", By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"));

        //2.18 Click on “REGISTER” tab
        clickOnElement(By.xpath("//button[normalize-space()='Register']"));

        //2.19 Verify the text “Register”
        compareText("Text not displayed", "Register", By.xpath("//h1[normalize-space()='Register']"));

        //  Fill the mandatory fields
        // firstname
        sendTextToTheElement(By.xpath("//input[@id='FirstName']"), "Daun");
        // lastname
        sendTextToTheElement(By.xpath("//input[@id='LastName']"), "Wilkinson");
        //Email
        //Generate random Email
        final String randomEmail = randomEmail();
        sendTextToTheElement(By.xpath("//input[@id='Email']"), randomEmail);
        // password
        sendTextToTheElement(By.xpath("//input[@id='Password']"), "abcd1234");
        //confirm password
        sendTextToTheElement(By.xpath("//input[@id='ConfirmPassword']"), "abcd1234");

        //2.21 Click on “REGISTER” Button
        clickOnElement(By.xpath("//button[@id='register-button']"));

        //  Verify the message “Your registration completed”
        compareText("Message not displayed", "Your registration completed", By.xpath("//div[@class='result']"));

        //2.23 Click on “CONTINUE” tab
        clickOnElement(By.xpath("//a[normalize-space()='Continue']"));

        // Verify the text “Shopping card”
        Thread.sleep(2000);
        compareText("Text not displayed", "Shopping cart", By.xpath("//h1[normalize-space()='Shopping cart']"));

        //click on Login
        clickOnElement(By.xpath("//a[normalize-space()='Log in']"));

        // verify text "Welcome, Please Sign In!"
        compareText("Text not displayed", "Welcome, Please Sign In!", By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"));

        // Enter email
        sendTextToTheElement(By.xpath("//input[@id='Email']"), "Julie.will1@gmail.com");

        // Enter password
        sendTextToTheElement(By.xpath("//input[@id='Password']"), "abcd1234");

        // click on LOGIN
        clickOnElement(By.xpath("//button[normalize-space()='Log in']"));

        //2.25 click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));

        //2.26 Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));

//        // click on continue
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        //  Click on Radio Button “2nd Day Air ($0.00)”
        clickOnElement(By.xpath("//input[@id='shippingoption_2']"));

        //  Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        // Select Radio Button “Credit Card”
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));

        //2.32 Select “Visa” From Select credit card dropdown
        dropDownOption(By.xpath("//select[@id='CreditCardType']"), "Visa");

        //2.33 Fill all the details
        sendTextToTheElement(By.xpath("//input[@id='CardholderName']"), "xyz");
        sendTextToTheElement(By.xpath("//input[@id='CardNumber']"), "0000000000000000");
        sendTextToTheElement(By.xpath("//select[@id='ExpireMonth']"), "10");
        sendTextToTheElement(By.xpath("//select[@id='ExpireYear']"),"2025");
        sendTextToTheElement(By.xpath("//input[@id='CardCode']"), "456");

        //2.34 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        // Verify “Payment Method” is “Credit Card”
        compareText("payment method not matching", "Credit Card", By.xpath("//span[normalize-space()='Credit Card']"));

        //2.36 Verify “Shipping Method” is “2nd Day Air”
        compareText("Shipping method does not match","2nd Day Air", By.xpath("//span[normalize-space()='2nd Day Air']"));

        //2.37 Verify Total is “$698.00”
        compareText("Total is not same", "$698.00", By.xpath("//span[@class='value-summary']//strong[contains(text(),'$698.00')]"));

        //2.38 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[normalize-space()='Confirm']"));

        // Verify the Text “Thank You”
        compareText("Text does not match", "Thank you", By.xpath("//h1[normalize-space()='Thank you']"));

        //2.40 Verify the message “Your order has been successfully processed!”
        compareText("Message not displayed", "Your order has been successfully processed!", By.xpath("//strong[normalize-space()='Your order has been successfully processed!']"));

        //2.41 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[normalize-space()='Continue']"));

        //2.42 Verify the text “Welcome to our store”
        compareText("Text does not match", "Welcome to our store", By.xpath("//h2[normalize-space()='Welcome to our store']"));

        //2.43 Click on “Logout” link
        clickOnElement(By.xpath("//a[normalize-space()='Log out']"));

        //2.44 Verify the URL is “https://demo.nopcommerce.com/”
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://demo.nopcommerce.com/";
        Assert.assertEquals("Url does not match", expectedUrl, currentUrl);

    }

    private static String randomEmail() {
        return "Random-" + UUID.randomUUID().toString() + "@example.com";
    }
}
