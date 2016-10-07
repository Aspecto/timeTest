package timeTest;

import static org.testng.Assert.fail;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class RegisterBase extends SeleniumBase{

	protected String path = "C:\\TEST\\asfa.txt";

	protected void setUpBrowser()
	{
		setUpBrowser(Browser.Firefox,"https://sso.dev.seup.corp.ialbatros.net/login?service=https%3A%2F%2Fdev.seup.corp.ialbatros.net#!/");
		log.debug("Setting Firefox for https://sso.dev.seup.corp.ialbatros.net/login?service=https%3A%2F%2Fdev.seup.corp.ialbatros.net#!/");
	}
	
	@BeforeClass(alwaysRun = true)
	@Parameters({ "reportFilePath", "fileOutput", "consoleOutput", "operationNames"})
	public void setUp(String reportFilePath, String fileOutput, String consoleOutput, String operationNames) throws Exception {
		setCustomOutputs(reportFilePath, fileOutput, consoleOutput, operationNames);
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
