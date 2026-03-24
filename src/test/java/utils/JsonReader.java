package utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.LoginData;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class JsonReader {

    public static List<LoginData> getLoginDataList() {

        ObjectMapper mapper = new ObjectMapper();

        try {
            return Arrays.asList(
                    mapper.readValue(
                            new File("src/test/resources/testdata/login.json"),
                            LoginData[].class
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}