package Ques_2;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

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

public class Ques_2 {
	protected static String url="http://the-internet.herokuapp.com/nested_frames";
	WebDriver driver=null;
  @Test
  public void f() throws IOException {
      WebElement frame1=driver.findElement(By.xpath("//frameset//frame[@name='frame-top']"));
	  driver.switchTo().frame(frame1);
	  List<WebElement> list1=driver.findElements(By.tagName("frame"));
	  System.out.println("Frameset  sub frames :: "+list1.size());
	  
	  if(list1.size()==3) {
		  System.out.println("Yes,Three frames are present in top frame");
	  }else {
		  System.out.println("No,there are"+list1.size()+"in the top frame");
	  }
	  
	  WebElement frameleft=driver.findElement(By.xpath("//frameset//frame[@name=\"frame-left\"]"));
	  driver.switchTo().frame(frameleft);
	  WebElement lefttext=driver.findElement(By.xpath("//body[contains(.,'LEFT')]"));
	  Assert.assertEquals(lefttext.getText(), "LEFT");
	  System.out.println("Left frame text:: "+lefttext.getText());
	  
	  
	  driver.switchTo().defaultContent();
	  driver.switchTo().frame(frame1);
	  WebElement framemiddle=driver.findElement(By.xpath("//frame[@name='frame-middle']"));
	  driver.switchTo().frame(framemiddle);
	  WebElement middletext=driver.findElement(By.xpath("//body[contains(.,'MIDDLE')]"));
	  Assert.assertEquals(middletext.getText(), "MIDDLE");
	  System.out.println("Middle frame text:: "+middletext.getText());
	  
	  
	  driver.switchTo().defaultContent();
	  driver.switchTo().frame(frame1);
	  WebElement frameright=driver.findElement(By.xpath("//frame[@name='frame-right']"));
	  driver.switchTo().frame(frameright);
	  WebElement righttext=driver.findElement(By.xpath("//body[contains(.,'RIGHT')]"));
	  Assert.assertEquals(righttext.getText(), "RIGHT");
	  System.out.println("Right frame text:: "+righttext.getText());
	  
	  
	  driver.switchTo().defaultContent();
	  driver.switchTo().frame(frame1);
	  
	  
	  driver.switchTo().defaultContent();
	  WebElement framebottom=driver.findElement(By.xpath("//frame[@name='frame-bottom']"));
	  driver.switchTo().frame(framebottom);
	  WebElement bottomtext=driver.findElement(By.xpath("//body[contains(.,'BOTTOM')]"));
	  Assert.assertEquals(bottomtext.getText(), "BOTTOM");
	  System.out.println("Bottom frame text:: "+bottomtext.getText());
	  
	  
	  driver.switchTo().defaultContent();
	  driver.switchTo().frame(frame1);
	  
	  
	  driver.switchTo().defaultContent();
	  System.out.println("Title of main page after switching back from frames: " + driver.getTitle());
	  System.out.println("URL of main page after switching back from frames: " + driver.getCurrentUrl());
	  
	  String title=driver.getTitle();
	  if(title.equals("Frames")) {
		  System.out.println("Yes title of page is same");
	  }
	  else if(! title.equals("Frames")) {
		  System.out.println("Title is either different or no title is present");
	  }
	  
	  File file1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  FileUtils.copyFile(file1, new File("C:\\Users\\harin\\eclipse-workspace\\Task_11\\Task_11_Screenshots\\Ques_2.png"));
	  
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
