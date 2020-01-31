package utilities;

import cucumber.api.java.Before;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.Assert.*;

public class Support {

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    Endpoints endpoints = new utilities.Endpoints();

    String uName = "michael@showoff.ie";
    String pass = "password";
    String clientId = "277ef29692f9a70d511415dc60592daf4cf2c6f6552d3e1b769924b2f2e2e6fe";
    String clientSecret = "d6106f26e8ff5b749a606a1fba557f44eb3dca8f48596847770beb9b643ea352";

    String authUri = endpoints.baseUrl + endpoints.oAuth; //This API is not functioning
    String createUser = endpoints.baseUrl + endpoints.createUser;
    String updateUser = endpoints.baseUrl + endpoints.updateUser;
    String me = createUser + endpoints.me;
    String passwordUpdate = me + endpoints.password;


    String oAuthJson;
    String accressToken;
    String userId;

    Response response;
    Response user;
    Response userDetails;
    Response changepass;

    // returnBearerToken() method does not work as API is not functioning

    public String returnBearerToken() throws Throwable{

        // This request URI does not work as expected. It throws error 422


        oAuthJson = "{\"grant_type\": \""+ pass + "\", \"client_id\": \""+ clientId + "\" , \"client_secret\": \"" + clientSecret + "\",\"userName\": \""+ uName +"\", \"password\": \""+ pass + "\"}";

        String bearerToken = given()
                .relaxedHTTPSValidation()
                .headers("Content-Type", JSON)
                .body(oAuthJson)
                .when()
                .post(authUri)
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .header("Authorization");

        return bearerToken;
    }

    public void createUser(String fName, String lName, String pass, String email) throws Throwable{

        JSONObject userJson = new JSONObject();
        JSONArray userArray = new JSONArray();
        JSONObject userParam = new JSONObject();

        userJson.put("client_id", clientId);
        userJson.put("client_secret", clientSecret);

        userParam.put("first_name", fName);
        userParam.put("last_name", lName);
        userParam.put("password", pass);
        userParam.put("email", email);
        userParam.put("image_url", "https://static.thenounproject.com/png/961-200.png");

        userArray.put(userParam);

        userJson.put("user", userParam);

        System.out.println("---------------------------------");
        System.out.println(userJson);
        System.out.println("---------------------------------");
        System.out.println(createUser);

         user = given()
                .relaxedHTTPSValidation()
                .headers("Content-Type", JSON)
                .body(userJson)
                .when()
                .post(createUser)
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .response();
    }

    public void isUserCreated(){
        assertEquals("Success", user.path("message"));
        accressToken = user.jsonPath().getString("data.token.access_token");
        userId = user.jsonPath().getString("data.user.id");
    }

//    public void updateUser1() throws Throwable{
//
//        JSONObject userJson = new JSONObject();
//        JSONArray userArray = new JSONArray();
//        JSONObject userParam = new JSONObject();
//
//        userJson.put("client_id", clientId);
//        userJson.put("client_secret", clientSecret);
//
//        userParam.put("first_name", "Soff33");
//        userParam.put("last_name", "Test1" );
//        userParam.put("date_of_birth", 11041984);
//        userParam.put("image_url", "https://static.thenounproject.com/png/961-200.png");
//
//        userArray.put(userParam);
//
//        userJson.put("user", userParam);
//
//        response = given()
//                .relaxedHTTPSValidation()
//                .headers(
//                        "Authorization",
//                        accressToken
//
//                )
//                .header("Content-Type", JSON)
//                .when()
//                .put(updateUser)
//                .then()
//                .log()
//                .all()
//                .contentType(ContentType.JSON)
//                .statusCode(200)
//                .extract()
//                .response();
//
//    }

    public void updateUser(String fName, String lName, String dob) throws Throwable{

        JSONObject userJson = new JSONObject();
        JSONArray userArray = new JSONArray();
        JSONObject userParam = new JSONObject();

        userParam.put("first_name", fName);
        userParam.put("last_name", lName );
        userParam.put("date_of_birth", dob);
        userParam.put("image_url", "https://static.thenounproject.com/png/961-200.png");

        userArray.put(userParam);

        userJson.put("user", userParam);

        response = given()
                .relaxedHTTPSValidation()
//                .proxy("127.0.0.1")
                .headers(
                        "Authorization",
                        accressToken

                )
                .header("Content-Type", JSON)
                .body(userJson)
//                .proxy("corporate.proxy", 80)
                .when()
                .put(updateUser)
                .then()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .extract()
                .response();
    }

    public void isUserUpdated(){
        assertEquals("Success", response.path("message"));
    }

    public void doGetRequest(String api) throws Throwable{

        userDetails = given()
                .relaxedHTTPSValidation()
                .headers("Authorization",
                        accressToken)
                .when()
                .get(api)
                .then()
                .log()
                .all()
                .contentType(JSON)
                .statusCode(200)
                .extract()
                .response();
    }

    public void fetchUserDetails() throws Throwable{
        String fetchUserAPI = createUser + "/" + userId;
        doGetRequest(fetchUserAPI);
    }

    public void isUserFethed() throws Throwable{
        assertEquals("Success", userDetails.path("message"));
    }

    public void fetchMe() throws Throwable {
        doGetRequest(me);
    }

    public void changePassword( String pw) throws Throwable {

        JSONObject userJson = new JSONObject();
        JSONArray userArray = new JSONArray();
        JSONObject userParam = new JSONObject();

        userParam.put("current_password", pass);
        userParam.put("new_password", pw );

        userArray.put(userParam);

        userJson.put("user", userParam);

        changepass = given()
                .relaxedHTTPSValidation()
                .headers("Content-Type", JSON)
                .body(userJson)
                .when()
                .post(passwordUpdate)
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .response();
    }

    public void isPasswordChanced() throws Throwable {
        assertEquals("Success", changepass.path("message"));
    }

}
