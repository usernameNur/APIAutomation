package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        plugin="html:target/reports/cucumber.html",
        features = "src/test/resources",
        glue = "steps",
        tags = "@createProduct",
        dryRun=true

)

public class CucumberRunner extends AbstractTestNGCucumberTests {
 @Override
    @DataProvider(parallel = true)
    public Object[][]scenarios(){
     return super.scenarios();
 }


}
