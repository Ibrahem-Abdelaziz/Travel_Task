package tests;

import data.JsonReader;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PaymentPage;
import pages.searchResultsPage;

import java.io.IOException;

public class BookingBusTripTest extends TestBase {

    HomePage homeObject;
    searchResultsPage searchResultsObject ;
    PaymentPage paymentObject;




    @Test

    public void testFullCycle() throws InterruptedException, IOException, ParseException {
        homeObject = new HomePage(driver) ;
        searchResultsObject = new searchResultsPage(driver) ;
        paymentObject = new PaymentPage(driver);
        String fromCity = JsonReader.jsonData("searching","fromCity") ;
        String toCity = JsonReader.jsonData("searching","toCity") ;
        String date = JsonReader.jsonData("searching","date") ;

        String seatNum = JsonReader.jsonData("seatSelection","seatNumber") ;
        String phoneNum = JsonReader.jsonData("CustomerDetails","phoneNumber") ;
        String email = JsonReader.jsonData("CustomerDetails","email") ;

        String fnName = JsonReader.jsonData("PassengerInfo","fnName") ;
        String gender = JsonReader.jsonData("PassengerInfo","gender") ;
        String age = JsonReader.jsonData("PassengerInfo","age") ;



        homeObject.selectLeaveCity(fromCity );
        homeObject.selectGoingToCity( toCity);
        homeObject.selectDate(date );
        homeObject.submitSearch();

        searchResultsObject.selectSeatAndPoints(seatNum );
        searchResultsObject.clickProvidePassengerDetails();
        searchResultsObject.fillCustomerDetails( phoneNum,email );
        searchResultsObject.fillPassengerInfo(fnName ,gender ,age);

        paymentObject.selectPayment();
        paymentObject.clickProceedToPay();
        paymentObject.clickAgree();
        paymentObject.clickProceedToPay();
    }

}
