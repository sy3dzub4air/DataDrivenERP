package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLogoutPage {
	@FindBy(xpath = "(//a[contains(text(),'Logout')])[3]")
	WebElement objLogoutLink;
	public void AdminLogout() 
	{
		objLogoutLink.click();
	}

}
