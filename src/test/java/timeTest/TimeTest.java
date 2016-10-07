package timeTest;


import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

@Listeners(timeTest.RegisterTestListener.class)		

public class TimeTest extends RegisterBase{
	
	@Test
	public void base_test() throws Exception {
		getDriverAndCalcPeriod(baseUrl + "/", "Czas otwarcia strony(w sekundach)");
		sendKeysAndCalcPeriod(By.id("username"), "", "Czas wpisywania loginu(w sekundach)");
		sendKeysAndCalcPeriod(By.id("password"), "", "Czas wpisywania hasła(w sekundach)");
		clickElementAndCalcPeriod(By.xpath("//button[@type='submit']"), "Czas logowania(w sekundach)");
		Thread.sleep(3000);
		clickElementAndCalcPeriod(By.cssSelector("span.select-field-icon-chevron.btn"), "Czas wybierania systemu(w sekundach)");
		clickElementAndCalcPeriod(By.xpath("//div[2]/div/div/div/span[3]"), "Czas wyboru akt(w sekundach)");
		clickElementAndCalcPeriod(By.xpath("//div/span/span/a/span"), "");
		clickElementAndCalcPeriod(By.linkText("Wykonaj"), "");
		Thread.sleep(1000);
		sendKeysAndCalcPeriod(By.id("tytul"), "test", "");
		clickElementAndCalcPeriod(By.xpath("//div[2]/div/div/div/div/div[2]/div/input"), "");
		clickElementAndCalcPeriod(By.xpath("//div[2]/div/div/div/div/div[2]/div/div/div/span[2]"), "");
		clickElementAndCalcPeriod(By.id("item-AKTA_RODZAJ-0"), "");
		clickElementAndCalcPeriod(By.id("next-task-button"), "");
		Thread.sleep(1000);
		clickElementAndCalcPeriod(By.xpath("//div/div/div/div/div/div/div/div[2]/div/input"), "");
		clickElementAndCalcPeriod(By.xpath("//div/div/div/div/div/div/div/div[2]/div/div/div/span"), "");
		clickElementAndCalcPeriod(By.xpath("//div[2]/div/div/div/div/div/div[2]/div/input"), "");
		clickElementAndCalcPeriod(By.xpath("//div[2]/div/div/div/div/div/div[2]/div/div/div/span"), "");
		clickElementAndCalcPeriod(By.id("next-task-button"), "");
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(3000);
		clickElementAndCalcPeriod(By.xpath("//div[2]/div/div/div/div/div/div[2]/div/input"), "");
		clickElementAndCalcPeriod(By.xpath("//div[2]/div/div/div/div/div/div[2]/div/div/div/span"), "");
		clickElementAndCalcPeriod(By.id("next-task-button"), "");
		Thread.sleep(1000);
		clickElementAndCalcPeriod(By.xpath("//div/div/div/div/div/div/div/div[2]/div/input"), "");
		clickElementAndCalcPeriod(By.xpath("//div/div/div/div/div/div/div/div[2]/div/div/div/span"), "");
		clickElementAndCalcPeriod(By.name("filedata"), "");
		clickElementAndCalcPeriod(By.cssSelector("input[name=\"filedata\"]"), "");
		sendKeysAndCalcPeriod(By.cssSelector("input[name=\"filedata\"]"), "C:\\Users\\mpilarek\\Desktop\\MS.txt", "");
		clickElementAndCalcPeriod(By.id("next-task-button"), "");
		Thread.sleep(1000);
		
		
	}

	
}
