package Functional_Scenarios;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import BugRegressionSuite.BaseTestBugRegression;
import Utility.ParamObject;
//import StoreAPI.RestClient;
import Utility.RestClient;

public class PersonalizationEventsScenarios extends BaseTestBugRegression {

	public static String payloadForEvents(String gte,String lte,String deviceId) throws JSONException {

		JSONObject order = new JSONObject();
		JSONObject terms = new JSONObject();
		JSONObject numeric2 = new JSONObject();
		JSONObject aggs = new JSONObject();
		JSONObject servertime = new JSONObject();
		JSONObject range = new JSONObject();
		JSONArray must = new JSONArray();
		JSONObject deviceid = new JSONObject();
		JSONObject match = new JSONObject();
		JSONObject query = new JSONObject();
		JSONObject rootquery = new JSONObject();
		JSONObject rootrange = new JSONObject();
		JSONArray must_not = new JSONArray();
		JSONObject bool = new JSONObject();
		JSONObject filter = new JSONObject();
		JSONObject query_string = new JSONObject();
		JSONObject query_1 = new JSONObject();
		JSONObject filtered = new JSONObject();
		JSONObject parentquery = new JSONObject();
		JSONObject rootobject = new JSONObject();
		JSONObject rootobject_1 = new JSONObject();

		order.put("_count", "desc");
		terms.put("field", "event_name");
		terms.put("size", 100);
		terms.put("order", order);
		numeric2.put("terms", terms);
		aggs.put("2", numeric2);
		// System.out.println(gte);
		servertime.put("gte", gte);
		// System.out.println(uCurrentTime);
		servertime.put("lte", lte);
		servertime.put("format", "epoch_millis");
		range.put("server_time", servertime);

		rootrange.put("range", range);
		deviceid.put("query",deviceId);
		deviceid.put("type", "phrase");
		match.put("device-id", deviceid);
		query.put("match", match);
		rootquery.put("query", query);
		must.put(rootquery);
		must.put(rootrange);
		bool.put("must", must);
		bool.put("must_not", must_not);
		filter.put("bool", bool);
		query_string.put("query", "*");
		query_string.put("analyze_wildcard", true);
		query_1.put("query_string", query_string);
		filtered.put("query", query_1);
		filtered.put("filter", filter);
		parentquery.put("filtered", filtered);
		rootobject.put("size", 0);
		rootobject.put("query", parentquery);
		rootobject.put("aggs", aggs);

		rootobject_1.put("index", "test_index_8");
		rootobject_1.put("search_type", "count");
		rootobject_1.put("ignore_unavailable", true);

		return rootobject_1.toString() + "\n" + rootobject.toString() + "\n";

	}

	public static String getCurrentDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// "20-01-2016
																			// 23:59:00"
		// get current date time with Date()
		Date date = new Date();
		// System.out.println(dateFormat.format(date));

