package testUtils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    public String getScreenshot(String TestCaseName, WebDriver driver) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "//report//screenshots//" + TestCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "//report//screenshots//" + TestCaseName + ".png";
    }
}
