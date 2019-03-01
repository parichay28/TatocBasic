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
        action.verify("/html/body/div/div[2]/h1", "Grid Gate");

    }

    @Test (dependsOnMethods = {"openTatocBasic"})
    public void testGridGate(){
        //driver.get(TestTatocActions.getURL("gridGateURL"));
        action.gridGate();
        action.verify("/html/body/div/div[2]/h1", "Frame Dungeon");

    }

    @Test (dependsOnMethods = {"testGridGate"})
    public void testFrameDungeon(){
        action.frameDungeon();
        action.verify("/html/body/div/div[2]/h1", "Drag Around");

        
        }

    @Test (dependsOnMethods = {"testFrameDungeon"})
    public void testDragAround(){
        action.dragAround();
        action.verify("/html/body/div/div[2]/h1", "Popup Windows");
                
    }

    @Test (dependsOnMethods = {"testDragAround"})
    public void testPopupWindow() {
        action.popupWindow();
        action.verify("/html/body/div/div[2]/h1", "Cookie Handling");
                
    }

    @AfterClass
    public void teardown(){
        driver.close();
    }
}