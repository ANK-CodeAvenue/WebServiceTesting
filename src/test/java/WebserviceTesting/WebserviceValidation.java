package WebserviceTesting;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class WebserviceValidation {
	
	@Test
	public void Test1_PvrTokenGeneration(ITestContext context) {
	String Token = 	RestAssured.given()
		.formParam("username", "admin-dev@parcelhero.com")
		.formParam("password","@RJ6ff!byR4kgfyr")
		.formParam("scope","pvr-all openid offline_access")
		.formParam("client_id","pvr-admin.client")
		.formParam("grant_type","password")
		.post("https://api-sandboxconfig.parcel.vision/connect/token")
		.then()
		.extract()
		.jsonPath().get("access_token");
		
	context.setAttribute("Token", Token);	
	System.out.println(Token);	
		
	}
	
	@Test(enabled=false)
	public void Test2(ITestContext context) {

		JSONObject jso = new JSONObject("        {\r\n"
				+ "        \"shipmentid\": \"\",\r\n"
				+ "        \"retailerId\": \"61d0547f-ec2b-4bd6-9cb5-5b74abaf3020\",\r\n"
				+ "        \"items\": [\r\n"
				+ "            {\r\n"
				+ "                \"PackingType\": \"Parcel\",\r\n"
				+ "                \"ContentType\": \"Goods\",\r\n"
				+ "                \"Weight\": 1,\r\n"
				+ "                \"Length\": 10.0,\r\n"
				+ "                \"Width\": 10.0,\r\n"
				+ "                \"Height\": 10.0\r\n"
				+ "            }\r\n"
				+ "       \r\n"
				+ "        ],\r\n"
				+ "        \"collectionAddress\": {\r\n"
				+ "            \"addressLine1\": \"84 Butler Road\",\r\n"
				+ "            \"addressLine2\": null,\r\n"
				+ "            \"addressLine3\": null,\r\n"
				+ "            \"city\": \"Baverly Hill\",\r\n"
				+ "            \"postcode\": \"90210\",\r\n"
				+ "            \"county\": \"\",\r\n"
				+ "            \"countryCode\": \"US\",\r\n"
				+ "            \"addressType\": \"Residential\"\r\n"
				+ "        },\r\n"
				+ "        \"deliveryAddress\": {\r\n"
				+ "            \"addressLine1\": \"Unit 1, 13A Dendy St\",\r\n"
				+ "            \"addressLine2\": \"BRIGHTON\",\r\n"
				+ "            \"addressLine3\": null,\r\n"
				+ "            \"city\": \"Brentford\",\r\n"
				+ "            \"postcode\": \"tw88hr\",\r\n"
				+ "            \"county\": \"\",\r\n"
				+ "            \"countryCode\": \"GB\",\r\n"
				+ "            \"addressType\": \"Residential\"\r\n"
				+ "        },\r\n"
				+ "        \"declaredValue\": {\r\n"
				+ "            \"currency\": \"GBP\",\r\n"
				+ "            \"amount\": 15.0\r\n"
				+ "        }\r\n"
				+ "    }");

		System.out.println(jso.get("declaredValue") instanceof JSONArray);
		System.out.println(jso.get("declaredValue") instanceof JSONObject);	
		System.out.println(jso.getJSONObject("declaredValue").get("amount"));
	}
	
	
	@Test(enabled=false)
	public void Test3(ITestContext context) {
		
		
	 Response res   =  RestAssured.given().baseUri("https://api-sandboxconfig.parcel.vision/v2/draft-shipments/")
		.auth()
		.oauth2(context.getAttribute("Token").toString())
		
        .body("        {\r\n"
        		+ "        \"shipmentid\": \"\",\r\n"
        		+ "        \"retailerId\": \"61d0547f-ec2b-4bd6-9cb5-5b74abaf3020\",\r\n"
        		+ "        \"items\": [\r\n"
        		+ "            {\r\n"
        		+ "                \"PackingType\": \"Parcel\",\r\n"
        		+ "                \"ContentType\": \"Goods\",\r\n"
        		+ "                \"Weight\": 1,\r\n"
        		+ "                \"Length\": 10.0,\r\n"
        		+ "                \"Width\": 10.0,\r\n"
        		+ "                \"Height\": 10.0\r\n"
        		+ "            }\r\n"
        		+ "       \r\n"
        		+ "        ],\r\n"
        		+ "        \"collectionAddress\": {\r\n"
        		+ "            \"addressLine1\": \"84 Butler Road\",\r\n"
        		+ "            \"addressLine2\": null,\r\n"
        		+ "            \"addressLine3\": null,\r\n"
        		+ "            \"city\": \"Brentford\",\r\n"
        		+ "            \"postcode\": \"TW88HR\",\r\n"
        		+ "            \"county\": \"\",\r\n"
        		+ "            \"countryCode\": \"GB\",\r\n"
        		+ "            \"addressType\": \"Residential\"\r\n"
        		+ "        },\r\n"
        		+ "        \"deliveryAddress\": {\r\n"
        		+ "            \"addressLine1\": \"Unit 1, 13A Dendy St\",\r\n"
        		+ "            \"addressLine2\": \"BRIGHTON\",\r\n"
        		+ "            \"addressLine3\": null,\r\n"
        		+ "            \"city\": \"Baverly Hill\",\r\n"
        		+ "            \"postcode\": \"90210\",\r\n"
        		+ "            \"county\": \"\",\r\n"
        		+ "            \"countryCode\": \"US\",\r\n"
        		+ "            \"addressType\": \"Residential\"\r\n"
        		+ "        },\r\n"
        		+ "        \"declaredValue\": {\r\n"
        		+ "            \"currency\": \"GBP\",\r\n"
        		+ "            \"amount\": 15.0\r\n"
        		+ "        }\r\n"
        		+ "    }")
        .contentType(ContentType.JSON)
        .when()
        .post()
        .then()
        .extract()
         .response();
		
    	 JsonArray arrs; String st;
    	 JsonObject jsonObject =  JsonParser.parseString(res.body().asString()).getAsJsonObject();
		
		 JsonArray arr = jsonObject.getAsJsonArray("itineraries");
		    for (int i = 0; i < arr.size(); i++) {
		    	System.out.println(arr.get(i).getAsJsonObject().get("id").toString());
		    	arrs = arr.get(i).getAsJsonObject().getAsJsonArray("legs");
		    	System.out.println( arrs.size());

		    			for (int ii = 0; ii < arrs.size(); ii++) {
		    				st=arrs.get(ii).getAsJsonObject().getAsJsonObject("carrierServiceQuote").get("serviceName").getAsString();
		    				
		    			System.out.println(st);
		    				}
		    }
	
	}

	@Test
	public void Test4(ITestContext context) {
	//	Map<String,Object> responseBody = null;	
		
	String	responseBody    =  RestAssured.given().baseUri("https://api-sandboxconfig.parcel.vision/v2/draft-shipments/")
		.auth()
		.oauth2(context.getAttribute("Token").toString())
		
        .body("     {\r\n"
        		+ "        \"shipmentid\": \"\",\r\n"
        		+ "        \"retailerId\": \"61d0547f-ec2b-4bd6-9cb5-5b74abaf3020\",\r\n"
        		+ "        \"items\": [\r\n"
        		+ "            {\r\n"
        		+ "                \"PackingType\": \"Parcel\",\r\n"
        		+ "                \"ContentType\": \"Goods\",\r\n"
        		+ "                \"Weight\": 1,\r\n"
        		+ "                \"Length\": 10.0,\r\n"
        		+ "                \"Width\": 10.0,\r\n"
        		+ "                \"Height\": 10.0\r\n"
        		+ "            }\r\n"
        		+ "       \r\n"
        		+ "        ],\r\n"
        		+ "        \"collectionAddress\": {\r\n"
        		+ "            \"addressLine1\": \"84 Butler Road\",\r\n"
        		+ "            \"addressLine2\": null,\r\n"
        		+ "            \"addressLine3\": null,\r\n"
        		+ "            \"city\": \"Brentford\",\r\n"
        		+ "            \"postcode\": \"TW88HE\",\r\n"
        		+ "            \"county\": \"\",\r\n"
        		+ "            \"countryCode\": \"GB\",\r\n"
        		+ "            \"addressType\": \"Residential\"\r\n"
        		+ "        },\r\n"
        		+ "        \"deliveryAddress\": {\r\n"
        		+ "            \"addressLine1\": \"Unit 1, 13A Dendy St\",\r\n"
        		+ "            \"addressLine2\": \"BRIGHTON\",\r\n"
        		+ "            \"addressLine3\": null,\r\n"
        		+ "            \"city\": \"Paris\",\r\n"
        		+ "            \"postcode\": \"75001\",\r\n"
        		+ "            \"county\": \"\",\r\n"
        		+ "            \"countryCode\": \"FR\",\r\n"
        		+ "            \"addressType\": \"Residential\"\r\n"
        		+ "        },\r\n"
        		+ "        \"declaredValue\": {\r\n"
        		+ "            \"currency\": \"GBP\",\r\n"
        		+ "            \"amount\": 15.0\r\n"
        		+ "        }\r\n"
        		+ "    }")
        .contentType(ContentType.JSON)
        .when()
        .post()
        .then()
        .extract()
         .body().asString();
       //  .as(new TypeRef<Map<String,Object>>() {});
		
		JSONObject jo =	new JSONObject(responseBody);
		
		System.out.println(jo.get("draftShipmentId").toString());
		context.setAttribute("draftShipmentId", jo.get("draftShipmentId").toString());
		
		   JSONArray ja = new JSONArray (jo.get("itineraries").toString());
		    System.out.println("No of services = "+ja.length());
		    for(int i=0;i<ja.length();i++)
		    {JSONObject jobb =	new JSONObject(ja.get(i).toString());
		    System.out.println();
		    JSONArray jaa = new JSONArray (jobb.get("legs").toString());
		    System.out.println("Service with id = "+jobb.get("id")+" has "+jaa.length()+" legs.");
		    
		     for(int ii=0;ii<jaa.length();ii++)
		    { JSONObject jobbb =	new JSONObject(jaa.get(ii).toString());
		    	JsonPath jp = JsonPath.from(jobbb.get("carrierServiceQuote").toString());
		    System.out.println(jp.getString("carrierName")+"      "+jp.getString("serviceName")); 
		    
		    }
		    
	}
		

	}


	@Test(enabled=false)
	public void Test5(ITestContext context) {
		
		
	 Response res   =  RestAssured.given().baseUri("https://api-sandboxconfig.parcel.vision/v2/draft-shipments/")
		.auth()
		.oauth2(context.getAttribute("Token").toString())
		
        .body("       {\r\n"
        		+ "    \"shipmentId\": \""
        		+ context.getAttribute("draftShipmentId").toString()
        		+ "\",\r\n"
        		+ "    \"goods\": [\r\n"
        		+ "        {\r\n"
        		+ "            \"description\": \"Product1\",\r\n"
        		+ "            \"countryIso2CodeOfManufacture\": \"GB\",\r\n"
        		+ "            \"tariffCode\": \"49011001\",\r\n"
        		+ "            \"unitPrice\": {\r\n"
        		+ "                \"currency\": \"GBP\",\r\n"
        		+ "                \"amount\": 1.0\r\n"
        		+ "            },\r\n"
        		+ "            \"quantity\": 1\r\n"
        		+ "        }\r\n"
        		+ "    ],\r\n"
        		+ "    \"reasonForExport\": \"others\",\r\n"
        		+ "    \"declarationStatement\": \"\",\r\n"
        		+ "    \"shipperTaxIds\": {\r\n"
        		+ "        \"vatNumber\": \"\",\r\n"
        		+ "        \"eori\": \"\",\r\n"
        		+ "        \"ioss\": \"IM2760000742\",\r\n"
        		+ "        \"other\": \"\"\r\n"
        		+ "    },\r\n"
        		+ "    \"receiverTaxIds\": {\r\n"
        		+ "        \"vatNumber\": \"123456789\",\r\n"
        		+ "        \"eori\": \"\",\r\n"
        		+ "        \"ioss\": \"\",\r\n"
        		+ "        \"other\": \"\"\r\n"
        		+ "    }\r\n"
        		+ "}")
        .contentType(ContentType.JSON)
        .when()
        .post()
        .then()
        .extract()
         .response();
		
    	 JsonArray arrs; String st;
    	 JsonObject jsonObject =  JsonParser.parseString(res.body().asString()).getAsJsonObject();
		
		 JsonArray arr = jsonObject.getAsJsonArray("itineraries");
		    for (int i = 0; i < arr.size(); i++) {
		    	System.out.println(arr.get(i).getAsJsonObject().get("id").toString());
		    	arrs = arr.get(i).getAsJsonObject().getAsJsonArray("legs");
		    	System.out.println( arrs.size());

		    			for (int ii = 0; ii < arrs.size(); ii++) {
		    				st=arrs.get(ii).getAsJsonObject().getAsJsonObject("carrierServiceQuote").get("serviceName").getAsString();
		    				
		    			System.out.println(st);
		    				}
		    }
	
	}

	@Test(enabled=false)
	public void Test6(ITestContext context) {
		
		
	 Response res   =  RestAssured.given().baseUri("https://api-sandboxconfig.parcel.vision/v2/draft-shipments/")
		.auth()
		.oauth2(context.getAttribute("Token").toString())
		
        .body("       {\r\n"
        		+ "    \"shipmentId\": \""
        		+ context.getAttribute("draftShipmentId").toString()
        		+ "\",\r\n"
        		+ "    \"itineraryId\": \""
        		+ "52f129e2-bb2d-4fa7-a3bc-4921cabb0959"
        		+ "\",\r\n"
        		+ "    \"version\":1,\r\n"
        		+ "    \"reference\": \"N/A\",\r\n"
        		+ "    \"contentsDescription\": \"Paper\",\r\n"
        		+ "    \"collectionDate\": \"2022-09-07\",\r\n"
        		+ "    \"shipperDetails\": {\r\n"
        		+ "        \"contact\": {\r\n"
        		+ "            \"name\": \"Tonya Dendrinou\",\r\n"
        		+ "            \"email\": \"tonya.dendrinou@intercargo.org\",\r\n"
        		+ "            \"companyName\": \"INTERCARGO\",\r\n"
        		+ "            \"phoneNumber\": \"07596996260\",\r\n"
        		+ "            \"vat\": \"\"\r\n"
        		+ "        },\r\n"
        		+ "        \"address\": {\r\n"
        		+ "            \"addressLine1\": \"123 Minories\",\r\n"
        		+ "            \"addressLine2\": \"4th Floor\",\r\n"
        		+ "            \"addressLine3\": \"\",\r\n"
        		+ "            \"city\": \"Brentford\",\r\n"
        		+ "            \"postcode\": \"tw88hr\",\r\n"
        		+ "            \"county\": \"\",\r\n"
        		+ "            \"countryCode\": \"GB\",\r\n"
        		+ "            \"addressType\": \"Business\"\r\n"
        		+ "        }\r\n"
        		+ "    },\r\n"
        		+ "    \"collectionContact\": {\r\n"
        		+ "        \"name\": \"Tonya Dendrinou\",\r\n"
        		+ "        \"email\": \"tonya.dendrinou@intercargo.org\",\r\n"
        		+ "        \"companyName\": \"INTERCARGO\",\r\n"
        		+ "        \"phoneNumber\": \"07596996260\",\r\n"
        		+ "        \"vat\": \"\"\r\n"
        		+ "    },\r\n"
        		+ "    \"receiverContact\": {\r\n"
        		+ "        \"name\": \"Mr Steve C. Vassilakis\",\r\n"
        		+ "        \"email\": \"operations@gencoshipping.com\",\r\n"
        		+ "        \"companyName\": \"GENCO SHIPPING  & TRADING LTD\",\r\n"
        		+ "        \"phoneNumber\": \"6464438550\",\r\n"
        		+ "        \"vat\": \"\"\r\n"
        		+ "    }\r\n"
        		+ "}")
        .contentType(ContentType.JSON)
        .when()
        .post()
        .then()
        .extract()
         .response();
		
    	 JsonArray arrs; String st;
    	 JsonObject jsonObject =  JsonParser.parseString(res.body().asString()).getAsJsonObject();
		
		 JsonArray arr = jsonObject.getAsJsonArray("itineraries");
		    for (int i = 0; i < arr.size(); i++) {
		    	System.out.println(arr.get(i).getAsJsonObject().get("id").toString());
		    	arrs = arr.get(i).getAsJsonObject().getAsJsonArray("legs");
		    	System.out.println( arrs.size());

		    			for (int ii = 0; ii < arrs.size(); ii++) {
		    				st=arrs.get(ii).getAsJsonObject().getAsJsonObject("carrierServiceQuote").get("serviceName").getAsString();
		    				
		    			System.out.println(st);
		    				}
		    }
	
	}




}
