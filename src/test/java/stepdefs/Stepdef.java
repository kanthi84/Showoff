package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.Support;

public class Stepdef {

    private Support support = new Support();

    @Given("I have created the access token")
    public void i_created_the_access_token() throws Throwable{
        support.returnBearerToken();
    }

    @When("I create user with \"(.*)\" and \"(.*)\" and \"(.*)\" and \"(.*)\"")
    public void i_create_user(String firstName, String lastName, String password, String email) throws Throwable {
        support.createUser(firstName, lastName, password, email);
    }

    @Then("User created successfully")
    public void user_created_successfully() {
        support.isUserCreated();
    }

    @Then("user detail is updated")
    public void userDetailIsUpdated() {
        support.isUserUpdated();
    }

    @When("I upadate user with \"(.*)\" and \"(.*)\" and \"(.*)\"")
    public void iUpadeteUserWithAnd(String firstName, String lastName, String dob) throws Throwable {
        support.updateUser(firstName, lastName, dob);
    }

    @When("I fetch user details by id")
    public void iFetchUserDetails() throws Throwable {
        support.fetchUserDetails();

    }

    @Then("User details fetched successfully")
    public void userDetailsFetchedSuccessfully() throws Throwable {
        support.isUserFethed();
    }

    @When("I fetch user details based on access token")
    public void iFetchMyUserDetails() throws Throwable {
        support.fetchMe();
    }

    @When("I change the user password to \"(.*)\"")
    public void iChangeTheUserPassword( String pw) throws Throwable{
        support.changePassword(pw);
    }

    @Then("Password successfully updated")
    public void passwordSuccessfullyUpdated() throws Throwable{
        support.isPasswordChanced();
    }
}
