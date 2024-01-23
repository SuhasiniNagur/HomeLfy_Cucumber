package com.utilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.nativekey.KeyEvent;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;

import org.openqa.selenium.remote.RemoteWebElement;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class LibraryActionsClass {
	

    private AndroidDriver driver;
    private int timeOut; // Set your timeout value accordingly
    
//    private static final Logger Log = LogManager.getLogger(LibraryActionsClass.class);
    
    public LibraryActionsClass(AndroidDriver driver) {
        this.driver = driver;
    }

    public void longPressAction(WebElement ele) {
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(),
                        "duration", 2000));
    }

    public void scrollToEndAction() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down", "percent", 3.0));
        } while (canScrollMore);
    }

    public void swipeAction(WebElement ele, String direction) {
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "direction", direction, "percent", 0.75));
    }

    public static void custom_Sendkeys(WebElement element, String value) {
        try {
            element.sendKeys(value);
            System.out.println( value);
        } catch (Exception e) {
            System.out.println("Unable to send value" + e);
        }
    }

    public static void custom_Click(WebElement element, String fieldname) {
        try {
            element.click();
            System.out.println("clicked Successfully :" + fieldname);
        } catch (Exception e) {
            System.out.println("Unable to click" + e);
        }
    }

    public Boolean isElementPresent(By targetElement) throws InterruptedException {
        Boolean isPresent = driver.findElements(targetElement).size() > 0;
        return isPresent;
    }


    
    public void hideKeyboard() {
    	this.driver=driver;
        if (driver == null) {
            System.out.println("Unable to hide keyboard: Driver is null.");
            // You can log an error or take appropriate action based on your requirements.
            return;
        }

        if (driver instanceof HidesKeyboard) {
            try {
                ((HidesKeyboard) driver).hideKeyboard();
                System.out.println("Keyboard hidden successfully.");
            } catch (Exception e) {
                System.out.println("Failed to hide keyboard: " + e.getMessage());
            }
        } else {
            System.out.println("Unable to hide keyboard: Driver does not support keyboard hiding.");
        }
    }
   
    public void back() {
    	 if (driver == null) {
             System.out.println("Unable to press back: Driver is null.");
             return;
         }
    	if (driver instanceof AndroidDriver) {
            try {
                ((AndroidDriver) driver).pressKey(new KeyEvent().withKey(AndroidKey.BACK));
                System.out.println("Back button pressed successfully.");
            } catch (Exception e) {
                System.out.println("Failed to press back: " + e.getMessage());
            }
        } else {
            System.out.println("Unable to press back: Driver is not an instance of AndroidDriver.");
      
        }
    }

    public boolean waitForVisibility(By targetElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.visibilityOfElementLocated(targetElement));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element is not visible: " + targetElement);
            throw e;
        }
    }
    


    public boolean waitForInvisibility(By targetElement) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(targetElement));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element is still visible: " + targetElement);
            System.out.println();
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public void tap(double xPosition, double yPosition) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> tapObject = new HashMap<String, Double>();
        tapObject.put("startX", xPosition);
        tapObject.put("startY", yPosition);
        js.executeScript("mobile: tap", tapObject);
    }

    public List<WebElement> findElements(By locator) {
        try {
            List<WebElement> element = driver.findElements(locator);
            return element;
        } catch (NoSuchElementException e) {
            throw e;
        }
    }

    public String getAlertText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            return alertText;
        } catch (NoAlertPresentException e) {
            throw e;
        }
    }

    public boolean isAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            throw e;
        }
    }

    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    public void getNetworkConnection() {
        System.out.println(((AndroidDriver) driver).getConnection());
    }

    public void setNetworkConnection(boolean airplaneMode, boolean wifi, boolean data) {
        long mode = 1L;

        if (wifi) {
            mode = 2L;
        } else if (data) {
            mode = 4L;
        }

        ConnectionState connectionState = new ConnectionState(mode);
        ((AndroidDriver) driver).setConnection(connectionState);
        System.out.println("Your current connection settings are :" + ((AndroidDriver) driver).getConnection());
    }

    public void getContext() {
        Set<String> contextHandles = ((AndroidDriver) driver).getContextHandles();
        for (String context : contextHandles) {
            System.out.println("Context: " + context);
        }
    }

    public void setContext(String context) {
        Set<String> contextNames = ((AndroidDriver) driver).getContextHandles();

        if (contextNames.contains(context)) {
            ((AndroidDriver) driver).context(context);
//            Log.info("Context changed successfully to: " + context);
        } else {
//            Log.info(context + " not found on this page");
        }

//        Log.info("Current context: " + ((AndroidDriver) driver).getContext());
    }

    public void longPress(By locator) {
        try {
            WebElement element = driver.findElement(locator);

            TouchAction touch = new TouchAction((AndroidDriver) driver);
            LongPressOptions longPressOptions = new LongPressOptions();
            longPressOptions.withElement(ElementOption.element(element));
            touch.longPress(longPressOptions).release().perform();
//            Log.info("Long press successful on element: " + element);
        } catch (NoSuchElementException e) {
//            Log.error(this.getClass().getName(), "findElement", "Element not found" + locator);
            throw e;
        }
    }

    public void longPress(int x, int y) {
        TouchAction touch = new TouchAction((AndroidDriver) driver);
        PointOption pointOption = new PointOption();
        pointOption.withCoordinates(x, y);
        touch.longPress(pointOption).release().perform();
//        Log.info("Long press successful on coordinates: " + "( " + x + "," + y + " )");
    }

    public void longPress(By locator, int x, int y) {
        try {
            WebElement element = driver.findElement(locator);
            TouchAction touch = new TouchAction((AndroidDriver) driver);
            LongPressOptions longPressOptions = new LongPressOptions();
            longPressOptions.withPosition(new PointOption().withCoordinates(x, y)).withElement(ElementOption.element(element));
            touch.longPress(longPressOptions).release().perform();
//            Log.info("Long press successful on element: " + element + " on coordinates" + "( " + x + "," + y + " )");
        } catch (NoSuchElementException e) {
//            Log.error(this.getClass().getName(), "findElement", "Element not found" + locator);
            throw e;
        }
    }

    public void swipe(double startX, double startY, double endX, double endY, double duration) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, Double> swipeObject = new HashMap<String, Double>();
        swipeObject.put("startX", startX);
        swipeObject.put("startY", startY);
        swipeObject.put("endX", endX);
        swipeObject.put("endY", endY);
        swipeObject.put("duration", duration);
        js.executeScript("mobile: swipe", swipeObject);
    }

    static String UiScrollable(String uiSelector) {
        return "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + uiSelector + ".instance(0));";
    }

    public void openNotifications() {
        ((AndroidDriver) driver).openNotifications();
    }

