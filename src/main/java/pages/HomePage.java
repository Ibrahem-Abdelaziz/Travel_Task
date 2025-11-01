package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private final WebDriver driver ;




    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By leaveFromDDL = By.cssSelector("div[id='fromCity_chosen'] span") ;
    private final By searchTXTBoxForLeave = By.xpath(("//div[@id='fromCity_chosen']//input")) ;
 private  final  By goingToCity = By.cssSelector("div[id='toCity_chosen'] span") ;
    private final By searchTXTBoxForGoingTo = By.xpath(("//div[@id='toCity_chosen']//input")) ;

    private  final  By calenderIcon = By.xpath("//input[@id='departDate']") ;

    private  final By dateSelection = By.xpath("//a[normalize-space()='27']") ;

    private  final  By searchButton = By.id("submitSearch") ;






    public void selectLeaveCity()
    {
        PageBase.clickButton(driver.findElement(leaveFromDDL));
        PageBase.setTxtToElement(driver.findElement(searchTXTBoxForLeave) , "Chikkamagaluru");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='fromCity_chosen']//ul[@class='chosen-results']/li[contains(.,'Chikkamagaluru')]")));
        option.click();


    }

    public void selectGoingToCity()
    {
        PageBase.clickButton(driver.findElement(goingToCity));
        PageBase.setTxtToElement(driver.findElement(searchTXTBoxForGoingTo) , "Bengaluru");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='toCity_chosen']//ul[@class='chosen-results']/li[contains(.,'Bengaluru')]")));
        option.click();


    }

    public void selectDate()
    {
        PageBase.clickButton(driver.findElement(calenderIcon));
        WebElement dateElement = driver.findElement(dateSelection);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", dateElement);
        js.executeScript("arguments[0].click();", dateElement);


    }

    public void submitSearch()
    {
        WebElement button = driver.findElement(searchButton);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", button);


    }



}
