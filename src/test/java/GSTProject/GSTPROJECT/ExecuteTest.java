package GSTProject.GSTPROJECT;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExecuteTest {
	WebDriver webdriver;
	ReadGuru99ExcelFile file;
	ReadObject object;
	Properties allObjects;
	UIOperation operation;
	String filepath;
	ExtentReports report;
	ExtentTest test;
	
@BeforeTest
public void setUp() throws Exception
{
	System.setProperty("webdriver.chrome.driver","C:\\Users\\Sarath Nithyananthan\\Downloads\\chromedriver_win32 (4)\\chromedriver.exe");
	  webdriver = new ChromeDriver();
	  webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  report = new ExtentReports("C:\\Users\\Sarath Nithyananthan\\Desktop\\GST_Report.html");
	  test = report.startTest("Auto Test starts");
	  file = new ReadGuru99ExcelFile();
	  object = new ReadObject();
	  allObjects = object.getObjectRepository();
	  operation = new UIOperation(webdriver,test);
	  filepath="C:\\Users\\Sarath Nithyananthan\\eclipse-workspace\\GSTPROJECT\\src\\test\\java\\GSTProject\\GSTPROJECT";
}
  @Test
  public void testLogin() throws Exception {
	  //Read keyword sheet
	  Sheet guru99Sheet = file.readExcel(filepath+"\\","Interview1.xlsx" , "Sheet1");
	  //Find number of rows in excel file
	      int rowCount = guru99Sheet.getLastRowNum()-guru99Sheet.getFirstRowNum();
	      //Create a loop over all the rows of excel file to read it
	      for (int i = 1; i < rowCount+1; i++) {
	          //Loop over all the rows
	          Row row = guru99Sheet.getRow(i);
	          //Check if the first cell contain a value, if yes, That means it is the new testcase name
	          if(row.getCell(0).toString().length()==5 ||row.getCell(0).toString().length()==6 ){
	          //Print testcase detail on console
	              System.out.println(row.getCell(1).toString()+"----"+ row.getCell(2).toString()+"----"+
	              row.getCell(3).toString()+"----"+ row.getCell(4).toString());
	          //Call perform function to perform operation on UI
	              operation.perform(allObjects, row.getCell(1).toString(), row.getCell(2).toString(),
	                  row.getCell(3).toString(), row.getCell(4).toString());
	       }
	          else{
	              //Print the new testcase name when it started
	                  System.out.println("New Testcase->"+row.getCell(0).toString() +" Started");
	              }
	          }
  }
  @AfterTest
	public void tearDown() throws Exception {
	  report.endTest(test);
	  report.flush();
		webdriver.quit();
	}
  }
