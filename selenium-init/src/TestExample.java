import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestExample {
    static WebDriver driver;

    @Before
    public void prepareEnvironment() {
        String systemName = System.getProperty("os.name").toLowerCase();
        if (systemName.contains("windows")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\bin\\chromedriver.exe");
        } else if (systemName.contains("linux")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/bin/chromedriver_linux");
        } else {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/bin/chromedriver_osx");
        }

        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com");
        String pagetTitle = driver.getTitle();
        System.out.println(pagetTitle);
        driver.manage().window().maximize();
    }

    @Test
    public void tryToLoginWithImproperCredentialsTest() {
        // Test  case 1 - improper credentials
        WebElement usernameInput = driver.findElement(By.id("txtUsername"));
        usernameInput.sendKeys("Admin");
        WebElement passwordInput = driver.findElement(By.name("txtPassword"));
        passwordInput.sendKeys("admin123455");

        driver.findElement(By.id("btnLogin")).click();

        WebElement errorMessageSpan = driver.findElement(By.id("spanMessage"));
        String errorMessage = errorMessageSpan.getText();

        Assert.assertEquals("Invalid credentials", errorMessage);
    }

    @Test
    public void loginWithoutPasswordTest() {
        // TC 2 - Missing password
        WebElement usernameInput = driver.findElement(By.id("txtUsername"));
        usernameInput.sendKeys("Admin");
        WebElement passwordInput = driver.findElement(By.name("txtPassword"));
        passwordInput.sendKeys("");

        driver.findElement(By.id("btnLogin")).click();

        WebElement errorMessageSpan = driver.findElement(By.id("spanMessage"));
        String errorMessage = errorMessageSpan.getText();

        Assert.assertEquals("Password cannot be empty", errorMessage);
    }

       /* // TC 3 - Missing username
        usernameInput = driver.findElement(By.id("txtUsername"));
        usernameInput.clear();
        passwordInput = driver.findElement(By.name("txtPassword"));
        passwordInput.sendKeys("dasdsa");


        driver.findElement(By.id("btnLogin")).click();

        errorMessageSpan = driver.findElement(By.id("spanMessage"));
        errorMessage = errorMessageSpan.getText();

        Assert.assertEquals("Username cannot be empty", errorMessage);

        // TC 3 - Login as admin
        usernameInput.clear();
        passwordInput.clear();
        usernameInput = driver.findElement(By.id("txtUsername"));
        usernameInput.sendKeys("Admin");
        passwordInput = driver.findElement(By.name("txtPassword"));
        passwordInput.sendKeys("admin123");


        driver.findElement(By.id("btnLogin")).click();

        WebElement welcomeLink = driver.findElement(By.id("welcome"));
        Assert.assertTrue(welcomeLink.isDisplayed());
        String welcomeText = welcomeLink.getText();


        Assert.assertTrue(welcomeText.contains("Admin"));
        driver.findElement(By.id("menu_admin_viewAdminModule")).click();
        driver.findElement(By.id("searchSystemUser_userName")).sendKeys("Admin");
        driver.findElement(By.id("searchBtn")).click();

        WebElement searchResult = driver.findElement(By.xpath("//table[@id='resultTable']//td/a[text()='Admin']"));
        //$x("szukany_xpath") - wyszukiwanie xpathow w consoli developer tools


        Assert.assertTrue(searchResult.isDisplayed()); */
        //driver.close();
    @After
    public void closeingdriver() {
        driver.close();
    }

    }
