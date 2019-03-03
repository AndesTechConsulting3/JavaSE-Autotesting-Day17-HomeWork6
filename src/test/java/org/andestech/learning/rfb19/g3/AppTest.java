package org.andestech.learning.rfb19.g3;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class AppTest
{
    private WebDriver wd = null;

    @BeforeClass
    public void initData(){
    System.setProperty("webdriver.chrome.driver",
            "C:\\SeleniumDrivers\\Chrome\\chromedriver_win32\\chromedriver.exe");
    System.out.println("+++ Class: " + this);
    }

    @Test
    public void testCaseChrome01() //проверяем, что сайт andestech.org доступен
    {
        wd = new ChromeDriver();
        wd.get("http://andestech.org/learning/rfb18");
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wd.findElement(By.xpath("//*[@id='mainTitle']"));
        Assert.assertTrue(true);
    }

    @Test(dependsOnMethods = "testCaseChrome01")
    public void testCaseChrome02() throws InterruptedException //создаем пользователя
    {
        //wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //wd.get("http://andestech.org/learning/rfb18");
        wd.findElement(By.linkText("New customer")).click();
        wd.findElement(By.name("reset")).click();
        wd.findElement(By.cssSelector("input#name")).sendKeys("Гендальф");
        wd.findElement(By.cssSelector("input#sname")).sendKeys("Серый");
        wd.findElement(By.cssSelector("input#login")).sendKeys("GendalfG");
        wd.findElement(By.cssSelector("input#pass")).sendKeys("G!Gray98");
        wd.findElement(By.name("submit")).submit();
        Thread.sleep(5000);
        assertTrue( wd.switchTo().alert().getText().contentEquals("User  GendalfG created ok!"));
        wd.switchTo().alert().accept();
    }

    @Test(dependsOnMethods = "testCaseChrome02")
    public void testCaseChrome03() throws InterruptedException //проверяем, что пользователь успешно залогинился на сайте
    {
        //wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //wd.get("http://andestech.org/learning/rfb18");
        wd.findElement(By.linkText("Login")).click();
        wd.findElement(By.name("reset")).click();
        wd.findElement(By.cssSelector("input#login")).sendKeys("GendalfG");
        wd.findElement(By.cssSelector("input#pass")).sendKeys("G!Gray98");
        wd.findElement(By.name("submit")).submit();
        Thread.sleep(5000);
        //System.out.println(wd.getTitle());
        Assert.assertTrue(wd.getTitle().contentEquals("Моя страница"));
    }

    @Test(dependsOnMethods = "testCaseChrome03")
    public void testCaseChrome04() throws InterruptedException //проверяем, что пользователь успешно раззалогинился на сайте
    {
        //wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //wd.get("http://andestech.org/learning/rfb18/login.html");
        //wd.findElement(By.linkText("Login")).click();
//        wd.findElement(By.name("reset")).click();
//        wd.findElement(By.cssSelector("input#login")).sendKeys("GendalfG");
//        wd.findElement(By.cssSelector("input#pass")).sendKeys("G!Gray98");
//        wd.findElement(By.name("submit")).submit();
//        Thread.sleep(5000);
        wd.findElement(By.linkText("Logout")).click();
        //System.out.println(wd.switchTo().alert().getText());
        Assert.assertTrue(wd.switchTo().alert().getText().contentEquals("Вы успешно вышли из системы."));
        wd.switchTo().alert().accept();
    }

    @Test(dependsOnMethods = "testCaseChrome01", priority = 99)
    public void testCaseChrome05() throws InterruptedException //проверяем, что пользователь НЕ залогинился на сайте
    {
        //wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //wd.get("http://andestech.org/learning/rfb18");
        wd.findElement(By.linkText("Login")).click();
        wd.findElement(By.name("reset")).click();
        wd.findElement(By.cssSelector("input#login")).sendKeys("GendalfG");
        wd.findElement(By.cssSelector("input#pass")).sendKeys("G!Gray98QQQ");
        wd.findElement(By.name("submit")).submit();
        Thread.sleep(5000);
        //System.out.println(wd.getTitle());
        //System.out.println(wd.switchTo().alert().getText());
        Assert.assertTrue(wd.switchTo().alert().getText().contentEquals("Неверный логин или пароль!!"));
    }

//    @AfterTest
//    public void tearDownTest()
//    {
//        if(wd != null) wd.quit();
//        System.out.println("-- Test: " + this);
//    }

    @AfterClass
    public void tearDown()
    {
      if(wd != null) wd.quit();
      System.out.println("--- Class: " + this);
    }

}
