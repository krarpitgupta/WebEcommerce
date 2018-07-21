package Functional_Scenarios;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import App_Functions.ChangeZopperPassword;
import App_Functions.GmailLogin;
import App_Functions.OpenLoginPage;
import BugRegressionSuite.BaseTestBugRegression;
import Page_Objects.AppData;
import Page_Objects.BugRegressionAppConstants;
import Page_Objects.SanitySuiteAppConstants;

import com.relevantcodes.extentreports.LogStatus;

//Test case info:In this test case we are  verifying multiple cases
//In first test case we are verifying connection error should get displayed MY account page on closing internet connection
//In second test  case we are verifying forget password functionality
//Author: Arvind
public class MyAccountScenario extends BaseTestBugRegression {

	public String verifyConnectionErrorOnAllTabs() {
		String tabs = AppData.ConnectionErrortext;
		int i = 1;
		try {
			WebElement element = driver.findElement(By.className(SanitySuiteAppConstants.MyAccountTabsParent_class));
			List<WebElement> elements = element
					.findElements(By.className(SanitySuiteAppConstants.MyAccountTabsChilld_class));
			for (WebElement elemnt : elements) {
				elemnt.click();
				if (verifyElementDisplayedByName(BugRegressionAppConstants.ConnectionErrorPage_name)) {
				} else {
					tabs = tabs + " Tab " + i;
				}
				i++;
				backButton();
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
		return tabs;
	}

	@Test(enabled = false)
	public void verifyConnectionErrorOnMyAccountPage() throws Exception {
		try {
			extentInfoLogs("Open login page");
			OpenLoginPage.openLoginPage();
			extentInfoLogs("Gmail Login Page");
			GmailLogin.gmailLogin();
			Thread.sleep(5000);
			extentInfoLogs("Switch off the wifi");
			hitADBCommandForWiFi("off");
			extentInfoLogs("Verify connection error on my account tabs");
			String err_Tabs = verifyConnectionErrorOnAllTabs();
			Assert.assertEquals(err_Tabs, AppData.ConnectionErrortext);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		} finally {
			extentInfoLogs("Open Wi-Fi connection");
			hitADBCommandForWiFi("on");
		}
	}

	@Test(enabled = false)
	public void forgotPasswordFunctionality() throws Exception {
		boolean flag = false;
		try {
			extentInfoLogs("Open Login Page");
			OpenLoginPage.openLoginPage();
			extentInfoLogs("Click Forgot password link");
			clickId(BugRegressionAppConstants.Forgot_Password_Link_id);
			extentInfoLogs("sending data for email id");
			clickId(BugRegressionAppConstants.Forgot_Email_id);
			sendKeysForID(BugRegressionAppConstants.Forgot_Email_id, AppData.UserId);

			extentInfoLogs("Click submit button");
			clickId(BugRegressionAppConstants.Forgot_Submit_id);
			Thread.sleep(5000);
			extentInfoLogs("Close Appium Session");
			closeAppiumSession();

			extentInfoLogs("Open selenium browser");
			ChangeZopperPassword.openBrowser(AppData.Rediff_URL);

			extentInfoLogs("Rediff login");
			ChangeZopperPassword.rediffLogin();

			ChangeZopperPassword.closeChildWindow();

			extentInfoLogs("Click on mail box link");
			ChangeZopperPassword.openMailBox();

			ChangeZopperPassword.closeChildWindow();

			extentInfoLogs("Click on mail");
			ChangeZopperPassword.clickChangePasswordMail();
			Thread.sleep(5000);

			extentInfoLogs("Click on link in mail");
			ChangeZopperPassword.clickMailLink();

			Thread.sleep(5000);

			extentInfoLogs("Switch to child window");
			ChangeZopperPassword.switchToChildWindow();

			extentInfoLogs("Reset password");
			flag = ChangeZopperPassword.resetPassword();

			extentInfoLogs("Close selenium session");
			ChangeZopperPassword.closeBrowser();

			Assert.assertEquals(flag, true);

			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Test(enabled = false, dependsOnMethods = { "forgotPasswordFunctionality" })
	public void loginWithChangedPassword() throws Exception {
		boolean flag = false;
		try {
			extentInfoLogs("open Login Page");
			OpenLoginPage.openLoginPage();

			extentInfoLogs("Enter Login user name");
			clickId(BugRegressionAppConstants.Login_UserName_Id);
			sendKeysForID(BugRegressionAppConstants.Login_UserName_Id, AppData.UserId);

			extentInfoLogs("Enter Login Password");
			sendKeysForID(BugRegressionAppConstants.Login_Password_Id, AppData.password);

			extentInfoLogs("Click On Login button");
			clickId(BugRegressionAppConstants.Login_Button_id);

			// extentInfoLogs("Open My Account Page");
			// clickClassName(App_Constants.Open_Navigation_Drawer);
			// clickId(App_Constants.SignUpIcon_id);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.name(AppData.UserId)));
			flag = verifyElementDisplayedByName(AppData.UserId);
			Assert.assertEquals(flag, true);
		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Test(enabled = false)
	public void logOutPopupOnMyAccount() throws Exception {
		try {
			extentInfoLogs("Open Login Page");
			OpenLoginPage.openLoginPage();
			extentInfoLogs("Gmail Login Page");
			GmailLogin.gmailLogin();
			Thread.sleep(2000);
			clickId(BugRegressionAppConstants.Logout_button_Prod_id);

			extentInfoLogs("Verifying Logout pop out");
			Assert.assertTrue(findElementById(BugRegressionAppConstants.myAccount_LogOutPopupMessage_Id).isDisplayed());
			logger.log(LogStatus.PASS, "logOutPopupOnMyAccount");

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Test(enabled = true)
	public void forgotPasswordFunctionality_SocialLogin() throws Exception {
		try {
			extentInfoLogs("Open Login Page");
			OpenLoginPage.openLoginPage();
			extentInfoLogs("Click Forgot password link");
			clickId(BugRegressionAppConstants.Forgot_Password_Link_id);
			extentInfoLogs("sending data for email id");
			clickId(BugRegressionAppConstants.Forgot_Email_id);
			sendKeysForID(BugRegressionAppConstants.Forgot_Email_id, AppData.Gmail_MailId);

			extentInfoLogs("Click submit button");
			clickId(BugRegressionAppConstants.Forgot_Submit_id);

			Thread.sleep(2000);

			clickId(BugRegressionAppConstants.Forgot_Password_Link_id);
			extentInfoLogs("sending data for email id");
			clickId(BugRegressionAppConstants.Forgot_Email_id);
			sendKeysForID(BugRegressionAppConstants.Forgot_Email_id, AppData.Facebook_MailId);

			extentInfoLogs("Click submit button");
			clickId(BugRegressionAppConstants.Forgot_Submit_id);

			extentInfoLogs("Close Appium Session");
			closeAppiumSession();

			extentInfoLogs("Open selenium browser");
			ChangeZopperPassword.openBrowser(AppData.Gmail_URL);
			extentInfoLogs("Gmail login");
			ChangeZopperPassword.gMailLogin(AppData.Gmail_MailId, AppData.password);
			extentInfoLogs("Click on received change password link");
			ChangeZopperPassword.clickOnZopperForgotPasswordLink();

			Thread.sleep(5000);

			extentInfoLogs("Switch to child window");
			ChangeZopperPassword.switchToChildWindow();

			extentInfoLogs("Reset zopper password");
			ChangeZopperPassword.changeZopperGmailPassword(AppData.password);

			wDriver.quit();
			
			extentInfoLogs("Open selenium browser");
			ChangeZopperPassword.openBrowser(AppData.Gmail_URL);

			extentInfoLogs("Gmail login");
			ChangeZopperPassword.gMailLogin(AppData.Facebook_MailId, AppData.password);
			extentInfoLogs("Click on received change password link");
			ChangeZopperPassword.clickOnZopperForgotPasswordLink();
			
			Thread.sleep(5000);
			
			extentInfoLogs("Switch to child window");
			ChangeZopperPassword.switchToChildWindow();

			extentInfoLogs("Reset zopper password");
			ChangeZopperPassword.changeZopperGmailPassword(AppData.password);
			
			wDriver.quit();

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}

	@Test(enabled = false)
	public void executeTestCase() throws Exception {
		// TODO Auto-generated method stub

	}

}
