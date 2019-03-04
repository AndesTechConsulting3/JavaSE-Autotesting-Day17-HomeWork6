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
            "C:\\chromedriver_win32\\chromedriver.exe");
    System.out.println("+++ Class: " + this);

    }

    @Test
    public void testCaseChrome01()
    {
        wd = new ChromeDriver();
        wd.get("http://andestech.org/learning/rfb18");
        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wd.findElement(By.id("mainTitle"));
        assertTrue( true );
    }

    @Test(dependsOnMethods = "testCaseChrome01")
    public void testCaseChrome02() throws InterruptedException //создаем пользователя
    {

        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wd.findElement(By.linkText("New customer")).click();
        wd.findElement(By.name("reset")).click();
        wd.findElement(By.cssSelector("input#name")).sendKeys("Привет");
        wd.findElement(By.cssSelector("input#sname")).sendKeys("Медвед");
        wd.findElement(By.cssSelector("input#login")).sendKeys("Medved123");
        wd.findElement(By.cssSelector("input#pass")).sendKeys("Medved_pass123");
        wd.findElement(By.name("submit")).submit();
        Thread.sleep(7000);
        assertTrue( wd.switchTo().alert().getText().contentEquals("User  Medved123 created ok!"));
        wd.switchTo().alert().accept();
    }

    @Test(dependsOnMethods = "testCaseChrome02")
    public void testCaseChrome03() throws InterruptedException
    {
        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wd.findElement(By.linkText("Login")).click();
        wd.findElement(By.name("reset")).click();
        wd.findElement(By.cssSelector("input#login")).sendKeys("Medved123");
        wd.findElement(By.cssSelector("input#pass")).sendKeys("Medved_pass123");
        wd.findElement(By.name("submit")).submit();
        Thread.sleep(7000);
        Assert.assertTrue(wd.getTitle().contentEquals("Моя страница"));
    }

    @Test(dependsOnMethods = "testCaseChrome03")
    public void testCaseChrome04() throws InterruptedException
    {

        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wd.findElement(By.linkText("Logout")).click();
        Assert.assertTrue(wd.switchTo().alert().getText().contentEquals("Вы успешно вышли из системы."));
        wd.switchTo().alert().accept();
    }

    @Test(dependsOnMethods = "testCaseChrome01")
    public void testCaseChrome05() throws InterruptedException
    {
        wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wd.findElement(By.linkText("Login")).click();
        wd.findElement(By.name("reset")).click();
        wd.findElement(By.cssSelector("input#login")).sendKeys("Medved_123");
        wd.findElement(By.cssSelector("input#pass")).sendKeys("Medved_pass1234");
        wd.findElement(By.name("submit")).submit();
        Thread.sleep(7000);
        Assert.assertTrue(wd.switchTo().alert().getText().contentEquals("Неверный логин или пароль!!"));
    }


    @AfterClass
    public void tearDown()
    {
      if(wd != null) wd.quit();
      System.out.println("--- Class: " + this);
    }

}
