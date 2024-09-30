package api.testutils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.Properties;

public class TestUtils {

    public static String getJsonPath(Response response, String key){
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }

    public static String getGlobalValue(String key) {
        try(var input = TestUtils.class.getClassLoader().getResourceAsStream("global.properties")){
            if(input == null){
                throw new IOException("File not found");
            }
            var prop = new Properties();
            prop.load(input);
            return prop.getProperty(key);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}
