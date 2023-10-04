package thirdTask;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;

import java.util.Base64;

public class AddComment {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://jira.moscow.alfaintra.net";

        String login = "login";
        String password = "password";
        String issueKey = "SFAIMP-1219";
        String comment = "Привет! \nКулешов Алексей был здесь.";
        String commentUrl = "/rest/api/2/issue/" + issueKey + "/comment";
        String requestBody = "{\"body\": \"" + comment + "\"}";

        SessionFilter session = new SessionFilter();
        System.out.println(commentUrl);
        Response loginResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .body("{ \"username\": \"" + login + "\", \"password\": \"" + password + "\" }")
                .filter(session)
                .post("/rest/auth/1/session");

        loginResponse.then().statusCode(200);

        Response commentResponse = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((login + ":" + password).getBytes()))
                .body(requestBody)
                .filter(session)
                .post(commentUrl);

        commentResponse.then().statusCode(201);

        /*
        Response getCommIdRespons = RestAssured.given()
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((login + ":" + password).getBytes()))
                .filter(session)
                .get("/rest/api/2/issue/" + issueKey + "/comment");
        getCommIdRespons.then().log().all();

        String commentId = "4374676";
        Response delCommRespons = RestAssured.given()
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((login + ":" + password).getBytes()))
                .delete("/rest/api/2/issue/" + issueKey + "/comment/" + commentId); //Получаю сообщение, нет прав "errorMessages": ["You do not have permission to delete comment with id: 4374676"

        delCommRespons.then().log().all();
        */
    }
}
