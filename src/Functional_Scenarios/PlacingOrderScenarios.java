package Functional_Scenarios;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import App_Functions.AddToCart;
import App_Functions.GmailLogin;
import App_Functions.OpenAndroidPage;
import App_Functions.OpenHomePage;
import BugRegressionSuite.BaseTestBugRegression;
import Page_Objects.AppData;
import Page_Objects.AppVerificationChecks;
import Page_Objects.BugRegressionAppConstants;
//Test case info:In this test case we are  verifying placing order scenarios
// In first test case we are verifying placing order scenario
import Page_Objects.SanitySuiteAppConstants;

public class PlacingOrderScenarios extends BaseTestBugRegression {
	String price = null;
	int intprice = 0;

	public void clickBuyNowBySeller_MoreSeller(String sellerName) {
		try {
			WebElement parentLayout = driver.findElement(By.id("com.zopperapp:id/rv_stores"));
			List<WebElement> linearLayout = parentLayout.findElements(By.className("android.widget.LinearLayout"));
			for (WebElement ele : linearLayout) {
				String StoreName = ele.findElement(By.id("com.zopperapp:id/tv_item_product_store_title")).getText();
				if (StoreName.trim().toLowerCase().equals(sellerName.trim().toLowerCase())) {
					ele.findElement(By.id("com.zopperapp:id/tv_item_buy")).click();
					break;
				}
			}
		} catch (Exception e) {

		}
	}

