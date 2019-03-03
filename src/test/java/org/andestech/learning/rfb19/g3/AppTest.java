package org.andestech.learning.rfb19.g3;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
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
            "lib/chromedriver.exe");
    System.out.println("+++ Class: " + this);

    }

    @Test()
    public void testCaseChrome01()
    {
        wd = new ChromeDriver();
        wd.get("http://andestech.org/learning/rfb18/index.html");
        wd.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        wd.findElement(By.linkText("New customer")).isEnabled();
    }

    @Test(dependsOnMethods = "testCaseChrome01")
    public void testCaseChrome02()
    {
        wd = new ChromeDriver();
        wd.get("http://andestech.org/learning/rfb18/index.html");
        wd.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        wd.findElement(By.linkText("New customer")).isEnabled();
        wd.findElement(By.linkText("New customer")).click();
        wd.findElement(By.name("reset")).click();
        wd.findElement(By.cssSelector("input#name")).sendKeys("Name");
        wd.findElement(By.cssSelector("input#sname")).sendKeys("SName");
        wd.findElement(By.cssSelector("input#login")).sendKeys("TestLogin");
        wd.findElement(By.cssSelector("input#pass")).sendKeys("Test12!!!");
        wd.findElement(By.name("submit")).submit();
        wd.switchTo().alert().accept();
    }

    @Test(dependsOnMethods = "testCaseChrome02")
    public void testCaseChrome03()
    {
        wd = new ChromeDriver();
        wd.get("http://andestech.org/learning/rfb18/index.html");
        wd.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        wd.findElement(By.linkText("New customer")).isEnabled();
        wd.findElement(By.linkText("New customer")).click();
        wd.findElement(By.name("reset")).click();
        wd.findElement(By.cssSelector("input#name")).sendKeys("Тест");
        wd.findElement(By.cssSelector("input#sname")).sendKeys("CТест");
        wd.findElement(By.cssSelector("input#login")).sendKeys("TestLogin");
        wd.findElement(By.cssSelector("input#pass")).sendKeys("Test12!!!");
        wd.findElement(By.name("submit")).submit();
        wd.switchTo().alert().accept();
        wd.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        wd.findElement(By.linkText("Login")).click();
        wd.findElement(By.name("reset")).click();
        wd.findElement(By.cssSelector("input#login")).sendKeys("TestLogin");
        wd.findElement(By.cssSelector("input#pass")).sendKeys("Test12!!!");
        wd.findElement(By.name("submit")).submit();
    }

    @Test(dependsOnMethods = "testCaseChrome03")
    public void testCaseChrome04()
{
    wd = new ChromeDriver();
    wd.get("http://andestech.org/learning/rfb18/index.html");
    wd.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    wd.findElement(By.linkText("New customer")).isEnabled();
    wd.findElement(By.linkText("New customer")).click();
    wd.findElement(By.name("reset")).click();
    wd.findElement(By.cssSelector("input#name")).sendKeys("Тест");
    wd.findElement(By.cssSelector("input#sname")).sendKeys("CТест");
    wd.findElement(By.cssSelector("input#login")).sendKeys("TestLogin");
    wd.findElement(By.cssSelector("input#pass")).sendKeys("Test12!!!");
    wd.findElement(By.name("submit")).submit();
    wd.switchTo().alert().accept();
    wd.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    wd.findElement(By.linkText("Login")).click();
    wd.findElement(By.name("reset")).click();
    wd.findElement(By.cssSelector("input#login")).sendKeys("TestLogin");
    wd.findElement(By.cssSelector("input#pass")).sendKeys("Test12!!!");
    wd.findElement(By.name("submit")).submit();
    wd.getTitle().compareTo("Вы зашли как TestLogin.");
    wd.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    wd.findElement(By.cssSelector("#sub > footer > nav > div > a:nth-child(5)")).click();
    wd.switchTo().alert().accept();
}

    @Test(dependsOnMethods = "testCaseChrome01")
    public void testCaseChrome05()
    {
        wd = new ChromeDriver();
        wd.get("http://andestech.org/learning/rfb18/index.html");
        wd.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        wd.findElement(By.linkText("Login")).click();
        wd.findElement(By.name("reset")).click();
        wd.findElement(By.cssSelector("input#login")).sendKeys("TestLogin");
        wd.findElement(By.cssSelector("input#pass")).sendKeys("Test12!!!");
        wd.findElement(By.name("submit")).submit();
        wd.findElement(By.linkText("Login")).isEnabled();
    }

    @AfterClass
    public void tearDown()
    {
      if(wd != null) wd.quit();
      System.out.println("--- Class: " + this);
    }

}
