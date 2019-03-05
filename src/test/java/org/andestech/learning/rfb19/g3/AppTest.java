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

import static org.testng.Assert.assertTrue;

public class AppTest
{
    private WebDriver wd = null;

    @BeforeClass
    public void initData(){
    System.setProperty("webdriver.chrome.driver",
            "D:\\drivers\\chromedriver.exe");
    System.out.println("+++ Class: " + this);

    }

    @Test
    public void siteTest00() throws InterruptedException {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wd.get("http://andestech.org/learning/rfb18/");
        WebElement home = wd.findElement(By.linkText("Home"));
        System.out.println("Считано с сайта: " + home.getText());
        assertTrue(true);
        Thread.sleep(1000);
        tearDown();
    }

    @Test (dependsOnMethods = "siteTest00")
    public void siteTest01() throws InterruptedException {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wd.get("http://andestech.org/learning/rfb18/index.html");
        WebElement newCustomer = wd.findElement(By.cssSelector("a:nth-child(2)"));
        newCustomer.click();
        wd.findElement(By.cssSelector("input#name")).sendKeys("Анна");
        wd.findElement(By.cssSelector("input#sname")).sendKeys("Каренина");
        wd.findElement(By.cssSelector("input#login")).sendKeys("Train56");
        wd.findElement(By.cssSelector("input#pass")).sendKeys("Pass02train1");
        wd.findElement(By.id("submit")).submit();

        String loginPass = wd.switchTo().alert().getText();
        Assert.assertEquals("User  Train56 created ok!",loginPass);
        wd.switchTo().alert().accept();
        Thread.sleep(3000);
        tearDown();
    }

    @Test (dependsOnMethods = "siteTest01")
    public void siteTest02() throws InterruptedException {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wd.get("http://andestech.org/learning/rfb18/index.html");
        WebElement login = wd.findElement(By.cssSelector("a:nth-child(3)"));
        login.click();
        wd.findElement(By.cssSelector("input#login")).sendKeys("Train56");
        wd.findElement(By.cssSelector("input#pass")).sendKeys("Pass02train1");
        wd.findElement(By.cssSelector("#lgn")).submit();
        Thread.sleep(2000);
        Assert.assertTrue(wd.getTitle().contentEquals("Моя страница"));

//        String loginPass = wd.switchTo().alert().getText();
//        Assert.assertEquals("User  Train created ok!",loginPass);
//        wd.switchTo().alert().accept();
    }

    @AfterClass
    public void tearDown()
    {
      if(wd != null) wd.quit();
      System.out.println("--- Class: " + this);
    }

}
