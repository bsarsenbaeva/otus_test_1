package user;

import dto.User;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

public class CreateUserTest extends UserTestBase {

    @Test
    public void createUserWithValidData() {
        Response response;
        User user;
        Long id = 101L;
        String firstName = "Ivan";
        String lastName = "Petrov";
        String email = "Test@mail.ru";
        String expectedType = "unknown";

        user = User.builder()
                .email(email)
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .password("Password")
                .phone("8-777-920-23-23")
                .username("Ivan123")
                .userStatus(10L)
                .build();

        response = userService.addUserRequest(user);

        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(5000L))
                .body("type", equalTo(expectedType))
                .body("message", comparesEqualTo(id.toString()))
                .body("firstName", equalTo(firstName));
    }

    @Test
    public void createUserWithoutData() {
        Response response;
        User user;
        Long id = 101L;
        String expectedType = "unknown";

        user = User.builder()
                .email("")
                .id(id)
                .firstName("")
                .lastName("")
                .password("")
                .phone("")
                .username("")
                .userStatus(10L)
                .build();

        response = userService.addUserRequest(user);

        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .time(lessThan(5000L))
                .body("type", equalTo(expectedType))
                .body("message", comparesEqualTo(id.toString()))
                .body("firstName", nullValue());
    }
}
