import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Example6 {

    static WebDriver driver;

    @Test
    public void aletrTest() throws InterruptedException {
        String systemName = System.getProperty("os.name").toLowerCase();
        if (systemName.contains("windows")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\bin\\chromedriver.exe");
        } else if (systemName.contains("linux")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/bin/chromedriver_linux");
        } else {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/bin/chromedriver_osx");
        }

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html\n");

        WebElement aletrButton = driver.findElement(By.xpath("(//button['Click me!'])[2]"));
        aletrButton.click();
        String alertText = driver.switchTo().alert().getText();
        Assert.assertEquals("I am an alert box!", alertText);
        Thread.sleep(5000);
        driver.switchTo().alert().accept();
        Thread.sleep(5000);
        driver.switchTo().defaultContent();
        driver.close();

    }
}