	@Override
	@Test(enabled = true)
	public void executeTestCase() throws Exception {
		try {

			extentInfoLogs("Open Home page");
			OpenHomePage.openHomePage();
			extentInfoLogs("Open Navigation drawer");
			clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
			extentInfoLogs("Click on sign up  iconOpen Home page");
			clickId(BugRegressionAppConstants.SignUpIcon_id);
			extentInfoLogs("Gmail login");
			GmailLogin.gmailLogin();
			extentInfoLogs("Navigate back");
			backButton();
			extentInfoLogs("Click on cart icon");
			clickId(BugRegressionAppConstants.Prod_Cart_Id);
			extentInfoLogs("Delete all products from cart");
			deleteCartItems();
			extentInfoLogs("Navigate back");
			backButton();
			// clickClassName(AppConstants.Open_Navigation_Drawer);
			// extentInfoLogs("open navigation drawer");
			extentInfoLogs("Click on home sarch text box");
			clickId(BugRegressionAppConstants.Home_Search_TextBox_Id);
			extentInfoLogs("Enter product name in search field");
			sendKeysForID(BugRegressionAppConstants.Home_SearchEditBox_id, AppData.Priyank_Store_SearchProduct_Name);
			extentInfoLogs("Select product from auto suggestion list");
			OpenAndroidPage.clickOnCategoryByName(BugRegressionAppConstants.AutoSuggestSearch_id,
					AppData.Priyank_Store_Product_Name);

			Thread.sleep(5000);
			extentInfoLogs("Scroll to view store link");
			// driver.scrollTo(AppData.View_Store_Text);
			for (int k = 0; k < 3; k++) {
				swipeVertically_FullPage();
			}
			extentInfoLogs("Get product name");
			String strName = findElementById(BugRegressionAppConstants.Product_Store_Name_id).getText();
			if (!strName.trim().equals(AppData.Priyank_Store_Text)) {
				extentInfoLogs("Click on view more sellers link");

				clickId(BugRegressionAppConstants.View_More_Sellers_Id);
				for (int j = 0; j < 3; j++) {
					if (findElementsById(BugRegressionAppConstants.View_More_Sellers_Id).size() > 0)
						clickId(BugRegressionAppConstants.View_More_Sellers_Id);
					else
						break;
				}
				// clickId(BugRegressionAppConstants.View_More_Sellers_Id);
				extentInfoLogs("Select priyank store from view more sellers");
				for (int i = 0; i < 3; i++) {
					// int sellerCount =
					// findElementsById(BugRegressionAppConstants.More_Sellers_Store_Name_Id).size();
					// for (int j = 0; j < sellerCount; j++) {
					// String storeName =
					// findElementsById(BugRegressionAppConstants.More_Sellers_Store_Name_Id).get(j)
					// .getText();
					// if (storeName.trim().equals(AppData.Priyank_Store_Text))
					// {
					// try {
					// swipeVertically(1000);
					// findElementsById(BugRegressionAppConstants.MoreSellersBuyNow_id).get(j).click();
					// AddToCart.addToCart();
					// break;
					// } catch (Exception e) {
					// }
					// }
					// }
					try {
						clickBuyNowBySeller_MoreSeller(AppData.Priyank_Store_Text);
					} catch (Exception e) {
						swipeVertically_FullPage();
						clickBuyNowBySeller_MoreSeller(AppData.Priyank_Store_Text);
					}
					swipeVertically_FullPage();
					swipeVertically_FullPage();
				}

			} else {
				extentInfoLogs("Click on buy now button");
				clickXpath(BugRegressionAppConstants.ProductDetailsVarBuyNow_xpath);
				AddToCart.addToCart();
			}

			// OpenHomePage.clickOnCategoryByName(AppConstants.NavDrawer_Mobile_tablets_link);
			// extentInfoLogs("click on Mobile and tablets link");
			//
			// clickName(AppConstants.AndroidPhones_name);
			//
			// OpenAndroidPage.clickOnFirstAndroidProduct();
			extentInfoLogs("clicking on checkout button");
			clickId(BugRegressionAppConstants.ProductionCheckoutButton_id);

			extentInfoLogs("Checking for address fields");
			if (findElementsById(SanitySuiteAppConstants.Shipping_FullName_Id).size() > 0) {
				extentInfoLogs("Enter user name in shipping address");
				sendKeysForID(SanitySuiteAppConstants.Shipping_FullName_Id, AppData.Shipping_UserName);
				extentInfoLogs("Enter address in shipping address");
				sendKeysForID(SanitySuiteAppConstants.Shipping_Address_Id, AppData.ShippingAddress);
				extentInfoLogs("Enter Pincode in shipping address");
				sendKeysForID(SanitySuiteAppConstants.Shipping_Pincode_Id, AppData.ShippingPincode);
				extentInfoLogs("Click on save button");
				clickId(SanitySuiteAppConstants.Shipping_SaveButton_Id);
			}

			extentInfoLogs("clicking on continue button");
			clickId(BugRegressionAppConstants.ProductionContinueButton_id);
			extentInfoLogs("clicking on cash on delivery button");
			try {
				clickName(BugRegressionAppConstants.CashOnDelievery_name);
			} catch (Exception e) {
				swipeVertically_FullPage();
				clickName(BugRegressionAppConstants.CashOnDelievery_name);
			}
			extentInfoLogs("clicking on place order button");
			clickId(BugRegressionAppConstants.ProductionPlaceOrder_id);
			extentInfoLogs("Getting order id after order");
			String verifyOrderPageOrderID = findElementById(BugRegressionAppConstants.VerifyOrderPageOrderId_id)
					.getText();
			extentInfoLogs("clicking on shop more button");
			clickId(BugRegressionAppConstants.ProductionShopMore_id);
			extentInfoLogs("Assert for home screen");
			Assert.assertEquals(findElementByName(BugRegressionAppConstants.Hometext_name).getText(),
					AppVerificationChecks.Hometext);
			extentInfoLogs("open nav drawer");
			clickClassName(BugRegressionAppConstants.Open_Navigation_Drawer);
			extentInfoLogs("Clicking on user profile");
			clickId(BugRegressionAppConstants.SignUpIcon_id);
			extentInfoLogs("clicking on My orderlink option");
			clickId(BugRegressionAppConstants.MyProfileOrderLink_id);
			extentInfoLogs("Getting ordr id from my orders page");
			String myOrderPageOrderID = findElementById(BugRegressionAppConstants.OrderID_id).getText();
			extentInfoLogs("Assert for order ids");
			Assert.assertEquals(verifyOrderPageOrderID, myOrderPageOrderID);
			clickIDByIndex(SanitySuiteAppConstants.ViewOrder_id, 0);
			String orderPlaced = clickOnCategoryByName(BugRegressionAppConstants.OrderPlaced_id, AppData.OrderPlaced);
			System.out.println(orderPlaced);
			Assert.assertTrue(orderPlaced.equalsIgnoreCase(AppVerificationChecks.OrderStatusProcessing));

		} catch (Exception e) {
			e.printStackTrace();
			throw (e);
		}

	}

