package matheus.andrew.brick.acceptancetests;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features/",
        glue="matheus.andrew.brick",
        stepNotifications = true,
        tags = "@SignIn or @SignUp")
public class AcceptanceTestSuite {}
