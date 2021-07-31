package in.reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

public class UserCreationTests {

    @Test
    void verifyUserCreation() {
        given()
                .contentType(JSON)
                .body("{\"name\": \"testUser\"," +
                              "\"job\": \"testJob\"}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("testUser"),
                      "job", is("testJob"));
    }

    @Test
    void verifyUserCreationWithoutJobField() {
        given()
                .contentType(JSON)
                .body("{\"name\": \"testUser\"}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("testUser"));
    }

    @Test
    void verifyUserCreationWithoutNameField() {
        given()
                .contentType(JSON)
                .body("{\"job\": \"testJob\"}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("job", is("testJob"));
    }

    @Test
    void verifyUserCreationWithoutNameAndJobFields() {
        given()
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201);
    }

    @Test
    void verifyUnsuccessfulUserCreationWithoutNameAndJobValues() {
        given()
                .contentType(JSON)
                .body("{\"name\": ," +
                              "\"job\": }")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(400)
                .body(containsString("Bad Request"));
    }

    @Test
    void verifyUnsuccessfulUserCreationWithoutJobValue() {
        given()
                .contentType(JSON)
                .body("{\"name\": \"testUser\"," +
                              "\"job\": }")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(400)
                .body(containsString("Bad Request"));
    }

    @Test
    void verifyUnsuccessfulUserCreationWithoutNameValue() {
        given()
                .contentType(JSON)
                .body("{\"name\": ," +
                              "\"job\": \"testJob\"}")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(400)
                .body(containsString("Bad Request"));
    }
}