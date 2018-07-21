package App_Functions;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import BugRegressionSuite.BaseTestBugRegression;
import Page_Objects.AppData;
import Page_Objects.BugRegressionAppConstants;

//This function is created to handle the Gmail login from Login page
public class ChangeZopperPassword extends BaseTestBugRegression {


	public static void openBrowser(String URL) {
		try {
//			System.setProperty("webdriver.chrome.driver", "E:/chromedriver_win32/chromedriver.exe");
			
			wDriver = new FirefoxDriver();
			wait1= new WebDriverWait(wDriver,10);
//			wDriver=new ChromeDriver();
			wDriver.get(URL);
			wDriver.manage().window().maximize();
			wDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Actions action = new Actions(wDriver);
		} catch (Exception e) {
			e.printStackTrace();
			// takeSnapShot("D:\\takeScreenshots", "openBrowser");
			throw (e);
		}
	}
	

	public static void rediffLogin() throws Exception {
		try {
			wDriver.findElement(By.id(BugRegressionAppConstants.Rediff_LoginUser_id))
					.sendKeys(AppData.UserId);
			wDriver.findElement(By.id(BugRegressionAppConstants.Rediff_ContinueButton_id))
					.click();
			Thread.sleep(1000);
			wDriver.findElement(By.id(BugRegressionAppConstants.Rediff_Password_id))
					.click();
			Thread.sleep(1000);
			wDriver.findElement(By.id(BugRegressionAppConstants.Rediff_Password_id))
					.sendKeys(AppData.password);
			wDriver.findElement(
					By.xpath(BugRegressionAppConstants.Rediff_LoginButton_xpath)).click();
		} catch (Exception e) {
			e.printStackTrace();
			// takeSnapShot("D:\\takeScreenshots", "rediffLogin");
			throw (e);
		}
	}
	
	public static void gMailLogin(String userId,String password) throws Exception {
		try {
			wDriver.findElement(By.id(BugRegressionAppConstants.GMail_User_Id))
					.sendKeys(userId);
			wDriver.findElement(By.id(BugRegressionAppConstants.Gmail_Next_Id))
					.click();
			Thread.sleep(1000);
			wDriver.findElement(By.id(BugRegressionAppConstants.Gmail_Password_Id))
					.sendKeys(password);
			wDriver.findElement(
					By.id(BugRegressionAppConstants.Gmail_SignIn_Button_Id)).click();
		} catch (Exception e) {
			e.printStackTrace();
			// takeSnapShot("D:\\takeScreenshots", "rediffLogin");
			throw (e);
		}
	}
	
	public static void clickOnZopperForgotPasswordLink() throws Exception {
		try {
//			wait1.until(ExpectedConditions.elementToBeClickable(wDriver.findElements(By.xpath(BugRegressionAppConstants.Gmail_MailList_User_Id)).get(0)));
			List<WebElement> mailList=wDriver.findElements(By.xpath(BugRegressionAppConstants.Gmail_MailList_User_Id));
			for(WebElement ele:mailList){
				ele.click();
				break;
			}
			wDriver.findElement(By.partialLinkText(BugRegressionAppConstants.Gmail_ChangePassword_LinkText)).click();
		} catch (Exception e) {
			e.printStackTrace();
			// takeSnapShot("D:\\takeScreenshots", "rediffLogin");
			throw (e);
		}
	}
	
	public static void changeZopperGmailPassword(String password) throws Exception {
		try {
			wait1.until(ExpectedConditions.presenceOfElementLocated(By.id(BugRegressionAppConstants.Gmail_NewPassword_Id)));
			
			wDriver.findElement(By.id(BugRegressionAppConstants.Gmail_NewPassword_Id)).sendKeys(password);
			wDriver.findElement(By.id(BugRegressionAppConstants.Gmail_ConfirmPassword_Id)).sendKeys(password);
			wDriver.findElement(By.id(BugRegressionAppConstants.Gmail_ChangePWDButton)).click();
			Thread.sleep(2000);
			Assert.assertTrue(wDriver.findElement(By.id(BugRegressionAppConstants.Gmail_PwdChangeSuccessMsg_Id)).getText().trim().contains(AppData.ChangePassword_SuccessMsg));
		} catch (Exception e) {
			e.printStackTrace();
			// takeSnapShot("D:\\takeScreenshots", "rediffLogin");
			throw (e);
		}
	}

