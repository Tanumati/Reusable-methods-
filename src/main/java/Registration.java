import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Registration extends Utils {
    Loadprops loadprops = new Loadprops();

    @BeforeMethod
    // called method to launch browser and url
    public void LunchBrowser() {
        ToLaunchBrowser();
        driver.get(loadprops.getProperty("url"));
    }

    @AfterMethod
    //called method to close browser
    public void teardown() {
       ToClosebrowser();
    }

    @Test
    public void UserShouldBeAbleToRegistration() {
        //Click on register
        ClickElements(By.xpath("//a[@class='ico-register']"));
        // Enter First name
        EnterText(By.id("FirstName"),loadprops.getProperty("Firstname"));
        //enter lastname
        EnterText(By.xpath("//input[@name ='LastName']"),loadprops.getProperty("Lastname"));
        // select date of birth
        //select day
        SelectByValue(By.xpath("//select[@name=\"DateOfBirthDay\"]"), "4");
        // select month
        SelectByValue(By.xpath("//select[@name=\"DateOfBirthMonth\"]"), "August");
        //select year
        SelectByValue(By.xpath("//Select[@name=\"DateOfBirthYear\"]"), "1910");
        // enter emial
        EnterText(By.name("Email"),loadprops.getProperty("Emailpart1")+randomeDate()+ loadprops.getProperty("Emailpart2"));
        // Enter password
        EnterText(By.id("Password"), loadprops.getProperty("Password"));
        //Enter Confirm password
        EnterText(By.name("ConfirmPassword"), loadprops.getProperty("Confirmpassword"));
        // click on register
        ClickElements(By.name("register-button"));
        String expected = "Your registration completed";
        String actualmsg = EnterTextFromElement(By.xpath("//div[@class='result']"));
        Assert.assertEquals(actualmsg, expected);

    }

    @Test
    public void UserShouldBeAbleSendAProductEmail() {
        // call method for registration
        UserShouldBeAbleToRegistration();
        //navigate homepage
        ClickElements(By.xpath("//img[@alt=\"nopCommerce demo store\"]"));
        //click macbook
        ClickElements(By.xpath("//img[@alt=\"Picture of Apple MacBook Pro 13-inch\"]"));
        // send to friend
        ClickElements(By.xpath("//input[@value=\"Email a friend\"]"));
        //enter friend email
        EnterText(By.id("FriendEmail"), loadprops.getProperty("Emailafriend"));
        // click email
        ClickElements(By.xpath("//input[@placeholder=\"Enter your email address.\"]"));
        // send message
        EnterText(By.xpath("//textarea[@class=\"your-email\"]"),loadprops.getProperty("SendMessage"));
        // click send
        ClickElements(By.xpath("//input[@name=\"send-email\"]"));
        String expectedsentmsg = "Your message has been sent.";
        String actualsentmsg = EnterTextFromElement(By.xpath("//*[@class='result'and contains(text(),'Your message has been sent.')]"));
        Assert.assertEquals(actualsentmsg, expectedsentmsg);
    }

    @Test
    public void UserShouldBeAbleToNavigateCameraAndPhoto() {
        //navigate to eloctonics
        ClickElements(By.xpath("//h2/a[@title=\"Show products in category Electronics\"]"));
        //navigate to photo and camera
        ClickElements(By.xpath("//h2/a[@title=\"Show products in category Camera & photo\"]"));
        String expectedtitle = "Camera & photo";
        String actualtitle = EnterTextFromElement(By.xpath("//div[@class=\"page-title\"]"));
        Assert.assertEquals(actualtitle, expectedtitle);
    }

    @Test
    public void UserShouldBeAbleToFilterJwellery() {
        //navigate to jewelry
        ClickElements(By.linkText("Jewelry"));
        //Select range
        ClickElements(By.xpath("//a[@href=\"//demo.nopcommerce.com/jewelry?price=700-3000\"]"));
        //save expectedtitle by locators
        String expectedtitle = "$700.00 - $3,000.00";
        //save actualtitle by locators
        String actualtitle = EnterTextFromElement(By.xpath("//span[@class= 'item']"));
        Assert.assertEquals(actualtitle, expectedtitle);
        //find minimum price
        String minimumrange = EnterTextFromElement(By.xpath("//span[@class=\"PriceRange\"and text()= '$700.00']"));
        System.out.println(minimumrange);
        // find Actual price
        String actualrange = EnterTextFromElement(By.xpath("//span[@class=\"price actual-price\" and text() ='$2,100.00']"));
        System.out.println(actualrange);
        //find maximum price
        String maximumrange = EnterTextFromElement(By.xpath("//span[@class=\"PriceRange\"and text()= '$3,000.00']"));
        System.out.println(maximumrange);
        // convert string minrange to float
        float minrange = Float.parseFloat(minimumrange.substring(1));
        System.out.println(minrange);
        //convert String actualrange
        float arange = Float.parseFloat(actualrange.replace(",", "").substring(1));
        System.out.println(arange);
        //Convert String maxminrange
        float maxrange = Float.parseFloat(maximumrange.replace(",", "").substring(1));
        System.out.println(maxrange);
        //checking actual price between minimum and maximum range
        Assert.assertTrue(arange >= minrange && arange <= maxrange);
    }

    @Test
    public void UserShouldBeAbletoTwoProductInShoppingCart() {
        //click on books category
        ClickElements(By.linkText("Books"));
        //click on  book name  'First Prize Pies'
        ClickElements(By.linkText("First Prize Pies"));
        //Click on add to cart
        ClickElements(By.xpath("//input[@id=\"add-to-cart-button-38\"]"));
        // webdriver wait to add product into basket
        //pageLoad(5,TimeUnit.SECONDS);
        WaitForElementsvisible(By.xpath("//span[@class=\"cart-qty\" and text()='(1)']"),5);
        //click on book name 'Fahrenheit 451 by Ray Bradbury'
        ClickElements(By.linkText("Fahrenheit 451 by Ray Bradbury"));
        //click on add to cart
        ClickElements(By.xpath("//input[@id=\"add-to-cart-button-37\"]"));
        // webdriver wait to add product into basket
        WaitForElementsvisible(By.xpath("//span[@class=\"cart-qty\" and text()='(2)']"),5);
        // cllick on shopping cart
        ClickElements(By.linkText("shopping cart"));
        //click on shopping cart
        String expextedresult = "Shopping cart";
        //actual  quantity locators
        String actualresult = EnterTextFromElement(By.linkText("Shopping cart"));
        //compairing with actual quantity by Expected
        Assert.assertEquals(actualresult,expextedresult);
        //To varify book 1
        String expectedbook1 = "First Prize Pies";
        //actualbook1 locators
        String actualbook1 =EnterTextFromElement(By.linkText("First Prize Pies"));
        Assert.assertEquals(actualbook1,expectedbook1);
        // to varify book 2
        String expectedbook2 = "Fahrenheit 451 by Ray Bradbury";
        //actual book 2 locators
        String actualbook2 = EnterTextFromElement(By.linkText("Fahrenheit 451 by Ray Bradbury"));
        Assert.assertEquals(actualbook2,expectedbook2);
    }
}