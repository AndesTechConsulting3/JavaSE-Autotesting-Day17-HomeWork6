package org.andestech.learning.rfb19.g3;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class AppTest3
{
    private WebDriver wd = null;
    private InternetExplorerOptions options = null;

    @BeforeClass
    public void initData(){

    System.setProperty("webdriver.ie.driver",
            "C:\\SeleniumDrivers\\IE\\IEDriverServer_x64_3.14.0\\IEDriverServer.exe");
        System.out.println("+++ Class: " + this);

     options = new InternetExplorerOptions();
     options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
     options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);

    }

    @Test
    public void testCaseIE01() //проверяем, что сайт andestech.org доступен
    {
        wd = new InternetExplorerDriver(options);
        wd.get("http://andestech.org/learning/rfb18");
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wd.findElement(By.xpath("//*[@id='mainTitle']"));
        Assert.assertTrue(true);
    }

    @Test
    public void testCaseIE02() //создаем пользователя
    {
        //wd = new InternetExplorerDriver(options);
        //wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wd.get("http://andestech.org/learning/rfb18");
        wd.findElement(By.linkText("New customer")).click();
        wd.findElement(By.name("reset")).click();
        wd.findElement(By.cssSelector("input#name")).sendKeys("Гендальф");
        wd.findElement(By.cssSelector("input#sname")).sendKeys("Серый");
        wd.findElement(By.cssSelector("input#login")).sendKeys("GendalfG");
        wd.findElement(By.cssSelector("input#pass")).sendKeys("G!Gray98");
        wd.findElement(By.name("submit")).submit();
        wd.switchTo().alert().accept();
        assertTrue( true );
    }

    @Test
    public void testCaseIE03() throws InterruptedException //проверяем, что пользователь успешно залогинился на сайте
    {
        //wd = new InternetExplorerDriver(options);
        //wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wd.get("http://andestech.org/learning/rfb18");
        wd.findElement(By.linkText("Login")).click();
        wd.findElement(By.name("reset")).click();
        wd.findElement(By.cssSelector("input#login")).sendKeys("GendalfG");
        wd.findElement(By.cssSelector("input#pass")).sendKeys("G!Gray98");
        wd.findElement(By.name("submit")).submit();
        Thread.sleep(5000);
        //System.out.println(wd.getTitle());
        //wd.getTitle().compareTo(" Вы зашли как  GendalfG.");
        Assert.assertTrue(wd.getTitle().contentEquals("Моя страница"));
    }

    @Test
    public void testCaseIE04() throws InterruptedException //проверяем, что пользователь успешно залогинился на сайте
    {
        //wd = new InternetExplorerDriver(options);
        //wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //wd.get("http://andestech.org/learning/rfb18/login.html");
        //wd.findElement(By.linkText("Login")).click();
//        wd.findElement(By.name("reset")).click();
//        wd.findElement(By.cssSelector("input#login")).sendKeys("GendalfG");
//        wd.findElement(By.cssSelector("input#pass")).sendKeys("G!Gray98");
//        wd.findElement(By.name("submit")).submit();
//        Thread.sleep(5000);
        wd.findElement(By.linkText("Logout")).click();
        //Thread.sleep(5000);
        //System.out.println(wd.switchTo().alert().getText());
        Assert.assertTrue(wd.switchTo().alert().getText().contentEquals("Вы успешно вышли из системы."));
        wd.switchTo().alert().accept();
    }

    @Test(priority = 99)
    public void testCaseIE05() throws InterruptedException //проверяем, что пользователь успешно залогинился на сайте
    {
        //wd = new InternetExplorerDriver(options);
        //wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //wd.get("http://andestech.org/learning/rfb18");
        wd.findElement(By.linkText("Login")).click();
        wd.findElement(By.name("reset")).click();
        wd.findElement(By.cssSelector("input#login")).sendKeys("GendalfG");
        wd.findElement(By.cssSelector("input#pass")).sendKeys("G!Gray98QQQ");
        wd.findElement(By.name("submit")).submit();
        Thread.sleep(5000);
        //System.out.println(wd.switchTo().alert().getText());
        //System.out.println(wd.getTitle());
        Assert.assertTrue(wd.switchTo().alert().getText().contentEquals("Неверный логин или пароль!!"));
    }

    @AfterClass
    public void tearDown()
    {
        if(wd != null) wd.quit();
        System.out.println("--- Class: " + this);
    }

}
