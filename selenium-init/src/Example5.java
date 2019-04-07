import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class Example5 {

    static WebDriver driver;

    @Before
    public void openPageAndTryToLoginWithImproperCredentials(){

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
        driver.get("https://opensource-demo.orangehrmlive.com");
        System.out.println("Page opened");

        }

        @Test
        public void actionsTest() {

            WebElement usernameInput = driver.findElement(By.id("txtUsername"));
            usernameInput.sendKeys("Admin");
            WebElement passwordInput = driver.findElement(By.id("txtPassword"));
            passwordInput.sendKeys("admin123");
            driver.findElement(By.id("btnLogin")).click();

            WebElement pimButton = driver.findElement(By.id("menu_pim_viewPimModule"));
            WebElement addEmployeeButton = driver.findElement(By.id("menu_pim_addEmployee"));

            Actions navigateAction = new Actions(driver);
            navigateAction.moveToElement(pimButton)
                    .moveToElement(addEmployeeButton)
                    .click()
                    .perform();

        }

        @Test
        public void shouldNotLoginWithUserNameOnly(){
            WebElement usernameInput = driver.findElement(By.id("txtUsername"));
            usernameInput.sendKeys("Admin");
            WebElement passwordInput = driver.findElement(By.id("txtPassword"));
            passwordInput.sendKeys("");
            driver.findElement(By.id("btnLogin")).click();
            WebElement warningMessage = driver.findElement(By.id("spanMessage"));

            Assert.assertEquals("Password cannot be empty", warningMessage.getText());
        }

        @Test
        public void shouldNotLoginWithImproperPassword() {
            WebElement usernameInput = driver.findElement(By.id("txtUsername"));
            usernameInput.sendKeys("Admin");
            WebElement passwordInput = driver.findElement(By.id("txtPassword"));
            passwordInput.sendKeys("a");
            driver.findElement(By.id("btnLogin")).click();
            WebElement warningMessage = driver.findElement(By.id("spanMessage"));

            Assert.assertEquals("Invalid credentials", warningMessage.getText());
        }

        @After
        public void clearCookiesAndRefreshPage(){
            //driver.close();
        }
}
