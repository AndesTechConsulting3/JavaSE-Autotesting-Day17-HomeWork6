package org.andestech.learning.rfb19.g3;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AppTest
{
    private WebDriver wd = null;

    @BeforeClass
    public void initData(){
    System.setProperty("webdriver.chrome.driver",
            "C:\\WebDrivers\\chromedriver.exe");
    System.out.println("+++ Class: " + this);

    }

    @Test()
    public void testCheckUrl() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        //FirefoxOptions firefoxOptions = new FirefoxOptions();
        chromeOptions.addArguments("--start-maximized");

        wd = new ChromeDriver(chromeOptions);
        wd.get("http://andestech.org/learning/rfb18/");
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        assertEquals(wd.getCurrentUrl(), "http://andestech.org/learning/rfb18/");
    }

    @Test(dependsOnMethods = "testCheckUrl")
    public void registrationTest() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        //FirefoxOptions firefoxOptions = new FirefoxOptions();
        chromeOptions.addArguments("--start-maximized");

        wd = new ChromeDriver(chromeOptions);
        wd.get("http://andestech.org/learning/rfb18/");
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//login button
        WebElement element1 = wd.findElement(By.cssSelector("a:nth-child(2)"));
        element1.click();
//insert logopass
        WebElement element2 = wd.findElement(By.id("name"));
        element2.sendKeys("Александр");

        WebElement element3 = wd.findElement(By.id("sname"));
        element3.sendKeys("Солнышко" + new Random().nextInt(1000));

        WebElement element4 = wd.findElement(By.id("login"));
        element4.sendKeys("WildBanana" + new Random().nextInt(1000));

        WebElement element5 = wd.findElement(By.name("pass"));
        element5.sendKeys("NewPass1");

        WebElement element6 = wd.findElement(By.name("submit"));
        element6.click();
//login button
        //WebElement element4 = wd.findElement(By.name("submit")); -- y


    }

    @Test(dependsOnMethods = "registrationTest")
    public void testLogin() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        //FirefoxOptions firefoxOptions = new FirefoxOptions();
        chromeOptions.addArguments("--start-maximized");

        wd = new ChromeDriver(chromeOptions);
        wd.get("http://andestech.org/learning/rfb18/");
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//login button
        WebElement element1 = wd.findElement(By.cssSelector("a:nth-child(3)"));
        element1.click();
//insert logopass
        WebElement element2 = wd.findElement(By.name("login"));
        element2.sendKeys("WildBanana");

        WebElement element3 = wd.findElement(By.name("password"));
        element3.sendKeys("NewPass1");
//login button
        //WebElement element4 = wd.findElement(By.name("submit")); -- y

        WebElement element4 = wd.findElement(By.cssSelector("#lgn"));
        element4.click();

        assertEquals(wd.getCurrentUrl(), "http://andestech.org/learning/rfb18/home.html");
    }

    @Test(dependsOnMethods = "testLogin")
    public void testLogout() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        //FirefoxOptions firefoxOptions = new FirefoxOptions();
        chromeOptions.addArguments("--start-maximized");

        wd = new ChromeDriver(chromeOptions);
        wd.get("http://andestech.org/learning/rfb18/");
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//login button
        WebElement element1 = wd.findElement(By.cssSelector("a:nth-child(3)"));
        element1.click();
//insert logopass
        WebElement element2 = wd.findElement(By.name("login"));
        element2.sendKeys("WildBanana");

        WebElement element3 = wd.findElement(By.name("password"));
        element3.sendKeys("NewPass1");
//login button
        //WebElement element4 = wd.findElement(By.name("submit")); -- y

        WebElement element4 = wd.findElement(By.cssSelector("#lgn"));
        element4.click();

        assertEquals(wd.getCurrentUrl(), "http://andestech.org/learning/rfb18/home.html");

        WebElement element5 = wd.findElement(By.cssSelector("a:nth-child(5)"));
        element5.click();

    }

    @Test(dependsOnMethods = "testCheckUrl")
    public void testUncorrectLogin() throws InterruptedException {

        ChromeOptions chromeOptions = new ChromeOptions();
        //FirefoxOptions firefoxOptions = new FirefoxOptions();
        chromeOptions.addArguments("--start-maximized");

        wd = new ChromeDriver(chromeOptions);
        wd.get("http://andestech.org/learning/rfb18/");
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//login button
        WebElement element1 = wd.findElement(By.cssSelector("a:nth-child(3)"));
        element1.click();
//insert logopass
        WebElement element2 = wd.findElement(By.name("login"));
        element2.sendKeys("WildBanana");

        WebElement element3 = wd.findElement(By.name("password"));
        element3.sendKeys("NewPass1");
//login button
        //WebElement element4 = wd.findElement(By.name("submit")); -- y

        WebElement element4 = wd.findElement(By.cssSelector("#lgn"));
        element4.click();

        assertEquals(wd.getCurrentUrl(), "http://andestech.org/learning/rfb18/home.html");
    }


    @AfterClass
    public void tearDown()
    {
      if(wd != null) wd.quit();
      System.out.println("--- Class: " + this);
    }

}
