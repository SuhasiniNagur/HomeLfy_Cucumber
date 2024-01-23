package com.pom;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.utilities.*;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignIn {

	AndroidDriver driver;
	WebDriverWait wait;
	LibraryActionsClass action = new LibraryActionsClass(driver);

	public SignIn(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "(//android.widget.EditText)[1]")
	private WebElement email;

	@AndroidFindBy(xpath = ("(//android.widget.EditText)[2]"))
	private WebElement password;

	@AndroidFindBy(xpath = ("//android.widget.Button[@content-desc=\"Sign In\"]"))
	private WebElement signin;

	public void enterEmail(String mail) throws IOException {

			this.email=email;
			action.explicitWait(driver, email, 40);
			action.click(email);
			action.custom_Sendkeys(email, mail);
			action.hideKeyboard();

	}

	public void enterPassword(String pass) throws  IOException {
		
		action.explicitWait(driver, password, 40);
		action.click(password);
		action.custom_Sendkeys(password, pass);
		action.hideKeyboard();
	}

	public WebElement Logoutbtn() {
		action.implicitWait(driver, 20);
		action.click(signin);
		return signin;

	}
}
