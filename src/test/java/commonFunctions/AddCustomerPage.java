package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AddCustomerPage {
	WebDriver driver;
	//creating constructor for invoking webdriver methods
	public AddCustomerPage(WebDriver driver)
	{
		this.driver=driver;
	}

	// Define repository for add customer page elements
	@FindBy(xpath="(//a[contains(text(),'Customers')])[2]")
	WebElement objClickCustomerLink;
	@FindBy(xpath="(//span[@data-caption='Add'])[1]")
	WebElement objClickAddIcon;
	@FindBy(xpath="//input[@id='x_Customer_Number']")
	WebElement objCustomerNumber;
	@FindBy(xpath="//input[@id='x_Customer_Name']")
	WebElement objCustomerName;
	@FindBy(xpath="//textarea[@id='x_Address']")
	WebElement objAddress;
	@FindBy(xpath = "//input[@id='x_City']")
	WebElement objCity;
	@FindBy(xpath = "//input[@id='x_Country']")
	WebElement objCountry;
	@FindBy(xpath = "//input[@id='x_Contact_Person']")
	WebElement objContactPerson;
	@FindBy(xpath = "//input[@id='x_Phone_Number']")
	WebElement objPhoneNumber;
	@FindBy(xpath = "//input[@id='x__Email']")
	WebElement objEmail;
	@FindBy(xpath = "//input[@id='x_Mobile_Number']")
	WebElement objMobileNumber;
	@FindBy(name = "x_Notes")	
	WebElement objNotes;
	@FindBy(id = "btnAction")
	WebElement objAddButton;
	@FindBy(xpath = "//button[normalize-space()='OK!']")
	WebElement objConfirmOk;
	@FindBy(xpath = "(//button[starts-with(text(),'OK')])[6]")
	WebElement objAlertOk;
	@FindBy(xpath = "//span[@class='glyphicon glyphicon-search ewIcon']")
	WebElement objSearchPanel;
	@FindBy(xpath = "//input[@id='psearch']")
	WebElement objSearchTextBox;
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement objSearchButton;
	@FindBy(xpath = "//table[@class = 'table ewTable']/tbody/tr[1]/td[5]/div/span/span")
	WebElement objCustomerTable;
	// method for add customer
	public boolean addCustomer(String Cname, String Address, String City, String Country, String Cperson, String Pnumber,
			String Email, String Mnumber, String Notes) throws Throwable
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(this.objClickCustomerLink).click().perform();
		ac.moveToElement(this.objClickAddIcon).click().perform();
		//capture customer number
		String Exp_Data = this.objCustomerNumber.getAttribute("value");
		this.objCustomerName.sendKeys(Cname);
		this.objAddress.sendKeys(Address);
		this.objCity.sendKeys(City);
		this.objCountry.sendKeys(Country);
		this.objContactPerson.sendKeys(Cperson);
		this.objPhoneNumber.sendKeys(Pnumber);
		this.objEmail.sendKeys(Email);
		this.objMobileNumber.sendKeys(Mnumber);
		this.objNotes.sendKeys(Notes);
		ac.moveToElement(this.objAddButton).click().perform();
		Thread.sleep(2000);
		this.objConfirmOk.click();
		Thread.sleep(2000);
		this.objAlertOk.click();
		Thread.sleep(2000);
		if(!this.objSearchTextBox.isDisplayed())
			this.objSearchPanel.click();
		this.objSearchTextBox.clear();
		this.objSearchTextBox.sendKeys(Exp_Data);
		this.objSearchButton.click();
		Thread.sleep(2000);
		String Act_Data = this.objCustomerTable.getText();
		if (Act_Data.equals(Exp_Data))
		{
			Reporter.log(Act_Data+"       "+Exp_Data+" Customer Number is Matching",true);
			return true;	
		}
		else
		{
			Reporter.log(Act_Data+"       "+Exp_Data+" Customer Number is Not Matching",true);
			return false;
		}
	}




}
