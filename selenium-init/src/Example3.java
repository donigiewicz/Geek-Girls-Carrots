import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class Example3 {


    public static void main(String[] args) {
        String systemName = System.getProperty("os.name").toLowerCase();
        if (systemName.contains("windows")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\bin\\chromedriver.exe");
        } else if (systemName.contains("linux")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/bin/chromedriver_linux");
        } else {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/bin/chromedriver_osx");
        }

        String employeeFirstName = "Tom";
        String employeeLastName = "Johns";
        String employeeUsername = "userXxx";
        String employeePassword = "haslo123!";

        //Arrange
        //Open browser and navigate to test page
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://opensource-demo.orangehrmlive.com");
        System.out.println("Page opened");

        //Log in to application
        WebElement usernameInput = driver.findElement(By.id("txtUsername"));
        usernameInput.sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();

        //Assert dashboard page is open
        String headerText = driver.findElement(By.cssSelector("#content .head h1")).getText();
        Assertions.assertEquals("Dashboard", headerText);
        System.out.println("User logged in");

        //Open
        WebElement pimMenuItem = driver.findElement(By.id("menu_pim_viewPimModule"));
        pimMenuItem.click();
        System.out.println("PIM module opened");


        //Act
        //Add new employee
        //Enter data
        WebElement employeeMenuItem = driver.findElement(By.id("menu_pim_addEmployee"));
        pimMenuItem.click();
        WebElement firstNameInput = driver.findElement(By.id("firstName"));
        firstNameInput.sendKeys(employeeFirstName);
        WebElement lastNameInput = driver.findElement(By.id("lastName"));
        lastNameInput.sendKeys(employeeLastName);
        WebElement createLoginDetailsCheckbox = driver.findElement(By.id("chkLogin"));
        createLoginDetailsCheckbox.click();
        WebElement userNameInput = driver.findElement(By.name("user_name"));
        userNameInput.sendKeys(employeeUsername);
        WebElement userPasswordInput = driver.findElement(By.name("user_password"));
        userPasswordInput.sendKeys(employeePassword);
        WebElement repasswordInput = driver.findElement(By.name("re_password"));
        repasswordInput.sendKeys(employeePassword);
        Select statusSelect = new Select(driver.findElement(By.id("status")));
        statusSelect.selectByValue("Disabled");
        System.out.println("User data entered");
        //Save data
        WebElement saveButton = driver.findElement(By.id("btnSave"));
        saveButton.click();

        //Assert
        //Check first and last name
        String personalFirstName = driver.findElement(By.id("personal_txtEmpFirstName")).getAttribute("value");
        String personalLastName = driver.findElement(By.id("personal_txtEmpLastName")).getAttribute("value");
        Assert.assertEquals(employeeFirstName, personalFirstName);
        Assert.assertEquals(employeeLastName, personalLastName);

        //Cleanup
        driver.close();
    }

}
