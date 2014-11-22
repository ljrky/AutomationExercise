package com.testframework.base.UBS;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForElement;
import static com.testframework.base.Utils.WebDriverHelper.WaitHelper.WaitForElementEnabled;

public class ProductPage {

    private WebDriver driver;
    private String productPage = "http://www.ubs.com/ch/en/asset_management/etfs/etf-institutional/etf_products/etf_product_detail.ch.en.ie00b58fqx63.basedata.html";
    private Map<String, String> tableContent = new HashMap<String, String>();

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Fund profile")
    public static WebElement TabFundProfile;

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Index information")
    public static WebElement TabIndexInformation;

    @FindBy(how = How.CSS, using = "table[class='table quotes']")
    public static WebElement Table;


    public ProductPage(WebDriver driver) {
        this.driver = driver;
        ElementLocatorFactory finder = new AjaxElementLocatorFactory(driver,
                120);
        PageFactory.initElements(finder, this);
    }

    public void goToPage() {
        driver.get(productPage);
    }

    public void goToIndexInformationTab() {
        TabIndexInformation.click();
    }

    public boolean isProductPageOpened() {
        return driver.getTitle().contains("ETF Product Detail");
    }

    public boolean isTabFundProfileSelected() {
        if(TabFundProfile.findElement(By.xpath("..")).getAttribute("class").contains("active")){
            return true;
        }
        else {
            return false;
        }
    }

    private void getTableContent(WebElement Table){
        List<WebElement> rows = Table.findElements(By.tagName("tr"));
        String temp = null;
        for(WebElement row : rows){
            System.out.println("content of ROW : " + row.findElement(By.tagName("th")).getText());
            System.out.println("content of ROW : " + row.findElement(By.tagName("td")).getText());
            tableContent.put(row.findElement(By.tagName("th")).getText(), row.findElement(By.tagName("td")).getText());
        }
    }

    public String getIndexValue(String indexName){
        getTableContent(Table);
        System.out.println("Actually result is : " + tableContent.get(indexName));
        return tableContent.get(indexName);
    }
}