	public static void openMailBox() {
		try {
			wDriver.findElement(By.xpath(BugRegressionAppConstants.Rediff_MailBox_xpath))
					.click();
		} catch (Exception e) {
			e.printStackTrace();
			// takeSnapShot("D:\\takeScreenshots", "openMailBox");
			throw (e);
		}
	}

	public static void clickChangePasswordMail() {
		try {
//			System.out.println("Mail link function");
			List<WebElement> element = wDriver.findElements(By
					.xpath(BugRegressionAppConstants.Rediff_ResetMail_xpath));
//			System.out.println("list size : " + element);
			for (WebElement ele : element) {
//				System.out.println("Mail name : " + ele.getText());
				ele.click();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// takeSnapShot("D:\\takeScreenshots", "clickChangePasswordMail");
			throw (e);
		}
	}

	public static void clickMailLink() {
		try {
			wDriver.findElement(
					By.xpath(BugRegressionAppConstants.Rediff_ResetLinkInMail_xpath))
					.click();

		} catch (Exception e) {
			e.printStackTrace();
			// takeSnapShot("D:\\takeScreenshots", "clickMailLink");
			throw (e);
		}
	}

	public static void switchToChildWindow() {
		try {
			String parentWindow = wDriver.getWindowHandle();
//			System.out.println("Parent window : " + parentWindow);
			Set<String> handles = wDriver.getWindowHandles();
			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					wDriver.switchTo().window(windowHandle);
//					System.out.println("Child window : " + windowHandle);
					break;
				} else {
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			// takeSnapShot("D:\\takeScreenshots", "switchToChildWindow");
			throw (e);
		}
	}

	public static void closeChildWindow() {
		try {
			String parentWindow = wDriver.getWindowHandle();
//			System.out.println("Parent window : " + parentWindow);
			Set<String> handles = wDriver.getWindowHandles();
			for (String windowHandle : handles) {
				if (!windowHandle.equals(parentWindow)) {
					wDriver.switchTo().window(windowHandle);
					wDriver.close();
				}
			}
			wDriver.switchTo().window(parentWindow);

		} catch (Exception e) {
			e.printStackTrace();
			// takeSnapShot("D:\\takeScreenshots", "switchToChildWindow");
			throw (e);
		}
	}

	public static boolean resetPassword() throws Exception {
		boolean flag = false;
		try {
			wDriver.findElement(By.id(BugRegressionAppConstants.Rediff_NewPassword_id))
					.sendKeys(AppData.password);
			wDriver.findElement(By.id(BugRegressionAppConstants.Rediff_ConfirmPassword_id))
					.sendKeys(AppData.password);
			wDriver.findElement(By.id(BugRegressionAppConstants.Rediff_Submit_id)).click();
			Thread.sleep(5000);
//			System.out.println("Flag value : "
//					+ wDriver.findElement(
//							By.id(App_Constants.Rediff_SuccessMsg_id))
//							.getText());
			if (wDriver.findElement(By.id(BugRegressionAppConstants.Rediff_SuccessMsg_id))
					.getText().trim().contains(AppData.Success_Msg))
				flag = true;

		} catch (Exception e) {
			e.printStackTrace();
			// takeSnapShot("D:\\takeScreenshots", "resetPassword");
			throw (e);
		}
		return flag;
	}

	public static void closeBrowser() {
		wDriver.quit();
	}
	
	public static boolean verifyMailHeader(String sub) {
        boolean flag = false;
        try {
//                System.out.println("Mail link function");
                if (wDriver.findElements(By.xpath("//*[@id='mailList']//span[contains(text(),sub)]")).size() > 0)
                        flag = true;
        } catch (Exception e) {
                e.printStackTrace();
                // takeSnapShot("D:\\takeScreenshots", "clickChangePasswordMail");
                throw (e);
        }
        return flag;
}

	// public static void main(String[] args) throws Exception
	// {
	// openBrowser();
	// rediffLogin();
	// closeChildWindow();
	// openMailBox();
	// closeChildWindow();
	// clickChangePasswordMail();
	// Thread.sleep(5000);
	// clickMailLink();
	// Thread.sleep(5000);
	// switchToChildWindow();
	// resetPassword();
	// closeBrowser();
	//
	// }

	@Test
	public void executeTestCase() throws Exception {
		// TODO Auto-generated method stub

	}

}
