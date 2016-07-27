package timeTest;



import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;



public class SeleniumBase {
	Logger log = Logger.getLogger("TestLog");
	protected WebDriver driver;
	protected String baseUrl;
	protected StringBuffer verificationErrors = new StringBuffer();
	protected static BufferedWriter output;
	protected static BufferedWriter outputStats;
	protected static boolean consoleOutputEnabled = false;
	protected static boolean printOperationNames = false;
	protected static boolean fileOutputEnabled = false;
	protected static int operationCount = 0;
	protected static List<Pair<String, Period>> PeriodList = new ArrayList<Pair<String, Period>>();
	protected PeriodFormatter seconds = new PeriodFormatterBuilder().appendSeconds().appendPrefix(",").appendMillis3Digit().toFormatter();
	
	protected void setCustomOutputs(String reportFilePath, String fileOutput, String consoleOutput, String operationNames, int countedOperations) {
		operationCount = countedOperations;
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
    
    protected WebElement findElement(By by)
    {
    	return driver.findElement(by);
    }
    
    protected String getTitle()
    {
    	return driver.getTitle();
    }
    
    protected void quit()
    {
    	driver.quit();
    }
    
    protected String getFirstSelectedOptionText(By by)
    {
    	return new Select(findElement(by)).getFirstSelectedOption().getText();
    }
    

	protected void printToFile(String testName, String result, long totalTime) throws IOException {
		if(printOperationNames)
		{
			output.append("Rozpoczynam test;");
			for (Pair<String, Period> pair : PeriodList) {
				output.append(pair.getDescription() + ";");
			}
			for(int i=0;i<operationCount;i++)
			{
				output.append(";");
			}
			output.append("Czas trwania testu;");
			output.append("Test zakończony;\n");
		}
		output.append("testName;");
		for (Pair<String, Period> pair : PeriodList) {
			output.append(printPeriod(pair.getPeriod())+";");
		}
		for(int i=0;i<operationCount;i++)
		{
			output.append(";");
		}
		output.append(printPeriod(totalTime) + ";");
		output.append(result+ ";\n");
	}
	
	
	protected void printToConsole() {
		for (Pair<String, Period> pair : PeriodList) {
			System.out.println(pair.getDescription() + ": " + printPeriod(pair.getPeriod())+"\n");
		}
	}
	
	protected void testFinished(ITestResult result, String resultString) {
		log.debug(result.getName()+" zakończony " + resultString + "\n\nCzas trwania testu(w sekundach): "+printPeriod(result.getEndMillis()-result.getStartMillis()));
		if(consoleOutputEnabled)
		{
			printToConsole();
			System.out.println(result.getName()+" zakończony"  + resultString + "\n\nCzas trwania testu(w sekundach): "+printPeriod(result.getEndMillis()-result.getStartMillis()));
		}
		try {
			if(fileOutputEnabled)
			{
				printToFile(result.getName() , resultString, result.getEndMillis()-result.getStartMillis());
			}
		} catch (IOException e) {
			log.debug(result.getName() + " " + e.getMessage());
		}
	}

	protected void addPeriodToCustomOutputs(String logText, long periodInMillis)
	{
		PeriodList.add(new Pair<String, Period>(logText, new Period(periodInMillis)));
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
		if(operationCount>0)
		{
			addPeriodToCustomOutputs(logText,System.currentTimeMillis()-start);
			operationCount--;
		}
		log.debug(logText + ": " + printPeriod(System.currentTimeMillis()-start)+"\n");
	}

	protected void clickElementAndCalcPeriod(By by, String logText) throws IOException {
		long start = System.currentTimeMillis();
		clickElement(by);
		if(operationCount>0)
		{
			addPeriodToCustomOutputs(logText, System.currentTimeMillis()-start);
			operationCount--;
		}
		log.debug(logText + ": " + printPeriod(System.currentTimeMillis()-start)+"\n");
	}

	protected void selectElementByVisibleTextAndCalcPeriod(By by, String text, String logText)
			throws IOException {
		long start = System.currentTimeMillis();
		selectElementByVisbleText(by,text);
		if(operationCount>0)
		{
			addPeriodToCustomOutputs(logText,System.currentTimeMillis()-start);
			operationCount--;
		}
		log.debug(logText + ": " + printPeriod(System.currentTimeMillis()-start)+"\n");
		
	}
}
