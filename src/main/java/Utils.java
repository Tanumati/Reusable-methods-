import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Utils extends Basepage {
    // #1.to  open chromebrowser
    public static void ToLaunchBrowser() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\Resources\\Browser driver\\chromedriver.exe");
        //open the  chrome browser
        driver = new ChromeDriver();
        // maximise the browser screen
        driver.manage().window().fullscreen();
        //set implicity wait for web object
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    // #2. To closebrowser
    public static void ToClosebrowser() {
        //browser close
        driver.quit();
    }

    // #3. To enter text in fields
    public void EnterText(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    // #4.To Enter text from element
    public String EnterTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    // #5. To click elements
    public String ClickElements(By by) {
        driver.findElement(by).click();
        return null;
    }
    // #6. Enter the value
    public void SelectByValue(By by, String text) {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }
    // #7. wait for clickable /explicit wait method
    public static void WaitForClickable(By by, Long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    // #8. wait for alert present / explicit wait method
    public static void WaitForAlertPresent(long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.alertIsPresent());
    }
    // #9. wait util elements visible / explicit wait method
    public static void WaitForElementsvisible(By by, long time) {
        WebDriverWait wait = new WebDriverWait(driver, time);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    // #10. clear text form elements
    public void ClearText(By by) {
        //Clear Text form inout box/area
        driver.findElement(by).clear();
    }
    // #11. clear and enter text
    public void ClearAndEnterText(By by, String text) {
        //Clear and enter text in input field
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }
    // #12.
    public String genarateEmail(String starvalue) {
        String email = starvalue.concat(new Date().toString());
        return email;
    }
    // #13. random date
    public static String randomeDate() {
        DateFormat formate = new SimpleDateFormat("ddMMyyHHMMss");
        return formate.format(new Date());
    }
    // #14. navigate to url
    public static void NavigateToUrl(String text)
    {
        driver.navigate().to(text);
    }
    // #15. page load time
    public static void pageLoad(long value, TimeUnit time) {
        driver.manage().timeouts().pageLoadTimeout(value, time);

    }
    // #16. To select by index drag ang drop
    public  void SelectByIndex(By by , int value)
    { Select select = new Select(driver.findElement(by));
      select.deselectByIndex(value);
    }
    // #17. To select by visible text drag and drop
    public void SelectByVisibleText(By by, String value){
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(value);
    }
    // #18.checking web elements is display or not
    public void WebElementsDispaly(By by ){
        if (driver.findElement(by).isDisplayed()) {
            System.out.println("Elements is present");
        }else {
            System.out.println("Elements is Absent");
        }
    }
    // #19. elements pesent in dom
    public void CheckWebelementPresentInDom(By by) {
        ///Checking WebElemnt present in DOM
        if (driver.findElement(by) != null) {
            System.out.println("Elements is present");
        } else {
            System.out.println("Elements is not present");
        }
    }
    // #20.wait for web elements is invisible
    public static void ElementsInvisible(By by, long time) {
            WebDriverWait wait = new WebDriverWait(driver, time);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    // #21.wait for web elements is invisible text
   public static void ElementsInvisible(By by,String text,long time){
        WebDriverWait wait =new WebDriverWait(driver,time);
        wait.until(ExpectedConditions.invisibilityOfElementWithText(by,text));
   }
   // #22.scrol to elements
    public static void ScrollToElements(By by){
        Actions actions = new Actions(driver);
        actions.moveToElement((WebElement) by).perform();
    }
    // #23.scroll to elements and click
    public static void ScrollToElementsAndClick(By by){
        Actions actions = new Actions(driver);
        actions.moveToElement((WebElement) by).click();
    }
    // #24. screenshot methods
   /* public static void takeSnapShot(String fileWithPath) throws Exception{
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File DestFile=new File(fileWithPath);
        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);*/



}
