package trelloAPI.GET;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import trelloAPI.Specifications;
import trelloAPI.TestRestClient;

import static io.restassured.RestAssured.given;
import static trelloAPI.Globals.BOARD_NAME;
import static trelloAPI.Globals.LABEL_NAME;

public class GetLabelsOnABoard {
    public String BOARD_ID;
    @Test
    public void getLabelsOnABoard() {
        Specifications.installSpec(Specifications.requestSpec(), Specifications.responseSpecOK200());
        BOARD_ID = TestRestClient.createNewBoard(BOARD_NAME).get("id");
        JsonPath jsonResponse = given()
        .when()
                .get("/1/boards/{id}/labels", BOARD_ID)
        .then()
                .log().all()
                .extract().jsonPath();

        Assert.assertEquals(jsonResponse.get("[0].name"), LABEL_NAME);

        TestRestClient.deleteBoard(BOARD_ID);
    }
}
