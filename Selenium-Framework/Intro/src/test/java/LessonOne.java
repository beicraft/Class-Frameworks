import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.MessageFormat;
import java.time.Duration;

public class LessonOne {

    public static WebDriver driver;

    public static void main(String[] args) {

        String url = "https://services.nhsbsa.nhs.uk/cra/start";

        OpenBrowser("chrome");
        MaximiseBrowser();
        NavigateToUrl(url);
        WebElement startButton = ElementIdentifier("id=startButton");
        assert startButton != null;
        startButton.click();
        CloseBrowser();

    }

    public static WebElement ElementIdentifier(String element) {
        String[] elementString = element.split("=");
        WebElement elementLocator = null;

        if (elementString[0].equalsIgnoreCase("id")) {
            elementLocator = driver.findElement(By.id(elementString[1]));
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOf(elementLocator));

        scrollToElement(elementLocator);

        highlightElement(elementLocator);

            return elementLocator;
    }

    public static void scrollToElement(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }


        public static void highlightElement(WebElement element) {
            // Create a JavaScriptExecutor instance
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Define the JavaScript code to highlight the element
            String highlightJS = "arguments[0].style.border = '3px solid red';";

            // Execute the JavaScript code to highlight the element
            js.executeScript(highlightJS, element);

            // Wait for a short time to see the highlighted element (you can adjust this)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Reset the element's style to its original state
            String resetJS = "arguments[0].style.border = ''";
            js.executeScript(resetJS, element);
        }




    public static void OpenBrowser(String BrowserType){

        if(BrowserType.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (BrowserType.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver();
            driver = new FirefoxDriver();
        }
        String message = MessageFormat.format("Opening a {0} browser", BrowserType);
        System.out.println(message);
    }

    public static void NavigateToUrl(String url){
        driver.get(url);

        String message = MessageFormat.format("Navigating to {0} url", url);
        System.out.println(message);
    }

    public static void CloseBrowser(){
        driver.quit();

        System.out.println("Closed browser");
    }

    public static void MaximiseBrowser(){
        driver.manage().window().maximize();
    }



}
