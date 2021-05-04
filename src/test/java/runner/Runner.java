package runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json","rerun:target/rerun.txt"},
        features = "src/test/resources/features",
        glue = "steps",
        tags = "@regression",
        // Test UI regression test cases. -> @regression and @ui
        // run only those feature files that has either @HR-5 or @HR-6 tag.
        // @HR-5 and @HR-6 -> run only those features files that has both @HR-5 and @HR-6 tags.
        dryRun = false
)
public class Runner {
}