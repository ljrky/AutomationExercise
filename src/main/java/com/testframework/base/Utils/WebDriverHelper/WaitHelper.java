package com.testframework.base.Utils.WebDriverHelper;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WaitHelper {
    static int numberOfRetry = 10;
    public static int waitForElementTimeout = 30;
    static int sleepTime = waitForElementTimeout / 10;


    public static void SetImplicitlyWaitForAllElement(WebDriver driver) {
        System.out.println("waitForElementTimeout is set to : " + waitForElementTimeout);
        System.out.println("sleepTime is set to : " + sleepTime);
        driver.manage().timeouts().implicitlyWait(waitForElementTimeout, TimeUnit.SECONDS);
    }

    public static void WaitForElement(WebElement Element){
        for (int i = 0; i < numberOfRetry; i++){
            try {
                if (Element.isDisplayed()){
                    break;
                }
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void WaitForElementToBeInvisible(WebElement Element){
        for (int i = 0; i < numberOfRetry; i++){
            try {
                if (Element.isDisplayed() == false){
                    break;
                }
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void WaitForElementEnabled(WebElement Element){
        for (int i = 0; i < numberOfRetry; i++){
            try {
                if (Element.isEnabled()){
                    break;
                }
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void WaitForElementToBeClickable(WebDriver driver, WebElement Element){
        WebDriverWait wait = new WebDriverWait(driver, waitForElementTimeout);
        wait.until(ExpectedConditions.elementToBeClickable(Element));
    }

    public static void WaitForElementToBeVisible(WebDriver driver, WebElement Element){
        WebDriverWait wait = new WebDriverWait(driver, waitForElementTimeout);
        wait.until(ExpectedConditions.visibilityOf(Element));
    }

    public static void WaitWithOutExcpetion(WebDriver driver, By by){
        FluentWait wait = new FluentWait(driver);
        wait.withTimeout(waitForElementTimeout,TimeUnit.SECONDS).pollingEvery(10,TimeUnit.MILLISECONDS).ignoring(org.openqa.selenium.NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void WaitForSeconds(int seconds){
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
