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

    // 2. Locator for the seat (using nth-child structure to match the actual HTML path)
    private final By selectSeatNumber = By.cssSelector(
            "body > div:nth-child(14) > div:nth-child(1) > div:nth-child(2) > div:nth-child(4) > " +
                    "div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > " +
                    "div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(6)"
    );


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
    private final By maleOption = By.xpath("//div[@class='pass-info']//div//div[2]");
    private final By femaleOption = By.xpath("(//div[@class='ddn'])[2]");
    private final By otherOption = By.xpath("(//div[@class='ddn'])[3]");
    private final By passengerAgeField = By.xpath("(//input[@name='paxAge[0]'])[1]");
    private final By proceedToCheckoutBtn = By.xpath("(//div[normalize-space()='Proceed to Checkout'])[1]");

    // =================== Seat Selection ===================
    public void selectSeatAndPoints() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Step 1: Click on "Select Seats" button
        List<WebElement> seatButtons = driver.findElements(selectSeat);
        if (!seatButtons.isEmpty()) {
            WebElement seatButton = seatButtons.getFirst();
            wait.until(ExpectedConditions.elementToBeClickable(seatButton));
            seatButton.click();
            System.out.println("Clicked on 'Select Seats' button.");
        } else {
            System.out.println("No 'Select Seats' buttons found!");
            return;
        }

        // Step 2: Wait for seat map to appear and click the seat
        try {
            WebElement seat = wait.until(ExpectedConditions.elementToBeClickable(selectSeatNumber));
            PageBase.clickButton(seat);
            System.out.println("Seat selected successfully.");
        } catch (TimeoutException e) {
            System.out.println("Seat not found or not clickable.");
            return;
        }

        // Step 3: Select boarding point
        try {
            WebElement boardingValue = wait.until(ExpectedConditions.elementToBeClickable(selectBoardingValue));
            PageBase.clickButton(boardingValue);
            System.out.println("Boarding point selected.");
        } catch (TimeoutException e) {
            System.out.println("Boarding point not found!");
            return;
        }

        // Step 4: Select dropping point dropdown
        try {
            WebElement droppingPointDDL = wait.until(ExpectedConditions.elementToBeClickable(selectDroppingPointDDL));
            PageBase.clickButton(droppingPointDDL);
        } catch (TimeoutException e) {
            System.out.println("Dropping point dropdown not clickable!");
            return;
        }

        // Step 5: Try both dropping options (BENGALURU or MAJESTIC)
        List<WebElement> bengaluruDrop = driver.findElements(selectDroppingValueNum1);
        if (!bengaluruDrop.isEmpty()) {
            wait.until(ExpectedConditions.elementToBeClickable(bengaluruDrop.getFirst())).click();
            System.out.println("Clicked on BENGALURU drop point.");
        } else {
            List<WebElement> majesticDrop = driver.findElements(selectDroppingValueNum2);
            if (!majesticDrop.isEmpty()) {
                wait.until(ExpectedConditions.elementToBeClickable(majesticDrop.getFirst())).click();
                System.out.println("Clicked on KEMPEGOWDA BS MAJESTIC drop point.");
            } else {
                System.out.println("No valid drop point found!");
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
            System.out.println("Clicked on 'Provide Passenger Details' button.");
        } catch (TimeoutException e) {
            System.out.println("'Provide Passenger Details' button not found or not clickable!");
        }
    }
    public void fillCustomerDetails(String mobileNumber, String email) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Enter Mobile Number
            WebElement mobileField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(mobileNumberField)
            );
            mobileField.clear();
            mobileField.sendKeys(mobileNumber);
            System.out.println("Entered mobile number: " + mobileNumber);

            // Validate and Enter Email
            if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                System.out.println("Invalid email format: " + email);
                return;
            }

            WebElement emailFieldElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(emailField)
            );
            emailFieldElement.clear();
            emailFieldElement.sendKeys(email);
            System.out.println("Entered email: " + email);

            // Click Proceed Button
            WebElement proceedButton = wait.until(
                    ExpectedConditions.elementToBeClickable(proceedToPassengerDetailsBtn)
            );
            PageBase.clickButton(proceedButton);
            System.out.println("Clicked on 'PROCEED TO passenger detail as guest user' button.");

        } catch (TimeoutException e) {
            System.out.println("Failed to fill customer data: One or more elements not found.");
        }
    }
    public void fillPassengerInfo(String name, String gender, String age) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Step 1: Enter Passenger Name
            WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(passengerNameField));
            nameField.clear();
            nameField.sendKeys(name);
            System.out.println("Entered passenger name: " + name);

            // Step 2: Select Gender
            WebElement genderDropdown = wait.until(ExpectedConditions.elementToBeClickable(passengerGenderDropdown));
            genderDropdown.click();
            System.out.println("Opened gender dropdown.");

            switch (gender.toLowerCase()) {
                case "male":
                    wait.until(ExpectedConditions.elementToBeClickable(maleOption)).click();
                    System.out.println("Selected gender: Male");
                    break;
                case "female":
                    wait.until(ExpectedConditions.elementToBeClickable(femaleOption)).click();
                    System.out.println("Selected gender: Female");
                    break;
                case "other":
                    wait.until(ExpectedConditions.elementToBeClickable(otherOption)).click();
                    System.out.println("Selected gender: Other");
                    break;
                default:
                    System.out.println("Invalid gender input: " + gender + ". Please use Male, Female, or Other.");
                    return;
            }

            // Step 3: Enter Passenger Age
            WebElement ageField = wait.until(ExpectedConditions.visibilityOfElementLocated(passengerAgeField));
            ageField.clear();
            ageField.sendKeys(age);
            System.out.println("Entered passenger age: " + age);

            // Step 4: Click "Proceed to Checkout"
            WebElement proceedButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutBtn));
            PageBase.clickButton(proceedButton);
            System.out.println("Clicked on 'Proceed to Checkout' button.");

        } catch (TimeoutException e) {
            System.out.println("Failed to fill passenger information: One or more elements not found.");
        }
    }


}
