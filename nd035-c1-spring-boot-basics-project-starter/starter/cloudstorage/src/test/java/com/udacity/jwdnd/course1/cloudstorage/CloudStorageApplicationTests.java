package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private String baseURL;
	private UserMapper userMapper;
	private WebDriverWait wait;
	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
		baseURL = "http://localhost:" + port;
	}

	@Test
	void UnauthorizedAccesstest()
	{
		driver.get(baseURL+"/home");
		assertEquals(baseURL+"/login",driver.getCurrentUrl());

		driver.get(baseURL+"/home/notexist");
		assertEquals(baseURL+"/login",driver.getCurrentUrl());

		driver.get(baseURL+"/notexist");
		assertEquals(baseURL+"/login",driver.getCurrentUrl());

		driver.get(baseURL+"/home/note/delete/2");
		assertEquals(baseURL+"/login",driver.getCurrentUrl());

		driver.get(baseURL+"/home/credential/delete/2");
		assertEquals(baseURL+"/login",driver.getCurrentUrl());

		driver.get(baseURL+"/home/credential/delete/2");
		assertEquals(baseURL+"/login",driver.getCurrentUrl());

	}

	@Test
	void signupNewUserAndLogoutTest()
	{
		User user=new User(null,"testuser","","testpasword","testfirstname","testLastname");
	//	userMapper.deleteuser(user.getUsername());

		driver.get(baseURL+"/signup");
		SignUpPage signUpPage=new SignUpPage(driver);
		signUpPage.setUpNewUser(user);


		driver.get(baseURL+"/login");
		LoginPage loginPage=new LoginPage(driver);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(loginPage.getSubmitbutton()));
		loginPage.login(user);



		HomePage homePage =new HomePage(driver);
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(homePage.getlogoutbtn())).click();
		//homePage.logOut();
		//driver.get(baseURL+"/login");


		assertEquals("Login",driver.getTitle());

		driver.get(baseURL+"/home");
		assertEquals("Login",driver.getTitle());


	}
	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

}
