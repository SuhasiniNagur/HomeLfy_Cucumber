package com.StepDefination;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.pom.SignIn;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Setup {

	 SignIn signin;
	public AppiumDriverLocalService service;
	public  static AndroidDriver driver;
		
	@Given("Setup Appium Server")
	public void setup_appium_server() throws MalformedURLException {
service = new AppiumServiceBuilder ().withAppiumJS(new File("C:\\Users\\Suhasini\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				
				.withIPAddress("127.0.0.1").usingPort(4724).withTimeout(Duration.ofSeconds(1000)).build();
			
			service.start();
		
				
     		UiAutomator2Options options = new UiAutomator2Options ();
			
			options.setDeviceName("Pixel3");
			
			options.setApp("C:\\Users\\Suhasini\\QDTAS\\HomeLfyServices_Cucumber\\src\\test\\resources\\apk\\app1.apk");
			
		     driver= new AndroidDriver(new URL("http://127.0.0.1:4724/"),options);
		String sessionId =driver.getSessionId().toString();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		     
		     
		DesiredCapabilities desiredCap =new DesiredCapabilities();
		desiredCap.setCapability("platformName", "Android");
		//desiredCap.setCapability("platformVersion", "(9.0");
		desiredCap.setCapability("deviceName", "any device name");
		desiredCap.setCapability("automationName", "UiAutomator2");
		//desiredCap.setCapability("appActivity", "");
		desiredCap.setCapability("avd", "Pixel3");
		desiredCap.setCapability("app", "/Users");
	  
	}

	@When("Login the App Email as {string} and Password as {string}")
	public void login_the_app_email_as_and_password_as(String email, String password) throws IOException {
       signin = new SignIn(driver);
	
		signin.enterEmail(email);
		signin.enterPassword(password);
		signin.Logoutbtn();
	}

	@Then("logOut the App")
	public void log_out_the_app() {
	   
		signin.Logoutbtn();
	}
}