	@Test(enabled = false)
	public void payTmFlow() {
		try {
			extentInfoLogs("Open Home page");
			OpenHomePage.openHomePage();
			wait.until(
					ExpectedConditions.invisibilityOfElementLocated(By.id(BugRegressionAppConstants.HomeSpinner_id)));
			swipeWithAxis(200, 300, 100, 100, 3000);
			extentInfoLogs("Click on home search icon");
			clickId(BugRegressionAppConstants.Home_search_button_id);
			extentInfoLogs("Enter search string as " + AppData.Priyank_Store_Product_Name);
			sendKeysForID(BugRegressionAppConstants.Home_SearchEditBox_id, AppData.Priyank_Store_SearchProduct_Name);
			extentInfoLogs("Click on auto suggest first item");
			clickXpath(BugRegressionAppConstants.SearchSuggestedFirstProduct_xpath);
			List<WebElement> element = findElementsById(BugRegressionAppConstants.ProductPriceOnPrdctDetails_id);
			price = element.get(0).getText().replaceAll(",", "");
			intprice = Integer.parseInt(price);
			if (intprice <= 10000) {
				extentInfoLogs("Click on cart icon");
				clickId(BugRegressionAppConstants.Prod_Cart_Id);
				extentInfoLogs("Gmail login");
				GmailLogin.gmailLogin();
				extentInfoLogs("Delete all products from cart");
				deleteCartItems();
				extentInfoLogs("Navigate back");
				backButton();
				extentInfoLogs("Click on buy now button");
				clickXpath(BugRegressionAppConstants.ProductDetailsVarBuyNow_xpath);
				AddToCart.addToCart();
				extentInfoLogs("clicking on checkout button");
				clickId(BugRegressionAppConstants.ProductionCheckoutButton_id);

				extentInfoLogs("Checking for address fields");
				if (findElementsById(SanitySuiteAppConstants.Shipping_FullName_Id).size() > 0) {
					extentInfoLogs("Enter user name in shipping address");
					sendKeysForID(SanitySuiteAppConstants.Shipping_FullName_Id, AppData.Shipping_UserName);
					extentInfoLogs("Enter address in shipping address");
					sendKeysForID(SanitySuiteAppConstants.Shipping_Address_Id, AppData.ShippingAddress);
					extentInfoLogs("Enter Pincode in shipping address");
					sendKeysForID(SanitySuiteAppConstants.Shipping_Pincode_Id, AppData.ShippingPincode);
					extentInfoLogs("Click on save button");
					clickId(SanitySuiteAppConstants.Shipping_SaveButton_Id);
				}

				extentInfoLogs("clicking on continue button");
				clickId(BugRegressionAppConstants.ProductionContinueButton_id);
				extentInfoLogs("clicking on cash on continue button");
				clickOnCategoryByName(BugRegressionAppConstants.AllPaymentMode_id, AppData.PayTm);
				Assert.assertTrue(BugRegressionAppConstants.PaytmpageCancel_name
						.equalsIgnoreCase(AppVerificationChecks.PaytmCancel));
			} else {
				verifyProductForPaytm();
			}
			// }
		} catch (Exception e) {
			throw (e);
		}

	}

	public void verifyProductForPaytm() {
		extentInfoLogs("Click on home search icon");
		clickId(BugRegressionAppConstants.Home_search_button_id);
		extentInfoLogs("Enter search string as " + AppData.Producttext10);
		sendKeysForID(BugRegressionAppConstants.Home_SearchEditBox_id, AppData.Producttext11);
		extentInfoLogs("Click on auto suggest first item");
		clickXpath(BugRegressionAppConstants.SearchSuggestedFirstProduct_xpath);
		List<WebElement> element = findElementsById(BugRegressionAppConstants.ProductPriceOnPrdctDetails_id);
		price = element.get(0).getText().replaceAll(",", "");
		intprice = Integer.parseInt(price);
		extentInfoLogs("Click on cart icon");
		clickId(BugRegressionAppConstants.Prod_Cart_Id);
		extentInfoLogs("Gmail login");
		GmailLogin.gmailLogin();
		extentInfoLogs("Delete all products from cart");
		deleteCartItems();
		extentInfoLogs("Navigate back");
		backButton();
		extentInfoLogs("Click on buy now button");
		clickXpath(BugRegressionAppConstants.ProductDetailsVarBuyNow_xpath);
		AddToCart.addToCart();
		extentInfoLogs("clicking on checkout button");
		clickId(BugRegressionAppConstants.ProductionCheckoutButton_id);
		extentInfoLogs("clicking on continue button");
		clickId(BugRegressionAppConstants.ProductionContinueButton_id);
		extentInfoLogs("clicking on cash on continue button");
		clickOnCategoryByName(BugRegressionAppConstants.AllPaymentMode_id, AppData.PayTm);
		Assert.assertTrue(
				BugRegressionAppConstants.PaytmpageCancel_name.equalsIgnoreCase(AppVerificationChecks.PaytmCancel));

	}

}
