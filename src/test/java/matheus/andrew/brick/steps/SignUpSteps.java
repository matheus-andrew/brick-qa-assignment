package matheus.andrew.brick.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import matheus.andrew.brick.app.model.UserData;
import matheus.andrew.brick.app.page.SignUpPage;
import matheus.andrew.brick.app.property.ValueHelper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SignUpSteps {

    @Autowired
    private SignUpPage signUpPage;

    @Given("I go to Brick page")
    public void iGoToBrickPage() {
        signUpPage.setDriver(Hooks.driver);
        signUpPage.openAt("https://brick-qa-assignment.herokuapp.com/");
    }

    @When("I fill all valid data")
    public void iFillAllValidData() {
        signUpPage.setUserData(new UserData());
        signUpPage.getUserData().setFirstName("matheus");
        signUpPage.getUserData().setLastName("andrew");
        signUpPage.getUserData().setEmail("matheus.andrew@gmail.com");
        signUpPage.getUserData().setPhone("087887788047");
        signUpPage.getUserData().setAddress("jakarta");
        signUpPage.getUserData().setPassword("indonesia");
        signUpPage.getUserData().setConfirmPassword("indonesia");

        signUpPage.fillData();
    }

    @And("I click sign up button")
    public void iClickSignUpButton() {
        signUpPage.clickRegister();
    }

    @Then("I see {string} notification")
    public void iSeeNotification(String notif) {
        signUpPage.validateNotification(notif);
    }

    @When("I input invalid data {string} {string} {string} {string} {string} {string}")
    public void iInputInvalidData(String firstName, String lastName, String email, String phone, String pass, String confirmPass) {
        signUpPage.setUserData(new UserData());
        signUpPage.getUserData().setFirstName(ValueHelper.setString(firstName));
        signUpPage.getUserData().setLastName(ValueHelper.setString(lastName));
        signUpPage.getUserData().setEmail(ValueHelper.setString(email));
        signUpPage.getUserData().setPhone(ValueHelper.setString(phone));
        signUpPage.getUserData().setAddress("Indonesia");
        signUpPage.getUserData().setPassword(ValueHelper.setString(pass));
        signUpPage.getUserData().setConfirmPassword(ValueHelper.setString(confirmPass));

        signUpPage.fillData();
    }

    @Then("I see error label")
    public void iSeeErrorLabel() {
        signUpPage.validateLabel();
    }

    @And("Delete current registered user")
    public void deleteCurrentRegisteredUser() {
        signUpPage.openAt("http://brick-qa-assignment.herokuapp.com/h2");
        signUpPage.deleteCurrentRegisteredUser();
    }

    @When("I click login page")
    public void iClickLoginPage() {
        signUpPage.goToLoginPage();
    }

    @Then("I see login page")
    public void iSeeLoginPage() {
        signUpPage.validateLoginPage();
    }

    @When("I click contact sales button")
    public void iClickContactSalesButton() {
        signUpPage.clickContactSales();
    }

    @Then("I will be directed to mail sales")
    public void iWillBeDirectedToMailSales() {
        String href = signUpPage.clickContactSales();

        assertThat("email is mismatch", href,
                equalTo("mailto:sales@onebrick.io?subject=Interested%20in%20Brick%20for%20Enterprise&body=Please%20tell%20us%20more%20about%20how%20we%20can%20help%2C%20What%20are%20you%20building%2C%20What%27s%20the%20estimated%20end%20users%2C%20Details%20are%20helpful"));
    }
}
