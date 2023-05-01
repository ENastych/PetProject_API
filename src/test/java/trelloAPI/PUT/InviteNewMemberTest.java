package trelloAPI.PUT;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import trelloAPI.Specifications;
import trelloAPI.TestRestClient;

import static io.restassured.RestAssured.given;
import static trelloAPI.Globals.BOARD_NAME;
import static trelloAPI.Globals.NEW_MEMBER_EMAIL;

public class InviteNewMemberTest {
    public String BOARD_ID;

    @Test
    public void inviteNewMemberToBoard() {
        Specifications.installSpec(Specifications.requestSpec(), Specifications.responseSpecOK200());
        BOARD_ID = TestRestClient.createNewBoard(BOARD_NAME).get("id");
        JsonPath jsonResponse = given()
                .queryParam("email", NEW_MEMBER_EMAIL)
        .when()
                .put("/1/boards/{id}/members",BOARD_ID)
        .then()
                .log().all()
                .extract().jsonPath();

        Assert.assertEquals(jsonResponse.get("members[0].fullName"), "Eduard Nastych");
        Assert.assertEquals(jsonResponse.get("id"), BOARD_ID);

        TestRestClient.deleteBoard(BOARD_ID);
    }
}
