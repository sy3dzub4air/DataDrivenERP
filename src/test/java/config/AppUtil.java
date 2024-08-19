package config;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import commonFunctions.AdminLoginPage;
import commonFunctions.AdminLogoutPage;

public class AppUtil
{
	public static WebDriver driver;
	public static Properties conpro;
	@BeforeTest
	public static void setup() throws Throwable
	{
		conpro = new Properties();
		conpro.load(new FileInputStream("./PropertyFiles/Environment.properties"));
		if (conpro.getProperty("Browser").equalsIgnoreCase("Chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(conpro.getProperty("Url"));
			//call loginpage class
			AdminLoginPage login = PageFactory.initElements(driver, AdminLoginPage.class);
			login.adminLogin("admin", "master");
		}
		else if (conpro.getProperty("Browser").equalsIgnoreCase("fireFox")) 
		{
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(conpro.getProperty("Url"));
			//call loginpage class
			AdminLoginPage login = PageFactory.initElements(driver, AdminLoginPage.class);
			login.adminLogin("admin", "master");

		}
	}

	@AfterTest
	public static void tearDown()
	{
		AdminLogoutPage logout = PageFactory.initElements(driver, AdminLogoutPage.class);
		logout.AdminLogout();
		driver.quit();
		
	}
}
