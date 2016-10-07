package timeTest;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;



public class SeleniumBase {
	Logger log = Logger.getLogger("TestLog");
	protected WebDriver driver;
	protected String baseUrl;
	protected StringBuffer verificationErrors = new StringBuffer();
	protected static BufferedWriter output;
	protected static boolean consoleOutputEnabled = false;
	protected static boolean printOperationNames = false;
	protected static boolean fileOutputEnabled = false;
	protected static Multimap<String,Period> PeriodMap = ArrayListMultimap.create();
	protected PeriodFormatter seconds = new PeriodFormatterBuilder().printZeroAlways().appendSeconds().appendPrefix(",").appendMillis3Digit().toFormatter();
	protected WebDriverWait wait;
	
	protected void setCustomOutputs(String reportFilePath, String fileOutput, String consoleOutput, String operationNames) {
		if (consoleOutput.equals("true"))
			consoleOutputEnabled = true;
		if (fileOutput.equals("true"))
			fileOutputEnabled = true;
		if (operationNames.equals("true"))
			printOperationNames = true;
		if (fileOutputEnabled) {
			try {
				output = new BufferedWriter(new FileWriter(reportFilePath, true));
			} catch (Exception E) {
				E.printStackTrace();
			}
		}
	}
	
	
	protected enum Browser { Chrome, Firefox, Safari, IE, Opera, };

	@SuppressWarnings("incomplete-switch")
	protected void setUpBrowser(Browser browser, String URL) {
		switch(browser)
		{
			case Firefox:
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
		}
		baseUrl = URL;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 2000);
	}
	
    protected void getURL(String URL)
    {
    	driver.get(URL);
    }
    
    protected void clickElement(By by)
    {
    	driver.findElement(by).click();
    }
    
    protected void selectElementByVisbleText(By by, String text)
    {
    	new Select(driver.findElement(by)).selectByVisibleText(text);
    }
    
    protected WebElement findElement(By by) throws InterruptedException
    {
    	WebElement element = driver.findElement(by);
    	Thread.sleep(20);
    	wait.until(ExpectedConditions.visibilityOf(element));
    	Thread.sleep(20);
    	wait.until(ExpectedConditions.elementToBeClickable(element));
    	return element;
    }
    
    protected String getTitle()
    {
    	return driver.getTitle();
    }
    
    protected void quit()
    {
    	driver.quit();
    }
    
    protected String getFirstSelectedOptionText(By by) throws InterruptedException
    {
    	return new Select(findElement(by)).getFirstSelectedOption().getText();
    }
    

	protected void printToFile(String testName, String result, long totalTime) throws IOException {
		if(printOperationNames)
		{
			output.append("Nazwa testu;");
			output.append("Czas trwania testu;");
			output.append("Test zakończony;");
			for (Entry<String, Period> entry : PeriodMap.entries()) {
				output.append(entry.getKey() + ";");
			}
			output.append("\n");
		}
		output.append("testName;");
		output.append(printPeriod(totalTime) + ";");
		output.append(result+ ";");
		for (Entry<String, Period> entry : PeriodMap.entries()) {
			output.append(printPeriod(entry.getValue())+";");
		}
		output.append("\n");
	}
	
	protected void testFinished(ITestResult result, String resultString) {
		log.debug(result.getName()+" zakończony " + resultString + "\n\nCzas trwania testu(w sekundach): "+printPeriod(result.getEndMillis()-result.getStartMillis()));
		if(consoleOutputEnabled)
		{
			System.out.println(result.getName()+" zakończony "  + resultString + "\n\nCzas trwania testu(w sekundach): "+printPeriod(result.getEndMillis()-result.getStartMillis()));
		}
		try {
			if(fileOutputEnabled)
			{
				printToFile(result.getName() , resultString, result.getEndMillis()-result.getStartMillis());
			}
		} catch (Exception e) {
			log.debug(result.getName() + " " + e.getMessage());
		}
	}

	protected void addPeriodToCustomOutputs(String logText, long periodInMillis)
	{
		PeriodMap.put(logText, new Period(periodInMillis));
	}
	
	protected String printPeriod(long millis)
	{
		return printPeriod(new Period(millis));
	}
	
	protected String printPeriod(Period period)
	{
		return seconds.print(period);
	}
	
	protected void getDriverAndCalcPeriod(String URL, String logText) throws IOException {
		long start = System.currentTimeMillis();
		getURL(URL);
		long time = System.currentTimeMillis()-start;
		addPeriodToCustomOutputs(logText,time);
		if(consoleOutputEnabled)
			System.out.println(logText+": " + printPeriod(time) + "\n");
		log.debug(logText + ": " + printPeriod(time)+"\n");
	}

	protected void clickElementAndCalcPeriod(By by, String logText) throws IOException {
		long start = System.currentTimeMillis();
		clickElement(by);
		long time = System.currentTimeMillis()-start;
		addPeriodToCustomOutputs(logText, time);
		if(consoleOutputEnabled)
			System.out.println(logText+": " + printPeriod(time) + "\n");
		log.debug(logText + ": " + printPeriod(time)+"\n");
	}

	protected void selectElementByVisibleTextAndCalcPeriod(By by, String text, String logText)
			throws IOException {
		long start = System.currentTimeMillis();
		selectElementByVisbleText(by,text);
		long time = System.currentTimeMillis()-start;
		addPeriodToCustomOutputs(logText,time);
		if(consoleOutputEnabled)
			System.out.println(logText+": " + printPeriod(time) + "\n");
		log.debug(logText + ": " + printPeriod(time)+"\n");		
	}
	
	protected void sendKeysAndCalcPeriod(By by, String text, String logText)
			throws IOException, InterruptedException {
		long start = System.currentTimeMillis();
		findElement(by).sendKeys(text);
		long time = System.currentTimeMillis()-start;
		addPeriodToCustomOutputs(logText,time);
		if(consoleOutputEnabled)
			System.out.println(logText+": " + printPeriod(time) + "\n");
		log.debug(logText + ": " + printPeriod(time)+"\n");		
	}
	
}
