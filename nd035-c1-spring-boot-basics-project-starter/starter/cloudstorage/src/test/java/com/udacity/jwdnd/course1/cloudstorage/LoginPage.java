package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id="inputUsername")
    private WebElement inputUsername;

    @FindBy(id="inputPassword")
    private WebElement inputPassword;

    @FindBy(id="submit-button")
    private WebElement submitbutton;



    @FindBy (id="signup-link")
    private WebElement signuplink;

    @FindBy (id="error-msg")
    private WebElement errormsg;

    @FindBy (id="logopu-msg")
    private WebElement logopumsg;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getSubmitbutton() {
        return submitbutton;
    }

    public void setSubmitbutton(WebElement submitbutton) {
        this.submitbutton = submitbutton;
    }

    public void login(User user)
    {
        inputUsername.sendKeys(user.getUsername());
        inputPassword.sendKeys(user.getPassword());
        submitbutton.click();
    }
}
