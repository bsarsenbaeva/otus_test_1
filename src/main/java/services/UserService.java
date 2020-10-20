package services;

import dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserService {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String CREATE_USER = "/user";
    private static final String GET_USER = "/user/{username}";

    RequestSpecification spec;

    public UserService() {
        spec = given()
                .contentType(ContentType.JSON)
                .baseUri(BASE_URL);
    }

    public Response addUserRequest(User user) {
        return given()
                .spec(spec)
                .with()
                .body(user)
                .when()
                .log().all()
                .post(CREATE_USER);
    }

    public Response getUserRequest(String userName) {
        return given()
                .spec(spec)
                .with()
                .pathParam("username",userName)
                .when()
                .log().all()
                .get(GET_USER);
    }

    public Response createTestUserWithData(){
        User user;
        Long id = 101L;
        String firstName = "Ivan";
        String lastName = "Petrov";
        String email = "Test@mail.ru";

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

        return addUserRequest(user);
    }
}
