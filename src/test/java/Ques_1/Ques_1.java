package Ques_1;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

public class Ques_1 {
	protected static String url="https://the-internet.herokuapp.com/windows";
	WebDriver driver=null;
  @Test
  public void f() throws InterruptedException, IOException {
	  String currenturl=driver.getWindowHandle();
	  WebElement newurl=driver.findElement(By.xpath("//*[.='Click Here']"));
	  newurl.click();
	  System.out.println("First window : "+currenturl);
	  System.out.println("Parent window : "+driver.getCurrentUrl());
	  Set<String> windowhandles=driver.getWindowHandles();
	  System.out.println("Windowhandles :: "+windowhandles);
	  System.out.println("Total windows in webdriver :: "+windowhandles.size());
	  
	  Iterator<String> it1=windowhandles.iterator();
	  String parent=it1.next();
	  while(it1.hasNext()) {
		  String child=it1.next();
		  if(child!=null) {
			  driver.switchTo().window(child);
			  String child_currenturl=driver.getWindowHandle();
			  System.out.println("Second window : "+child_currenturl);
			  System.out.println("Child : "+driver.getCurrentUrl());
			  
			  WebElement news=driver.findElement(By.xpath("(//*[.='New Window'])[2]"));
			  System.out.println("New window text : "+news.getText());
			  Assert.assertEquals(news.getText(), "New Window");
			  System.out.println("Yes the window has been switched to child and prints the heading in that window.");
			  Thread.sleep(1000);
			  driver.close();
			  
			  driver.switchTo().window(parent);
			  System.out.println("Back to original url : "+driver.getCurrentUrl());
			  Thread.sleep(1000);

			  
			  File file1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			  FileUtils.copyFile(file1, new File("C:\\Users\\harin\\eclipse-workspace\\Task_11\\Task_11_Screenshots\\Ques_1.png"));
			    
		  }
		  
	  }
	  
  }
  @BeforeSuite
  public void beforeSuite() {
	  WebDriverManager.chromedriver().setup();
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  }
@BeforeClass
public void beforclass() {
	driver.get(url);
}
  @AfterSuite
  public void afterSuite() {
	  if(driver!=null) {
		  driver.quit();
	  }
  }

}
