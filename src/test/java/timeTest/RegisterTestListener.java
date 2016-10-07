package timeTest;
import org.testng.ITestContext ;		
import org.testng.ITestListener ;		
import org.testng.ITestResult ;		


public class RegisterTestListener extends RegisterBase implements ITestListener				{

	public void onTestStart(ITestResult result) {
			log.debug("Rozpoczynam test " + result.getName() + ":\n");
			if(consoleOutputEnabled)
				System.out.println("Rozpoczynam test " + result.getName() + ":\n");
	}

	public void onTestSuccess(ITestResult result) {
		testFinished(result, "powodzeniem");
	}

	public void onTestFailure(ITestResult result) {
		testFinished(result, "niepowodzeniem");
	}

	public void onTestSkipped(ITestResult result) {
		testFinished(result, "przerwaniem");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		testFinished(result, "niepowodzeniem z annotacją successPercentage");
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	//	log.debug(context);
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	//	log.debug(context);
	}




}
