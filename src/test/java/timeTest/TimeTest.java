package timeTest;


import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;

@Listeners(timeTest.RegisterTestListener.class)		

public class TimeTest extends RegisterBase{
	
	@Test
	public void base_test() throws Exception {
		getDriverAndCalcPeriod(baseUrl + "/", "Czas otwarcia strony(w sekundach)");
		assertEquals(getTitle(), "CSIOZ - Rejestry medyczne");
		clickElementAndCalcPeriod(By.xpath("//a[2]/div"), "Czas otwarcia rejestru(w sekundach)");
		assertEquals(getTitle(), "Rejestr Podmiotów Wykonujących Działalność Leczniczą - Strona główna");
		clickElementAndCalcPeriod(By.linkText("Wyszukiwarki"), "Czas otwarcia wyboru wyszukiwarki(w sekundach)");
		assertEquals(findElement(By.linkText("Wyszukiwarka Podmiotów Leczniczych")).getText(), "Wyszukiwarka Podmiotów Leczniczych");
		clickElementAndCalcPeriod(By.linkText("Wyszukiwarka Podmiotów Leczniczych"),
				"Czas wybrania wyszukiwarki(w sekundach)");
		assertEquals(findElement(By.cssSelector("h1")).getText(), "Wyszukiwanie Podmiotów Leczniczych");
		selectElementByVisibleTextAndCalcPeriod(By.id("InstitutionId"), "Wojewoda Dolnośląski",
				"Czas wybrania instytucji(w sekundach)");
		assertEquals(getFirstSelectedOptionText(By.id("InstitutionId")),"Wojewoda Dolnośląski");
		clickElementAndCalcPeriod(By.xpath("//button[@type='submit']"), "Czas wyszukiwania(w sekundach)");
		assertEquals(findElement(By.cssSelector("h1")).getText(), "Lista ksiąg rejestrowych");
		clickElementAndCalcPeriod(By.linkText("»»"), "Czas otwarcia ostatniej strony(w sekundach)");
		assertEquals(findElement(By.xpath("//li[11]/a")).getText(),findElement(By.xpath("//li[@class=\"active\"]")).getText());
		clickElementAndCalcPeriod(By.xpath("(//a[contains(text(),'Wyświetl')])[last()]"),
				"Czas otwarcia ostatniego podmiotu(w sekundach)");
		assertEquals(findElement(By.cssSelector("h1")).getText(), "Podgląd księgi rejestrowej");
		clickElementAndCalcPeriod(By.linkText("Wyszukiwarki"), "Czas otwarcia wyboru wyszukiwarki(w sekundach)");
		assertEquals(findElement(By.linkText("Wyszukiwarka Podmiotów Leczniczych")).getText(), "Wyszukiwarka Podmiotów Leczniczych");
		clickElementAndCalcPeriod(By.linkText("Wyszukiwarka Podmiotów Leczniczych"),
				"Czas wybrania wyszukiwarki(w sekundach)");
		assertEquals(findElement(By.cssSelector("h1")).getText(), "Wyszukiwanie Podmiotów Leczniczych");
		selectElementByVisibleTextAndCalcPeriod(By.id("InstitutionId"), "Wojewoda Dolnośląski",
				"Czas wybrania instytucji(w sekundach)");
		assertEquals(getFirstSelectedOptionText(By.id("InstitutionId")),"Wojewoda Dolnośląski");
		clickElementAndCalcPeriod(By.xpath("//button[@type='submit']"), "Czas wyszukiwania(w sekundach)");
		assertEquals(findElement(By.cssSelector("h1")).getText(), "Lista ksiąg rejestrowych");
		clickElementAndCalcPeriod(By.linkText("2"), "Czas wybrania drugiej strony(w sekundach)");
		assertEquals(findElement(By.xpath("//a[contains(text(),'2')]")).getText(),findElement(By.xpath("//li[@class=\"active\"]")).getText());
		clickElementAndCalcPeriod(By.xpath("(//a[contains(text(),'Wyświetl')])[1]"), "Czas otwarcia podmiotu(w sekundach)");
		assertEquals(findElement(By.cssSelector("h1")).getText(), "Podgląd księgi rejestrowej");
	}

	
}
