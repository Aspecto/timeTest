package timeTest;


import org.testng.annotations.*;
import static org.testng.Assert.*;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.*;

@Listeners(timeTest.RegisterTestListener.class)		

public class TimeTest extends RegisterBase{
	
	@Test
	public void base_test() throws Exception {
		getDriverAndCalcPeriod(baseUrl + "/", "Czas otwarcia strony(w sekundach)");
		sendKeysAndCalcPeriod(By.id("username"), "zbochenek", "Czas wpisywania loginu(w sekundach)");
		sendKeysAndCalcPeriod(By.id("password"), "Seup123$", "Czas wpisywania hasła(w sekundach)");
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
		/*
		Thread.sleep(1000);
		clickElementAndCalcPeriod(By.name("filedata"), "");
		Thread.sleep(1000);
		Robot robot = new Robot();
		for(char c : path.toCharArray())
		{
			robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(c));
			Thread.sleep(50);
		}
		robot.keyPress(KeyEvent.VK_ENTER);
		*///Uploadowanie pliku
		clickElementAndCalcPeriod(By.id("next-task-button"), "");
		Thread.sleep(1000);
		
		
	}

	
}
