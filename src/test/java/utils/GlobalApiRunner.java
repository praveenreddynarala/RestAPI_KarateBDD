package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.intuit.karate.KarateOptions;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

@KarateOptions()
public class GlobalApiRunner {

	@Test
	public void testParallel() {
		System.setProperty("karate.env", "dev");
		System.setProperty("userdir", System.getProperty("user.dir"));

		String karateOutputPath = "target/surefire-reports";
		Results	results = Runner.parallel(getClass(),5,karateOutputPath);
		System.out.println(results.getReportDir());
		generateReport(results.getReportDir());
		boolean skipFailures = Boolean.valueOf(System.getProperty("skipFailures", "false"));
		int failCount = results.getFailCount();
		System.out.println("Skip failures JVM property value "+ skipFailures);
		if(skipFailures && failCount != 0){
			System.out.println("Skipping build features - "+ failCount);
		}else{
			assertEquals(0, failCount, results.getErrorMessages());
		}
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
