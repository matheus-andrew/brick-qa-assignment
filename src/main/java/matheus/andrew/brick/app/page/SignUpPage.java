package matheus.andrew.brick.app.page;

import lombok.Data;
import matheus.andrew.brick.app.Messages;
import matheus.andrew.brick.app.model.UserData;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;

@Data
public class SignUpPage extends PageObject {

    @Autowired
    private UserData userData;

    public void fillData() {
        final String firstName = "//input[@id='firstName']";
        final String lastName = "//input[@id='lastName']";
        final String email = "//input[@id='email']";
        final String phone = "//input[@id='phoneNumber']";
        final String address = "//input[@id='address']";
        final String password = "//input[@id='password']";
        final String confirmPassword = "//input[@id='confirm_password']";

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstName)));
        WebElementFacade txtFirstName = find(By.xpath(firstName));
        txtFirstName.type(userData.getFirstName());

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(lastName)));
        WebElementFacade txtLastName = find(By.xpath(lastName));
        txtLastName.type(userData.getLastName());

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(email)));
        WebElementFacade txtEmail = find(By.xpath(email));
        txtEmail.type(userData.getEmail());

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(phone)));
        WebElementFacade txtPhone = find(By.xpath(phone));
        txtPhone.type(userData.getPhone());

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(address)));
        WebElementFacade txtAddress = find(By.xpath(address));
        txtAddress.type(userData.getAddress());

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(password)));
        WebElementFacade txtPassword = find(By.xpath(password));
        txtPassword.type(userData.getPassword());

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(confirmPassword)));
        WebElementFacade txtConfirmPassword = find(By.xpath(confirmPassword));
        txtConfirmPassword.type(userData.getConfirmPassword());
    }

    public void clickRegister() {
        final String register = "//input[@name='register']";

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(register)));
        WebElementFacade btnRegister = find(By.xpath(register));
        btnRegister.click();
    }

    public void validateNotification(String notif) {
        final String banner = "//*[@role='dialog']";
        final String title = "//*[@class='swal2-title']";
        final String message = "//*[@id='swal2-content']";
        final String ok = "//*[@class='swal2-confirm swal2-styled']";

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(banner)));
        WebElementFacade txtBanner = find(By.xpath(banner));
        assertThat("banner is not yet displayed", txtBanner.isDisplayed());

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(title)));
        WebElementFacade txtTitle = find(By.xpath(title));

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(message)));
        WebElementFacade txtMessage = find(By.xpath(message));

        if (notif.equalsIgnoreCase("success")) {
            assertThat("notification message is mismatch",
                    txtTitle.getText().equalsIgnoreCase("Success"));
            assertThat("notification message is mismatch",
                    txtMessage.getText().equalsIgnoreCase(Messages.m1));
        } else if (notif.equalsIgnoreCase("error")) {
            assertThat("notification message is mismatch",
                    txtTitle.getText().equalsIgnoreCase("Error"));
            assertThat("notification message is mismatch",
                    txtMessage.getText().equalsIgnoreCase(Messages.m2));
        }

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(ok)));
        WebElementFacade btnOk = find(By.xpath(ok));
        btnOk.click();
    }

    public void validateLabel() {
        final String firstName = "//*[@id='firstName-error']";
        final String lastName = "//*[@id='lastName-error']";
        final String email = "//*[@id='email-error']";
        final String phone = "//*[@id='phoneNumber-error']";
        final String password = "//*[@id='password-error']";
        final String confirmPassword = "//*[@id='confirm_password-error']";

        if (userData.getFirstName().equalsIgnoreCase("")) {
            waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(firstName)));
            WebElementFacade txtFirstName = find(By.xpath(firstName));
            assertThat("label message is mismatch",
                    txtFirstName.getText().equalsIgnoreCase("Please enter a firstname"));
        }

        if (userData.getLastName().equalsIgnoreCase("")) {
            waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(lastName)));
            WebElementFacade txtLastName = find(By.xpath(lastName));
            assertThat("label message is mismatch",
                    txtLastName.getText().equalsIgnoreCase("Please enter a lastname"));
        }

        if (userData.getEmail().equalsIgnoreCase("")) {
            waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(email)));
            WebElementFacade txtEmail = find(By.xpath(email));
            assertThat("label message is mismatch",
                    txtEmail.getText().equalsIgnoreCase("Please provide an email"));
        } else {
            waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(email)));
            WebElementFacade txtEmail = find(By.xpath(email));

            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(txtEmail.getText());

            if (!matcher.matches()) {
                assertThat("label message is mismatch",
                        txtEmail.getText().equalsIgnoreCase("Please enter a valid email address."));
            }
        }

        if (userData.getPhone().equalsIgnoreCase("")) {
            waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(phone)));
            WebElementFacade txtPhone = find(By.xpath(phone));
            assertThat("label message is mismatch",
                    txtPhone.getText().equalsIgnoreCase("This field is required."));
        }

        if (userData.getPassword().equalsIgnoreCase("")) {
            waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(password)));
            WebElementFacade txtPassword = find(By.xpath(password));
            assertThat("label message is mismatch",
                    txtPassword.getText().equalsIgnoreCase("Please enter a password"));
        } else if (userData.getPassword().length() < 6) {
            waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(password)));
            WebElementFacade txtPassword = find(By.xpath(password));
            assertThat("label message is mismatch",
                    txtPassword.getText().equalsIgnoreCase("Please enter at least 6 characters."));
        }

        if (userData.getConfirmPassword().equalsIgnoreCase("")) {
            waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(confirmPassword)));
            WebElementFacade txtConfirmPassword = find(By.xpath(confirmPassword));
            assertThat("label message is mismatch",
                    txtConfirmPassword.getText().equalsIgnoreCase("Please enter a password"));
        } else if (!userData.getConfirmPassword().equalsIgnoreCase(userData.getPassword())) {
            waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(confirmPassword)));
            WebElementFacade txtConfirmPassword = find(By.xpath(confirmPassword));
            assertThat("label message is mismatch",
                    txtConfirmPassword.getText().equalsIgnoreCase("Password need to match"));
        }
    }

    public void deleteCurrentRegisteredUser() {
        final String url = "//*[@name='url']";
        final String connect = "//*[@type='submit']";
        final String sql = "//form[@name='h2query']//*[@id='sql']";
        final String run = "//*[@value='Run']";

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(url)));
        WebElementFacade txtUrl = find(By.xpath(url));
        txtUrl.type("jdbc:h2:mem:testdb");

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(connect)));
        WebElementFacade btnConnect = find(By.xpath(connect));
        btnConnect.click();

        waitFor(ExpectedConditions.frameToBeAvailableAndSwitchToIt("h2query"));
        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(sql)));
        WebElementFacade txtSql = find(By.xpath(sql));
        txtSql.type("DELETE FROM REGISTRATION;");

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(run)));
        WebElementFacade btnRun = find(By.xpath(run));
        btnRun.click();
    }

    public void goToLoginPage() {
        final String login = "//a[.='Login']";

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(login)));
        WebElementFacade btnLogin = find(By.xpath(login));
        btnLogin.click();
    }

    public void validateLoginPage() {
        final String login = "//*[@class='login']";

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(login)));
        WebElementFacade txtLogin = find(By.xpath(login));
        assertThat("mismatch page", txtLogin.getText().equalsIgnoreCase("Login"));
    }

    public String clickContactSales() {
        final String contact = "//a[contains(.,'Contact sales')]";

        waitFor(ExpectedConditions.visibilityOfElementLocated(By.xpath(contact)));
        WebElementFacade btnContact = find(By.xpath(contact));
        return btnContact.getAttribute("href");
    }
}
