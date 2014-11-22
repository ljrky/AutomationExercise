package com.testframework.base.Utils.WebDriverHelper;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class TakeScreenshotHelper {
    public static void getScreenShot(WebDriver driver) {
        if (driver instanceof TakesScreenshot) {
            final File screenshot = new File(System.currentTimeMillis() + "screenshot.png");
            final File tmpScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(tmpScreenshot, screenshot);
            } catch (IOException e) {
                System.out.println("Failed to take screenshot." + e.getMessage());
            }
        }
    }
}
