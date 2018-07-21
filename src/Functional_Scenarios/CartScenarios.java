package Functional_Scenarios;

import org.testng.Assert;
import org.testng.annotations.Test;

import App_Functions.AddToCart;
import App_Functions.GmailLogin;
import App_Functions.OpenAndroidPage;
import App_Functions.OpenHomePage;
import BugRegressionSuite.BaseTestBugRegression;
import Page_Objects.AppVerificationChecks;
import Page_Objects.BugRegressionAppConstants;

//Test case info:In this test case we are  verifying multiple cases
//In first test case we are verifying store details on Cart
//In second test case we are verifying cart should get empty on changing location
//Author : Argneshu Gupta

public class CartScenarios extends BaseTestBugRegression {

	@Override
	@Test(enabled = true, priority = 1)
	public void executeTestCase() throws Exception {
		try {
			extentInfoLogs("Open Home page");
			OpenHomePage.openHomePage();
			extentInfoLogs("open navigation drawer");
			clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
			info("open navigation drawer");
			extentInfoLogs("click on Mobiles and Tablets link");
			OpenHomePage
					.clickOnCategoryByName(BugRegressionAppConstants.NavDrawer_Mobile_tablets_link);
			info("click on Mobile and tablets link");
			
			extentInfoLogs("Open Android phone category");
			clickName(BugRegressionAppConstants.AndroidPhones_name);
			extentInfoLogs("click On First Android Product");
			OpenAndroidPage.clickOnFirstAndroidProduct();
			extentInfoLogs("click on Buy Now button ");
			clickXpath(BugRegressionAppConstants.ProductDetailsVarBuyNow_xpath);
			
			info("clicking on Buy Now button");
			AddToCart.addToCart();
			extentInfoLogs("verifying assertion for login screen");
			Assert.assertEquals(
					findElementByName(BugRegressionAppConstants.Login_text)
							.getText(), AppVerificationChecks.Logintext);
			info("verifying assertion");
			extentInfoLogs("Login to Gmail");
			GmailLogin.gmailLogin();
			extentInfoLogs("Verifying assertion for store locality");
			boolean storeLocalityDisplayed = findElementById(
					BugRegressionAppConstants.StoreLocality_id).isDisplayed();
			Assert.assertEquals(storeLocalityDisplayed, true);
			boolean storeTitleDisplayed = findElementById(
					BugRegressionAppConstants.StoreTitle_id).isDisplayed();
			extentInfoLogs("Verifying assertion for store title");
			Assert.assertEquals(storeTitleDisplayed, true);
			

		} catch (Exception e) {
			e.printStackTrace();
			// takeSnapShot("D:\\takeScreenshots",
			// "Store details on Cart Page");
			throw (e);
		}

	}

	@Test(enabled = true, priority = 2)
	public void emptyCartOnChangingLocation() throws Exception {
		try {
			extentInfoLogs("Open Home page");
			OpenHomePage.openHomePage();
			extentInfoLogs("open navigation drawer");
			clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
			info("open navigation drawer");

			extentInfoLogs("click on Mobiles and Tablets link");
			OpenHomePage
					.clickOnCategoryByName(BugRegressionAppConstants.NavDrawer_Mobile_tablets_link);
			info("click on Mobile and tablets link");
			
			extentInfoLogs("Open Android Phone category");
			clickName(BugRegressionAppConstants.AndroidPhones_name);
			
			extentInfoLogs("click On First Android Product");
			OpenAndroidPage.clickOnFirstAndroidProduct();
			extentInfoLogs("click on Buy Now button ");
			clickXpath(BugRegressionAppConstants.ProductDetailsVarBuyNow_xpath);
			
			info("clicking on Buy Now button");
			AddToCart.addToCart();
			extentInfoLogs("Login to Gmail");
			GmailLogin.gmailLogin();
			
			extentInfoLogs("Navigate back");
			backButton();
			extentInfoLogs("Navigate back");
			backButton();
			extentInfoLogs("Navigate back");
			backButton();
			extentInfoLogs("opening navigation drawer");
			clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
			extentInfoLogs("clicking on location icon");
			clickId(BugRegressionAppConstants.NavDrawerLocationIcon_id);
		
			extentInfoLogs("clicking on bangalore");
			clickName(BugRegressionAppConstants.BangaloreLocation_text);
			
			extentInfoLogs("clicking on BTM location");
			clickName(BugRegressionAppConstants.BTMLocation_text);
			info("clicking on BTM location");
			extentInfoLogs("clicking on cart alert");
			clickName(BugRegressionAppConstants.LocationChangeCartPrompt_name);
			info("clicking on cart alert");
			extentInfoLogs("open nav drawer");
			clickId(BugRegressionAppConstants.NavDrawerHomeIcon_id);
			info("clicking on Home icon");
			
			extentInfoLogs("clicking on cart");
			clickCart();
			info("clicking on cart");
			extentInfoLogs("Verifying assertion for empty cart");
			Assert.assertEquals(
					findElementById(BugRegressionAppConstants.NoItemInCart_id)
							.getText(), AppVerificationChecks.NoItemInCart);

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}
	}
}
