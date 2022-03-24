package Application_Utility;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


import Base_Library.Base_library;

public class Application_utility extends Base_library
{
	static Actions act=new Actions(driver);
	public static void getscrollbyxpath(WebElement ele)
	{
		try {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true)", ele);
			
		} catch (Exception e) {
			System.out.println("Issue in getscrollbyxpath "+e);
		}
	}
	public static void GetScrollByJavaScript(String scrollvalueInPixel)
	{
		try {
			String scrollvalueInPixel_Can_Be="scroll(0,500)";
			((JavascriptExecutor)driver).executeScript(scrollvalueInPixel);
			
		} catch (Exception e) {
			System.out.println("Issue in GetScrollByJavaScript "+e);
		}
	}
	public static void Doubleclick(WebElement ele)
	{
		try
		{
			act.doubleClick(ele).perform();
		} catch (Exception e) {
			System.out.println("Issue in Doubleclick "+e);
		}
	}
	public static void Rightclick(WebElement ele)
	{
		try
		{
			act.contextClick(ele).perform();
		} catch (Exception e) {
			System.out.println("Issue in Rightclick "+e);
		}
	}
	public static void Changewindow(int tabno)
	{
		try 
		{
			Set<String> handle=driver.getWindowHandles();
			ArrayList<String>tabs=new ArrayList<String>(handle);
			driver.switchTo().window(tabs.get(tabno));
		} catch (Exception e) {
			System.out.println("Issue in Changewindow "+e);
		}
	}
	public static void UploadFile(String path)
	{
		try
		{
			StringSelection select=new StringSelection(path);
			Clipboard clipboard=Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(select, null);
			Robot robot=new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			//robot.delay(150);
			robot.keyRelease(KeyEvent.VK_ENTER);	
			
		} catch (Exception e) {
			System.out.println("Issue in UploadFile "+e);
		}
	}
	public static void DropdownSelectByVisibleText(WebElement ele,String value)
	{
		try
		{
			Select sel=new Select(ele);
			sel.selectByVisibleText(value);		
		} catch (Exception e) {
			System.out.println("Issue in DropdownSelectByVisibleText "+e);
		}
	}
	public static void DropdownSelectByValueAttribute(WebElement ele,String value)
	{
		try
		{
			Select sel=new Select(ele);
			sel.selectByValue(value);
		} catch (Exception e) {
			System.out.println("Issue in DropdownSelectByValueAttribute "+e);
		}
	}
	public static void DropdownSelectByIndex(WebElement ele, int indexing_number)
	{
		try {
			Select sel=new Select(ele);
			sel.selectByIndex(indexing_number);
		} catch (Exception e) {
			System.out.println("Issue in DropdownSelectByIndex "+e);
		}
		
	}
	public static void Dot_Click_Using_JavascriptExecutor(WebElement ele)
	{
		try {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].click()", ele);
			
		} catch (Exception e) {
			System.out.println("Issue in Dot_Click_Using_JavascriptExecutor "+e);
		}
	}
	//check broken link
	public static void Broken_Link()
	{
		try {
			String current_url=driver.getCurrentUrl();
			String url = "";
	        HttpURLConnection huc = null;
	        int respCode = 200;
	        
	        List<WebElement> links = driver.findElements(By.tagName("a"));
	        
	        Iterator<WebElement> it = links.iterator();
	        
	        while(it.hasNext()){
	            
	            url = it.next().getAttribute("href");
	            
	            System.out.println(url);
	        
	            if(url == null || url.isEmpty()){
	System.out.println("URL is either not configured for anchor tag or it is empty");
	                continue;
	            }
	            
	            if(!url.startsWith(current_url)){
	                System.out.println("URL belongs to another domain, skipping it.");
	                continue;
	            }
	            
	            try {
	                huc = (HttpURLConnection)(new URL(url).openConnection());
	                
	                huc.setRequestMethod("HEAD");
	                
	                huc.connect();
	                
	                respCode = huc.getResponseCode();
	                
	                if(respCode >= 400){
	                    System.out.println(url+" is a broken link");
	                }
	                else{
	                    System.out.println(url+" is a valid link");
	                }
	                    
	            } catch (MalformedURLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
			
		} catch (Exception e) {
			System.out.println("Issue in Broken_Link "+e);
		}
	}
}
