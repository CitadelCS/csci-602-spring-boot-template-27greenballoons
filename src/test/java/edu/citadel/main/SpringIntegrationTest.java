package edu.citadel.main;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class SpringIntegrationTest {
    @LocalServerPort
    private int port;

    protected TestRestTemplate restTemplate = new TestRestTemplate();
    protected ResponseEntity<String> latestResponse;

    protected void executeGet(String url) {
        latestResponse = restTemplate.getForEntity(url, String.class);
    }

    protected String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
