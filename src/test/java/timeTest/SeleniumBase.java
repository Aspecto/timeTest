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


public class SeleniumBase {
	Logger log = Logger.getLogger("TestLog");
	protected WebDriver driver;
	protected String baseUrl;
	protected StringBuffer verificationErrors = new StringBuffer();
	protected static BufferedWriter output;
	protected static boolean consoleOutputEnabled = false;
	protected static boolean fileOutputEnabled = false;
	protected static List<Pair<String, Period>> PeriodList = new ArrayList<Pair<String, Period>>();
	protected PeriodFormatter seconds = new PeriodFormatterBuilder().appendSecondsWithMillis().appendSuffix("s").toFormatter();
	
	protected void setCustomOutputs(String reportFilePath, String fileOutput, String consoleOutput) {
		if (consoleOutput.equals("true"))
			consoleOutputEnabled = true;
		if (fileOutput.equals("true"))
			fileOutputEnabled = true;
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
		output.append("\nRozpoczynam test;");
		for (Pair<String, Period> pair : PeriodList) {
			output.append(pair.getDescription() + ";");
		}
		output.append("Test zakończony");
		output.append("Czas trwania testu;");
		output.append("\ntestName;");
		for (Pair<String, Period> pair : PeriodList) {
			output.append(printPeriod(pair.getPeriod())+";");
		}
		output.append(result+ ";");
		output.append(printPeriod(totalTime) + ";");
	}
	
	protected void printToConsole() {
		for (Pair<String, Period> pair : PeriodList) {
			System.out.println(pair.getDescription() + printPeriod(pair.getPeriod())+"\n");
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
		addPeriodToCustomOutputs(logText,System.currentTimeMillis()-start);
		log.debug(logText + printPeriod(System.currentTimeMillis()-start)+"\n");
	}

	protected void clickElementAndCalcPeriod(By by, String logText) throws IOException {
		long start = System.currentTimeMillis();
		clickElement(by);
		addPeriodToCustomOutputs(logText, System.currentTimeMillis()-start);
		log.debug(logText + printPeriod(System.currentTimeMillis()-start)+"\n");
	}

	protected void selectElementByVisibleTextAndCalcPeriod(By by, String text, String logText)
			throws IOException {
		long start = System.currentTimeMillis();
		selectElementByVisbleText(by,text);
		addPeriodToCustomOutputs(logText,System.currentTimeMillis()-start);
		log.debug(logText + printPeriod(System.currentTimeMillis()-start)+"\n");
		
	}
}
