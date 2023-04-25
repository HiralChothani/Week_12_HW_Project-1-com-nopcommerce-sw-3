package homepage;

import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {
    public void selectMenu(String menu){
        driver.findElement(By.linkText(menu)).click();
        if (menu == "Computers") {
            //Select computer tab and click
            clickOnElement(By.linkText("Computers"));
            //Verify the page navigation
            compareText("","Computers", By.xpath("//div[@class='page-title']"));
        } else if (menu == "electronics") {
            clickOnElement(By.linkText("electronics"));
            compareText("", "electronics",By.className("page-title"));
        } else if (menu == "Apparel") {
            clickOnElement(By.linkText("Apparel"));
            compareText("","Apparel",By.className("page-title"));
        } else if (menu == "Digital downloads") {
            clickOnElement(By.linkText("Digital downloads"));
            compareText("","Digital downloads",By.className("page-title"));
        } else if (menu == "Books") {
            clickOnElement(By.linkText("Books"));
            compareText("","Books",By.className("page-title"));
        } else if (menu == "Jewelry") {
            clickOnElement(By.linkText("Jewelry"));
            compareText("","Jewelry",By.className("page-title"));
        } else if (menu == "Gift Cards") {
            clickOnElement(By.linkText("Gift Cards"));
            compareText("","Gift Cards",By.className("page-title"));
        } else {
            System.out.println("Please enter valid Top-menu tab or check actual name");
        }
    }

    @Test
    public void verifyPageNavigation(){
        selectMenu("Computers");
        selectMenu("electronics");
        selectMenu("Apparel");
        selectMenu("Digital downloads");
        selectMenu("Books");
        selectMenu("Jewelry");
        selectMenu("Gift Cards");

    }
}
