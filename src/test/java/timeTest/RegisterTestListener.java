package timeTest;
import java.io.IOException;
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
		log.debug(result.getName()+" zakończony powodzeniem\n\nCzas trwania testu: "+printPeriod(result.getEndMillis()-result.getStartMillis()));
		if(consoleOutputEnabled)
		{
			printToConsole();
			System.out.println(result.getName()+" zakończony powodzeniem\n\nCzas trwania testu: "+printPeriod(result.getEndMillis()-result.getStartMillis()));
		}
		try {
			if(fileOutputEnabled)
			{
				printToFile(result.getName() , "powodzeniem", result.getEndMillis()-result.getStartMillis());
			}
		} catch (IOException e) {
			log.debug(result.getName() + " " + e.getMessage());
		}
	}

	public void onTestFailure(ITestResult result) {
		log.debug("Test zakończony niepowodzeniem\n\nCzas trwania testu: "+printPeriod(result.getEndMillis()-result.getStartMillis()));
		if(consoleOutputEnabled)
		{
			printToConsole();
			System.out.println("Test zakończony niepowodzeniem\n\nCzas trwania testu: "+printPeriod(result.getEndMillis()-result.getStartMillis()));
		}
		try {
			if(fileOutputEnabled)
			{
				printToFile(result.getName() , "niepowodzeniem", result.getEndMillis()-result.getStartMillis());
			}
		} catch (IOException e) {
			log.debug(result.getName() + " " + e.getMessage());
		}
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	//	log.debug(result);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	//	log.debug(result);
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	//	log.debug(context);
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		try {
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	log.debug(context);
	}




}