//    public void launchApp() {
//        ((AndroidDriver) driver).launchApp();
//
//    }

    public void click(WebElement btn) {
    	btn.click();
    }
    public void scrollDown(int swipeTimes, int durationForSwipe) {
        Dimension dimension = driver.manage().window().getSize();

        for (int i = 0; i <= swipeTimes; i++) {
            int start = (int) (dimension.getHeight() * 0.5);
            int end = (int) (dimension.getHeight() * 0.3);
            int x = (int) (dimension.getWidth() * .5);

            new TouchAction((AndroidDriver) driver).press(PointOption.point(x, start)).moveTo(PointOption.point(x, end))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationForSwipe)))
                    .release().perform();
        }
    }

    public void scrollUp(int swipeTimes, int durationForSwipe) {
        Dimension dimension = driver.manage().window().getSize();

        for (int i = 0; i <= swipeTimes; i++) {
            int start = (int) (dimension.getHeight() * 0.3);
            int end = (int) (dimension.getHeight() * 0.5);
            int x = (int) (dimension.getWidth() * .5);

            new TouchAction((AndroidDriver) driver).press(PointOption.point(x, start)).moveTo(PointOption.point(x, end))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationForSwipe)))
                    .release().perform();
        }
    }
    
	public void fluentWait(WebDriver driver,WebElement element, int timeOut) {
	    Wait<WebDriver> wait = null;
	    try {
	        wait = new FluentWait<WebDriver>((WebDriver) driver)
	        		.withTimeout(Duration.ofSeconds(20))
	        	    .pollingEvery(Duration.ofSeconds(2))
	        	    .ignoring(Exception.class);
	        wait.until(ExpectedConditions.visibilityOf(element));
	        element.click();
	    }catch(Exception e) {
	    }
	}

	public void implicitWait(WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void explicitWait(WebDriver driver, WebElement element, int timeOut ) {
		try {
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void pageLoadTimeOut(WebDriver driver, int timeOut) {
		driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
	}
}