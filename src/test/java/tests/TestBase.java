package tests;

import com.beust.jcommander.Parameter;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TestBase {

    public static WebDriver driver;

    @BeforeClass
    @Parameters("browser")
    public void startDriver(@Optional("chrome") String browserName) {
        String downloadFilePath = new File("Downloads").getAbsolutePath();

        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("download_default_directory", downloadFilePath);
            options.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();

        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        }
        driver.get("https://ksrtc.in/");
        driver.manage().window().setSize(new Dimension(1024,768));

    }

}