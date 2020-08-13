package parallel;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.EmailException;
import org.openqa.selenium.io.Zip;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import net.masterthought.cucumber.Configuration;

import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;
import util.ZIPFile;


@CucumberOptions(features = "src/test/resources/parallel",
				 glue = "parallel",
				 plugin = {"pretty","html:target/html","json:target/cucumber-report/cucumber.json","rerun:target/rerun.txt",
						   "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class RunCucumberTest {

	TestNGCucumberRunner testNGCucumberRunner;
	@BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
 
    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "scenarios")
    public void feature(PickleEventWrapper eventWrapper, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        testNGCucumberRunner.runScenario(eventWrapper.getPickleEvent());
    }
 
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }
    
	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		testNGCucumberRunner.finish();
	}
	
	@AfterSuite
	public void html_report() {
		File reportOutputDirectory = new File("target");
		List<String> jsonFiles = new ArrayList<>();
		jsonFiles.add("target/cucumber-report/cucumber.json");

		String projectName = "ParallelExecution";

		Configuration configuration = new Configuration(reportOutputDirectory, projectName);
		// optional configuration - check javadoc for details
		configuration.addPresentationModes(PresentationMode.RUN_WITH_JENKINS);
		// do not make scenario failed when step has status SKIPPED
		configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
		// addidtional metadata presented on main page
		configuration.addClassifications("Platform", "Windows");
		configuration.addClassifications("Browser", "Chrome");
		ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
		Reportable result = reportBuilder.generateReports();
	}
	
	@AfterSuite
	public void sendMail() throws EmailException, IOException {
		// Create object of Property file
		Properties props = new Properties(); 
		// this will set host of server- you can change based on your requirement
		props.put("mail.smtp.host", "stbeehive.oracle.com");
		// set the port of socket factory
		props.put("mail.smtp.socketFactory.port", "465");
		// set socket factory
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		// set the authentication to true
		props.put("mail.smtp.auth", "true");
		// set the port of SMTP server
		props.put("mail.smtp.port", "465");
		// This will handle the complete authentication
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("vinod.duddukuri@oracle.com", "WINod1@14");
				}
			});
		try {
			ZIPFile zip = new ZIPFile();
	        File filename = new File("C:\\Users\\VDUDDUKU\\eclipse-workspace\\ParallelExecution\\target\\cucumber-html-reports");
	        zip.zipDirectory(filename, "C:\\Users\\VDUDDUKU\\eclipse-workspace\\ParallelExecution\\target\\cucumber-html-reports.zip");
	        System.out.println("Zipping of CucumberHTMLReports successfully");
			//compress.zip(new File("C:\\Users\\VDUDDUKU\\eclipse-workspace\\ParallelExecution\\test-output"), new File("C:\\Users\\VDUDDUKU\\eclipse-workspace\\ParallelExecution\\report.zip"));
			// Create object of MimeMessage class
			Message message = new MimeMessage(session);
			// Set the from address
			message.setFrom(new InternetAddress("vinod.duddukuri@oracle.com"));
			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("vinod.duddukuri@oracle.com"));
			Date dt = new Date();
		    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		    String strDate= formatter.format(dt);  
			
//			Set subject and body and adding attachments.
			message.setSubject("Execution report on "+strDate);	
			BodyPart messageBodyPart = new MimeBodyPart();	
			messageBodyPart.setText("Hello Vinod, Good day! \n"
					+ "\n"
					+ "All scenarios have been executed. Please find the attached report of the execution.  \n"
					+ "\n"
					+ "Thanks,\n"
					+ "Vinod D");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			File file1 = new File("C:\\Users\\VDUDDUKU\\eclipse-workspace\\ParallelExecution\\target\\HtmlReport\\ExtentReport.html");
			File file2 = new File("C:\\Users\\VDUDDUKU\\eclipse-workspace\\ParallelExecution\\target\\cucumber-report\\cucumber.json");
			File file3 = new File("C:\\Users\\VDUDDUKU\\eclipse-workspace\\ParallelExecution\\target\\cucumber-html-reports.zip");
			messageBodyPart = new MimeBodyPart();
			BodyPart messageBodyPart2 = new MimeBodyPart();
			BodyPart messageBodyPart3 = new MimeBodyPart();
			DataSource source = new FileDataSource(file1.getAbsolutePath());
			DataSource source2 = new FileDataSource(file2.getAbsolutePath());
			DataSource source3 = new FileDataSource(file3.getAbsolutePath());
		    messageBodyPart.setDataHandler(new DataHandler(source));
		    messageBodyPart2.setDataHandler(new DataHandler(source2));
		    messageBodyPart3.setDataHandler(new DataHandler(source3));
		    messageBodyPart.setFileName("ExtentReport.html");
		    messageBodyPart2.setFileName("cucumber.json");
		    messageBodyPart3.setFileName("cucumber-html-reports.zip");
		    multipart.addBodyPart(messageBodyPart);
		    multipart.addBodyPart(messageBodyPart2);
		    multipart.addBodyPart(messageBodyPart3);
		    message.setContent(multipart);
			// finally send the email
			Transport.send(message);
			System.out.println("=====Email Sent=====");

		} catch (MessagingException e) {

			throw new RuntimeException(e);

		}

	}
	
}