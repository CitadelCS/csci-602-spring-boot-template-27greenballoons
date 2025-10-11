package edu.citadel.main;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VersionFeatureTest extends SpringIntegrationTest {

    @LocalServerPort
    private int port;

    private final TestRestTemplate rest = new TestRestTemplate();
    private ResponseEntity<String> latestResponse;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private String url(String path) {
        if (!path.startsWith("/")) path = "/" + path;
        return "http://localhost:" + port + path;
    }

    @When("the client calls {string}")
    public void the_client_calls(String path) {
        latestResponse = rest.getForEntity(url(path), String.class);
    }

    @Then("the client receives status code of {int}")
    public void the_client_receives_status_code_of(Integer status) {
        assertThat("HTTP status", latestResponse.getStatusCode().value(), is(status));
    }

    @And("the client receives server version {string}")
    public void the_client_receives_server_version(String expectedVersion) throws Exception {
        JsonNode json = objectMapper.readTree(latestResponse.getBody());
        // Common places for version: /version or /build/version for actuator /info
        String actual = null;
        if (json.has("version")) {
            actual = json.get("version").asText();
        } else if (json.has("build") && json.get("build").has("version")) {
            actual = json.get("build").get("version").asText();
        }
        assertThat("server version", actual, is(expectedVersion));
    }

    @And("the json at path {string} is {string}")
    public void the_json_at_path_is(String jsonPointer, String expected) throws Exception {
        // Supports simple JSON Pointer-like paths e.g. "$.status" or "$.build.version"
        JsonNode root = objectMapper.readTree(latestResponse.getBody());
        String[] parts = jsonPointer.replaceFirst("^\\$\\.", "").split("\\.");
        JsonNode cur = root;
        for (String p : parts) {
            if (cur == null) break;
            cur = cur.get(p);
        }
        String actual = (cur == null || cur.isNull()) ? null : (cur.isTextual() ? cur.asText() : cur.toString());
        assertThat("json at path " + jsonPointer, actual, is(expected));
    }
}


//package edu.citadel.main;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class VersionFeatureTest extends SpringIntegrationTest {
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @When("^the client calls {string}$")
//    public void the_client_calls_info(String path) {
//        executeGet(createURLWithPort(path));
//    }
//
//    @Then("^the client receives status code of (\\d+)$")
//    public void the_client_receives_status_code_of(int statusCode) {
//        int currentStatusCode = latestResponse.getStatusCode().value();
//        assertThat("status code is incorrect : " + latestResponse.getBody(),
//                currentStatusCode, is(statusCode));
//    }
//
//    @And("^the client receives server version (.+)$")
//    public void the_client_receives_server_version(String version) throws Exception {
//        JsonNode jsonResponse = objectMapper.readTree(latestResponse.getBody());
//        String actualVersion = jsonResponse.get("version").asText();
//        assertThat(actualVersion, is(version));
//    }
//}
