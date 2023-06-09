package trelloAPI.PUT;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import trelloAPI.Specifications;
import trelloAPI.TestRestClient;

import static io.restassured.RestAssured.given;
import static trelloAPI.Globals.BOARD_NAME;
import static trelloAPI.Globals.SIDEBAR_INFO;

public class UpdateShowSidebarPrefOnABoardTest {
    public String BOARD_ID;
    @Test
    public void updateEmailPositionPrefOnABoard() {
        Specifications.installSpec(Specifications.requestSpec(), Specifications.responseSpecOK200());
        BOARD_ID = TestRestClient.createNewBoard(BOARD_NAME).get("id");
        JsonPath jsonResponse = given()
                .body(SIDEBAR_INFO)
        .when()
                .put("/1/boards/{id}/myPrefs/showSidebar", BOARD_ID)
        .then()
                .log().all()
                .extract().jsonPath();

        Assert.assertEquals(jsonResponse.get("showSidebar"), true);

        TestRestClient.deleteBoard(BOARD_ID);
    }
}
