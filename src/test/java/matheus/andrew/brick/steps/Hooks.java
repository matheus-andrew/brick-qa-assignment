package matheus.andrew.brick.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

@AllArgsConstructor
public class Hooks {

    public static WebDriver driver;

    @Before
    public void BeforeSteps() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("−−incognito");
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.setHeadless(false);
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }

    @After
    public void AfterSteps() {
        driver.close();
    }
}
