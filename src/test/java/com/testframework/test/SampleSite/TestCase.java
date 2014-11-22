package com.testframework.test.SampleSite;

import com.testframework.base.UBS.HomePage;
import com.testframework.base.BaseTestCase.SimpleTestCase;
import com.testframework.base.UBS.ProductPage;
import com.testframework.base.UBS.StartPage;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

import static com.testframework.base.Utils.TestDataHelper.GetResourceBundle.getResourceBundle;

public class TestCase extends SimpleTestCase {

    public StartPage startPage;
    public HomePage homePage;
    public ProductPage productPage;

    @BeforeMethod
    public void beforeMethod(){
        //Login Function
        Reporter.log("Navigate to home page; \n", 1);
        startPage = new StartPage(driver);
        Reporter.log("Accept the cookies popup and confirm that you are a qualified investor in Switzerland; \n", 1);
        startPage.goToPage();
    }

    @Test()
    public void TC1() {
        Reporter.log("TC1 starting \n", 1);
        Reporter.log("1) Go to page http://www.ubs.com/ch/en/asset_management/etfs/etf-institutional.html\n", 1);
        homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isSearchDisplayed(), "Page is NOT opened properly.");

        Reporter.log("2) Introduce \"CMCI Composite\" on the ETF product search field.\n", 1);
        homePage.input("CMCI Composite");
        Assert.assertEquals(homePage.searchSuggestionNumber(), 3, "3 products are NOT displayed on the search suggestion overlay (that appears while typing).");

        Reporter.log("3) Press the search button.\n", 1);
        homePage.search();
        Assert.assertTrue(homePage.isProductsAppear(), "The same 3 products NOT appears on the search results page (after sending the search request).");
    }

    @Test()
    public void TC2() {
        Reporter.log("TC2 starting \n", 1);
        Reporter.log("1) Go to product http://www.ubs.com/ch/en/asset_management/etfs/etfinstitutional/etf_products/etf_product_detail.ch.en.ie00b58fqx63.basedata.html\n", 1);
        productPage = new ProductPage(driver);
        productPage.goToPage();
        Assert.assertTrue(productPage.isProductPageOpened(), "Product is NOT opened.");
        Assert.assertTrue(productPage.isTabFundProfileSelected(), "the \"Funds profile\" tab is NOT selected.");

        Reporter.log("2) Go to \"Index information\" tab and check the index name.\n", 1);
        productPage.goToIndexInformationTab();
        Assert.assertEquals(productPage.getIndexValue("Index name"), "UBS Bloomberg CMCI® hedged to CHF Total Return", "Index name value is NOT : \"UBS Bloomberg CMCI® to CHF Total Return\"");
    }

}
