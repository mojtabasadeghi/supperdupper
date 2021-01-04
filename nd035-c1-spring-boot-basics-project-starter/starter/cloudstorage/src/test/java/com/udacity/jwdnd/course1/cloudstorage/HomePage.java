package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    @FindBy (id="logoutbtn")
    private WebElement logoutbtn;

    @FindBy (id="nav-files-tab")
    private WebElement navfilestab;
    @FindBy (id="nav-notes-tab")
    private WebElement navnotestab;
    @FindBy (id="nav-credentials-tab")
    private WebElement navcredentialstab;

    //File tab
    @FindBy (id="fileUpload")
    private WebElement fileUpload;
    @FindBy (id="uploadBtn")
    private WebElement uploadBtn;

    //Note tab
    @FindBy (id="add-new-note")
    private WebElement addnewnote;
    @FindBy (id="note-title")
    private WebElement notetitle;
    @FindBy (id="note-description")
    private WebElement notedescription;
    @FindBy (id="noteSubmit")
    private WebElement noteSubmit;

    //Credential tab
    @FindBy (id="add-new-credential")
    private WebElement addnewcredential;
    @FindBy (id="credential-url")
    private WebElement credentialurl;
    @FindBy (id="credential-username")
    private WebElement credentialusername;
    @FindBy (id="credential-password")
    private WebElement credentialpassword;
    @FindBy (id="credentialSubmit")
    private WebElement credentialSubmit;

    public WebElement getlogoutbtn() {
        return logoutbtn;
    }

    public void setlogoutbtn(WebElement logout) {
        this.logoutbtn = logout;
    }

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void logOut()
    {
        logoutbtn.click();
    }
}
