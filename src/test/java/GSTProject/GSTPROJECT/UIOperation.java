package GSTProject.GSTPROJECT;

import java.awt.Desktop.Action;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class UIOperation {
	WebDriver driver;
	JavascriptExecutor js;
	ExtentTest test;
	Actions action;
	public UIOperation(WebDriver driver, ExtentTest test){
        this.driver = driver;
        this.test = test;
    }
  @Test
  public void perform(Properties p,String operation,String objectName,String objectType,String value) throws Exception {
	  System.out.println("");
	  WebElement e1= null;
	  String window=null;
      switch (operation.toUpperCase()) {
      case "ADVCLICK":
    	  action = new Actions(driver);
    	 e1 = driver.findElement(getObject(p,objectName,objectType));
    	 action.moveToElement(e1).build().perform();
    	 Thread.sleep(3000);
    	 test.log(LogStatus.PASS, "Advanced Click is sucessful");
          break;
      case "CLICK":
    	  action = new Actions(driver);
    	  e1 = driver.findElement(getObject(p,objectName,objectType));
    	  if(objectName.contentEquals("Next"))
    	  {
    		  for(int i =0;i<2; i++)
    		  {
    			  action.moveToElement(e1).click().build().perform();
    		  }
    		  driver.findElement(By.xpath("//div[@class='promo-slider__item promo-slider__item--style-2 draggable gstn-e-invoice-bannerbg banner3-bg']/div/div/div[1]/div/div/div[2]/a")).click();
    	  }
    	  else
    	  {
    	  action.moveToElement(e1).click().build().perform();
    	  }
    	  Thread.sleep(3000);
    	  if(objectName.contentEquals("Options") || (objectName.contentEquals("knowmore")) || (objectName.contentEquals("Next")) || (objectName.contentEquals("event"))|| (objectName.contentEquals("tendors")))
    	  {
    	  driver.navigate().back();
    	  }
    	 test.log(LogStatus.PASS, "Click is sucessful");
          break;
      case "WINDOW":
    	  window =driver.getWindowHandle();
    	  Set<String> windows = driver.getWindowHandles();
    	  for(String handle: windows)
    	  {
    		  if(!handle.contentEquals(window))
    		  {
    			  driver.switchTo().window(handle);
    			  break;
    		  }
    	  }
    	  break;
      case "JCLICK":
    	  js = (JavascriptExecutor)driver;
    	  e1 = driver.findElement(getObject(p,objectName,objectType));
    	  boolean b1 = e1.isEnabled();
    	  System.out.println(b1);
    	  js.executeScript("arguments[0].click();", e1);
     case "CLEAR":
    	  driver.findElement(getObject(p,objectName,objectType)).clear();
    	  break;
      case "SCROLL":
    	  js.executeScript("window.scrollBy(0,500)");
    	  Thread.sleep(5000);
    	  break;
      case "SETTEXT":
    	  driver.findElement(getObject(p,objectName,objectType)).sendKeys(value);
    	  test.log(LogStatus.PASS, "set text is successful");
    	  break;
      case "GOTOURL":   
          //Get url of application
          driver.get("https://gstn.org.in/");
          driver.manage().window().maximize();
          test.log(LogStatus.PASS, "Gotourl is successful");
          break;
      case "GETTEXT":
          //Get text of an element
          driver.findElement(getObject(p,objectName,objectType)).getText();
          break;
      case "SELECT":
    	  e1 = driver.findElement(getObject(p,objectName,objectType));
    	  Select s1 = new Select(e1);
    	  s1.selectByIndex(8);
    	  break;
      case "ALERT":
          //Accepting the alert
          Alert alert = driver.switchTo().alert();
          alert.accept();
          break;   
      default:
          break;
      }
  }
  private By getObject(Properties p,String objectName,String objectType) throws Exception{
      //Find by xpath
      if(objectType.equalsIgnoreCase("XPATH")){
          
          return By.xpath(p.getProperty(objectName));
      }
      //find by class
      else if(objectType.equalsIgnoreCase("CLASSNAME")){
          
          return By.className(p.getProperty(objectName));
          
      }
      //find by name
      else if(objectType.equalsIgnoreCase("NAME")){
          
          return By.name(p.getProperty(objectName));
          
      }
      //Find by css
      else if(objectType.equalsIgnoreCase("CSS")){
          
          return By.cssSelector(p.getProperty(objectName));
          
      }
      //find by link
      else if(objectType.equalsIgnoreCase("LINK")){
          
          return By.linkText(p.getProperty(objectName));
          
      }
     else if(objectType.equalsIgnoreCase("ID")){
          
          return By.id(p.getProperty(objectName));
          
      }
      
      //find by partial link
      else if(objectType.equalsIgnoreCase("PARTIALLINK")){
          
          return By.partialLinkText(p.getProperty(objectName));
          
      }else
      {
          throw new Exception("Wrong object type");
      }
  }
}

