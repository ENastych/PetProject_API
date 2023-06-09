package trelloAPI.POST;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import trelloAPI.Specifications;
import trelloAPI.TestRestClient;

import static io.restassured.RestAssured.given;
import static trelloAPI.Globals.BOARD_NAME;
import static trelloAPI.Globals.POSITION_OF_EMAIL;

public class CreateEmailKeyForABoardTest {
    public String BOARD_ID;
    @Test
    public void createEmailKeyForABoard() {
        Specifications.installSpec(Specifications.requestSpec(), Specifications.responseSpecOK200());
        BOARD_ID = TestRestClient.createNewBoard(BOARD_NAME).get("id");
        JsonPath jsonResponse = given()
        .when()
                .post("/1/boards/{id}/emailKey/generate", BOARD_ID)
        .then()
                .log().all()
                .extract().jsonPath();

        Assert.assertEquals(jsonResponse.get("myPrefs.emailPosition"), POSITION_OF_EMAIL);

        TestRestClient.deleteBoard(BOARD_ID);
    }
}
