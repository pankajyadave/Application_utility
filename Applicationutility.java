package ApplicationUtility;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import BaseLibrary.BaseLibrary;

public class Applicationutility extends BaseLibrary
{
	static Actions act=new Actions(driver);
	public static void getscrollbyxpath(WebElement ele)
	{
		try {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true)", ele);
			
		} catch (Exception e) {
			System.out.println("Issue in getscrollbyxpath"+e);
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
	public static void DropdownSelectByText(WebElement ele,String value)
	{
		try
		{
			Select sel=new Select(ele);
			sel.selectByVisibleText(value);		
		} catch (Exception e) {
			System.out.println("Issue in DropdownSelectByText "+e);
		}
	}
	public static void DropdownSelectByValue(WebElement ele,String value)
	{
		try
		{
			Select sel=new Select(ele);
			sel.selectByValue(value);
		} catch (Exception e) {
			System.out.println("Issue in DropdownSelectByValue "+e);
		}
	}
}