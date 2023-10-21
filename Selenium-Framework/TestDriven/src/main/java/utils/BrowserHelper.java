package utils;

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

public class BrowserHelper {

    public static void MaximiseBrowser(){
        DriverManager.getDriver().manage().window().maximize();
    }

    public static void NavigateToUrl(String url){
        DriverManager.getDriver().get(url);

        String message = MessageFormat.format("Navigating to {0} url", url);
        System.out.println(message);
    }

    public static void scrollToElement(WebElement element){
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void highlightElement(WebElement element) {
        // Create a JavaScriptExecutor instance
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

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

    public static WebElement ElementIdentifier(String element) {
        String[] elementString = element.split("=");
        WebElement elementLocator = null;

        if (elementString[0].equalsIgnoreCase("id")) {
            elementLocator = DriverManager.getDriver().findElement(By.id(elementString[1]));
        }

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOf(elementLocator));

        scrollToElement(elementLocator);

        highlightElement(elementLocator);

        return elementLocator;
    }


}
