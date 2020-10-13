package user;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class GetUserTest extends UserTestBase{
    @Test
    public void getUser() {
        Response response;
        String userName = "string";
        Integer expectedId = 1292;

        response = userService.getUserRequest("string");

        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(5000L))
                .body("id", equalTo(expectedId))
                .body("firstName", equalTo(userName));
    }

    @Test
    public void getNotFoundUser() {
        Response response;
        String userName = "User1";
        String errorType = "error";
        String userNotFoundMessage = "User not found";

        response = userService.getUserRequest(userName);

        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                .time(lessThan(5000L))
                .body("type", equalTo(errorType))
                .body("message", comparesEqualTo(userNotFoundMessage));
    }
}
