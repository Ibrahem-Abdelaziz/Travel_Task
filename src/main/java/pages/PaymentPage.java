package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage extends PageBase {
    private final WebDriver driver ;
    private final WebDriverWait wait;


    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    }

    private final By easebuzzOption = By.xpath("(//div[@class='pg--text'])[1]");
     private final By proceedToPayButton = By.xpath("//div[normalize-space()='PROCEED TO PAY']");

    private final By agreePopupButton = By.xpath("(//div[normalize-space()='AGREE'])[1]");



    public void selectPayment() {


        WebElement paymentOption = wait.until(
                ExpectedConditions.visibilityOfElementLocated(easebuzzOption)
        );
        wait.until(ExpectedConditions.elementToBeClickable(paymentOption));

        PageBase.scrollToElement(driver, paymentOption);
        PageBase.clickWithJS(driver, paymentOption);
    }

    public void clickProceedToPay() {


        WebElement proceedButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(proceedToPayButton)
        );
        wait.until(ExpectedConditions.elementToBeClickable(proceedButton));

        PageBase.scrollToElement(driver, proceedButton);
        PageBase.clickWithJS(driver, proceedButton);
    }

    public void clickAgree() {


        WebElement agreeButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(agreePopupButton)
        );
        wait.until(ExpectedConditions.elementToBeClickable(agreeButton));

        PageBase.scrollToElement(driver, agreeButton);
        PageBase.clickWithJS(driver, agreeButton);
    }

}





