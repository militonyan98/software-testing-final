package base;

import com.google.common.io.Files;
import org.junit.Before;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import utils.EventListener;
import utils.WindowManager;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;

import static org.testng.Assert.assertTrue;


public class BaseTests {

    private EventFiringWebDriver driver;
    private final String HOME_PAGE = "https://sephora.com";
    protected HomePage homePage;
    protected WindowManager windowManager;
    protected boolean closeAfterFinishing = true;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions()));
        driver.register(new EventListener());
        windowManager = new WindowManager(driver);
        goHome();
        //setCookie();
    }

    @BeforeMethod
    public void goHome(){
        driver.get(HOME_PAGE);
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void tearDown(){
        if(closeAfterFinishing)
            driver.quit();
    }

    @AfterMethod
    public void recordFailure(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus())
        {
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try{
                Files.move(screenshot, new File("screenshots/" + result.getName() + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }



    private ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        //options.setHeadless(true);
        return options;
    }

    private void setCookie(){
        Cookie cookie = new Cookie.Builder("tau", "123")
                .domain("the-internet.herokuapp.com")
                .build();
        driver.manage().addCookie(cookie);
    }


    public void assertMore(int value, int compareTo, @Nullable String message){
        assertTrue(value>compareTo, message);
    }

    public void assertMoreOrEquals(int value, int compareTo, @Nullable String message){
        assertTrue(value>=compareTo, message);
    }

    public void assertLess(int value, int compareTo,@Nullable String message){
        assertTrue(value<compareTo, message);
    }

    public void assertLessOrEquals(int value, int compareTo, @Nullable String message){
        assertTrue(value<=compareTo, message);
    }
}