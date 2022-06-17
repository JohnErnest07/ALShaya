package StepDefinitions;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;	
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


public class Steps {
	
	
	private static final String BaseURL="https://restful-booker.herokuapp.com/booking";
	private static final String AuthURL="https://restful-booker.herokuapp.com/auth";
	private static final String firstname="John";
	private static final String newfirstname="Johne";
	private static final String username="admin";
	private static final String password="password123";
	private static final String lastname="Ernest";
	private static final Integer amount=1200;
	boolean depositstatus = true;
	private static final String checkindate="2022-01-01";
	private static final String checkoutdate="2022-01-05";
	private static final String comments="BeachView";
	


	private static Response response;
	private static Response authresponse;
	private static Response paramresponse;
	private static String jsonString;
	private static String bookid;
	private static String tokenvalue;
	
    @Given("User enters valid details")
        public void User_enters_valid_details() {
    	System.out.println("User Enters Valid details");
    }
    
    
    @When("CreateBooking API is called")
    
    public void CreateBookingAPIiscalled() {
    	
    	RestAssured.baseURI = BaseURL;
		RequestSpecification request = RestAssured.given();
			request.header("Accept", "application/json")
			       .header("Content-Type", "application/json");
			   
			        System.out.println(" Body ==> "+ request.log().all());	
			
		
		response = request.body("{ \"firstname\": \"" + firstname + "\"," +
				                   "\"lastname\": \"" + lastname + "\", " +
				                   "\"totalprice\": \"" + amount + "\", " +
				                   "\"depositpaid\": \"" + depositstatus + "\"," +
				                  // "\"checkin\": \"" + checkindate + "\", " +
				                   //"\"checkout\": \"" + checkoutdate + "\", " +
				                   "\"additionalneeds\": \"" + comments + "\", " +
				                   "\"bookingdates\" : {\"checkin\" : \"2022-01-01\","
				                                     + "\"checkout\" : \"2022-01-05\"}} "
				                   )
					.post();

    	    	}
    	
		
    	@Then ("Booking is created successfully")
    
    public void Bookingiscreatedsuccessfully() {
    	Assert.assertEquals(200, response.getStatusCode());
    	String jsonstring=response.asString();
    	System.out.println(jsonstring.toString());
    	JsonPath jsonPath = new JsonPath(response.asString());
    	String bookid = jsonPath.getString("bookingid");
        System.out.println("Booking id is" +bookid);
    
    }
    @Given("User enters valid booking details")
   
    public void Userentersvalidbookingdetails() {
    	
    	
    }
    
    @When("UpdateBooking API is called")
    
    public void UpdateBookingAPIiscalled() {

    	
    	JsonPath jsonPath = new JsonPath(response.asString());
    	String bookid = jsonPath.getString("bookingid");
        System.out.println("Booking id is new" +bookid);
    
        
// Getting token details
    	
    	RestAssured.baseURI =AuthURL;
		RequestSpecification authrequest = RestAssured.given();

		authrequest.header("Content-Type", "application/json");
		authresponse = authrequest.body("{ \"username\":\"" + username + "\", \"password\":\"" + password + "\"}")
				.post();
		System.out.println(" Update request "+ authrequest.log().all());
		String jsonstring=authresponse.asString();
    	System.out.println(jsonstring.toString());
    	
		

		String jsonString1 = authresponse.asString();
		tokenvalue = JsonPath.from(jsonString1).get("token");
		System.out.println(tokenvalue);
		
    	RestAssured.baseURI = BaseURL;
		RequestSpecification request1 = RestAssured.given();
			request1.header("Accept", "application/json")
			       .header("Content-Type", "application/json")
			       .header("Cookie", "token="+tokenvalue+ "");
		
			 		
			
			System.out.println(" Update request "+ request1.log().all());
			
			response = request1.body("{ \"firstname\": \"" + newfirstname + "\"," +
	                   "\"lastname\": \"" + lastname + "\", " +
	                   "\"totalprice\": \"" + amount + "\", " +
	                   "\"depositpaid\": \"" + depositstatus + "\"," +
	                  // "\"checkin\": \"" + checkindate + "\", " +
	                   //"\"checkout\": \"" + checkoutdate + "\", " +
	                   "\"additionalneeds\": \"" + comments + "\", " +
	                   "\"bookingdates\" : {\"checkin\" : \"2022-01-01\","
	                                     + "\"checkout\" : \"2022-01-05\"}} "
	                   
	                   )
		.put("" + bookid + "" );
			
			
			
			        	
    }	
    
    
    @Then("Booking is updated successfully")
    
    public void Bookingisupdatedsuccessfully() {
    	
    	Assert.assertEquals(200, response.getStatusCode());
    	String restss=response.getBody().asString();
    	
    }
	
    
    @When("DeleteBooking API is called")
    
    
    public void DeleteBookingAPIiscalled() {
    	
    	RestAssured.baseURI = BaseURL;
		RequestSpecification paramrequest = RestAssured.given();
			paramrequest.header("Accept", "application/json")
			       .header("Content-Type", "application/json")
			       .queryParam("firstname","Johne")
			       .queryParam("Lastname", "Ernest");
			
			System.out.println(" Param request "+ paramrequest.log().all());
			
			paramresponse = paramrequest.get();
			
			
			String jsonString2 = paramresponse.asString();
	    	System.out.println(jsonString2.toString());
			
			
    	
    }
    
    @Then("Booking is deleted successfully")
    
    public void Bookingisdeletedsuccessfully() {
    	
    }
}
