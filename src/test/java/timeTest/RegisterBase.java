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
	@Parameters({ "reportFilePath", "fileOutput", "consoleOutput", "operationNames", "countedOperations"})
	public void setUp(String reportFilePath, String fileOutput, String consoleOutput, String operationNames, int countedOperations) throws Exception {
		setCustomOutputs(reportFilePath, fileOutput, consoleOutput, operationNames, countedOperations);
		setUpBrowser();
	}


	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		output.close();
		quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	
}
