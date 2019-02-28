package com.qainfotech;


import com.qainfotech.action.Action;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestTatocBasic {
    WebDriver driver;
    Action action = new Action();

    @BeforeClass
    public void setup(){
        driver = action.openChromeDriver();
    }

    @Test
    public void openTatocBasic(){
        action.openBaseURL();
    }

    @Test (dependsOnMethods = {"openTatocBasic"})
    public void testGridGate(){
        //driver.get(TestTatocActions.getURL("gridGateURL"));
        action.gridGate();
    }

    @Test (dependsOnMethods = {"testGridGate"})
    public void testFrameDungeon(){
        action.frameDungeon();
        
        }

    @Test (dependsOnMethods = {"testFrameDungeon"})
    public void testDragAround(){
        action.dragAround();
                
    }

    @Test (dependsOnMethods = {"testDragAround"})
    public void testPopupWindow() throws InterruptedException {
        action.popupWindow();
                
    }

    @AfterClass
    public void teardown(){
        driver.close();
    }
}