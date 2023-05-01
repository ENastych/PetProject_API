package trelloAPI.PUT;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import trelloAPI.Specifications;
import trelloAPI.TestRestClient;

import static io.restassured.RestAssured.given;
import static trelloAPI.Globals.BOARD_NAME;
import static trelloAPI.Globals.SIDEBAR_BOARD_INFO;

public class UpdateShowSidebarBoardActionsPrefOnABoardTest {
    public String BOARD_ID;

    @Test
    public void updateShowSidebarBoard() {
        Specifications.installSpec(Specifications.requestSpec(),Specifications.responseSpecOK200());
        BOARD_ID = TestRestClient.createNewBoard(BOARD_NAME).get("id");
        JsonPath jsonResponse= given()
                .body(SIDEBAR_BOARD_INFO)
        .when()
                .put("/1/boards/{Id}/myPrefs/showSidebarBoardActions",BOARD_ID)
        .then()
                .log().all()
                .extract().jsonPath();

        Assert.assertTrue(jsonResponse.get("showSidebarMembers"));

        TestRestClient.deleteBoard(BOARD_ID);
    }
}


