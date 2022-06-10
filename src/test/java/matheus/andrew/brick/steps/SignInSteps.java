package matheus.andrew.brick.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import matheus.andrew.brick.app.model.UserData;
import matheus.andrew.brick.app.page.SignInPage;
import org.springframework.beans.factory.annotation.Autowired;

public class SignInSteps {

    @Autowired
    private SignInPage signInPage;

    @When("I input username and password")
    public void iInputUsernameAndPassword() {
        signInPage.setDriver(Hooks.driver);

        signInPage.setUserData(new UserData());
        signInPage.getUserData().setEmail("matheus.andrew@gmail.com");
        signInPage.getUserData().setPassword("indonesia");
        signInPage.inputCredential();
    }

    @And("I click sign in button")
    public void iClickSignInButton() {
        signInPage.clickLogin();
    }

    @Then("I see login notification")
    public void iSeeLoginNotification() {
        signInPage.validateNotification();
    }

    @Then("I see invalid login notification")
    public void iSeeInvalidLoginNotification() {
        signInPage.validateInvalidNotification();
    }

    @When("I click register page")
    public void iClickRegisterPage() {
        signInPage.setDriver(Hooks.driver);

        signInPage.goToRegisterPage();
    }

    @Then("I see register page")
    public void iSeeRegisterPage() {
        signInPage.validateRegisterPage();
    }

    @When("I click forgot password button")
    public void iClickForgotPasswordButton() {
        signInPage.setDriver(Hooks.driver);

        signInPage.goToForgotPage();
    }

    @Then("I see forgot password page")
    public void iSeeForgotPasswordPage() {
        signInPage.validateForgotPage();
    }
}
