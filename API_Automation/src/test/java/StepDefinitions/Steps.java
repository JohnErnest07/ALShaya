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
	private static final String firstname="John";
	private static final String lastname="Ernest";
	private static final Integer amount=1200;
	boolean depositstatus = true;
	private static final String checkindate="2022-01-01";
	private static final String checkoutdate="2022-01-05";
	private static final String comments="BeachView";
	


	private static Response response;
	private static String jsonString;
	private static String bookid;
	
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
        System.out.println(bookid);
    
    }
    @Given("User enters valid booking details")
    
    public void Userentersvalidbookingdetails() {
    	System.out.println(bookid);
    }
    
    @When("UpdateBooking API is called")
    
    public void UpdateBookingAPIiscalled() {
    }
    
    
    @Then("Booking is updated successfully")
    
    public void Bookingisupdatedsuccessfully() {
    	
    }
	
}
