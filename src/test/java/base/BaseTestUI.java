package base;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pom.HomePage;
import utils.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class BaseTestUI extends BaseTest {
    public WebDriver driver;
    protected HomePage homePage;

    public WebDriver initializeDriver() {
        String browserName = Utils.getGlobalValue("browser");

        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "chrome-headless": // option with headless mode - without opening real browser. Rahul waking it with for-loop. and if statements
                //I decided like just one more possible scenario
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080"); //Best to have in headless mode. to be sure it will open window fullscreeen
                driver = new ChromeDriver(options);
                driver.manage().window().setSize(new Dimension(1920, 1080)); //to be sure BEST to have both rows with window size
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                throw new IllegalArgumentException("Браузер не підтримується: " + browserName);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public List<HashMap<String, String>> getJsonDataToHasMap(String filePath) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<>() {
        });
    }

    @BeforeMethod(alwaysRun = true)
    public void launchApplication() {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        homePage.goToHomePage();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        driver.quit();
    }
}