package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.AddCustomerPage;
import config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil {
	String inputpath= "./FileInput/CustomerData.xlsx";
	String outputpath = "./FileOutput/DataDrivenResults.xlsx";
	String TestData = "AddCustomer";
	ExtentReports report;
	ExtentTest logger;
	@Test
	public void startTest() throws Throwable
	{
		report = new ExtentReports("./target/Reports/Customer.html");
		// call addcustomerpage class
		AddCustomerPage customer = PageFactory.initElements(driver, AddCustomerPage.class);
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc = xl.rowCount(TestData);
		Reporter.log("No. of Rows are::", rc, true);
		for (int i = 1; i <=rc; i++) 
		{
			logger = report.startTest("Add Customer");
			String customerName= xl.getCellData(TestData, i, 0);
			String Address = xl.getCellData(TestData, i, 1);
			String City = xl.getCellData(TestData, i, 2);
			String Country = xl.getCellData(TestData, i, 3);
			String ContactPerson = xl.getCellData(TestData, i, 4);
			String phoneNumber = xl.getCellData(TestData, i, 5);
			String Email = xl.getCellData(TestData, i, 6);
			String MobileNumber = xl.getCellData(TestData, i, 7);
			String Notes = xl.getCellData(TestData, i, 8);
			logger.log(LogStatus.INFO, customerName+"   "+Address+"  "+City+"  "+Country+"  "+ContactPerson+"  "+
			phoneNumber+"  "+Email+"  "+MobileNumber+"  "+Notes);
			boolean res = customer.addCustomer(customerName, Address, City, Country, ContactPerson, phoneNumber, Email, 
					MobileNumber, Notes);
			if(res)
			{
				// if res is true write as pass into status column in testdata sheet
				xl.setCellData(TestData, i, 9, "Pass", outputpath);
				logger.log(LogStatus.PASS, "Add Customer is Passed");
			}
			else
			{
				xl.setCellData(TestData, i, 9, "Fail", outputpath);
				logger.log(LogStatus.FAIL, "Add Customer is Failed");
			}
			report.endTest(logger);
			report.flush();

		}
	}

}
