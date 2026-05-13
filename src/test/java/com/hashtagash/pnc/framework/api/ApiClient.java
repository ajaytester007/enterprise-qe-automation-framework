package com.hashtagash.pnc.framework.api;

import com.hashtagash.pnc.framework.config.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.Map;

public class ApiClient {
    public Response post(String path, Map<String, ?> payload) {
        return RestAssured.given()
                .baseUri(Config.get("base.api.url"))
                .contentType("application/json")
                .body(payload)
                .log().all()
                .when().post(path)
                .then().log().all()
                .extract().response();
    }
    public Response get(String path) {
        return RestAssured.given().baseUri(Config.get("base.api.url")).log().all()
                .when().get(path).then().log().all().extract().response();
    }
}
