package testpackage;




import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.intuit.karate.junit5.Karate;
import com.intuit.karate.junit5.Karate.Test;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;


public class TestApiRunner {


	@Test
	Karate testUsers() { 
		System.setProperty("karate.env", "dev");
		System.setProperty("userdir", System.getProperty("user.dir"));
		//System.setProperty("karate.requestBodyFile", "sample.json"); 
		Results	results = Runner.parallel(getClass(),1,"target/surefire-reports");
		generateReport(results.getReportDir());
		assertTrue(results.getErrorMessages(), results.getFailCount() == 0); 
		Karate a = null;
		return a; 
	}
	

	public static void generateReport(String karateOutputPath) {        
		Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
		final List<String> jsonPaths = new ArrayList(jsonFiles.size());
		jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
		Configuration config = new Configuration(new File("target"), "testapi");
		ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
		reportBuilder.generateReports();        
	}
}
