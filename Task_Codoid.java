package org.test.codoid;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Task_Codoid {
	
	@Test(enabled = false)
	public static void Question1() throws AWTException {
//		Write code for handling multiple windows
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("iphone",Keys.ENTER);
		WebElement firstProductClick = driver.findElement(By.xpath("//div[contains(text(),'APPLE iPhone 14 (Purple, 128 GB)')]"));
		//clicking the first product for openning the new windows
		firstProductClick.click();		
		String mainWindow = driver.getWindowHandle();
		System.out.println(mainWindow);
		Set<String> allWindows = driver.getWindowHandles();
		System.out.println(allWindows);
		//switching to new windows
		for(String i:allWindows) {
			if(!mainWindow.equals(i)) {
				driver.switchTo().window(i);
			}
		}
		//performing actions on new windows
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")).click();
		//switching back to the main  window
		driver.switchTo().window(mainWindow);
	}
	@Test(enabled = false)
	public static void Question2() {
//		Write code for positive and negative scenarios for Alerts
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://demo.automationtesting.in/Alerts.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		//simple alert
		driver.findElement(By.xpath("//button[contains(@onclick,'alertbox()')]")).click();
		Alert a=driver.switchTo().alert();
		a.accept();	
		// confirm alert 
		driver.findElement(By.xpath("(//a[contains(@class,'analystic')])[2]")).click();
		driver.findElement(By.xpath("//button[contains(@onclick,'confirmbox()')]")).click();
		Alert c=driver.switchTo().alert();
		c.dismiss();
		// prompt alert
		driver.findElement(By.xpath("(//a[contains(@class,'analystic')])[3]")).click();
		driver.findElement(By.xpath("//button[contains(@onclick,'promptbox()')]")).click();
		Alert p=driver.switchTo().alert();
		p.sendKeys("hai");
		p.accept();
	}
	@Test(enabled = false)
	public static void Question3() {
//		Write code for Cross Browser Testing
		//launching chrome driver
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driverChrome = new ChromeDriver(options);
		driverChrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driverChrome.manage().window().maximize();
		driverChrome.get("https://www.facebook.com/");
		driverChrome.quit();
		//launching edge driver
		WebDriverManager.edgedriver().setup();
		WebDriver driverEdge = new EdgeDriver();
		driverEdge.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driverEdge.manage().window().maximize();
		driverEdge.get("https://www.facebook.com/");
		driverEdge.quit();	
	}
	@Test(enabled = false)
	public static void Question4() {
//		Write code for handling Frames
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://chercher.tech/practice/frames");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
//		----------no of frames---------------
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		System.out.println("---total frames---");
		System.out.println(frames.size());
//		----------------for text--------------
//		by uisng index
//		driver.switchTo().frame(0);
//		by uisng id
//		driver.switchTo().frame("frame1");
//		by using name
//		driver.switchTo().frame("iamframe");
//		-------------by using REF-----------------------
		WebElement f1 = driver.findElement(By.xpath("//iframe[@id='frame1']"));
		driver.switchTo().frame(f1);
		WebElement txtent = driver.findElement(By.xpath("//b[@id='topic']//following::input"));
		txtent.sendKeys("maran");
		driver.switchTo().defaultContent();
//		-------------for that dropdown---------------------------------------------
		driver.switchTo().frame("frame2");
		WebElement drp = driver.findElement(By.id("animals"));
		Select s=new Select(drp);
		s.selectByVisibleText("Big Baby Cat");
		driver.switchTo().defaultContent();
//		-----------------------for checkbox------------------------------------------
		driver.switchTo().frame("frame1");
		driver.switchTo().frame("frame3");
		WebElement clk = driver.findElement(By.id("a"));
		clk.click();
		driver.switchTo().parentFrame();
		txtent.sendKeys("   3");		
	}
	@Test(enabled = false)
	public static void Question5() throws InterruptedException {
//		Automate Menu and Sub Menu and click on a link in Sub Menu and navigate to the page and click on an element
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
		Actions ac = new Actions(driver);
		WebElement menu = driver.findElement(By.xpath("//div[contains(text(),'Fashion')]"));
		ac.moveToElement(menu).perform();
		Thread.sleep(3000);
		WebElement subMenu = driver.findElement(By.xpath("//a[@class='_6WOcW9 _2-k99T']"));
		ac.moveToElement(subMenu).perform();
		WebElement sunMenuClick = driver.findElement(By.xpath("(//a[@class='_6WOcW9 _3YpNQe'])[4]"));
		ac.click(sunMenuClick).perform();		
	}
	@Test(enabled =false)
	public static void Question6() {
//		Select multiple options from the dropdown
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.softwaretestingmaterial.com/sample-webpage-to-automate/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		WebElement drop = driver.findElement(By.name("multipleselect[]"));
		Select sc = new Select(drop);
		sc.selectByIndex(0);
		sc.selectByValue("msmanual");
		sc.selectByVisibleText("Selenium");
		boolean selectedMultiple = sc.isMultiple();
		System.out.println(selectedMultiple);
	}
	@Test(enabled = false)
	public static void Question8() throws IOException {
//		Write code for taking Screenshot
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File from = ts.getScreenshotAs(OutputType.FILE);
		File to = new File("C:\\Users\\Admin\\eclipse-workspace\\CodoidTask\\codoidTask.png"); 
		FileUtils.copyFile(from, to);
		System.out.println("Screen Shot DONE");
	}
	public static String getDataFromExcel( String sheetname, int RowNo, int CellNo) throws IOException {
		File loc=new File("C:\\\\Users\\\\Admin\\\\eclipse-workspace\\\\CodoidTask\\\\src\\\\test\\\\resources\\\\codoid.xlsx");
		FileInputStream st=new FileInputStream(loc);		
		Workbook w=new XSSFWorkbook(st);		
		Sheet sheet=w.getSheet(sheetname);		
		Row row = sheet.getRow(RowNo);		
		Cell cell = row.getCell(CellNo);		
		int type = cell.getCellType();
		String value=null;
		if(type==1) {
			value = cell.getStringCellValue();
		}
		else {
			if(DateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				SimpleDateFormat sf=new SimpleDateFormat("dd/MM/yyyy");
				value = sf.format(date);
			}
			else {
				double db = cell.getNumericCellValue();
				long num=(long)db;
				value = String.valueOf(num);
			}
		}
		return value;
	}
	@Test
	public static void Question9() throws IOException {
//		Read data from Excel and given that data as input for login and password and click on submit and validate the popup which says Login is successful
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
		WebElement user = driver.findElement(By.name("email"));
		user.sendKeys(getDataFromExcel("FbLogin", 1,0));
		WebElement password = driver.findElement(By.name("pass"));
		password.sendKeys(getDataFromExcel("FbLogin", 1, 1));
	}	
	@Test(enabled =false)
	public static void Question11() {
//		Write code for handling multiple browsers and switch to new windows?		        
//		chrome
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driverChrome = new ChromeDriver(options);
		driverChrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driverChrome.manage().window().maximize();
//		firefox
		WebDriverManager.edgedriver().setup();
		WebDriver driverEdge = new EdgeDriver();
		driverEdge.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driverEdge.manage().window().maximize();
//		Open Chrome and navigate to a website
		WebDriver chromeDriver = new ChromeDriver();
		chromeDriver.get("https://www.google.com");		        
//		Open Firefox and navigate to the same website
        WebDriver edgeDriver = new EdgeDriver();
        edgeDriver.get("https://www.google.com");
//      Perform some actions on each browser window
        WebElement chromeSearchBox = chromeDriver.findElement(By.name("q"));
        chromeSearchBox.sendKeys("Selenium");
        chromeSearchBox.submit();		        
        WebElement firefoxSearchBox = edgeDriver.findElement(By.name("q"));
        firefoxSearchBox.sendKeys("Java");
        firefoxSearchBox.submit();		  
//      Switch to the new Firefox window and perform some actions
        String mainWindowHandle = edgeDriver.getWindowHandle();
        for (String handle : edgeDriver.getWindowHandles()) {
        	if (!handle.equals(mainWindowHandle)) {
        		edgeDriver.switchTo().window(handle);
        		WebElement firefoxResult = edgeDriver.findElement(By.xpath("//h3[contains(text(),'Java Programming Language')]"));
        		System.out.println(firefoxResult.getText());
		        }
        	}
	}	
	@Test(enabled = false)
	public static void Question12() {
//		How to shift between tabs of the same browser using selenium?
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("iphone",Keys.ENTER);
		WebElement firstProductClick = driver.findElement(By.xpath("//div[contains(text(),'APPLE iPhone 14 (Purple, 128 GB)')]"));
		//clicking the first product for openning the new windows
		firstProductClick.click();		
		String mainWindow = driver.getWindowHandle();
		System.out.println(mainWindow);
		Set<String> allWindows = driver.getWindowHandles();
		System.out.println(allWindows);
		//switching to new windows
		for(String i:allWindows) {
			if(!mainWindow.equals(i)) {
				driver.switchTo().window(i);
			}
		}
		//performing actions on new windows
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")).click();
	}
	@Test(enabled =false)
	public static void Question15() {
//		How to find more than one web element in the list?
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		List<WebElement> webElementsList = driver.findElements(By.xpath("//div[@class='_6ltg']"));
		webElementsList.get(0);
		System.out.println(webElementsList);
	}

}
