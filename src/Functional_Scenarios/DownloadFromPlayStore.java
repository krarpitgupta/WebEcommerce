package Functional_Scenarios;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import App_Functions.ChangeZopperPassword;
import BugRegressionSuite.BaseTestBugRegression;
import Page_Objects.AppData;
import Page_Objects.BugRegressionAppConstants;

public class DownloadFromPlayStore extends BaseTestBugRegression {

	public static void installAppFromPlayStore() throws Exception {

		try {
			extentInfoLogs("Opening playstore url");
			ChangeZopperPassword.openBrowser(AppData.playStore_URL);

			extentInfoLogs("clicking on android app on plays store");
			wDriver.findElement(By.id(BugRegressionAppConstants.Play_Store_AndroidApp_Download_id)).click();
			extentInfoLogs("click on sign in button");
			wDriver.findElement(By.xpath(BugRegressionAppConstants.Play_Store_SignIn_xpath)).click();
			extentInfoLogs("enter email id");
			wDriver.findElement(By.id(BugRegressionAppConstants.Play_Store_Email_id)).sendKeys(AppData.PlayStore_Email);
			extentInfoLogs("click on next button");
			wDriver.findElement(By.id(BugRegressionAppConstants.Play_Store_Next_id)).click();
			Thread.sleep(5000);
			extentInfoLogs("enter password");
			wDriver.findElement(By.id(BugRegressionAppConstants.Play_Store_Password_id))
					.sendKeys(AppData.PlayStore_Password);
			extentInfoLogs("Click on login login button");
			wDriver.findElement(By.id(BugRegressionAppConstants.Play_Store_Login_id)).click();
			extentInfoLogs("click on installed button");
			wDriver.findElement(By.xpath(BugRegressionAppConstants.Play_Store_Installed_xpath)).click();
			Thread.sleep(2000);
			extentInfoLogs("click on pay store dropdown");
			wDriver.findElement(By.xpath(BugRegressionAppConstants.Play_Store_DropDown_xpath)).click();
			extentInfoLogs("Get device name");
			String deviceName = getDeviceName();
			extentInfoLogs("Select device name");
			wDriver.findElement(By.xpath("//span[@class='device-title' and contains(text(),'" + deviceName + "')]"))
					.click();
			extentInfoLogs("Click on install");
			Thread.sleep(2000);
			extentInfoLogs("click on install button");
			wDriver.findElement(By.id(BugRegressionAppConstants.Play_Store_InstallButton_id)).click();
			extentInfoLogs("Wait for congratulation message");
			wait = new WebDriverWait(wDriver, 60);
			extentInfoLogs("click on ok button");
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath(BugRegressionAppConstants.Play_Store_CongratulationMessage_xpath)));
			extentInfoLogs("click on ok button");
			wDriver.findElement(By.id(BugRegressionAppConstants.Play_Store_CongratulationMessage_Ok_id)).click();
			extentInfoLogs("close browser");
			ChangeZopperPassword.closeBrowser();
			Thread.sleep(12000);

		} catch (Exception e) {
			System.out.println(e.toString());
			// throw(e);
		}

	}

	@Test(enabled = true, priority = 0)
	public void verifyPlayStoreAppInstallationFunctionality() throws Exception {

		try {
			extentInfoLogs("close appium session");
			closeAppiumSession();

			extentInfoLogs("uninstall consumer app");
			Runtime.getRuntime().exec("adb uninstall com.zopperapp");

			extentInfoLogs("Calling instsall app from playstore function");
			installAppFromPlayStore();

		} catch (Exception e) {
			System.out.println(e.toString());
			throw (e);
		}
	}

	@Test(enabled = true, dependsOnMethods = { "verifyPlayStoreAppInstallationFunctionality" }, priority = 1)
	public void verifyInstalledApp() throws Exception {
		boolean flag = false;
		try {
			extentInfoLogs("Open Notifications");
			driver.openNotifications();
			extentInfoLogs("Verify intalled app notificaation");
			for (int i = 0; i < 3; i++) {
				List<WebElement> element = driver
						.findElements(By.className(BugRegressionAppConstants.Mobile_Notification_Class));
				for (WebElement ele : element) {
					System.out.println(ele.getText());
					if (ele.getText().trim().equalsIgnoreCase(AppData.InstalledApp_Notification)) {
						flag = true;
						break;
					}
				}
				swipeVertically_FullPage();
			}
			extentInfoLogs("Assert for installed app notification");
			Assert.assertEquals(flag, true);
		} catch (Exception e) {
			System.out.println(e.toString());
			throw (e);
		}
	}

	@Override
	@Test(enabled = false)
	public void executeTestCase() throws Exception {
		// TODO Auto-generated method stub

	}

}
