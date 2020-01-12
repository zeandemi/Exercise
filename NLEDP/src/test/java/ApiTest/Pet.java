package ApiTest;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class Pet {

    private static JsonPath jsonPath;
    private String path = System.getProperty("user.dir");
    private String responsePath = path + "\\src\\test\\resources\\";

    public static List<String> stringToJsonResponse(String response) {
        List<String> listOfDogs;
        jsonPath = new JsonPath(response);
        listOfDogs = jsonPath.getList("name");
        return listOfDogs;
    }

    @Test
    public void getPetByStatus() throws FileNotFoundException {
        String name = "doggie";
        ArrayList<String> numberOfDoggie = new ArrayList<String>();
        try {
            RestAssured.baseURI = "https://petstore.swagger.io";
            Response rs = given().get("/v2/pet/findByStatus?status=available").then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
                    .extract().response();
            String rawMessage = rs.asString();
            List<String> names = stringToJsonResponse(rawMessage);
            for (String s : names) {
                if (s.contains(name) || s.equalsIgnoreCase(name)) {
                    numberOfDoggie.add(s);
                }
                Assert.assertTrue(!numberOfDoggie.isEmpty());
                System.out.println(" There are " + numberOfDoggie.size() + "dogs called doggie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonParser jsonParser = new JsonParser();
        Object obj = jsonParser.parse(new FileReader(responsePath + "__files/pets.json"));
        JsonArray jsonArray = (JsonArray)obj;
        String savedResponse = jsonArray.toString();
        List<String> names1 = stringToJsonResponse(savedResponse);
        for (String s : names1) {
            if (s.contains(name) || s.equalsIgnoreCase(name)) {
                numberOfDoggie.add(s);
            }
            Assert.assertTrue(!numberOfDoggie.isEmpty());
        }
        System.out.println(" There are " + numberOfDoggie.size() + "dogs called doggie");
    }

    /*WIP*/
    public void exactNumberOfDogFromResponse() {
        stubFor(get(urlEqualTo("/v2/pet/findByStatus?status=available"))
                .willReturn(aResponse()
                        .withBodyFile(responsePath + "__files/pets.json")));
    }


}
