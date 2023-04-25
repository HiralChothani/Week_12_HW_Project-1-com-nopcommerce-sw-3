package computer;

import homepage.TopMenuTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestSuite extends TopMenuTest {
    @Test
    public void verifyProductArrangeInAlphabeticalOrder() {
        // Click on Computer Menu.
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Computers '] "));
        //1.2 Click on Desktop
        clickOnElement(By.xpath("//a[@title='Show products in category Desktops'][normalize-space()='Desktops']"));

        //1.3 Select Sort By position "Name: Z to A"
        dropDownOption(By.xpath("//select[@id='products-orderby']"), "Name: Z to A");

        //1.4 Verify the Product will arrange in Descending order.
        List<WebElement> actualProductOrder = driver.findElements(By.xpath("//h2"));
        ArrayList<String> actualProductSequence = new ArrayList<String>();
        for (WebElement element : actualProductOrder) {
            actualProductSequence.add(element.getText());
        }
        List<WebElement> expectedProductOrder = driver.findElements(By.xpath("//h2[@class='product-title']"));
        ArrayList<String> expectedProductSequence = new ArrayList<String>();
        for (WebElement element : expectedProductOrder) {
            expectedProductSequence.add(element.getText());
        }
        compareElements("Product not displayed in Descending order", expectedProductSequence, actualProductSequence);
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {

        // Click on Computer Menu.
        clickOnElement(By.xpath("//ul[@class='top-menu notmobile']//a[text()='Computers '] "));

        // Click on Desktop
        clickOnElement(By.xpath("//a[@title='Show products in category Desktops'][normalize-space()='Desktops']"));

        //2.3 Select Sort By position "Name: A to Z"
        dropDownOption(By.xpath("//select[@id='products-orderby']"), "Name: A to Z");

        //2.4 Click on "Add To Cart"
        Thread.sleep(1000);
        clickOnElement(By.cssSelector("body > div:nth-child(7) > div:nth-child(3) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(4) > div:nth-child(2) > button:nth-child(1)"));

        //2.5 Verify the Text "Build your own computer"
        compareText("Text does not match", "Build your own computer", By.xpath("//h1[normalize-space()='Build your own computer']"));

        //2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        dropDownOption(By.xpath("//select[@id='product_attribute_1']"), "2.2 GHz Intel Pentium Dual-Core E2200");

        //2.7.Select "8GB [+$60.00]" using Select class.
        dropDownOption(By.xpath("//select[@id='product_attribute_2']"), "8GB [+$60.00]");

        //2.8 Select HDD radio "400 GB [+$100.00]"
        clickOnElement(By.xpath("//input[@id='product_attribute_3_7']"));

        //2.9 Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.xpath("//input[@id='product_attribute_4_9']"));

        //2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander [+$5.00]"
        driver.findElement(By.xpath("//input[@id='product_attribute_5_10']")).isSelected();
        Thread.sleep(1000);
        clickOnElement(By.xpath("//input[@id='product_attribute_5_12']"));

        // 11 Verify the price "$1,470.00"
        Thread.sleep(3000);
        compareText("Price not matched", "$1,475.00", By.xpath("//span[@id='price-value-1']"));

        //2.12 Click on "ADD TO CARD" Button.
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));

        //2.13 Verify the Message "The product has been added to your shopping cart" on Top green Bar
        compareText("Message not displayed","The product has been added to your shopping cart", By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]"));

        // After that close the bar clicking on the cross button
        clickOnElement(By.xpath("//body/div[@id='bar-notification']/div[1]/span[1]"));

        // Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverOnTheElement(RelativeLocator.with(By.xpath("//span[contains(text(),'Shopping cart')]")).toRightOf(By.xpath("//span[contains(text(),'Wishlist')]")));
        clickOnElement(By.xpath("//button[contains(text(),'Go to cart')]"));

        //2.15 Verify the message "Shopping cart"
        compareText("Text not displayed", "Shopping cart", By.xpath("//h1[contains(text(),'Shopping cart')]"));

        // Change the Qty to "2" and Click on "Update shopping cart"
        clickOnElement(By.xpath("//input[@class='qty-input']"));
        clearText(By.xpath("//input[@class='qty-input']"));
        sendTextToTheElement(By.xpath("//input[@class='qty-input']"), "2");
        clickOnElement(By.xpath("//button[@id='updatecart']"));

        //2.17 Verify the Total"$2,950.00"
        Thread.sleep(1000);
        compareText("Total not matched", "$2,950.00", By.xpath("//span[@class='product-subtotal']"));

        //2.18 click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));

        //2.19 Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));

        // Verify the Text “Welcome, Please Sign In!
        compareText("Message not displayed", "Welcome, Please Sign In!", By.xpath("//h1[normalize-space()='Welcome, Please Sign In!']"));

        // Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[normalize-space()='Checkout as Guest']"));

        //2.22 Fill the all mandatory field
        // firstname
        sendTextToTheElement(By.id("BillingNewAddress_FirstName"), "Daun");
        //Lastname
        sendTextToTheElement(By.id("BillingNewAddress_LastName"), "Wilkinson");
        //Email
        // Generate random Email
        final String randomEmail = randomEmail();
        sendTextToTheElement(By.id("BillingNewAddress_Email"), randomEmail);
        //select country
        dropDownOption(By.id("BillingNewAddress_CountryId"),"United Kingdom");
        // select CITY
        sendTextToTheElement(By.id("BillingNewAddress_City"), "Birmingham");
        // address 1
        sendTextToTheElement(By.id("BillingNewAddress_Address1"), "67");
        // ZIP code
        sendTextToTheElement(By.id("BillingNewAddress_ZipPostalCode"), "RT65 8UH");
        // phone number
        sendTextToTheElement(By.id("BillingNewAddress_PhoneNumber"), "07656723476");

        //2.23 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='Billing.save()']"));

        // Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.xpath("//input[@id='shippingoption_1']"));

        //2.25 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 shipping-method-next-step-button']"));

        // Select Radio Button “Credit Card”
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));

        //2.27 Select “Master card” From Select credit card dropdown
        dropDownOption(By.xpath("//select[@id='CreditCardType']"),"Master card");

        //2.28 Fill all the details
        sendTextToTheElement(By.xpath("//input[@id='CardholderName']"), "xyz");
        sendTextToTheElement(By.xpath("//input[@id='CardNumber']"), "0000000000000000");
        sendTextToTheElement(By.xpath("//select[@id='ExpireMonth']"), "10");
        sendTextToTheElement(By.xpath("//select[@id='ExpireYear']"),"2025");
        sendTextToTheElement(By.xpath("//input[@id='CardCode']"), "456");

        //2.29 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@class='button-1 payment-info-next-step-button']"));

        // Verify “Payment Method” is “Credit Card”
        compareText("does not match", "Credit Card", By.xpath("//span[normalize-space()='Credit Card']"));

        //2.32 Verify “Shipping Method” is “Next Day Air”
        compareText("does not match", "Next Day Air", By.xpath("//span[normalize-space()='Next Day Air']"));

        //2.33 Verify Total is “$2,950.00”
        compareText("wrong price", "$2,950.00", By.xpath("//span[@class='value-summary']//strong[contains(text(),'$2,950.00')]"));

        //2.34 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[normalize-space()='Confirm']"));

        // Verify the Text “Thank You”
        compareText("Text not matched", "Thank you", By.xpath("//h1[normalize-space()='Thank you']"));

        //2.36 Verify the message “Your order has been successfully processed!”
        compareText("Message not displayed", "Your order has been successfully processed!", By.xpath("//strong[normalize-space()='Your order has been successfully processed!']"));

        //2.37 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[normalize-space()='Continue']"));

        //2.37 Verify the text “Welcome to our store”
        compareText("Text not displayed", "Welcome to our store", By.xpath("//h2[normalize-space()='Welcome to our store']"));

    }

    private static String randomEmail() {
        return "Random-" + UUID.randomUUID().toString() + "@example.com";
    }
}
