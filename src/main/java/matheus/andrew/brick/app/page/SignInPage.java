package matheus.andrew.brick.app.page;

import lombok.Data;
import matheus.andrew.brick.app.model.UserData;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;

@Data
public class SignInPage extends PageObject {

    @Autowired
    private UserData userData;

    public void inputCredential() {
        final String email = "//*[@id='your_email']";
        final String password = "//*[@id='password']";

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(email)));
        WebElementFacade txtEmail = find(By.xpath(email));
        txtEmail.type(userData.getEmail());

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(password)));
        WebElementFacade txtPassword = find(By.xpath(password));
        txtPassword.type(userData.getPassword());
    }

    public void clickLogin() {
        final String login = "//input[@name='login']";

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(login)));
        WebElementFacade btnLogin = find(By.xpath(login));
        btnLogin.click();
    }

    public void validateNotification() {
        final String banner = "//*[@class='swal2-popup swal2-modal swal2-icon-success swal2-show']";
        final String title = "//*[@id='swal2-title']";
        final String ok = "//*[@class='swal2-confirm swal2-styled']";

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(banner)));
        WebElementFacade txtBanner = find(By.xpath(banner));
        assertThat("banner is not yet displayed", txtBanner.isDisplayed());

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(title)));
        WebElementFacade txtTitle = find(By.xpath(title));
        assertThat("notification message is mismatch",
                txtTitle.getText().equalsIgnoreCase("Success"));

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(ok)));
        WebElementFacade btnOk = find(By.xpath(ok));
        btnOk.click();
    }

    public void validateInvalidNotification() {
        final String banner = "//*[@class='swal2-popup swal2-modal swal2-icon-error swal2-show']";
        final String title = "//*[@id='swal2-title']";
        final String message = "//*[@id='swal2-content']";
        final String ok = "//*[@class='swal2-confirm swal2-styled']";

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(banner)));
        WebElementFacade txtBanner = find(By.xpath(banner));
        assertThat("banner is not yet displayed", txtBanner.isDisplayed());

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(title)));
        WebElementFacade txtTitle = find(By.xpath(title));
        assertThat("notification title is mismatch",
                txtTitle.getText().equalsIgnoreCase("Error"));

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(message)));
        WebElementFacade txtMessage = find(By.xpath(message));
        assertThat("notification message is mismatch",
                txtMessage.getText().equalsIgnoreCase("Wrong email or password"));

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(ok)));
        WebElementFacade btnOk = find(By.xpath(ok));
        btnOk.click();
    }

    public void goToRegisterPage() {
        final String register = "//a[.='Sign Up']";

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(register)));
        WebElementFacade btnRegister = find(By.xpath(register));
        btnRegister.click();
    }

    public void validateRegisterPage() {
        final String register = "//*[.='Sign Up']";

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(register)));
        WebElementFacade txtRegister = find(By.xpath(register));
        assertThat("mismatch page", txtRegister.getText().equalsIgnoreCase("Sign Up"));
    }

    public void goToForgotPage() {
        final String forgot = "//a[.='Forgot Password?']";

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(forgot)));
        WebElementFacade btnForgot = find(By.xpath(forgot));
        assertThat("mismatch link",
                btnForgot.getAttribute("href").equalsIgnoreCase("https://brick-qa-assignment.herokuapp.com/forgot"));
        btnForgot.click();
    }

    public void validateForgotPage() {
        final String forgot = "//h1";

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(forgot)));
        WebElementFacade txtForgot = find(By.xpath(forgot));
        assertThat("mismatch page",
                txtForgot.getText().equalsIgnoreCase("We couldn't find what you were looking for."));
    }
}
