package trelloAPI.GET;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import trelloAPI.Specifications;
import trelloAPI.TestRestClient;

import static io.restassured.RestAssured.given;
import static trelloAPI.Globals.BOARD_NAME;
import static trelloAPI.Globals.MEMBER_TYPE;

public class GetMembershipsOfABoardTest {
    public String BOARD_ID;

    @Test
    public void getMembershipsOfABoard() {
        Specifications.installSpec(Specifications.requestSpec(), Specifications.responseSpecOK200());
        BOARD_ID = TestRestClient.createNewBoard(BOARD_NAME).get("id");
        JsonPath jsonResponse = given()
        .when()
                .get("/1/boards/{id}/memberships", BOARD_ID)
        .then()
                .log().all()
                .extract().jsonPath();

        Assert.assertEquals(jsonResponse.get("[0].memberType"), MEMBER_TYPE);

        TestRestClient.deleteBoard(BOARD_ID);
    }
}
