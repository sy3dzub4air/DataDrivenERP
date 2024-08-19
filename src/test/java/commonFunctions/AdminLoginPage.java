package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage 
{
	//define repository for login elements
	@FindBy(xpath="//button[@id='btnreset']")
	WebElement objReset;
	@FindBy(xpath = "//input[@id='username']")
	WebElement objUser;
	@FindBy(xpath="//input[@id='password']")
	WebElement objPass;
	@FindBy(xpath= "//button[@id='btnsubmit']")
	WebElement objLogin;
	
	public void adminLogin(String user, String pass)
	{
		objReset.click();
		objUser.sendKeys(user);
		objPass.sendKeys(pass);
		objLogin.click();
	}

}
