package App_Functions;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.util.ServiceException;

public class GoogleSheetsApiTest {

// Generate a service account and P12 key:
// https://developers.google.com/identity/protocols/OAuth2ServiceAccount
private final String CLIENT_ID = "287201579073-cl6l0v6rca9avbmpfdjf3vvp9cnsavrn.apps.googleusercontent.com";
// Add requested scopes.
private final List<String> SCOPES = Arrays.asList("https://spreadsheets.google.com/feeds");
// The name of the p12 file you created when obtaining the service account
private final String P12FILE = "89H6tJhQ5tkbmjjDOw45OmxK";


public void testConnectToSpreadSheet() throws GeneralSecurityException,
        IOException, ServiceException, URISyntaxException {

    SpreadsheetService service = new SpreadsheetService("google-spreadsheet");
    GoogleCredential credential = getCredentials();
    service.setOAuth2Credentials(credential);

    URL SPREADSHEET_FEED_URL = new URL(
            "https://docs.google.com/spreadsheets/d/1l3ly87aJFh8DrkZaMa1dy9GLxLIav_NFil0p4VTmY6M/edit#gid=286887973");
    SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,
            SpreadsheetFeed.class);
    List<SpreadsheetEntry> spreadsheets = feed.getEntries();

    if (spreadsheets.size() == 0) {
        // // TODO: There were no spreadsheets, act accordingly.
    }
    //
    SpreadsheetEntry spreadsheet = spreadsheets.get(0);
    System.out.println(spreadsheet.getTitle().getPlainText());

}

private GoogleCredential getCredentials() throws GeneralSecurityException,
        IOException, URISyntaxException {
    JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    HttpTransport httpTransport = GoogleNetHttpTransport
            .newTrustedTransport();

    URL fileUrl = this.getClass().getResource(P12FILE);
    GoogleCredential credential = new GoogleCredential.Builder()
            .setTransport(httpTransport)
            .setJsonFactory(JSON_FACTORY)
            .setServiceAccountId(CLIENT_ID)
            .setServiceAccountPrivateKeyFromP12File(
                    new File(fileUrl.toURI()))
            .setServiceAccountScopes(SCOPES).build();

    return credential;
}

public static void main(String[] args){
	GoogleSheetsApiTest api=new GoogleSheetsApiTest();
	try {
		api.testConnectToSpreadSheet();
	} catch (GeneralSecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ServiceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (URISyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}