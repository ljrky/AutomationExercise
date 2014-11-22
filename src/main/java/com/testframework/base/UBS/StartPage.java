package com.testframework.base.UBS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.util.ArrayList;
import java.util.List;

import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForElementEnabled;


public class StartPage {

    private WebDriver driver;
    private String startPage = "http://www.ubs.com/ch/en/asset_management/etfs/etf-institutional.html";

    @FindBy(how = How.ID, using = "Confirmation_1")
    public static WebElement CheckboxConfirmation;

    @FindBy(how = How.CLASS_NAME, using = "button-prim")
    public static WebElement ButtonSubmit;

    @FindBy(how = How.CLASS_NAME, using = "cboxIframe")
    public static WebElement IFrame;


    public StartPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
                120);
        PageFactory.initElements(finder, this);
    }

    public void acceptCookies() {
        if(IFrame.isDisplayed()){
            driver.switchTo().frame(IFrame);
            WebElement ButtonAllowAllCookiesAndContinue = driver.findElement(By.cssSelector("a[class='button button-prim cookieSettings']"));
            ButtonAllowAllCookiesAndContinue.click();
        }
    }

    public void confirmSwitzerland() {
        CheckboxConfirmation.click();
    }

    public void submit() {
        ButtonSubmit.click();
    }

    public void goToPage() {
        driver.get(startPage);
//        acceptCookies();
        confirmSwitzerland();
        submit();
    }

}
