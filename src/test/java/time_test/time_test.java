package time_test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class time_test {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();
  private BufferedWriter output;
  private boolean consoleOutputEnabled=false;
  private boolean fileOutputEnabled=false;
  private List<Pair<String,Period>> PeriodList= new ArrayList<Pair<String, Period>>();
  private String testName="base_test";
  PeriodFormatter seconds= new PeriodFormatterBuilder().appendSecondsWithMillis().appendSuffix("s").toFormatter();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.rejestrymedyczne.csioz.gov.pl/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  @Parameters({"reportFilePath","fileOutput","consoleOutput"})
  public void base_test(String reportFilePath,String fileOutput,String consoleOutput) throws Exception {
	  if(consoleOutput.equals("true"))
		  consoleOutputEnabled=true;
	  if(fileOutput.equals("true"))
		  fileOutputEnabled=true;
	  if(fileOutputEnabled)
	  {
		  try{
		output = new BufferedWriter(new FileWriter(reportFilePath, true));}
		  catch(Exception E)
		  {
			  E.printStackTrace();
		  }
	 }
	long start = System.currentTimeMillis();
	getDriverAndCalcPeriod(baseUrl + "/","Czas otwarcia strony - ");
	findElementClickAndCalcPeriod(By.xpath("//a[2]/div"),"Czas otwarcia rejestru - ");
	findElementClickAndCalcPeriod(By.linkText("Wyszukiwarki"),"Czas otwarcia wyboru wyszukiwarki - ");
	findElementClickAndCalcPeriod(By.linkText("Wyszukiwarka Podmiotów Leczniczych"),"Czas wybrania wyszukiwarki - ");
	findAndSelectElementByVisibleTextAndCalcPeriod(By.id("InstitutionId"),"Wojewoda Dolnośląski","Czas wybrania instytucji - ");
    findElementClickAndCalcPeriod(By.xpath("//button[@type='submit']"),"Czas wyszukiwania - ");
    findElementClickAndCalcPeriod(By.linkText("»»"),"Czas otwarcia ostatniej strony - ");
    findElementClickAndCalcPeriod(By.xpath("(//a[contains(text(),'Wyświetl')])[4]"),"Czas otwarcia ostatniego podmiotu - ");
    findElementClickAndCalcPeriod(By.linkText("Wyszukiwarki"),"Czas otwarcia wyboru wyszukiwarki - ");
    findElementClickAndCalcPeriod(By.linkText("Wyszukiwarka Podmiotów Leczniczych"),"Czas wybrania wyszukiwarki - ");
    findAndSelectElementByVisibleTextAndCalcPeriod(By.id("InstitutionId"),"Wojewoda Dolnośląski","Czas wybrania instytucji - ");
    findElementClickAndCalcPeriod(By.xpath("//button[@type='submit']"),"Czas wyszukiwania - ");
    findElementClickAndCalcPeriod(By.linkText("2"),"Czas wybrania drugiej strony - ");
    findElementClickAndCalcPeriod(By.xpath("(//a[contains(text(),'Wyświetl')])[8]"),"Czas otwarcia podmiotu - ");
    long finish = System.currentTimeMillis();
    long totalTime = (finish - start);
    PeriodList.add(new Pair<String, Period>("Całkowity czas testu - ",new Period(totalTime)));
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
	if(fileOutputEnabled) printToFile();
    output.close();
	if(consoleOutputEnabled) printToConsole();
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }  
  }
  
  public void printToFile() throws IOException
  {
	output.append("Rozpoczynam test "+testName+":\n");
	for(Pair<String,Period> pair : PeriodList)
	{
		output.append(pair.getDescription()+seconds.print(pair.getTime()));
	}		
  }
  
  public void printToConsole() throws IOException
  {
	  System.out.println("Rozpoczynam test "+testName+":\n");
	for(Pair<String,Period> pair : PeriodList)
	{
		System.out.println(pair.getDescription()+seconds.print(pair.getTime()));
	}		
  }
  
  public void getDriverAndCalcPeriod(String URL,String logText) throws IOException
  {
		long start = System.currentTimeMillis();
	    driver.get(URL);
		long finish = System.currentTimeMillis();
		long totalTime = (finish - start);
		PeriodList.add(new Pair<String, Period>(logText,new Period(totalTime)));
  }
  
  public void findElementClickAndCalcPeriod(By arg0,String logText) throws IOException
  {
  	long start = System.currentTimeMillis();
  	driver.findElement(arg0).click();;
 	long finish = System.currentTimeMillis();
 	long totalTime = (finish - start);
 	PeriodList.add(new Pair<String, Period>(logText,new Period(totalTime)));
  }
  
  public void findAndSelectElementByVisibleTextAndCalcPeriod(By arg0,String Text,String logText) throws IOException
  {
  	long start = System.currentTimeMillis();
  	new Select(driver.findElement(arg0)).selectByVisibleText(Text);
 	long finish = System.currentTimeMillis();
 	long totalTime = (finish - start);
 	PeriodList.add(new Pair<String, Period>(logText,new Period(totalTime)));
  }
}
