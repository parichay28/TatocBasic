package com.qainfotech.action;

import java.util.Set;

import com.qainfotech.FetchURLs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class Action {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    
    public WebDriver openChromeDriver(){
        System.setProperty("webdriver.chrome.driver", projectPath+"/chromedriver/chromedriver");
        driver = new ChromeDriver();
        return driver;
    }

    public void openBaseURL(){
        driver.get(FetchURLs.getURL("baseURL"));
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/div[2]/h1")).getText(), 
        "Grid Gate");

    }

    public void gridGate(){
        WebElement greenbox = driver.findElement(By.className("greenbox"));
        greenbox.click();
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/div[2]/h1")).getText(), 
        "Frame Dungeon");
    }

    public void frameDungeon(){
        driver.switchTo().frame("main");
        String box1Color = driver.findElement(By.id("answer")).getAttribute("class").toString();
        driver.switchTo().frame("child");
        String box2Color = driver.findElement(By.id("answer")).getAttribute("class").toString();
        while(!box1Color.equals(box2Color)){
            driver.switchTo().parentFrame();
            driver.findElement(By.linkText("Repaint Box 2")).click();;
            driver.switchTo().frame("child");
            box2Color = driver.findElement(By.id("answer")).getAttribute("class").toString();
        }
        driver.switchTo().parentFrame();
        driver.findElement(By.linkText("Proceed")).click();

        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/div[2]/h1")).getText(), 
        "Drag Around");
    }

    public void dragAround(){
        WebElement dragBox = driver.findElement(By.id("dragbox"));
        WebElement dropBox = driver.findElement(By.id("dropbox"));
        Actions act = new Actions(driver);
        act.dragAndDrop(dragBox, dropBox).build().perform();
        driver.findElement(By.linkText("Proceed")).click();
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/div[2]/h1")).getText(), 
        "Popup Windows");
    }

    public void popupWindow(){
        String mainWindowHandle = driver.getWindowHandle();
        driver.findElement(By.linkText("Launch Popup Window")).click();
        driver.switchTo().window("popup");
        driver.findElement(By.id("name")).sendKeys("Winter is coming!");
        driver.findElement(By.id("submit")).click();
        driver.switchTo().window(mainWindowHandle);
        driver.findElement(By.linkText("Proceed")).click();
        Assert.assertEquals(driver.findElement(By.xpath("/html/body/div/div[2]/h1")).getText(), 
        "Cookie Handling");
    }
}

