package Functional_Scenarios;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import App_Functions.OpenHomePage;
import BugRegressionSuite.BaseTestBugRegression;
import Page_Objects.AppData;
import Page_Objects.BugRegressionAppConstants;
import Page_Objects.SanitySuiteAppConstants;

//Test case info:In this test case we are  verifying multiple cases
//In first test case we are searching the product by product categories
//In second test case are verifying auto suggest results
//Author: Argneshu Gupta

public class SearchScenarios extends BaseTestBugRegression {
	boolean found = false;

	@Override
	@Test(enabled = true)
	public void executeTestCase() throws Exception {
		try {
			extentInfoLogs("Call the function of home page function");
			OpenHomePage.openHomePage();
			Thread.sleep(4000);
			swipeWithAxis(200, 300, 100, 100, 3000);
			extentInfoLogs("Click on home search icon");
			clickId(BugRegressionAppConstants.Home_search_button_id);
			String[] categories = { "Android Phones", "Air Conditioner", "Refrigerator  " };
			for (String category : categories) {
				extentInfoLogs("Enter search string as " + category);
				sendKeysForID(BugRegressionAppConstants.Home_SearchEditBox_id, category);
				extentInfoLogs("Click on auto suggest first item");
				clickXpath(BugRegressionAppConstants.SearchSuggestedFirstProduct_xpath);
				extentInfoLogs("Assert for suggested first product");
				Assert.assertTrue(
						findElementsById(SanitySuiteAppConstants.MyAccountFirstFavouriteProduct_id).get(0).isEnabled());
				extentInfoLogs("Assert for suggested second product");
				Assert.assertTrue(
						findElementsById(SanitySuiteAppConstants.MyAccountFirstFavouriteProduct_id).get(1).isEnabled());

				extentInfoLogs("Click on home search icon");
				clickId(BugRegressionAppConstants.Home_search_button_id);
			}
		} catch (Exception e) {
			e.getMessage();
			throw (e);
		}

	}

	@Test(enabled = true)
	public void autoSuggestResult() throws Exception {
		try {
			extentInfoLogs("Call the function of home page function");
			OpenHomePage.openHomePage();
			Thread.sleep(4000);
			swipeWithAxis(200, 300, 100, 100, 3000);
			extentInfoLogs("Click on home search icon");
			clickId(BugRegressionAppConstants.Home_search_button_id);
			extentInfoLogs("Enter search string as " + AppData.Producttext9);
			sendKeysForID(BugRegressionAppConstants.Home_SearchEditBox_id, AppData.Producttext9);
			while (!found) {
				extentInfoLogs("Getting suggested list");
				WebElement li = findElementByClassName(BugRegressionAppConstants.ListView_class);
				List<WebElement> text = li.findElements(By.className(BugRegressionAppConstants.TextView_class));
				for (int i = 0; i < text.size(); i++) {
					boolean valid = text.get(i).isDisplayed();
					extentInfoLogs("Assert for element displayed");
					Assert.assertEquals(valid, true);

					swipeWithAxis(350, 750, 350, 250, 3000);
				}
				if (text.get(8).isDisplayed()) {
					found = true;
					boolean productDisplayed8 = text.get(8).isDisplayed();
					extentInfoLogs("Assert for presence " + productDisplayed8);
					Assert.assertEquals(productDisplayed8, true);
					break;
				} else {
					extentInfoLogs("Swipe vertically");
					swipeWithAxis(350, 750, 350, 250, 3000);
				}

			}

		} catch (Exception e) {
			e.getMessage();
			throw (e);
		}
	}
	
	@Test(enabled = true)
	public void searachWithOneCharacter(){
		try{
			extentInfoLogs("Open home page");
			OpenHomePage.openHomePage();
			clickId(BugRegressionAppConstants.HomeSearchtextbox_id);
			extentInfoLogs("click on search textbox");
			sendKeysForID(BugRegressionAppConstants.Home_SearchEditBox_id,
					AppData.SeacrhWithOneCharacter);
			List<WebElement> list = findElementsById(BugRegressionAppConstants.AutoSuggestSearch_id);
			int count =list.size();
			Assert.assertTrue(count==10);
			
		}catch(Exception e){
			throw(e);
		}
	}

}