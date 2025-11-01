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
    private final By razorpayOption = By.xpath("(//div[@class='pg--text'])[2]");
    private final By proceedToPayButton = By.xpath("//div[normalize-space()='PROCEED TO PAY']");

    private final By agreePopupButton = By.xpath("(//div[normalize-space()='AGREE'])[1]");



    private void safeClick(By locator) {
        int retries = 3;
        while (retries > 0) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
                return;
            } catch (StaleElementReferenceException e) {
                System.out.println("⚠️ Stale element detected, retrying click... (" + (4 - retries) + ")");
                retries--;
            } catch (TimeoutException e) {
                throw new RuntimeException("❌ Timeout waiting for element: " + locator);
            }
        }
        throw new RuntimeException("❌ Failed to click after retries: " + locator);
    }

    // ==============================
    // 🔸 Page Actions
    // ==============================

    /** Select the Easebuzz payment option */
    public void selectEasebuzzPayment() {
        safeClick(easebuzzOption);
        System.out.println("✅ Selected Easebuzz payment option.");
    }

    /** Select the Razorpay payment option */
    public void selectRazorpayPayment() {
        safeClick(razorpayOption);
        System.out.println("✅ Selected Razorpay payment option.");
    }

    /** Proceed to pay and handle the AGREE popup if it appears */
    public void clickProceedToPay() {
        safeClick(proceedToPayButton);
        System.out.println("✅ Clicked 'PROCEED TO PAY'.");
        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
    }
    public void clickAgree() {
        try {
            WebElement agreeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(agreePopupButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", agreeButton);
            System.out.println("✅ Clicked 'AGREE' popup.");
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        } catch (TimeoutException ignored) {
            System.out.println("ℹ️ No 'AGREE' popup appeared.");
        }
    }
}





