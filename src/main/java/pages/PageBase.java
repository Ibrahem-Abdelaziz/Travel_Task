package pages;

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

}
