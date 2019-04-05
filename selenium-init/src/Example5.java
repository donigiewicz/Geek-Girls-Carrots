import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Example5 {

    static WebDriver driver;

    @BeforeClass
    public static void openPageAndLogin(){

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

        @Before
        public void openPage() {

            driver.get("https://opensource-demo.orangehrmlive.com");

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


        @AfterClass
        public static void closeBrowser() {
            //Cleanup
            driver.close();
        }
}
