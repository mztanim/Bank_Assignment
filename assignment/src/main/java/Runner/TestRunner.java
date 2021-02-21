package Runner;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/FeatureFiles/Amazon_ProductSearch.feature",
        glue = {"StepDefinition"},
        plugin = {"pretty","html:cucumber-reports/report.html"})
public class TestRunner {


}
