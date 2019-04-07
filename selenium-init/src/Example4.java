import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Example4 {


    public static void main(String[] args) {
        String systemName = System.getProperty("os.name").toLowerCase();
        if (systemName.contains("windows")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\bin\\chromedriver.exe");
        } else if (systemName.contains("linux")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/bin/chromedriver_linux");
        } else {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/bin/chromedriver_osx");
        }

        //Arrange
        //Open browser and navigate to test page
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com");
        System.out.println("Page opened");


        //Log in to application
        WebElement usernameInput = driver.findElement(By.id("txtUsername"));
        usernameInput.sendKeys("Admin");
        WebElement passwordInput = driver.findElement(By.id("txtPassword"));
        passwordInput.sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();

        //Assert dashboard page is open
        String headerText = driver.findElement(By.cssSelector("#content .head h1")).getText();
        Assertions.assertEquals("Dashboard", headerText);
        System.out.println("User logged in");


        //Act
        //Logout
        WebElement welcomeText = driver.findElement(By.id("welcome"));
        welcomeText.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement logoutLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Logout")));
        logoutLink.click();

        //Assert
        //Check login form is displayed
        usernameInput = driver.findElement(By.id("txtUsername"));
        Assert.assertTrue(usernameInput.isDisplayed());

        //Cleanup
        driver.close();
    }

}
