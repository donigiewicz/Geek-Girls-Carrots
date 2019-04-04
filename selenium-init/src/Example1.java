import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Example1 {

    public static void main(String[] args) {
        String systemName = System.getProperty("os.name").toLowerCase();
        if (systemName.contains("windows")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\bin\\chromedriver.exe");
        } else if (systemName.contains("linux")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/bin/chromedriver_linux");
        } else {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/bin/chromedriver_osx");
        }

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com");
        String title = driver.getTitle();
        WebElement usernameInput = driver.findElement(By.id("txtUsername"));
        usernameInput.sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
        //driver.close();
    }

}
