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

public class TimeTest {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	private BufferedWriter output;
	private boolean consoleOutputEnabled = false;
	private boolean fileOutputEnabled = false;
	private List<Pair<String, Period>> PeriodList = new ArrayList<Pair<String, Period>>();
	private String testName = "base_test";
	PeriodFormatter seconds = new PeriodFormatterBuilder().appendSecondsWithMillis().appendSuffix("s").toFormatter();

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://www.rejestrymedyczne.csioz.gov.pl/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	@Parameters({ "reportFilePath", "fileOutput", "consoleOutput" })
	public void base_test(String reportFilePath, String fileOutput, String consoleOutput) throws Exception {
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
		long start = System.currentTimeMillis();
		getDriverAndCalcPeriod(baseUrl + "/", "Czas otwarcia strony - ");
		assertEquals(driver.getTitle(), "CSIOZ - Rejestry medyczne");
		findElementClickAndCalcPeriod(By.xpath("//a[2]/div"), "Czas otwarcia rejestru - ");
		assertEquals(driver.getTitle(), "Rejestr Podmiotów Wykonujących Działalność Leczniczą - Strona główna");
		findElementClickAndCalcPeriod(By.linkText("Wyszukiwarki"), "Czas otwarcia wyboru wyszukiwarki - ");
		assertEquals(driver.findElement(By.linkText("Wyszukiwarka Podmiotów Leczniczych")).getText(), "Wyszukiwarka Podmiotów Leczniczych");
		findElementClickAndCalcPeriod(By.linkText("Wyszukiwarka Podmiotów Leczniczych"),
				"Czas wybrania wyszukiwarki - ");
		assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "Wyszukiwanie Podmiotów Leczniczych");
		findAndSelectElementByVisibleTextAndCalcPeriod(By.id("InstitutionId"), "Wojewoda Dolnośląski",
				"Czas wybrania instytucji - ");
		assertEquals(new Select(driver.findElement(By.id("InstitutionId"))).getFirstSelectedOption().getText(),"Wojewoda Dolnośląski");
		findElementClickAndCalcPeriod(By.xpath("//button[@type='submit']"), "Czas wyszukiwania - ");
		assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "Lista ksiąg rejestrowych");
		findElementClickAndCalcPeriod(By.linkText("»»"), "Czas otwarcia ostatniej strony - ");
		assertEquals(driver.findElement(By.xpath("//li[11]/a")).getText(),driver.findElement(By.xpath("//li[@class=\"active\"]")).getText());
		findElementClickAndCalcPeriod(By.xpath("(//a[contains(text(),'Wyświetl')])[last()]"),
				"Czas otwarcia ostatniego podmiotu - ");
		assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "Podgląd księgi rejestrowej");
		findElementClickAndCalcPeriod(By.linkText("Wyszukiwarki"), "Czas otwarcia wyboru wyszukiwarki - ");
		assertEquals(driver.findElement(By.linkText("Wyszukiwarka Podmiotów Leczniczych")).getText(), "Wyszukiwarka Podmiotów Leczniczych");
		findElementClickAndCalcPeriod(By.linkText("Wyszukiwarka Podmiotów Leczniczych"),
				"Czas wybrania wyszukiwarki - ");
		assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "Wyszukiwanie Podmiotów Leczniczych");
		findAndSelectElementByVisibleTextAndCalcPeriod(By.id("InstitutionId"), "Wojewoda Dolnośląski",
				"Czas wybrania instytucji - ");
		assertEquals(new Select(driver.findElement(By.id("InstitutionId"))).getFirstSelectedOption().getText(),"Wojewoda Dolnośląski");
		findElementClickAndCalcPeriod(By.xpath("//button[@type='submit']"), "Czas wyszukiwania - ");
		assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "Lista ksiąg rejestrowych");
		findElementClickAndCalcPeriod(By.linkText("2"), "Czas wybrania drugiej strony - ");
		assertEquals(driver.findElement(By.xpath("//a[contains(text(),'2')]")).getText(),driver.findElement(By.xpath("//li[@class=\"active\"]")).getText());
		findElementClickAndCalcPeriod(By.xpath("(//a[contains(text(),'Wyświetl')])[1]"), "Czas otwarcia podmiotu - ");
		assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "Podgląd księgi rejestrowej");
		PeriodList.add(new Pair<String, Period>("Całkowity czas testu - ", new Period(System.currentTimeMillis()-start)));
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		if (fileOutputEnabled)
			printToFile();
		output.close();
		if (consoleOutputEnabled)
			printToConsole();
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	public void printToFile() throws IOException {
		output.append("Rozpoczynam test " + testName + ":\n");
		for (Pair<String, Period> pair : PeriodList) {
			output.append(pair.getDescription() + seconds.print(pair.getPeriod())+"\n");
		}
	}

	public void printToConsole() throws IOException {
		System.out.println("Rozpoczynam test " + testName + ":\n");
		for (Pair<String, Period> pair : PeriodList) {
			System.out.println(pair.getDescription() + seconds.print(pair.getPeriod())+"\n");
		}
	}

	public void getDriverAndCalcPeriod(String URL, String logText) throws IOException {
		long start = System.currentTimeMillis();
		driver.get(URL);
		PeriodList.add(new Pair<String, Period>(logText, new Period(System.currentTimeMillis()-start)));
	}

	public void findElementClickAndCalcPeriod(By arg0, String logText) throws IOException {
		long start = System.currentTimeMillis();
		driver.findElement(arg0).click();
		PeriodList.add(new Pair<String, Period>(logText, new Period(System.currentTimeMillis()-start)));
	}

	public void findAndSelectElementByVisibleTextAndCalcPeriod(By arg0, String Text, String logText)
			throws IOException {
		long start = System.currentTimeMillis();
		new Select(driver.findElement(arg0)).selectByVisibleText(Text);
		PeriodList.add(new Pair<String, Period>(logText, new Period(System.currentTimeMillis()-start)));
	}
	
}
