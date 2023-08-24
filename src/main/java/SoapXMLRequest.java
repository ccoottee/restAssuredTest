import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import org.apache.commons.io.IOUtils;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;


public class SoapXMLRequest {

    // http://dneonline.com/calculator.asmx?op=Add
    // https://www.youtube.com/watch?v=_SUD5A2LiJs&list=PLhW3qG5bs-L8xPrBwDv66cTMlFNeUPdJx&index=13
    // https://www.freeformatter.com/xpath-tester.html

    @Test
    public void validateSoapXml() throws IOException {

        File file = new File("./SoapRequest/test.xml");

        FileInputStream fileInputStream = new FileInputStream(file);
        String requestBody = IOUtils.toString(fileInputStream, "UTF-8");

        RestAssured.baseURI = "http://dneonline.com";
        RestAssured.given().
                contentType("text/xml").
                accept(ContentType.XML).
                body(requestBody).
                when().
                post("/calculator.asmx").
                then().
                statusCode(200).log().all().
                and().
                body("//*:AddResult.text()",equalTo("5")); // para buscar un resultado se hace mediante XPATH utilizar pagina freeformatter

    }
}
