package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.WebOrdersHomePage;
import pages.WebOrdersLoginPage;
import utilities.BrowserUtils;
import utilities.Configuration;
import utilities.Driver;


import java.util.List;
import java.util.Map;

public class WebOrderSteps {
    WebDriver driver= Driver.getDriver();
    WebOrdersLoginPage webOrdersLoginPage = new WebOrdersLoginPage();
    WebOrdersHomePage webOrdersHomePage = new WebOrdersHomePage();
   // List<Map><String,Object>> data  = new ArrayList<>();


    @Given("User navigates to application")
    public void user_navigates_to_application() {
        driver.get(Configuration.getProperty("WebOrdersURL"));
    }

    @When("User log in with username {string}  and password {string}")
    public void user_provides_username_and_password(String username, String password) {
        webOrdersLoginPage.username.sendKeys(username);
        webOrdersLoginPage.password.sendKeys(password);
        webOrdersLoginPage.loginButton.click();

    }

    @Then("User validates that application is on homepage")
    public void user_validates_that_application_is_on_homepage() {
        String expectedTitle="Web Orders";
        String actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
        driver.quit();


    }


    @When("log in with username {string} and password {string}")
    public void logInWithUsernameAndPassword(String username, String password) {
        webOrdersLoginPage.username.sendKeys(username);
        webOrdersLoginPage.password.sendKeys(password);
        webOrdersLoginPage.loginButton.click();
    }


    @Then("User validates error login message")
    public void userValidatesErrorLoginMessage() {
        String expectedError="Invalid Login or Password.";
        String actualError=webOrdersLoginPage.errorStatus.getText();
        Assert.assertEquals(actualError,expectedError);
    }


    @When("User clicks on {string} module")
    public void user_clicks_on_module(String string) {
        webOrdersHomePage.orderButton.click();
    }

    @When("User provides product {string} and quantity {int}")
    public void user_provides_product_and_quantity(String product, Integer quantity) {
        BrowserUtils.selectByValue(webOrdersHomePage.chooseProduct,"FamilyAlbum");
        webOrdersHomePage.chooseQuantity.sendKeys(Keys.BACK_SPACE);
        webOrdersHomePage.chooseQuantity.sendKeys(quantity.toString());
        webOrdersHomePage.chooseQuantity.sendKeys(Keys.ENTER);

    }

    @Then("User validates total is calculated properly {int}")
    public void userValidatesTotalIsCalculatedProperly(Integer quantity) {
       String pricePerUnit= webOrdersHomePage.pricePerUnit.getAttribute("value");
       int discountAmount=Integer.parseInt(webOrdersHomePage.dissAmount.getAttribute("value"));
       int expectedTotal=0;
       if(discountAmount!=0){
           expectedTotal = quantity * Integer.parseInt(pricePerUnit)*((100-discountAmount)/100);
       }else {
           expectedTotal = quantity * Integer.parseInt(pricePerUnit);
       }
       int actualTotal =Integer.parseInt(webOrdersHomePage.getTotal.getAttribute("value"));
       Assert.assertEquals(actualTotal,expectedTotal);


    }

    @When("User creates an order with data")
    public void user_creates_an_order_with_data(DataTable dataTable) {
       // convert datatable to list of Maps>> List<Map<String,Object>>
        List<Map<String,Object> > data = dataTable.asMaps(String.class,Object.class);
        for(int i=0; i<data.size();i++) {
            BrowserUtils.selectByValue(webOrdersHomePage.chooseProduct, data.get(0).get("Product").toString());
            webOrdersHomePage.chooseQuantity.sendKeys(Keys.BACK_SPACE);
            webOrdersHomePage.chooseQuantity.sendKeys(data.get(0).get("Quantity").toString());
            webOrdersHomePage.inputName.sendKeys(data.get(0).get("Customer name").toString());
            webOrdersHomePage.inputStreet.sendKeys(data.get(0).get("Street").toString());
            webOrdersHomePage.inputCity.sendKeys(data.get(0).get("City").toString());
            webOrdersHomePage.inputState.sendKeys(data.get(0).get("State").toString());
            webOrdersHomePage.inputZip.sendKeys(data.get(0).get("Zip").toString());
            webOrdersHomePage.visaCardBtn.click();
            webOrdersHomePage.inputCCNumber.sendKeys(data.get(0).get("Card Nr").toString());
            webOrdersHomePage.inputExpDate.sendKeys(data.get(0).get("Exp date").toString());
            webOrdersHomePage.processBtn.click();
        }


    }

    @Then("User validates error success message {string}")
    public void user_validates_error_success_message(String expectedResult) {
        String actualResult=webOrdersHomePage.getMessage.getText();
        Assert.assertEquals(actualResult,expectedResult);

    }
    @Then("User validates that created order are in the list of all orders")
    public void user_validates_that_created_order_are_in_the_list_of_all_orders() {


    }





}
