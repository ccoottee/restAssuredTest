import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class GetAndPostRemu {

    @Test
    public void testGet() {

        String headerKey = "x-session-web-id";
        String headerValue = "52990712";

        baseURI = "https://nbx-develop.test-nubox.com:40043";

        given()
                .header(headerKey, headerValue)
                .get("/remugrlbff/api/departamentos/44316")
                .then()
                .contentType("application/xml")
                .statusCode(409).log().all();
    }

    @Test
    public void testPost() {
        JSONObject request = new JSONObject();
        request.put("name", "cote");
        request.put("job", "qa");
        System.out.println(request.toJSONString());
        baseURI = "https://reqres.in/api";
        given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().all();

    }
}
