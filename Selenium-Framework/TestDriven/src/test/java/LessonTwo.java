
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserHelper;
import utils.DriverManager;

import java.text.MessageFormat;


public class LessonTwo {

    public static WebDriver driver;

    @BeforeEach
    public void setUp(){

        String browserType = "chrome";
        String message = MessageFormat.format("Opening a {0} browser", browserType);
        System.out.println(message);

        // Set the driver
        driver = DriverManager.getDriver(browserType);

    }

    @AfterEach
    public void tearDown(){
        // Quit the driver in the DriverManager class
        DriverManager.quitDriver();
    }



    @Test
    public void testOne(){
        // Write your test logic here
        String url ="https://services.nhsbsa.nhs.uk/cra/start";

        BrowserHelper.MaximiseBrowser();
        BrowserHelper.NavigateToUrl(url);
        WebElement startButton = BrowserHelper.ElementIdentifier("id=startButton");
        assert startButton != null;
        startButton.click();

    }


}