		// get current date time with Calendar()
		Calendar cal = Calendar.getInstance();
		String datetype = dateFormat.format(cal.getTime());
		System.out.println(dateFormat.format(cal.getTime()));
		return datetype;
	}

	public static Utility.ParamObject getParamsForEvents(String payLoad) {
		Utility.ParamObject obj = new Utility.ParamObject();
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		String url = "http://52.76.154.241:5600/elasticsearch/_msearch?timeout=0&ignore_unavailable=true&preference=1456206452143";
		obj.setUrl(url);
		obj.addHeader("Authorization", "Basic a2liYW5hOmtFRG04eGRrUmdT");
		obj.addHeader("kbn-xsrf-token", "56d4cca433b219ef20c209b69d6f4f0e3ee49c61e7d4c700e8384096479bcd75");
		obj.addHeader("Content-Type", "application/json");
		obj.setPayload(payLoad);
		obj.setMethodType("POST");
		return obj;
	}

	@Test
	public static void getPersonlizationEvents() throws Exception {
		String eventsList = "";
		String[] events = {"action_view_product", "action_login", "action_register", "conversion_buy_intent",
				"screen_order_success"};
		
//		"search_go"
		try {
			extentInfoLogs("Personalization events");
			String deviceid = BaseTestBugRegression.getDeviceId();
			System.out.println("device id is " + deviceid);
			Date date = new Date();
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
			format1 = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
			endTime = format1.format(date);
			long endTime_Long1 = date.getTime();
			System.out.println("endTime_Long1:::::" + endTime_Long1);
			long executionTime = getTotalExecutionTimeInMS(startTime_Long, endTime_Long1);
			System.out.println("execution time is : " + executionTime);

			String currentTime = getCurrentDateTime();
			long uCurrentTime = getCurrentUnixTime(currentTime);
			String currentTimeInString = Long.toString(uCurrentTime);
//			currentTimeInString = "\"" + currentTimeInString + "\"";
			System.out.println("unix current time " + currentTimeInString);
			long gteStartTime = uCurrentTime - executionTime;
			System.out.println("the value of gte starttime :" + gteStartTime);

			long gte = uCurrentTime - executionTime;
			String gteInString = Long.toString(gte);
//			gteInString = "\"" + gteInString + "\"";
			System.out.println("the value of gte(starting time) is : " + gteInString);
			RestClient.restClient_Flag = true;
			
			String payload = payloadForEvents(gteInString,currentTimeInString,deviceid);// deviceid,gteInString,currentTimeInString
			ParamObject params = getParamsForEvents(payload);
			String json = (String) Utility.RestClient.getJSONFromParamsObject(params,
					Utility.RestClient.RETURN_JSON_STRING);
			System.out.println("Response: "+json);
			String rootobject = getValueFromParser_WithBrace(json, "responses");
			JSONArray responses = new JSONArray(rootobject);
			for (int i = 0; i < responses.length(); i++) {
				JSONObject aggregationobject = responses.getJSONObject(i);
				String aggregations = aggregationobject.getString("aggregations");
				JSONObject numeric2 = new JSONObject(aggregations);
				String numeric2S = numeric2.getString("2");
				JSONObject buckets = new JSONObject(numeric2S);
				String buckets_1 = buckets.getString("buckets");
				JSONArray bucketsArray = new JSONArray(buckets_1);

				for (int k = 0; k < bucketsArray.length(); k++) {
					String key = getValueFromParser(bucketsArray.getJSONObject(k).toString(), "key");
					String count = getValueFromParser(bucketsArray.getJSONObject(k).toString(), "doc_count");

					eventsList = eventsList + " " + key + "(" + count + ")";
				}
				System.out.println("Events List with count : " + eventsList);

				for (int j = 0; j < events.length; j++) {
					if (eventsList.contains(events[j])) {
						extentInfoLogs("Assert for " + events[j] + " event");
						Assert.assertTrue(eventsList.contains(events[j]));
					}else{
						extentFailLogs("Assert for " + events[j] + " event");
					}
				}
			}
		} catch (Exception e) {
			throw (e);
		}

	}

	// public static void getDataObjects(String deviceid) throws JSONException,
	// IOException{
	// RestClient.restClient_Flag = false;
	// String payload = payloadForEvents(deviceid);
	// ParamObject params = getParamsForEvents(payload);
	// String json = (String) Utility.RestClient.getJSONFromParamsObject(
	// params, Utility.RestClient.RETURN_JSON_STRING);
	// System.out.println(json);
	// Gson gson = new Gson();
	// //Root ps = new Root();
	// // Type listType = new TypeToken<Root>() {}.getType();
	// Root ps = gson.fromJson(json, Root.class);
	// System.out.println(ps.toString());
	// }

	public static String getDeviceID() {
		String id = null;
		String myid = null;
		try {
			InputStream is = Runtime.getRuntime().exec("adb devices").getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			StringBuilder out = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
			id = out.toString().split("attached")[1];
			System.out.println(id);
			id = id.split("        ")[0];
			System.out.println(id);
			myid = id.split("d")[0];
			System.out.println("my id is :" + myid);
			is = Runtime.getRuntime().exec("adb -s " + id + " shell getprop net.hostname").getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));
			out = new StringBuilder();
			System.out.println(out);

			while ((line = reader.readLine()) != null) {
				out.append(line);
			}
			id = out.toString().split("-")[1];

		} catch (Exception e) {
			System.out.println("Connect your device properly : " + e.getMessage());
		}
		return myid;
	}

	private static String getValueFromParser(String str, String keyValue) throws Exception {
		String value = "";
		Object temp;
		try {
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(str.toString());
			JsonObject jsonObject = element.getAsJsonObject();
			try {
				temp = jsonObject.get("status");
				value = temp.toString();
				if (value.contains("Failed")) {
					temp = jsonObject.get("msg");
					value = temp.toString();
					throw new Exception(value);
				}
			} catch (Exception e) {

			}

			temp = jsonObject.get(keyValue);
			value = temp.toString().replaceAll("\"", "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception(value);
		}
		return value;

	}

	private static String getValueFromParser_WithBrace(String str, String keyValue) throws Exception {
		String value = "";
		Object temp;
		try {
			JsonParser jsonParser = new JsonParser();
			JsonElement element = jsonParser.parse(str.toString());
			JsonObject jsonObject = element.getAsJsonObject();
			try {
				temp = jsonObject.get("status");
				value = temp.toString();
				if (value.contains("Failed")) {
					temp = jsonObject.get("msg");
					value = temp.toString();
					throw new Exception(value);
				}
			} catch (Exception e) {

			}

			temp = jsonObject.get(keyValue);
			// value = temp.toString().replaceAll("\"", "");
			value = temp.toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception(value);
		}
		return value;

	}

	public static long getUnixTime(String dateString) throws ParseException {
		// String dateString = "2016-01-11T01:00:00Z";
		String dateString1 = dateString.split("T")[0];
		String dateString2 = dateString.split("T")[1];
		String dateString3 = dateString2.split("Z")[0];
		String dt = dateString1 + " " + dateString3;
		System.out.println(dateString1);
		System.out.println(dateString2);
		System.out.println(dateString3);
		System.out.println(dt);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		Date date = dateFormat.parse(dt);
		long unixTime = date.getTime() / 1000;
		System.out.println(unixTime);
		// System.out.println(unixTime );
		return unixTime;
	}

	public static long getCurrentUnixTime(String dateString) throws ParseException {
		// String dateString = "2016-01-11T01:00:00Z";

		// System.out.println(dateString);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		Date date = dateFormat.parse(dateString);
		long unixTime = date.getTime() / 1000;
		System.out.println(unixTime);
		// System.out.println(unixTime );
		return unixTime;
	}

	public static long getExecutionUnixTime(String dateString) throws ParseException {
		// String dateString = "2016-02-090:1:42";

		// System.out.println(dateString);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-ddh:m:s");
		Date date = dateFormat.parse(dateString);
		long unixTime = date.getTime() / 1000;
		System.out.println(unixTime);
		return unixTime;
	}

	public static void main(String[] args) throws Exception {
		// payloadForEvents();
		getPersonlizationEvents();
	}

	// //String deviceid = getDeviceID();
	// //System.out.println(deviceid);
	// //getCurrentDateTime();
	// //String deviceid = getDeviceID();
	// //System.out.println(deviceid);
	// getPersonlizationEvents();
	// //getExecutionUnixTime();
	// //getUnixTime("2016-01-11T01:00:00Z");
	// //getUnixTime("2016-01-12T10:00:00Z");
	//
	// //getDataObjects();
	// // payloadForEvents();
	// // String id = getDeviceID();
	// // System.out.println(id);
	// // long time= getTotalExecutionTime( System.currentTimeMillis(),
	// // System.currentTimeMillis());
	// // System.out.println(time);
	//
	// }

	public class ParentObject {
		public CommonObject _source;
	}

	public class Hits {
		public ParentObject[] hits;
	}

	public class CommonObject {
		public String product_id;
		public String zopper_price;
		public String carousel_id;
	}

	public class Root {

		private Hits hits;

		private String _shards;

		private String timed_out;

		private String took;

		public Hits getHits() {
			return hits;
		}

		public void setHits(Hits hits) {
			this.hits = hits;
		}

		public String get_shards() {
			return _shards;
		}

		public void set_shards(String _shards) {
			this._shards = _shards;
		}

		public String getTimed_out() {
			return timed_out;
		}

		public void setTimed_out(String timed_out) {
			this.timed_out = timed_out;
		}

		public String getTook() {
			return took;
		}

		public void setTook(String took) {
			this.took = took;
		}

		@Override
		public String toString() {
			return "ClassPojo [hits = " + hits + ", _shards = " + _shards + ", timed_out = " + timed_out + ", took = "
					+ took + "]";
		}
	}

	@Override
	@Test(enabled = false)
	public void executeTestCase() throws Exception {
		// TODO Auto-generated method stub

	}

}
