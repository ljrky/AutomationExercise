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
import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForElementToBeVisible;


public class HomePage{

    private WebDriver driver;
    private List<String> products = new ArrayList<String>();

    @FindBy(how = How.ID, using = "globalSearch")
    public static WebElement FieldSearch;

    @FindBy(how = How.ID, using = "etfProductSearch")
    public static WebElement FieldETFProductSearch;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
                120);
        PageFactory.initElements(finder, this);
    }

    public boolean isSearchDisplayed() {
        return FieldSearch.isDisplayed() && FieldETFProductSearch.isDisplayed();
    }

    public void input(String searchString) {
        FieldETFProductSearch.clear();
        FieldETFProductSearch.sendKeys(searchString);
    }

    public void search() {
        FieldETFProductSearch.submit();
    }

    public int searchSuggestionNumber() {
        int counter = 0;
        WebElement searchSuggestion = driver.findElement(By.cssSelector("a[class='ui-corner-all']"));
        WaitForElementToBeVisible(driver, searchSuggestion);
        List<WebElement> popups = driver.findElements(By.cssSelector("li[class='etfSearch_result ui-menu-item']"));
        if (!popups.isEmpty()) {
            for (WebElement popup : popups){
                if (popup.getText().contains("SF UCITS ETF")){
                    System.out.println("Found the suggest : " + popup.getText());
                    products.add(popup.getText());
                    counter++;
                }
            }
        }
        return counter;
    }

    public boolean isProductsAppear() {
        if (!products.isEmpty()) {
            for (String product : products) {
                if (driver.findElement(By.partialLinkText(product)).isDisplayed() == false) {
                    return false;
                }
                else {
                    System.out.println("Found the product : " + product);
                }
            }
        }
        return true;
    }
}
