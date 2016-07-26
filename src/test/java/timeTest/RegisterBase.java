package timeTest;

import static org.testng.Assert.fail;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class RegisterBase extends SeleniumBase{

	protected void setUpBrowser()
	{
		setUpBrowser(Browser.Firefox,"https://www.rejestrymedyczne.csioz.gov.pl/");
		log.debug("Setting Firefox for https://www.rejestrymedyczne.csioz.gov.pl/");
	}
	
	@BeforeClass(alwaysRun = true)
	@Parameters({ "reportFilePath", "fileOutput", "consoleOutput" })
	public void setUp(String reportFilePath, String fileOutput, String consoleOutput) throws Exception {
		setCustomOutputs(reportFilePath, fileOutput, consoleOutput);
		setUpBrowser();
	}


	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	
}
