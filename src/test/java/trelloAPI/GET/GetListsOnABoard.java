package trelloAPI.GET;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import trelloAPI.Globals;
import trelloAPI.Specifications;
import trelloAPI.TestRestClient;

import static io.restassured.RestAssured.given;
import static trelloAPI.Globals.BOARD_NAME;

public class GetListsOnABoard {
    public String BOARD_ID;
    @Test
    public void getLabelsOnABoard() {
        Specifications.installSpec(Specifications.requestSpec(), Specifications.responseSpecOK200());
        BOARD_ID = TestRestClient.createNewBoard(BOARD_NAME).get("id");
        JsonPath jsonResponse = given()
        .when()
                .get("/1/boards/{id}/lists", BOARD_ID)
        .then()
                .log().all()
                .extract().jsonPath();

        Assert.assertEquals(jsonResponse.get("[0].name"), Globals.LABELS_NAME);
        Assert.assertEquals(jsonResponse.get("[0].closed"), false);
        Assert.assertEquals(jsonResponse.get("[0].subscribed"), false);

        TestRestClient.deleteBoard(BOARD_ID);
    }
}
