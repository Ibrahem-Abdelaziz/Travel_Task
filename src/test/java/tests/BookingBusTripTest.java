package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.PaymentPage;
import pages.searchResultsPage;

public class BookingBusTripTest extends TestBase {

    HomePage homeObject;
    searchResultsPage searchResultsObject ;
    PaymentPage paymentObject;




    @Test

    public void testFullCycle() {
        homeObject = new HomePage(driver) ;
        searchResultsObject = new searchResultsPage(driver) ;
        paymentObject = new PaymentPage(driver);


        homeObject.selectLeaveCity();
        homeObject.selectGoingToCity();
        homeObject.selectDate();
        homeObject.submitSearch();

        searchResultsObject.selectSeatAndPoints();
        searchResultsObject.clickProvidePassengerDetails();
        searchResultsObject.fillCustomerDetails("6789125987","Ibrahem@asd.com");
        searchResultsObject.fillPassengerInfo("Ibrahem" , "Male","19");

        paymentObject.selectRazorpayPayment();
        paymentObject.clickProceedToPay();
        paymentObject.clickAgree();
        paymentObject.clickProceedToPay();
    }

}
