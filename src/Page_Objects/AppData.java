package Page_Objects;

//This interface hold all the application data which is used in different test cases scripts

public interface AppData {

	// Test data for Submit review scenario
	String SubmitReviewTestData = "Symphony Ninja 17 Ltr Air Cooler";
	String ReviewString = "Reviews";

	String title = ".";
	String description = ".";

	// Test data for Review with Special character scenario
	String title1 = "@";
	String description1 = "@";

	// Test data for Similar products scenario
	String EnteredStringInTextBox = "Samsung galaxy Grand Neo 8GB Black";
	String SimilarProductsText = "Similar Products";
	String EnteredStringInTextBox1 = "Samsung Z1 Dual Sim Red";
	String Lg = "LG";
	String Fly = "Fly";
	String Samsung = "Samsung";
	String EnteredStringInTextBox2 = "Eureka Forbes Easy Clean Vacuum Cleaner";

	// Test data for HtcProductConnectionIssue scenario
	String EnteredStringInTextBox3 = "Micromax 32 Inch 32B200HD LED TV";

	// Test data for Shopping Cart Product Delete
	String MobileAndTabletsLink = "Mobiles and Tablets";

	// Test data for CouponPromptIssue
	String CouponTextboxData = "YAVEX323";

	// Test data for AddressPagevalidationIssue
	String Shipping_UserName = "Test User";
	String ShippingAddress = "Test Address";
	String ShippingPincode = "201002";
	String AddressEmailTextboxData = "a";
	String AdressContactNumberData = "9899";

	// Quick Tour scenarios
	String Skiptext = "Skip";
	String LetGoShoppingtext = "LET'S GO SHOPPING";
	String Similar_Product_Text = "Similar Products";
	String Priyank_Store_Text = "priyank";
	String HomePageExploreExcitingCategories_text = "Explore Exciting Categories";

	// Cart
	String Delete_text = "Delete";
	String SEEWHATTRENDING_Text = "SEE WHAT'S TRENDING";
	String TrendingInYourCity_Text = "Trending in your city";

	// Location scenarios
	String Locationtext = "Select Location";
	String Hometext = "Home";
	String BTMtext = "BTM";
	String Oopstext = "OOPS !";
	String Location_text = "Dehradun";

	// Navigation drawer
	String MTtext = "Mobiles and Tablets";
	String KAtext = "Kitchen Appliances";
	String HAtext = "Home Appliances";
	String TVVtext = "TV & Home Entertainment";
	String CAtext = "Computers & Accessories";
	String PCtext = "Personal Care";
	String username = "zopperprachi@gmail.com";
	String password = "qa@zopper";
	String TrendingProductstext = "Trending Products";
	String PopularCategoriestext = "Categories";
	String MoreCategoriestext = "More Categories";

	// Search Scenarios
	String AndroidPhonestext = "Android Phones";

	// ProductDetailsScenarios
	String Producttext = "Philips HL 1632   ";
	String Producttext1 = "Apple iphone 4S 8GB White";
	String Producttext2 = "Sony Xperia T2 Ultra Dual Black";
	// String Producttext3 = "Oster Sandwich Maker With Removable Grill -
	// CCKSTSM 3887-049";
	String Producttext4 = "Prestige Jumbo Sandwich Maker";
	String Producttext5 = "Morphy Richards SM3006 SS 700 W Sandwich Make";
	String Producttext6 = "orpat sandwich makers ost-1117-dx";
	String Producttext7 = "bajaj majesty new swx 3 sandwich maker";
	String ImageClassName = "android.widget.ImageView";

	String Producttext8 = "Samsung Galaxy";
	String Producttext9 = "Samsung";
	String Producttext10 = "Micromax Unite 3  ";
	String Producttext11 = "Intex aqua 3g  ";
	String ExchangeProductList = "IFB Senator SX 8 Kg Front Loading Fully Automatic Washing Machine Silver##LG 32LF550A 32";
	String ZSP_Product = "Samsung galaxy s6 32gb black sapphire";// "LG
																	// P8239R3SA
																	// Top
																	// Loading
																	// Semi
																	// Automatic
																	// Washing
																	// Machine
																	// Burgandy";

	// Priyank store product
	String Priyank_Store_Product_Name = "Philips HL 1632/00 3 Jar 500W Juicer Mixer Grinder";

	String Priyank_Store_SearchProduct_Name = "Philips HL 1632   ";
	String Brandedproduct = "Philips HL1631/j ";
	String View_Store_Text = "VIEW STORE";
	String SeacrhWithOneCharacter = "S";

	// ContactUs scenarios
	String Fullname = "zopper";
	String EmailID = "qa@zopper.com";
	String Feedback = "good";

	// Connection error
	String ConnectionErrortext = "Connection Error";

	// Login Scenarios
	String UserId_1 = "zopper.test@gmail.com";
	String UserId = "pooja_jaiswal001@rediffmail.com";
	String Ok_Text = "OK";
	String Facebook_MailId="zopperprachi@gmail.com";
	String Gmail_MailId="zopper.Test@gmail.com";

	// Warranty Scenarios
	String Contacts = "9899279150";
	String SerialNumber = "q1a2s3d4";
	String ProductPrice = "20000";
	String ProductReasons = "Testing";

	// Overflow scenarios
	String AboutZopper = "ABOUT ZOPPER";
	String Warranty = "WARRANTY";
	String BackLinkText = "BACK";

	// Redif
	String Rediff_URL = "https://mypage.rediff.com/login/dologin";
	String Success_Msg = "Password changed";
	String MailName = "no-reply@zopper.com";
	String MailSubject = "Share Zopper app";
	String Gmail_URL = "https://gmail.com";

	// Waranty
	String LG_Name = "LG  ";
	String Warrant_For = "1 year";

	// play store

	String playStore_URL = "http://onelink.to/zopper";
	// String playStore_URL =
	// "https://play.google.com/store/apps/details?id=com.zopperapp&referrer=utm_source%3Dzopperapp";

	// Installed App notification

	String InstalledApp_Notification = "Zopper - Mobiles & Electronics";
	String PlayStore_Email = "zopper.test@gmail.com";
	String PlayStore_Password = "qa@zopper";

	String prod_Consumer_PackageName = "com.zopperapp";
	String prod_Consumer_ActivityName = "com.zopper.aegon.ui.activity.SplashActivity";
	String staging_Consumer_PackageName = "com.zopperapp.staging";
	String staging_Consumer_ActivityName = "com.zopper.aegon.ui.activity.SplashActivity";
	String Retailer_PackageName = "com.zopper.rmsstaging";
	String Retailer_ActivityName = "com.zopper.rms.activities.SplashActivity";
	// Freshdesk
	String Freshdesklink = "http://zopper.freshdesk.com/support/login";
	String FreshdeskUsername = "triveni@zopper.com";
	String FreshdeskPassword = "zopper";

	// Mode of Payment
	String PayTm = "Paytm";
	// Location page
	String VadodaraCity = "Vadodara";

	String OlaMoney = "OLA MONEY";

	String Branch = "hello";
	
	String OrderPlaced = "Order Placed";
	
	String ChangePassword_SuccessMsg="Password changed successfully";
}
