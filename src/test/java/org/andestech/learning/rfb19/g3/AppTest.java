package org.andestech.learning.rfb19.g3;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AppTest
{
    private WebDriver wd = null;

    @BeforeClass
    public void initData(){
    System.setProperty("webdriver.chrome.driver",
            "drivers/chromedriver");
    System.out.println("+++ Class: " + this);

    }

    @Test
    public void openMainPageTest()
    {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wd.get("http://andestech.org/learning/rfb18/");

        WebElement $mainPageTitle = wd.findElement(By.cssSelector("#mainTitle"));

        Assert.assertTrue($mainPageTitle.isDisplayed());
    }

    @Test(dependsOnMethods = "openMainPageTest")
    public void successfullRegistrationTest() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wd.get("http://andestech.org/learning/rfb18/");

        WebElement $newCustomerLink = wd.findElement(By.linkText("New customer"));
        $newCustomerLink.click();

        WebElement $titleAddCustomerPage = wd.findElement(By.cssSelector("h2"));
        Assert.assertTrue($titleAddCustomerPage.isDisplayed());

        WebElement $name = wd.findElement(By.id("name"));
        WebElement $sname = wd.findElement(By.id("sname"));
        WebElement $login = wd.findElement(By.id("login"));
        WebElement $pass = wd.findElement(By.id("pass"));
        WebElement $add = wd.findElement(By.id("submit"));

        $name.sendKeys("Алексей");
        $sname.sendKeys("Владимиров");
        $login.sendKeys("avladimirov1");
        $pass.sendKeys("Qq12345!");
        $add.click();

        String alertText = wd.switchTo().alert().getText();


        Assert.assertEquals("User  avladimirov1 created ok!", alertText);
        wd.switchTo().alert().accept();
    }

    @Test(dependsOnMethods = "successfullRegistrationTest")
    public void loginTest() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wd.get("http://andestech.org/learning/rfb18/");

        WebElement $loginLink = wd.findElement(By.linkText("Login"));

        $loginLink.click();

        WebElement $loginField = wd.findElement(By.id("login"));
        WebElement $passLogin = wd.findElement(By.id("pass"));
        WebElement $loginButton = wd.findElement(By.id("lgn"));

        Assert.assertTrue($loginField.isDisplayed());

        $loginField.sendKeys("avladimirov1");
        $passLogin.sendKeys("Qq12345!");
        $loginButton.click();

        WebElement $titlePersonalPage = wd.findElement(By.cssSelector("h1"));

        Assert.assertTrue($titlePersonalPage.isDisplayed());
    }

    @Test(dependsOnMethods = "loginTest")
    public void logoutTest()
    {
        //Логинимся
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wd.get("http://andestech.org/learning/rfb18/");

        WebElement $loginLink = wd.findElement(By.linkText("Login"));

        $loginLink.click();

        WebElement $loginField = wd.findElement(By.id("login"));
        WebElement $passLogin = wd.findElement(By.id("pass"));
        WebElement $loginButton = wd.findElement(By.id("submit"));

        $loginField.sendKeys("avladimirov1");
        $passLogin.sendKeys("Qq12345!");
        $loginButton.click();

        WebElement $titlePersonalPage = wd.findElement(By.cssSelector("h1"));
        Assert.assertTrue($titlePersonalPage.isDisplayed());

        WebElement $logoutButton = wd.findElement(By.linkText("Logout"));

        $logoutButton.click();

        WebElement $mainPageTitle = wd.findElement(By.cssSelector("#mainTitle"));

        Assert.assertTrue($mainPageTitle.isDisplayed());
    }

    @Test(dependsOnMethods = "openMainPageTest")
    public void unsuccessfullLoginTest()
    {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wd.get("http://andestech.org/learning/rfb18/");

        WebElement $loginLink = wd.findElement(By.linkText("Login"));

        $loginLink.click();

        WebElement $loginField = wd.findElement(By.id("login"));
        WebElement $passLogin = wd.findElement(By.id("pass"));
        WebElement $loginButton = wd.findElement(By.id("lgn"));

        Assert.assertTrue($loginField.isDisplayed());

        $loginField.sendKeys("avladimirov1");
        $passLogin.sendKeys("Qq12345!1");
        $loginButton.click();

        Assert.assertTrue($loginField.isDisplayed());

        String loginTextAfterClick = $loginField.getText();

        Assert.assertEquals("", loginTextAfterClick);
    }

    @AfterClass
    public void tearDown()
    {
      if(wd != null) wd.quit();
      System.out.println("--- Class: " + this);
    }

}
