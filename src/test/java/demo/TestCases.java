package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;
        Wrappers wrapper; 

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

    

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        
    }

    @Test
    public void testCase01() throws InterruptedException {
        System.out.println("Start Test Case : testCase01");
        wrapper = new Wrappers(driver);
        //open URL
        wrapper.openUrl("https://www.flipkart.com/");
        Thread.sleep(1000);
        // locate search bar and send the values
        wrapper.enterText(By.xpath("//input[@name='q']"), "Washing Machine"+ Keys.ENTER);
        Thread.sleep(1000);
        // select the popularity title
        wrapper.selectPopularity(By.xpath("//div[@class='zg-M3Z']"));
        Thread.sleep(1000);
        // get rating count
        wrapper.rating(By.xpath("//div[@class='XQDdHH']"));
        System.out.println("End Test Case : testCase01");
    }

    @Test
    public void testCase02() throws InterruptedException {
        System.out.println("Start Test Case : testCase02");
        wrapper = new Wrappers(driver);
        Thread.sleep(1000);
        // locate search bar and send the values
        wrapper.enterText(By.xpath("//input[@name='q']"), "iPhone" + Keys.ENTER);
        Thread.sleep(1000);
        // call the method to print the discount percentage
        wrapper.discount(By.xpath("//div[@class='yKfJKb row']"));
        System.out.println("End Test Case: testCase02");
    }

    @Test
    public void testCase03() throws InterruptedException {
        System.out.println("Start Test Case: testCase03");
        wrapper = new Wrappers(driver);
        // locate search bar and send the values
        wrapper.enterText(By.xpath("//input[@name='q']"), "Coffee Mug" + Keys.ENTER);
        Thread.sleep(1000);
        wrapper.clickRating(By.xpath("//div[@class='XqNaEv']/following-sibling::div"));
        Thread.sleep(5000);
        //call the method to print the title and image url of the product
        wrapper.titleAndImageUrl(By.xpath("//div[contains(@class,'slAVV4')]"));
    }
    

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}