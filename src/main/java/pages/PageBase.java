package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PageBase {

    protected static void clickButton(WebElement button) {
        button.click();
    }


    protected static void setTxtToElement(WebElement txtElement, String value) {
        txtElement.sendKeys(value);
    }


    protected static void selectElementByTxt(WebElement select, String value) {
        Select dropdown = new Select(select);
        dropdown.selectByVisibleText(value);
    }

    protected static void setInputText(WebElement inputElement, String value) {
        inputElement.sendKeys(value);
    }


    protected static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
    }


    protected static void clickWithJS(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

}
