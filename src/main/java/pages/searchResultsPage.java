package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class searchResultsPage extends PageBase {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public searchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // =================== Locators ===================
    private final By selectSeatBtn = By.xpath("//div[normalize-space(text())='Select Seats']");
    private final By selectSeat = By.xpath("//div[normalize-space(text())='Select Seats']");

    private By getSeatNumber(String seatIndex) {
        return By.xpath("(//div[@class='seatlook'])[" + seatIndex + "]");
    }

    private final By selectBoardingValue = By.xpath("//div[contains(@class,'pick--val') and .//div[contains(.,'CHIKKAMAGALURU')]]");
    private final By selectDroppingPointDDL = By.xpath("//div[normalize-space(text())='Select Dropping Point']");
    private final By selectDroppingValueNum1 = By.xpath("//div[contains(@class,'drop--val') and .//div[contains(.,'BENGALURU')]]");
    private final By selectDroppingValueNum2 = By.xpath("//div[contains(@class,'drop--val') and .//div[contains(.,'KEMPEGOWDA BS MAJESTIC')]]");
    private final By providePassengerDetailsBtn = By.xpath("//div[@class='btnPassDetails']");
    private final By mobileNumberField = By.xpath("//input[@name='mobileNo']");
    private final By emailField = By.xpath("//input[@name='email']");
    private final By proceedToPassengerDetailsBtn = By.xpath("//div[normalize-space()='PROCEED TO passenger detail as guest user']");
    private final By passengerNameField = By.xpath("//input[@placeholder='Name']");
    private final By passengerGenderDropdown = By.xpath("(//input[@name='paxGender[0]'])[1]");
    private final By passengerAgeField = By.xpath("(//input[@name='paxAge[0]'])[1]");
    private final By proceedToCheckoutBtn = By.xpath("(//div[normalize-space()='Proceed to Checkout'])[1]");

    private By getGenderOption(String gender) {
        return By.xpath("//div[contains(text(),'" + gender + "')]");
    }

    // =================== Seat Selection ===================
    public void selectSeatAndPoints(String seatNum) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
             wait.until(driver -> !driver.findElements(selectSeat).isEmpty());
            List<WebElement> seatButtons = driver.findElements(selectSeat);
             WebElement seatButton = seatButtons.getFirst();
             wait.until(ExpectedConditions.visibilityOf(seatButton));
            wait.until(ExpectedConditions.elementToBeClickable(seatButton));

            try {
                seatButton.click();
             } catch (ElementClickInterceptedException e) {
                 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", seatButton);
            }

        } catch (TimeoutException e) {
            throw new RuntimeException("Select Seats' buttons not found, visible, or clickable within timeout.", e);
        }


        // Step 2: Wait for seat map to appear and click the seat
        try {
            // Wait for the seat to become visible first
            WebElement seat = wait.until(ExpectedConditions.visibilityOfElementLocated(getSeatNumber(seatNum)));

            // Then wait until it's clickable
            wait.until(ExpectedConditions.elementToBeClickable(seat));

            PageBase.clickButton(seat);

        } catch (TimeoutException e) {
            throw new RuntimeException("Seat not found or not clickable.", e);
        }

        // Step 3: Select boarding point
        try {
            WebElement boardingValue = wait.until(ExpectedConditions.elementToBeClickable(selectBoardingValue));
            PageBase.clickButton(boardingValue);
        } catch (TimeoutException e) {
            throw new RuntimeException("Boarding point not found!", e);
        }

        // Step 4: Select dropping point dropdown
        try {
            WebElement droppingPointDDL = wait.until(ExpectedConditions.elementToBeClickable(selectDroppingPointDDL));
            PageBase.clickButton(droppingPointDDL);
        } catch (TimeoutException e) {
            throw new RuntimeException("Dropping point dropdown not clickable!", e);
        }

        // Step 5: Try both dropping options (BENGALURU or MAJESTIC)
        List<WebElement> bengaluruDrop = driver.findElements(selectDroppingValueNum1);
        if (!bengaluruDrop.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(bengaluruDrop.getFirst())).click();
        } else {
            List<WebElement> majesticDrop = driver.findElements(selectDroppingValueNum2);
            if (!majesticDrop.isEmpty()) {
                wait.until(ExpectedConditions.elementToBeClickable(majesticDrop.getFirst())).click();
            } else {
                throw new RuntimeException("No valid drop point found!");
            }
        }
    }

    public void clickProvidePassengerDetails() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement passengerDetailsButton = wait.until(
                    ExpectedConditions.elementToBeClickable(providePassengerDetailsBtn)
            );
            PageBase.clickButton(passengerDetailsButton);
        } catch (TimeoutException e) {
            throw new RuntimeException("'Provide Passenger Details' button not found or not clickable!", e);
        }
    }

    public void fillCustomerDetails(String mobileNumber, String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement mobileField = wait.until(ExpectedConditions.visibilityOfElementLocated(mobileNumberField));
            mobileField.clear();
            mobileField.sendKeys(mobileNumber);

            WebElement emailFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
            emailFieldElement.clear();
            emailFieldElement.sendKeys(email);

            WebElement proceedButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToPassengerDetailsBtn));
            PageBase.clickButton(proceedButton);

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to fill customer data: One or more elements not found.", e);
        }
    }

    public void fillPassengerInfo(String name, String gender, String age) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(passengerNameField));
            nameField.clear();
            nameField.sendKeys(name);

            WebElement genderDropdown = wait.until(ExpectedConditions.elementToBeClickable(passengerGenderDropdown));
            genderDropdown.click();

            WebElement genderOption = wait.until(ExpectedConditions.elementToBeClickable(getGenderOption(gender)));
            genderOption.click();

            WebElement ageField = wait.until(ExpectedConditions.visibilityOfElementLocated(passengerAgeField));
            ageField.clear();
            ageField.sendKeys(age);

            WebElement proceedButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn));
            PageBase.clickButton(proceedButton);

        } catch (TimeoutException e) {
            throw new RuntimeException("Failed to fill passenger information: One or more elements not found.", e);
        }
    }
}
